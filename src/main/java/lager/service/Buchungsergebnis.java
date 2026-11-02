package lager.service;

import lager.model.Artikelbestand;
import lager.model.Bewegung;

/**
 * Ergebnis einer Buchung: die gespeicherte Bewegung, der neue Gesamtbestand des Artikels und
 * ob eine Mindestbestand-Warnung angezeigt werden muss.
 */
public record Buchungsergebnis(Bewegung bewegung, Artikelbestand bestand, boolean mindestbestandWarnung) {
}
