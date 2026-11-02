package lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lager.model.Bewegung;
import lager.model.Bewegungsart;

/** SQL-Zugriff auf das Bewegungsprotokoll. Es wird nur angefügt, nie geändert. */
public final class BewegungRepository {

    private static final DateTimeFormatter ZEITFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Datenbank db;

    public BewegungRepository(Datenbank db) {
        this.db = db;
    }

    /** Speichert eine neue Bewegung und liefert sie mit der vergebenen laufenden Nummer zurück. */
    public Bewegung speichern(Bewegung b) {
        db.ausfuehren("""
                INSERT INTO bewegung (zeitpunkt, art, artikel, menge, von_lagerort, nach_lagerort, bemerkung)
                VALUES (?, ?, ?, ?, ?, ?, ?)""",
                ZEITFORMAT.format(b.zeitpunkt()), b.art().name(), b.artikel(), b.menge(),
                b.vonLagerort(), b.nachLagerort(), b.bemerkung());
        long nr = db.zahl("SELECT last_insert_rowid()");
        return new Bewegung(nr, b.zeitpunkt(), b.art(), b.artikel(), b.menge(),
                b.vonLagerort(), b.nachLagerort(), b.bemerkung());
    }

    /** Bewegungen in Buchungsreihenfolge. Ein Filter mit null bedeutet "alle". */
    public List<Bewegung> protokoll(String artikel, String lagerort) {
        return db.liste("""
                SELECT * FROM bewegung
                WHERE (?1 IS NULL OR artikel = ?1)
                  AND (?2 IS NULL OR von_lagerort = ?2 OR nach_lagerort = ?2)
                ORDER BY nr""", BewegungRepository::lies, artikel, lagerort);
    }

    public boolean gibtEsFuerArtikel(String artikel) {
        return db.zahl("SELECT EXISTS (SELECT 1 FROM bewegung WHERE artikel = ?)", artikel) == 1;
    }

    public boolean gibtEsFuerLagerort(String code) {
        return db.zahl("SELECT EXISTS (SELECT 1 FROM bewegung WHERE von_lagerort = ?1 OR nach_lagerort = ?1)",
                code) == 1;
    }

    private static Bewegung lies(ResultSet rs) throws SQLException {
        return new Bewegung(
                rs.getLong("nr"),
                LocalDateTime.parse(rs.getString("zeitpunkt"), ZEITFORMAT),
                Bewegungsart.valueOf(rs.getString("art")),
                rs.getString("artikel"),
                rs.getLong("menge"),
                rs.getString("von_lagerort"),
                rs.getString("nach_lagerort"),
                rs.getString("bemerkung"));
    }
}
