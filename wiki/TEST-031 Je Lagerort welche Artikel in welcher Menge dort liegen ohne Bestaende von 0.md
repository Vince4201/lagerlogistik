---
type: test-case
id: TEST-031
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-028]
---

# TEST-031 — Je Lagerort: welche Artikel in welcher Menge dort liegen, ohne Bestände von 0

Automatisierter Systemtest (JUnit) zu REQ-028 (Anforderung A28 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Dazu die vier gemeinsamen Buchungen der Testklasse: #1 Eingang 8 × 1001 nach A-01, #2 Eingang 4 × 1002 nach A-01, #3 Umlagerung 3 × 1001 von A-01 nach B-01, #4 Ausgang 4 × 1002 von A-01 (danach liegt 1002 nirgends mehr).

## Ablauf

Automatisiert in `AuswertungenTest.bestandJeLagerort()`:

1. Bestände an A-01, über alle Lagerorte und an C-01 abfragen.

## Erwartung

- An A-01 liegt nur 1001 mit 5; 1002 mit Bestand 0 erscheint nicht.
- Über alle Lagerorte: A-01/1001 mit 5 und B-01/1001 mit 3.
- C-01 ist leer.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-031
- **Prüft:** REQ-028 (A28)
- **Herkunft:** `src/test/java/lager/service/AuswertungenTest.java`, Methode `bestandJeLagerort()` mit `@DisplayName("A28 Je Lagerort: welche Artikel in welcher Menge dort liegen, ohne Bestände von 0")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
