---
type: decision
id: DEC-011
status: umgesetzt
status_date: 2026-11-09
decision_type: technologie
---

# DEC-011 Tests mit JUnit Jupiter über Surefire

Bestand aus `pom.xml`, der Dokumentenkette und Commit
`2eb6d29` (Migration, Phase 3) — ohne Zielmarke und
Anlage-Datum.

## Kontext und Problem

Bis Commit `2eb6d29` gab es keine automatischen Tests, nur
den Prüflauf mit `beispieldaten.txt` (Commit `1013075`).
Anlass laut Commit `2eb6d29`: „Jede Anforderung soll hinten
ihren Test finden.“

## Betrachtete Optionen

Nicht dokumentiert.

## Entscheidung

Automatische Tests mit JUnit 6 (`org.junit.jupiter:junit-jupiter`,
nur für die Tests unter `src/test/java`), ausgeführt vom
`maven-surefire-plugin`. Eine Begründung für die Wahl nennen
die Quellen nicht.

## Folgen

- Die Tests laufen bei jedem `mvn package` mit, auch in
  `lager.sh` (DEC-012).
- Surefire startet die Tests mit freigegebenem nativem
  Zugriff für den SQLite-Treiber (`pom.xml`).
- Namen der Tests: [[Testname-mit-Anforderungsnummer]];
  Ausführung: [[Testen-mit-Maven]].

## Verworfen und warum

Nicht dokumentiert.

## Verweise

- Herkunft: Commit `2eb6d29`; `pom.xml` (`junit-jupiter`
  im Test-Scope, `maven-surefire-plugin`);
  `spezifikation.md` §Prüfen („JUnit 6“)
- Nachbarn: [[DEC-009 Build mit Maven]], DEC-012

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
- Testaufbau: [[Softwaresystemtest-Setup]]
