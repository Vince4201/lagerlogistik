package lager.ui;

import java.util.ArrayList;
import java.util.List;

/** Gibt Zeilen als Tabelle aus; jede Spalte ist so breit wie ihr längster Eintrag. */
final class Tabelle {

    private final List<String> titel = new ArrayList<>();
    private final List<Boolean> rechtsbuendig = new ArrayList<>();
    private final List<String[]> zeilen = new ArrayList<>();

    /** Spalte für Text (linksbündig). */
    Tabelle links(String spaltentitel) {
        titel.add(spaltentitel);
        rechtsbuendig.add(false);
        return this;
    }

    /** Spalte für Zahlen (rechtsbündig). */
    Tabelle rechts(String spaltentitel) {
        titel.add(spaltentitel);
        rechtsbuendig.add(true);
        return this;
    }

    /** Fügt eine Zeile hinzu; null wird als leere Zelle ausgegeben. */
    void zeile(Object... werte) {
        String[] zellen = new String[titel.size()];
        for (int i = 0; i < zellen.length; i++) {
            zellen[i] = i < werte.length && werte[i] != null ? werte[i].toString() : "";
        }
        zeilen.add(zellen);
    }

    void drucken(Konsole k) {
        int[] breite = new int[titel.size()];
        String[] striche = new String[titel.size()];
        for (int i = 0; i < breite.length; i++) {
            breite[i] = titel.get(i).length();
            for (String[] zeile : zeilen) {
                breite[i] = Math.max(breite[i], zeile[i].length());
            }
            striche[i] = "-".repeat(breite[i]);
        }
        k.zeile(formatieren(titel.toArray(String[]::new), breite));
        k.zeile(formatieren(striche, breite));
        for (String[] zeile : zeilen) {
            k.zeile(formatieren(zeile, breite));
        }
    }

    private String formatieren(String[] zellen, int[] breite) {
        StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < zellen.length; i++) {
            String luecke = " ".repeat(breite[i] - zellen[i].length());
            sb.append(' ').append(rechtsbuendig.get(i) ? luecke + zellen[i] : zellen[i] + luecke).append(' ');
        }
        return sb.toString().stripTrailing();
    }
}
