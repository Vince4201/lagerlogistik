---
type: test-case
id: TEST-030
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-027]
---

# TEST-030 — Je Artikel: Gesamtbestand und Verteilung auf die Lagerorte

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-027 (Anforderung A27 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Dazu die vier gemeinsamen Buchungen der Testklasse: #1 Eingang 8 × 1001 nach A-01, #2 Eingang 4 × 1002 nach A-01, #3 Umlagerung 3 × 1001 von A-01 nach B-01, #4 Ausgang 4 × 1002 von A-01 (danach liegt 1002 nirgends mehr).

## Ablauf

Automatisiert in `AuswertungenTest.bestandJeArtikel()`:

1. Verteilung von 1001 auf die Lagerorte abfragen.
2. Artikelübersicht abfragen.

## Erwartung

- Verteilung von 1001: A-01 mit 5, B-01 mit 3.
- Übersicht: 1001 mit Gesamtbestand 8, 1002 mit Gesamtbestand 0.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-030
- **Prüft:** REQ-027 (A27)
- **Herkunft:** `src/test/java/lager/service/AuswertungenTest.java`, Methode `bestandJeArtikel()` mit `@DisplayName("A27 Je Artikel: Gesamtbestand und Verteilung auf die Lagerorte")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
