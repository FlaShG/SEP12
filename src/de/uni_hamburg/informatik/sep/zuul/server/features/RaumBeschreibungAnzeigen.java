package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public final class RaumBeschreibungAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{

	/**
	 * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
	 * befindet.
	 */
	public static void zeigeRaumbeschreibung(ServerKontext kontext,
			Spieler spieler)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				SpielLogik.getAktuellenRaumZu(kontext, spieler).getBeschreibung());
	}

	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler, Befehl befehl,
			boolean hasRoomChanged)
	{
		if(hasRoomChanged)
			RaumBeschreibungAnzeigen.zeigeRaumbeschreibung(kontext, spieler);
		return true;
	}

}