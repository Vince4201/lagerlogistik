---
type: test-case
id: TEST-026
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-023]
---

# TEST-026 — Beim Bearbeiten bleibt die Nummer unverändert

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-023 (Anforderung A23 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.nummerBleibtBeimBearbeiten()`:

1. Artikel 1001 bearbeiten: Bezeichnung „Schraube M6x20“, Einheit Stk, Mindestbestand 15.
2. Lagerort A-01 bearbeiten: Bezeichnung „Halle A, Regal 1, Fach 1“.

## Erwartung

- Die Artikelliste enthält weiter die Nummern 1001 und 1002.
- 1001 heißt jetzt „Schraube M6x20“; A-01 trägt die Bezeichnung „Halle A, Regal 1, Fach 1“.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-026
- **Prüft:** REQ-023 (A23)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `nummerBleibtBeimBearbeiten()` mit `@DisplayName("A23 Beim Bearbeiten bleibt die Nummer unverändert")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
