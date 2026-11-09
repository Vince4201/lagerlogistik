# Tools — installierte MCPs und Erweiterungen

**Bearbeitungsstand: 2026-11-09**

_(Je Kategorie die installierten Werkzeuge und die
Werkzeug-Wahlen des Anwenders, auch die Wahl „ohne
Werkzeug“ — Vorgehen.md §7b. Angelegt in der Migration
nach `_template/migration/MIGRATION.md` Phase 3.)_

## MCPs (agent-übergreifend)

### playwright
- **Installiert:** mit dem clawbook-Container, eingetragen für alle Agenten
  (`clawbook agenten status`); Datum nicht bekannt
- **Zweck:** Chromium steuern (Browser-Automatisierung)

_(stitch und wikijs sind eingetragen, aber ohne Schlüssel und damit nicht
nutzbar; Stand 2026-11-09.)_

## Erweiterungen

### graphify, notebooklm, playwright-cli (Skills)
- **Agent:** alle Agenten im clawbook-Container
- **Installiert:** mit dem clawbook-Container; Datum nicht bekannt
- **Zweck:** Wissensgraph aus Code und Dokumenten; NotebookLM; Browser von der
  Kommandozeile

## Prüf-Werkzeuge

### Subagenten mit frischem Kontext
- **Agent:** Claude Code (Agent-Tool)
- **Zweck:** Prüfer mit frischem Kontext starten (Zwei-Modelle-Regel);
  Prüfer 1 der Migration ist Claude Sonnet 5.5 als Subagent

### Zweites Modell für Prüfungen
- **Werkzeug:** Codex CLI 0.160.0 (Modell gpt-6-luna), am 2026-11-09 vom
  Anwender angemeldet (`codex login`, über ChatGPT)
- **Aufruf im clawbook-Container:** Die Codex-Sandbox läuft hier nicht
  (bubblewrap fehlt, unprivilegierte Namensräume gesperrt). Gewählt am
  2026-11-09: Prüfläufe mit `codex exec --dangerously-bypass-approvals-and-sandbox`
  in einem Klon des Repos, nie im Projektordner; Codex hat dabei die Rechte
  des Benutzers
- **Ersatzweg (nur ohne Werkzeug):** Ein-Modell-Weg (`Reviewmuster.md` §2
  Schleife 2, Fallback) — dasselbe Modell prüft in frischem Kontext, die
  Schwäche steht im Bericht und in der Freigabe
- **Gewählt am:** 2026-11-09 (Fragen-Block der Migration)

## Recherche-Werkzeuge

### WebSearch und WebFetch (Claude Code)
- **Zweck:** externe Web-Recherche
- **Stand:** verfügbar, bisher nicht gewählt

### gh (GitHub CLI)
- **Zweck:** Repos und Dateien auf GitHub lesen
- **Stand:** angemeldet als Inhaber des Projekt-Repos

## Sub-Agent-Werkzeuge

### Zum Starten eines Sub-Agenten
- Claude Code Agent-Tool (Hintergrund-Subagenten)

### Zur Kommunikation mit einem laufenden Sub-Agenten
- Claude Code SendMessage

### Verfügbare Modelle mit Klassen
- Claude Opus 5.5, Claude Sonnet 5.5, Claude Haiku 4.5 — Klassen noch nicht
  zugeordnet

## UI-Entwurfs-Werkzeuge

- **Wahl:** keines (2026-11-09) — Terminal-Anwendung ohne grafische
  Oberfläche

## Code-Review-Werkzeuge

- **Wahl:** Codex CLI (2026-11-09), Aufruf wie in §Prüf-Werkzeuge

## Peer-Agenten: Start und Transport

### Start
- **Gewählt am:** 2026-11-09
- **Werkzeug:** keines — Rolle Standalone; peeragent ist nicht installiert

### Transport
- **Gewählt am:** 2026-11-09
- **Werkzeug:** Datei-Variante

### Signal bei neuer Nachricht
- **Gewählt am:** 2026-11-09
- **Signal:** ohne (C8 nur zu den übrigen Anlässen)

## Ordner-Synchronisation zwischen Repos

- **Wahl:** keines (2026-11-09) — ein Repo

## Kadenz-Werkzeuge

### Testlauf
- **Kommando:** `mvn -q -B test`
- **Zweck:** alle JUnit-Tests vor Commits mit Code-Änderungen

### Prüflauf mit Beispieldaten
- **Kommando:** `rm -f test.db && ./lager.sh test.db < beispieldaten.txt`
- **Zweck:** kompletter Durchlauf über die Bedienung; baut vorher mit Tests

### Linter/Formatter
- **Wahl:** keines (2026-11-09)

## Projekt-Skripte

_(Skripte im Ordner `tools/` — MasterPrompt §1.9. Registrier-Pflicht: jedes
Skript ein Eintrag. Bisher liegt kein Projekt-Skript in `tools/`.)_

### _template/tools/lint.sh (Template-Werkzeug, nur lesen)
- **Zweck:** deterministischer Vor-Check des Wikis vor dem Lint-Workflow
  (Wiki.md §9)
- **Aufruf:** `sh _template/tools/lint.sh` im Projekt-Root; den Stichtag liest
  er aus der Zeile `Template-Stand` des Herkunfts-Vermerks in
  `wiki/Aktivierter-Prozess.md`. Hilfe mit `--help`
- **Abhängigkeiten:** POSIX sh, awk; keine Kopie ins Projekt-`tools/`, Pflege
  nur im Template
