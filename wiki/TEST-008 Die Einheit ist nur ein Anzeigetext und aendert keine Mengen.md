---
type: test-case
id: TEST-008
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-007]
---

# TEST-008 — Die Einheit ist nur ein Anzeigetext und ändert keine Mengen

Automatisierter Systemtest (JUnit) zu REQ-007 (Anforderung A07 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.einheitIstNurAnzeigetext()`:

1. Eingang 12 × 1001 nach A-01.
2. Artikel 1001 bearbeiten: Einheit „Karton“ (Bezeichnung „Schraube M6“ und Mindestbestand 10 wie bisher).

## Erwartung

- Der Gesamtbestand von 1001 bleibt 12.
- Die Einheit von 1001 ist „Karton“.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-008
- **Prüft:** REQ-007 (A07)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `einheitIstNurAnzeigetext()` mit `@DisplayName("A07 Die Einheit ist nur ein Anzeigetext und ändert keine Mengen")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
