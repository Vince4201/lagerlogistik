---
type: test-case
id: TEST-035
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-007]
---

# TEST-035 — Mengen sind ganze Zahlen, eine Kommazahl wird erneut abgefragt

Automatisierter Systemtest (JUnit) zu REQ-007 (Anforderung A07 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.mengeIstEineGanzeZahl()`:

1. Eingaben: `1` (Wareneingang), `1001`, `A-01`, `2,5`, `5`, Enter, Enter, `0`.

## Erwartung

- Die Ausgabe enthält `Fehler: Bitte eine ganze Zahl eingeben.`
- Sie enthält `Gebucht (#1): Eingang 5 Stk 1001 nach A-01`.
- Bestand von 1001 an A-01: 5.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-035
- **Prüft:** REQ-007 (A07)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `mengeIstEineGanzeZahl()` mit `@DisplayName("A07 Mengen sind ganze Zahlen, eine Kommazahl wird erneut abgefragt")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
