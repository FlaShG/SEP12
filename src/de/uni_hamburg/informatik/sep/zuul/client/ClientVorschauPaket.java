package de.uni_hamburg.informatik.sep.zuul.client;

import java.util.ArrayList;
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

public class ClientVorschauPaket extends ClientPaket
{
	private int _raumID;
	private boolean _katze;
	private boolean _maus;
	private Collection<Item> _items;
	private String _nachricht; // Terminal ausgabe
	private int _lebensEnergie;
	private String _richtung;
	private List<String> _andereSpieler;
	private RaumArt _raumArt;
	private String _spielerName;
	private String[] _moeglicheAusgaenge;
	private Map<String, Boolean> _verfuegbareBefehle;

	public ClientVorschauPaket(ServerKontext kontext, Spieler spieler,
			String nachricht, String richtung)
	{
		super(kontext, spieler, richtung); // eigentlich unnötig, aber gut. was solls
		
		Raum vorschauRaum = kontext.getAktuellenRaumZu(spieler).getAusgang(richtung);

		_raumID = vorschauRaum.getId();
		_katze = vorschauRaum.hasKatze();
		_maus = vorschauRaum.hasMaus();
		_items = new ArrayList<Item>(vorschauRaum.getItems());
		_nachricht = nachricht;
		_lebensEnergie = spieler.getLebensEnergie();
		_andereSpieler = kontext.getSpielerNamenInRaum(vorschauRaum);
		_raumArt = vorschauRaum.getRaumart();
		_richtung = richtung;
		_spielerName = spieler.getName();
		_moeglicheAusgaenge = vorschauRaum.getMoeglicheAusgaenge();

		_verfuegbareBefehle = new HashMap<String, Boolean>();
		for(Entry<String, Befehl> entry : BefehlFactory.getMap().entrySet())
		{
			String befehlsname = entry.getKey();
			Befehl befehl = entry.getValue();

			_verfuegbareBefehle.put(befehlsname, befehl.vorbedingungErfuellt(
					kontext, spieler, new Befehlszeile(befehlsname)));
		}
	}

	@Override
	public boolean hasKatze()
	{
		return _katze;
	}

	@Override
	public boolean hasMaus()
	{
		return _maus;
	}

	@Override
	public Collection<Item> getItems()
	{
		return _items;
	}

	@Override
	public String getNachricht()
	{
		return _nachricht;
	}

	@Override
	public int getLebensEnergie()
	{
		return _lebensEnergie;
	}

	@Override
	public List<String> getAndereSpieler()
	{
		return _andereSpieler;
	}

	@Override
	public RaumArt getRaumArt()
	{
		return _raumArt;
	}

	@Override
	public String getSpielerName()
	{
		return _spielerName;
	}

	@Override
	public String[] getMoeglicheAusgaenge()
	{
		return _moeglicheAusgaenge;
	}

	@Override
	public int getRaumID()
	{
		return _raumID;
	}
	
	public String getRichtung()
	{
		return _richtung;
	}

	/**
	 * @return the verfuegbareBefehle
	 */
	@Override
	public Map<String, Boolean> getVerfuegbareBefehle()
	{
		return _verfuegbareBefehle;
	}
}
