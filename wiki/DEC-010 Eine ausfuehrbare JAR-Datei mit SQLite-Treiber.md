---
type: decision
id: DEC-010
status: umgesetzt
status_date: 2026-11-09
decision_type: technologie
---

# DEC-010 Eine ausführbare JAR-Datei mit SQLite-Treiber

Bestand aus `pom.xml` und der Dokumentenkette (Migration,
Phase 3) — ohne Zielmarke und Anlage-Datum.

## Kontext und Problem

Form, in der das gebaute Programm vorliegt und gestartet
wird.

## Betrachtete Optionen

Nicht dokumentiert.

## Entscheidung

Maven packt Programm und SQLite-Treiber in eine einzige
ausführbare Datei `target/lagerverwaltung.jar`
(`maven-shade-plugin`, Hauptklasse `lager.Main`);
`lager.sh` startet sie mit `java -jar`. Eine Begründung
nennen die Quellen nicht.

## Folgen

- JUnit kommt nicht in die JAR-Datei; die Abhängigkeit gilt
  nur für die Tests (`pom.xml`, Kommentar).
- Das Manifest der JAR-Datei gibt dem SQLite-Treiber den
  nativen Zugriff frei (ab Java 24 sonst mit Warnung;
  `pom.xml`).

## Verworfen und warum

Nicht dokumentiert.

## Verweise

- Herkunft: `pom.xml` (`maven-shade-plugin`, Kommentar
  „Packt Programm und SQLite-Treiber in eine einzige
  ausführbare JAR-Datei.“); `spezifikation.md` §Technik und
  Start; `lager.sh`
- Nachbarn: [[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]],
  [[DEC-009 Build mit Maven]]

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
- Bauen und Starten: [[Bauen-und-Starten-mit-lager-sh]]
