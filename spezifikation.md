# Lagerverwaltung – Spezifikation

Drittes Dokument der Kette [anforderungen.md](anforderungen.md) →
[datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md) → **spezifikation.md**. Hier steht, wie
das Programm technisch gebaut ist, genau genug, um es umzusetzen und zu prüfen.

## Technik und Start

- Java 21, SQLite über den Treiber `org.xerial:sqlite-jdbc`, Build mit Maven.
- Maven packt Programm und Treiber in eine einzige ausführbare Datei `target/lagerverwaltung.jar`.

```bash
./lager.sh            # baut bei Bedarf und startet; die Daten liegen in lager.db
./lager.sh test.db    # mit einer anderen Datenbankdatei
```

`lager.sh` baut neu, wenn `pom.xml` oder etwas unter `src/` neuer ist als die JAR-Datei.

Voraussetzungen: Java 21 oder neuer und Maven (Technikpaket `clawbook-java`).
In die Datenbank schauen: `sqlite3 lager.db "SELECT * FROM bestand"`.

## Datenbankschema

Das Schema wird beim Öffnen angelegt, soweit es fehlt (`lager.db.Datenbank`). Fremdschlüssel sind
eingeschaltet (`PRAGMA foreign_keys = ON`).

| Tabelle | Spalten |
|---|---|
| `artikel` | `nummer` TEXT PK, `bezeichnung` TEXT, `einheit` TEXT, `mindestbestand` INTEGER ≥ 0 (Standard 0), `aktiv` 0/1 (Standard 1) |
| `lagerort` | `code` TEXT PK, `bezeichnung` TEXT, `aktiv` 0/1 (Standard 1) |
| `bewegung` | `nr` INTEGER PK AUTOINCREMENT, `zeitpunkt` TEXT, `art` TEXT, `artikel` → `artikel`, `menge` INTEGER > 0, `von_lagerort` → `lagerort`, `nach_lagerort` → `lagerort`, `bemerkung` TEXT (Standard leer) |

- `art` ist `EINGANG`, `AUSGANG`, `UMLAGERUNG` oder `KORREKTUR`. Ein CHECK erzwingt die Belegung von
  `von_lagerort` und `nach_lagerort` nach der Tabelle in datenmodell-und-ablaeufe.md.
- Die Trigger `bewegung_nicht_aendern` und `bewegung_nicht_loeschen` lehnen UPDATE und DELETE auf
  `bewegung` ab.
- Die View `bestand (artikel, lagerort, menge)` berechnet Zugänge minus Abgänge je Artikel und
  Lagerort.
- Jede Buchung und jede Stammdatenänderung läuft in einer Transaktion.

## Eingaben

| Feld | Regel |
|---|---|
| Artikelnummer, Lagerort-Code | höchstens 20 Zeichen; Buchstaben, Ziffern und `. _ / -`, keine Leerzeichen; wird in Großbuchstaben umgewandelt |
| Bezeichnung | höchstens 60 Zeichen; beim Artikel Pflicht, beim Lagerort optional |
| Einheit | höchstens 10 Zeichen, Vorgabe `Stk` |
| Bemerkung, Begründung | höchstens 100 Zeichen; die Begründung einer Korrektur ist Pflicht |
| Menge | ganze Zahl von 1 bis 1.000.000.000, bei Entnahmen höchstens der Bestand an der Quelle |
| Gezählter Bestand | ganze Zahl von 0 bis 1.000.000.000 |
| Mindestbestand | ganze Zahl von 0 bis 1.000.000.000, Vorgabe 0 |

- Leerzeichen am Anfang und Ende einer Eingabe werden entfernt.
- Ungültige Eingaben werden mit einer Fehlermeldung erneut abgefragt.
- Bleibt ein Pflichtfeld leer, wird der laufende Vorgang abgebrochen („Abgebrochen.“).
- Bei Feldern mit Vorgabe übernimmt eine leere Eingabe die Vorgabe. Eine nicht leere Vorgabe steht
  in eckigen Klammern hinter der Frage.
- Bei Filtern in den Auswertungen heißt eine leere Eingabe „alle“.
- `?` bei Artikel-Nr. oder Lagerort zeigt eine Auswahlliste.
- Das Ende der Eingabe (Strg+D) beendet das Programm wie Menüpunkt 0.
- Ein- und Ausgabe sind UTF-8. Farbe gibt es nur in einem echten Terminal und nur, wenn `NO_COLOR`
  nicht gesetzt ist.

## Menüs

```
=== LAGERVERWALTUNG ===
  1  Wareneingang
  2  Warenausgang
  3  Umlagerung
  4  Korrektur / Inventur
  5  Bestand je Artikel
  6  Bestand je Lagerort
  7  Artikel unter Mindestbestand
  8  Bewegungsprotokoll
  9  Stammdaten
  0  Beenden
```

```
--- Stammdaten ---
  1  Artikel anzeigen
  2  Artikel anlegen
  3  Artikel bearbeiten / reaktivieren
  4  Artikel löschen / deaktivieren
  5  Lagerorte anzeigen
  6  Lagerort anlegen
  7  Lagerort bearbeiten / reaktivieren
  8  Lagerort löschen / deaktivieren
  0  Zurück
```

## Ausgaben

- Nach einer Buchung: `Gebucht (#<Nr>): <Beschreibung>`, danach der neue Gesamtbestand oder, wenn
  der Mindestbestand unterschritten ist, ein Warnblock mit Gesamtbestand, Mindestbestand und
  Fehlmenge.
- Bestandslisten zeigen nur Bestände ungleich 0. „Bestand je Lagerort“ ohne Filter nennt zusätzlich
  die leeren aktiven Lagerorte.
- Artikel und Lagerorte werden zuerst nach dem Zahlenwert von Nummer bzw. Code sortiert
  (`CAST(... AS INTEGER)`), dann alphabetisch, damit 2 vor 10 kommt.
- Das Bewegungsprotokoll steht in Buchungsreihenfolge.
- Zeitpunkte werden sekundengenau gespeichert und als `TT.MM.JJJJ HH:MM` angezeigt.

## Aufbau des Codes

| Paket | Inhalt |
|---|---|
| `lager` | `Main`: Startpunkt, öffnet die Datenbank und startet die Konsole |
| `lager.model` | Artikel, Lagerort, Bewegung und weitere Records |
| `lager.db` | SQLite-Zugriff: Schema mit View `bestand`, Repositories |
| `lager.service` | Fachlogik: Buchungen, Regeln, Mindestbestand |
| `lager.ui` | Menüs und Dialoge im Terminal |

## Prüfen

`beispieldaten.txt` enthält die Eingaben für einen kompletten Durchlauf: 6 Lagerorte, 10 Artikel,
Eingänge, Umlagerungen, Ausgänge und eine Inventurkorrektur.

```bash
rm -f test.db && ./lager.sh test.db < beispieldaten.txt
sqlite3 test.db "SELECT * FROM bestand"
```
