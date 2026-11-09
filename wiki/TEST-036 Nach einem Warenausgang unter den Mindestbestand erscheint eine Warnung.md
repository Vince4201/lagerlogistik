---
type: test-case
id: TEST-036
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-018]
---

# TEST-036 — Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung

Automatisierter Systemtest (JUnit) zu REQ-018 (Anforderung A18 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

Vorab gebucht: Eingang 12 × 1001 nach A-01.

## Ablauf

Automatisiert in `LagerKonsoleTest.warnungNachWarenausgang()`:

1. Eingaben: `2` (Warenausgang), `1001`, `A-01`, `5`, Enter, Enter, `0`.

## Erwartung

- Die Ausgabe enthält `!! WARNUNG: Mindestbestand unterschritten` und `!!   Es fehlen 3 Stk.`

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-036
- **Prüft:** REQ-018 (A18)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `warnungNachWarenausgang()` mit `@DisplayName("A18 Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
