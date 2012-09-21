package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

public final class GewonnenTextAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{
	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler,
			boolean hasRoomChanged)
	{
		if(kontext.getAktuellenRaumZu(spieler).getNaechstesItem() == Item.Gegengift)
		{
			// TODO: Spiel beenden.
			//			SpielLogik.beendeSpiel(kontext, TextVerwalter.SIEGTEXT + "\n"
			//					+ TextVerwalter.BEENDENTEXT);
			return false;
		}
		return true;
	}
}