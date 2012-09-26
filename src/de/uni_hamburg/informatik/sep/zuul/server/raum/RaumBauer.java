package de.uni_hamburg.informatik.sep.zuul.server.raum;

import java.util.ArrayList;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class RaumBauer
{
	private Raum _startRaum;
	private Raum _endRaum;
	private Map<Raum, Raum[]> _connections;

	public RaumBauer(RaumStruktur struktur, int anzahlMaeuse)
	{
		// initialisiereRaeumeHart();
		_connections = struktur.getConnections();
		initialisiereRaeume();
		setzeMaeuse(anzahlMaeuse);
	}

	/**
	 * Verbinde alle Räume.
	 * 
	 * @param verbindungen
	 */
	private void initialisiereRaeume()
	{

		for(Raum raum : _connections.keySet())
		{
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN,
					_connections.get(raum)[0], TextVerwalter.RICHTUNG_SUEDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_OSTEN,
					_connections.get(raum)[1], TextVerwalter.RICHTUNG_WESTEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_SUEDEN,
					_connections.get(raum)[2], TextVerwalter.RICHTUNG_NORDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_WESTEN,
					_connections.get(raum)[3], TextVerwalter.RICHTUNG_OSTEN);

			if(raum.getRaumart() == RaumArt.Start)
			{
				_startRaum = raum;
			}
			if(raum.getRaumart() == RaumArt.Ende)
			{
				_endRaum = raum;
			}
		}
	}

	public void mausInRaumSetzen(ArrayList<Raum> kannMausEnthaltenRaum, int i)
	{
		for(; i > 0 && kannMausEnthaltenRaum.size() > 0; --i)
		{
			Raum r = mausInRaumSetzen(kannMausEnthaltenRaum);
			kannMausEnthaltenRaum.remove(r);
		}
	}

	/**
	 * @param kannMausEnthaltenRaum
	 */
	public Raum mausInRaumSetzen(ArrayList<Raum> kannMausEnthaltenRaum)
	{

		Raum mausRaum = FancyFunction.getRandomEntry(kannMausEnthaltenRaum);
		if(mausRaum == null)
			return null;

		mausRaum.setMaus(new Maus(mausRaum, _endRaum));

		return mausRaum;
	}

	/**
	 * Gibt den Startraum zurück, von dem aus der Spieler startet.
	 * 
	 * @return Der Startraum
	 */
	public Raum getStartRaum()
	{
		return _startRaum;
	}

	public void setzeMaeuse(int anzahlMaeuse)
	{
		ArrayList<Raum> kannMausEnthaltenRaum = new ArrayList<Raum>();

		for(Raum raum : _connections.keySet())
		{
			if(raum.getRaumart() != RaumArt.Ende
					&& raum.getRaumart() != RaumArt.Start)
				kannMausEnthaltenRaum.add(raum);
		}

		if(kannMausEnthaltenRaum.size() > 0)
			mausInRaumSetzen(kannMausEnthaltenRaum, anzahlMaeuse);

	}
}
