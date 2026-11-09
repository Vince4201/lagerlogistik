---
type: facts
active: true   # kann nicht deaktiviert werden — Facts ist Pflicht-Fassade (mandatory)
---

# Facts

## Meta

- **Typ:** `facts` — Projekteinstieg, projektweite
  Fakten, Stolpersteine. Singleton, Pflicht-Fassade.
- **Kategorie:** (b) Inline-Fassade im wiki/ — siehe
  Wiki.md §Kategorisierung der Register.
- **Anwender-Terrain:** Facts gehört dem Anwender. Der
  Agent editiert hier nur auf ausdrückliche Anweisung —
  keine automatische Pflege beim Ingest, keine
  Agent-gepflegte Konfiguration. Der Fassaden-Status
  lebt in [[Fassaden]]; Betriebs-Konfiguration
  (aktivierter Prozess, WorkspaceManager, Commit-Sprache)
  lebt in `internal-rule`-Knoten unter
  [[InterneMechanik]].
- **Lese-Priorität:** Facts hat beim Sessionstart hohe
  Lese-Priorität. Stolpersteine überstimmen
  Detail-Knoten bei Widersprüchen — der Agent klärt die
  Diskrepanz zeitnah und pflegt den Detail-Knoten nach
  (oder aktualisiert den Stolperstein, wenn er veraltet
  ist).
- **Bearbeitungsstand-Zeile:** _(vom Anwender oder auf
  Anweisung gepflegt)_.

---

**Bearbeitungsstand:** 2026-11-09 — §1 in der Migration aus den vorgefundenen Dateien gefüllt (Bootstrap Facts); §2 und §3 noch leer.

## 1. Projekteinstieg

Terminal-Lagerverwaltung für einen Benutzer an einem Rechner, in
Java 21 mit SQLite. Sie bucht Wareneingang, Warenausgang, Umlagerung
und Korrektur/Inventur als unveränderliche Bewegungen und berechnet
den Bestand daraus; dazu Stammdaten (Artikel, Lagerorte), eine
Warnung beim Unterschreiten des Mindestbestands und vier
Auswertungen. Bedienung über Menüs im Terminal, Start mit
`./lager.sh`.

Der Stand wurde am 2026-11-09 aus der Dokumentenkette
(`anforderungen.md`, `datenmodell-und-ablaeufe.md`,
`spezifikation.md`), dem Quelltext und den JUnit-Tests ins wiki/
übernommen; erste Marke ist `Uebernahme-2026-11-09`
([[Zielmarken]]). Einstieg: [[Mission Statement]],
[[Anforderungen]], [[Funktionsbeschreibungen]],
[[Softwaresystemarchitektur]], [[Testfaelle]].

## 2. Allgemeine Fakten

_(TLA-Zuordnungen, projektweiter Rahmen, sonstige
projektweite Fakten; die Tag-Konvention des Projekts —
Muster des Tag-Namens, `Vorgehen.md` §4 „Freigabe per
Tag“ — und daneben die Pfade des Produkts, auf die
sich „unverändert“ bei der Übernahme eines Tags
bezieht (im Zwei-Repo-Fall das Produkt-Repo;
[[Kettenpruefung]] §Kette, Übernahme des Tags und der
Lieferung), beides abgefragt vor dem ersten Tag; dazu
die Zweig-Konvention für Zwischenmarken — Muster des
Zweignamens im Repo, das die Pfade des Produkts trägt,
z.B. `marke/2.0.1`, abgezweigt am Tag der Vormarke
([[Zielmarken]] §Meta „Zwischenmarke“, Tag und
Produkt-Repo) —, abgefragt vor der ersten
Zwischenmarke.)_

## 3. Stolpersteine

_(Hinweise auf wiederkehrende KI-Fehler oder subtile
Sachverhalte, die permanent sichtbar bleiben sollen.)_

_(vom User oder auf User-Anweisung gefüllt)_
