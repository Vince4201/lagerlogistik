---
type: test-case
id: TEST-010
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-009]
---

# TEST-010 — Ein Warenausgang senkt den Bestand am Quell-Lagerort

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-009 (Anforderung A09 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.warenausgangSenktBestandAnDerQuelle()`:

1. Eingang 25 × 1001 nach A-01.
2. Ausgang 8 × 1001 von A-01, Bemerkung „Auftrag 1“.

## Erwartung

- Die Bewegung hat die Art Ausgang, den Quell-Lagerort A-01 und keinen Ziel-Lagerort.
- Bestand von 1001 an A-01: 17.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-010
- **Prüft:** REQ-009 (A09)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `warenausgangSenktBestandAnDerQuelle()` mit `@DisplayName("A09 Ein Warenausgang senkt den Bestand am Quell-Lagerort")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
