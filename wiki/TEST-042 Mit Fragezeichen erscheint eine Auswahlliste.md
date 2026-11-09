---
type: test-case
id: TEST-042
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-033]
---

# TEST-042 — Mit ? erscheint eine Auswahlliste

Automatisierter Systemtest (JUnit) zu REQ-033 (Anforderung A33 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.fragezeichenZeigtAuswahlliste()`:

1. Eingaben: `1` (Wareneingang), `?`, Enter, `0`.

## Erwartung

- Die Ausgabe enthält `Bezeichnung`, `Schraube M6` und `Mutter M6`.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-042
- **Prüft:** REQ-033 (A33)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `fragezeichenZeigtAuswahlliste()` mit `@DisplayName("A33 Mit ? erscheint eine Auswahlliste")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
