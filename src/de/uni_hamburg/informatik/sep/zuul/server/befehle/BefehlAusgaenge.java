package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlAusgaenge implements Befehl
{

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return true;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		String[] ausgaenge = SpielLogik.getAktuellenRaumZu(kontext, spieler).getMoeglicheAusgaenge();
		String output = "Ausgänge: ";

		for (String s : ausgaenge)
		{
			output += s + " ";
		}
		
		BefehlFactory.schreibeNL(kontext, spieler, output);
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] {"ausgänge"};
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_AUSGAENGE;
	}

}
