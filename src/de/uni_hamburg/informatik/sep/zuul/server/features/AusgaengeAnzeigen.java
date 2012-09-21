package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final public class AusgaengeAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{
	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an.
	 */
	public static String zeigeAusgaenge(Raum raum)
	{
		//TODO: rewrite
		StringBuilder builder = new StringBuilder();
		builder.append(TextVerwalter.AUSGAENGE + ": ");

		for(String s : raum.getMoeglicheAusgaenge())
		{
			builder.append(s + " ");
		}
		return builder.toString();
	}

	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler,
			boolean hasRoomChanged)
	{
		if(hasRoomChanged)
		{
			Raum raum = kontext.getAktuellenRaumZu(spieler);
			String ausgaenge = AusgaengeAnzeigen.zeigeAusgaenge(raum);
			BefehlFactory.schreibeNL(kontext, spieler, ausgaenge);
		}
		return true;
	}
}