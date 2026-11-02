package lager.ui;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lager.db.DatenbankException;
import lager.model.Artikel;
import lager.model.Artikelbestand;
import lager.model.Bestandsposten;
import lager.model.Bewegung;
import lager.model.Lagerort;
import lager.service.Buchungsergebnis;
import lager.service.Entfernen;
import lager.service.LagerException;
import lager.service.LagerService;

/** Menüführung im Terminal: Buchungen, Auswertungen und Stammdatenpflege. */
public final class LagerKonsole {

    private static final String HAUPTMENUE = """

            === LAGERVERWALTUNG ===
              1  Wareneingang
              2  Warenausgang
              3  Umlagerung
              4  Korrektur / Inventur
              5  Bestand je Artikel
              6  Bestand je Lagerort
              7  Artikel unter Mindestbestand
              8  Bewegungsprotokoll
              9  Stammdaten
              0  Beenden""";

    private static final String STAMMDATENMENUE = """

            --- Stammdaten ---
              1  Artikel anzeigen
              2  Artikel anlegen
              3  Artikel bearbeiten / reaktivieren
              4  Artikel löschen / deaktivieren
              5  Lagerorte anzeigen
              6  Lagerort anlegen
              7  Lagerort bearbeiten / reaktivieren
              8  Lagerort löschen / deaktivieren
              0  Zurück""";

    private static final long MAX_MENGE = 1_000_000_000L;
    private static final int MAX_CODE = 20;
    private static final int MAX_BEZEICHNUNG = 60;
    private static final int MAX_EINHEIT = 10;
    private static final int MAX_BEMERKUNG = 100;
    /** Erlaubte Zeichen für Artikelnummern und Lagerort-Codes. */
    private static final Pattern CODE = Pattern.compile("[\\p{L}\\p{N}._/-]+");
    private static final DateTimeFormatter ZEIT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final LagerService lager;
    private final Konsole k;

    public LagerKonsole(LagerService lager, BufferedReader ein, PrintStream aus, boolean farbe) {
        this.lager = lager;
        this.k = new Konsole(ein, aus, farbe);
    }

    /** Läuft, bis "Beenden" gewählt wird oder die Eingabe endet (Strg+D). */
    public void starte(String datenbank) {
        k.zeile("Datenbank: " + datenbank);
        k.zeile("Tipp: Eine leere Eingabe bricht den laufenden Vorgang ab, ? zeigt eine Auswahlliste.");
        if (lager.artikelListe(true).isEmpty()) {
            k.zeile("Noch keine Artikel vorhanden: zuerst unter 9 Stammdaten Lagerorte und Artikel anlegen.");
        }
        int unter = lager.unterMindestbestand().size();
        if (unter > 0) {
            k.warnung("Hinweis: " + unter + (unter == 1 ? " Artikel liegt" : " Artikel liegen")
                    + " unter dem Mindestbestand (Menüpunkt 7).");
        }
        try {
            hauptmenue();
        } catch (Konsole.EingabeEnde e) {
            // Ende der Eingabe wie "Beenden" behandeln
        }
        k.zeile("Auf Wiedersehen.");
    }

    private void hauptmenue() {
        while (true) {
            k.zeile(HAUPTMENUE);
            switch (k.lies("Auswahl")) {
                case "1" -> ausfuehren(this::wareneingang);
                case "2" -> ausfuehren(this::warenausgang);
                case "3" -> ausfuehren(this::umlagerung);
                case "4" -> ausfuehren(this::korrektur);
                case "5" -> ausfuehren(this::bestandJeArtikel);
                case "6" -> ausfuehren(this::bestandJeLagerort);
                case "7" -> ausfuehren(this::mindestbestandsliste);
                case "8" -> ausfuehren(this::protokoll);
                case "9" -> stammdatenmenue();
                case "0" -> {
                    return;
                }
                case "" -> {
                }
                default -> k.fehler("Bitte eine Zahl von 0 bis 9 eingeben.");
            }
        }
    }

    private void stammdatenmenue() {
        while (true) {
            k.zeile(STAMMDATENMENUE);
            switch (k.lies("Auswahl")) {
                case "1" -> ausfuehren(this::artikelAnzeigen);
                case "2" -> ausfuehren(this::artikelAnlegen);
                case "3" -> ausfuehren(this::artikelBearbeiten);
                case "4" -> ausfuehren(this::artikelEntfernen);
                case "5" -> ausfuehren(this::lagerorteAnzeigen);
                case "6" -> ausfuehren(this::lagerortAnlegen);
                case "7" -> ausfuehren(this::lagerortBearbeiten);
                case "8" -> ausfuehren(this::lagerortEntfernen);
                case "0", "" -> {
                    return;
                }
                default -> k.fehler("Bitte eine Zahl von 0 bis 8 eingeben.");
            }
        }
    }

    /** Führt einen Menüpunkt aus. Abbruch und Regelverstöße führen zurück ins Menü. */
    private void ausfuehren(Runnable aktion) {
        try {
            aktion.run();
        } catch (Konsole.Abbruch e) {
            k.zeile("Abgebrochen.");
            return;
        } catch (LagerException e) {
            k.fehler(e.getMessage());
        } catch (DatenbankException e) {
            k.fehler("Datenbank: " + e.getMessage());
        }
        k.warten();
    }

    // --- Buchungen ----------------------------------------------------------------------------

    private void wareneingang() {
        ueberschrift("Wareneingang");
        Artikel artikel = frageArtikel("Artikel-Nr.", false);
        zeigeArtikelbestand(artikel);
        Lagerort nach = frageLagerort("Nach Lagerort", false);
        long menge = k.zahl("Menge in " + artikel.einheit(), 1, MAX_MENGE, "Höchstens " + MAX_MENGE + " auf einmal.");
        String bemerkung = k.textMitVorgabe("Bemerkung, z. B. Lieferschein (optional)", "", MAX_BEMERKUNG);
        zeigeErgebnis(lager.eingang(artikel.nummer(), nach.code(), menge, bemerkung));
    }

    private void warenausgang() {
        ueberschrift("Warenausgang");
        Artikel artikel = frageArtikel("Artikel-Nr.", false);
        if (zeigeArtikelbestand(artikel) == 0) {
            throw new LagerException("Artikel " + artikel.nummer() + " hat keinen Bestand, ein Ausgang ist nicht möglich.");
        }
        Bestandsposten quelle = frageQuelle(artikel, "Aus Lagerort");
        long menge = frageEntnahme(quelle);
        String bemerkung = k.textMitVorgabe("Bemerkung, z. B. Auftrag (optional)", "", MAX_BEMERKUNG);
        zeigeErgebnis(lager.ausgang(artikel.nummer(), quelle.lagerort(), menge, bemerkung));
    }

    private void umlagerung() {
        ueberschrift("Umlagerung");
        if (lager.lagerortListe(false).size() < 2) {
            throw new LagerException("Für eine Umlagerung braucht es mindestens zwei aktive Lagerorte.");
        }
        Artikel artikel = frageArtikel("Artikel-Nr.", false);
        if (zeigeArtikelbestand(artikel) == 0) {
            throw new LagerException("Artikel " + artikel.nummer() + " hat keinen Bestand, es gibt nichts umzulagern.");
        }
        Bestandsposten quelle = frageQuelle(artikel, "Von Lagerort");
        Lagerort ziel = frageLagerort("Nach Lagerort", false);
        while (ziel.code().equals(quelle.lagerort())) {
            k.fehler("Das Ziel muss ein anderer Lagerort als " + quelle.lagerort() + " sein.");
            ziel = frageLagerort("Nach Lagerort", false);
        }
        long menge = frageEntnahme(quelle);
        String bemerkung = k.textMitVorgabe("Bemerkung (optional)", "", MAX_BEMERKUNG);
        zeigeErgebnis(lager.umlagerung(artikel.nummer(), quelle.lagerort(), ziel.code(), menge, bemerkung));
    }

    private void korrektur() {
        ueberschrift("Korrektur / Inventur");
        Artikel artikel = frageArtikel("Artikel-Nr.", false);
        zeigeArtikelbestand(artikel);
        Lagerort ort = frageLagerort("Lagerort", false);
        long gebucht = lager.bestand(artikel.nummer(), ort.code());
        k.zeile("Gebuchter Bestand an " + ort.code() + ": " + gebucht + " " + artikel.einheit());
        long gezaehlt = k.zahl("Gezählter Bestand in " + artikel.einheit(), 0, MAX_MENGE,
                "Höchstens " + MAX_MENGE + " möglich.");
        if (gezaehlt == gebucht) {
            k.zeile("Kein Unterschied, es wird nichts gebucht.");
            return;
        }
        String begruendung = k.text("Begründung (Pflicht)", MAX_BEMERKUNG);
        zeigeErgebnis(lager.korrektur(artikel.nummer(), ort.code(), gezaehlt, begruendung));
    }

    /** Fragt einen Lagerort, an dem der Artikel liegt, und liefert ihn mit dem dortigen Bestand. */
    private Bestandsposten frageQuelle(Artikel artikel, String frage) {
        while (true) {
            Lagerort ort = frageLagerort(frage, false);
            long vorhanden = lager.bestand(artikel.nummer(), ort.code());
            if (vorhanden > 0) {
                return new Bestandsposten(artikel, ort.code(), vorhanden);
            }
            k.fehler("An " + ort.code() + " liegt nichts von Artikel " + artikel.nummer() + ".");
        }
    }

    /** Entnahmemenge: höchstens so viel, wie am Quell-Lagerort liegt. */
    private long frageEntnahme(Bestandsposten quelle) {
        String einheit = quelle.artikel().einheit();
        return k.zahl("Menge in " + einheit, 1, quelle.menge(),
                "An " + quelle.lagerort() + " liegen nur " + quelle.menge() + " " + einheit + ".");
    }

    private void zeigeErgebnis(Buchungsergebnis ergebnis) {
        Bewegung b = ergebnis.bewegung();
        Artikelbestand bestand = ergebnis.bestand();
        Artikel artikel = bestand.artikel();
        String einheit = artikel.einheit();
        k.leerzeile();
        k.zeile("Gebucht (#" + b.nr() + "): " + beschreibe(b, einheit));
        if (ergebnis.mindestbestandWarnung()) {
            k.warnung("WARNUNG: Mindestbestand unterschritten",
                    "  " + artikel.nummer() + " " + artikel.bezeichnung(),
                    "  Gesamtbestand " + bestand.gesamtbestand() + " " + einheit
                            + ", Mindestbestand " + artikel.mindestbestand() + " " + einheit,
                    "  Es " + fehlt(bestand.fehlmenge(), einheit) + ".");
        } else {
            k.zeile("Gesamtbestand " + artikel.nummer() + ": " + bestand.gesamtbestand() + " " + einheit);
        }
    }

    private static String beschreibe(Bewegung b, String einheit) {
        String mengeUndArtikel = b.menge() + " " + einheit + " " + b.artikel();
        return switch (b.art()) {
            case EINGANG -> "Eingang " + mengeUndArtikel + " nach " + b.nachLagerort();
            case AUSGANG -> "Ausgang " + mengeUndArtikel + " aus " + b.vonLagerort();
            case UMLAGERUNG -> "Umlagerung " + mengeUndArtikel + " von " + b.vonLagerort() + " nach " + b.nachLagerort();
            case KORREKTUR -> "Korrektur " + mitVorzeichen(b.aenderungGesamtbestand()) + " " + einheit + " "
                    + b.artikel() + " an " + (b.vonLagerort() != null ? b.vonLagerort() : b.nachLagerort());
        };
    }

    // --- Auswertungen -------------------------------------------------------------------------

    private void bestandJeArtikel() {
        ueberschrift("Bestand je Artikel");
        Optional<Artikel> artikel = frageArtikelfilter("Artikel-Nr.");
        if (artikel.isPresent()) {
            zeigeArtikelbestand(artikel.get());
            return;
        }
        List<Artikelbestand> liste = lager.artikelUebersicht();
        if (liste.isEmpty()) {
            k.zeile("Es gibt noch keine Artikel.");
            return;
        }
        var tabelle = new Tabelle().links("Artikel").links("Bezeichnung").rechts("Bestand")
                .rechts("Mindestbestand").links("Einheit").links("Status");
        for (Artikelbestand ab : liste) {
            Artikel a = ab.artikel();
            tabelle.zeile(a.nummer(), a.bezeichnung(), ab.gesamtbestand(), a.mindestbestand(), a.einheit(),
                    ab.unterMindestbestand() ? "!! unter Mindestbestand" : "");
        }
        tabelle.drucken(k);
    }

    private void bestandJeLagerort() {
        ueberschrift("Bestand je Lagerort");
        Optional<Lagerort> ort = frageLagerortfilter("Lagerort");
        List<Bestandsposten> posten = lager.bestaende(null, ort.map(Lagerort::code).orElse(null));
        if (posten.isEmpty()) {
            k.zeile(ort.isPresent() ? "Lagerort " + ort.get().code() + " ist leer." : "Es ist nichts eingelagert.");
        } else {
            var tabelle = new Tabelle().links("Lagerort").links("Artikel").links("Bezeichnung")
                    .rechts("Menge").links("Einheit");
            for (Bestandsposten p : posten) {
                Artikel a = p.artikel();
                tabelle.zeile(p.lagerort(), a.nummer(), a.bezeichnung(), p.menge(), a.einheit());
            }
            tabelle.drucken(k);
        }
        if (ort.isEmpty()) {
            Set<String> belegt = posten.stream().map(Bestandsposten::lagerort).collect(Collectors.toSet());
            List<String> leer = lager.lagerortListe(false).stream()
                    .map(Lagerort::code).filter(code -> !belegt.contains(code)).toList();
            if (!leer.isEmpty()) {
                k.zeile("Leere Lagerorte: " + String.join(", ", leer));
            }
        }
    }

    private void mindestbestandsliste() {
        ueberschrift("Artikel unter Mindestbestand");
        List<Artikelbestand> liste = lager.unterMindestbestand();
        if (liste.isEmpty()) {
            k.zeile("Kein Artikel liegt unter dem Mindestbestand.");
            return;
        }
        var tabelle = new Tabelle().links("Artikel").links("Bezeichnung").rechts("Bestand")
                .rechts("Mindestbestand").rechts("Fehlmenge").links("Einheit");
        for (Artikelbestand ab : liste) {
            Artikel a = ab.artikel();
            tabelle.zeile(a.nummer(), a.bezeichnung(), ab.gesamtbestand(), a.mindestbestand(), ab.fehlmenge(),
                    a.einheit());
        }
        tabelle.drucken(k);
    }

    private void protokoll() {
        ueberschrift("Bewegungsprotokoll");
        Optional<Artikel> artikel = frageArtikelfilter("Artikel-Nr.");
        Optional<Lagerort> ort = frageLagerortfilter("Lagerort");
        List<Bewegung> bewegungen = lager.protokoll(artikel.map(Artikel::nummer).orElse(null),
                ort.map(Lagerort::code).orElse(null));
        if (bewegungen.isEmpty()) {
            k.zeile("Keine Bewegungen gefunden.");
            return;
        }
        var tabelle = new Tabelle().rechts("Nr").links("Zeitpunkt").links("Art").links("Artikel")
                .rechts("Menge").links("Von").links("Nach").links("Bemerkung");
        for (Bewegung b : bewegungen) {
            tabelle.zeile(b.nr(), ZEIT.format(b.zeitpunkt()), b.art().anzeigename(), b.artikel(),
                    mengeImProtokoll(b), b.vonLagerort(), b.nachLagerort(), b.bemerkung());
        }
        tabelle.drucken(k);
        k.zeile(bewegungen.size() + (bewegungen.size() == 1 ? " Bewegung" : " Bewegungen"));
    }

    /** Zu- und Abgänge mit Vorzeichen; Umlagerungen ohne, weil sie den Gesamtbestand nicht ändern. */
    private static String mengeImProtokoll(Bewegung b) {
        long aenderung = b.aenderungGesamtbestand();
        return aenderung == 0 ? String.valueOf(b.menge()) : mitVorzeichen(aenderung);
    }

    // --- Stammdaten ---------------------------------------------------------------------------

    private void artikelAnzeigen() {
        ueberschrift("Artikel");
        if (lager.artikelListe(true).isEmpty()) {
            k.zeile("Es gibt noch keine Artikel.");
            return;
        }
        zeigeArtikeltabelle(true);
    }

    private void artikelAnlegen() {
        ueberschrift("Artikel anlegen");
        String nummer = frageNeuenCode("Artikel-Nr.", nr -> lager.findeArtikel(nr).isPresent(), "Artikel");
        String bezeichnung = k.text("Bezeichnung", MAX_BEZEICHNUNG);
        String einheit = k.textMitVorgabe("Einheit", "Stk", MAX_EINHEIT);
        long mindestbestand = k.zahlMitVorgabe("Mindestbestand", 0, 0, MAX_MENGE);
        Artikel artikel = lager.artikelAnlegen(nummer, bezeichnung, einheit, mindestbestand);
        k.zeile("Angelegt: " + artikel.nummer() + " " + artikel.bezeichnung() + ", Einheit " + artikel.einheit()
                + ", Mindestbestand " + artikel.mindestbestand());
        hinweisMindestbestand(artikel);
    }

    private void artikelBearbeiten() {
        ueberschrift("Artikel bearbeiten");
        Artikel artikel = frageArtikel("Artikel-Nr.", true);
        if (!artikel.aktiv() && k.jaNein("Artikel " + artikel.nummer() + " ist deaktiviert. Wieder aktivieren?")) {
            lager.artikelReaktivieren(artikel.nummer());
            k.zeile("Reaktiviert.");
        }
        k.zeile("Enter übernimmt den Wert in eckigen Klammern.");
        String bezeichnung = k.textMitVorgabe("Bezeichnung", artikel.bezeichnung(), MAX_BEZEICHNUNG);
        String einheit = k.textMitVorgabe("Einheit", artikel.einheit(), MAX_EINHEIT);
        long mindestbestand = k.zahlMitVorgabe("Mindestbestand", artikel.mindestbestand(), 0, MAX_MENGE);
        Artikel neu = lager.artikelAendern(artikel.nummer(), bezeichnung, einheit, mindestbestand);
        k.zeile("Gespeichert.");
        if (neu.aktiv()) {
            hinweisMindestbestand(neu);
        }
    }

    private void artikelEntfernen() {
        ueberschrift("Artikel löschen / deaktivieren");
        Artikel artikel = frageArtikel("Artikel-Nr.", false);
        Entfernen wie = lager.artikelEntfernenPruefen(artikel.nummer());
        String frage = wie == Entfernen.LOESCHEN
                ? "Artikel " + artikel.nummer() + " " + artikel.bezeichnung() + " endgültig löschen?"
                : "Artikel " + artikel.nummer() + " steht im Bewegungsprotokoll und kann nicht gelöscht werden."
                        + " Stattdessen deaktivieren?";
        if (!k.jaNein(frage)) {
            k.zeile("Nichts geändert.");
            return;
        }
        k.zeile(lager.artikelEntfernen(artikel.nummer()) == Entfernen.LOESCHEN ? "Gelöscht." : "Deaktiviert.");
    }

    private void lagerorteAnzeigen() {
        ueberschrift("Lagerorte");
        if (lager.lagerortListe(true).isEmpty()) {
            k.zeile("Es gibt noch keine Lagerorte.");
            return;
        }
        zeigeLagerorttabelle(true);
    }

    private void lagerortAnlegen() {
        ueberschrift("Lagerort anlegen");
        String code = frageNeuenCode("Lagerort-Code, z. B. A-01-03", c -> lager.findeLagerort(c).isPresent(), "Lagerort");
        String bezeichnung = k.textMitVorgabe("Bezeichnung (optional)", "", MAX_BEZEICHNUNG);
        Lagerort ort = lager.lagerortAnlegen(code, bezeichnung);
        k.zeile("Angelegt: Lagerort " + ort.code() + (ort.bezeichnung().isEmpty() ? "" : " " + ort.bezeichnung()));
    }

    private void lagerortBearbeiten() {
        ueberschrift("Lagerort bearbeiten");
        Lagerort ort = frageLagerort("Lagerort", true);
        if (!ort.aktiv() && k.jaNein("Lagerort " + ort.code() + " ist deaktiviert. Wieder aktivieren?")) {
            lager.lagerortReaktivieren(ort.code());
            k.zeile("Reaktiviert.");
        }
        k.zeile("Enter übernimmt den Wert in eckigen Klammern.");
        String bezeichnung = k.textMitVorgabe("Bezeichnung", ort.bezeichnung(), MAX_BEZEICHNUNG);
        lager.lagerortAendern(ort.code(), bezeichnung);
        k.zeile("Gespeichert.");
    }

    private void lagerortEntfernen() {
        ueberschrift("Lagerort löschen / deaktivieren");
        Lagerort ort = frageLagerort("Lagerort", false);
        Entfernen wie = lager.lagerortEntfernenPruefen(ort.code());
        String frage = wie == Entfernen.LOESCHEN
                ? "Lagerort " + ort.code() + " endgültig löschen?"
                : "Lagerort " + ort.code() + " steht im Bewegungsprotokoll und kann nicht gelöscht werden."
                        + " Stattdessen deaktivieren?";
        if (!k.jaNein(frage)) {
            k.zeile("Nichts geändert.");
            return;
        }
        k.zeile(lager.lagerortEntfernen(ort.code()) == Entfernen.LOESCHEN ? "Gelöscht." : "Deaktiviert.");
    }

    // --- Abfragen und Anzeigen ----------------------------------------------------------------

    /** Pflichtabfrage eines Artikels. */
    private Artikel frageArtikel(String frage, boolean auchInaktive) {
        if (lager.artikelListe(auchInaktive).isEmpty()) {
            throw new LagerException("Es gibt noch keine Artikel. Bitte zuerst unter 9 Stammdaten anlegen.");
        }
        return sucheArtikel(frage + " (? = Liste)", auchInaktive, false).orElseThrow();
    }

    /** Filterabfrage: Eine leere Eingabe bedeutet "alle" und liefert Optional.empty(). */
    private Optional<Artikel> frageArtikelfilter(String frage) {
        return sucheArtikel(frage + " (leer = alle, ? = Liste)", true, true);
    }

    private Optional<Artikel> sucheArtikel(String frage, boolean auchInaktive, boolean leerErlaubt) {
        while (true) {
            String eingabe = leerErlaubt ? k.textMitVorgabe(frage, "", MAX_CODE) : k.text(frage, MAX_CODE);
            if (eingabe.isEmpty()) {
                return Optional.empty();
            }
            String nummer = eingabe.toUpperCase(Locale.ROOT);
            if (nummer.equals("?")) {
                zeigeArtikeltabelle(auchInaktive);
                continue;
            }
            Optional<Artikel> artikel = lager.findeArtikel(nummer);
            if (artikel.isEmpty()) {
                k.fehler("Artikel " + nummer + " gibt es nicht.");
            } else if (!artikel.get().aktiv() && !auchInaktive) {
                k.fehler("Artikel " + nummer + " ist deaktiviert (reaktivieren unter 9 Stammdaten).");
            } else {
                return artikel;
            }
        }
    }

    /** Pflichtabfrage eines Lagerorts. */
    private Lagerort frageLagerort(String frage, boolean auchInaktive) {
        if (lager.lagerortListe(auchInaktive).isEmpty()) {
            throw new LagerException("Es gibt noch keine Lagerorte. Bitte zuerst unter 9 Stammdaten anlegen.");
        }
        return sucheLagerort(frage + " (? = Liste)", auchInaktive, false).orElseThrow();
    }

    /** Filterabfrage: Eine leere Eingabe bedeutet "alle" und liefert Optional.empty(). */
    private Optional<Lagerort> frageLagerortfilter(String frage) {
        return sucheLagerort(frage + " (leer = alle, ? = Liste)", true, true);
    }

    private Optional<Lagerort> sucheLagerort(String frage, boolean auchInaktive, boolean leerErlaubt) {
        while (true) {
            String eingabe = leerErlaubt ? k.textMitVorgabe(frage, "", MAX_CODE) : k.text(frage, MAX_CODE);
            if (eingabe.isEmpty()) {
                return Optional.empty();
            }
            String code = eingabe.toUpperCase(Locale.ROOT);
            if (code.equals("?")) {
                zeigeLagerorttabelle(auchInaktive);
                continue;
            }
            Optional<Lagerort> ort = lager.findeLagerort(code);
            if (ort.isEmpty()) {
                k.fehler("Lagerort " + code + " gibt es nicht.");
            } else if (!ort.get().aktiv() && !auchInaktive) {
                k.fehler("Lagerort " + code + " ist deaktiviert (reaktivieren unter 9 Stammdaten).");
            } else {
                return ort;
            }
        }
    }

    /** Fragt eine neue Artikelnummer bzw. einen neuen Lagerort-Code ab (eindeutig, in Großbuchstaben). */
    private String frageNeuenCode(String frage, Predicate<String> gibtEsSchon, String was) {
        while (true) {
            String code = k.text(frage, MAX_CODE).toUpperCase(Locale.ROOT);
            if (!CODE.matcher(code).matches()) {
                k.fehler("Erlaubt sind Buchstaben, Ziffern und . _ / - (keine Leerzeichen).");
            } else if (gibtEsSchon.test(code)) {
                k.fehler(was + " " + code + " gibt es schon.");
            } else {
                return code;
            }
        }
    }

    /** Zeigt Gesamtbestand und Verteilung auf die Lagerorte; liefert den Gesamtbestand. */
    private long zeigeArtikelbestand(Artikel artikel) {
        List<Bestandsposten> posten = lager.bestaende(artikel.nummer(), null);
        long gesamt = posten.stream().mapToLong(Bestandsposten::menge).sum();
        String einheit = artikel.einheit();
        k.zeile("  " + artikel.nummer() + " " + artikel.bezeichnung() + (artikel.aktiv() ? "" : " (deaktiviert)")
                + " | Gesamtbestand " + gesamt + " " + einheit
                + " | Mindestbestand " + artikel.mindestbestand() + " " + einheit);
        if (posten.isEmpty()) {
            k.zeile("    (kein Bestand)");
        }
        int ortBreite = posten.stream().mapToInt(p -> p.lagerort().length()).max().orElse(1);
        int mengenBreite = posten.stream().mapToInt(p -> String.valueOf(p.menge()).length()).max().orElse(1);
        for (Bestandsposten p : posten) {
            k.zeile(String.format("    %-" + ortBreite + "s  %" + mengenBreite + "d %s", p.lagerort(), p.menge(), einheit));
        }
        var bestand = new Artikelbestand(artikel, gesamt);
        if (artikel.aktiv() && bestand.unterMindestbestand()) {
            k.warnung("Unter Mindestbestand, es " + fehlt(bestand.fehlmenge(), einheit) + ".");
        }
        return gesamt;
    }

    /** Nach Anlegen oder Ändern sofort zeigen, wenn der Bestand schon unter dem Mindestbestand liegt. */
    private void hinweisMindestbestand(Artikel artikel) {
        var bestand = new Artikelbestand(artikel, lager.gesamtbestand(artikel.nummer()));
        if (bestand.unterMindestbestand()) {
            k.warnung("Hinweis: Gesamtbestand " + bestand.gesamtbestand() + " " + artikel.einheit()
                    + " liegt unter dem Mindestbestand von " + artikel.mindestbestand() + " " + artikel.einheit() + ".");
        }
    }

    private void zeigeArtikeltabelle(boolean auchInaktive) {
        var tabelle = new Tabelle().links("Artikel").links("Bezeichnung").links("Einheit")
                .rechts("Mindestbestand").links("Status");
        for (Artikel a : lager.artikelListe(auchInaktive)) {
            tabelle.zeile(a.nummer(), a.bezeichnung(), a.einheit(), a.mindestbestand(),
                    a.aktiv() ? "aktiv" : "deaktiviert");
        }
        tabelle.drucken(k);
    }

    private void zeigeLagerorttabelle(boolean auchInaktive) {
        var tabelle = new Tabelle().links("Lagerort").links("Bezeichnung").links("Status");
        for (Lagerort l : lager.lagerortListe(auchInaktive)) {
            tabelle.zeile(l.code(), l.bezeichnung(), l.aktiv() ? "aktiv" : "deaktiviert");
        }
        tabelle.drucken(k);
    }

    private void ueberschrift(String titel) {
        k.leerzeile();
        k.zeile("--- " + titel + " ---");
    }

    private static String mitVorzeichen(long zahl) {
        return zahl > 0 ? "+" + zahl : String.valueOf(zahl);
    }

    /** "fehlt 1 Stk" bzw. "fehlen 5 Stk". */
    private static String fehlt(long menge, String einheit) {
        return (menge == 1 ? "fehlt " : "fehlen ") + menge + " " + einheit;
    }
}
