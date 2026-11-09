---
type: review-report
id: REP-001
titel: Absicherungs-Review der Wissens-Migration, Prüfer 1
modell: claude-sonnet-5-5
bau_modell: claude-opus-5-5
runde: 1
vorgaenger: —
datum: 2026-11-09
ampel: gelb
marke: Uebernahme-2026-11-09
---

# REP-001 — Absicherungs-Review Migration Pruefer 1

Prüfbericht von Prüfer 1 (Claude Sonnet 5.5 als Subagent mit frischem
Kontext) in Phase 4 Stufe 1 der Wissens-Migration
(`_template/migration/MIGRATION.md`), Runde 1. Bau-Modell der Migration:
Claude Opus 5.5. Register: [[Agent-Berichte]].

## Auftrag

### Auftrag an Prüfer 1 — Absicherungs-Review der Wissens-Migration (Phase 4, Stufe 1, Runde 1)

Projekt: `lagerverwaltung`, Projektordner `/home/student/source/largerlogistik`.
Prüf-Modell: Claude Sonnet 5.5 (frischer Kontext). Bau-Modell der Migration: Claude Opus 5.5.

Prüfe die Wissens-Migration im Projekt `lagerverwaltung`: (1) Vollständigkeit — stichprobe
jede Wissensbasis-Datei gegen das wiki/: fehlt Substanz? (2) Treue — verfälschen Einträge ihre
Quelle? (3) Mapping-Disziplin — alles am richtigen Ort nach `_template/migration/MIGRATION.md`
Phase 3? (4) Register-Hygiene — Formate, Status-Werte, Quell-Verweise, Bestand ohne
`zielmarke:` und Anlage-Datum, Neues mit beidem, keine erfundenen Daten? (5) Herkunft und
Festlegungen — `Template-Stand` mit Übernahme-Tag, Übernahme-Marke `in-progress`,
Ordner-Freigaben, neue `TODO.md` mit Links, Kollisionen nach Schema umbenannt, unverändert und
verlinkt? Dazu `sh _template/tools/lint.sh` aus dem Projekt-Root: keine FEHLER. Schreibe genau
einen Bericht mit Ampel (grün/gelb/rot) und nummerierten Befunden samt Fundstelle. Kein Commit.

#### Kontext-Bootstrap (zu lesen)

- `_template/migration/MIGRATION.md` — die Anleitung (Phase 2 Bestands-Regel, Phase 3 Mapping,
  Phase 4).
- Die Wissensbasis-Dateien, wie im Fragen-Block bestätigt: `anforderungen.md`,
  `datenmodell-und-ablaeufe.md`, `spezifikation.md`, die Tests unter `src/test/java/`,
  `beispieldaten.txt`, `CLAUDE-vorgefunden.md` (die umbenannte `CLAUDE.md`) und die
  Git-Historie bis Commit `2eb6d29`.
- Das `wiki/` — Einstieg über `wiki/Migrations-Grundlagen.md`, `wiki/Aktivierter-Prozess.md`
  §Herkunft, `wiki/Zielmarken.md` und die Register samt ihrer `## Meta` —, dazu `Tools.md`,
  `TODO.md` und `devstate_active.md`.
- Die Commits der Migration: `git log --oneline 2eb6d29..HEAD` (nur lesen).

#### Grenzen

- Nur lesen. Schreiben darfst du ausschließlich die Berichtsdatei unter „Lieferung“. Keine
  Änderung an Projektdateien, kein `git add`, kein Commit.
- Bekannte offene Punkte stehen in `wiki/Migrations-Grundlagen.md` §Ungeklärt. Nimm Stellung,
  wo du anderer Meinung bist oder etwas fehlt; wiederhole sie nicht nur.
- Keine Probleme erfinden; saubere Bereiche ausdrücklich als sauber benennen.

#### Lieferung

- Berichtsdatei:
  `/tmp/claude-1000/-home-student-source-largerlogistik/02774d3e-c910-4421-931b-cb81319c24f6/scratchpad/rep-001-bericht.md`
- Gliederung: erste Zeile `Ampel: gruen`, `Ampel: gelb` oder `Ampel: rot`; dann `## Befunde`,
  nummeriert, je Befund: Prüfpunkt (1–5), Fundstelle (Datei mit Zeile oder ID), Befund,
  Einstufung (entscheidungsbedürftig | Formulierung | akzeptabel), Vorschlag; dann `## Sauber`
  (was geprüft wurde und ohne Befund ist); dann `## Lint` (Ergebniszeile des Lint).
- Rückgabetext: nur die Ampel und höchstens drei Sätze.

## Bericht

Ampel: gelb

Prüfer 1 (Claude Sonnet 5.5, frischer Kontext), Phase 4 Stufe 1, Runde 1. Nur gelesen; geschrieben wurde allein diese Datei (Lint-Zwischenablage und ein Prüflauf-Gegentest lagen im Scratchpad, nicht im Projekt; `git status` leer).

### Befunde

1. **Prüfpunkt 1/2 (Vollständigkeit, Treue)** — Fundstelle: `wiki/Softwaresystemtest-Setup.md:194-196`, `wiki/Prueflauf-mit-Beispieldaten.md:21`, `wiki/Migrations-Grundlagen.md:84-86` (Ungeklärt 7).
   Befund: Die Wiki-Texte sagen, der Prüflauf habe kein Soll-Ergebnis („ein Soll-Bestand ist nicht festgehalten“). Die Commit-Nachricht von `2eb6d29` hält es fest: „Prüflauf mit beispieldaten.txt ohne Fehler, 12 Bestandsposten, Summe 1907“. Ich habe es nachgerechnet (Simulation von `beispieldaten.txt`) und mit der vorhandenen JAR gegengeprüft (Datenbank im Scratchpad): `SELECT COUNT(*), SUM(menge) FROM bestand WHERE menge<>0` ergibt 12 | 1907. Substanz aus der Git-Historie fehlt, und die Aussage „kein Soll“ ist so nicht richtig.
   Einstufung: Formulierung (inhaltlich Nachtrag).
   Vorschlag: Referenzergebnis (12 Bestandsposten, Summe 1907, Quelle Commit `2eb6d29`) in Setup §Prüflauf und `Prueflauf-mit-Beispieldaten` eintragen; Ungeklärt 7 damit auf die Frage „eigener TEST?“ verkürzen. Die „Gegenprobe“ aus derselben Nachricht (zwei absichtliche Fehler machten 5 bzw. 1 Test rot) gehört zu Befund 8.

2. **Prüfpunkt 4 (Register-Hygiene, Drift Fassade/Knoten)** — Fundstelle: `wiki/Softwaresystemarchitektur.md:154-177` (ARC-001/002/003, „Gestützt auf“) gegen `wiki/ARC-001 Datenmodell.md:6`, `wiki/ARC-002 Bestand aus Bewegungen berechnet.md:6`, `wiki/ARC-003 Paketaufbau des Codes.md:6` (`abgeleitet_von`).
   Befund: Die Meta (Z. 66-70) verlangt im ARC-Eintrag „Gestützt auf (DEC, FUNC)“. Die Einträge nennen nur FUNC; die Knoten nennen zusätzlich DEC-004/005/006/008 (ARC-001), DEC-004 (ARC-002), DEC-007/009/010 (ARC-003). Der Lint prüft diese Feld-Drift nicht (steht unter „Nicht geprüft“).
   Einstufung: Formulierung.
   Vorschlag: DEC-Verweise in die drei Fassaden-Einträge übernehmen (oder aus den Knoten streichen); die Fassade ist kanonisch.

3. **Prüfpunkt 2 (Treue, keine erfundenen Fakten)** — Fundstelle: `wiki/Mission Statement.md:56-58`.
   Befund: „Das Projekt entstand zunächst nach dem ‚naiven Ansatz‘ mit einer Dokumentenkette“. Der Begriff „naiver Ansatz“ steht in keiner Quelldatei, keiner Commit-Nachricht und nicht im Template. Außerdem stimmt der Hergang nicht: Die Kette entstand erst am selben Tag nach `KONZEPT.md` (`f3b2bd4`, `e11a977`, `0ef4466`).
   Einstufung: Formulierung.
   Vorschlag: Satz streichen oder durch das Belegte ersetzen („zuerst `KONZEPT.md`, am 2026-11-02 in die Dokumentenkette aufgeteilt“, Commit `0ef4466`).

4. **Prüfpunkt 2 (Treue)** — Fundstelle: `wiki/DEC-004 Bestand nur aus den Bewegungen berechnet.md:20-24, 52-56`; `wiki/Entscheidungen.md:392-394`; `wiki/ARC-002 Bestand aus Bewegungen berechnet.md:30-31`; `wiki/Migrations-Grundlagen.md:110-111` (Ungeklärt 13).
   Befund: Als „verworfene Alternative“ gilt eine eigene Bestandstabelle und Ungeklärt 13 nennt sie „belegt“. Die Quelle (`datenmodell-und-ablaeufe.md:44`) sagt nur: „Weil es keine eigene Bestandstabelle gibt, können Bestand und Protokoll nie voneinander abweichen.“ Dass die Alternative ernsthaft betrachtet und verworfen wurde, steht nirgends; es ist eine Ableitung.
   Einstufung: Formulierung.
   Vorschlag: In DEC-004/ARC-002/Eintrag als „abgeleitet aus dem Begründungssatz der Quelle“ kennzeichnen, in Ungeklärt 13 „belegt“ streichen; dann gilt „Verworfenes: nicht dokumentiert“ einheitlich bei allen DEC.

5. **Prüfpunkt 3 (Mapping-Disziplin)** — Fundstelle: `wiki/Entscheidungen.md:347-385` (DEC-001 bis DEC-003), `:403-435` (DEC-005, DEC-006).
   Befund: Diese DEC geben REQ-001/002/003 bzw. REQ-006 und REQ-024 bis 026 praktisch wörtlich wieder; „Begründung und verworfene Alternativen nicht dokumentiert“, keine neue Substanz. Das Mapping gibt jeder Wissens-Klasse genau ein Ziel (Soll-Verhalten → REQ). Dieselbe Aussage liegt damit in zwei Registern (Pflegeaufwand, mögliche Drift); Ungeklärt führt das nicht auf.
   Einstufung: entscheidungsbedürftig.
   Vorschlag: Entweder DEC-001/002/003/005/006 behalten und in der Gesamt-Annahme ausdrücklich als „Rahmen-Festlegungen, aus REQ abgeleitet“ bestätigen lassen, oder auf die DEC mit eigener Substanz beschränken (DEC-004, 007 bis 012). Meine Neigung: behalten, aber in Ungeklärt aufnehmen, weil ARC-001 sich auf sie stützt.

6. **Prüfpunkt 3 (Mapping-Disziplin)** — Fundstelle: `wiki/Softwaresystemarchitektur.md:200-209` (§Komponenten, Platzhalterzeile) mit Meta Z. 80-94; `wiki/Migrations-Grundlagen.md:87-91` (Ungeklärt 8) und `:112-116` (Ungeklärt 14).
   Befund: Bei Rolle Standalone verlangt die Meta „genau eine“ Zeile (Name, Aufgabe, Rolle, Repo-Name, Pfad, Remote). Die Zeile fehlt. Pfad (`.`), Aufgabe (`pom.xml` Name „Lagerverwaltung“) und Remote (`https://github.com/Vince4201/lagerlogistik`, `CLAUDE-vorgefunden.md:80`, `git remote -v`) stehen fest in den Quellen. Nur der Repo-Name als Qualifizierer braucht eine Anwender-Wahl (Ordner `largerlogistik`, GitHub `lagerlogistik`, Artefakt `lagerverwaltung`). Die GitHub-Adresse ist ein Lage-Faktum der Komponente, kein Facts-Kandidat (Ungeklärt 14 schlägt Facts §2 vor, das ist Anwender-Terrain).
   Einstufung: entscheidungsbedürftig.
   Vorschlag: Zeile jetzt anlegen (Pfad `.`, Remote, Rolle „eigenes Bauteil“, Repo-Name mit Vorschlag) und den Repo-Namen in der Gesamt-Annahme bestätigen lassen, statt bis Phase 5 zu warten. Ungeklärt 14 entsprechend ändern.

7. **Prüfpunkt 3 (Mapping-Disziplin, Zielumgebung/Deployment)** — Fundstelle: `wiki/Zielumgebungen.md:64`, `wiki/Deployments.md` (leer); Quelle `spezifikation.md:13-21`.
   Befund: Zeile „Installation und Betrieb je Umgebung → Deployments/Zielumgebungen“. Start und Voraussetzungen (`./lager.sh`, Java 21+, Maven) stehen als InterneMechanik-Anleitung (`wiki/Bauen-und-Starten-mit-lager-sh.md`); das ist für die Build-Anleitung zulässig. Dass für die einzige Umgebung („lokal“, Einzelplatz) keine Zielumgebung angelegt wurde, ist aber weder entschieden noch in Ungeklärt vermerkt.
   Einstufung: entscheidungsbedürftig (gering).
   Vorschlag: Entweder Zielumgebung „lokal“ mit den Fakten aus der Quelle anlegen oder in `Migrations-Grundlagen` §Festlegungen einen Satz „keine Zielumgebung/DEPL: nur lokaler Einzelplatzbetrieb vorgefunden, Start/Build als Anleitung geführt“ ergänzen.

8. **Prüfpunkt 3 (Mapping-Disziplin, alte Testergebnisse)** — Fundstelle: `wiki/Rechercheartefakte.md:60-65` (leer); `wiki/Migrations-Grundlagen.md:77-83` (Ungeklärt 5) und `:112-116` (Ungeklärt 14).
   Befund: Die Zeile „Alte Testergebnisse → Nachweise in Rechercheartefakte“ ist nicht angewendet. Die Prüfergebnisse in der Nachricht von `2eb6d29` (42 Tests grün; Prüflauf 12 Bestandsposten, Summe 1907; Gegenprobe mit zwei absichtlichen Fehlern, 5 bzw. 1 Test rot) liegen nur im Git-Verlauf und sind im Wiki bloß über den Hash erreichbar. Die Empfehlung in Ungeklärt 5, `target/surefire-reports/` nicht aufzunehmen (nicht versioniert, regenerierbar), teile ich.
   Einstufung: entscheidungsbedürftig (gering).
   Vorschlag: Ein REC „Prüfergebnisse zum Stand von Commit `2eb6d29`“ (Quelle: Commit-Nachricht) anlegen, oder in Ungeklärt 14 begründen, dass die Nachweise im Git-Verlauf bleiben und über `Nachschlagen-in-der-Git-Historie` erreichbar sind.

9. **Prüfpunkt 4 (Register-Hygiene, Ungeklärt 6)** — Fundstelle: `wiki/Testfaelle.md:20-29` (Meta), `wiki/Softwaresystemtest-Setup.md:85-89, 125-136`, `wiki/Migrations-Grundlagen.md:81-83`.
   Befund: Alle 42 TEST tragen `testart: system`. 33 laufen ohne Oberfläche gegen `LagerService`/Datenbank; die Meta sagt: Unit- und Integrationstests bleiben Code, außer eine Projekt-Regel in InterneMechanik führt sie als Testfall. Die Begründung im Setup („jeder Test prüft eine Anforderung, deshalb Systemtests“) ist eine Hilfskonstruktion. Gleichzeitig ist die Rückverfolgung Anforderung → Test im Projekt gewollte Regel (`Testname-mit-Anforderungsnummer`).
   Einstufung: entscheidungsbedürftig.
   Vorschlag: Eine Projekt-Regel in `Testname-mit-Anforderungsnummer` ergänzen („JUnit-Tests mit Anforderungsnummer sind Testfälle, auch wenn sie unterhalb der Konsole laufen“) und die 33 als `integration` führen; das ist ehrlicher als `system`. Alternative: bei `system` bleiben und die Begründung im Setup als bewusste Abweichung kennzeichnen. Die Entscheidung liegt beim Anwender (Gesamt-Annahme).

10. **Prüfpunkt 4 (Register-Hygiene, Quell-Verweise)** — Fundstelle: `wiki/Anforderungen.md:302`, `wiki/Entscheidungen.md:354`, `wiki/Rollen.md:82`, `wiki/Testfaelle.md` (Zeilen „Quelle“) gegen `wiki/Funktionsbeschreibungen.md:108-109`, `wiki/Spezifikationen.md:108`, `wiki/Softwaresystemarchitektur.md:159`.
   Befund: Die Herkunft steht je Register in anderer Form: als Code-Span (REQ, DEC, TEST, Rolle) oder als Markdown-Link (FUNC, SPEC, ARC), unter „Quelle“ (REQ, FUNC, SPEC, ARC, TEST, Rolle) oder „Verweise: Herkunft“ (DEC). Die Zeile „Quelle“ kennt keine Meta (Ungeklärt 4); in der Sache ist sie richtig und nötig (MIGRATION: „Quelle je Eintrag“). Der Lint prüft das nicht.
   Einstufung: Formulierung.
   Vorschlag: Ungeklärt 4 mit „so lassen“ entscheiden, die Zeile aber einheitlich als Markdown-Link auf die Quelldatei führen (anklickbar, wie in FUNC/SPEC/ARC).

11. **Prüfpunkt 4 (Fassaden-Pflege)** — Fundstelle: `wiki/Fassaden.md:107`.
    Befund: Die Zeile Contract-Index sagt noch „Rolle offen — Aktivierung bei Rolle Koordinator oder per Zuruf“, obwohl `:39` und `Aktivierter-Prozess.md:48` Rolle Standalone nennen.
    Einstufung: Formulierung.
    Vorschlag: „Rolle Standalone gewählt (2026-11-09); Aktivierung nur bei Rolle Koordinator oder per Zuruf“.

12. **Prüfpunkt 3 (Vorwärts-Lücke in einer Regel)** — Fundstelle: `wiki/Testname-mit-Anforderungsnummer.md:11-17`.
    Befund: Die Regel verlangt, dass der Testname mit der Anforderungsnummer beginnt, bezieht „Nummer“ aber auf `anforderungen.md` (A01 bis A33). Eine neue Anforderung ist REQ-034 und hat kein A-Pendant mehr; der Namensbestandteil für künftige Tests ist offen.
    Einstufung: Formulierung.
    Vorschlag: Festlegen, dass neue Tests mit `REQ-NNN` beginnen (bestehende `A..` bleiben), und das in der Regel und in Setup §Unit-Tests vermerken.

13. **Prüfpunkt 5 (TODO.md mit Links)** — Fundstelle: `TODO.md:18-24`.
    Befund: Der Abschnitt „Vorgefundene Dateien mit offenen Arbeiten“ sagt „Keine“; das deckt sich mit dem Fragen-Block (Commit `7f3a3d3`: „keine Dateien mit offenen Arbeiten“). Die genannten Dateien stehen aber nur als Code-Spans, nicht als Links; es gibt außerdem keine `TODO-vorgefunden.md` (es gab keine `TODO.md`). Formal erfüllt, weil nichts zu verlinken ist.
    Einstufung: akzeptabel.
    Vorschlag: Die fünf Nachweis-Dateien optional als Links schreiben.

14. **Prüfpunkt 4 (Facts, Anwender-Terrain)** — Fundstelle: `wiki/Facts.md:45-51` gegen MasterPrompt §9.4 (Z. 2138-2147: kein Verweis-Charakter, keine Chronologie-Zusammenfassung).
    Befund: Der zweite Absatz von Facts §1 („Der Stand wurde am … ins wiki/ übernommen … Einstieg: [[…]]“) ist eine Link- und Verlaufs-Notiz. Das Facts-Bootstrap (Schritt 1) verlangt aber ausdrücklich Wikilinks auf zentrale Themen, und das Mapping nennt Facts für „Einstieg“; deshalb vertretbar.
    Einstufung: akzeptabel (Hinweis für die Gesamt-Annahme: Facts gehört dem Anwender).
    Vorschlag: Im Bericht zur Gesamt-Annahme erwähnen; Ungeklärt 17 deckt §2/§3. Kandidat für §3 (Stolperstein): „Ein roter Test verhindert den Start (DEC-012)“, nur auf Anweisung.

15. **Stellungnahme zu den bekannten offenen Punkten** (Migrations-Grundlagen §Ungeklärt), wo ich nicht nur wiederhole:
    - 1 (Sektionen): einverstanden. 2 (Status): `umgesetzt` bei REQ und DEC ist durch MIGRATION Phase 2 gedeckt („nach dem, was Code belegt“); FUNC/SPEC/ARC/TEST `offen` entspricht der Meta. Keine Angleichung auf `angenommen` nötig; ein Hinweis in der Gesamt-Annahme genügt, dass „umgesetzt“ ohne Tag nach Register-Lebenszyklus (Z. 53-54) eine Einschätzung ist.
    - 3, 10, 11, 12: einverstanden (durch Regelwerk abgelöst; Begründung in `Commit-Prozedur`/MasterPrompt §9 liegt vor).
    - 5: einverstanden (siehe 8). 6: siehe 9. 7: Prämisse falsch (siehe 1). 8: siehe 6. 13: siehe 4. 14: siehe 6 und 8.
    - 9 (REQ ohne FUNC): REQ-001, 003, 004, 006 haben keine FUNC (Skript bestätigt; jede REQ hat aber ≥ 1 TEST). REQ-004/006 sind über ARC-001/SPEC-001/DEC-005 getragen. Meine Empfehlung: so lassen und in der ersten Kettenprüfung nach der Migration entscheiden, nicht jetzt FUNC „erfinden“; die Meta der Funktionsbeschreibungen zählt Qualitäten (Einzelplatz, nur Mengen) allerdings zur Außensicht.
    - 15 (`target/`): einverstanden, kein KI-verwalteter Ordner (MasterPrompt C6 Root-Inventar). 16 (`.gitignore`): einverstanden; die Vorlage enthält nur Office-/OS-Dateien, nichts zu Geheimnissen.
    - Nicht aufgeführt, aber vermerkenswert für die Gesamt-Annahme: Durch das Umbenennen von `CLAUDE.md` bricht kein Link in vorgefundenen Dateien (geprüft); bis Phase 5 gibt es keine Instruktionsdatei.
    Einstufung: akzeptabel (Stellungnahme).
    Vorschlag: Punkte 3, 4, 7, 8, 12 ergänzen die Liste für die Gesamt-Annahme.

### Sauber

- **REQ-001 bis REQ-033** (`wiki/Anforderungen.md:294-511`): wortgetreu zu A01 bis A33; die drei Pronomen-Ersetzungen (A02, A03, A25) sind im Bearbeitungsstand angegeben; Abschnitts- und Einleitungs-Zitate der Zeile „Quelle“ stimmen; Sektionen und Reihenfolge nach Meta; Status `umgesetzt`, Bestand ohne Zielmarke/Angelegt.
- **FUNC-001 bis FUNC-009, SPEC-001/002, ARC-001 bis ARC-003**: gegen `datenmodell-und-ablaeufe.md` und `spezifikation.md` Satz für Satz gelesen und mit einem Satz-Abgleichsskript geprüft; Menüs, Eingaberegeln, Fragen/Prüfungen/Ausgaben, Schema, Trigger, View, Paketaufbau sind vollständig und treu. Die Trennung FUNC (Außensicht) / SPEC (Schema, Sortier-Umsetzung) folgt der Mapping-Zeile.
- **TEST-001 bis TEST-042**: 42 `@Test` = 42 `@DisplayName` = 42 Knoten; `prueft:` entspricht der A-Nummer des DisplayName; Methoden- und Klassennamen stimmen; Vorbedingung/Ablauf/Erwartung aller 42 gegen den Quelltext gelesen, keine Abweichung; jede REQ hat ≥ 1 TEST; Testklassen-Tabelle im Setup stimmt (4/13/5/7/4/9).
- **Softwaresystemtest-Setup**: Zählung des Prüflaufs (6 Lagerorte, 10 Artikel, 10 Eingänge, 2 Umlagerungen, 9 Ausgänge, 1 Korrektur) per Simulation bestätigt; Feststellungen (kein docker-compose, keine Umgebungsvariablen) begründet.
- **Chronologie**: alle 9 Commits bis `2eb6d29` abgedeckt, Hashes und Datum (2026-11-02) richtig, nur Daten aus Git.
- **InterneMechanik** (4 Regeln, 5 Anleitungen): Zitate und Commit-Verweise (`3b6f6b4`, `1013075`, `e11a977`/`f3b2bd4`, `2eb6d29`) gegen `git show` verifiziert; kein wichtiger Inhalt der `CLAUDE-vorgefunden.md` verloren (Abgelöstes steht in Ungeklärt 10 bis 12).
- **DEC-007 bis DEC-012** (Technologie): durch `pom.xml`, `lager.sh`, Commit `2eb6d29` belegt; „nicht dokumentiert“ wird ehrlich gesetzt.
- **Glossar Review**: 20 Vorschläge, alle nur als Vorschlag, `Glossar.md` unverändert; Herkunft je Eintrag.
- **Rollen**: Eintrag „Benutzer“ nach Meta, Quelle A01.
- **Register-Hygiene**: Status-Werte gültig; keine Status-Drift zwischen Fassaden-Einträgen und Knoten (Skript über FUNC/SPEC/ARC/TEST/DEC); alle Detail-Links lösen auf; alle 72 neuen Wiki-Dateien stehen in `wiki/index.md`; Bestand (101 Elemente) trägt weder `zielmarke:` noch Anlage-Datum; Neues (DEC-013 bis DEC-017 im Eintrag, DEC-018 im Knoten) trägt beides; keine erfundenen Daten (nur 2026-11-09 und Git-Daten).
- **Herkunft**: `Template-Stand: 2.1 seit 2026-11-09` = Tag des Aufnahme-Commits `64f9b02`; Quelle `4fdc0ae05fe6…` (release-2.1) = `git submodule status`; Modus B; Rolle Standalone; Format nach Init-Choreografie §Phase A0; keine Zeile `Vorher`.
- **Übernahme-Marke**: `Uebernahme-2026-11-09 — Übernahme des vorgefundenen Stands`, Zustand `in-progress`, keine Marke `current`, Marken-Dreiklang vollständig, Beschreibung durch `pom.xml` 1.0 und „Version 1“ gedeckt.
- **Ordner-Freigaben** DEC-013 bis DEC-017 (`src/`, `tests/`, `ci/`, `deploy/`, `docs/`): decken die „Mitgebrachten Ordner“ des SP-1-Steckbriefs (Z. 80-86); kein vorgefundenes `docs/`; Eintragsformat und Verweise stimmen.
- **Kollision**: nur `CLAUDE.md`; `git mv` (R100, Inhalt unverändert) nach `CLAUDE-vorgefunden.md`, Schema `<Name>-vorgefunden.<Endung>`, verlinkt in `Migrations-Grundlagen` §Herkunft; `TODO.md`, `Tools.md`, `devstate_active.md` kollidierten nicht; keine gebrochenen Links in vorgefundenen Dateien.
- **Kopie-Integrität**: 130 unveränderte Wiki-Dateien und 8 Root-Dokumente byte-gleich zu `_template/`; die 6 Migrations-Varianten sind eingetauscht.
- **Tools.md / Prüf-Werkzeuge**: Struktur nach `wiki/templates/Tools-Vorlage.md`, alle Kategorien vorhanden; Angaben geprüft (Codex installiert, „Not logged in“; `gh` als `Vince4201`, Inhaber des Remotes; `peeragent` nicht installiert); Prüfer-Wahl und Ersatzweg decken sich mit dem Fragen-Block (`7f3a3d3`).
- **Vorgefundene Wissensdateien** (`anforderungen.md`, `datenmodell-und-ablaeufe.md`, `spezifikation.md`, `beispieldaten.txt`, Tests, `lager.sh`, `pom.xml`): unverändert seit `2eb6d29`.
- **`devstate_active.md`**: Zielmarke und Nächste Schritte stimmen mit dem Stand.

### Lint

`sh _template/tools/lint.sh` (aus dem Projekt-Root, Stichtag 2026-11-09 aus `Aktivierter-Prozess`): **FEHLER: 0   HINWEISE: 203** (202 × `PFLICHTFELD-BESTAND` = 101 Bestands-Elemente × `datum`/`zielmarke`, 1 × `REG-OHNE-EINTRAG` für DEC-018; beides erwartet). `devstate_active.md:11-14` nennt nur die erste Gruppe; die zweite steht in `Migrations-Grundlagen` und ist dort erklärt.

## Vermerke

- **Vermerk 2026-11-09:** Auftrag und Bericht unverändert übernommen;
  nur die Überschriften sind herabgestuft, damit sie unter „Auftrag“ und
  „Bericht“ stehen. Prüfer 2 (Codex) prüft unabhängig und bekommt diesen
  Bericht nicht zu sehen.
