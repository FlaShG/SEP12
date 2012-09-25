package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * Legt ein Item in den aktuellen Raum, wenn eines im INventar vorhanden ist.
 * 
 * @author 1fechner
 * 
 */
final class BefehlAblegen implements Befehl
{

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Item item = spieler.getInventar().getAnyKuchen();
		switch (item)
		{
		case IKuchen:
			item = Item.UKuchen;
			break;
		case IGiftkuchen:
			item = Item.UGiftkuchen;
			break;
		}
		kontext.getAktuellenRaumZu(spieler).addItem(item);
		kontext.schreibeAnSpieler(spieler, TextVerwalter.ABLEGEN_TEXT);

		return true;
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_ABLEGEN };
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext serverKontext,
			Spieler spieler, Befehlszeile befehlszeile)
	{
		return spieler.getInventar().hasAnyKuchen()
				&& befehlszeile.getZeile().equals(TextVerwalter.BEFEHL_ABLEGEN);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.NICHTS_ZUM_ABLEGEN);
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_ABLEGEN;
	}
}
