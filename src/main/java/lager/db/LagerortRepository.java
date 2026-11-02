package lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import lager.model.Lagerort;

/** SQL-Zugriff auf die Tabelle lagerort. */
public final class LagerortRepository {

    private final Datenbank db;

    public LagerortRepository(Datenbank db) {
        this.db = db;
    }

    public Optional<Lagerort> finde(String code) {
        return db.einzeln("SELECT * FROM lagerort WHERE code = ?", LagerortRepository::lies, code);
    }

    /** Sortiert Codes aus Ziffern nach Zahlenwert (2 vor 10), alle anderen alphabetisch. */
    public List<Lagerort> alle(boolean auchInaktive) {
        return db.liste("""
                SELECT * FROM lagerort
                WHERE aktiv = 1 OR ?
                ORDER BY CAST(code AS INTEGER), code""", LagerortRepository::lies, auchInaktive ? 1 : 0);
    }

    public void einfuegen(Lagerort l) {
        db.ausfuehren("INSERT INTO lagerort (code, bezeichnung, aktiv) VALUES (?, ?, ?)",
                l.code(), l.bezeichnung(), l.aktiv() ? 1 : 0);
    }

    public void aendern(Lagerort l) {
        db.ausfuehren("UPDATE lagerort SET bezeichnung = ?, aktiv = ? WHERE code = ?",
                l.bezeichnung(), l.aktiv() ? 1 : 0, l.code());
    }

    public void loeschen(String code) {
        db.ausfuehren("DELETE FROM lagerort WHERE code = ?", code);
    }

    private static Lagerort lies(ResultSet rs) throws SQLException {
        return new Lagerort(rs.getString("code"), rs.getString("bezeichnung"), rs.getInt("aktiv") == 1);
    }
}
