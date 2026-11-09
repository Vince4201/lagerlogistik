---
type: internal-pattern
---

# Bauen und Starten mit lager.sh

**Zweck:** die Lagerverwaltung starten und sie vorher bauen,
wenn sich etwas geändert hat — ein Aufruf für beides.

**Voraussetzungen:** Java 21 oder neuer und Maven
(Technikpaket `clawbook-java`).

**Schritte:**

1. Im Projektordner `./lager.sh` aufrufen.
2. Fehlt `target/lagerverwaltung.jar` oder ist `pom.xml` oder
   etwas unter `src/` neuer als die JAR-Datei, baut das Skript
   zuerst mit `mvn -q -B package` — samt allen Tests: schlägt
   ein Test fehl, startet das Programm nicht (DEC-012).
3. Danach startet es die JAR-Datei mit `java -jar` und der
   Datenbankdatei als Argument.

**Wahl-Optionen:**

- **Datenbankdatei:** ohne Argument `lager.db` im
  Projektordner; `./lager.sh test.db` arbeitet mit einer
  anderen Datei. Fehlt die Datei, legt das Programm sie an.
- **Start trotz rotem Test:** „wer trotzdem starten will,
  baut mit `mvn package -DskipTests`“ (Commit `2eb6d29`).

**Grenzen und Hinweise:**

- Ab Java 24 löst Maven harmlose Unsafe-Warnungen aus; kennt
  die installierte Java-Version die Option, setzt das Skript
  `--sun-misc-unsafe-memory-access=allow` in `MAVEN_OPTS`.
- Der SQLite-Treiber lädt eine native Bibliothek; Manifest der
  JAR-Datei und Surefire geben den Zugriff frei, sonst käme
  ab Java 24 eine Warnung (`pom.xml`).
- `target/` ist nicht im Repository
  ([[Build-und-Laufzeitdaten-nicht-im-Repository]]).

## Herkunft

Bestand aus `CLAUDE-vorgefunden.md` (Kopf: „Starten mit
`./lager.sh`, das bei Bedarf vorher mit Maven baut“),
`spezifikation.md` §Technik und Start, `lager.sh` (Kommentare
und Ablauf), `pom.xml` (Kommentare) und Commit `2eb6d29`.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Entscheidungen: [[DEC-007 Java 21 als Sprache und Laufzeit]],
  [[DEC-009 Build mit Maven]],
  [[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]],
  DEC-012
- Nur testen: [[Testen-mit-Maven]]; kompletter Durchlauf:
  [[Prueflauf-mit-Beispieldaten]]
