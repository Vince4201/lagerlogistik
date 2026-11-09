---
type: test-case
id: TEST-019
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-017]
---

# TEST-019 — Mindestbestand 0 heißt: keine Überwachung

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-017 (Anforderung A17 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `MindestbestandTest.mindestbestandNullWirdNichtUeberwacht()`:

1. Eingang 3 × 1002 nach A-01.
2. Ausgang 3 × 1002 von A-01.
3. Liste der Artikel unter dem Mindestbestand abfragen.

## Erwartung

- Der Ausgang warnt nicht.
- 1002 steht nicht in der Liste.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-019
- **Prüft:** REQ-017 (A17)
- **Herkunft:** `src/test/java/lager/service/MindestbestandTest.java`, Methode `mindestbestandNullWirdNichtUeberwacht()` mit `@DisplayName("A17 Mindestbestand 0 heißt: keine Überwachung")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
