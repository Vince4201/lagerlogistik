package lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import lager.model.Artikel;

/** SQL-Zugriff auf die Tabelle artikel. */
public final class ArtikelRepository {

    private final Datenbank db;

    public ArtikelRepository(Datenbank db) {
        this.db = db;
    }

    public Optional<Artikel> finde(String nummer) {
        return db.einzeln("SELECT * FROM artikel WHERE nummer = ?", ArtikelRepository::lies, nummer);
    }

    /** Sortiert Nummern aus Ziffern nach Zahlenwert (2 vor 10), alle anderen alphabetisch. */
    public List<Artikel> alle(boolean auchInaktive) {
        return db.liste("""
                SELECT * FROM artikel
                WHERE aktiv = 1 OR ?
                ORDER BY CAST(nummer AS INTEGER), nummer""", ArtikelRepository::lies, auchInaktive ? 1 : 0);
    }

    public void einfuegen(Artikel a) {
        db.ausfuehren("INSERT INTO artikel (nummer, bezeichnung, einheit, mindestbestand, aktiv) VALUES (?, ?, ?, ?, ?)",
                a.nummer(), a.bezeichnung(), a.einheit(), a.mindestbestand(), a.aktiv() ? 1 : 0);
    }

    public void aendern(Artikel a) {
        db.ausfuehren("UPDATE artikel SET bezeichnung = ?, einheit = ?, mindestbestand = ?, aktiv = ? WHERE nummer = ?",
                a.bezeichnung(), a.einheit(), a.mindestbestand(), a.aktiv() ? 1 : 0, a.nummer());
    }

    public void loeschen(String nummer) {
        db.ausfuehren("DELETE FROM artikel WHERE nummer = ?", nummer);
    }

    /** Liest einen Artikel; die Abfrage muss die Spalten der Tabelle artikel unter ihren Namen liefern. */
    static Artikel lies(ResultSet rs) throws SQLException {
        return new Artikel(rs.getString("nummer"), rs.getString("bezeichnung"), rs.getString("einheit"),
                rs.getLong("mindestbestand"), rs.getInt("aktiv") == 1);
    }
}
