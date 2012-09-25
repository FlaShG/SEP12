package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

class BefehlEssen implements Befehl
{
	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return false;
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return false;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.KEINORT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_ESSEN };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_EAT;
	}

	static boolean esseKuchen(ServerKontext kontext, Spieler spieler,
			Item kuchen)
	{
		int energie = spieler.getLebensEnergie();

		if(kuchen.isKuchen())
		{

			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			kontext.schreibeAnSpieler(spieler,
					TextVerwalter.kuchengegessentext(energie));
		}
		else if(kuchen.isGiftkuchen())
		{
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
		}

		spieler.setLebensEnergie(energie);
		return true;
	}
}
