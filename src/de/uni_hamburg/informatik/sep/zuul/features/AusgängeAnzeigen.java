package de.uni_hamburg.informatik.sep.zuul.features;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final public class AusgängeAnzeigen implements Feature, TickListener
{
	@Override
	public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
	{
		if(hasRoomChanged)
			AusgängeAnzeigen.zeigeAusgaenge(kontext);
		return true;
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
	}

	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an.
	 */
	public static void zeigeAusgaenge(SpielKontext kontext)
	{
		Spiel.getInstance().schreibe(TextVerwalter.AUSGAENGE + ": ");

		for(String s : kontext.getAktuellerRaum().getMoeglicheAusgaenge())
		{
			Spiel.getInstance().schreibe(s + " ");
		}

		Spiel.getInstance().schreibeNL("");
	}
}