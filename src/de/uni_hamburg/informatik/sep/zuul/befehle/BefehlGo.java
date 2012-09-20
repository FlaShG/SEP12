package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.ArrayList;
import java.util.Arrays;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlGo implements Befehl
{
	String[] _shortcuts = new String[] { "n", "w", "s", "e" };
	String[] _richtungen = new String[] { TextVerwalter.RICHTUNG_NORDEN,
			TextVerwalter.RICHTUNG_OSTEN, TextVerwalter.RICHTUNG_SUEDEN,
			TextVerwalter.RICHTUNG_WESTEN };

	@Override
	public boolean ausfuehren(ServerKontext serverKontext, SpielerKontext kontext, Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(richtung);
		kontext.setAktuellerRaum(naechsterRaum);
		
		return true;
	}

	public boolean vorbedingungErfuellt(ServerKontext serverKontext,
			SpielerKontext kontext, Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);
		if(richtung == null)
			return false;

		// Kann man in den Raum gehen?
		Raum raum = kontext.getAktuellerRaum();
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
	public void gibFehlerAus(ServerKontext serverKontext,
			SpielerKontext kontext, Befehlszeile befehlszeile)
	{
		Spiel.getInstance().schreibeNL(TextVerwalter.KEINERICHTUNG);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_GEHEN, "n", "w", "s", "e" };
	}
}