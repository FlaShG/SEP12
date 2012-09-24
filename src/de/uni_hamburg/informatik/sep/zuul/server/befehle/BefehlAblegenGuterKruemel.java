package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlAblegenGuterKruemel implements Befehl
{

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		spieler.getInventar().getKuchen(Item.IKuchen);
		kontext.getAktuellenRaumZu(spieler).addItem(Item.UKuchen);
		kontext.schreibeAnSpieler(spieler, TextVerwalter.ABLEGEN_TEXT);

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
		return spieler.getInventar().has(Item.IKuchen)
				&& befehlszeile.getZeile().equals(TextVerwalter.BEFEHL_ABLEGEN + " guter krümel");
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.NICHTS_ZUM_ABLEGEN + " guter krümel");
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_ABLEGEN;
	}
}
