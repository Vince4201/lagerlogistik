---
type: function-description
id: FUNC-002
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-002, REQ-007, REQ-012, REQ-013, REQ-014, REQ-016, REQ-023, REQ-026, REQ-031, REQ-032, REQ-033]
---

# FUNC-002 — Bedienung im Terminal

Die Lagerverwaltung wird im Terminal über nummerierte Menüs bedient.
Für alle Befehle gelten dieselben Eingaberegeln: Feldformate, Vorgaben,
Abbruch, Auswahlliste, Fehlermeldungen und die Rückkehr ins Menü.
Listen und Zeitpunkte erscheinen nach festen Anzeigeregeln.

## Hauptmenü

Hauptmenü nach REQ-002:

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

- Eine andere Eingabe ergibt „Bitte eine Zahl von 0 bis 9 eingeben.“
- Punkte 1 bis 4: [[FUNC-004 Wareneingang]],
  [[FUNC-005 Warenausgang]], [[FUNC-006 Umlagerung]],
  [[FUNC-007 Korrektur und Inventur]].
- Punkte 5 bis 8: [[FUNC-008 Auswertungen]].
- Punkt 9 öffnet das Stammdatenmenü: [[FUNC-009 Stammdatenpflege]].
- Punkt 0 gibt „Auf Wiedersehen.“ aus und beendet das Programm.

## Eingaberegeln

Diese Regeln gelten für alle Befehle.

| Feld | Regel |
|---|---|
| Artikelnummer, Lagerort-Code | höchstens 20 Zeichen; Buchstaben, Ziffern und `. _ / -`, keine Leerzeichen; wird in Großbuchstaben umgewandelt (REQ-023) |
| Bezeichnung | höchstens 60 Zeichen; beim Artikel Pflicht, beim Lagerort optional |
| Einheit | höchstens 10 Zeichen, Vorgabe `Stk` |
| Bemerkung, Begründung | höchstens 100 Zeichen; die Begründung einer Korrektur ist Pflicht (REQ-016) |
| Menge | ganze Zahl von 1 bis 1.000.000.000, bei Entnahmen höchstens der Bestand an der Quelle (REQ-007, REQ-012, REQ-014) |
| Gezählter Bestand | ganze Zahl von 0 bis 1.000.000.000 |
| Mindestbestand | ganze Zahl von 0 bis 1.000.000.000, Vorgabe 0 |

- Leerzeichen am Anfang und Ende einer Eingabe werden entfernt.
- Ungültige Eingaben werden mit einer Fehlermeldung erneut abgefragt
  (REQ-031).
- Bleibt ein Pflichtfeld leer, wird der Befehl abgebrochen: Ausgabe
  „Abgebrochen.“, zurück ins Menü (REQ-032).
- Bei Feldern mit Vorgabe übernimmt eine leere Eingabe die Vorgabe. Eine
  nicht leere Vorgabe steht in eckigen Klammern hinter der Frage.
- Bei Filtern in den Auswertungen heißt eine leere Eingabe „alle“.
- `?` bei Artikel-Nr. oder Lagerort zeigt eine Auswahlliste (REQ-033).
- Für Buchungen sind nur aktive Artikel und Lagerorte wählbar. Bei einem
  deaktivierten kommt die Meldung „… ist deaktiviert (reaktivieren unter
  9 Stammdaten).“, und die Frage wird wiederholt (REQ-013, REQ-026).
- Ja/Nein-Fragen nehmen `j`, `ja`, `n` oder `nein` an.
- Verstößt ein Befehl gegen eine Regel, erscheint die Fehlermeldung, und
  es wird nichts geändert.
- Nach jedem Befehl, der nicht abgebrochen wurde, wartet das Programm auf
  Enter („Weiter mit Enter ...“), dann erscheint das Menü wieder.
- Das Ende der Eingabe (Strg+D) beendet das Programm wie Befehl 0.
- Ein- und Ausgabe sind UTF-8. Farbe gibt es nur in einem echten
  Terminal und nur, wenn `NO_COLOR` nicht gesetzt ist.

## Sortierung und Anzeige

- Artikel und Lagerorte erscheinen zuerst nach dem Zahlenwert von Nummer
  bzw. Code sortiert, dann alphabetisch, damit 2 vor 10 kommt.
- Zeitpunkte werden als `TT.MM.JJJJ HH:MM` angezeigt.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-002)
- Basiert auf: REQ-002, REQ-007, REQ-012, REQ-013, REQ-014, REQ-016,
  REQ-023, REQ-026, REQ-031, REQ-032, REQ-033 ([[Anforderungen]])
- Siehe auch: [[FUNC-001 Programmstart]] — vor dem ersten Hauptmenü;
  [[FUNC-003 Gemeinsamer Buchungsablauf]] — Regeln jeder Buchung
- Erwähnt in: [[SPEC-002 Sortierung und Zeitpunkte]] — Umsetzung der
  Anzeigeregeln; [[ARC-003 Paketaufbau des Codes]] — Paket `lager.ui`
- Herkunft (Bestand): [spezifikation.md](../spezifikation.md)
  §Eingaben, §Hauptmenü (Menübild, 9 Stammdaten, 0 Beenden),
  §Sortierung und Anzeige (sichtbarer Teil)
