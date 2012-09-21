package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlNehmen implements Befehl
{
	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);
		return raum.getItems().size() > 0;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);

		Item item = raum.getNaechstesItem();

		spieler.getInventar().fuegeItemHinzu(item);

		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.KUCHENGENOMMENTEXT);
		raum.loescheItem();
		if(raum.getNaechstesItem() != Item.Keins)
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.IMMERNOCHKUCHENTEXT);
		}
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.NICHTSZUMNEHMENTEXT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_INVENTAR };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_TAKE;
	}
}
