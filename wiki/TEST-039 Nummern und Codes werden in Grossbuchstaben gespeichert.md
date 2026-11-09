---
type: test-case
id: TEST-039
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-023]
---

# TEST-039 — Nummern und Codes werden in Großbuchstaben gespeichert

Automatisierter Systemtest (JUnit) zu REQ-023 (Anforderung A23 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.grossbuchstaben()`:

1. Eingaben: `9` (Stammdaten), `2` (Artikel anlegen), `ab-7`, `Testartikel`, dreimal Enter, `6` (Lagerort anlegen), `x-01`, zweimal Enter, `0`, `0`.

## Erwartung

- Artikel `AB-7` existiert, `ab-7` nicht.
- Lagerort `X-01` existiert.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-039
- **Prüft:** REQ-023 (A23)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `grossbuchstaben()` mit `@DisplayName("A23 Nummern und Codes werden in Großbuchstaben gespeichert")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
