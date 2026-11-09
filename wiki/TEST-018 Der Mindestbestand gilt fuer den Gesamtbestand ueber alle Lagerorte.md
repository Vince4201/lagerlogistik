---
type: test-case
id: TEST-018
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-017]
---

# TEST-018 — Der Mindestbestand gilt für den Gesamtbestand über alle Lagerorte

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-017 (Anforderung A17 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `MindestbestandTest.mindestbestandGiltFuerDenGesamtbestand()`:

1. Eingang 6 × 1001 nach A-01 und 6 × 1001 nach B-01.
2. Ausgang 1 × 1001 von A-01.
3. Ausgang 2 × 1001 von B-01.

## Erwartung

- Schritt 2 warnt nicht: an A-01 liegen danach nur 5, insgesamt aber 11.
- Schritt 3 meldet die Mindestbestand-Warnung (insgesamt 9 bei Mindestbestand 10).

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-018
- **Prüft:** REQ-017 (A17)
- **Herkunft:** `src/test/java/lager/service/MindestbestandTest.java`, Methode `mindestbestandGiltFuerDenGesamtbestand()` mit `@DisplayName("A17 Der Mindestbestand gilt für den Gesamtbestand über alle Lagerorte")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
