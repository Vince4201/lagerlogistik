---
type: test-case
id: TEST-021
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-018]
---

# TEST-021 — Buchungen, die den Gesamtbestand erhöhen, warnen nicht

Automatisierter Systemtest (JUnit) zu REQ-018 (Anforderung A18 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `MindestbestandTest.keineWarnungBeiSteigendemBestand()`:

1. Eingang 3 × 1001 nach A-01 (unter dem Mindestbestand 10).
2. Eingang 2 × 1001 nach A-01.
3. Korrektur an A-01 auf den gezählten Bestand 7, Begründung „Inventur“.

## Erwartung

- Weder der Eingang (Schritt 2) noch die Korrektur nach oben (Schritt 3) warnt, obwohl der Gesamtbestand unter dem Mindestbestand bleibt.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-021
- **Prüft:** REQ-018 (A18)
- **Herkunft:** `src/test/java/lager/service/MindestbestandTest.java`, Methode `keineWarnungBeiSteigendemBestand()` mit `@DisplayName("A18 Buchungen, die den Gesamtbestand erhöhen, warnen nicht")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
