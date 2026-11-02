package lager;

import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import lager.db.Datenbank;
import lager.service.LagerService;

/**
 * Gemeinsame Grundlage der Tests: Jeder Test bekommt eine eigene, leere Datenbankdatei in einem
 * temporären Ordner und dazu einen kleinen Satz Stammdaten.
 *
 * <pre>
 * Lagerorte  A-01, B-01, C-01
 * Artikel    1001 Schraube M6  Stk  Mindestbestand 10
 *            1002 Mutter M6    Stk  Mindestbestand 0 (keine Überwachung)
 * </pre>
 */
public abstract class LagerTestBasis {

    @TempDir
    protected Path ordner;

    protected Datenbank db;
    protected LagerService lager;

    @BeforeEach
    void datenbankAnlegen() {
        db = Datenbank.oeffnen(ordner.resolve("test.db").toString());
        lager = new LagerService(db);
        lager.lagerortAnlegen("A-01", "Halle A, Regal 1");
        lager.lagerortAnlegen("B-01", "Halle B, Regal 1");
        lager.lagerortAnlegen("C-01", "");
        lager.artikelAnlegen("1001", "Schraube M6", "Stk", 10);
        lager.artikelAnlegen("1002", "Mutter M6", "Stk", 0);
    }

    @AfterEach
    void datenbankSchliessen() {
        db.close();
    }
}
