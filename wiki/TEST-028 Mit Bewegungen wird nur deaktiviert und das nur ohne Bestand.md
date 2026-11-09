---
type: test-case
id: TEST-028
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-025]
---

# TEST-028 — Mit Bewegungen wird nur deaktiviert, und das nur ohne Bestand

Automatisierter Systemtest (JUnit) zu REQ-025 (Anforderung A25 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.mitBewegungenWirdNurDeaktiviert()`:

1. Eingang 5 × 1001 nach A-01.
2. Artikel 1001 und Lagerort A-01 entfernen versuchen.
3. Ausgang 5 × 1001 von A-01.
4. Artikel 1001 und Lagerort A-01 entfernen.

## Erwartung

- Schritt 2 wird abgelehnt (`LagerException`): der Artikel hat Bestand, der Lagerort ist belegt.
- In Schritt 4 ist das Ergebnis jeweils „Deaktivieren“ (`Entfernen.DEAKTIVIEREN`); beide sind danach inaktiv.
- Das Protokoll für 1001 bleibt vollständig (2 Bewegungen).

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-028
- **Prüft:** REQ-025 (A25)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `mitBewegungenWirdNurDeaktiviert()` mit `@DisplayName("A25 Mit Bewegungen wird nur deaktiviert, und das nur ohne Bestand")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
