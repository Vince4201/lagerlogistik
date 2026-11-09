---
type: test-case
id: TEST-037
status: offen
status_date: 2026-11-09
testart: system
prueft: [REQ-020]
---

# TEST-037 — Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand

Automatisierter Systemtest (JUnit) zu REQ-020 (Anforderung A20 im Vorgefundenen). Status kanonisch im Eintrag in [[Testfaelle]]; Ausführung nach [[Softwaresystemtest-Setup]].

## Vorbedingung

Testbasis ([[Softwaresystemtest-Setup#Testbasis]]): eigene, frische Datenbankdatei mit den Lagerorten A-01, B-01, C-01 und den Artikeln 1001 Schraube M6 (Mindestbestand 10) und 1002 Mutter M6 (Mindestbestand 0).

Die Konsole läuft auf dieser Datenbank mit Eingaben, die zeilenweise eingespielt werden wie am Terminal; eine leere Zeile ist ein Enter. Geprüft werden die Ausgabe und der Datenbestand danach.

## Ablauf

Automatisiert in `LagerKonsoleTest.hinweisBeimStart()`:

1. Programm starten und mit `0` beenden; 1001 hat Mindestbestand 10 und noch keinen Bestand.
2. Eingang 10 × 1001 nach A-01, direkt gebucht (nicht über die Konsole).
3. Programm erneut starten und mit `0` beenden.

## Erwartung

- Beim ersten Start enthält die Ausgabe `!! Hinweis: 1 Artikel liegt unter dem Mindestbestand (Menüpunkt 7).`
- Beim zweiten Start fehlt der Hinweis.

## Vermerke

_(keine)_

## Bezüge

- **Gehört zu:** [[Testfaelle]] — Fassaden-Eintrag TEST-037
- **Prüft:** REQ-020 (A20)
- **Herkunft:** `src/test/java/lager/ui/LagerKonsoleTest.java`, Methode `hinweisBeimStart()` mit `@DisplayName("A20 Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand")`
- **Verweist auf:** [[Softwaresystemtest-Setup]] (Testbasis, Ausführung)
