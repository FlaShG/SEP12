package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

class BefehlEssenTasche implements Befehl
{

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return spieler.getInventar().hasAnyKuchen();
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Item item = spieler.getInventar().nehmeLetztesItem();

		return esseKuchen(kontext, spieler, item);
	}

	static boolean esseKuchen(ServerKontext kontext, Spieler spieler,
			Item kuchen)
	{
		int energie = spieler.getLebensEnergie();

		switch (kuchen)
		{
		case IKuchen:
		case UKuchen:
			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			kontext.schreibeAnSpieler(spieler,
					TextVerwalter.kuchengegessentext(energie));
			break;
		case IGiftkuchen:
		case UGiftkuchen:
			energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
			if(energie > 0)
			{
				kontext.schreibeAnSpieler(spieler,
						TextVerwalter.giftkuchengegessentext(energie));
			}
			else
			{
				spieler.die();
				kontext.schreibeAnSpieler(spieler, TextVerwalter.KUCHENTODTEXT);
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
		kontext.schreibeAnSpieler(spieler, TextVerwalter.NICHTSZUMESSENTEXT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_ESSEN_TASCHE };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_EAT;
	}

}
