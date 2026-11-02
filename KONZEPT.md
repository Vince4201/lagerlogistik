# Lagerverwaltung – Konzept Version 1

Einzelplatz-Lagerverwaltung für das Terminal, geschrieben in Java mit SQLite.

## Starten

```bash
./lager.sh            # baut bei Bedarf und startet; die Daten liegen in lager.db
./lager.sh test.db    # mit einer anderen Datenbankdatei
```

Voraussetzungen: Java 21 oder neuer und Maven (Technikpaket `clawbook-java`).
In die Datenbank schauen: `sqlite3 lager.db "SELECT * FROM bestand"`.

## Grundidee

- Keine Preise.
- Ein Artikel kann an beliebig vielen Lagerorten liegen, ein Lagerort kann beliebig viele Artikel enthalten.
- Jede Bestandsänderung ist eine Bewegung im Protokoll. Den Bestand berechnet die View `bestand`
  aus dem Protokoll (Zugänge minus Abgänge). Eine eigene Bestandstabelle gibt es nicht, deshalb
  können Bestand und Protokoll nie voneinander abweichen.
- Bewegungen werden nie geändert oder gelöscht (in der Datenbank per Trigger abgesichert).
  Fehler korrigiert man mit einer Korrekturbuchung.

## Bewegungsarten

| Art | Wirkung am Lagerort | Gesamtbestand | Mindestbestand prüfen? |
|---|---|---|---|
| Eingang | + Ziel | steigt | nein |
| Ausgang | − Quelle | sinkt | ja |
| Umlagerung | − Quelle, + Ziel | unverändert | nein |
| Korrektur / Inventur | gezählter Bestand wird übernommen, Differenz ± | ± | ja, wenn er sinkt |

## Regeln

**Harte Regeln:** Die Buchung wird abgelehnt, wenn eine davon verletzt ist.

- Die Menge ist eine ganze Zahl größer 0.
- Artikel und Lagerort existieren und sind aktiv.
- Entnommen wird höchstens, was an diesem Lagerort liegt. Kein Lagerort wird negativ.
- Bei einer Umlagerung sind Quelle und Ziel verschieden.
- Eine Korrektur braucht eine Begründung.

**Weiche Regel Mindestbestand:** Die Buchung wird ausgeführt, danach kommt eine Warnung.

- Der Mindestbestand gilt je Artikel für den Gesamtbestand über alle Lagerorte.
- Gewarnt wird, wenn Gesamtbestand < Mindestbestand. Mindestbestand 0 heißt: keine Überwachung.
- Geprüft wird nach jeder Buchung, die den Gesamtbestand senkt (Ausgang, Korrektur nach unten).
  Die Warnung kommt bei jedem solchen Vorgang, solange der Artikel unter dem Mindestbestand liegt.
- Eine Umlagerung löst nie eine Warnung aus.
- Zusätzlich gibt es die Liste unter Menüpunkt 7 und einen Hinweis beim Programmstart.

## Stammdaten

- Artikel: Nummer, Bezeichnung, Einheit (Standard Stk), Mindestbestand.
- Lagerort: Code (z. B. A-01-03), Bezeichnung (optional).
- Nummern und Codes sind eindeutig, unveränderlich und werden in Großbuchstaben gespeichert.
- Löschen geht nur, solange es keine Bewegungen gibt. Danach kann man nur noch deaktivieren, und
  das nur ohne Bestand. Deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen; reaktiviert
  werden sie unter „bearbeiten“.
- Mengen sind ganze Zahlen, die Einheit ist nur ein Anzeigetext.

## Bedienung

Das Programm ist menügeführt. Falsche Eingaben werden erneut abgefragt, eine leere Eingabe bricht
den laufenden Vorgang ab, `?` zeigt eine Auswahlliste.

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

## Aufbau des Codes

| Paket | Inhalt |
|---|---|
| `lager.model` | Artikel, Lagerort, Bewegung und weitere Records |
| `lager.db` | SQLite-Zugriff: Schema mit View `bestand`, Repositories |
| `lager.service` | Fachlogik: Buchungen, Regeln, Mindestbestand |
| `lager.ui` | Menüs und Dialoge im Terminal |
