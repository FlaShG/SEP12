package de.uni_hamburg.informatik.sep.zuul.server.raum;

import java.util.ArrayList;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class RaumBauer
{
	/**
	 * Verbinde alle Räume.
	 * 
	 * @param verbindungen
	 */
	public static void initialisiereRaeume(RaumStruktur raumStruktur)
	{
		Map<Raum, Raum[]> verbindungen = raumStruktur.getConnections();
		ArrayList<Raum> kannMausEnthaltenRaum = new ArrayList<Raum>();
		Raum endRaum = null;
		for(Raum raum : verbindungen.keySet())
		{
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN,
					verbindungen.get(raum)[0], TextVerwalter.RICHTUNG_SUEDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_OSTEN,
					verbindungen.get(raum)[1], TextVerwalter.RICHTUNG_WESTEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_SUEDEN,
					verbindungen.get(raum)[2], TextVerwalter.RICHTUNG_NORDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_WESTEN,
					verbindungen.get(raum)[3], TextVerwalter.RICHTUNG_OSTEN);

//			if(raum.getRaumart() == RaumArt.Start)
//			{
//				_startRaum = raum;
//			}
			if(raum.getRaumart() == RaumArt.Ende)
			{
				endRaum = raum;
			}
			if(raum.getRaumart() != RaumArt.Ende
					&& raum.getRaumart() != RaumArt.Start)
				kannMausEnthaltenRaum.add(raum);
		}

		// TODO: Maus anzahl auslesen
		//TODO : Move!
		// Setze 3 Mäuse zufällig.

		if(kannMausEnthaltenRaum.size() > 0)
			mausInRaumSetzen(kannMausEnthaltenRaum, 3, endRaum);
	}

	public static void mausInRaumSetzen(ArrayList<Raum> kannMausEnthaltenRaum, int i, Raum endRaum)
	{
		for(; i > 0 && kannMausEnthaltenRaum.size() > 0; --i)
		{
			Raum r = mausInRaumSetzen(kannMausEnthaltenRaum, endRaum);
			kannMausEnthaltenRaum.remove(r);
		}
	}

	/**
	 * @param kannMausEnthaltenRaum
	 */
	private static Raum mausInRaumSetzen(ArrayList<Raum> kannMausEnthaltenRaum, Raum endRaum)
	{

		Raum mausRaum = FancyFunction.getRandomEntry(kannMausEnthaltenRaum);
		if(mausRaum != null)
			return null;

		mausRaum.setMaus(new Maus(mausRaum, endRaum));

		return mausRaum;
	}
}
