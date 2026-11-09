---
type: test-case
id: TEST-022
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-019]
---

# TEST-022 — Eine Umlagerung warnt nie, auch unter dem Mindestbestand

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-019 (Anforderung A19 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `MindestbestandTest.umlagerungWarntNie()`:

1. Eingang 4 × 1001 nach A-01.
2. Umlagerung 2 × 1001 von A-01 nach B-01.
3. Liste der Artikel unter dem Mindestbestand abfragen.

## Erwartung

- Die Umlagerung warnt nicht.
- Die Liste enthält genau einen Artikel.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-022
- **Prüft:** REQ-019 (A19)
- **Herkunft:** `src/test/java/lager/service/MindestbestandTest.java`, Methode `umlagerungWarntNie()` mit `@DisplayName("A19 Eine Umlagerung warnt nie, auch unter dem Mindestbestand")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
