---
type: test-case
id: TEST-009
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-008]
---

# TEST-009 — Ein Wareneingang erhöht den Bestand am Ziel-Lagerort

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-008 (Anforderung A08 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.wareneingangErhoehtBestandAmZiel()`:

1. Eingang 25 × 1001 nach A-01, Bemerkung „LS 4711“.

## Erwartung

- Die Bewegung hat die Art Eingang, keinen Quell-Lagerort, den Ziel-Lagerort A-01 und die Bemerkung „LS 4711“.
- Bestand von 1001 an A-01: 25.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-009
- **Prüft:** REQ-008 (A08)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `wareneingangErhoehtBestandAmZiel()` mit `@DisplayName("A08 Ein Wareneingang erhöht den Bestand am Ziel-Lagerort")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
