package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTascheUnbekannterKruemel implements Befehl
{
private static final String BEFEHLSNAME = TextVerwalter.BEFEHL_ESSEN_TASCHE_UNBEKANNT;
	

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return befehlszeile.getZeile().equals(BEFEHLSNAME)
				&& (spieler.getInventar().has(Item.UKuchen)||spieler.getInventar().has(Item.UGiftkuchen));
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		esseUnbekannterKruemel(kontext, spieler);
		return true;
	}

	private void esseUnbekannterKruemel(ServerKontext kontext, Spieler spieler)
	{
		Item kuchen = spieler.getInventar().getAnyUKuchen();
		if(kuchen == Item.UGiftkuchen)
		{
			spieler.setLebensEnergie(spieler.getLebensEnergie()
					- SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST);
			kontext.schreibeAnSpieler(spieler, TextVerwalter
			.kuchengegessentext(spieler.getLebensEnergie()));
		}
		else if(kuchen == Item.UKuchen) 
		{
			spieler.setLebensEnergie(spieler.getLebensEnergie()
					+ SpielLogik.KUCHEN_ENERGIE_GEWINN);
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
