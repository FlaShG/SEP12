package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.ArrayList;
import java.util.Arrays;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlGehe implements Befehl
{
	String[] _shortcuts = new String[] { "n", "w", "s", "o" };
	String[] _richtungen = new String[] { TextVerwalter.RICHTUNG_NORDEN,
			TextVerwalter.RICHTUNG_WESTEN, TextVerwalter.RICHTUNG_SUEDEN,
			TextVerwalter.RICHTUNG_OSTEN };

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);

		Raum naechsterRaum = kontext.getAktuellenRaumZu(spieler).getAusgang(
				richtung);
		kontext.wechseleRaum(spieler, naechsterRaum);

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
		// o,n,s,w
		String richtung = translateShortcut(befehlszeile.getZeile());
		if(richtung != null)
			return richtung;

		// Ist der Befehl eine lange Richtung ?
		// gehe …
		if(befehlszeile.beginntMit(TextVerwalter.BEFEHL_GEHEN)
				&& befehlszeile.getGeparsteZeile().size() == 2)
		{
			richtung = befehlszeile.getGeparsteZeile().get(1);

			// lange Richtung?
			// gehe osten, …
			if(isKorrekteRichtung(richtung))
			{
				return richtung;
			}

			// vllt. Shortcut?
			//gehe o, …
			richtung = translateShortcut(richtung);
			if(richtung != null)
				return richtung;
		}

		return null;
	}

	/**
	 * @param richtung
	 * @return
	 */
	private boolean isKorrekteRichtung(String richtung)
	{
		return Arrays.asList(_richtungen).contains(richtung);
	}

	/**
	 * @param befehlszeile
	 */
	private String translateShortcut(String shortcut)
	{
		int indexOf = Arrays.asList(_shortcuts).indexOf(shortcut);
		if(indexOf >= 0)
			return Arrays.asList(_richtungen).get(indexOf);
		return null;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.KEINERICHTUNG);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		ArrayList<String> befehlsnamen = new ArrayList<String>();
		befehlsnamen.addAll(Arrays.asList(_shortcuts));

		for(String shortcut : _shortcuts)
			befehlsnamen.add(TextVerwalter.BEFEHL_GEHEN + " " + shortcut);
		for(String richtung : _richtungen)
			befehlsnamen.add(TextVerwalter.BEFEHL_GEHEN + " " + richtung);
		befehlsnamen.add("gehe");
		return befehlsnamen.toArray(new String[0]);
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_GO;
	}
}
