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
REQ-033 in derselben Zählung.

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
A33).

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Test-Technik: [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]
