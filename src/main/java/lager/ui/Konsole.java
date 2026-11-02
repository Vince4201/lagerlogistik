package lager.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.Locale;

/**
 * Ein- und Ausgabe im Terminal. Ungültige Eingaben werden erneut abgefragt; eine leere Eingabe
 * in einem Pflichtfeld bricht den laufenden Vorgang ab.
 */
final class Konsole {

    /** Ein Pflichtfeld blieb leer: Der laufende Vorgang wird abgebrochen. */
    static final class Abbruch extends RuntimeException {
        Abbruch() {
            super(null, null, false, false);
        }
    }

    /** Die Eingabe ist zu Ende (Strg+D oder Ende einer umgeleiteten Datei). */
    static final class EingabeEnde extends RuntimeException {
        EingabeEnde() {
            super(null, null, false, false);
        }
    }

    private static final String GELB = "\u001B[1;33m";
    private static final String ROT = "\u001B[1;31m";
    private static final String NORMAL = "\u001B[0m";

    private final BufferedReader ein;
    private final PrintStream aus;
    private final boolean farbe;

    Konsole(BufferedReader ein, PrintStream aus, boolean farbe) {
        this.ein = ein;
        this.aus = aus;
        this.farbe = farbe;
    }

    void zeile(String text) {
        aus.println(text);
    }

    void leerzeile() {
        aus.println();
    }

    /** Hervorgehobene Warnung; jede Zeile beginnt mit "!!". */
    void warnung(String... zeilen) {
        for (String zeile : zeilen) {
            aus.println(einfaerben(GELB, "!! " + zeile));
        }
    }

    void fehler(String text) {
        aus.println(einfaerben(ROT, "Fehler: " + text));
    }

    /** Gibt die Frage aus und liest eine Zeile ohne Leerzeichen am Anfang und Ende. */
    String lies(String frage) {
        aus.print(frage + ": ");
        aus.flush();
        return naechsteZeile().strip();
    }

    /** Pflichtfeld: Eine leere Eingabe bricht ab. */
    String text(String frage, int maxLaenge) {
        while (true) {
            String eingabe = lies(frage);
            if (eingabe.isEmpty()) {
                throw new Abbruch();
            }
            if (eingabe.length() <= maxLaenge) {
                return eingabe;
            }
            fehler("Bitte höchstens " + maxLaenge + " Zeichen eingeben.");
        }
    }

    /** Eine leere Eingabe übernimmt die Vorgabe; ist sie nicht leer, steht sie in eckigen Klammern. */
    String textMitVorgabe(String frage, String vorgabe, int maxLaenge) {
        String anzeige = vorgabe.isEmpty() ? frage : frage + " [" + vorgabe + "]";
        while (true) {
            String eingabe = lies(anzeige);
            if (eingabe.isEmpty()) {
                return vorgabe;
            }
            if (eingabe.length() <= maxLaenge) {
                return eingabe;
            }
            fehler("Bitte höchstens " + maxLaenge + " Zeichen eingeben.");
        }
    }

    /** Pflichtfeld für eine ganze Zahl von min bis max; zuGross ist die Meldung bei mehr als max. */
    long zahl(String frage, long min, long max, String zuGross) {
        while (true) {
            String eingabe = lies(frage);
            if (eingabe.isEmpty()) {
                throw new Abbruch();
            }
            Long wert = pruefeZahl(eingabe, min, max, zuGross);
            if (wert != null) {
                return wert;
            }
        }
    }

    /** Ganze Zahl von min bis max; eine leere Eingabe übernimmt die Vorgabe. */
    long zahlMitVorgabe(String frage, long vorgabe, long min, long max) {
        while (true) {
            String eingabe = lies(frage + " [" + vorgabe + "]");
            if (eingabe.isEmpty()) {
                return vorgabe;
            }
            Long wert = pruefeZahl(eingabe, min, max, "Höchstens " + max + " möglich.");
            if (wert != null) {
                return wert;
            }
        }
    }

    boolean jaNein(String frage) {
        while (true) {
            switch (lies(frage + " (j/n)").toLowerCase(Locale.ROOT)) {
                case "j", "ja" -> {
                    return true;
                }
                case "n", "nein" -> {
                    return false;
                }
                default -> fehler("Bitte j oder n eingeben.");
            }
        }
    }

    void warten() {
        aus.print("Weiter mit Enter ...");
        aus.flush();
        naechsteZeile();
    }

    /** Liefert die Zahl oder null, nachdem eine Fehlermeldung ausgegeben wurde. */
    private Long pruefeZahl(String eingabe, long min, long max, String zuGross) {
        long wert;
        try {
            wert = Long.parseLong(eingabe);
        } catch (NumberFormatException e) {
            fehler("Bitte eine ganze Zahl eingeben.");
            return null;
        }
        if (wert < min) {
            fehler("Die Zahl muss mindestens " + min + " sein.");
            return null;
        }
        if (wert > max) {
            fehler(zuGross);
            return null;
        }
        return wert;
    }

    private String naechsteZeile() {
        try {
            String zeile = ein.readLine();
            if (zeile == null) {
                aus.println();
                throw new EingabeEnde();
            }
            return zeile;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String einfaerben(String farbcode, String text) {
        return farbe ? farbcode + text + NORMAL : text;
    }
}
