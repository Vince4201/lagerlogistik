package lager.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import lager.db.ArtikelRepository;
import lager.db.BestandRepository;
import lager.db.BewegungRepository;
import lager.db.Datenbank;
import lager.db.LagerortRepository;
import lager.model.Artikel;
import lager.model.Artikelbestand;
import lager.model.Bestandsposten;
import lager.model.Bewegung;
import lager.model.Bewegungsart;
import lager.model.Lagerort;

/**
 * Fachlogik der Lagerverwaltung. Jede Buchung wird hier geprüft und in einer Transaktion gespeichert.
 *
 * <p>Harte Regeln führen zu einer {@link LagerException}, dann wird nichts gebucht: Die Menge ist
 * größer als 0, Artikel und Lagerorte gibt es und sie sind aktiv, kein Lagerort wird negativ.
 * Die weiche Regel zum Mindestbestand bucht trotzdem und meldet eine Warnung im Ergebnis.
 */
public final class LagerService {

    private final Datenbank db;
    private final ArtikelRepository artikelRepo;
    private final LagerortRepository lagerortRepo;
    private final BewegungRepository bewegungRepo;
    private final BestandRepository bestandRepo;

    public LagerService(Datenbank db) {
        this.db = db;
        this.artikelRepo = new ArtikelRepository(db);
        this.lagerortRepo = new LagerortRepository(db);
        this.bewegungRepo = new BewegungRepository(db);
        this.bestandRepo = new BestandRepository(db);
    }

    // --- Buchungen ----------------------------------------------------------------------------

    public Buchungsergebnis eingang(String artikelNr, String nachLagerort, long menge, String bemerkung) {
        return buchen(Bewegungsart.EINGANG, artikelNr, menge, null, nachLagerort, bemerkung);
    }

    public Buchungsergebnis ausgang(String artikelNr, String vonLagerort, long menge, String bemerkung) {
        return buchen(Bewegungsart.AUSGANG, artikelNr, menge, vonLagerort, null, bemerkung);
    }

    public Buchungsergebnis umlagerung(String artikelNr, String vonLagerort, String nachLagerort, long menge,
                                       String bemerkung) {
        if (vonLagerort.equals(nachLagerort)) {
            throw new LagerException("Quell- und Ziel-Lagerort müssen verschieden sein.");
        }
        return buchen(Bewegungsart.UMLAGERUNG, artikelNr, menge, vonLagerort, nachLagerort, bemerkung);
    }

    /** Inventur: Der gezählte Bestand wird übernommen, gebucht wird die Differenz zum bisherigen Bestand. */
    public Buchungsergebnis korrektur(String artikelNr, String lagerort, long gezaehlt, String begruendung) {
        if (gezaehlt < 0) {
            throw new LagerException("Der gezählte Bestand kann nicht negativ sein.");
        }
        if (begruendung == null || begruendung.isBlank()) {
            throw new LagerException("Eine Korrektur braucht eine Begründung.");
        }
        return db.transaktion(() -> {
            long differenz = gezaehlt - bestandRepo.bestand(artikelNr, lagerort);
            if (differenz == 0) {
                throw new LagerException("Gezählter und gebuchter Bestand stimmen überein, es gibt nichts zu korrigieren.");
            }
            return differenz > 0
                    ? buchen(Bewegungsart.KORREKTUR, artikelNr, differenz, null, lagerort, begruendung)
                    : buchen(Bewegungsart.KORREKTUR, artikelNr, -differenz, lagerort, null, begruendung);
        });
    }

    private Buchungsergebnis buchen(Bewegungsart art, String artikelNr, long menge, String von, String nach,
                                    String bemerkung) {
        if (menge <= 0) {
            throw new LagerException("Die Menge muss größer als 0 sein.");
        }
        return db.transaktion(() -> {
            Artikel artikel = aktiverArtikel(artikelNr);
            if (nach != null) {
                aktiverLagerort(nach);
            }
            if (von != null) {
                aktiverLagerort(von);
                long vorhanden = bestandRepo.bestand(artikelNr, von);
                if (menge > vorhanden) {
                    throw new LagerException("An " + von + " liegen nur " + vorhanden + " " + artikel.einheit()
                            + " von Artikel " + artikelNr + ".");
                }
            }
            Bewegung bewegung = bewegungRepo.speichern(new Bewegung(0, jetzt(), art, artikelNr, menge, von, nach,
                    bemerkung == null ? "" : bemerkung.strip()));
            var bestand = new Artikelbestand(artikel, bestandRepo.gesamtbestand(artikelNr));
            // Mindestbestand nur prüfen, wenn die Buchung den Gesamtbestand senkt.
            // Eine Umlagerung ändert ihn nicht und löst deshalb nie eine Warnung aus.
            boolean warnung = bewegung.aenderungGesamtbestand() < 0 && bestand.unterMindestbestand();
            return new Buchungsergebnis(bewegung, bestand, warnung);
        });
    }

    private static LocalDateTime jetzt() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    // --- Bestände und Auswertungen ------------------------------------------------------------

    public long gesamtbestand(String artikelNr) {
        return bestandRepo.gesamtbestand(artikelNr);
    }

    public long bestand(String artikelNr, String lagerort) {
        return bestandRepo.bestand(artikelNr, lagerort);
    }

    /** Bestände ungleich 0. Ein Filter mit null bedeutet "alle". */
    public List<Bestandsposten> bestaende(String artikelNr, String lagerort) {
        return bestandRepo.posten(artikelNr, lagerort);
    }

    /** Alle aktiven Artikel mit ihrem Gesamtbestand. */
    public List<Artikelbestand> artikelUebersicht() {
        return bestandRepo.artikelbestaende();
    }

    /** Aktive Artikel, deren Gesamtbestand unter dem Mindestbestand liegt. */
    public List<Artikelbestand> unterMindestbestand() {
        return artikelUebersicht().stream().filter(Artikelbestand::unterMindestbestand).toList();
    }

    /** Bewegungen in Buchungsreihenfolge. Ein Filter mit null bedeutet "alle". */
    public List<Bewegung> protokoll(String artikelNr, String lagerort) {
        return bewegungRepo.protokoll(artikelNr, lagerort);
    }

    // --- Stammdaten: Artikel ------------------------------------------------------------------

    public Optional<Artikel> findeArtikel(String nummer) {
        return artikelRepo.finde(nummer);
    }

    public List<Artikel> artikelListe(boolean auchInaktive) {
        return artikelRepo.alle(auchInaktive);
    }

    public Artikel artikelAnlegen(String nummer, String bezeichnung, String einheit, long mindestbestand) {
        pruefeMindestbestand(mindestbestand);
        return db.transaktion(() -> {
            if (artikelRepo.finde(nummer).isPresent()) {
                throw new LagerException("Artikel " + nummer + " gibt es schon.");
            }
            var neu = new Artikel(nummer, bezeichnung, einheit, mindestbestand, true);
            artikelRepo.einfuegen(neu);
            return neu;
        });
    }

    public Artikel artikelAendern(String nummer, String bezeichnung, String einheit, long mindestbestand) {
        pruefeMindestbestand(mindestbestand);
        return db.transaktion(() -> {
            var neu = new Artikel(nummer, bezeichnung, einheit, mindestbestand, vorhandenerArtikel(nummer).aktiv());
            artikelRepo.aendern(neu);
            return neu;
        });
    }

    public void artikelReaktivieren(String nummer) {
        db.transaktion(() -> {
            artikelRepo.aendern(vorhandenerArtikel(nummer).mitAktiv(true));
        });
    }

    /** Sagt, ob der Artikel gelöscht oder nur deaktiviert werden kann, ohne etwas zu ändern. */
    public Entfernen artikelEntfernenPruefen(String nummer) {
        Artikel artikel = vorhandenerArtikel(nummer);
        if (!artikel.aktiv()) {
            throw new LagerException("Artikel " + nummer + " ist bereits deaktiviert.");
        }
        long gesamt = bestandRepo.gesamtbestand(nummer);
        if (gesamt != 0) {
            throw new LagerException("Artikel " + nummer + " hat noch " + gesamt + " " + artikel.einheit()
                    + " Bestand. Erst ausbuchen oder per Korrektur auf 0 setzen.");
        }
        return bewegungRepo.gibtEsFuerArtikel(nummer) ? Entfernen.DEAKTIVIEREN : Entfernen.LOESCHEN;
    }

    public Entfernen artikelEntfernen(String nummer) {
        return db.transaktion(() -> {
            Entfernen wie = artikelEntfernenPruefen(nummer);
            if (wie == Entfernen.LOESCHEN) {
                artikelRepo.loeschen(nummer);
            } else {
                artikelRepo.aendern(vorhandenerArtikel(nummer).mitAktiv(false));
            }
            return wie;
        });
    }

    // --- Stammdaten: Lagerorte ----------------------------------------------------------------

    public Optional<Lagerort> findeLagerort(String code) {
        return lagerortRepo.finde(code);
    }

    public List<Lagerort> lagerortListe(boolean auchInaktive) {
        return lagerortRepo.alle(auchInaktive);
    }

    public Lagerort lagerortAnlegen(String code, String bezeichnung) {
        return db.transaktion(() -> {
            if (lagerortRepo.finde(code).isPresent()) {
                throw new LagerException("Lagerort " + code + " gibt es schon.");
            }
            var neu = new Lagerort(code, bezeichnung, true);
            lagerortRepo.einfuegen(neu);
            return neu;
        });
    }

    public Lagerort lagerortAendern(String code, String bezeichnung) {
        return db.transaktion(() -> {
            var neu = new Lagerort(code, bezeichnung, vorhandenerLagerort(code).aktiv());
            lagerortRepo.aendern(neu);
            return neu;
        });
    }

    public void lagerortReaktivieren(String code) {
        db.transaktion(() -> {
            lagerortRepo.aendern(vorhandenerLagerort(code).mitAktiv(true));
        });
    }

    /** Sagt, ob der Lagerort gelöscht oder nur deaktiviert werden kann, ohne etwas zu ändern. */
    public Entfernen lagerortEntfernenPruefen(String code) {
        Lagerort lagerort = vorhandenerLagerort(code);
        if (!lagerort.aktiv()) {
            throw new LagerException("Lagerort " + code + " ist bereits deaktiviert.");
        }
        int artikelAnzahl = bestandRepo.posten(null, code).size();
        if (artikelAnzahl > 0) {
            throw new LagerException("An Lagerort " + code + (artikelAnzahl == 1 ? " liegt" : " liegen")
                    + " noch " + artikelAnzahl + " Artikel. Erst umlagern oder ausbuchen.");
        }
        return bewegungRepo.gibtEsFuerLagerort(code) ? Entfernen.DEAKTIVIEREN : Entfernen.LOESCHEN;
    }

    public Entfernen lagerortEntfernen(String code) {
        return db.transaktion(() -> {
            Entfernen wie = lagerortEntfernenPruefen(code);
            if (wie == Entfernen.LOESCHEN) {
                lagerortRepo.loeschen(code);
            } else {
                lagerortRepo.aendern(vorhandenerLagerort(code).mitAktiv(false));
            }
            return wie;
        });
    }

    // --- Hilfsmethoden ------------------------------------------------------------------------

    private Artikel vorhandenerArtikel(String nummer) {
        return artikelRepo.finde(nummer)
                .orElseThrow(() -> new LagerException("Artikel " + nummer + " gibt es nicht."));
    }

    private Artikel aktiverArtikel(String nummer) {
        Artikel artikel = vorhandenerArtikel(nummer);
        if (!artikel.aktiv()) {
            throw new LagerException("Artikel " + nummer + " ist deaktiviert.");
        }
        return artikel;
    }

    private Lagerort vorhandenerLagerort(String code) {
        return lagerortRepo.finde(code)
                .orElseThrow(() -> new LagerException("Lagerort " + code + " gibt es nicht."));
    }

    private Lagerort aktiverLagerort(String code) {
        Lagerort lagerort = vorhandenerLagerort(code);
        if (!lagerort.aktiv()) {
            throw new LagerException("Lagerort " + code + " ist deaktiviert.");
        }
        return lagerort;
    }

    private static void pruefeMindestbestand(long mindestbestand) {
        if (mindestbestand < 0) {
            throw new LagerException("Der Mindestbestand kann nicht negativ sein.");
        }
    }
}
