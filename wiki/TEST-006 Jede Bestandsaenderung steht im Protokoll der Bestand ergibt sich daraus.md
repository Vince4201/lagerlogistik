---
type: test-case
id: TEST-006
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-005]
---

# TEST-006 — Jede Bestandsänderung steht im Protokoll, der Bestand ergibt sich daraus

Automatisierter Systemtest (JUnit) zu REQ-005 (Anforderung A05 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.bestandErgibtSichAusDenBewegungen()`:

1. Eingang 10 × 1001 nach A-01.
2. Ausgang 3 × 1001 von A-01.
3. Umlagerung 2 × 1001 von A-01 nach B-01.
4. Korrektur von 1001 an B-01 auf den gezählten Bestand 1, Begründung „Inventur“.
5. Protokoll für 1001 abfragen.

## Erwartung

- Das Protokoll enthält 4 Bewegungen.
- Die Summe der Änderungen am Gesamtbestand über alle Bewegungen ist gleich dem Gesamtbestand.
- Bestand an A-01: 5, an B-01: 1.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-006
- **Prüft:** REQ-005 (A05)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `bestandErgibtSichAusDenBewegungen()` mit `@DisplayName("A05 Jede Bestandsänderung steht im Protokoll, der Bestand ergibt sich daraus")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
