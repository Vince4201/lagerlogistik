# Wiki-Katalog

**Bearbeitungsstand:** 2026-11-09 — Migration Phase 3: Detail-Knoten der übernommenen Register eingetragen.

_(Kein Fassaden-Knoten — Struktur-Datei des Wiki-Layers,
ohne Bootstrap-/Meta-Sektionen.)_

## Wiki-Struktur (Meta-Header)

Dieses Wiki enthält das komplette projektspezifische
Wissen. Zwei Ebenen:

- **Fassaden** (Übersichts-Knoten) — geschlossene,
  am Stück lesbare Dokumente pro Themenkomplex
  (Facts, Chronologie, Fristen, Ansprechpartner
  etc.). Für Überblick beim Sessionstart geladen.
  Übersicht aller Fassaden mit Status: [[Fassaden]].
- **Detail-Knoten** — atomare Wiki-Knoten pro
  Wissensobjekt (`document`, `person`, `place`,
  `organization`, `thing`, `concept`, `event`,
  `deadline`, `idea`, `decision`, `case`,
  `argument` etc.).

**Progressive Disclosure:** wie Fassaden und
Detail-Knoten zusammen gelesen werden und welche
Ebene bei Aufgaben mit Außenwirkung und bei kanonisch
markierten Feldern maßgeblich ist:
`../MasterPrompt.md` §5.4 (Progressive Disclosure)
und `../Wiki.md` §1.1a.

**Externe Agenten und neue Leser** starten bei
[[Mission Statement]], sofern angelegt.

**Fassaden-Status** (aktiv/deaktiviert) lebt führend in
der FassadenLandkarte [[Fassaden]] — dieser Katalog
listet die Detail-Knoten nach Typ.

Vollständige Wiki-Logik: siehe `../Wiki.md`.

## document

_(wird beim ersten Ingest angelegt)_

## person

_(wird beim Ingest oder redaktionell angelegt;
Register: [[Personen]])_

## place

_(wird beim Ingest oder redaktionell angelegt;
Register: [[Orte]])_

## organization

_(wird beim Ingest oder redaktionell angelegt;
Register: [[Organisationen]])_

## thing

_(wird beim Ingest oder redaktionell angelegt;
Register: [[Dinge]])_

## concept

_(wird redaktionell angelegt)_

## event

_(wird beim Ingest bei gewichtigen Ereignissen
angelegt)_

## deadline

_(wird beim Ingest bei gewichtigen Fristen angelegt)_

## idea

_(wird bei gewichtigen IDEA-Einträgen angelegt;
Register: [[Ideen]])_

## role

_(wird bei der ersten Rolle angelegt; Register:
[[Rollen]])_

## need

_(wird bei gewichtigen NEED-Einträgen angelegt;
Register: [[Bedarfe]])_

## scenario

_(wird bei gewichtigen SCEN-Einträgen angelegt;
Register: [[Szenarien]])_

## decision

- **[[DEC-004 Bestand nur aus den Bewegungen berechnet]]** — Bestand wird aus dem Bewegungsprotokoll berechnet, keine eigene Bestandstabelle.
- **[[DEC-007 Java 21 als Sprache und Laufzeit]]** — Technologie: Java 21.
- **[[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]]** — Technologie: SQLite in einer lokalen Datei über sqlite-jdbc.
- **[[DEC-009 Build mit Maven]]** — Technologie: Build mit Maven.
- **[[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]]** — Programm und Treiber in einer ausführbaren JAR-Datei.
- **[[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]** — Technologie: JUnit-Tests über das Surefire-Plugin.
- **[[Migrations-Grundlagen]]** — Auffang- und Herkunftsknoten der Wissens-Migration vom 2026-11-09 (DEC-018, Gesamt-Annahme steht aus).

_(Register: [[Entscheidungen]])_

## agent-report

- **[[REP-001 — Absicherungs-Review Migration Pruefer 1]]** — Absicherungs-Review der Migration, Prüfer 1, Ampel gelb.

_(Register: [[Agent-Berichte]])_

## research-artifact

_(wird beim Recherche-Ingest angelegt; Register:
[[Rechercheartefakte]])_

## member

_(wird bei attribut-reichen Mitgliedschaften
angelegt; Register: [[Mitgliedschaften]])_

## internal-concept / internal-procedure / internal-pattern / internal-rule

_(mitgelieferte Basis-Knoten; beim Init um die
Betriebs-Konfigurations-Knoten ergänzt — Register:
[[InterneMechanik]]. Projekt-eigene Knoten aus der
Migration:)_

- **[[Bauen-und-Starten-mit-lager-sh]]** — Anleitung: bauen und starten mit `lager.sh`.
- **[[Build-und-Laufzeitdaten-nicht-im-Repository]]** — Regel: `target/` und `*.db` gehören nicht ins Repo.
- **[[Datenbank-mit-sqlite3-ansehen]]** — Anleitung: in die SQLite-Datenbank schauen.
- **[[Experimente-auf-eigenem-Branch]]** — Regel: Experimente auf einem eigenen Branch, nicht auf `main`.
- **[[Keine-Umschreibung-der-Git-Historie]]** — Regel: die Git-Historie wird nicht umgeschrieben.
- **[[Nachschlagen-in-der-Git-Historie]]** — Anleitung: in der Git-Historie nachschlagen.
- **[[Prueflauf-mit-Beispieldaten]]** — Anleitung: kompletter Durchlauf mit `beispieldaten.txt`.
- **[[Testen-mit-Maven]]** — Anleitung: alle JUnit-Tests mit Maven ausführen.
- **[[Testname-mit-Anforderungsnummer]]** — Regel: jeder Test nennt im Namen die Nummer seiner Anforderung.

## function-description

- **[[FUNC-001 Programmstart]]** — Datenbank öffnen oder anlegen, Hinweise beim Start, Hauptmenü.
- **[[FUNC-002 Bedienung im Terminal]]** — Menüs, Eingaberegeln und Anzeige im Terminal.
- **[[FUNC-003 Gemeinsamer Buchungsablauf]]** — Prüfungen, Speichern und Mindestbestand-Warnung für alle Buchungen.
- **[[FUNC-004 Wareneingang]]** — Einlagern an einen Ziel-Lagerort.
- **[[FUNC-005 Warenausgang]]** — Auslagern aus einem Quell-Lagerort.
- **[[FUNC-006 Umlagerung]]** — Menge von einem Lagerort an einen anderen.
- **[[FUNC-007 Korrektur und Inventur]]** — Gezählten Bestand übernehmen, Differenz buchen.
- **[[FUNC-008 Auswertungen]]** — Bestand je Artikel und je Lagerort, Mindestbestand-Liste, Protokoll.
- **[[FUNC-009 Stammdatenpflege]]** — Artikel und Lagerorte anlegen, bearbeiten, löschen oder deaktivieren.

## architecture-description

- **[[ARC-001 Datenmodell]]** — Ist-Stand: Artikel, Lagerort, Bewegung und ihre Beziehungen.
- **[[ARC-002 Bestand aus Bewegungen berechnet]]** — Ist-Stand: Bestand als Berechnung über die Bewegungen.
- **[[ARC-003 Paketaufbau des Codes]]** — Ist-Stand: Pakete model, db, service, ui.

## specification

- **[[SPEC-001 Datenbankschema]]** — Tabellen, Constraints, Trigger und View `bestand`.
- **[[SPEC-002 Sortierung und Zeitpunkte]]** — Sortierregeln und Zeitpunkt-Format.

## test-case

- **[[TEST-001 Alle Daten liegen in einer einzigen lokalen Datei ein Server ist nicht noetig]]** — prüft REQ-001.
- **[[TEST-002 Keine Tabelle hat eine Spalte fuer Preise oder Werte]]** — prüft REQ-003.
- **[[TEST-003 Der Bestand ist keine gespeicherte Tabelle sondern eine View ueber den Bewegungen]]** — prüft REQ-005.
- **[[TEST-004 Die Datenbank lehnt das Aendern und Loeschen gebuchter Bewegungen ab]]** — prüft REQ-006.
- **[[TEST-005 Ein Artikel liegt an mehreren Lagerorten ein Lagerort enthaelt mehrere Artikel]]** — prüft REQ-004.
- **[[TEST-006 Jede Bestandsaenderung steht im Protokoll der Bestand ergibt sich daraus]]** — prüft REQ-005.
- **[[TEST-007 Ein Fehler wird mit einer Korrektur ausgeglichen die falsche Bewegung bleibt stehen]]** — prüft REQ-006.
- **[[TEST-008 Die Einheit ist nur ein Anzeigetext und aendert keine Mengen]]** — prüft REQ-007.
- **[[TEST-009 Ein Wareneingang erhoeht den Bestand am Ziel-Lagerort]]** — prüft REQ-008.
- **[[TEST-010 Ein Warenausgang senkt den Bestand am Quell-Lagerort]]** — prüft REQ-009.
- **[[TEST-011 Eine Umlagerung verschiebt die Menge der Gesamtbestand bleibt gleich]]** — prüft REQ-010.
- **[[TEST-012 Eine Korrektur uebernimmt den gezaehlten Bestand und bucht die Differenz]]** — prüft REQ-011.
- **[[TEST-013 Eine Menge von 0 oder weniger wird abgelehnt]]** — prüft REQ-012.
- **[[TEST-014 Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen]]** — prüft REQ-013.
- **[[TEST-015 Entnommen wird hoechstens was am Lagerort liegt kein Lagerort wird negativ]]** — prüft REQ-014.
- **[[TEST-016 Bei einer Umlagerung muessen Quelle und Ziel verschieden sein]]** — prüft REQ-015.
- **[[TEST-017 Eine Korrektur ohne Begruendung wird abgelehnt]]** — prüft REQ-016.
- **[[TEST-018 Der Mindestbestand gilt fuer den Gesamtbestand ueber alle Lagerorte]]** — prüft REQ-017.
- **[[TEST-019 Mindestbestand 0 heisst keine Ueberwachung]]** — prüft REQ-017.
- **[[TEST-020 Nach jeder Buchung die den Gesamtbestand unter den Mindestbestand senkt wird gewarnt]]** — prüft REQ-018.
- **[[TEST-021 Buchungen die den Gesamtbestand erhoehen warnen nicht]]** — prüft REQ-018.
- **[[TEST-022 Eine Umlagerung warnt nie auch unter dem Mindestbestand]]** — prüft REQ-019.
- **[[TEST-023 Ein Artikel hat Nummer Bezeichnung Einheit und Mindestbestand]]** — prüft REQ-021.
- **[[TEST-024 Ein Lagerort hat einen Code und eine optionale Bezeichnung]]** — prüft REQ-022.
- **[[TEST-025 Nummern und Codes sind eindeutig]]** — prüft REQ-023.
- **[[TEST-026 Beim Bearbeiten bleibt die Nummer unveraendert]]** — prüft REQ-023.
- **[[TEST-027 Ohne Bewegungen werden Artikel und Lagerorte geloescht]]** — prüft REQ-024.
- **[[TEST-028 Mit Bewegungen wird nur deaktiviert und das nur ohne Bestand]]** — prüft REQ-025.
- **[[TEST-029 Deaktivierte lassen sich nicht bebuchen nach dem Reaktivieren wieder]]** — prüft REQ-026.
- **[[TEST-030 Je Artikel Gesamtbestand und Verteilung auf die Lagerorte]]** — prüft REQ-027.
- **[[TEST-031 Je Lagerort welche Artikel in welcher Menge dort liegen ohne Bestaende von 0]]** — prüft REQ-028.
- **[[TEST-032 Liste der Artikel unter dem Mindestbestand mit Fehlmenge]]** — prüft REQ-029.
- **[[TEST-033 Bewegungsprotokoll in Buchungsreihenfolge filterbar nach Artikel und Lagerort]]** — prüft REQ-030.
- **[[TEST-034 Bedienung ueber ein Menue im Terminal]]** — prüft REQ-002.
- **[[TEST-035 Mengen sind ganze Zahlen eine Kommazahl wird erneut abgefragt]]** — prüft REQ-007.
- **[[TEST-036 Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung]]** — prüft REQ-018.
- **[[TEST-037 Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand]]** — prüft REQ-020.
- **[[TEST-038 Die Einheit eines neuen Artikels ist ohne Eingabe Stk]]** — prüft REQ-021.
- **[[TEST-039 Nummern und Codes werden in Grossbuchstaben gespeichert]]** — prüft REQ-023.
- **[[TEST-040 Falsche Eingaben werden erneut abgefragt]]** — prüft REQ-031.
- **[[TEST-041 Eine leere Eingabe bricht den laufenden Vorgang ab]]** — prüft REQ-032.
- **[[TEST-042 Mit Fragezeichen erscheint eine Auswahlliste]]** — prüft REQ-033.
