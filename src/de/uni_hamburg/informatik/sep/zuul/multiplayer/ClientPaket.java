package de.uni_hamburg.informatik.sep.zuul.multiplayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.ServerKontext;

public class ClientPaket {
	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht; // Terminal ausgabe
	private int _lebensEnergie;
	private List<String> _andereSpieler;
	private RaumArt _raumArt;
	private String _spielerName;

	// setter nötig??

	public ClientPaket(ServerKontext kontext, Spieler spieler) {
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);

		_katze = aktuellerRaum.hasKatze();
		_maus = aktuellerRaum.hasMaus();
		_items = new ArrayList<Item>(aktuellerRaum.getItems());
		_nachricht = kontext.getNachrichtFuer(spieler);
		_lebensEnergie = spieler.getLebensEnergie();
		_andereSpieler = kontext.getSpielerNamenInRaum(aktuellerRaum);
		_raumArt = aktuellerRaum.getRaumart();
		_spielerName = spieler.getName();
	}

	public boolean hasKatze() {
		return _katze;
	}

	public boolean hasMaus() {
		return _maus;
	}

	public Collection<Item> getItems() {
		return _items;
	}

	public String getNachricht() {
		return _nachricht;
	}

	public int getLebensEnergie() {
		return _lebensEnergie;
	}

	public List<String> getAndereSpieler() {
		return _andereSpieler;
	}

	public RaumArt getRaumArt() {
		return _raumArt;
	}

	public String getSpielerName() {
		return _spielerName;
	}

}
