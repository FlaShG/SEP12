package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

class BefehlFuettereUnbekanntenKruemel extends BefehlFuettere
{

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return super.vorbedingungErfuellt(kontext, spieler, befehlszeile)
				&& spieler.getInventar().hasAnyUKuchen();
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Item kuchen = spieler.getInventar().getAnyUKuchen();
		return fuettereTierMit(kontext, spieler, kuchen);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		if(!spieler.getInventar().hasAnyUKuchen())
		{
			kontext.schreibeAnSpieler(spieler, TextVerwalter.MAUS_KEIN_KRUEMEL);
			return;
		}
		super.gibFehlerAus(kontext, spieler, befehlszeile);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_FUETTERE_UNBEKANNT };
	}
}