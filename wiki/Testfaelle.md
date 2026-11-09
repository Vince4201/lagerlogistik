---
type: test-case-log
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Testfälle

**Ich bin neu in SP-1.** Diese Fassade entsteht mit der
Wahl von SP-1 (Wiki.md §11 Prozess-Teil-Wikis) — das
Register der Testfälle speist den Testplan der Phase
P10 „Testen“.

## Meta

- **Typ:** `test-case-log` — Register aller Testfälle
  (TEST) dieses Repos. Singleton, ID-Präfix
  `TEST-NNN`.
- **Kategorie:** (a) Fassade + Detail-Knoten im wiki/ —
  siehe Wiki.md §Kategorisierung der Register.
- **Gegenstand:** System- und Anwendertests mit
  Ablauf und Erwartung; jeder Testfall nennt per
  `prueft:`, welche Anforderungen,
  Funktionsbeschreibungen oder Spezifikationen er
  prüft (Anwendertests dürfen auf ein Szenario
  zeigen). **Pflicht nur für System- und
  Anwendertests;** Unit- und Integrationstests bleiben
  Code. Integrationstests dürfen als Testfall geführt
  werden, wenn eine Projekt-Regel in
  [[InterneMechanik]] das festlegt.
- **Abgrenzung:** das **Test-Setup** (wie geprüft wird:
  Testarten, Umgebung, Ausführung) steht lebend in
  [[Systemtest-Setup]]; der **Testplan** ist kein
  Wiki-Knoten, sondern der Stand über die Testfälle
  einer Marke ([[Katalog-Testplan]]); das Ergebnis
  eines Durchgangs steht in [[Testberichte]].
- **Eintritt und Lese-Klammer:** Testfälle dürfen
  jederzeit entstehen, auch vor dem Tag der
  Implementierung; der Agent legt sie als `offen` an,
  sie bleiben bearbeitbar, bis sie einzeln angenommen
  werden oder mit der Freigabe des Testplan-Stands,
  der sie verweist, und sind ab dann eingefroren
  ([[Einfrieren-und-Abloesen]] §Eintrittsregel). Bis
  zur Bildung des Testplans ist diese Fassade die
  Lese-Klammer der Phase P10
  ([[Lebenslauf-eines-Stands]]). Für einen
  neuen Testplan wird ein Testfall abgelöst,
  weggelassen oder ergänzt.
- **Kanonizität:** die Wahrheit beim Status liegt in
  dieser Fassade — Detail-Knoten (Typ `test-case`)
  spiegeln (Wiki.md §1.1a, [[Register-Grundmuster]]).
- **Eintragsformat:** `### TEST-NNN — <Kurzsatz>` mit
  Feldern Testart / Prüft (IDs) / Status +
  status_date / Angelegt (Anlage-Datum `JJJJ-MM-TT`,
  Pflichtfeld seit 2.0 am Eintrag ohne Detail-Knoten,
  sonst `datum:` im Detail-Knoten; [[Katalog]]
  §Typ-Konventionen) / Zielmarke ([[Zielmarken]];
  Pflichtfeld, kanonisch hier) /
  Detail (Wikilink, nur wenn Detail-Knoten existiert).
- **`test-case`-Detail-Knoten-Konventionen:**
  Frontmatter-Felder: [[Katalog-Testfaelle]] — lesen
  beim Anlegen eines `test-case`-Knotens. Sektionen:
  Vorbedingung /
  Ablauf / Erwartung / Vermerke (append-only) /
  Bezüge (Fassaden-Eintrag [[Testfaelle]]). Dateiname
  `TEST-NNN <Kurzsatz>.md`.

---

**Bearbeitungsstand:** 2026-11-09 — 42 Testfälle
(TEST-001 bis TEST-042) bei der Migration aus den
JUnit-Tests unter `src/test/java/` übernommen
(Übernahme-Marke `Uebernahme-2026-11-09`); Bestand ohne
Zielmarke und ohne Anlage-Datum, alle `offen`, Testart
`system`.

## Testfälle

Je JUnit-Testmethode ein Testfall mit Detail-Knoten
(Vorbedingung, Ablauf, Erwartung). Reihenfolge nach
Testklasse wie in `spezifikation.md` §Prüfen, darin nach
Quelltext. `Prüft` folgt der Anforderungsnummer im
`@DisplayName` des Tests (A01 → REQ-001 … A33 →
REQ-033). Testbasis, Testklassen und Ausführung:
[[Softwaresystemtest-Setup]].

### TEST-001 — Alle Daten liegen in einer einzigen lokalen Datei, ein Server ist nicht nötig

- **Testart:** system
- **Prüft:** REQ-001
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/db/DatenbankTest.java`, `datenLiegenInEinerLokalenDatei()` (A01)
- **Detail:** [[TEST-001 Alle Daten liegen in einer einzigen lokalen Datei ein Server ist nicht noetig]]

### TEST-002 — Keine Tabelle hat eine Spalte für Preise oder Werte

- **Testart:** system
- **Prüft:** REQ-003
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/db/DatenbankTest.java`, `keineSpaltenFuerPreise()` (A03)
- **Detail:** [[TEST-002 Keine Tabelle hat eine Spalte fuer Preise oder Werte]]

### TEST-003 — Der Bestand ist keine gespeicherte Tabelle, sondern eine View über den Bewegungen

- **Testart:** system
- **Prüft:** REQ-005
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/db/DatenbankTest.java`, `bestandIstEineView()` (A05)
- **Detail:** [[TEST-003 Der Bestand ist keine gespeicherte Tabelle sondern eine View ueber den Bewegungen]]

### TEST-004 — Die Datenbank lehnt das Ändern und Löschen gebuchter Bewegungen ab

- **Testart:** system
- **Prüft:** REQ-006
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/db/DatenbankTest.java`, `bewegungenSindUnveraenderlich()` (A06)
- **Detail:** [[TEST-004 Die Datenbank lehnt das Aendern und Loeschen gebuchter Bewegungen ab]]

### TEST-005 — Ein Artikel liegt an mehreren Lagerorten, ein Lagerort enthält mehrere Artikel

- **Testart:** system
- **Prüft:** REQ-004
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `artikelUndLagerorteSindNzuM()` (A04)
- **Detail:** [[TEST-005 Ein Artikel liegt an mehreren Lagerorten ein Lagerort enthaelt mehrere Artikel]]

### TEST-006 — Jede Bestandsänderung steht im Protokoll, der Bestand ergibt sich daraus

- **Testart:** system
- **Prüft:** REQ-005
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `bestandErgibtSichAusDenBewegungen()` (A05)
- **Detail:** [[TEST-006 Jede Bestandsaenderung steht im Protokoll der Bestand ergibt sich daraus]]

### TEST-007 — Ein Fehler wird mit einer Korrektur ausgeglichen, die falsche Bewegung bleibt stehen

- **Testart:** system
- **Prüft:** REQ-006
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `fehlerWerdenPerKorrekturAusgeglichen()` (A06)
- **Detail:** [[TEST-007 Ein Fehler wird mit einer Korrektur ausgeglichen die falsche Bewegung bleibt stehen]]

### TEST-008 — Die Einheit ist nur ein Anzeigetext und ändert keine Mengen

- **Testart:** system
- **Prüft:** REQ-007
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `einheitIstNurAnzeigetext()` (A07)
- **Detail:** [[TEST-008 Die Einheit ist nur ein Anzeigetext und aendert keine Mengen]]

### TEST-009 — Ein Wareneingang erhöht den Bestand am Ziel-Lagerort

- **Testart:** system
- **Prüft:** REQ-008
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `wareneingangErhoehtBestandAmZiel()` (A08)
- **Detail:** [[TEST-009 Ein Wareneingang erhoeht den Bestand am Ziel-Lagerort]]

### TEST-010 — Ein Warenausgang senkt den Bestand am Quell-Lagerort

- **Testart:** system
- **Prüft:** REQ-009
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `warenausgangSenktBestandAnDerQuelle()` (A09)
- **Detail:** [[TEST-010 Ein Warenausgang senkt den Bestand am Quell-Lagerort]]

### TEST-011 — Eine Umlagerung verschiebt die Menge, der Gesamtbestand bleibt gleich

- **Testart:** system
- **Prüft:** REQ-010
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `umlagerungAendertGesamtbestandNicht()` (A10)
- **Detail:** [[TEST-011 Eine Umlagerung verschiebt die Menge der Gesamtbestand bleibt gleich]]

### TEST-012 — Eine Korrektur übernimmt den gezählten Bestand und bucht die Differenz

- **Testart:** system
- **Prüft:** REQ-011
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `korrekturBuchtDieDifferenz()` (A11)
- **Detail:** [[TEST-012 Eine Korrektur uebernimmt den gezaehlten Bestand und bucht die Differenz]]

### TEST-013 — Eine Menge von 0 oder weniger wird abgelehnt

- **Testart:** system
- **Prüft:** REQ-012
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `mengeMussGroesserNullSein()` (A12)
- **Detail:** [[TEST-013 Eine Menge von 0 oder weniger wird abgelehnt]]

### TEST-014 — Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen

- **Testart:** system
- **Prüft:** REQ-013
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `nurVorhandeneUndAktiveWerdenBebucht()` (A13)
- **Detail:** [[TEST-014 Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen]]

### TEST-015 — Entnommen wird höchstens, was am Lagerort liegt, kein Lagerort wird negativ

- **Testart:** system
- **Prüft:** REQ-014
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `keinLagerortWirdNegativ()` (A14)
- **Detail:** [[TEST-015 Entnommen wird hoechstens was am Lagerort liegt kein Lagerort wird negativ]]

### TEST-016 — Bei einer Umlagerung müssen Quelle und Ziel verschieden sein

- **Testart:** system
- **Prüft:** REQ-015
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `umlagerungBrauchtVerschiedeneLagerorte()` (A15)
- **Detail:** [[TEST-016 Bei einer Umlagerung muessen Quelle und Ziel verschieden sein]]

### TEST-017 — Eine Korrektur ohne Begründung wird abgelehnt

- **Testart:** system
- **Prüft:** REQ-016
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/BuchungenTest.java`, `korrekturBrauchtBegruendung()` (A16)
- **Detail:** [[TEST-017 Eine Korrektur ohne Begruendung wird abgelehnt]]

### TEST-018 — Der Mindestbestand gilt für den Gesamtbestand über alle Lagerorte

- **Testart:** system
- **Prüft:** REQ-017
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/MindestbestandTest.java`, `mindestbestandGiltFuerDenGesamtbestand()` (A17)
- **Detail:** [[TEST-018 Der Mindestbestand gilt fuer den Gesamtbestand ueber alle Lagerorte]]

### TEST-019 — Mindestbestand 0 heißt: keine Überwachung

- **Testart:** system
- **Prüft:** REQ-017
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/MindestbestandTest.java`, `mindestbestandNullWirdNichtUeberwacht()` (A17)
- **Detail:** [[TEST-019 Mindestbestand 0 heisst keine Ueberwachung]]

### TEST-020 — Nach jeder Buchung, die den Gesamtbestand unter den Mindestbestand senkt, wird gewarnt

- **Testart:** system
- **Prüft:** REQ-018
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/MindestbestandTest.java`, `warnungBeiJederSenkendenBuchung()` (A18)
- **Detail:** [[TEST-020 Nach jeder Buchung die den Gesamtbestand unter den Mindestbestand senkt wird gewarnt]]

### TEST-021 — Buchungen, die den Gesamtbestand erhöhen, warnen nicht

- **Testart:** system
- **Prüft:** REQ-018
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/MindestbestandTest.java`, `keineWarnungBeiSteigendemBestand()` (A18)
- **Detail:** [[TEST-021 Buchungen die den Gesamtbestand erhoehen warnen nicht]]

### TEST-022 — Eine Umlagerung warnt nie, auch unter dem Mindestbestand

- **Testart:** system
- **Prüft:** REQ-019
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/MindestbestandTest.java`, `umlagerungWarntNie()` (A19)
- **Detail:** [[TEST-022 Eine Umlagerung warnt nie auch unter dem Mindestbestand]]

### TEST-023 — Ein Artikel hat Nummer, Bezeichnung, Einheit und Mindestbestand

- **Testart:** system
- **Prüft:** REQ-021
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `artikelHatSeineEigenschaften()` (A21)
- **Detail:** [[TEST-023 Ein Artikel hat Nummer Bezeichnung Einheit und Mindestbestand]]

### TEST-024 — Ein Lagerort hat einen Code und eine optionale Bezeichnung

- **Testart:** system
- **Prüft:** REQ-022
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `lagerortHatCodeUndOptionaleBezeichnung()` (A22)
- **Detail:** [[TEST-024 Ein Lagerort hat einen Code und eine optionale Bezeichnung]]

### TEST-025 — Nummern und Codes sind eindeutig

- **Testart:** system
- **Prüft:** REQ-023
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `nummernUndCodesSindEindeutig()` (A23)
- **Detail:** [[TEST-025 Nummern und Codes sind eindeutig]]

### TEST-026 — Beim Bearbeiten bleibt die Nummer unverändert

- **Testart:** system
- **Prüft:** REQ-023
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `nummerBleibtBeimBearbeiten()` (A23)
- **Detail:** [[TEST-026 Beim Bearbeiten bleibt die Nummer unveraendert]]

### TEST-027 — Ohne Bewegungen werden Artikel und Lagerorte gelöscht

- **Testart:** system
- **Prüft:** REQ-024
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `ohneBewegungenWirdGeloescht()` (A24)
- **Detail:** [[TEST-027 Ohne Bewegungen werden Artikel und Lagerorte geloescht]]

### TEST-028 — Mit Bewegungen wird nur deaktiviert, und das nur ohne Bestand

- **Testart:** system
- **Prüft:** REQ-025
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `mitBewegungenWirdNurDeaktiviert()` (A25)
- **Detail:** [[TEST-028 Mit Bewegungen wird nur deaktiviert und das nur ohne Bestand]]

### TEST-029 — Deaktivierte lassen sich nicht bebuchen, nach dem Reaktivieren wieder

- **Testart:** system
- **Prüft:** REQ-026
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/StammdatenTest.java`, `deaktivierteLassenSichReaktivieren()` (A26)
- **Detail:** [[TEST-029 Deaktivierte lassen sich nicht bebuchen nach dem Reaktivieren wieder]]

### TEST-030 — Je Artikel: Gesamtbestand und Verteilung auf die Lagerorte

- **Testart:** system
- **Prüft:** REQ-027
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/AuswertungenTest.java`, `bestandJeArtikel()` (A27)
- **Detail:** [[TEST-030 Je Artikel Gesamtbestand und Verteilung auf die Lagerorte]]

### TEST-031 — Je Lagerort: welche Artikel in welcher Menge dort liegen, ohne Bestände von 0

- **Testart:** system
- **Prüft:** REQ-028
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/AuswertungenTest.java`, `bestandJeLagerort()` (A28)
- **Detail:** [[TEST-031 Je Lagerort welche Artikel in welcher Menge dort liegen ohne Bestaende von 0]]

### TEST-032 — Liste der Artikel unter dem Mindestbestand mit Fehlmenge

- **Testart:** system
- **Prüft:** REQ-029
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/AuswertungenTest.java`, `listeUnterMindestbestand()` (A29)
- **Detail:** [[TEST-032 Liste der Artikel unter dem Mindestbestand mit Fehlmenge]]

### TEST-033 — Bewegungsprotokoll in Buchungsreihenfolge, filterbar nach Artikel und Lagerort

- **Testart:** system
- **Prüft:** REQ-030
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/service/AuswertungenTest.java`, `bewegungsprotokoll()` (A30)
- **Detail:** [[TEST-033 Bewegungsprotokoll in Buchungsreihenfolge filterbar nach Artikel und Lagerort]]

### TEST-034 — Bedienung über ein Menü im Terminal

- **Testart:** system
- **Prüft:** REQ-002
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `hauptmenue()` (A02)
- **Detail:** [[TEST-034 Bedienung ueber ein Menue im Terminal]]

### TEST-035 — Mengen sind ganze Zahlen, eine Kommazahl wird erneut abgefragt

- **Testart:** system
- **Prüft:** REQ-007
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `mengeIstEineGanzeZahl()` (A07)
- **Detail:** [[TEST-035 Mengen sind ganze Zahlen eine Kommazahl wird erneut abgefragt]]

### TEST-036 — Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung

- **Testart:** system
- **Prüft:** REQ-018
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `warnungNachWarenausgang()` (A18)
- **Detail:** [[TEST-036 Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung]]

### TEST-037 — Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand

- **Testart:** system
- **Prüft:** REQ-020
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `hinweisBeimStart()` (A20)
- **Detail:** [[TEST-037 Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand]]

### TEST-038 — Die Einheit eines neuen Artikels ist ohne Eingabe Stk

- **Testart:** system
- **Prüft:** REQ-021
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `einheitStandardStk()` (A21)
- **Detail:** [[TEST-038 Die Einheit eines neuen Artikels ist ohne Eingabe Stk]]

### TEST-039 — Nummern und Codes werden in Großbuchstaben gespeichert

- **Testart:** system
- **Prüft:** REQ-023
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `grossbuchstaben()` (A23)
- **Detail:** [[TEST-039 Nummern und Codes werden in Grossbuchstaben gespeichert]]

### TEST-040 — Falsche Eingaben werden erneut abgefragt

- **Testart:** system
- **Prüft:** REQ-031
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `falscheEingabenWerdenErneutAbgefragt()` (A31)
- **Detail:** [[TEST-040 Falsche Eingaben werden erneut abgefragt]]

### TEST-041 — Eine leere Eingabe bricht den laufenden Vorgang ab

- **Testart:** system
- **Prüft:** REQ-032
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `leereEingabeBrichtAb()` (A32)
- **Detail:** [[TEST-041 Eine leere Eingabe bricht den laufenden Vorgang ab]]

### TEST-042 — Mit ? erscheint eine Auswahlliste

- **Testart:** system
- **Prüft:** REQ-033
- **Status:** offen — **Datum:** 2026-11-09
- **Quelle:** `src/test/java/lager/ui/LagerKonsoleTest.java`, `fragezeichenZeigtAuswahlliste()` (A33)
- **Detail:** [[TEST-042 Mit Fragezeichen erscheint eine Auswahlliste]]
