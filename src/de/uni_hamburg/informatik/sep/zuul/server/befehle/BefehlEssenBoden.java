package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenBoden extends BefehlEssen
{

	private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN + " "
			+ TextVerwalter.ORT_BODEN;

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);
		boolean hasKuchen = false;
		for(Item item : aktuellerRaum.getItems())
		{
			if(isKuchen(item))
			{
				hasKuchen = true;
				break;
			}
		}
		return befehlszeile.getZeile().equals(BEFEHLSNAME) && hasKuchen;
	}

	/**
	 * @param item
	 * @return
	 */
	private boolean isKuchen(Item item)
	{
		return item == Item.IGiftkuchen || item == Item.UGiftkuchen
				|| item == Item.IKuchen || item == Item.UKuchen;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);
		Item kuchen = aktuellerRaum.getNaechstesItem();
		int energie = spieler.getLebensEnergie();

		if(kuchen.isKuchen())
		{
			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			aktuellerRaum.loescheItem();
			kontext.schreibeAnSpieler(spieler,
					TextVerwalter.kuchenVomBodenGegessenText(energie));
			if(aktuellerRaum.getNaechstesItem() != Item.Keins)
			{
				kontext.schreibeAnSpieler(spieler,
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}
		}

		if(kuchen.isGiftkuchen())
		{

			energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
			aktuellerRaum.loescheItem();
			if(energie > 0)
			{
				kontext.schreibeAnSpieler(spieler,
						TextVerwalter.giftkuchenVomBodenGegessenText(energie));
			}
			else
			{
				spieler.die();
				kontext.schreibeAnSpieler(spieler, TextVerwalter.KUCHENTODTEXT);
			}
		}
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler,
				TextVerwalter.NICHTSZUMESSENTEXTBODEN);

	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}
}
