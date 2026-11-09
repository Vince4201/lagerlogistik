---
type: test-case
id: TEST-011
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-010]
---

# TEST-011 — Eine Umlagerung verschiebt die Menge, der Gesamtbestand bleibt gleich

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-010 (Anforderung A10 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.umlagerungAendertGesamtbestandNicht()`:

1. Eingang 25 × 1001 nach A-01.
2. Umlagerung 5 × 1001 von A-01 nach B-01.

## Erwartung

- Die Bewegung hat die Art Umlagerung.
- Bestand an A-01: 20, an B-01: 5.
- Das Buchungsergebnis meldet den Gesamtbestand 25.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-011
- **Prüft:** REQ-010 (A10)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `umlagerungAendertGesamtbestandNicht()` mit `@DisplayName("A10 Eine Umlagerung verschiebt die Menge, der Gesamtbestand bleibt gleich")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
