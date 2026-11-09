---
type: research-artifact-log
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Rechercheartefakte

## Meta

- **Typ:** `research-artifact-log` — Register aller
  Rechercheartefakte des Projekts. Singleton.
- **Kategorie:** (a) Fassade + Detail-Knoten im
  wiki/ — siehe Wiki.md §Kategorisierung der
  Register.
- **Verseuchungs-Warnung (fester Kopf-Satz):**
  Diese Fassade sammelt **extern beschafftes
  Wissen**. Externe Aussagen können verseucht sein
  (Fehler, Bias, veraltet, absichtlich manipuliert).
  Rechercheartefakte sind **Nachweis, nicht
  Wahrheit**. Die Übernahme einer Aussage ins
  Projekt ist eine bewusste Anwender-Entscheidung
  und wird als Entscheidung im Register gebucht —
  das Rechercheartefakt selbst dient als Beleg.
- **Nachweis, eingefroren ab Anlage:** ein
  Rechercheartefakt wird nie abgelöst und nie
  geändert; Nachträge stehen als angehängte Vermerke
  ([[Einfrieren-und-Abloesen]] §Klassen). Die
  Fassaden-Zeile spiegelt den Detail-Knoten
  (Wiki.md §1.1a).
- **Eintragsformat:**
  `### REC-NNN — <Kurzsatz>` mit Feldern
  Quelle(n) + Datum / Frage / Kern-Ergebnis
  (1–2 Sätze) / Übernommen als DEC
  (Wikilink oder „offen") / Detail (Wikilink).
- **Detail-Knoten-Konventionen:** Frontmatter-Felder:
  [[Katalog-Rechercheartefakte]] — lesen beim Anlegen
  eines REC-Knotens. Sektionen: Frage / Recherche-Verlauf /
  Fundstellen (Zitate als Blockquotes mit
  Quellen-Angabe unmittelbar am Zitat) / Ergebnis /
  Vermerke (append-only) / Bezüge. Dateiname
  `REC-NNN — <Kurzsatz>.md`, flach im wiki/.
- **Ingest-Regel (Standardaufgabe):** nach
  Abschluss einer externen Recherche nächste freie
  REC-Nummer vergeben, Detail-Knoten anlegen,
  Fremdaussagen als solche kennzeichnen,
  **Quellen archivieren** (alles, worauf ein Zitat
  oder eine Übernahme-Buchung sich stützt, als
  Kopie/Snapshot nach `research/REC-NNN -
  <Kurzsatz>/` — MasterPrompt §1.8; ein flüchtiger
  Link allein ist kein Nachweis), Fassaden-Eintrag
  ergänzen („Übernommen als DEC: offen"), Commit
  mit Präfix `Recherche:`.
- **Übernahme ins Projekt:** eigene
  Anwender-Aktion, als Entscheidung gebucht;
  danach das Feld „Übernommen als DEC" mit
  Wikilink vervollständigen.

---

**Bearbeitungsstand:** 2026-11-09 — REC-001 in der Migration
angelegt (alte Prüfergebnisse aus der Git-Historie).

## Einträge

_(chronologisch nach ID)_

### REC-001 — Prüfergebnisse zum Stand von Commit 2eb6d29

- **Quelle(n) + Datum:** Nachricht von Commit `2eb6d29`
  (2026-11-02), archiviert unter
  `research/REC-001 - Pruefergebnisse zum Stand von Commit 2eb6d29/`;
  abgelegt am 2026-11-09
- **Frage:** Welche Prüfergebnisse hielt das Projekt vor der
  Migration fest, und was ist das Soll-Ergebnis des Prüflaufs?
- **Kern-Ergebnis:** 42 JUnit-Tests grün; Prüflauf mit
  `beispieldaten.txt` ohne Fehler, 12 Bestandsposten, Summe 1907;
  Gegenprobe mit zwei absichtlichen Fehlern machte 5 bzw. 1 Test rot.
- **Übernommen als DEC:** offen
- **Detail:** [[REC-001 — Pruefergebnisse zum Stand von Commit 2eb6d29]]
