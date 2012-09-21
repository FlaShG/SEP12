package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Arrays;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlGehe implements Befehl
{
	String[] _shortcuts = new String[] { "n", "w", "s", "e" };
	String[] _richtungen = new String[] { TextVerwalter.RICHTUNG_NORDEN,
			TextVerwalter.RICHTUNG_OSTEN, TextVerwalter.RICHTUNG_SUEDEN,
			TextVerwalter.RICHTUNG_WESTEN };

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);

		Raum naechsterRaum = kontext.getAktuellenRaumZu(spieler).getAusgang(
				richtung);
		kontext.setAktuellenRaumZu(spieler, naechsterRaum);

		return true;
	}

	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);
		if(richtung == null)
			return false;

		// Kann man in den Raum gehen?
		Raum raum = kontext.getAktuellenRaumZu(spieler);
		return Arrays.asList(raum.getMoeglicheAusgaenge()).contains(richtung);
	}

	/**
	 * Extrahiert aus einer Go-Befehlszeile die angegebene Richtung.
	 */
	private String extrahiereRichtung(Befehlszeile befehlszeile)
	{
		// Ist der Befehl ein Shortcut?		
		int indexOf = Arrays.asList(_shortcuts)
				.indexOf(befehlszeile.getZeile());
		if(indexOf >= 0)
			return Arrays.asList(_richtungen).get(indexOf);

		// Ist der Befehl eine lange Richtung ?
		if(befehlszeile.beginntMit(TextVerwalter.BEFEHL_GEHEN)
				&& befehlszeile.getGeparsteZeile().size() == 2)
		{
			// Extrahiere Richtung
			String richtung = befehlszeile.getGeparsteZeile().get(1);
			if(Arrays.asList(_richtungen).contains(richtung))
			{
				return richtung;
			}
		}

		return null;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.KEINERICHTUNG);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_GEHEN, "n", "w", "s", "e" };
	}
}