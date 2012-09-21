package de.uni_hamburg.informatik.sep.zuul.client;

import java.util.Collection;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

public class ClientPaket
{
	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht;
	private int _lebensEnergie;
	private String[] _andereSpieler;
	private RaumArt _raumArt;
	private boolean _isSpielZuEnde;

	//setter nötig??

	public ClientPaket(ServerKontext kontext, Spieler spieler)
	{
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);
		_katze = aktuellerRaum.hasKatze();
		_maus = aktuellerRaum.hasMaus();
		_items = (Collection<Item>) aktuellerRaum.getItems().clone();
		// TODO: Nachricht?
		//		_nachricht = spieler.getNachricht();
		_lebensEnergie = spieler.getLebensEnergie();
		// TODO: Andere Spieler
		//		_andereSpieler = kontext.
		_raumArt = aktuellerRaum.getRaumart();
		// TODO: Spiel zu Ende?
		//		_isSpielZuEnde = kontext.isSpielZuEnde();
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

	public String[] getAndereSpieler()
	{
		return _andereSpieler;
	}

	public RaumArt getRaumArt()
	{
		return _raumArt;
	}

}
