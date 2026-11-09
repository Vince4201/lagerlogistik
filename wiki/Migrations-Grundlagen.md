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
   Ein-Modell-Weg, neue `TODO.md`.
3. **Version 1:** Der Satz „Alle Anforderungen gelten für Version 1.“
   (`anforderungen.md`) geht in die Beschreibung der Übernahme-Marke
   ein. Eine eigene Marke „1“ wird nicht nachgebaut (Grundsatz:
   frühere Versionen, Marken und Stände nicht nachbauen).
4. **Dokumentenkette als Änderungsweg** (`CLAUDE-vorgefunden.md`
   §Dokumentenkette; Commits `f3b2bd4`, `e11a977`, `0ef4466`) ist
   durch die Register und die Kettenprüfung des Referenzprozesses
   abgelöst. Zwischenzeitlich verworfen war die Variante „nur
   KONZEPT.md“ (`f3b2bd4`, zurückgenommen in `e11a977`).

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
   bei den DEC wäre `angenommen` vorsichtiger. Angleichen?
3. **Regeln aus der Einleitung von `anforderungen.md`** („je
   Anforderung ein Satz“, „Nummern bleiben fest“): Empfehlung, sie als
   durch das REQ-Register abgedeckt zu betrachten (Kurzsatz je REQ,
   Kennungen werden nie wiederverwendet); keine eigene Regel.
4. **Herkunftszeilen:** Die REQ-Einträge tragen eine Zeile
   `- **Quelle:**`, die die Meta nicht kennt; die übrigen Register
   nennen die Herkunft unter Verweise oder Bezüge. Vereinheitlichen
   oder so lassen?
5. **Alte Testergebnisse** in `target/surefire-reports/` (12 Dateien
   vom 2026-11-02, Build-Ausgabe, nicht versioniert, nicht gelesen):
   Empfehlung, sie nicht als Nachweis aufzunehmen; der erste
   Testbericht entsteht im nächsten Testdurchgang.
6. **Testart:** Alle 42 TEST tragen `testart: system`. 33 davon laufen
   technisch unterhalb der Konsole; `integration` bräuchte laut Meta
   von [[Testfaelle]] eine Projekt-Regel in [[InterneMechanik]].
7. **Prüflauf mit `beispieldaten.txt`:** Er hat kein Soll-Ergebnis und
   keine Anforderungsnummer und steht deshalb nur im
   [[Softwaresystemtest-Setup]]. Eigener TEST?
8. **Eigene Zeile in §Komponenten** von
   [[Softwaresystemarchitektur]] (Pfad `.`) ist nicht angelegt. Namen
   in den Quellen: Ordner `largerlogistik`, GitHub-Repo
   `Vince4201/lagerlogistik`, Maven-Artefakt `lagerverwaltung`.
   Empfehlung: mit der Ein-/Zwei-Repo-Frage in Phase 5 klären.
9. **Anforderungen ohne Funktionsbeschreibung:** REQ-001, REQ-003,
   REQ-004 und REQ-006 haben keine FUNC (A04 und A06 stehen nur im
   Datenmodell, jetzt ARC-001 und SPEC-001; A01 und A03 nur in
   §Prüfen). Eigene FUNC anlegen oder so lassen?
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
13. **Verworfene Alternativen:** nur bei DEC-004 belegt; bei den übrigen
    DEC steht „nicht dokumentiert“.
14. **Nicht eingeordnet:** die öffentliche GitHub-Adresse des Projekts
    (Kandidat für [[Facts]] §2, Anwender-Terrain, nur auf Anweisung),
    die Bezüge auf das Kursskript „Arbeiten mit KI-Agenten“ und die
    Prüfergebnisse in der Nachricht von Commit `2eb6d29` (Kandidaten
    für [[Rechercheartefakte]]).
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

_(keine)_
