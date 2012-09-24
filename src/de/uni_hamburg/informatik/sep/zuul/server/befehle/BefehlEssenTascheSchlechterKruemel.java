package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTascheSchlechterKruemel implements Befehl
{

	private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN
			+ " tasche schlechter krümel";

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return befehlszeile.getZeile().equals(BEFEHLSNAME)
				&& spieler.getInventar().has(Item.IGiftkuchen);
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		esseGuterKruemel(kontext, spieler);
		return true;
	}

	private void esseGuterKruemel(ServerKontext kontext, Spieler spieler)
	{
		if(spieler.getInventar().getKuchen(Item.IGiftkuchen) != null)
		{
			spieler.setLebensEnergie(spieler.getLebensEnergie()
					- SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST);
			kontext.schreibeAnSpieler(spieler, TextVerwalter
			.kuchengegessentext(spieler.getLebensEnergie()));
		}
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.KEINIDENTIFIZIERTERKUCHEN);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_EAT;
	}

}
