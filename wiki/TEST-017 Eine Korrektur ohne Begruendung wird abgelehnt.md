---
type: test-case
id: TEST-017
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-016]
---

# TEST-017 — Eine Korrektur ohne Begründung wird abgelehnt

Automatisierter Systemtest (JUnit) zu REQ-016 (Anforderung A16 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.korrekturBrauchtBegruendung()`:

1. Eingang 5 × 1001 nach A-01.
2. Korrektur an A-01 auf den gezählten Bestand 4 versuchen: mit leerer Begründung, mit einer Begründung nur aus Leerzeichen und ganz ohne Begründung.

## Erwartung

- Alle drei Korrekturen werden abgelehnt (`LagerException`).
- Der Bestand an A-01 bleibt 5.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-017
- **Prüft:** REQ-016 (A16)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `korrekturBrauchtBegruendung()` mit `@DisplayName("A16 Eine Korrektur ohne Begründung wird abgelehnt")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
