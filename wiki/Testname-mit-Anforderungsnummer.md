---
type: internal-rule
---

# Testname mit Anforderungsnummer

**Regel:** Jede Anforderung hat mindestens einen
automatischen Test (JUnit, unter `src/test/`), dessen Name
mit ihrer Nummer beginnt, z. B. `@DisplayName("A14 …")`.
Kommt eine Anforderung dazu, kommt ihr Test dazu.

**Nummer:** die Nummer aus `anforderungen.md` (A01 bis A33);
im Register [[Anforderungen]] stehen sie als REQ-001 bis
REQ-033 in derselben Zählung. Ein neuer Test beginnt mit der
REQ-Nummer, z. B. `@DisplayName("REQ-034 …")`; die bestehenden
Namen mit `A..` bleiben.

**Testfälle im Register:** Jeder JUnit-Test mit
Anforderungsnummer ist ein Testfall in [[Testfaelle]], je
Testmethode einer — auch wenn er unterhalb der Konsole gegen
Service und Datenbank läuft. Testart: `system`, wenn der Test
über die Konsole bedient (`LagerKonsoleTest`), sonst
`integration`. Das ist die Projekt-Regel, die die Meta von
[[Testfaelle]] für Integrationstests verlangt.

**Abgrenzung:** Die Regel betrifft die automatischen Tests im
Code. Welche Prüfungen als Testfälle im Register
[[Testfaelle]] stehen, regelt dessen `## Meta`; wie die Tests
aufgebaut sind, [[Softwaresystemtest-Setup]]; wie sie laufen,
[[Testen-mit-Maven]].

## Herkunft

Bestand aus `CLAUDE-vorgefunden.md` §Dokumentenkette (Glied 5
„Test“); gleichlautend `spezifikation.md` §Prüfen („Jeder Test
beginnt im Namen mit der Nummer der Anforderung, die er
prüft.“). Eingeführt mit Commit `2eb6d29` (Tests für A01 bis
A33). Ergänzt am 2026-11-09 um „Testfälle im Register“ und
die REQ-Nummer für neue Tests: Anwender-Entscheid zu den
Prüfberichten REP-001 (Befunde 9, 12) und REP-002 (Befund 1)
der Migration ([[Migrations-Grundlagen]] §Festlegungen).

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Test-Technik: [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]
