# Lagerverwaltung – Anforderungen

Erstes Dokument der Kette **anforderungen.md** → [datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md)
→ [spezifikation.md](spezifikation.md). Hier steht, *was* das Programm leisten soll, ohne Technik.

## Zweck

Eine Einzelplatz-Lagerverwaltung für das Terminal. Sie weiß jederzeit, welcher Artikel in welcher
Menge an welchem Lagerort liegt, und macht jede Bestandsänderung nachvollziehbar.

## Grundsätze

- Keine Preise. Es geht nur um Mengen.
- Ein Artikel kann an beliebig vielen Lagerorten liegen, ein Lagerort kann beliebig viele Artikel
  enthalten.
- Jede Bestandsänderung wird als Bewegung protokolliert. Bestand und Protokoll dürfen nie
  voneinander abweichen.
- Gebuchte Bewegungen werden nie geändert oder gelöscht. Fehler korrigiert man mit einer
  Korrekturbuchung.

## Buchungen

| Vorgang | Wirkung am Lagerort | Gesamtbestand | Mindestbestand prüfen? |
|---|---|---|---|
| Wareneingang | + Ziel | steigt | nein |
| Warenausgang | − Quelle | sinkt | ja |
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
- Zusätzlich gibt es eine Liste aller Artikel unter Mindestbestand und einen Hinweis beim
  Programmstart.

## Stammdaten

- Artikel: Nummer, Bezeichnung, Einheit (Standard Stk), Mindestbestand.
- Lagerort: Code (z. B. A-01-03), Bezeichnung (optional).
- Nummern und Codes sind eindeutig, unveränderlich und werden in Großbuchstaben gespeichert.
- Löschen geht nur, solange es keine Bewegungen gibt. Danach kann man nur noch deaktivieren, und
  das nur ohne Bestand. Deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen, können aber
  wieder aktiviert werden.
- Mengen sind ganze Zahlen, die Einheit ist nur ein Anzeigetext.

## Auswertungen

- Bestand je Artikel: Gesamtbestand und Verteilung auf die Lagerorte.
- Bestand je Lagerort: Was liegt wo?
- Artikel unter Mindestbestand, mit Fehlmenge.
- Bewegungsprotokoll, filterbar nach Artikel und Lagerort.

## Bedienung

- Menügeführt im Terminal.
- Falsche Eingaben werden erneut abgefragt.
- Eine leere Eingabe bricht den laufenden Vorgang ab.
- `?` zeigt eine Auswahlliste.
