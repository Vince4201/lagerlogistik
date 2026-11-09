---
type: software-system-test-setup
replaces_type: system-test-setup
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Softwaresystemtest-Setup

**Ich löse [[Systemtest-Setup]] aus SP-1 ab und verändere
die Semantik wie folgt:** software-spezifische Sektionen
(Unit-Tests, Integrationstests, docker-compose.yml,
Umgebungsvariablen, Ausführung).

## Meta

- **Typ:** `software-system-test-setup` —
  Sollzustands-Fassade Softwaresystemtest-Setup.
  Singleton; löst `system-test-setup` aus SP-1 ab
  (`replaces_type:` als Typ-Name, String-Notation;
  Katalog-Eintrag [[Katalog-Softwaresystemtest-Setup]]).
- **Kategorie:** (b) Inline-Fassade im wiki/ — siehe
  Wiki.md §Kategorisierung der Register.
- **Kanonizität:** die Wahrheit bei allen hier
  festgehaltenen Setup-Angaben liegt in dieser Fassade
  (Wiki.md §1.1a).
- **Geheimnisse-Regel:** Werte von Zugangsdaten stehen
  nie in dieser Fassade — nur Variablennamen und
  Erklärung, wie sie zu setzen sind
  (MasterPrompt §6.3).
- **Abgrenzung:** diese Fassade beschreibt, **wie**
  geprüft wird. Die einzelnen Testfälle stehen als
  TEST-Elemente im SP-1-Register [[Testfaelle]], die
  Ergebnisse eines Durchgangs nach Testplan im
  SP-1-Register [[Testberichte]].
- **Ausnahmen sind Feststellungen:** braucht ein
  Projekt keine externen Dienste, wird das ausdrücklich
  festgehalten — das Fehlen der `docker-compose.yml`
  ist dann eine bewusste Feststellung, kein Versäumnis.
- **Betrieb docker-compose:** der Agent stellt die
  `docker-compose.yml` bereit, deployt sie aber nicht;
  der User deployt und meldet Host, Ports und
  Zugangsdaten zurück.
- **Testplan ist ein Stand, kein Detail-Knoten:**
  diese Fassade hält die Solllage (Testarten,
  Testumgebung, Ausführung, Ausnahmen) und bleibt
  lebend; der Testplan zu einer Marke ist ein Stand
  mit der Dokumenttyp-Kennung `testplan`, abgeleitet
  aus den Testfällen und mit Kopie dieser Solllage
  ([[Katalog-Testplan]]; Mechanik:
  [[Einfrieren-und-Abloesen]]). Für Ausführende ohne
  Wiki-Zugang gibt es je Testdurchgang die
  Durchführungs-Instanz
  ([[Katalog-Durchfuehrungs-Instanz]]).
- **Testergebnis-Regel:** die dreistufige
  Nachweis-Regel (flüchtiger Lauf, beleg-würdiger Lauf
  mit TRES oder REP, nicht reproduzierbarer Lauf)
  gilt unverändert aus SP-1 — führender Ort `## Meta`
  der SP-1-Fassade [[Systemtest-Setup]]; lesen, bevor
  ein Testlauf belegt wird.
- **Commit-Präfix:** Änderungen laufen unter dem
  SP-1-Präfix `Test-Setup:` weiter (kein eigener
  RP-1-Präfix).

---

**Bearbeitungsstand:** 2026-11-09 — bei der Migration
aus dem Vorgefundenen übernommen (Übernahme-Marke
`Uebernahme-2026-11-09`); die Quelle steht je Abschnitt.
Bei Änderungen überschrieben.

Beschreibt, wie geprüft wird: mit den automatischen Tests
(JUnit) und mit dem Prüflauf mit Beispieldaten.

## Unit-Tests

Die automatischen Tests sind JUnit-Tests (JUnit 6) unter
`src/test/java/`: 6 Testklassen mit zusammen 42 Tests und
eine gemeinsame Basisklasse. Jeder Test beginnt im Namen
(`@DisplayName`) mit der Nummer der Anforderung, die er
prüft (A01 bis A33, im Wiki REQ-001 bis REQ-033); die
Tests decken A01 bis A33 ab, jede mit mindestens einem
Test. Neue Tests beginnen mit der REQ-Nummer
(`REQ-034 …`), die bestehenden behalten `A..`. Jeder Test
arbeitet auf einer eigenen, frischen Datenbankdatei.

**Feststellung:** Isolierte Unit-Tests ohne Datenbank gibt
es nicht. Alle Testklassen erben von der Testbasis und
arbeiten auf einer echten SQLite-Datei, und jeder Test
prüft eine Anforderung. Nach der Projekt-Regel
[[Testname-mit-Anforderungsnummer]] steht jeder Test als
Testfall im Register [[Testfaelle]]: die 33 Tests der
Datenbank- und Serviceklassen als `integration`
(TEST-001 bis TEST-033), die 9 Tests über die Konsole als
`system` (TEST-034 bis TEST-042).

| Testklasse | Verantwortungsbereich | Anforderungen | Tests | Testfälle |
|---|---|---|---|---|
| `lager.db.DatenbankTest` | Schema: lokale Datei, keine Preise, View `bestand`, Trigger | A01, A03, A05, A06 | 4 | TEST-001 bis TEST-004 |
| `lager.service.BuchungenTest` | Lager und Bestand, die vier Buchungsarten, harte Regeln | A04 bis A16 | 13 | TEST-005 bis TEST-017 |
| `lager.service.MindestbestandTest` | weiche Regel Mindestbestand | A17 bis A19 | 5 | TEST-018 bis TEST-022 |
| `lager.service.StammdatenTest` | Anlegen, Bearbeiten, Löschen, Deaktivieren, Reaktivieren | A21 bis A26 | 7 | TEST-023 bis TEST-029 |
| `lager.service.AuswertungenTest` | die vier Auswertungen | A27 bis A30 | 4 | TEST-030 bis TEST-033 |
| `lager.ui.LagerKonsoleTest` | Bedienung mit eingespielten Eingaben | A02, A07, A18, A20, A21, A23, A31 bis A33 | 9 | TEST-034 bis TEST-042 |

### Testbasis

Alle Testklassen erben von `lager.LagerTestBasis`. Vor
jedem Test legt sie in einem temporären Ordner
(`@TempDir`) eine eigene, leere Datenbankdatei `test.db`
an und darin einen kleinen Satz Stammdaten; nach jedem
Test schließt sie die Datenbank.

| Stammdaten | Werte |
|---|---|
| Lagerorte | A-01 „Halle A, Regal 1“, B-01 „Halle B, Regal 1“, C-01 ohne Bezeichnung |
| Artikel | 1001 Schraube M6, Einheit Stk, Mindestbestand 10; 1002 Mutter M6, Einheit Stk, Mindestbestand 0 (keine Überwachung) |

Zusätzlich je Testklasse: `AuswertungenTest` bucht vor
jedem Test dieselben vier Bewegungen (Eingang 8 × 1001
nach A-01, Eingang 4 × 1002 nach A-01, Umlagerung
3 × 1001 von A-01 nach B-01, Ausgang 4 × 1002 von A-01).
`LagerKonsoleTest` startet die Konsole mit Eingaben, die
zeilenweise eingespielt werden wie am Terminal (eine leere
Zeile ist ein Enter), und prüft die Ausgabe.

_Quelle:_ `spezifikation.md` §Prüfen (Testklassen-Tabelle);
Testquellen unter `src/test/java/` (Basisklasse
`lager/LagerTestBasis.java`, Zählung der Tests).

## Integrationstests

**Feststellung:** Integrationstests sind die 33 Tests der
Klassen `DatenbankTest`, `BuchungenTest`,
`MindestbestandTest`, `StammdatenTest` und
`AuswertungenTest` (TEST-001 bis TEST-033): Sie prüfen
`LagerService` zusammen mit einer echten SQLite-Datei,
`DatenbankTest` das Schema selbst. Eine eigene Stufe mit
externen Diensten gibt es nicht und braucht es nicht: Die
Datenbank ist eine lokale Datei, ein Server ist nicht
nötig (TEST-001).

_Quelle:_ Testquellen unter `src/test/java/`
(`LagerTestBasis`, `DatenbankTest`).

## docker-compose.yml

**Feststellung:** Es gibt keine `docker-compose.yml`, und
das ist bewusst: die Prüfung braucht keine externen
Dienste (siehe Integrationstests).

## Umgebungsvariablen

Keine bekannt: Weder die Testquellen noch
`spezifikation.md` §Prüfen nennen Umgebungsvariablen. Die
automatischen Tests legen ihre Datenbankdatei selbst in
einem temporären Ordner an; Zugangsdaten gibt es nicht
(lokale Datei, kein Server). Der Prüflauf bekommt den
Namen der Datenbankdatei als Argument (`test.db`).

## Ausführung

### Automatische Tests

```bash
mvn -q -B test        # nur die Tests
./lager.sh            # baut mit Tests; schlägt ein Test fehl, startet das Programm nicht
```

- **Werkzeuge:** Maven mit JUnit 6 als Test-Abhängigkeit
  `org.junit.jupiter:junit-jupiter` 6.1.3 (Scope `test`,
  kommt nicht in die JAR-Datei). Ausgeführt werden die
  Tests vom `maven-surefire-plugin` 3.6.1; es läuft bei
  jedem `mvn package`, also auch in `lager.sh`. Festlegungen:
  DEC-011 (JUnit über Surefire) und DEC-012 (Tests bei jedem Build)
  in [[Entscheidungen]].
- **JVM-Argument:** Surefire startet die Tests mit
  `--enable-native-access=ALL-UNNAMED`, weil der
  SQLite-Treiber eine native Bibliothek lädt (ab Java 24
  sonst mit Warnung).
- **Erwartet:** alle Tests grün.

_Quelle:_ `spezifikation.md` §Prüfen (Befehle); `pom.xml`
(`junit-jupiter`, `maven-surefire-plugin` samt
Kommentaren); Prüfkriterium „alle Tests grün“ aus
`CLAUDE-vorgefunden.md` (Abschnitt Commits).

### Prüflauf mit Beispieldaten

`beispieldaten.txt` enthält die Eingaben für einen
kompletten Durchlauf durch die Menüs: im Stammdatenmenü
6 Lagerorte (A-01-01, A-01-02, A-02-01, B-01-01, B-01-02,
C-01-01) und 10 Artikel, danach im Hauptmenü
10 Wareneingänge, 2 Umlagerungen, 9 Warenausgänge und
eine Inventurkorrektur, zuletzt Beenden.

```bash
rm -f test.db && ./lager.sh test.db < beispieldaten.txt
sqlite3 test.db "SELECT * FROM bestand"
```

- **Erwartet:** Der Lauf geht ohne Fehler durch. Die
  zweite Zeile zeigt danach den Bestand (View `bestand`):
  12 Posten mit der Summe 1907, zu prüfen mit
  `sqlite3 test.db "SELECT COUNT(*), SUM(menge) FROM bestand"`
  → `12|1907`. Referenz aus der Nachricht von Commit
  `2eb6d29`, als Nachweis abgelegt in
  [[REC-001 — Pruefergebnisse zum Stand von Commit 2eb6d29]];
  am 2026-11-09 mit der vorhandenen JAR nachgeprüft (Code
  unverändert seit `2eb6d29`).

_Quelle:_ `spezifikation.md` §Prüfen; Inhalt ausgezählt
aus `beispieldaten.txt`; Prüfkriterium „läuft ohne Fehler
durch“ aus `CLAUDE-vorgefunden.md` (Abschnitt Commits);
Soll-Ergebnis aus Commit `2eb6d29`, nachgetragen am
2026-11-09 nach Prüfbericht REP-001 (Befund 1).
