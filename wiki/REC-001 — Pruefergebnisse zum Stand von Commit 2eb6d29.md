---
type: research-artifact
id: REC-001
titel: Prüfergebnisse zum Stand von Commit 2eb6d29
quelle_liste:
  - "https://github.com/Vince4201/lagerlogistik/commit/2eb6d29f7c93a8778d7678cd24bfeefcd2830da2 — Archiv: research/REC-001 - Pruefergebnisse zum Stand von Commit 2eb6d29/commit-2eb6d29.txt"
datum: 2026-11-09
marke: Uebernahme-2026-11-09
---

# REC-001 — Prüfergebnisse zum Stand von Commit 2eb6d29

Nachweis der alten Testergebnisse aus der Wissens-Migration
(Mapping-Zeile „Alte Testergebnisse → Nachweise in
Rechercheartefakte“, `_template/migration/MIGRATION.md` Phase 3).
Die Quelle ist keine externe Recherche, sondern die Nachricht des
letzten Commits vor der Migration; sie ist Nachweis, nicht Wahrheit.

## Frage

Welche Prüfergebnisse hielt das Projekt zum Stand vor der Migration
fest, und was ist das Soll-Ergebnis des Prüflaufs mit
`beispieldaten.txt`?

## Recherche-Verlauf

- Nachricht von Commit `2eb6d29` (2026-11-02) mit `git show -s
  --format=fuller` gelesen und archiviert.
- Prüfbericht REP-001 (Befunde 1 und 8) hat das Ergebnis des
  Prüflaufs mit der vorhandenen JAR nachgerechnet.
- Am 2026-11-09 erneut nachgeprüft: `java -jar
  target/lagerverwaltung.jar` mit `beispieldaten.txt` auf eine frische
  Datenbankdatei, danach `SELECT COUNT(*), SUM(menge) FROM bestand`
  → `12|1907`; der Code ist seit `2eb6d29` unverändert.

## Fundstellen

> Prüfung: mvn test 42 Tests grün; Prüflauf mit beispieldaten.txt
> ohne Fehler, 12 Bestandsposten, Summe 1907 wie vorher.

— Commit `2eb6d29`, Archiv `commit-2eb6d29.txt`

> Gegenprobe: Zwei absichtlich eingebaute Fehler (Mindestbestand mit
> <=, fehlende Prüfung des Bestands an der Quelle) haben 5 bzw. 1 Test
> rot gemacht. Danach zurückgenommen.

— Commit `2eb6d29`, Archiv `commit-2eb6d29.txt`

## Ergebnis

Zum Stand von `2eb6d29` liefen alle 42 JUnit-Tests grün. Der Prüflauf
mit `beispieldaten.txt` lief ohne Fehler und ergab 12 Bestandsposten
mit der Summe 1907. Das ist das Soll-Ergebnis des Prüflaufs in
[[Softwaresystemtest-Setup]] §Ausführung. Die Gegenprobe zeigt, dass
die Tests absichtlich eingebaute Fehler finden. Einen Testbericht nach
[[Testberichte]] ersetzt das nicht; der erste entsteht im nächsten
Testdurchgang.

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Rechercheartefakte]]
- Angelegt nach Prüfbericht REP-001 (Befunde 1 und 8) auf
  Anwender-Entscheid vom 2026-11-09 ([[Migrations-Grundlagen]])
- Prüflauf: [[Prueflauf-mit-Beispieldaten]]
