# Lagerverwaltung – Spezifikation

Drittes Dokument der Kette [anforderungen.md](anforderungen.md) →
[datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md) → **spezifikation.md**. Die Bauanleitung:
welche Befehle es gibt, was jeder Befehl prüft, was er verändert und was er ausgibt. Die Nummern in
Klammern verweisen auf die Anforderungen.

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
  `bewegung` ab (A06).
- Die View `bestand (artikel, lagerort, menge)` berechnet Zugänge minus Abgänge je Artikel und
  Lagerort (A05).
- Jeder Befehl, der etwas verändert, läuft in einer Transaktion: ganz oder gar nicht.

## Eingaben

Diese Regeln gelten für alle Befehle.

| Feld | Regel |
|---|---|
| Artikelnummer, Lagerort-Code | höchstens 20 Zeichen; Buchstaben, Ziffern und `. _ / -`, keine Leerzeichen; wird in Großbuchstaben umgewandelt (A23) |
| Bezeichnung | höchstens 60 Zeichen; beim Artikel Pflicht, beim Lagerort optional |
| Einheit | höchstens 10 Zeichen, Vorgabe `Stk` |
| Bemerkung, Begründung | höchstens 100 Zeichen; die Begründung einer Korrektur ist Pflicht (A16) |
| Menge | ganze Zahl von 1 bis 1.000.000.000, bei Entnahmen höchstens der Bestand an der Quelle (A07, A12, A14) |
| Gezählter Bestand | ganze Zahl von 0 bis 1.000.000.000 |
| Mindestbestand | ganze Zahl von 0 bis 1.000.000.000, Vorgabe 0 |

- Leerzeichen am Anfang und Ende einer Eingabe werden entfernt.
- Ungültige Eingaben werden mit einer Fehlermeldung erneut abgefragt (A31).
- Bleibt ein Pflichtfeld leer, wird der Befehl abgebrochen: Ausgabe „Abgebrochen.“, zurück ins Menü
  (A32).
- Bei Feldern mit Vorgabe übernimmt eine leere Eingabe die Vorgabe. Eine nicht leere Vorgabe steht
  in eckigen Klammern hinter der Frage.
- Bei Filtern in den Auswertungen heißt eine leere Eingabe „alle“.
- `?` bei Artikel-Nr. oder Lagerort zeigt eine Auswahlliste (A33).
- Für Buchungen sind nur aktive Artikel und Lagerorte wählbar. Bei einem deaktivierten kommt die
  Meldung „… ist deaktiviert (reaktivieren unter 9 Stammdaten).“, und die Frage wird wiederholt
  (A13, A26).
- Ja/Nein-Fragen nehmen `j`, `ja`, `n` oder `nein` an.
- Verstößt ein Befehl gegen eine Regel, erscheint die Fehlermeldung, und es wird nichts geändert.
- Nach jedem Befehl, der nicht abgebrochen wurde, wartet das Programm auf Enter („Weiter mit
  Enter ...“), dann erscheint das Menü wieder.
- Das Ende der Eingabe (Strg+D) beendet das Programm wie Befehl 0.
- Ein- und Ausgabe sind UTF-8. Farbe gibt es nur in einem echten Terminal und nur, wenn `NO_COLOR`
  nicht gesetzt ist.

## Programmstart (A20)

- **Prüft:** ob es Artikel gibt und wie viele Artikel unter dem Mindestbestand liegen.
- **Verändert:** Fehlt die Datenbankdatei, wird sie angelegt, fehlende Tabellen ebenso.
- **Gibt aus:** den Pfad der Datenbank (mit „(neu angelegt)“, wenn sie neu ist), einen Tipp zur
  Bedienung, gegebenenfalls „Noch keine Artikel vorhanden …“ und „Hinweis: N Artikel liegen unter
  dem Mindestbestand (Menüpunkt 7).“, dann das Hauptmenü.

## Hauptmenü (A02)

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

Eine andere Eingabe ergibt „Bitte eine Zahl von 0 bis 9 eingeben.“

### 1 Wareneingang (A08, A12, A13)

- **Fragt:** Artikel-Nr., Nach Lagerort, Menge, Bemerkung (optional, z. B. Lieferschein).
- **Prüft:** Artikel und Lagerort existieren und sind aktiv, Menge > 0.
- **Verändert:** neue Bewegung `EINGANG` mit `nach_lagerort` = Ziel.
- **Gibt aus:** nach der Wahl des Artikels seinen Gesamtbestand, Mindestbestand und Bestand je
  Lagerort; nach der Buchung `Gebucht (#<Nr>): Eingang <Menge> <Einheit> <Artikel> nach <Lagerort>`
  und den neuen Gesamtbestand.

### 2 Warenausgang (A09, A12 bis A14, A17, A18)

- **Fragt:** Artikel-Nr., Aus Lagerort, Menge, Bemerkung (optional, z. B. Auftrag).
- **Prüft:** Artikel und Lagerort existieren und sind aktiv; der Artikel hat überhaupt Bestand
  (sonst Fehlermeldung); am gewählten Lagerort liegt etwas von ihm (sonst Frage wiederholen);
  1 ≤ Menge ≤ Bestand dort.
- **Verändert:** neue Bewegung `AUSGANG` mit `von_lagerort` = Quelle.
- **Gibt aus:** wie beim Wareneingang, mit `Ausgang … aus <Lagerort>`. Liegt der Gesamtbestand danach
  unter dem Mindestbestand, statt des Gesamtbestands der Block „WARNUNG: Mindestbestand
  unterschritten“ mit Artikel, Gesamtbestand, Mindestbestand und Fehlmenge.

### 3 Umlagerung (A10, A12 bis A15, A19)

- **Fragt:** Artikel-Nr., Von Lagerort, Nach Lagerort, Menge, Bemerkung (optional).
- **Prüft:** es gibt mindestens zwei aktive Lagerorte (sonst Fehlermeldung); der Artikel hat
  Bestand; an der Quelle liegt etwas von ihm; Ziel ≠ Quelle (sonst Ziel neu abfragen);
  1 ≤ Menge ≤ Bestand an der Quelle.
- **Verändert:** neue Bewegung `UMLAGERUNG` mit `von_lagerort` = Quelle und `nach_lagerort` = Ziel.
- **Gibt aus:** `Gebucht (#<Nr>): Umlagerung … von <Quelle> nach <Ziel>` und den unveränderten
  Gesamtbestand. Nie eine Mindestbestandswarnung.

### 4 Korrektur / Inventur (A11, A16 bis A18)

- **Fragt:** Artikel-Nr., Lagerort, Gezählter Bestand, Begründung (Pflicht).
- **Prüft:** Artikel und Lagerort existieren und sind aktiv; gezählter Bestand ≥ 0; ist er gleich dem
  gebuchten, endet der Befehl mit „Kein Unterschied, es wird nichts gebucht.“, bevor die Begründung
  abgefragt wird.
- **Verändert:** neue Bewegung `KORREKTUR` über die Differenz: mehr gezählt → `nach_lagerort`,
  weniger gezählt → `von_lagerort`.
- **Gibt aus:** wie beim Wareneingang den Bestand des Artikels, vor der Zählung den gebuchten
  Bestand am Lagerort; nach der Buchung
  `Gebucht (#<Nr>): Korrektur ±<Differenz> <Einheit> <Artikel> an <Lagerort>`. Bei einer Korrektur
  nach unten wie beim Warenausgang gegebenenfalls die Warnung.

### 5 Bestand je Artikel (A27)

- **Fragt:** Artikel-Nr. als Filter (leer = alle).
- **Verändert:** nichts.
- **Gibt aus:** mit Filter Gesamtbestand, Mindestbestand und Bestand je Lagerort dieses Artikels.
  Ohne Filter eine Tabelle aller aktiven Artikel: Artikel, Bezeichnung, Bestand, Mindestbestand,
  Einheit, Status („!! unter Mindestbestand“).

### 6 Bestand je Lagerort (A28)

- **Fragt:** Lagerort als Filter (leer = alle).
- **Verändert:** nichts.
- **Gibt aus:** Tabelle Lagerort, Artikel, Bezeichnung, Menge, Einheit mit allen Beständen ungleich 0.
  Ohne Filter danach „Leere Lagerorte: …“ mit den aktiven Lagerorten ohne Bestand.

### 7 Artikel unter Mindestbestand (A29)

- **Verändert:** nichts.
- **Gibt aus:** Tabelle Artikel, Bezeichnung, Bestand, Mindestbestand, Fehlmenge, Einheit für alle
  aktiven Artikel mit Gesamtbestand < Mindestbestand, oder „Kein Artikel liegt unter dem
  Mindestbestand.“

### 8 Bewegungsprotokoll (A30)

- **Fragt:** Artikel-Nr. und Lagerort als Filter (leer = alle). Der Lagerort trifft Bewegungen, bei
  denen er Von- oder Nach-Lagerort ist.
- **Verändert:** nichts.
- **Gibt aus:** Tabelle Nr, Zeitpunkt, Art, Artikel, Menge, Von, Nach, Bemerkung in
  Buchungsreihenfolge, danach die Anzahl. Die Menge steht mit Vorzeichen (+ Zugang, − Abgang), bei
  Umlagerungen ohne.

### 9 Stammdaten

Öffnet das Stammdatenmenü.

### 0 Beenden

Gibt „Auf Wiedersehen.“ aus und beendet das Programm.

## Stammdatenmenü (A21 bis A26)

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

0 oder eine leere Eingabe führt zurück ins Hauptmenü, eine andere Eingabe ergibt „Bitte eine Zahl
von 0 bis 8 eingeben.“

### 1 Artikel anzeigen

- **Gibt aus:** Tabelle aller Artikel, auch der deaktivierten: Artikel, Bezeichnung, Einheit,
  Mindestbestand, Status (aktiv/deaktiviert).

### 2 Artikel anlegen (A21, A23)

- **Fragt:** Artikel-Nr., Bezeichnung, Einheit [Stk], Mindestbestand [0].
- **Prüft:** erlaubte Zeichen der Nummer; die Nummer gibt es noch nicht (sonst Frage wiederholen).
- **Verändert:** neuer aktiver Artikel.
- **Gibt aus:** „Angelegt: …“ und, wenn der Bestand schon unter dem Mindestbestand liegt, einen
  Hinweis.

### 3 Artikel bearbeiten / reaktivieren (A23, A26)

- **Fragt:** Artikel-Nr. (auch deaktivierte); bei einem deaktivierten „Wieder aktivieren? (j/n)“;
  dann Bezeichnung, Einheit, Mindestbestand mit dem bisherigen Wert als Vorgabe. Die Nummer ist nicht
  änderbar.
- **Verändert:** gegebenenfalls `aktiv` = 1; Bezeichnung, Einheit, Mindestbestand.
- **Gibt aus:** „Reaktiviert.“, „Gespeichert.“ und bei einem aktiven Artikel unter dem
  Mindestbestand einen Hinweis.

### 4 Artikel löschen / deaktivieren (A24, A25)

- **Fragt:** Artikel-Nr. (nur aktive), dann eine Bestätigung (j/n).
- **Prüft:** Gesamtbestand = 0 (sonst Fehlermeldung „… hat noch N … Bestand“). Gibt es Bewegungen zum
  Artikel, wird Deaktivieren angeboten, sonst endgültiges Löschen.
- **Verändert:** löscht den Artikel oder setzt `aktiv` = 0. Bei „n“ nichts.
- **Gibt aus:** „Gelöscht.“, „Deaktiviert.“ oder „Nichts geändert.“

### 5 Lagerorte anzeigen

- **Gibt aus:** Tabelle aller Lagerorte, auch der deaktivierten: Lagerort, Bezeichnung, Status.

### 6 Lagerort anlegen (A22, A23)

- **Fragt:** Lagerort-Code, Bezeichnung (optional).
- **Prüft:** erlaubte Zeichen des Codes; den Code gibt es noch nicht (sonst Frage wiederholen).
- **Verändert:** neuer aktiver Lagerort.
- **Gibt aus:** „Angelegt: Lagerort …“.

### 7 Lagerort bearbeiten / reaktivieren (A23, A26)

- **Fragt:** Lagerort (auch deaktivierte); bei einem deaktivierten „Wieder aktivieren? (j/n)“; dann
  die Bezeichnung mit dem bisherigen Wert als Vorgabe. Der Code ist nicht änderbar.
- **Verändert:** gegebenenfalls `aktiv` = 1; die Bezeichnung.
- **Gibt aus:** „Reaktiviert.“, „Gespeichert.“

### 8 Lagerort löschen / deaktivieren (A24, A25)

- **Fragt:** Lagerort (nur aktive), dann eine Bestätigung (j/n).
- **Prüft:** am Lagerort liegt kein Artikel mehr (sonst Fehlermeldung „… liegen noch N Artikel“).
  Gibt es Bewegungen zum Lagerort, wird Deaktivieren angeboten, sonst endgültiges Löschen.
- **Verändert:** löscht den Lagerort oder setzt `aktiv` = 0. Bei „n“ nichts.
- **Gibt aus:** „Gelöscht.“, „Deaktiviert.“ oder „Nichts geändert.“

## Sortierung und Anzeige

- Artikel und Lagerorte werden zuerst nach dem Zahlenwert von Nummer bzw. Code sortiert
  (`CAST(... AS INTEGER)`), dann alphabetisch, damit 2 vor 10 kommt.
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

Automatische Tests gibt es noch nicht. `beispieldaten.txt` enthält die Eingaben für einen kompletten
Durchlauf: 6 Lagerorte, 10 Artikel, Eingänge, Umlagerungen, Ausgänge und eine Inventurkorrektur.

```bash
rm -f test.db && ./lager.sh test.db < beispieldaten.txt
sqlite3 test.db "SELECT * FROM bestand"
```
