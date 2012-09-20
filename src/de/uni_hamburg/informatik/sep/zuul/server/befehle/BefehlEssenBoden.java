package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenBoden implements Befehl
{

	private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN + " "
			+ TextVerwalter.ORT_BODEN;

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return befehlszeile.getZeile().equals(BEFEHLSNAME);
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);
		Item item = aktuellerRaum.getNaechstesItem();
		int energie = spieler.getLebensEnergie();

		switch (item)
		{
		case Kuchen:
			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			aktuellerRaum.loescheItem();
					BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.kuchenVomBodenGegessenText(energie));
			if(aktuellerRaum.getNaechstesItem() != Item.Keins)
			{
						BefehlFactory.schreibeNL(kontext, spieler,
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}

			break;
		case Giftkuchen:
			energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
			aktuellerRaum.loescheItem();
			if(energie > 0)
			{
						BefehlFactory.schreibeNL(kontext, spieler,
						TextVerwalter.giftkuchenVomBodenGegessenText(energie));
			}
			else
			{
				BefehlFactory.beendeSpielFuer(kontext, spieler, TextVerwalter.KUCHENTODTEXT);
			}
			break;
		}

		spieler.setLebensEnergie(energie);
		return true;
		//		return false;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.NICHTSZUMESSENTEXTBODEN);

	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}

}
