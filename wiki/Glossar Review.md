---
type: glossary-review
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Glossar Review

## Meta

- **Typ:** `glossary-review` — Glossar-Vorschläge in
  Bearbeitung und Audit-Trail freigegebener Einträge.
  Singleton.
- **Kategorie:** (b) Inline-Fassade im wiki/ — siehe
  Wiki.md §Kategorisierung der Register.
- **Prozess** (Wiki.md §3a.1):
  1. Vorschlag (Agent oder User) unter
     `## In Bearbeitung`.
  2. Iterativer Dialog: User → `Kommentar:` → Agent →
     `Rückmeldung AIG:` → …
  3. User setzt `Freigabe: <Datum> <TLA>`.
  4. Agent verschiebt die inhaltlichen Felder nach
     [[Glossar]].
  5. Vollständiger Trail bleibt hier unter
     `## Freigegeben (Audit-Trail)`.
- **Eintragsformat:** `### <Begriff>` mit `**English:**`,
  `**Freigabe:**`, `**Kommentar:**`,
  `**Rückmeldung AIG:**`, Definition in ein bis zwei
  Sätzen, `**Vormals:**` und `**Nicht verwenden:**`
  (nur, wo sie anfallen), `**Verankert in:**`,
  `**Siehe auch:**` — dieselben inhaltlichen Felder
  wie in [[Glossar]] (dort das Format), plus die
  Prozess-Felder.

---

**Bearbeitungsstand:** 2026-11-09 — Migration Phase 3:
Fachbegriffe aus `anforderungen.md`,
`datenmodell-und-ablaeufe.md` und `spezifikation.md` als
Vorschläge des Agenten (alphabetisch); die Herkunft steht je
Eintrag unter „Verankert in“. Gesammelte Freigabe in Phase 5
der Migration.

## In Bearbeitung

### Artikel

**English:** item
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Ware, deren Menge die Lagerverwaltung führt; ein Artikel hat
eine Nummer, eine Bezeichnung, eine Einheit und einen
Mindestbestand und kann an beliebig vielen Lagerorten liegen.

**Verankert in:** [[Anforderungen]] REQ-021, REQ-004
(Herkunft `anforderungen.md` A21, A04)

### Bestand

**English:** stock
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Menge eines Artikels an einem Lagerort: die dorthin gebuchten
Mengen minus die von dort abgebuchten. Er wird nicht
gespeichert, sondern aus den Bewegungen berechnet.

**Verankert in:** [[Anforderungen]] REQ-005;
[[DEC-004 Bestand nur aus den Bewegungen berechnet]]
(Herkunft `anforderungen.md` A05,
`datenmodell-und-ablaeufe.md` §Bestand)

### Bewegung

**English:** stock movement
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Protokollierte Bestandsänderung eines Artikels mit
fortlaufender Nummer, Zeitpunkt, Art, Menge und
Von- oder Nach-Lagerort oder beiden. Einmal gebucht, wird
sie nie geändert oder gelöscht.

**Verankert in:** [[Anforderungen]] REQ-005, REQ-006
(Herkunft `anforderungen.md` A05, A06;
`datenmodell-und-ablaeufe.md` §Datenmodell)

### Bewegungsprotokoll

**English:** movement log
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Die gebuchten Bewegungen in Buchungsreihenfolge, aus denen
sich der Bestand ergibt; die gleichnamige Auswertung zeigt
sie, filterbar nach Artikel und Lagerort.

**Verankert in:** [[Anforderungen]] REQ-030 (Herkunft
`anforderungen.md` A30; `datenmodell-und-ablaeufe.md`
§Bestand, §Auswerten)

### Buchung

**English:** posting
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Vorgang, der eine Bewegung anlegt. Er wird ganz oder gar nicht
ausgeführt; verletzt er eine harte Regel, wird er abgelehnt.

**Verankert in:** [[Anforderungen]] REQ-008 bis REQ-016
(Herkunft `anforderungen.md` §Buchungen, §Harte Regeln;
`datenmodell-und-ablaeufe.md` §Buchung)

### deaktivieren

**English:** deactivate
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Einen Artikel oder Lagerort, zu dem es Bewegungen gibt, statt
zu löschen sperren — nur ohne Bestand. Deaktiviert lässt er
sich nicht bebuchen, aber wieder aktivieren.

**Verankert in:** [[Anforderungen]] REQ-025, REQ-026;
Entscheidung DEC-006 (Herkunft `anforderungen.md` A25, A26)

### Einheit

**English:** unit of measure
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Text, der zur Menge eines Artikels angezeigt wird (Vorgabe
Stk). Die Einheit ist nur ein Anzeigetext; Mengen sind ganze
Zahlen.

**Verankert in:** [[Anforderungen]] REQ-007, REQ-021
(Herkunft `anforderungen.md` A07, A21)

### Fehlmenge

**English:** shortfall
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Mindestbestand minus Gesamtbestand eines Artikels, der unter
seinem Mindestbestand liegt.

**Verankert in:** [[Anforderungen]] REQ-029 (Herkunft
`anforderungen.md` A29; `datenmodell-und-ablaeufe.md`
§Auswerten)

### Gesamtbestand

**English:** total stock
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Summe der Bestände eines Artikels über alle Lagerorte.

**Verankert in:** [[Anforderungen]] REQ-017, REQ-027
(Herkunft `anforderungen.md` A17, A27;
`datenmodell-und-ablaeufe.md` §Bestand)

### harte Regel

**English:** hard rule
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Regel für Buchungen, deren Verletzung die Buchung ablehnt; es
wird dann nichts gespeichert und der Grund gemeldet.

**Verankert in:** [[Anforderungen]] REQ-012 bis REQ-016
(Herkunft `anforderungen.md` §Harte Regeln;
`datenmodell-und-ablaeufe.md` §Buchung)

### Korrekturbuchung

**English:** stock adjustment
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Buchung, die bei einer Inventur den gezählten Bestand eines
Artikels an einem Lagerort übernimmt und die Differenz zum
gebuchten Bestand bucht; sie braucht eine Begründung. Mit ihr
werden Fehler ausgeglichen, statt Bewegungen zu ändern.

**Verankert in:** [[Anforderungen]] REQ-006, REQ-011,
REQ-016 (Herkunft `anforderungen.md` A06, A11, A16;
Menüpunkt „Korrektur / Inventur“ in `spezifikation.md`)

### Lagerort

**English:** storage location
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Ort im Lager, an dem Artikel liegen; er hat einen Code
(z. B. A-01-03) und eine optionale Bezeichnung.

**Verankert in:** [[Anforderungen]] REQ-022, REQ-004
(Herkunft `anforderungen.md` A22, A04)

### Mindestbestand

**English:** minimum stock level
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Untergrenze für den Gesamtbestand eines Artikels; 0 heißt
keine Überwachung. Liegt der Gesamtbestand nach einer
Buchung, die ihn senkt, darunter, wird gewarnt, aber nicht
abgelehnt.

**Verankert in:** [[Anforderungen]] REQ-017, REQ-018
(Herkunft `anforderungen.md` §Mindestbestand)

### Nach-Lagerort

**English:** destination location
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Lagerort, an dem eine Bewegung die Menge zubucht; bei
Wareneingang und Umlagerung der Ziel-Lagerort.

**Verankert in:** [[Anforderungen]] REQ-008, REQ-010
(Herkunft `anforderungen.md` A08, A10;
`datenmodell-und-ablaeufe.md` §Menge und Richtung einer
Bewegung)

### Stammdaten

**English:** master data
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Die Artikel und Lagerorte mit ihren Eigenschaften; angelegt,
bearbeitet, gelöscht oder deaktiviert werden sie über das
Stammdatenmenü.

**Verankert in:** [[Anforderungen]] REQ-021 bis REQ-026
(Herkunft `anforderungen.md` §Stammdaten;
`spezifikation.md` §Stammdatenmenü)

### Umlagerung

**English:** stock transfer
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Buchung, die eine Menge eines Artikels von einem Lagerort an
einen anderen bringt, ohne den Gesamtbestand zu ändern; sie
löst nie eine Mindestbestandswarnung aus.

**Verankert in:** [[Anforderungen]] REQ-010, REQ-015,
REQ-019 (Herkunft `anforderungen.md` A10, A15, A19)

### Von-Lagerort

**English:** source location
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Lagerort, an dem eine Bewegung die Menge abbucht; bei
Warenausgang und Umlagerung der Quell-Lagerort.

**Verankert in:** [[Anforderungen]] REQ-009, REQ-010
(Herkunft `anforderungen.md` A09, A10;
`datenmodell-und-ablaeufe.md` §Menge und Richtung einer
Bewegung)

### Warenausgang

**English:** goods issue
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Buchung, die den Bestand eines Artikels an einem
Quell-Lagerort senkt. Auslagern heißt hier Warenausgang.

**Verankert in:** [[Anforderungen]] REQ-009 (Herkunft
`anforderungen.md` A09; `datenmodell-und-ablaeufe.md`
§Abläufe)

### Wareneingang

**English:** goods receipt
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Buchung, die den Bestand eines Artikels an einem
Ziel-Lagerort erhöht. Einlagern heißt hier Wareneingang.

**Verankert in:** [[Anforderungen]] REQ-008 (Herkunft
`anforderungen.md` A08; `datenmodell-und-ablaeufe.md`
§Abläufe)

### weiche Regel

**English:** soft rule
**Freigabe:**
**Kommentar:**
**Rückmeldung AIG:**

Regel, bei der die Buchung ausgeführt wird und danach eine
Warnung kommt.

**Verankert in:** [[Anforderungen]] REQ-017 bis REQ-019
(Herkunft `anforderungen.md` §Mindestbestand)

## Freigegeben (Audit-Trail)

_(Agent verschiebt Einträge hierher, sobald sie in
[[Glossar]] übernommen wurden.)_
