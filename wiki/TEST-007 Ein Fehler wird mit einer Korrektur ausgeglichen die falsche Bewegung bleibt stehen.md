---
type: test-case
id: TEST-007
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-006]
---

# TEST-007 — Ein Fehler wird mit einer Korrektur ausgeglichen, die falsche Bewegung bleibt stehen

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-006 (Anforderung A06 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.fehlerWerdenPerKorrekturAusgeglichen()`:

1. Eingang 100 × 1001 nach A-01, Bemerkung „Tippfehler, richtig wären 10“.
2. Korrektur von 1001 an A-01 auf den gezählten Bestand 10; die Begründung verweist auf die Nummer des falschen Eingangs.
3. Protokoll für 1001 abfragen.

## Erwartung

- Das Protokoll enthält 2 Bewegungen.
- Die erste ist der falsche Eingang, unverändert mit Menge 100.
- Die zweite ist eine Korrektur.
- Gesamtbestand von 1001: 10.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-007
- **Prüft:** REQ-006 (A06)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `fehlerWerdenPerKorrekturAusgeglichen()` mit `@DisplayName("A06 Ein Fehler wird mit einer Korrektur ausgeglichen, die falsche Bewegung bleibt stehen")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
