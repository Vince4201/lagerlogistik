---
type: timeline
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Chronologie

## Meta

- **Typ:** `timeline` — rückwärtsgerichtete Zeitachse:
  was ist im Projekt passiert. Singleton.
- **Kategorie:** (b) Inline-Fassade im wiki/ — siehe
  Wiki.md §Kategorisierung der Register.
- **Eintragsformat:** pro Datum eine
  `## YYYY-MM-DD`-Sektion mit Bullet-Points
  „Ereignis-Kurzform → [[Wiki-Knoten]]" (Wikilink nur,
  wenn Detail-Knoten existiert).
- **Gewichtige Ereignisse:** zusätzlicher
  `event`-Detail-Knoten; der Chronologie-Eintrag
  verweist per Wikilink.
- **`event`-Detail-Knoten-Konventionen:**
  Frontmatter-Felder: [[Katalog-Chronologie]] — lesen
  beim Anlegen eines `event`-Knotens. Sektionen: Kontext / Beteiligte / Ausschnitt aus
  Ursprungsdokument / Bezüge (Case, auslösendes
  Document, Chronologie-Datum).
- **Redaktionelle Ereignisse** (Telefonate, Meetings):
  am Marker `(redaktionell)` erkennbar.
- **Pflege:** beim Ingest und laufend.

---

**Bearbeitungsstand:** 2026-11-09 — Migration Phase 3:
Git-Historie bis Commit `2eb6d29` verdichtet übernommen; die
Commit-Nachrichten bleiben der Nachweis
([[Nachschlagen-in-der-Git-Historie]]). Daten nur aus der
Git-Historie.

## 2026-11-02

- Lagerverwaltung Version 1 unter Versionskontrolle gestellt,
  Stand wie in der damaligen `KONZEPT.md` beschrieben:
  menügeführte Terminal-Anwendung in Java 21 mit SQLite,
  Bestand als View über dem Bewegungsprotokoll, Bewegungen per
  Trigger unveränderlich; die Instruktionsdatei `CLAUDE.md`
  legt die Arbeit mit Git fest (Commit `3b6f6b4`)
  → [[DEC-004 Bestand nur aus den Bewegungen berechnet]]
- Festlegung „nur `KONZEPT.md` als Konzeptdokument“ als
  Missverständnis zurückgenommen; `KONZEPT.md` in die
  Dokumentenkette `anforderungen.md` →
  `datenmodell-und-ablaeufe.md` → `spezifikation.md`
  aufgeteilt, ohne fachliche Änderung (Commits `f3b2bd4`,
  `e11a977`, `0ef4466`)
- Git-Regeln in `CLAUDE.md` an das Kursskript „Arbeiten mit
  KI-Agenten“ angeglichen: Commit je Zug, Präfix-Katalog,
  Prüfung vor Code-Commits, Abschluss-Commit, kein Push ohne
  Anweisung, kein Umschreiben der Geschichte (Commit
  `1013075`) → [[Keine-Umschreibung-der-Git-Historie]]
- Anforderungen in 33 nummerierte Sätze A01 bis A33 zerlegt,
  die Nummern bleiben seitdem fest (Commit `e9aa365`)
- Datenmodell um den Ablauf Auswerten ergänzt, die Abschnitte
  verweisen auf die Anforderungsnummern (Commit `7de2209`)
- Spezifikation nach Befehlen gegliedert, ohne fachliche
  Änderung (Commit `b962fc1`)
- JUnit-Tests für alle Anforderungen A01 bis A33 eingeführt;
  sie laufen seitdem bei jedem Build mit (Commit `2eb6d29`)
  → [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]
