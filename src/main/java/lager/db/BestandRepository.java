package lager.db;

import java.util.List;

import lager.model.Artikelbestand;
import lager.model.Bestandsposten;

/** Liest Bestände aus der View bestand, die sie aus dem Bewegungsprotokoll berechnet. */
public final class BestandRepository {

    private final Datenbank db;

    public BestandRepository(Datenbank db) {
        this.db = db;
    }

    public long gesamtbestand(String artikel) {
        return db.zahl("SELECT COALESCE(SUM(menge), 0) FROM bestand WHERE artikel = ?", artikel);
    }

    public long bestand(String artikel, String lagerort) {
        return db.zahl("SELECT COALESCE(SUM(menge), 0) FROM bestand WHERE artikel = ? AND lagerort = ?",
                artikel, lagerort);
    }

    /** Alle Bestände ungleich 0, nach Lagerort und Artikel sortiert. Ein Filter mit null bedeutet "alle". */
    public List<Bestandsposten> posten(String artikel, String lagerort) {
        return db.liste("""
                SELECT a.nummer AS nummer, a.bezeichnung AS bezeichnung, a.einheit AS einheit,
                       a.mindestbestand AS mindestbestand, a.aktiv AS aktiv,
                       b.lagerort AS lagerort, b.menge AS menge
                FROM bestand b
                JOIN artikel a ON a.nummer = b.artikel
                WHERE b.menge <> 0
                  AND (?1 IS NULL OR b.artikel = ?1)
                  AND (?2 IS NULL OR b.lagerort = ?2)
                ORDER BY CAST(b.lagerort AS INTEGER), b.lagerort, CAST(a.nummer AS INTEGER), a.nummer""",
                rs -> new Bestandsposten(ArtikelRepository.lies(rs), rs.getString("lagerort"), rs.getLong("menge")),
                artikel, lagerort);
    }

    /** Alle aktiven Artikel mit ihrem Gesamtbestand über alle Lagerorte. */
    public List<Artikelbestand> artikelbestaende() {
        return db.liste("""
                SELECT a.nummer AS nummer, a.bezeichnung AS bezeichnung, a.einheit AS einheit,
                       a.mindestbestand AS mindestbestand, a.aktiv AS aktiv,
                       COALESCE(SUM(b.menge), 0) AS gesamt
                FROM artikel a
                LEFT JOIN bestand b ON b.artikel = a.nummer
                WHERE a.aktiv = 1
                GROUP BY a.nummer
                ORDER BY CAST(a.nummer AS INTEGER), a.nummer""",
                rs -> new Artikelbestand(ArtikelRepository.lies(rs), rs.getLong("gesamt")));
    }
}
