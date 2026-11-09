---
type: decision
id: DEC-008
status: umgesetzt
status_date: 2026-11-09
decision_type: technologie
---

# DEC-008 SQLite als Datenbank über sqlite-jdbc

Bestand aus `pom.xml` und der Dokumentenkette (Migration,
Phase 3) — ohne Zielmarke und Anlage-Datum.

## Kontext und Problem

Datenhaltung für Artikel, Lagerorte und Bewegungen der
Einzelplatz-Anwendung (DEC-001); seit dem ersten Commit
`3b6f6b4` „in Java 21 mit SQLite“.

## Betrachtete Optionen

Nicht dokumentiert.

## Entscheidung

SQLite: Die Daten liegen in einer Datenbankdatei, ohne
Argument `lager.db` im Projektordner, sonst die beim Start
angegebene Datei; fehlt sie, wird sie angelegt. Java greift
über den JDBC-Treiber `org.xerial:sqlite-jdbc` zu (Version
laut `pom.xml`). Eine Begründung nennen die Quellen nicht.

## Folgen

- Der Treiber kommt mit in die ausführbare JAR-Datei
  ([[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]]).
- Der Treiber lädt eine native Bibliothek; ab Java 24 gäbe
  das ohne Freigabe eine Warnung, darum setzen `pom.xml`
  (Surefire und Manifest der JAR-Datei) den nativen Zugriff
  frei.
- `lager.db.DatenbankTest` prüft für A01 die lokale Datei
  (`spezifikation.md` §Prüfen).
- In die Datenbank schauen: [[Datenbank-mit-sqlite3-ansehen]].

## Verworfen und warum

Nicht dokumentiert.

## Verweise

- Herkunft: `pom.xml` (Abhängigkeit
  `org.xerial:sqlite-jdbc`, Kommentare zum nativen Zugriff);
  `spezifikation.md` §Technik und Start, §Datenbankschema,
  §Prüfen; `datenmodell-und-ablaeufe.md` §Programmstart;
  `CLAUDE-vorgefunden.md` (Kopf); Commit `3b6f6b4`
- Nachbarn: DEC-001 (Einzelplatz),
  [[DEC-004 Bestand nur aus den Bewegungen berechnet]],
  [[DEC-007 Java 21 als Sprache und Laufzeit]]

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
