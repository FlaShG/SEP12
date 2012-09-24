package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public final class MausImRaumTextAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{

	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler, Befehl befehl,
			boolean hasRoomChanged)
	{
		// Maus
		if(hasRoomChanged && kontext.getAktuellenRaumZu(spieler).hasMaus())
		{
			kontext.schreibeAnSpieler(spieler, TextVerwalter.MAUS_GEFUNDEN);
			kontext.schreibeAnSpieler(spieler, TextVerwalter.MAUS_FRAGE);
		}
		return true;
	}
}