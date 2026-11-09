---
type: decision-log
active: true   # kann nicht deaktiviert werden — Entscheidungen ist Pflicht-Fassade (mandatory)
---

# Entscheidungen

## Meta

- **Typ:** `decision-log` — Register aller
  Entscheidungen des Projekts (universeller
  Mechanismus: jedes Projekt dokumentiert
  Entscheidungen). Singleton, Pflicht-Fassade in
  jedem Projekt (Wiki.md §3a): hier werden auch
  Freigaben, die Freigabe des Abschluss-Stands und
  der Marken-Wechsel gebucht ([[Zielmarken]] §Meta).
- **Kategorie:** (a) Fassade + Detail-Knoten im wiki/ —
  siehe Wiki.md §Kategorisierung der Register.
- **Kanonizität:** die Wahrheit bei
  Entscheidungs-Status liegt in dieser Fassade —
  Detail-Knoten (Typ `decision`) spiegeln
  (Wiki.md §1.1a).
- **Eintritts-Register:** ein DEC kommt erst mit
  Anwender-OK ins Register, direkt als `angenommen`;
  `offen`, `aufgeschoben` und Auslöser gibt es hier
  nicht. Wohin eine nicht getroffene oder
  zurückgestellte Entscheidung gehört (nach [[Ideen]])
  und auf welchen Registern Wächter und Marken-Trigger
  arbeiten: [[Einfrieren-und-Abloesen]]
  §Eintrittsregel — lesen, bevor eine Entscheidung
  zurückgestellt wird.
- **Eintragsformat:** `### DEC-NNN — <Gegenstand>` mit
  Feldern Status (angenommen | in-umsetzung |
  umgesetzt | abgeschlossen | verworfen | abgelöst) /
  Datum (letzter Statuswechsel) / Angelegt
  (Anlage-Datum, Pflichtfeld seit 2.0 — bei einem
  Eintrag ohne Detail-Knoten die Zeile
  `- **Angelegt:** JJJJ-MM-TT`, sonst `datum:` im Kopf
  des Detail-Knotens; wird nie geändert, [[Katalog]]
  §Typ-Konventionen) / Zielmarke
  ([[Zielmarken]]; Pflichtfeld, kanonisch hier — beim
  Anlegen die `in-progress`-Marke, für eine spätere
  Marke Geplantes diese Marke; ein Freigabe-Eintrag
  trägt die Marke der Freigabe, ein
  Marken-Wechsel-Eintrag die abgeschlossene Marke —
  unten) / Wahl und
  Begründung / Verweise / Nachbarschaft (die geprüften
  Nachbarn, Kollisions-Grep unten) / Detail (Wikilink,
  nur wenn Detail-Knoten existiert). Die Felder stehen
  als Zeilen `- **Feld:** Wert` unter der Überschrift,
  bei einem Eintrag ohne Detail-Knoten auch Angelegt
  und Zielmarke (so liest sie der Lint); mit
  Detail-Knoten steht Zielmarke als Anzeige ebenfalls im
  Eintrag — dieselbe Feldzeile, der Lint liest sie und
  prüft sie gegen den Knoten ([[Zielmarken]] §Meta
  „Sitz“) —, z.B.
  `- **Status:** angenommen — **Datum:** JJJJ-MM-TT`,
  `- **Angelegt:** JJJJ-MM-TT` und
  `- **Zielmarke:** 1.0`; ein bei der Buchung
  vergessenes `Zielmarke` heilt der angehängte Vermerk
  `- **Vermerk <Datum>:** Zielmarke <M> (nachgetragen)`
  am Eintrag, den der Lint wie das Feld liest
  ([[Zielmarken]] §Meta „Heilweg“). Bei `abgelöst` steht der
  Verweis nur im Feldpaar `supersedes:`/`superseded_by:`.
- **Freigabe-Einträge** (dieser Absatz ist der
  führende Ort ihres Aufbaus; Wortlaut der Formel,
  Tag-Fall, Zweck-Katalog und Marken-Koordinate:
  `Vorgehen.md` §4, lesen vor der Buchung einer
  Freigabe). Ein Freigabe-Eintrag ist ein DEC-Eintrag
  im Eintragsformat oben:
  - **Gegenstand** ist die Freigabe-Formel (Wortlaut:
    `Vorgehen.md` §4), **wörtlich eingehalten**: sie
    ist zugleich das Kennzeichen, an dem der Lint
    einen Freigabe-Eintrag erkennt und daraus Stand
    mit Version bzw. Tag-Name, Dokumenttyp, Zweck und
    Marke liest ([[Kettenpruefung]] §Lint-Anteil);
    eine Abweichung vom Wortlaut meldet er (Schwere
    dort). Es gibt kein eigenes Feld für Freigaben und
    keine Detail-Knoten-Pflicht über die Fälle unten
    hinaus.
  - **Verweise:** die Stand-Datei in `output/` mit
    Version — als Dateiname mit Endung `.md`, als Text
    ohne Wikilink (`output/` liegt außerhalb von
    `wiki/`; ein Wikilink mit Endung ist ein toter
    Link), z.B. `2026-10-20 - Deploy-Stand -
    Produktion-EU_01_ABC.md`; daran liest der Lint die
    Datei, auch ohne die Endung, und unterscheidet bei
    Deploy-Ständen mit derselben Version die Umgebung
    ([[Kettenpruefung]] §Lint-Anteil) — (beim Tag der
    Implementierung statt dessen
    der Tag-Name und die Nachweise des Tags: der
    `review-report` der Kettenprüfung des Glieds P9 —
    das Code-Review — und, soweit das Produkt
    automatisiert Prüfbares hat, Tests grün —
    [[Kettenpruefung]] §Code-Glieder) und, wenn ein
    Pflicht-Stand freigegeben wird, der
    `review-report` der Kettenprüfung (nicht beim
    Abschluss-Stand, der nur auf Vollständigkeit
    geprüft wird, [[Kettenpruefung]] §Regel) und bei
    den drei Doku-Ständen der Kette (Entwickler-Doku,
    Benutzer-Doku, Betriebs-Anleitung; mit SP-1) der
    Bericht des Verständnis-Checks M6
    (`Reviewmuster.md`); Prüfer
    und Reife stehen dort.
  - **Wahl und Begründung:** das Ergebnis der
    Kettenprüfung. Eine **Einschränkung** steht
    ausdrücklich da, je nach Fall: **fehlender
    Vorgänger** — ein früherer Pflicht-Stand fehlt
    ohne Verzicht und der Anwender gibt trotzdem frei
    ([[Kettenpruefung]] §Kette, Freigabe-Ordnung als
    Warnung); **„Doku folgt“** — ein Doku-Glied P11
    oder P12 fehlt ohne Verzicht und der Deploy-Stand
    wird trotzdem freigegeben ([[Kettenpruefung]]
    §Kette, Doku-Glieder); **„Teil-Doku <Komponente>
    fehlt“** — die Entwickler-Doku einer Komponente ist
    „später“ oder verzichtet und der Koordinator gibt
    seine Gesamtsicht trotzdem frei ([[Kettenpruefung]]
    §Freigabe-Ordnung des Koordinators); **offene BUG** — der
    Testbericht weist
    nicht Bestandenes aus und der Anwender gibt
    trotzdem für P13 oder einen Doku-Stand frei
    ([[Kettenpruefung]]
    §Code-Glieder); **Prüf-Modell = Bau-Modell** — die
    Prüfung lief im Ein-Modell-Weg (`Reviewmuster.md`
    §2 Schleife 2). Ebenso eine Notfall-Freigabe
    (`Reviewmuster.md` §1).
  - **Zielmarke:** die Marke der Freigabe — mit
    Stand-Datei die `marke` aus dem Kopf des Stands,
    ohne Stand-Datei die Marke aus „zu Marke M“; beide
    Angaben nennen dieselbe Marke, nicht ohne Weiteres
    die beim Buchen laufende (`in-progress`-)Marke. So
    gehört eine
    während einer Zwischenmarke gebuchte Freigabe für
    die zurückgestellte Marke zu dieser, und ihre
    Ablösung einer älteren Freigabe (Neu-Stand, unten)
    wirkt ab ihr ([[Einfrieren-und-Abloesen]]
    §Ablösung). Weicht das Feld von der Marke der
    Freigabe ab, meldet der Lint einen Hinweis.
  - **Neu-Stand:** seine Freigabe löst die alte per
    `supersedes:` ab ([[Einfrieren-und-Abloesen]]
    §Überholt-Regel) — wird derselbe Gegenstand erneut
    freigegeben, ist das ein neuer Eintrag, der den
    früheren ablöst; eine Freigabe wird nur durch die
    Freigabe eines neuen Stands abgelöst, beim Tag durch
    die Freigabe eines neuen Tags vor dem Marken-Wechsel.
    Die Ablösung steht im
    Frontmatter beider Elemente (Wiki.md §1.1a);
    ablösende und abgelöste Freigabe tragen deshalb
    einen Detail-Knoten.
  - **Ausnahme „Nachtrag zu einer abgeschlossenen
    Marke“:** Jede Freigabe, die nach dem Marken-Wechsel
    zu einer abgeschlossenen Marke gehört, steht als
    Formel im Nachtrag am Marken-Eintrag dieser Marke
    ([[Zielmarken]] §Meta, Nachtrag — die Formate stehen
    dort), nicht als eigener Freigabe-Eintrag. Der
    Nachtrag ist der Träger der Freigabe, weil die Marke
    abgeschlossen und eingefroren ist und ihr nur noch
    Vermerke folgen. Der Nachtrag löst nichts ab — kein
    `supersedes:` im Nachtrag, kein `superseded_by:` an
    einem Freigabe-Eintrag der Marke —, die Freigaben
    der Marke bleiben stehen, der Abschluss-Stand bleibt
    unverändert. Drei Fälle: **(a) Tag-Korrektur** —
    wird der Tag der Marke nach dem Wechsel bei gleichem
    Inhalt der Pfade des Produkts korrigiert, steht die
    neue Tag-Freigabe zu derselben Marke im Nachtrag
    „Tag korrigiert“; die frühere Tag-Freigabe wird
    nicht abgelöst, es gibt keinen neuen Stand, den sie
    ablösen könnte. **(b) Nachgetragener Deploy-Stand**
    — entsteht zu der Marke ohne Änderung in der Kette
    ein weiterer Deploy-Stand (dieselbe Software in
    einer neuen Umgebung oder in einer Umgebung, die
    anders ausgerollt wird; je Umgebung gilt der
    jüngste), steht seine Freigabe in der
    Nachtragszeile des Stands; sie wirkt wie jede
    Deploy-Stand-Freigabe (Pflicht-Freigabe der Umgebung
    für Lint und Vollständigkeits-Scan; Zeitpunkt und
    Form der Betriebs-Anleitung dieser Umgebung
    entscheidet die Doku-Abfrage, [[Kettenpruefung]]
    §Kette). **(c) Doku-Stand „später“** — wird ein
    Doku-Stand der Kette (Entwickler-Doku, Benutzer-Doku,
    Betriebs-Anleitung), der in der Doku-Abfrage
    „später“ erhielt, erst nach dem Wechsel freigegeben,
    steht seine Freigabe in derselben Form in der
    Nachtragszeile des Stands (Nachweise: `review-report`
    der Kettenprüfung, M6-Bericht, Testbericht); sie
    zählt als Pflicht-Freigabe des Doku-Glieds. Der Lint
    erkennt
    die Freigabe im Nachtrag an der Formel, ordnet sie
    der Marke des Eintrags zu und erwartet keine
    abgelöste Freigabe. Eine Freigabe im Nachtrag ist
    kein Meilenstein-Anlass (mit SP-1:
    `Meilenstein-und-T2`). Vor dem Wechsel bleibt es beim
    Neu-Stand (oben): jede Freigabe ein Eintrag, die
    erneute löst ab.
  - **Keine verworfenen Alternativen:** eine Freigabe
    ist keine Wahl zwischen Lösungen; die Felder oben
    sind vollständig (der Pflichtteil unten gilt hier
    nicht).
  - **Ordner-Freigabe** (MasterPrompt §1.1, Quelle (a)
    nach dem Init): ein DEC-Eintrag ohne Stand-Datei und
    ohne die Formel — der Lint liest ihn nicht; der
    Gegenstand nennt den freigegebenen Ordner (daran
    findet der Struktur-Wächter C6 die Buchung);
    `zielmarke:` ist die beim Buchen laufende
    (`in-progress`-)Marke, im Init die erste Marke; der
    Pflichtteil „Verworfene Alternativen“ gilt nicht
    (unten).
- **Marken-Wechsel-Einträge:** Der Wechsel des
  `in-progress`-Zeigers (Schritt 4 der
  Wechsel-Prozedur, [[Zielmarken]] §Meta) wird als
  DEC-Eintrag im Eintragsformat oben gebucht:
  Gegenstand „Marken-Wechsel von <A> zu <B>“ (nach
  einer Zwischenmarke ist B eine folgende Zwischenmarke
  oder, bei der Rückkehr, die zurückgestellte Marke;
  fehlt eine künftige Marke und ruht das Projekt,
  lautet der Gegenstand „Marken-Wechsel von <A>; Projekt
  ruht“ ohne B — [[Zielmarken]] §Meta);
  Verweise: der freigegebene Abschluss-Stand von A mit
  Version und sein Freigabe-Eintrag; Zielmarke: die
  abgeschlossene Marke A (der Eintrag ist ihr
  Abschluss-Akt; bei der Rückkehr aus einer
  Zwischenmarke also die Zwischenmarke); Begründung: das
  Ergebnis des Vollständigkeits-Scans in einem Satz
  (verschobene Punkte mit Ziel-Marke). Wie die Freigabe
  ist der Wechsel keine Wahl zwischen Lösungen — der
  Pflichtteil unten gilt nicht. Der Lint liest den
  Eintrag nicht; die Zeiger liest er aus den Zeilen
  `Zustand:` in [[Zielmarken]].
- **Verzichts-Einträge:** Lässt der Anwender eine
  Phase mit Pflicht-Stand für eine Marke bewusst aus
  (`Vorgehen.md` §1), wird das als DEC mit
  `decision_type: verzicht`, `zielmarke:` (die Marke)
  und `phase: Pn` (die ausgelassene Phase — genau
  eine je DEC) gebucht;
  ohne diese Felder gilt kein Verzicht. Für P9 gibt es
  keinen Verzicht (der Tag markiert das Produkt in
  jedem Projekt, [[Kettenpruefung]] §Kette); eine DEC
  mit `phase: P9` ist kein Verzicht, der Lint meldet
  sie (ebenda §Lint-Anteil). Der Verzicht auf die
  Betriebs-Anleitung (`phase: P15`) gilt **je
  Umgebung**: die DEC trägt zusätzlich `umgebung:` mit
  dem Namen der Umgebung aus der Entitäts-Fassade der
  Zielumgebungen (dasselbe Feld wie DEPL), je Umgebung
  eine DEC; `phase: P15` ohne `umgebung:` ist kein
  Verzicht, der Lint meldet einen Hinweis. Bei den
  Doku-Gliedern P11, P12 und P15 ist der Verzicht die
  Zeile „gar nicht (Verzicht)“ der Doku-Abfrage; die
  übrigen Zeilen-DECs der Abfrage (jetzt, später,
  Übernahme) sind gewöhnliche Entscheidungen
  ([[Kettenpruefung]] §Kette, Doku-Abfrage —
  Gegenstand und Buchung). Der Eintrag
  nennt wie jeder DEC die Begründung und die
  verworfene Alternative (die Phase doch zu führen).
  Was der Verzicht in der Kette bewirkt und wann kein
  Verzicht nötig ist, weil ein Glied abgeleitet
  entfällt oder vertreten wird: [[Kettenpruefung]]
  §Fehlende und vertretene Glieder — lesen, bevor ein
  Verzicht gebucht wird. **Ohne Prozess** gibt es
  keine Phasen und darum keinen Verzicht mit
  `phase:`; der einzige Verzicht dort ist der auf
  „Tests grün“ als Vorbedingung des Tags
  ([[Kettenpruefung]] §Kette, Projekt ohne Prozess):
  eine DEC mit `decision_type: verzicht` und
  `zielmarke:` (die Marke), **ohne** `phase:`, deren
  Gegenstand „Tests grün“ nennt und deren Begründung
  die Folge benennt. Der Lint liest diesen Verzicht
  nicht und meldet ihn ohne Prozess nicht.
- **Kollisions-Grep vor Anlage** (Lese-Pflicht,
  MasterPrompt §9): vor
  jeder neuen Entscheidung das Register nach
  thematisch verwandten Einträgen durchsuchen; der
  neue Eintrag nennt die geprüften Nachbarn
  (Nachbarschafts-Zeile); widerspricht er einem
  bestehenden, wird erst geklärt (Ablösung oder
  Vermerk), dann gebucht — nie beides stehen
  lassen.
- **Detail-Knoten-Pflicht:** sobald verworfene
  Alternativen, Vermerke, Nachweise oder ausführliche
  Wahl-Begründung anfallen. Einzeilige Einträge
  brauchen keinen Detail-Knoten.
- **`decision`-Detail-Knoten-Konventionen:**
  Frontmatter-Felder (Pflicht und Kann, Werte von
  `decision_type`): [[Katalog-Entscheidungen]] — lesen
  beim Anlegen eines DEC-Knotens. Sektionen:
  Entscheidung (gewählte Option und Begründung) /
  Verworfene Alternativen / Nachweis (bei
  abgeschlossen mit Umsetzungsarbeit) / Verweise /
  Vermerke (append-only) / Bezüge (Fassaden-Eintrag
  [[Entscheidungen]]). Dateiname
  `DEC-NNN <Gegenstand>.md`.
- **Freeze und Ablösung:** Mechanismus und Ausnahmen
  führt [[Einfrieren-und-Abloesen]] (Abschnitte Freeze
  und Ausnahmen, Ablösung; dort auch die Form des
  sichtbaren Ablöse-Vermerks) — lesen vor einer
  Ablösung.
- **Vermerk-Praxis:** ein Vermerk **präzisiert** nur;
  Präzisierungs-Vermerke sind immer zulässig, vor und
  nach der Umsetzung, Korrektur-Vermerke gibt es
  nicht. Was eine Entscheidung **ändert**, wird eine
  neue DEC, die die alte vollständig ablöst
  (`supersedes:`); was sie **erweitert**, ohne dass sie
  sich danach anders liest, eine neue DEC mit
  `extends:` als Rückverweis. Das gilt unabhängig vom
  Umsetzungs-Status; der Bestand bleibt.
- **Eine Entscheidung je DEC:** ein DEC enthält genau
  eine Entscheidung; mehrere voneinander unabhängige
  Entscheidungen werden mehrere DECs — so bleibt die
  vollständige Ablösung billig, und ein Eintrag muss
  nie teilweise gelten.
- **Verworfene Alternativen als Pflichtteil:** jeder
  DEC außer einem Freigabe-Eintrag (dazu zählen auch
  die Ordner-Freigaben nach MasterPrompt §1.1, die der
  Prozess-Merge automatisch bucht) und einem
  Marken-Wechsel-Eintrag (oben) benennt die
  ernsthaft betrachteten Optionen und
  begründet je verworfener Option, warum sie es nicht
  geworden ist — sonst wird die verworfene Lösung
  später erneut vorgeschlagen.

---

**Bearbeitungsstand:** 2026-11-09 — Migration Phase 3:
DEC-001 bis DEC-012 als Bestand übernommen aus
`anforderungen.md`, `datenmodell-und-ablaeufe.md`,
`spezifikation.md`, `pom.xml`, `lager.sh`,
`CLAUDE-vorgefunden.md` und der Git-Historie bis Commit
`2eb6d29`; Reihenfolge nach Sache
(Rahmen, Bestand und Bewegungen, Technologie, Verfahren).
Bestand trägt weder Zielmarke noch Anlage-Datum; Status
`umgesetzt` nach Beleg in `pom.xml` und `spezifikation.md`
(beschreibt das Verhalten des Codes), Status-Datum ist der
Buchungstag. Was die Quellen an Begründung und verworfenen
Alternativen nicht nennen, steht als „nicht dokumentiert“.
DEC-013 bis DEC-017 sind neu (Ordner-Freigaben aus dem
Fragen-Block, mit Zielmarke und Anlage-Datum); DEC-018 ist
für die Gesamt-Annahme der Migrations-Grundlagen
reserviert (Detail-Knoten [[Migrations-Grundlagen]], die
Register-Zeile folgt nach Phase 4).

Eine chronologische Liste — keine Trennung offen /
getroffen, der Status trägt die Unterscheidung.

## Einträge

_(chronologisch nach ID)_

### DEC-001 — Einzelplatz-Anwendung: ein Benutzer an einem Rechner

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Die Lagerverwaltung ist eine
  Einzelplatz-Anwendung, ein Benutzer an einem Rechner.
  Begründung und verworfene Alternativen sind nicht
  dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A01 (§Rahmen),
  übernommen als REQ-001; [spezifikation.md](../spezifikation.md) §Prüfen
  (`DatenbankTest` prüft für A01 die lokale Datei)
- **Nachbarschaft:** DEC-002, DEC-003, DEC-008 — geprüft,
  kein Widerspruch

### DEC-002 — Bedienung im Terminal über Menüs

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Die Lagerverwaltung wird im
  Terminal über Menüs bedient (Hauptmenü und
  Stammdatenmenü). Begründung und verworfene Alternativen
  sind nicht dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A02 (§Rahmen),
  übernommen als REQ-002; [spezifikation.md](../spezifikation.md) §Hauptmenü,
  §Stammdatenmenü; Commit `3b6f6b4` („menügeführte
  Terminal-Anwendung“)
- **Nachbarschaft:** DEC-001, DEC-007 — geprüft, kein
  Widerspruch

### DEC-003 — Nur Mengen, keine Preise

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Die Lagerverwaltung verwaltet nur
  Mengen; Preise kommen nicht vor. Begründung und
  verworfene Alternativen sind nicht dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A03 (§Rahmen),
  übernommen als REQ-003; [spezifikation.md](../spezifikation.md) §Prüfen
  (`DatenbankTest` prüft, dass es keine Preise gibt);
  Commit `2eb6d29` („keine Preisspalten“)
- **Nachbarschaft:** DEC-001, DEC-004 — geprüft, kein
  Widerspruch

### DEC-004 — Bestand nur aus den Bewegungen berechnet

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Gespeichert werden Artikel,
  Lagerorte und Bewegungen; der Bestand wird nicht
  gespeichert, sondern aus den Bewegungen berechnet. Laut
  Quelle können so Bestand und Protokoll nie voneinander
  abweichen. Eine eigene Bestandstabelle als Alternative
  ist daraus abgeleitet; eine Abwägung ist nicht
  dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A05 (REQ-005);
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md) §Datenmodell, §Bestand;
  [spezifikation.md](../spezifikation.md) §Datenbankschema (View `bestand`);
  Commit `3b6f6b4`
- **Nachbarschaft:** DEC-005, DEC-006, DEC-008 — geprüft,
  kein Widerspruch
- **Detail:** [[DEC-004 Bestand nur aus den Bewegungen berechnet]]

### DEC-005 — Gebuchte Bewegungen unveränderlich, Fehler per Korrekturbuchung

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Gebuchte Bewegungen werden nie
  geändert oder gelöscht; Fehler werden mit einer
  Korrekturbuchung ausgeglichen. Die Datenbank erzwingt das
  mit den Triggern `bewegung_nicht_aendern` und
  `bewegung_nicht_loeschen`. Begründung und verworfene
  Alternativen sind nicht dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A06 (REQ-006),
  Korrektur A11 und A16 (REQ-011, REQ-016);
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md) §Datenmodell („unveränderlich“),
  §Korrektur / Inventur; [spezifikation.md](../spezifikation.md)
  §Datenbankschema; Commit `3b6f6b4` („Bewegungen per
  Trigger unveränderlich“)
- **Nachbarschaft:** DEC-004, DEC-006 — geprüft, kein
  Widerspruch

### DEC-006 — Stammdaten mit Bewegungen deaktivieren statt löschen

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Artikel und Lagerorte lassen sich
  löschen, solange es zu ihnen keine Bewegungen gibt; danach
  nur noch deaktivieren, und das nur ohne Bestand.
  Deaktivierte lassen sich nicht bebuchen, aber wieder
  aktivieren. Begründung und verworfene Alternativen sind
  nicht dokumentiert.
- **Verweise:** Herkunft [anforderungen.md](../anforderungen.md) A24 bis A26
  (REQ-024 bis REQ-026); [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Artikel oder Lagerort entfernen; [spezifikation.md](../spezifikation.md)
  §Stammdatenmenü (Befehle 3, 4, 7, 8)
- **Nachbarschaft:** DEC-004, DEC-005 — geprüft, kein
  Widerspruch

### DEC-007 — Java 21 als Sprache und Laufzeit

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Java 21; zum Bauen und Starten
  Java 21 oder neuer. Begründung und verworfene
  Alternativen sind nicht dokumentiert.
- **Verweise:** Herkunft [pom.xml](../pom.xml)
  (`maven.compiler.release`); [spezifikation.md](../spezifikation.md) §Technik
  und Start; [CLAUDE-vorgefunden.md](../CLAUDE-vorgefunden.md) (Kopf); Commit
  `3b6f6b4`
- **Nachbarschaft:** DEC-008 bis DEC-011 — geprüft, kein
  Widerspruch
- **Detail:** [[DEC-007 Java 21 als Sprache und Laufzeit]]

### DEC-008 — SQLite als Datenbank über sqlite-jdbc

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** SQLite in einer Datenbankdatei,
  angebunden über den Treiber `org.xerial:sqlite-jdbc`.
  Begründung und verworfene Alternativen sind nicht
  dokumentiert.
- **Verweise:** Herkunft [pom.xml](../pom.xml); [spezifikation.md](../spezifikation.md)
  §Technik und Start, §Datenbankschema;
  [CLAUDE-vorgefunden.md](../CLAUDE-vorgefunden.md) (Kopf); Commit `3b6f6b4`
- **Nachbarschaft:** DEC-001, DEC-004, DEC-007, DEC-010 —
  geprüft, kein Widerspruch
- **Detail:** [[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]]

### DEC-009 — Build mit Maven

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Maven baut, testet und packt das
  Programm; [lager.sh](../lager.sh) ruft Maven bei Bedarf auf.
  Begründung und verworfene Alternativen sind nicht
  dokumentiert.
- **Verweise:** Herkunft [pom.xml](../pom.xml); [lager.sh](../lager.sh);
  [spezifikation.md](../spezifikation.md) §Technik und Start
- **Nachbarschaft:** DEC-007, DEC-010, DEC-011, DEC-012 —
  geprüft, kein Widerspruch
- **Detail:** [[DEC-009 Build mit Maven]]

### DEC-010 — Eine ausführbare JAR-Datei mit SQLite-Treiber

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Maven packt Programm und
  SQLite-Treiber in eine einzige ausführbare Datei
  `target/lagerverwaltung.jar`. Begründung und verworfene
  Alternativen sind nicht dokumentiert.
- **Verweise:** Herkunft [pom.xml](../pom.xml) (`maven-shade-plugin`);
  [spezifikation.md](../spezifikation.md) §Technik und Start; [lager.sh](../lager.sh)
- **Nachbarschaft:** DEC-008, DEC-009 — geprüft, kein
  Widerspruch
- **Detail:** [[DEC-010 Eine ausfuehrbare JAR-Datei mit SQLite-Treiber]]

### DEC-011 — Tests mit JUnit Jupiter über Surefire

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Automatische Tests mit JUnit 6
  (`junit-jupiter`, nur für Tests), ausgeführt vom
  `maven-surefire-plugin`. Anlass laut Quelle: Jede
  Anforderung soll ihren Test finden. Begründung der Wahl
  und verworfene Alternativen sind nicht dokumentiert.
- **Verweise:** Herkunft Commit `2eb6d29`; [pom.xml](../pom.xml);
  [spezifikation.md](../spezifikation.md) §Prüfen
- **Nachbarschaft:** DEC-009, DEC-012 — geprüft, kein
  Widerspruch
- **Detail:** [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]

### DEC-012 — Tests laufen bei jedem Build mit, ein roter Test verhindert den Start

- **Status:** umgesetzt — **Datum:** 2026-11-09
- **Wahl und Begründung:** Die Tests laufen bei jedem
  `mvn package`, also auch in [lager.sh](../lager.sh); schlägt ein Test
  fehl, startet das Programm nicht. Laut Quelle „gewollt;
  wer trotzdem starten will, baut mit
  `mvn package -DskipTests`“. Verworfene Alternativen sind
  nicht dokumentiert.
- **Verweise:** Herkunft Commit `2eb6d29`; [pom.xml](../pom.xml)
  (Kommentar zum `maven-surefire-plugin`);
  [spezifikation.md](../spezifikation.md) §Prüfen
- **Nachbarschaft:** DEC-009, DEC-011 — geprüft, kein
  Widerspruch

### DEC-013 — Freigabe: Bestands-Ordner src/

- **Status:** angenommen — **Datum:** 2026-11-09
- **Angelegt:** 2026-11-09
- **Zielmarke:** Uebernahme-2026-11-09
- **Wahl und Begründung:** Der vorgefundene Ordner `src/` bleibt im
  Repo-Root und ist Code-Bereich: Quelltext unter
  `src/main/java/`, JUnit-Tests unter `src/test/java/`
  (Maven-Standardaufbau). Er ist zugleich der von SP-1
  mitgebrachte Code-Bereich `src/`; der Bestand gilt unter
  dieser Freigabe. Vorschlag im Fragen-Block
  der Migration (Phase 1 Nr. 7), vom Anwender am 2026-11-09
  bestätigt.
- **Verweise:** `_template/migration/MIGRATION.md` Phase 1
  Nr. 7 und Phase 3; SP-1-Steckbrief
  (`_template/referenzprozesse/SP-1-Standardprozess/index.md`)
  §4.4 Freigabe-Ausnahme für Code-Bereiche; MasterPrompt §1.1
- **Nachbarschaft:** DEC-009, DEC-014 bis DEC-017 — geprüft, kein Widerspruch

### DEC-014 — Freigabe: Ordner tests/

- **Status:** angenommen — **Datum:** 2026-11-09
- **Angelegt:** 2026-11-09
- **Zielmarke:** Uebernahme-2026-11-09
- **Wahl und Begründung:** Von SP-1 mitgebrachter Code-Bereich für Tests,
  freigegeben wie beim Init. Der Ordner existiert noch nicht;
  die vorhandenen Tests liegen nach Maven-Konvention unter
  `src/test/java/` (DEC-013). Vorschlag im Fragen-Block
  der Migration (Phase 1 Nr. 7), vom Anwender am 2026-11-09
  bestätigt.
- **Verweise:** `_template/migration/MIGRATION.md` Phase 1
  Nr. 7 und Phase 3; SP-1-Steckbrief
  (`_template/referenzprozesse/SP-1-Standardprozess/index.md`)
  §4.4 Freigabe-Ausnahme für Code-Bereiche; MasterPrompt §1.1
- **Nachbarschaft:** DEC-011, DEC-013 — geprüft, kein Widerspruch

### DEC-015 — Freigabe: Ordner ci/

- **Status:** angenommen — **Datum:** 2026-11-09
- **Angelegt:** 2026-11-09
- **Zielmarke:** Uebernahme-2026-11-09
- **Wahl und Begründung:** Von SP-1 mitgebrachter Code-Bereich für die
  Build- und Prüf-Automatisierung, freigegeben wie beim Init.
  Der Ordner existiert noch nicht. Vorschlag im Fragen-Block
  der Migration (Phase 1 Nr. 7), vom Anwender am 2026-11-09
  bestätigt.
- **Verweise:** `_template/migration/MIGRATION.md` Phase 1
  Nr. 7 und Phase 3; SP-1-Steckbrief
  (`_template/referenzprozesse/SP-1-Standardprozess/index.md`)
  §4.4 Freigabe-Ausnahme für Code-Bereiche; MasterPrompt §1.1
- **Nachbarschaft:** DEC-013, DEC-016 — geprüft, kein Widerspruch

### DEC-016 — Freigabe: Ordner deploy/

- **Status:** angenommen — **Datum:** 2026-11-09
- **Angelegt:** 2026-11-09
- **Zielmarke:** Uebernahme-2026-11-09
- **Wahl und Begründung:** Von SP-1 mitgebrachter Code-Bereich für
  Auslieferung und Betrieb, freigegeben wie beim Init. Der
  Ordner existiert noch nicht. Vorschlag im Fragen-Block
  der Migration (Phase 1 Nr. 7), vom Anwender am 2026-11-09
  bestätigt.
- **Verweise:** `_template/migration/MIGRATION.md` Phase 1
  Nr. 7 und Phase 3; SP-1-Steckbrief
  (`_template/referenzprozesse/SP-1-Standardprozess/index.md`)
  §4.4 Freigabe-Ausnahme für Code-Bereiche; MasterPrompt §1.1
- **Nachbarschaft:** DEC-013, DEC-015 — geprüft, kein Widerspruch

### DEC-017 — Freigabe: Ordner docs/

- **Status:** angenommen — **Datum:** 2026-11-09
- **Angelegt:** 2026-11-09
- **Zielmarke:** Uebernahme-2026-11-09
- **Wahl und Begründung:** Von SP-1 mitgebrachte Ableitungs-Schicht für
  nach außen gerichtete, selbsttragende Projekt-Doku, aus dem
  wiki/ abgeleitet und nie Wahrheits-Ort. Der Ordner existiert
  noch nicht; ein handgeschriebenes `docs/` gab es nicht. Vorschlag im Fragen-Block
  der Migration (Phase 1 Nr. 7), vom Anwender am 2026-11-09
  bestätigt.
- **Verweise:** `_template/migration/MIGRATION.md` Phase 1
  Nr. 7 und Phase 3; SP-1-Steckbrief
  (`_template/referenzprozesse/SP-1-Standardprozess/index.md`)
  §4.4 Freigabe-Ausnahme für Code-Bereiche; MasterPrompt §1.1
- **Nachbarschaft:** DEC-013 — geprüft, kein Widerspruch
