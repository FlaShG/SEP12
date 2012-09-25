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
		
		// TODO: und wenn das item, das gegengift ist?
		if(item == Item.Gegengift){
			kontext.spielerGewinnt(spieler);
			return true;
		}

		kontext.schreibeAnSpieler(spieler, TextVerwalter.KUCHENGENOMMENTEXT);
		raum.loescheItem();
		if(raum.getNaechstesItem() != Item.Keins)
		{
			kontext.schreibeAnSpieler(spieler, TextVerwalter.IMMERNOCHKUCHENTEXT);
		}
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.NICHTSZUMNEHMENTEXT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_NEHMEN };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_TAKE;
	}
}
