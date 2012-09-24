package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

/**
 * Hält alle angemeldeten Spieler und kennt den Spielzustand. Dabei hat er
 * Wissen über die Räume und den Levelaufbau (die Raumstruktur).
 * 
 * @author 0ortmann, 0Schlund
 * 
 */
public class ServerKontext
{

	private Map<Spieler, Raum> _spielerPosition;
	private Raum _startRaum;
	private RaumStruktur _raumStruktur;
	private ArrayList<TickListener> _tickListeners = new ArrayList<TickListener>();
	private ArrayList<BefehlAusgefuehrtListener> _befehlAusgefuehrtListeners = new ArrayList<BefehlAusgefuehrtListener>();


	public ServerKontext(RaumStruktur raumStruktur)
	{
		_startRaum = raumStruktur.getStartRaum();
		_raumStruktur = raumStruktur;
		_spielerPosition = new HashMap<Spieler, Raum>();
	}

	/**
	 * Gib alle registrierten Spieler in einer Liste aus.
	 * 
	 * @return Liste aller Spieler
	 */
	public List<Spieler> getSpielerListe()
	{
		return new ArrayList<Spieler>(_spielerPosition.keySet());
	}

	/**
	 * Gibt die Nachricht für den Spieler Spieler.
	 * 
	 * @param spieler
	 * @return
	 */
	public String getNachrichtFuer(Spieler spieler)
	{
		return "";
		// TODO impl !!!!
	}

	public RaumStruktur getRaumStruktur()
	{
		return _raumStruktur;
	}

	
	/**
	 * @return the spielerPosition
	 */
	public Map<Spieler, Raum> getSpielerPosition()
	{
		return _spielerPosition;
	}

	/**
	 * @return the startRaum
	 */
	public Raum getStartRaum()
	{
		return _startRaum;
	}

	/**
	 * @return the tickListeners
	 */
	public ArrayList<TickListener> getTickListeners()
	{
		return _tickListeners;
	}

	/**
	 * @return the befehlAusgefuehrtListeners
	 */
	public ArrayList<BefehlAusgefuehrtListener> getBefehlAusgefuehrtListeners()
	{
		return _befehlAusgefuehrtListeners;
	}
}
