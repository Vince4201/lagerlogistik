---
type: test-case
id: TEST-033
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-030]
---

# TEST-033 — Bewegungsprotokoll in Buchungsreihenfolge, filterbar nach Artikel und Lagerort

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-030 (Anforderung A30 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Dazu die vier gemeinsamen Buchungen der Testklasse: #1 Eingang 8 × 1001 nach A-01, #2 Eingang 4 × 1002 nach A-01, #3 Umlagerung 3 × 1001 von A-01 nach B-01, #4 Ausgang 4 × 1002 von A-01 (danach liegt 1002 nirgends mehr).

## Ablauf

Automatisiert in `AuswertungenTest.bewegungsprotokoll()`:

1. Bewegungsprotokoll abfragen: ungefiltert, nach Artikel 1001, nach Lagerort B-01, nach 1002 an A-01 und nach 1002 an B-01.

## Erwartung

- Ungefiltert: die Bewegungen 1, 2, 3, 4 in dieser Reihenfolge.
- Nach 1001: 1, 3. Nach B-01: 3 (B-01 als Ziel einer Umlagerung).
- 1002 an A-01: 2, 4. 1002 an B-01: keine Bewegung.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-033
- **Prüft:** REQ-030 (A30)
- **Herkunft:** `src/test/java/lager/service/AuswertungenTest.java`, Methode `bewegungsprotokoll()` mit `@DisplayName("A30 Bewegungsprotokoll in Buchungsreihenfolge, filterbar nach Artikel und Lagerort")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
