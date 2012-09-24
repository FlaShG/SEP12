package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettere implements Befehl
{
	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		// Wenn eine Katze oder eine Maus gefüttert werden könnte
		Raum raum = kontext.getAktuellenRaumZu(spieler);
		return raum.hasKatze() || raum.hasMaus();
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		// Versuche eine Katze oder eine Maus zu füttern

		Raum raum = kontext.getAktuellenRaumZu(spieler);
		Befehl befehl = null;
		if(raum.hasKatze())
			befehl = BefehlFactory.gibBefehl(BefehlFuettereKatze.class);
		if(raum.hasMaus())
			befehl = BefehlFactory.gibBefehl(BefehlFuettereMaus.class);
		return Spiel.versucheBefehlAusfuehrung(kontext, spieler,
				befehlszeile, befehl);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		// Vorbedingung nicht erfüllt, daher ist kein Tier hier im Raum.
		kontext.schreibeAnSpieler(spieler, TextVerwalter.BEFEHL_FEED_NICHTS_DA_ZUM_FUETTERN);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_FEED };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_FEED;
	}

}
