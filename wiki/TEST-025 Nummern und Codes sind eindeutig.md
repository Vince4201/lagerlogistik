---
type: test-case
id: TEST-025
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-023]
---

# TEST-025 — Nummern und Codes sind eindeutig

Automatisierter Systemtest (JUnit) zu REQ-023 (Anforderung A23 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.nummernUndCodesSindEindeutig()`:

1. Artikel 1001 ein zweites Mal anlegen versuchen (Bezeichnung „Doppelt“).
2. Lagerort A-01 ein zweites Mal anlegen versuchen (Bezeichnung „Doppelt“).

## Erwartung

- Beide Versuche werden abgelehnt (`LagerException`).
- Artikel 1001 heißt weiter „Schraube M6“.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-025
- **Prüft:** REQ-023 (A23)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `nummernUndCodesSindEindeutig()` mit `@DisplayName("A23 Nummern und Codes sind eindeutig")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
