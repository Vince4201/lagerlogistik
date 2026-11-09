---
type: test-case
id: TEST-004
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-006]
---

# TEST-004 — Die Datenbank lehnt das Ändern und Löschen gebuchter Bewegungen ab

Automatisierter Systemtest (JUnit) zu REQ-006 (Anforderung A06 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `DatenbankTest.bewegungenSindUnveraenderlich()`:

1. Wareneingang 5 × 1001 nach A-01.
2. Direkt per SQL `UPDATE bewegung SET menge = 50` ausführen.
3. Direkt per SQL `DELETE FROM bewegung` ausführen.

## Erwartung

- Das Ändern scheitert mit einer `DatenbankException`, deren Meldung „nicht geändert“ enthält.
- Das Löschen scheitert mit einer `DatenbankException`, deren Meldung „nicht gelöscht“ enthält.
- Der Gesamtbestand von 1001 bleibt 5.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-004
- **Prüft:** REQ-006 (A06)
- **Herkunft:** `src/test/java/lager/db/DatenbankTest.java`, Methode `bewegungenSindUnveraenderlich()` mit `@DisplayName("A06 Die Datenbank lehnt das Ändern und Löschen gebuchter Bewegungen ab")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
