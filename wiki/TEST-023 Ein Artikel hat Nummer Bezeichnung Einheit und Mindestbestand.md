---
type: test-case
id: TEST-023
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-021]
---

# TEST-023 — Ein Artikel hat Nummer, Bezeichnung, Einheit und Mindestbestand

Automatisierter Systemtest (JUnit) zu REQ-021 (Anforderung A21 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.artikelHatSeineEigenschaften()`:

1. Artikel 2001 anlegen: Bezeichnung „Kabelbinder 200 mm“, Einheit „Beutel“, Mindestbestand 20.
2. Artikel 2001 lesen.

## Erwartung

- Nummer 2001, Bezeichnung „Kabelbinder 200 mm“, Einheit „Beutel“, Mindestbestand 20; der Artikel ist aktiv.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-023
- **Prüft:** REQ-021 (A21)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `artikelHatSeineEigenschaften()` mit `@DisplayName("A21 Ein Artikel hat Nummer, Bezeichnung, Einheit und Mindestbestand")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
