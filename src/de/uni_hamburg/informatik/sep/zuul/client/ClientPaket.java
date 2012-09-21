package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class ClientPaket implements Remote, Serializable
{

	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht; // Terminal ausgabe
	private int _lebensEnergie;
	private List<String> _andereSpieler;
	private RaumArt _raumArt;
	private String _spielerName;
	private Raum _aktuellerRaum;

	public ClientPaket(ServerKontext kontext, Spieler spieler)
			throws RemoteException
	{
		 _aktuellerRaum = kontext.getAktuellenRaumZu(spieler);

		_katze = _aktuellerRaum.hasKatze();
		_maus = _aktuellerRaum.hasMaus();
		_items = new ArrayList<Item>(_aktuellerRaum.getItems());
		_nachricht = kontext.getNachrichtFuer(spieler);
		_lebensEnergie = spieler.getLebensEnergie();
		_andereSpieler = kontext.getSpielerNamenInRaum(_aktuellerRaum);
		_raumArt = _aktuellerRaum.getRaumart();
		_spielerName = spieler.getName();
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
	
	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
	}

}
