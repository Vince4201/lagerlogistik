package lager;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import lager.db.Datenbank;
import lager.db.DatenbankException;
import lager.service.LagerService;
import lager.ui.LagerKonsole;

/** Startet die Lagerverwaltung. Optionales Argument: Pfad der Datenbankdatei (Standard: lager.db). */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Path datei = Path.of(args.length > 0 ? args[0] : "lager.db").toAbsolutePath().normalize();
        boolean neu = Files.notExists(datei);

        // Ein- und Ausgabe fest in UTF-8, damit Umlaute unabhängig von der Spracheinstellung funktionieren.
        var ein = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        var aus = new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8);
        boolean farbe = System.console() != null && System.getenv("NO_COLOR") == null;

        try (Datenbank db = Datenbank.oeffnen(datei.toString())) {
            String info = datei + (neu ? " (neu angelegt)" : "");
            new LagerKonsole(new LagerService(db), ein, aus, farbe).starte(info);
        } catch (DatenbankException e) {
            System.err.println("Datenbankfehler: " + e.getMessage());
            System.exit(1);
        }
    }
}
