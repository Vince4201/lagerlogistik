---
type: test-case
id: TEST-005
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-004]
---

# TEST-005 — Ein Artikel liegt an mehreren Lagerorten, ein Lagerort enthält mehrere Artikel

Automatisierter Systemtest (JUnit) zu REQ-004 (Anforderung A04 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.artikelUndLagerorteSindNzuM()`:

1. Wareneingänge: 5 × 1001 nach A-01, 7 × 1001 nach B-01, 3 × 1002 nach A-01.
2. Bestände je Artikel 1001 und je Lagerort A-01 sowie den Gesamtbestand von 1001 abfragen.

## Erwartung

- 1001 liegt an A-01 und B-01.
- An A-01 liegen 1001 und 1002.
- Gesamtbestand von 1001: 12.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-005
- **Prüft:** REQ-004 (A04)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `artikelUndLagerorteSindNzuM()` mit `@DisplayName("A04 Ein Artikel liegt an mehreren Lagerorten, ein Lagerort enthält mehrere Artikel")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
