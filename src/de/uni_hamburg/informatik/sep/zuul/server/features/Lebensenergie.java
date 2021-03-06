package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielKonstanten;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final public class Lebensenergie implements Feature, BefehlAusgefuehrtListener,
		RaumGeaendertListener
{
	/**
	 * Jedes Weitergehen zieht dem {@link Spieler} ein Leben ab.
	 */
	@Override
	public void raumGeaendert(ServerKontext kontext, Spieler spieler,
			Raum alterRaum, Raum neuerRaum)
	{
		spieler.setLebensEnergie(spieler.getLebensEnergie()
				- SpielKonstanten.RAUMWECHSEL_ENERGIE_KOSTEN);
	}

	/**
	 * Überprüft, ob der {@link Spieler} noch lebt.
	 */
	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler,
			Befehl befehl, boolean hasRoomChanged)
	{
		if(spieler.getLebensEnergie() <= 0)
		{
			kontext.schreibeAnSpieler(spieler, TextVerwalter.NIEDERLAGETEXT);
			return false;
		}

		if(hasRoomChanged)
			kontext.schreibeAnSpieler(spieler, TextVerwalter.RAUMWECHSELTEXT
					+ spieler.getLebensEnergie());

		return true;
	}
}