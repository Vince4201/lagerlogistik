---
type: decision
id: DEC-007
status: umgesetzt
status_date: 2026-11-09
decision_type: technologie
---

# DEC-007 Java 21 als Sprache und Laufzeit

Bestand aus `pom.xml` und der Dokumentenkette (Migration,
Phase 3) — ohne Zielmarke und Anlage-Datum.

## Kontext und Problem

Programmiersprache und Laufzeit der Terminal-Lagerverwaltung;
seit dem ersten Commit `3b6f6b4` „in Java 21 mit SQLite“.

## Betrachtete Optionen

Nicht dokumentiert.

## Entscheidung

Java 21: Maven übersetzt für Release 21
(`maven.compiler.release` in `pom.xml`). Zum Bauen und
Starten braucht es Java 21 oder neuer
(`spezifikation.md` §Technik und Start). Eine Begründung
nennen die Quellen nicht.

## Folgen

- Voraussetzung auf dem Rechner: Java 21 oder neuer und
  Maven (Technikpaket `clawbook-java`; `spezifikation.md`
  §Technik und Start).
- Für Java 24 und neuer vermeiden `pom.xml` (nativer Zugriff
  für den SQLite-Treiber) und `lager.sh` (Unsafe-Warnungen
  von Maven) Warnungen ([[Bauen-und-Starten-mit-lager-sh]]).

## Verworfen und warum

Nicht dokumentiert.

## Verweise

- Herkunft: `pom.xml` (`maven.compiler.release` 21);
  `spezifikation.md` §Technik und Start;
  `CLAUDE-vorgefunden.md` (Kopf: „Terminal-Lagerverwaltung
  in Java 21 mit SQLite“); Commit `3b6f6b4`
- Nachbarn: [[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]],
  [[DEC-009 Build mit Maven]],
  [[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]],
  [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
