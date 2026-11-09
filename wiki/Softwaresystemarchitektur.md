---
type: software-system-architecture-overview
replaces_type: system-architecture-overview
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Softwaresystemarchitektur

**Ich löse [[Systemarchitektur]] aus SP-1 ab und verändere
die Semantik wie folgt:** software-spezifische Sektionen
(Zweck+Abgrenzung, Einstiegspunkte, Technologie-Stack,
Architektur, Stilkonvention, Design-Patterns); das
ARC-Register (§Festlegungen) und die Lage-Entität
(§Komponenten) führe ich wie SP-1.

## Meta

- **Typ:** `software-system-architecture-overview` —
  Fassade der Softwaresystemarchitektur: Register der
  **Architektur-Festlegungen** (ARC) und lebende
  Übersicht mit software-spezifischen Sektionen.
  Singleton, ID-Präfix `ARC-NNN`; löst
  `system-architecture-overview` aus SP-1 ab
  (`replaces_type:` als Typ-Name im Frontmatter,
  String-Notation; Katalog-Eintrag
  [[Katalog-Softwaresystemarchitektur]]).
- **Kategorie:** (a) Fassade + Detail-Knoten im wiki/ —
  siehe Wiki.md §Kategorisierung der Register; dazu
  lebende Sektionen (Zweck und Abgrenzung,
  Einstiegspunkte, Technologie-Stack, Architektur,
  Komponenten, Stilkonvention, Design-Patterns).
- **ARC-Elemente:** Struktur-Beschreibungen ohne
  Alternativen-Abwägung — Zuschnitt und Zuständigkeit
  einer Komponente, Kommunikation über Contracts,
  Lage. Sie stützen sich per `abgeleitet_von:` auf
  Entscheidungen und Funktionsbeschreibungen; eine
  Wahl mit Alternativen (Muster, Technologie,
  Bibliothek) bleibt eine DEC in [[Entscheidungen]].
  Ein ARC-Element legt der Agent als `offen` an; es
  bleibt bearbeitbar, bis es einzeln angenommen wird
  oder mit der Freigabe des ersten Stands, der es
  verweist, und ist ab dann eingefroren
  ([[Einfrieren-und-Abloesen]] §Eintrittsregel); eine
  Änderung ist dann eine Ablösung. Bis zur Bildung des
  Stands ist diese Fassade die Lese-Klammer der Phase
  P5 ([[Lebenslauf-eines-Stands]]). Ihr Typ
  `architecture-description`
  ist der SP-1-Typ ([[Katalog-Systemarchitektur]]).
- **Architekturdokument:** der Stand der Phase P5;
  Zweck-Freigaben der Architektur binden sich an
  diesen Stand, nie an die lebende Fassade — Zweck,
  Filter und Pflichtfelder: [[Katalog-Architekturdokument]];
  lesen, bevor ein Architektur-Stand gebildet wird.
- **Lebende Sektionen:** die übrigen Sektionen sind
  Sollzustand — bei Änderungen überschrieben, nicht
  fortgeschrieben; ein Stand kopiert sie. Was eine
  Festlegung ist, steht als ARC-Element, nicht nur
  im Fließtext.
- **Kanonizität:** die Wahrheit beim Status der
  ARC-Elemente liegt in dieser Fassade —
  Detail-Knoten (Typ `architecture-description`)
  spiegeln (Wiki.md §1.1a, [[Register-Grundmuster]]);
  bei den lebenden Sektionen und bei
  `software-system-component`-Knoten liegt die
  Wahrheit ebenfalls hier.
- **Eintragsformat (ARC):** `### ARC-NNN — <Kurzsatz>`
  in der Sektion §Festlegungen, mit Feldern Status +
  status_date / Gestützt auf (DEC, FUNC) / optional
  Zielmarke ([[Zielmarken]]) / Ablösung / Detail
  (Wikilink, nur wenn Detail-Knoten existiert).
- **`architecture-description`-Detail-Knoten-
  Konventionen:** Frontmatter-Felder:
  [[Katalog-Systemarchitektur]] (SP-1-Typ, unter RP-1
  nicht ersetzt) — lesen beim Anlegen eines
  ARC-Knotens. Sektionen: Festlegung /
  Begründung aus DEC und FUNC / Vermerke (append-only)
  / Bezüge (Fassaden-Eintrag
  [[Softwaresystemarchitektur]]). Dateiname
  `ARC-NNN <Kurzsatz>.md`.
- **Komponenten (Lage-Entität):** die Sektion
  §Komponenten ist das Register der Komponenten und
  ihrer **Lage** — eine Zeile je Komponente (Name,
  Aufgabe, Rolle, Repo-Name als Qualifizierer in
  Verweisen über Repo-Grenzen, Pfad, Remote, Detail).
  Eine Komponente ist eine Entität; ihr Zuschnitt ist
  ein ARC-Element, ihre Lage lebend und im Stand
  kopiert. **Eigenes Bauteil** des Koordinators ist
  eine Zeile, deren Lage das eigene Repo ist; ein
  Standalone-Projekt hat genau eine solche Zeile. Im
  Komponenten-Repo führt die Sektion zusätzlich den
  Koordinator und die bekannten Nachbarn (gefüllt aus
  dem Startprompt). Lint und Prüfungen lesen die Lage
  hier; ein separates Komponenten-Register gibt es
  nicht.
- **Contract-Bezug:** bei Multi-Komponenten-Setups
  referenzieren die Komponenten-Beschreibungen ihre
  CON-Elemente im SP-1-Register [[Contract-Index]]
  (beim Koordinator). Das ARC-Element, aus dem ein
  CON abgeleitet wird, nennt `provider:` und
  `consumer:` (Komponenten-Namen aus §Komponenten;
  Felder und Lint-Prüfung: [[Katalog-Systemarchitektur]],
  SP-1-Typ `architecture-description`).
- **`software-system-component`-Detail-Knoten-
  Konventionen:** Frontmatter `type:
  software-system-component` und der Spiegel
  `replaces_type: system-component` (Ersetzung:
  [[Katalog]] §Typ-Konventionen — die Katalog-Zeile
  führt, das Frontmatter spiegelt; fehlt der Spiegel,
  meldet der Lint `REPLACES-SPIEGEL`); übrige
  Frontmatter-Felder:
  [[Katalog-Softwaresystemarchitektur]]; Dateiname im
  Title-Case. Sektionen: Zweck (ein bis drei Sätze) /
  Schnittstellen (API, Contract, Protokoll,
  Gegenstelle) / Verantwortung (z.B. Zuständigkeit je
  Verzeichnis — und wofür ausdrücklich nicht) /
  Abhängigkeiten (Komponenten, Bibliotheken, externe
  Dienste mit kurzer Einordnung) / Bezüge
  (Fassaden-Eintrag [[Softwaresystemarchitektur]]).
  **Anlage-Schwelle** (aus SP-1 geerbt, in RP-1
  praktisch immer erreicht): Pflicht, sobald eine
  Komponente einen eigenen Bearbeiter oder
  Peer-Agenten, einen Contract oder ein eigenes
  Repository hat — der Knoten ist der stabile
  Bezugs-Anker der Komponente; der [[Contract-Index]]
  verlinkt auf die Komponenten-Knoten.
- **Zielumgebungen und Deployments** sind eine eigene
  Achse ([[Zielumgebungen]], [[Deployments]]); die
  Architektur gilt je Marke umgebungsunabhängig.
- **Commit-Präfix:** Änderungen an Fassade und
  ARC-Detail-Knoten laufen unter dem SP-1-Präfix
  `Architektur:` weiter (kein eigener RP-1-Präfix).

---

**Bearbeitungsstand:** 2026-11-09 — Migration: Datenmodell aus
`datenmodell-und-ablaeufe.md` und Aufbau des Codes aus
`spezifikation.md` als Ist-Stand übernommen (ARC-Elemente in
§Festlegungen, Überblick in §Ist-Stand); die lebenden Sektionen des
Sollzustands sind noch nicht gefüllt.

Beschreibt den **Sollzustand** des Projekts; der übernommene Ist-Stand
steht getrennt davon in §Ist-Stand.

## Zweck und Abgrenzung

<Was leistet das Projekt — und ausdrücklich, was leistet
es nicht.>

## Festlegungen

_(Register der Architektur-Festlegungen, chronologisch
nach ID; Eintragsformat in `## Meta`.)_

### ARC-001 — Datenmodell

- **Status:** offen — **Datum:** 2026-11-09
- **Gestützt auf:** DEC-004, DEC-005, DEC-006, DEC-008, FUNC-003,
  FUNC-004, FUNC-005, FUNC-006, FUNC-007, FUNC-009
- **Quelle:** [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Datenmodell — Ist-Stand, Bestand aus der Migration
- **Detail:** [[ARC-001 Datenmodell]]

### ARC-002 — Bestand aus Bewegungen berechnet

- **Status:** offen — **Datum:** 2026-11-09
- **Gestützt auf:** DEC-004, FUNC-003, FUNC-008
- **Quelle:** [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Datenmodell, §Bestand — Ist-Stand, Bestand aus der Migration
- **Detail:** [[ARC-002 Bestand aus Bewegungen berechnet]]

### ARC-003 — Paketaufbau des Codes

- **Status:** offen — **Datum:** 2026-11-09
- **Gestützt auf:** DEC-007, DEC-009, DEC-010, FUNC-001, FUNC-002,
  FUNC-003
- **Quelle:** [spezifikation.md](../spezifikation.md) §Aufbau des Codes
  — Ist-Stand, Bestand aus der Migration
- **Detail:** [[ARC-003 Paketaufbau des Codes]]

## Einstiegspunkte

<Alle geplanten Einstiegspunkte mit Dateipfad und
Aufgabe: Hauptprogramme, CLI-Entry-Points, Server-
Startskripte, eigenständige Service-Funktionen,
Lambda-Handler etc.>

## Technologie-Stack

<Gewählte Sprache(n), Frameworks, Runtime-Versionen. Jede
externe Bibliothek mit kurzer Einordnung, wofür sie
eingesetzt wird. Bei gemischten Technologien: Aufteilung
beschreiben.>

## Architektur

<Zuständigkeit je Verzeichnis (z.B. `src/api/` → REST-
Controller, `src/models/` → Datenmodelle) sowie das
verbindliche Architekturmuster (MVC, Hexagonal, Layered
etc.).>

## Komponenten

_(Register der Komponenten und ihrer Lage — führender
Ort. Eine Zeile je Komponente; Detail-Knoten nach der
Anlage-Schwelle in `## Meta`. Standalone: eine Zeile
mit dem eigenen Repo.)_

| Komponente | Aufgabe | Rolle | Repo-Name | Pfad | Remote | Detail |
|---|---|---|---|---|---|---|
| Lagerverwaltung | Einzelplatz-Lagerverwaltung im Terminal: Artikel, Lagerorte und Bewegungen in einer lokalen SQLite-Datei | eigenes Bauteil | lagerlogistik | `.` | https://github.com/Vince4201/lagerlogistik | — |

_(Zeile angelegt am 2026-11-09 nach Prüfbericht REP-001,
Befund 6; Repo-Name `lagerlogistik` wie auf GitHub, vom
Anwender gewählt. Der lokale Ordner heißt `largerlogistik`,
das Maven-Artefakt `lagerverwaltung`.)_

## Stilkonvention

<Gesetzte Stilvorgabe (PEP 8, ESLint-Standards etc.) und
die Werkzeuge, die sie durchsetzen.>

## Design-Patterns

<Die für dieses Projekt verbindlichen Design-Patterns.>

## Ist-Stand

_(Dokumentierter Ist-Stand, aus dem Vorgefundenen übernommen — kein
Sollzustand. Was davon eine Festlegung ist, steht als ARC-Element in
§Festlegungen.)_

- **Datenmodell:** Artikel, Lagerort und Bewegung; der Bestand wird aus
  den Bewegungen berechnet, nicht gespeichert —
  [[ARC-001 Datenmodell]], [[ARC-002 Bestand aus Bewegungen berechnet]].
  Die Bauanleitung der Datenbank: [[SPEC-001 Datenbankschema]].
- **Aufbau des Codes:** Pakete `lager`, `lager.model`, `lager.db`,
  `lager.service` und `lager.ui` — [[ARC-003 Paketaufbau des Codes]].
- **Einstiegspunkte:** `lager.Main` — Startpunkt, öffnet die Datenbank
  und startet die Konsole; das Startskript `lager.sh` baut bei Bedarf
  und startet das Programm.
- **Technologie-Stack:** Java 21; SQLite über den Treiber
  `org.xerial:sqlite-jdbc`, die Daten liegen in einer lokalen
  Datenbankdatei (Standard `lager.db`); Build mit Maven, das Programm
  und Treiber in die eine ausführbare Datei `target/lagerverwaltung.jar`
  packt; automatische Tests mit JUnit 6. Die Festlegungen dazu stehen
  in [[Entscheidungen]]: DEC-007 (Java 21), DEC-008 (SQLite),
  DEC-009 (Maven), DEC-010 (eine ausführbare JAR-Datei), DEC-011
  (JUnit über Surefire).
- **Herkunft:** [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Datenmodell; [spezifikation.md](../spezifikation.md) §Technik und
  Start, §Aufbau des Codes, §Prüfen.
