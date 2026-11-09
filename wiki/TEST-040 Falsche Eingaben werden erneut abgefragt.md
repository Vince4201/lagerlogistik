---
type: test-case
id: TEST-040
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-031]
---

# TEST-040 — Falsche Eingaben werden erneut abgefragt

Automatisierter Systemtest (JUnit) zu REQ-031 (Anforderung A31 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.falscheEingabenWerdenErneutAbgefragt()`:

1. Eingaben: `x`, `1` (Wareneingang), `9999`, `1001`, `Z-99`, `A-01`, `-3`, `3`, Enter, Enter, `0`.

## Erwartung

- Die Ausgabe enthält `Fehler: Bitte eine Zahl von 0 bis 9 eingeben.`, `Fehler: Artikel 9999 gibt es nicht.`, `Fehler: Lagerort Z-99 gibt es nicht.` und `Fehler: Die Zahl muss mindestens 1 sein.`
- Die Buchung kommt mit den korrigierten Eingaben zustande: Bestand von 1001 an A-01: 3.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-040
- **Prüft:** REQ-031 (A31)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `falscheEingabenWerdenErneutAbgefragt()` mit `@DisplayName("A31 Falsche Eingaben werden erneut abgefragt")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
