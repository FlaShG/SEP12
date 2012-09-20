package de.uni_hamburg.informatik.sep.zuul.multiplayer;

import java.util.ArrayList;
import java.util.Collection;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public class ClientPaket
{
	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht;
	private int _lebensEnergie;
	private String[] _andereSpieler;
	private RaumArt _raumArt;
	
	
	//setter nötig??
	
	boolean aktuellerRaumHatKatze;
	
	public ClientPaket(SpielKontext kontext)
	{
		aktuellerRaumHatKatze = kontext.isKatzeImAktuellenRaum();
		_katze = !kontext.getKatzen().isEmpty();
		_maus = kontext.hasMaus();
		_items = new ArrayList<Item>(kontext.getItems()); 
		_nachricht = kontext.getNachricht();
		_lebensEnergie = kontext.getLebensEnergie();
		_andereSpieler = kontext.getSpielerFuerAktuellenRaum();
		_raumArt = kontext.getRaumArt();
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
