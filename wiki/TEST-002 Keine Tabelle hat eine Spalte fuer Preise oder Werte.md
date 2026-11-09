---
type: test-case
id: TEST-002
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-003]
---

# TEST-002 — Keine Tabelle hat eine Spalte für Preise oder Werte

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-003 (Anforderung A03 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `DatenbankTest.keineSpaltenFuerPreise()`:

1. Die Spaltennamen der Tabellen `artikel`, `lagerort` und `bewegung` aus dem Schema lesen (`pragma_table_info`).

## Erwartung

- Jede der drei Tabellen hat Spalten.
- Kein Spaltenname enthält, ohne Rücksicht auf Groß- und Kleinschreibung, „preis“, „price“ oder „wert“.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-002
- **Prüft:** REQ-003 (A03)
- **Herkunft:** `src/test/java/lager/db/DatenbankTest.java`, Methode `keineSpaltenFuerPreise()` mit `@DisplayName("A03 Keine Tabelle hat eine Spalte für Preise oder Werte")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
