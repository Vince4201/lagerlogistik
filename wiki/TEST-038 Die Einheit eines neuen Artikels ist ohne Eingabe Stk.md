---
type: test-case
id: TEST-038
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-021]
---

# TEST-038 — Die Einheit eines neuen Artikels ist ohne Eingabe Stk

Automatisierter Systemtest (JUnit) zu REQ-021 (Anforderung A21 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.einheitStandardStk()`:

1. Eingaben: `9` (Stammdaten), `2` (Artikel anlegen), `2001`, `Kabelbinder`, dreimal Enter, `0`, `0`.

## Erwartung

- Artikel 2001 ist angelegt mit Bezeichnung „Kabelbinder“, Einheit „Stk“, Mindestbestand 0, aktiv.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-038
- **Prüft:** REQ-021 (A21)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `einheitStandardStk()` mit `@DisplayName("A21 Die Einheit eines neuen Artikels ist ohne Eingabe Stk")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
