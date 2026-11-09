---
type: test-case
id: TEST-015
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-014]
---

# TEST-015 — Entnommen wird höchstens, was am Lagerort liegt, kein Lagerort wird negativ

Automatisierter Systemtest (JUnit) zu REQ-014 (Anforderung A14 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.keinLagerortWirdNegativ()`:

1. Eingang 5 × 1001 nach A-01 und 50 × 1001 nach B-01.
2. Versuchen: Ausgang 6 von A-01, Umlagerung 6 von A-01 nach C-01, Ausgang 1 von C-01.
3. Ausgang 5 × 1001 von A-01.

## Erwartung

- Alle Versuche aus Schritt 2 werden abgelehnt (`LagerException`), obwohl der Gesamtbestand 55 beträgt; an A-01 bleiben 5.
- Der Ausgang von genau 5 gelingt; Bestand an A-01: 0.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-015
- **Prüft:** REQ-014 (A14)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `keinLagerortWirdNegativ()` mit `@DisplayName("A14 Entnommen wird höchstens, was am Lagerort liegt, kein Lagerort wird negativ")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
