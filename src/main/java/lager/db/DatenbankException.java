package lager.db;

/** Technischer Fehler beim Zugriff auf die SQLite-Datenbank. */
public class DatenbankException extends RuntimeException {

    public DatenbankException(String meldung, Throwable ursache) {
        super(meldung, ursache);
    }
}
