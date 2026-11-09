---
type: agent-report-log
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Agent-Berichte

## Meta

- **Typ:** `agent-report-log` — Register aller
  Sub-Agent-Berichte des Projekts (interne Analyse,
  Prüfung, Ausführung als zitierfähiges Wissen).
  Singleton.
- **Kategorie:** (a) Fassade + Detail-Knoten im wiki/ —
  siehe Wiki.md §Kategorisierung der Register.
- **Nachweis, eingefroren ab Anlage, `ampel` als
  Bewertung:** ein Bericht wird nie abgelöst und nie
  geändert; Nachträge stehen als angehängte Vermerke
  ([[Einfrieren-und-Abloesen]] §Klassen).
- **Ingest-Regel (Standardaufgabe, keine Rückfrage):**
  sobald ein nicht-trivialer Sub-Agent-Aufruf beendet
  ist (eigenständiges Urteil oder Analyse — bloße
  Tool-Aufrufe ausgenommen): nächste freie REP-Nummer
  aus diesem Register vergeben, Detail-Knoten anlegen,
  Auftrag und Bericht unverändert übernehmen (der
  Sub-Agent-Text bleibt kanonisch), Wikilinks
  materialisieren, Register-Eintrag ergänzen, Commit
  mit Präfix `Report:`. **Reihenfolge (Pflege-Pflicht,
  MasterPrompt §9): erst ablegen, dann im Chat
  zusammenfassen** — die
  Zusammenfassung zitiert die REP-Nummer; ein
  Bericht, der nur in der Unterhaltung lebt, gilt
  als nicht erstattet.
- **Header führt, Fassade spiegelt:** die Felder eines
  Berichts stehen im Frontmatter des Detail-Knotens;
  der Fassaden-Eintrag zeigt die Kurzform. Wer ein
  Feld ändert, ändert den Header und zieht den
  Eintrag nach.
- **Eintragsformat:** `### REP-NNN — <Kurzsatz>` mit
  der Kurzform „**Modell:** … — **Bau:** … —
  **Runde:** … — **Datum:** … — **Ampel:** …“ (Bau
  und Runde nur bei Prüfberichten) / Anlass /
  Kern-Ergebnis (ein bis zwei Sätze) / Detail
  (Wikilink auf den Detail-Knoten).
- **Berichts-Typen** (Typ-Ebene per Vererbung,
  Wiki.md §3.4): `agent-report` (jeder Bericht),
  `review-report` (Prüfbericht, schreibt der Prüfer)
  und `convergence-report` (Konvergenz-Bericht,
  schreibt der Koordinator nach der
  Runden-Obergrenze, `Reviewmuster.md` §2
  Schleife 4). Felder, ihre Bedeutung und was der
  Vor-Check `sh _template/tools/lint.sh` daran prüft:
  [[Katalog-Agent-Berichte]],
  [[Katalog-review-report]],
  [[Katalog-convergence-report]] — lesen beim Anlegen
  eines Berichts. Die Meldung „Prüf-Modell =
  Bau-Modell“ gibt der Trigger-Hygiene-Wächter beim
  Sessionstart (MasterPrompt §5.3 C7) — Meldung, kein
  Block.
- **Detail-Knoten-Konventionen:** Sektionen Auftrag
  (Wortlaut inkl. Kontext-Bootstrap-Liste) / Bericht
  (unverändert übernommen) / Vermerke (append-only).
  Dateiname `REP-NNN — <Kurzsatz>.md` (Kurzsatz ohne
  Umlaut und ß), flach im `wiki/`-Root.

---

**Bearbeitungsstand:** 2026-11-09 — REP-001 und REP-002 abgelegt
(Absicherungs-Review der Migration, Prüfer 1 und 2, Runde 1), dazu
REP-003 und REP-004 (Kurz-Re-Check, Runde 2, beide grün).

Chronologische Liste.

## Einträge

_(chronologisch nach ID)_

### REP-001 — Absicherungs-Review Migration, Prüfer 1

- **Modell:** claude-sonnet-5-5 — **Bau:** claude-opus-5-5 — **Runde:** 1 — **Datum:** 2026-11-09 — **Ampel:** gelb
- **Anlass:** Phase 4 Stufe 1 der Wissens-Migration
  (`_template/migration/MIGRATION.md`), Prüfer 1 von 2.
- **Kern-Ergebnis:** Lint ohne Fehler, Übertragung vollständig und
  quellentreu; 15 Befunde — 5 entscheidungsbedürftig (DEC-001 bis 003,
  005, 006 doppeln REQ; eigene Zeile in §Komponenten; keine
  Zielumgebung; alte Prüfergebnisse ohne Nachweis; Testart), 7
  Formulierungen, 3 akzeptabel.
- **Detail:** [[REP-001 — Absicherungs-Review Migration Pruefer 1]]

### REP-002 — Absicherungs-Review Migration, Prüfer 2

- **Modell:** gpt-6-luna — **Bau:** claude-opus-5-5 — **Runde:** 1 — **Datum:** 2026-11-09 — **Ampel:** gelb
- **Anlass:** Phase 4 Stufe 1 der Wissens-Migration
  (`_template/migration/MIGRATION.md`), Prüfer 2 von 2, zweites Modell
  über die Codex CLI.
- **Kern-Ergebnis:** Lint ohne Fehler, Stichproben vollständig und
  quellentreu; ein entscheidungsbedürftiger Befund: die pauschale
  Testart `system` der 42 TEST (deckt sich mit REP-001 Befund 9).
- **Detail:** [[REP-002 — Absicherungs-Review Migration Pruefer 2]]

### REP-003 — Kurz-Re-Check Migration, Prüfer 1

- **Modell:** claude-sonnet-5-5 — **Bau:** claude-opus-5-5 — **Runde:** 2 — **Datum:** 2026-11-09 — **Ampel:** gruen
- **Anlass:** Phase 4 Stufe 1 der Wissens-Migration, Kurz-Re-Check der
  Nacharbeit (Commits `6653def..857e33b`); Vorgänger REP-001.
- **Kern-Ergebnis:** alle Befunde der Runde 1 behoben, die
  Anwender-Entscheide wie beschlossen umgesetzt; keine neuen Befunde;
  Lint ohne Fehler.
- **Detail:** [[REP-003 — Kurz-Re-Check Migration Pruefer 1]]

### REP-004 — Kurz-Re-Check Migration, Prüfer 2

- **Modell:** gpt-6-luna — **Bau:** claude-opus-5-5 — **Runde:** 2 — **Datum:** 2026-11-09 — **Ampel:** gruen
- **Anlass:** Phase 4 Stufe 1 der Wissens-Migration, Kurz-Re-Check der
  Nacharbeit (Commits `6653def..857e33b`) über die Codex CLI im Klon;
  Vorgänger REP-002.
- **Kern-Ergebnis:** Testart, REC-001, Soll des Prüflaufs und die
  übrigen Nacharbeiten konsistent; keine Befunde; Lint ohne Fehler.
- **Detail:** [[REP-004 — Kurz-Re-Check Migration Pruefer 2]]
