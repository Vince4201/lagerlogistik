# Lagerverwaltung – Anforderungen

Erstes Dokument der Kette **anforderungen.md** → [datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md)
→ [spezifikation.md](spezifikation.md). Hier steht, *was* das Programm können muss, ohne Technik.

Je Anforderung ein Satz. Die Nummern bleiben fest, auch wenn Anforderungen dazukommen oder
wegfallen, denn die späteren Dokumente verweisen darauf. Alle Anforderungen gelten für Version 1.

## Rahmen

- **A01** Die Lagerverwaltung ist eine Einzelplatz-Anwendung: ein Benutzer an einem Rechner.
- **A02** Sie wird im Terminal über Menüs bedient.
- **A03** Sie verwaltet nur Mengen, Preise kommen nicht vor.

## Lager und Bestand

- **A04** Ein Artikel kann an beliebig vielen Lagerorten liegen, und ein Lagerort kann beliebig
  viele Artikel enthalten.
- **A05** Jede Bestandsänderung wird als Bewegung protokolliert, und der Bestand ergibt sich allein
  aus den Bewegungen.
- **A06** Gebuchte Bewegungen werden nie geändert oder gelöscht; Fehler werden mit einer
  Korrekturbuchung ausgeglichen.
- **A07** Mengen sind ganze Zahlen, die Einheit ist nur ein Anzeigetext.

## Buchungen

- **A08** Ein Wareneingang erhöht den Bestand eines Artikels an einem Ziel-Lagerort.
- **A09** Ein Warenausgang senkt den Bestand eines Artikels an einem Quell-Lagerort.
- **A10** Eine Umlagerung bringt eine Menge eines Artikels von einem Lagerort an einen anderen,
  ohne den Gesamtbestand zu ändern.
- **A11** Bei einer Korrektur (Inventur) wird der gezählte Bestand an einem Lagerort übernommen und
  die Differenz zum gebuchten Bestand gebucht.

## Harte Regeln

Ist eine davon verletzt, wird die Buchung abgelehnt.

- **A12** Die Menge einer Buchung ist größer als 0.
- **A13** Gebucht wird nur auf Artikel und Lagerorte, die existieren und aktiv sind.
- **A14** Entnommen wird höchstens, was an dem Lagerort liegt, sodass kein Lagerort negativ wird.
- **A15** Bei einer Umlagerung sind Quelle und Ziel verschieden.
- **A16** Eine Korrektur braucht eine Begründung.

## Mindestbestand

Eine weiche Regel: Die Buchung wird ausgeführt, danach kommt eine Warnung.

- **A17** Jeder Artikel hat einen Mindestbestand, der für seinen Gesamtbestand über alle Lagerorte
  gilt; 0 heißt keine Überwachung.
- **A18** Nach jeder Buchung, die den Gesamtbestand senkt, wird gewarnt, wenn er danach unter dem
  Mindestbestand liegt, auch wenn er schon vorher darunter lag.
- **A19** Eine Umlagerung löst nie eine Mindestbestandswarnung aus.
- **A20** Beim Programmstart weist das Programm darauf hin, wenn Artikel unter dem Mindestbestand
  liegen.

## Stammdaten

- **A21** Ein Artikel hat eine Nummer, eine Bezeichnung, eine Einheit (Standard Stk) und einen
  Mindestbestand.
- **A22** Ein Lagerort hat einen Code (z. B. A-01-03) und eine optionale Bezeichnung.
- **A23** Nummern und Codes sind eindeutig, unveränderlich und werden in Großbuchstaben gespeichert.
- **A24** Artikel und Lagerorte lassen sich löschen, solange es zu ihnen keine Bewegungen gibt.
- **A25** Gibt es Bewegungen, lassen sie sich nur noch deaktivieren, und das nur, wenn sie keinen
  Bestand haben.
- **A26** Deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen, aber wieder aktivieren.

## Auswertungen

- **A27** Das Programm zeigt je Artikel den Gesamtbestand und die Verteilung auf die Lagerorte.
- **A28** Das Programm zeigt je Lagerort, welche Artikel in welcher Menge dort liegen.
- **A29** Das Programm listet alle Artikel unter dem Mindestbestand mit ihrer Fehlmenge.
- **A30** Das Programm zeigt das Bewegungsprotokoll, filterbar nach Artikel und Lagerort.

## Bedienung

- **A31** Falsche Eingaben werden erneut abgefragt.
- **A32** Eine leere Eingabe bricht den laufenden Vorgang ab.
- **A33** Mit `?` lässt sich eine Auswahlliste anzeigen.
