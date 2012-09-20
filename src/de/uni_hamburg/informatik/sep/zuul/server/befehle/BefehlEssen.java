package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlEssen implements Befehl
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
		BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.KEINORT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] {TextVerwalter.BEFEHL_ESSEN };
	}
}
