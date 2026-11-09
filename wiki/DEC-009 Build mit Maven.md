---
type: decision
id: DEC-009
status: umgesetzt
status_date: 2026-11-09
decision_type: technologie
---

# DEC-009 Build mit Maven

Bestand aus `pom.xml`, `lager.sh` und der Dokumentenkette
(Migration, Phase 3) — ohne Zielmarke und Anlage-Datum.

## Kontext und Problem

Werkzeug, mit dem das Programm übersetzt, getestet und
gepackt wird.

## Betrachtete Optionen

Nicht dokumentiert.

## Entscheidung

Maven: `pom.xml` beschreibt Abhängigkeiten und Build
(Compiler-, Surefire- und Shade-Plugin); `lager.sh` baut bei
Bedarf mit `mvn -q -B package`. Eine Begründung nennen die
Quellen nicht.

## Folgen

- Voraussetzung: Maven (Technikpaket `clawbook-java`;
  `spezifikation.md` §Technik und Start).
- Die Build-Ausgabe entsteht in `target/` und ist nicht im
  Repository ([[Build-und-Laufzeitdaten-nicht-im-Repository]]).
- Die Tests laufen bei jedem `mvn package` mit (DEC-012).
- Bauen und Starten: [[Bauen-und-Starten-mit-lager-sh]];
  nur testen: [[Testen-mit-Maven]].

## Verworfen und warum

Nicht dokumentiert.

## Verweise

- Herkunft: `pom.xml`; `lager.sh`; `spezifikation.md`
  §Technik und Start („Build mit Maven“)
- Nachbarn: [[DEC-007 Java 21 als Sprache und Laufzeit]],
  [[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]],
  [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]], DEC-012

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
