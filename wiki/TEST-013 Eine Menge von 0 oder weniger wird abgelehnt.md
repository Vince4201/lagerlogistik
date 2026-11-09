---
type: test-case
id: TEST-013
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-012]
---

# TEST-013 — Eine Menge von 0 oder weniger wird abgelehnt

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-012 (Anforderung A12 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.mengeMussGroesserNullSein()`:

1. Eingang von 1001 nach A-01 mit Menge 0 und mit Menge −5 versuchen.
2. Ausgang von 1001 von A-01 mit Menge 0 versuchen.
3. Umlagerung von 1001 von A-01 nach B-01 mit Menge 0 versuchen.

## Erwartung

- Alle vier Buchungen werden abgelehnt (`LagerException`).
- Das Protokoll bleibt leer.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-013
- **Prüft:** REQ-012 (A12)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `mengeMussGroesserNullSein()` mit `@DisplayName("A12 Eine Menge von 0 oder weniger wird abgelehnt")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
