---
type: test-case
id: TEST-024
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-022]
---

# TEST-024 — Ein Lagerort hat einen Code und eine optionale Bezeichnung

Automatisierter Systemtest (JUnit) zu REQ-022 (Anforderung A22 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.lagerortHatCodeUndOptionaleBezeichnung()`:

1. Die Lagerorte C-01 und A-01 der Testbasis lesen.

## Erwartung

- C-01 hat eine leere Bezeichnung und ist aktiv.
- A-01 hat die Bezeichnung „Halle A, Regal 1“.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-024
- **Prüft:** REQ-022 (A22)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `lagerortHatCodeUndOptionaleBezeichnung()` mit `@DisplayName("A22 Ein Lagerort hat einen Code und eine optionale Bezeichnung")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
