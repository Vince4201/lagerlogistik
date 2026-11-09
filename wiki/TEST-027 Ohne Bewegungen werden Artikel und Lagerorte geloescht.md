---
type: test-case
id: TEST-027
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-024]
---

# TEST-027 — Ohne Bewegungen werden Artikel und Lagerorte gelöscht

Automatisierter Systemtest (JUnit) zu REQ-024 (Anforderung A24 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.ohneBewegungenWirdGeloescht()`:

1. Artikel 1002 und Lagerort C-01 entfernen; beide haben keine Bewegungen.

## Erwartung

- Das Ergebnis ist jeweils „Löschen“ (`Entfernen.LOESCHEN`).
- Weder 1002 noch C-01 ist danach noch vorhanden.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-027
- **Prüft:** REQ-024 (A24)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `ohneBewegungenWirdGeloescht()` mit `@DisplayName("A24 Ohne Bewegungen werden Artikel und Lagerorte gelöscht")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
