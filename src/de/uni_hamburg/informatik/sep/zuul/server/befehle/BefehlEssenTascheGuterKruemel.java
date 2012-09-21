package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTascheGuterKruemel implements Befehl
{

	private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN
			+ " tasche guter krümel";

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return befehlszeile.getZeile().equals(BEFEHLSNAME)
				&& spieler.getInventar().hatDiesenKuchen(Item.IKuchen);
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		esseSchlechterKruemel(kontext, spieler);
		return true;
	}

	private void esseSchlechterKruemel(ServerKontext kontext, Spieler spieler)
	{
		if(spieler.getInventar().getKuchen(Item.IKuchen) != null)
		{
			spieler.setLebensEnergie(spieler.getLebensEnergie()
					+ SpielLogik.KUCHEN_ENERGIE_GEWINN);
			BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter
					.kuchengegessentext(spieler.getLebensEnergie()));
		}
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.KEINIDENTIFIZIERTERKUCHEN);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		// TODO Auto-generated method stub
		return new String[] { BEFEHLSNAME };
	}

}
