---
type: test-case
id: TEST-029
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-026]
---

# TEST-029 — Deaktivierte lassen sich nicht bebuchen, nach dem Reaktivieren wieder

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-026 (Anforderung A26 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `StammdatenTest.deaktivierteLassenSichReaktivieren()`:

1. Eingang 1 × 1001 nach A-01, danach Ausgang 1 × 1001 von A-01.
2. Artikel 1001 und Lagerort A-01 entfernen (sie werden deaktiviert).
3. Eingang von 1001 nach B-01 und von 1002 nach A-01 versuchen.
4. Artikel 1001 und Lagerort A-01 reaktivieren.
5. Eingang 3 × 1001 nach A-01.

## Erwartung

- Beide Eingänge aus Schritt 3 werden abgelehnt (`LagerException`).
- Nach dem Reaktivieren gelingt der Eingang; Bestand von 1001 an A-01: 3.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-029
- **Prüft:** REQ-026 (A26)
- **Herkunft:** `src/test/java/lager/service/StammdatenTest.java`, Methode `deaktivierteLassenSichReaktivieren()` mit `@DisplayName("A26 Deaktivierte lassen sich nicht bebuchen, nach dem Reaktivieren wieder")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
