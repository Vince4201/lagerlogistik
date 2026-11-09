---
type: test-case
id: TEST-020
status: offen
status_date: 2026-11-09
testart: integration
prueft: [REQ-018]
---

# TEST-020 — Nach jeder Buchung, die den Gesamtbestand unter den Mindestbestand senkt, wird gewarnt

Automatisierter Integrationstest (JUnit, unterhalb der Konsole) zu REQ-018 (Anforderung A18 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

## Ablauf

Automatisiert in `MindestbestandTest.warnungBeiJederSenkendenBuchung()`:

1. Eingang 12 × 1001 nach A-01.
2. Ausgang 2 × 1001 von A-01 (Gesamtbestand danach genau 10).
3. Ausgang 1 × 1001 von A-01 (danach 9).
4. Ausgang 1 × 1001 von A-01 (danach 8).
5. Korrektur an A-01 auf den gezählten Bestand 5, Begründung „Inventur“.

## Erwartung

- Schritt 2 warnt nicht: genau 10 liegt nicht unter dem Mindestbestand.
- Schritt 3 warnt; das Buchungsergebnis nennt die Fehlmenge 1.
- Schritt 4 warnt ebenfalls, auch wenn der Bestand schon darunter lag.
- Die Korrektur nach unten (Schritt 5) warnt.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-020
- **Prüft:** REQ-018 (A18)
- **Herkunft:** `src/test/java/lager/service/MindestbestandTest.java`, Methode `warnungBeiJederSenkendenBuchung()` mit `@DisplayName("A18 Nach jeder Buchung, die den Gesamtbestand unter den Mindestbestand senkt, wird gewarnt")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
