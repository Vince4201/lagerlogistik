---
type: test-case
id: TEST-003
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-005]
---

# TEST-003 — Der Bestand ist keine gespeicherte Tabelle, sondern eine View über den Bewegungen

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-005 (Anforderung A05 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `DatenbankTest.bestandIstEineView()`:

1. Im Schema (`sqlite_master`) den Typ des Objekts `bestand` lesen.
2. Alle Tabellen auflisten, ohne die SQLite-internen (`sqlite_*`).

## Erwartung

- `bestand` ist eine View.
- Es gibt genau die Tabellen `artikel`, `bewegung` und `lagerort`.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-003
- **Prüft:** REQ-005 (A05)
- **Herkunft:** `src/test/java/lager/db/DatenbankTest.java`, Methode `bestandIstEineView()` mit `@DisplayName("A05 Der Bestand ist keine gespeicherte Tabelle, sondern eine View über den Bewegungen")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
