---
type: test-case
id: TEST-001
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-001]
---

# TEST-001 — Alle Daten liegen in einer einzigen lokalen Datei, ein Server ist nicht nötig

Automatisierter Systemtest (JUnit) zu REQ-001 (Anforderung A01 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `DatenbankTest.datenLiegenInEinerLokalenDatei()`:

1. Wareneingang 5 × 1001 nach A-01.
2. Datenbank schließen.
3. Dieselbe Datei `test.db` erneut öffnen und den Gesamtbestand von 1001 abfragen.

## Erwartung

- `test.db` ist eine gewöhnliche Datei im Testordner.
- Nach dem erneuten Öffnen beträgt der Gesamtbestand von 1001 wieder 5.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-001
- **Prüft:** REQ-001 (A01)
- **Herkunft:** `src/test/java/lager/db/DatenbankTest.java`, Methode `datenLiegenInEinerLokalenDatei()` mit `@DisplayName("A01 Alle Daten liegen in einer einzigen lokalen Datei, ein Server ist nicht nötig")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
