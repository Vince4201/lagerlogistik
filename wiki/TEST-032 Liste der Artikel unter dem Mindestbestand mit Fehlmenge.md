---
type: test-case
id: TEST-032
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-029]
---

# TEST-032 — Liste der Artikel unter dem Mindestbestand mit Fehlmenge

Automatisierter Systemtest (JUnit) zu REQ-029 (Anforderung A29 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Dazu die vier gemeinsamen Buchungen der Testklasse: #1 Eingang 8 × 1001 nach A-01, #2 Eingang 4 × 1002 nach A-01, #3 Umlagerung 3 × 1001 von A-01 nach B-01, #4 Ausgang 4 × 1002 von A-01 (danach liegt 1002 nirgends mehr).

## Ablauf

Automatisiert in `AuswertungenTest.listeUnterMindestbestand()`:

1. Liste der Artikel unter dem Mindestbestand abfragen.

## Erwartung

- Die Liste enthält genau einen Eintrag: 1001 mit der Fehlmenge 2.
- 1002 fehlt, weil er keinen Mindestbestand hat.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-032
- **Prüft:** REQ-029 (A29)
- **Herkunft:** `src/test/java/lager/service/AuswertungenTest.java`, Methode `listeUnterMindestbestand()` mit `@DisplayName("A29 Liste der Artikel unter dem Mindestbestand mit Fehlmenge")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
