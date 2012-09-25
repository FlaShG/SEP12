package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class ClientPaket implements Remote, Serializable
{
	private int _raumID;
	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht; // Terminal ausgabe
	private int _lebensEnergie;
	private List<String> _andereSpieler;
	private RaumArt _raumArt;
	private String _spielerName;
	private List<String> _moeglicheAusgaenge;
	private Map<String, Boolean> _verfuegbareBefehle;
	private boolean _dead;
	private boolean _showLoseScreen;
	private boolean _showWinScreen;

	public ClientPaket(ServerKontext kontext, Spieler spieler, String nachricht)
	{
		_dead = !spieler.isAlive();
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);
		if(!spieler.isAlive())
		{
			// TODO: win / lose screen
			_showLoseScreen = true;
			_showWinScreen = false;
			_moeglicheAusgaenge = new ArrayList<String>();
			;

		}
		else
		{
			_moeglicheAusgaenge = Arrays.asList(aktuellerRaum
					.getMoeglicheAusgaenge());
		}

		_raumID = aktuellerRaum.getId();
		_katze = aktuellerRaum.hasKatze();
		_maus = aktuellerRaum.hasMaus();
		_items = new ArrayList<Item>(aktuellerRaum.getItems());
		_nachricht = nachricht;
		_lebensEnergie = spieler.getLebensEnergie();
		_andereSpieler = kontext.getSpielerNamenInRaum(aktuellerRaum);
		_raumArt = aktuellerRaum.getRaumart();
		_spielerName = spieler.getName();

		_verfuegbareBefehle = new HashMap<String, Boolean>();
		for(Entry<String, Befehl> entry : BefehlFactory.getMap().entrySet())
		{
			String befehlsname = entry.getKey();
			Befehl befehl = entry.getValue();

			boolean befehlVerfuegbar = _dead ? false : befehl
					.vorbedingungErfuellt(kontext, spieler, new Befehlszeile(
							befehlsname));

			_verfuegbareBefehle.put(befehlsname, befehlVerfuegbar);
		}
	}

	public boolean hasKatze()
	{
		return _katze;
	}

	public boolean hasMaus()
	{
		return _maus;
	}

	public Collection<Item> getItems()
	{
		return _items;
	}

	public String getNachricht()
	{
		return _nachricht;
	}

	public int getLebensEnergie()
	{
		return _lebensEnergie;
	}

	public List<String> getAndereSpieler()
	{
		return _andereSpieler;
	}

	public RaumArt getRaumArt()
	{
		return _raumArt;
	}

	public String getSpielerName()
	{
		return _spielerName;
	}

	public List<String> getMoeglicheAusgaenge()
	{
		return _moeglicheAusgaenge;
	}

	public int getRaumID()
	{
		return _raumID;
	}

	/**
	 * @return the verfuegbareBefehle
	 */
	public Map<String, Boolean> getVerfuegbareBefehle()
	{
		return _verfuegbareBefehle;
	}

	/**
	 * @return the showLoseScreen
	 */
	public boolean isShowLoseScreen()
	{
		return _showLoseScreen;
	}

	/**
	 * @return the showWinScreen
	 */
	public boolean isShowWinScreen()
	{
		return _showWinScreen;
	}

	public int buildUniqueID()
	{
		int[] subHashCodes = new int[] { _andereSpieler.hashCode(),
				_items.hashCode(), _katze ? 1 : 0, _maus ? 1 : 0,
				_moeglicheAusgaenge.hashCode(), _raumID, };

		int hashCode = 1;
		for(int code : subHashCodes)
		{
			hashCode = hashCode * 31 + code;
		}
		return hashCode;
	}

}
