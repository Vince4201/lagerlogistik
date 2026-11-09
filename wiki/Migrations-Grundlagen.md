---
type: decision
id: DEC-018
status: angenommen
status_date: 2026-11-09
datum: 2026-11-09
zielmarke: Uebernahme-2026-11-09
---

# Migrations-Grundlagen

Auffang- und Herkunftsknoten der Wissens-Migration vom 2026-11-09
nach `_template/migration/MIGRATION.md`. Er hält fest, wohin das
vorgefundene Wissen gegangen ist, was dabei offen blieb und woher es
stammt. Die Register-Zeile `### DEC-018 — Migrations-Grundlagen als
Gesamtpaket angenommen` in [[Entscheidungen]] wird erst mit der
Gesamt-Annahme durch den Anwender nach Phase 4 gebucht; bis dahin ist
der Lint-Hinweis `REG-OHNE-EINTRAG` erwartet.

## Festlegungen

1. **Wissensträger und Ziele** (Fragen-Block, Nr. 2, bestätigt):
   - `anforderungen.md` → [[Anforderungen]] REQ-001 bis REQ-033,
     feste Zuordnung An = REQ-0nn; die Nutzer-Rolle „Benutzer“ →
     [[Rollen]].
   - `datenmodell-und-ablaeufe.md` und `spezifikation.md` →
     [[Funktionsbeschreibungen]] FUNC-001 bis FUNC-009,
     [[Spezifikationen]] SPEC-001 und SPEC-002,
     [[Softwaresystemarchitektur]] ARC-001 bis ARC-003 und Abschnitt
     Ist-Stand.
   - Tests unter `src/test/java/`, `beispieldaten.txt` und
     `spezifikation.md` §Prüfen → [[Testfaelle]] TEST-001 bis
     TEST-042 (je JUnit-Testmethode ein Testfall) und
     [[Softwaresystemtest-Setup]].
   - Festlegungen aller Dateien, `CLAUDE-vorgefunden.md`, `pom.xml`,
     `lager.sh` und die Git-Historie bis `2eb6d29` →
     [[Entscheidungen]] DEC-001 bis DEC-012, [[InterneMechanik]]
     (4 Regeln, 5 Anleitungen), [[Chronologie]], [[Glossar Review]]
     (20 Vorschläge).
   - Projekteinstieg → [[Facts]] §1 und [[Mission Statement]]
     (Entwurf).
2. **Neu angelegt:** Übernahme-Marke `Uebernahme-2026-11-09`
   ([[Zielmarken]]), Ordner-Freigaben DEC-013 bis DEC-017,
   `Tools.md` mit den Prüf-Werkzeugen und dem Ersatzweg
   Ein-Modell-Weg, neue `TODO.md`; nach der Prüfrunde 1 der Nachweis
   REC-001 (archiviert unter `research/`).
3. **Version 1:** Der Satz „Alle Anforderungen gelten für Version 1.“
   (`anforderungen.md`) geht in die Beschreibung der Übernahme-Marke
   ein. Eine eigene Marke „1“ wird nicht nachgebaut (Grundsatz:
   frühere Versionen, Marken und Stände nicht nachbauen).
4. **Dokumentenkette als Änderungsweg** (`CLAUDE-vorgefunden.md`
   §Dokumentenkette; Commits `f3b2bd4`, `e11a977`, `0ef4466`) ist
   durch die Register und die Kettenprüfung des Referenzprozesses
   abgelöst. Zwischenzeitlich verworfen war die Variante „nur
   KONZEPT.md“ (`f3b2bd4`, zurückgenommen in `e11a977`).
5. **Entscheide des Anwenders zu den Prüfberichten der Runde 1**
   (REP-001, REP-002; „alles wie empfohlen“, 2026-11-09):
   - **Testart:** Projekt-Regel in
     [[Testname-mit-Anforderungsnummer]]: Jeder JUnit-Test mit
     Anforderungsnummer ist ein Testfall. TEST-001 bis TEST-033
     (Datenbank- und Serviceklassen) `integration`, TEST-034 bis
     TEST-042 (über die Konsole) `system`.
   - **DEC-001, DEC-002, DEC-003, DEC-005, DEC-006** bleiben als
     Rahmen-Festlegungen, aus REQ-001 bis REQ-003, REQ-006 und
     REQ-024 bis REQ-026 abgeleitet; ARC-001 stützt sich auf sie.
   - **Komponenten-Zeile** in [[Softwaresystemarchitektur]]
     §Komponenten angelegt, Repo-Name `lagerlogistik` (wie auf
     GitHub); Remote `https://github.com/Vince4201/lagerlogistik`.
   - **Keine Zielumgebung und kein Deployment:** vorgefunden ist nur
     lokaler Einzelplatzbetrieb; Start und Bau stehen als Anleitung
     [[Bauen-und-Starten-mit-lager-sh]] in [[InterneMechanik]].
   - **Alte Prüfergebnisse** aus Commit `2eb6d29` als Nachweis
     [[REC-001 — Pruefergebnisse zum Stand von Commit 2eb6d29]];
     daraus das Soll-Ergebnis des Prüflaufs (12 Posten, Summe 1907)
     in [[Softwaresystemtest-Setup]].
   - **Testnamen:** Neue Tests beginnen mit `REQ-NNN`, die
     bestehenden behalten `A..`.
   - **Herkunftszeilen** („Quelle“, bei DEC „Verweise: Herkunft“)
     bleiben und sind in allen Registern Markdown-Links auf die
     Quelldatei.

## Ungeklärt

Je Punkt Herkunft, Frage und die vorsichtig gewählte Lösung bzw.
Empfehlung. Vorzulegen bei der Gesamt-Annahme.

1. **Sektionen der Anforderungen** (`anforderungen.md` §Rahmen):
   REQ-001 und REQ-002 stehen unter nicht-funktional, REQ-003 („nur
   Mengen, keine Preise“) unter funktional. Bestätigen?
2. **Status-Wahl:** REQ-001 bis REQ-033 und DEC-001 bis DEC-012
   `umgesetzt` (Beleg: Code und Tests), FUNC, SPEC, ARC und TEST
   `offen` nach der Regel ihrer Meta. `abgeschlossen` wurde nicht
   gewählt, weil es noch keinen Testbericht und keinen Git-Tag gibt;
   bei den DEC wäre `angenommen` vorsichtiger. Prüfer 1 (REP-001)
   hält `umgesetzt` für gedeckt (MIGRATION Phase 2: „nach dem, was
   Code belegt“). Empfehlung: so lassen; `umgesetzt` ohne Git-Tag ist
   eine Einschätzung.
3. **Regeln aus der Einleitung von `anforderungen.md`** („je
   Anforderung ein Satz“, „Nummern bleiben fest“): Empfehlung, sie als
   durch das REQ-Register abgedeckt zu betrachten (Kurzsatz je REQ,
   Kennungen werden nie wiederverwendet); keine eigene Regel.
4. **Herkunftszeilen:** Die REQ-Einträge tragen eine Zeile
   `- **Quelle:**`, die die Meta nicht kennt. _Entschieden
   2026-11-09:_ so lassen, einheitlich als Markdown-Link
   (§Festlegungen Nr. 5).
5. **Alte Testergebnisse** in `target/surefire-reports/` (12 Dateien
   vom 2026-11-02, Build-Ausgabe, nicht versioniert, nicht gelesen):
   nicht als Nachweis aufgenommen; der erste Testbericht entsteht im
   nächsten Testdurchgang. Die Prüfergebnisse aus der Nachricht von
   Commit `2eb6d29` stehen in REC-001 (§Festlegungen Nr. 5).
6. **Testart:** _Entschieden 2026-11-09:_ 33 TEST `integration`,
   9 TEST `system`, Projekt-Regel in
   [[Testname-mit-Anforderungsnummer]] (§Festlegungen Nr. 5).
7. **Prüflauf mit `beispieldaten.txt`:** Sein Soll-Ergebnis (12
   Posten, Summe 1907) stand in der Nachricht von Commit `2eb6d29`
   und steht jetzt im [[Softwaresystemtest-Setup]]. Offen bleibt nur:
   eigener TEST? Er hat keine Anforderungsnummer. Empfehlung: so
   lassen, er bleibt Teil des Setups.
8. **Eigene Zeile in §Komponenten:** _Entschieden 2026-11-09:_
   angelegt, Repo-Name `lagerlogistik` (§Festlegungen Nr. 5). Die
   Ein-/Zwei-Repo-Frage bleibt für Phase 5.
9. **Anforderungen ohne Funktionsbeschreibung:** REQ-001, REQ-003,
   REQ-004 und REQ-006 haben keine FUNC (A04 und A06 stehen nur im
   Datenmodell, jetzt ARC-001 und SPEC-001; A01 und A03 nur in
   §Prüfen). Jede REQ hat aber mindestens einen TEST. Empfehlung:
   so lassen und in der ersten Kettenprüfung nach der Migration
   entscheiden, statt jetzt FUNC zu erfinden.
10. **„Git ist das Gedächtnis“** (`CLAUDE-vorgefunden.md`, Sitzungsbeginn
    mit `git status` und `git log`): abgelöst durch den Sessionstart
    des Regelwerks (MasterPrompt §9, §6.5). Empfehlung: nicht
    übernehmen; die Historie bleibt Nachweis.
11. **Pflicht zum „Warum“ in Commit-Nachrichten**
    (`CLAUDE-vorgefunden.md`): Entscheidungen gehören jetzt in
    [[Entscheidungen]]. Empfehlung: als schlanke Regel behalten
    (Begründung und betroffene IDs); Entscheid mit der
    Instruktionsdatei in Phase 5.
12. **Eigener Commit-Präfix-Katalog** (`CLAUDE-vorgefunden.md`):
    abgelöst durch MasterPrompt §6.2 und die aktiven Prozess-Präfixe
    ([[Aktivierter-Prozess]]); `Datenmodell:`, `Spezifikation:` und
    `Test:` gibt es dort nicht. Empfehlung: nicht als Projekt-Präfixe
    deklarieren; mit der Commit-Sprache in Phase 5 abschließen.
13. **Verworfene Alternativen:** bei keiner DEC dokumentiert. Bei
    DEC-004 ist die eigene Bestandstabelle aus dem Begründungssatz der
    Quelle abgeleitet und so gekennzeichnet; bei den übrigen DEC
    steht „nicht dokumentiert“.
14. **Nicht eingeordnet:** die Bezüge auf das Kursskript „Arbeiten
    mit KI-Agenten“. Die GitHub-Adresse steht jetzt als Remote in
    §Komponenten von [[Softwaresystemarchitektur]], die
    Prüfergebnisse aus Commit `2eb6d29` in REC-001.
15. **Ordner `target/`** (Maven-Build-Ausgabe, per `.gitignore`
    ausgeschlossen): keine Ordner-Freigabe gebucht, weil nicht
    versioniert.
16. **`.gitignore` der Vorlage** wurde nicht kopiert, weil das Projekt
    eine eigene hat (Regel der Kopier-Liste). Deren Einträge
    (Office-Sperrdateien, `*.tmp`, `Thumbs.db`, `.DS_Store` u. a.)
    fehlen im Projekt. Ergänzen?
17. **Anwender-Terrain:** [[Facts]] §2 und §3 sind leer geblieben; das
    [[Mission Statement]] ist ein Entwurf, die Bestätigung im
    Setup-Dialog steht aus.

## Herkunft

- **Vorgefundene Wissensdateien**, unverändert als Nachweis im
  Projekt-Root: [anforderungen.md](../anforderungen.md),
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md),
  [spezifikation.md](../spezifikation.md),
  [beispieldaten.txt](../beispieldaten.txt), die Tests unter
  `src/test/java/`.
- **Umbenennung aus der Kollisionsfrage:** `CLAUDE.md` →
  [CLAUDE-vorgefunden.md](../CLAUDE-vorgefunden.md) per `git mv`
  (Option (c) der Kollisionsfrage: Inhalt integrieren, die alte Datei
  umbenannt behalten; Commit
  `7f3a3d3`). Die neue Instruktionsdatei entsteht in Phase 5 und
  verlinkt sie; bis dahin hat das Projekt keine `CLAUDE.md`.
- **Sicherung:** Der Alt-Zustand war vollständig committet; Commit
  `2eb6d29` gilt als Sicherung. Aufnahme-Commit `64f9b02`,
  Fragen-Block `7f3a3d3`.
- **Vorlage:** Template-Stand 2.1 seit 2026-11-09, Submodul
  `_template/` gepinnt auf `4fdc0ae` (release-2.1), Anbindungs-Modus B
  ([[Aktivierter-Prozess]] §Herkunft).

## Vermerke

- 2026-11-09 — Nacharbeit nach den Prüfberichten der Runde 1
  ([[REP-001 — Absicherungs-Review Migration Pruefer 1]],
  [[REP-002 — Absicherungs-Review Migration Pruefer 2]], beide gelb):
  Entscheide des Anwenders in §Festlegungen Nr. 5; Ungeklärt 4 bis 9,
  13 und 14 nachgeführt.
