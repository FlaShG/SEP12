package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * Legt ein Item in den aktuellen Raum, wenn eines im INventar vorhanden ist.
 * 
 * @author 1fechner
 * 
 */
final class BefehlAblegen implements Befehl
{

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.getAktuellenRaumZu(spieler).addItem(
				spieler.getInventar().getAnyKuchen());
		BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.ABLEGEN_TEXT);

		return true;
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_ABLEGEN };
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext serverKontext,
			Spieler spieler, Befehlszeile befehlszeile)
	{
		return spieler.getInventar().isGefuellt()
				&& befehlszeile.getZeile().equals(TextVerwalter.BEFEHL_ABLEGEN);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.NICHTS_ZUM_ABLEGEN);
	}
}
