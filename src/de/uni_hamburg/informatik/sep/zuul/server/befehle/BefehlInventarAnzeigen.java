package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * Zeigt den Inhalt des Inventars an. Wenn ein Kuchen oder ein giftiger Kuchen
 * vorhanden ist wird ein Kuchen angezeigt
 * 
 * @author 1fechner
 * 
 */
final class BefehlInventarAnzeigen implements Befehl
{
	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return true;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler, "Ihre Tasche enthält: "
				+ spieler.getInventar().toString());
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
	}

	@Override
	public String[] getBefehlsnamen()
	{
		// TODO Auto-generated method stub
		return new String[] { TextVerwalter.BEFEHL_INVENTAR };
	}

}
