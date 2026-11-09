---
type: test-case
id: TEST-014
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-013]
---

# TEST-014 — Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen

Automatisierter Systemtest (JUnit) zu REQ-013 (Anforderung A13 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `BuchungenTest.nurVorhandeneUndAktiveWerdenBebucht()`:

1. Eingang für den unbekannten Artikel 9999 nach A-01 versuchen.
2. Eingang von 1001 an den unbekannten Lagerort Z-99 versuchen.
3. Eingang 1 × 1002 nach C-01, danach Ausgang 1 × 1002 von C-01.
4. Artikel 1002 und Lagerort C-01 entfernen; sie haben Bewegungen und keinen Bestand und werden deshalb deaktiviert.
5. Eingang von 1002 nach A-01 und von 1001 nach C-01 versuchen.

## Erwartung

- Die Versuche aus Schritt 1, 2 und 5 werden abgelehnt (`LagerException`).
- Das Protokoll enthält nur die 2 Bewegungen aus Schritt 3.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-014
- **Prüft:** REQ-013 (A13)
- **Herkunft:** `src/test/java/lager/service/BuchungenTest.java`, Methode `nurVorhandeneUndAktiveWerdenBebucht()` mit `@DisplayName("A13 Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
