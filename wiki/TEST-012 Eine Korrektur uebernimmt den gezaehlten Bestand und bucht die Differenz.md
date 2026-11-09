---
type: test-case
id: TEST-012
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-011]
---

# TEST-012 — Eine Korrektur übernimmt den gezählten Bestand und bucht die Differenz

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-011 (Anforderung A11 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.korrekturBuchtDieDifferenz()`:

1. Eingang 20 × 1001 nach A-01.
2. Korrektur an A-01 auf den gezählten Bestand 17, Begründung „Schwund“.
3. Korrektur an A-01 auf den gezählten Bestand 19, Begründung „Fund“.
4. Korrektur an A-01 auf den gezählten Bestand 19, Begründung „nichts zu tun“.

## Erwartung

- Schritt 2 bucht die Differenz 3 vom Quell-Lagerort A-01, ohne Ziel-Lagerort; Bestand 17.
- Schritt 3 bucht die Differenz 2 auf den Ziel-Lagerort A-01, ohne Quell-Lagerort; Bestand 19.
- Schritt 4 (gezählter Bestand gleich dem Bestand) wird abgelehnt (`LagerException`).

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-012
- **Prüft:** REQ-011 (A11)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `korrekturBuchtDieDifferenz()` mit `@DisplayName("A11 Eine Korrektur übernimmt den gezählten Bestand und bucht die Differenz")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
