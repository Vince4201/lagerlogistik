package lager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Verbindung zur SQLite-Datei. Legt beim Öffnen fehlende Tabellen an und bietet kleine
 * Hilfsmethoden, damit die Repositories ohne JDBC-Boilerplate auskommen.
 */
public final class Datenbank implements AutoCloseable {

    /** Liest die aktuelle Zeile eines ResultSets in ein Objekt. */
    @FunctionalInterface
    public interface Zeilenleser<T> {
        T lies(ResultSet rs) throws SQLException;
    }

    private static final String[] SCHEMA = {
        """
        CREATE TABLE IF NOT EXISTS artikel (
            nummer         TEXT    PRIMARY KEY,
            bezeichnung    TEXT    NOT NULL,
            einheit        TEXT    NOT NULL,
            mindestbestand INTEGER NOT NULL DEFAULT 0 CHECK (mindestbestand >= 0),
            aktiv          INTEGER NOT NULL DEFAULT 1 CHECK (aktiv IN (0, 1))
        )""",
        """
        CREATE TABLE IF NOT EXISTS lagerort (
            code        TEXT    PRIMARY KEY,
            bezeichnung TEXT    NOT NULL,
            aktiv       INTEGER NOT NULL DEFAULT 1 CHECK (aktiv IN (0, 1))
        )""",
        // Das Bewegungsprotokoll. Die Menge ist immer positiv, die Richtung steckt in von/nach.
        """
        CREATE TABLE IF NOT EXISTS bewegung (
            nr            INTEGER PRIMARY KEY AUTOINCREMENT,
            zeitpunkt     TEXT    NOT NULL,
            art           TEXT    NOT NULL,
            artikel       TEXT    NOT NULL REFERENCES artikel (nummer),
            menge         INTEGER NOT NULL CHECK (menge > 0),
            von_lagerort  TEXT    REFERENCES lagerort (code),
            nach_lagerort TEXT    REFERENCES lagerort (code),
            bemerkung     TEXT    NOT NULL DEFAULT '',
            CHECK (
                (art = 'EINGANG'    AND von_lagerort IS NULL     AND nach_lagerort IS NOT NULL) OR
                (art = 'AUSGANG'    AND von_lagerort IS NOT NULL AND nach_lagerort IS NULL) OR
                (art = 'UMLAGERUNG' AND von_lagerort IS NOT NULL AND nach_lagerort IS NOT NULL
                                    AND von_lagerort <> nach_lagerort) OR
                (art = 'KORREKTUR'  AND (von_lagerort IS NULL) <> (nach_lagerort IS NULL))
            )
        )""",
        // Gebuchte Bewegungen sind unveränderlich. Fehler werden mit einer Korrektur ausgeglichen.
        """
        CREATE TRIGGER IF NOT EXISTS bewegung_nicht_aendern BEFORE UPDATE ON bewegung
        BEGIN
            SELECT RAISE(ABORT, 'Bewegungen dürfen nicht geändert werden');
        END""",
        """
        CREATE TRIGGER IF NOT EXISTS bewegung_nicht_loeschen BEFORE DELETE ON bewegung
        BEGIN
            SELECT RAISE(ABORT, 'Bewegungen dürfen nicht gelöscht werden');
        END""",
        // Der Bestand wird nicht gespeichert, sondern aus dem Protokoll berechnet:
        // Zugänge (nach_lagerort) minus Abgänge (von_lagerort) je Artikel und Lagerort.
        """
        CREATE VIEW IF NOT EXISTS bestand AS
        SELECT artikel, lagerort, SUM(delta) AS menge
        FROM (
            SELECT artikel, nach_lagerort AS lagerort, menge AS delta
            FROM bewegung WHERE nach_lagerort IS NOT NULL
            UNION ALL
            SELECT artikel, von_lagerort AS lagerort, -menge AS delta
            FROM bewegung WHERE von_lagerort IS NOT NULL
        )
        GROUP BY artikel, lagerort"""
    };

    private final Connection verbindung;

    private Datenbank(Connection verbindung) {
        this.verbindung = verbindung;
    }

    /** Öffnet die Datei (legt sie bei Bedarf an) und richtet das Schema ein. */
    public static Datenbank oeffnen(String datei) {
        try {
            Connection verbindung = DriverManager.getConnection("jdbc:sqlite:" + datei);
            try (Statement st = verbindung.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
                for (String sql : SCHEMA) {
                    st.execute(sql);
                }
            } catch (SQLException e) {
                verbindung.close();
                throw e;
            }
            return new Datenbank(verbindung);
        } catch (SQLException e) {
            throw new DatenbankException("Datenbank " + datei + " lässt sich nicht öffnen: " + e.getMessage(), e);
        }
    }

    public <T> List<T> liste(String sql, Zeilenleser<T> leser, Object... parameter) {
        try (PreparedStatement ps = verbindung.prepareStatement(sql)) {
            binden(ps, parameter);
            try (ResultSet rs = ps.executeQuery()) {
                List<T> ergebnis = new ArrayList<>();
                while (rs.next()) {
                    ergebnis.add(leser.lies(rs));
                }
                return ergebnis;
            }
        } catch (SQLException e) {
            throw new DatenbankException(e.getMessage(), e);
        }
    }

    public <T> Optional<T> einzeln(String sql, Zeilenleser<T> leser, Object... parameter) {
        return liste(sql, leser, parameter).stream().findFirst();
    }

    /** Für Abfragen, die genau eine Zahl liefern (SUM, EXISTS, ...). */
    public long zahl(String sql, Object... parameter) {
        return einzeln(sql, rs -> rs.getLong(1), parameter).orElse(0L);
    }

    /** Für INSERT, UPDATE und DELETE; liefert die Anzahl geänderter Zeilen. */
    public int ausfuehren(String sql, Object... parameter) {
        try (PreparedStatement ps = verbindung.prepareStatement(sql)) {
            binden(ps, parameter);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatenbankException(e.getMessage(), e);
        }
    }

    /**
     * Führt die Arbeit in einer Transaktion aus: alles oder nichts. Bei einer Exception wird
     * zurückgerollt. Läuft bereits eine Transaktion, wird die Arbeit einfach darin ausgeführt.
     */
    public <T> T transaktion(Supplier<T> arbeit) {
        try {
            if (!verbindung.getAutoCommit()) {
                return arbeit.get();
            }
            verbindung.setAutoCommit(false);
            try {
                T ergebnis = arbeit.get();
                verbindung.commit();
                return ergebnis;
            } catch (RuntimeException e) {
                verbindung.rollback();
                throw e;
            } finally {
                verbindung.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DatenbankException(e.getMessage(), e);
        }
    }

    public void transaktion(Runnable arbeit) {
        transaktion(() -> {
            arbeit.run();
            return null;
        });
    }

    @Override
    public void close() {
        try {
            verbindung.close();
        } catch (SQLException e) {
            throw new DatenbankException(e.getMessage(), e);
        }
    }

    private static void binden(PreparedStatement ps, Object... parameter) throws SQLException {
        for (int i = 0; i < parameter.length; i++) {
            ps.setObject(i + 1, parameter[i]);
        }
    }
}
