---
type: test-case
id: TEST-016
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-015]
---

# TEST-016 — Bei einer Umlagerung müssen Quelle und Ziel verschieden sein

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-015 (Anforderung A15 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.umlagerungBrauchtVerschiedeneLagerorte()`:

1. Eingang 5 × 1001 nach A-01.
2. Umlagerung 1 × 1001 von A-01 nach A-01 versuchen.

## Erwartung

- Die Umlagerung wird abgelehnt (`LagerException`).
- Im Protokoll steht nur der Eingang.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-016
- **Prüft:** REQ-015 (A15)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `umlagerungBrauchtVerschiedeneLagerorte()` mit `@DisplayName("A15 Bei einer Umlagerung müssen Quelle und Ziel verschieden sein")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
