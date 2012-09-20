package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Arrays;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.features.KuchenImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTasche implements Befehl
{

	private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN + " "
			+ TextVerwalter.ORT_BODEN;

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		List<Item> items = Arrays.asList(spieler.getInventar()
				.getInhaltsliste());
		boolean kuchenImInventar = items.contains(Item.Kuchen)
				|| items.contains(Item.Giftkuchen);
		return befehlszeile.getZeile().equals(BEFEHLSNAME) && kuchenImInventar;
		// && befehlszeile.getGeparsteZeile().size() <= 3;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		//		String itemParam = null;
		//		if(befehlszeile.getGeparsteZeile().size() == 3)
		//		{
		//			itemParam = befehlszeile.getGeparsteZeile().get(2);
		//		}
		//
		//		if(itemParam != null && !itemParam.equals("kuchen"))
		//		{
		//			Spiel.getInstance().schreibeNL("Das können sie nicht essen...");
		//			return false;
		//		}
		Item item = spieler.getInventar().nehmeLetztesItem();

		int energie = spieler.getLebensEnergie();

		switch (item)
		{
		case Kuchen:
			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.kuchengegessentext(energie));
			break;
		case Giftkuchen:
			energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.giftkuchengegessentext(energie));
			if(energie > 0)
			{
				BefehlFactory.schreibeNL(kontext, spieler,
						TextVerwalter.giftkuchengegessentext(energie));
			}
			else
			{
				BefehlFactory.beendeSpielFuer(kontext, spieler,
						TextVerwalter.KUCHENTODTEXT);
			}
			break;
		}

		spieler.setLebensEnergie(energie);
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.NICHTSZUMESSENTEXT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}

}
