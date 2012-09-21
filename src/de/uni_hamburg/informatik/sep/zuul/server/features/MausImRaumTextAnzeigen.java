package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public final class MausImRaumTextAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{

	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler,
			boolean hasRoomChanged)
	{
		// Maus
		if(hasRoomChanged && kontext.getAktuellenRaumZu(spieler).hasMaus())
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.MAUS_GEFUNDEN);
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.MAUS_FRAGE);
		}
		return true;
	}
}