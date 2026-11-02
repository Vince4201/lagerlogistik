package lager.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;
import lager.service.LagerService;

/** Anforderungen, die das Datenbankschema selbst absichert. */
class DatenbankTest extends LagerTestBasis {

    @Test
    @DisplayName("A01 Alle Daten liegen in einer einzigen lokalen Datei, ein Server ist nicht nötig")
    void datenLiegenInEinerLokalenDatei() {
        lager.eingang("1001", "A-01", 5, "");
        db.close();

        Path datei = ordner.resolve("test.db");
        assertTrue(Files.isRegularFile(datei));
        db = Datenbank.oeffnen(datei.toString());
        assertEquals(5, new LagerService(db).gesamtbestand("1001"));
    }

    @Test
    @DisplayName("A03 Keine Tabelle hat eine Spalte für Preise oder Werte")
    void keineSpaltenFuerPreise() {
        for (String tabelle : List.of("artikel", "lagerort", "bewegung")) {
            List<String> spalten = db.liste("SELECT name FROM pragma_table_info(?)", rs -> rs.getString(1), tabelle);
            assertFalse(spalten.isEmpty(), tabelle);
            for (String spalte : spalten) {
                String name = spalte.toLowerCase(Locale.ROOT);
                assertFalse(name.contains("preis") || name.contains("price") || name.contains("wert"),
                        tabelle + "." + spalte);
            }
        }
    }

    @Test
    @DisplayName("A05 Der Bestand ist keine gespeicherte Tabelle, sondern eine View über den Bewegungen")
    void bestandIstEineView() {
        assertEquals("view", db.einzeln("SELECT type FROM sqlite_master WHERE name = 'bestand'",
                rs -> rs.getString(1)).orElseThrow());
        List<String> tabellen = db.liste(
                "SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%' ORDER BY name",
                rs -> rs.getString(1));
        assertEquals(List.of("artikel", "bewegung", "lagerort"), tabellen);
    }

    @Test
    @DisplayName("A06 Die Datenbank lehnt das Ändern und Löschen gebuchter Bewegungen ab")
    void bewegungenSindUnveraenderlich() {
        lager.eingang("1001", "A-01", 5, "");

        var aendern = assertThrows(DatenbankException.class,
                () -> db.ausfuehren("UPDATE bewegung SET menge = 50"));
        assertTrue(aendern.getMessage().contains("nicht geändert"), aendern.getMessage());
        var loeschen = assertThrows(DatenbankException.class,
                () -> db.ausfuehren("DELETE FROM bewegung"));
        assertTrue(loeschen.getMessage().contains("nicht gelöscht"), loeschen.getMessage());
        assertEquals(5, lager.gesamtbestand("1001"));
    }
}
