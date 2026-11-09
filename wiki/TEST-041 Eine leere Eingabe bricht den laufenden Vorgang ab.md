---
type: test-case
id: TEST-041
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-032]
---

# TEST-041 — Eine leere Eingabe bricht den laufenden Vorgang ab

Automatisierter Systemtest (JUnit) zu REQ-032 (Anforderung A32 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.leereEingabeBrichtAb()`:

1. Eingaben: `1` (Wareneingang), `1001`, `A-01`, Enter statt einer Menge, `0`.

## Erwartung

- Die Ausgabe enthält `Abgebrochen.`
- Das Protokoll bleibt leer.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-041
- **Prüft:** REQ-032 (A32)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `leereEingabeBrichtAb()` mit `@DisplayName("A32 Eine leere Eingabe bricht den laufenden Vorgang ab")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
