package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlAblegenGuterKruemel extends BefehlAblegen
{

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Item kuchen = spieler.getInventar().getKuchen(Item.IKuchen);
		legeItemInAktuellenRaum(kontext, spieler, kuchen);

		return true;
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_ABLEGEN + " guter krümel" };
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext serverKontext,
			Spieler spieler, Befehlszeile befehlszeile)
	{
		return spieler.getInventar().has(Item.IKuchen);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.NICHTS_ZUM_ABLEGEN
				+ " guter krümel");
	}
}
