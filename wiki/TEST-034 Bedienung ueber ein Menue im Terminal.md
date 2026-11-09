---
type: test-case
id: TEST-034
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-002]
---

# TEST-034 — Bedienung über ein Menü im Terminal

Automatisierter Systemtest (JUnit) zu REQ-002 (Anforderung A02 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.hauptmenue()`:

1. Programm starten, Eingabe `0`.

## Erwartung

- Die Ausgabe enthält die Überschrift `=== LAGERVERWALTUNG ===`.
- Sie enthält die Menüpunkte `1  Wareneingang`, `2  Warenausgang`, `3  Umlagerung`, `4  Korrektur / Inventur`, `5  Bestand je Artikel`, `6  Bestand je Lagerort`, `7  Artikel unter Mindestbestand`, `8  Bewegungsprotokoll`, `9  Stammdaten` und `0  Beenden`.
- Sie enthält `Auf Wiedersehen.`

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-034
- **Prüft:** REQ-002 (A02)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `hauptmenue()` mit `@DisplayName("A02 Bedienung über ein Menü im Terminal")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
