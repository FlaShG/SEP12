package de.uni_hamburg.informatik.sep.zuul.server.npcs;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.server.util.TickListener;

public class Katze implements TickListener
{
	public static final int KATZE_SCHADEN = 2;
	Raum _raum;
	boolean _satt = true;

	public Katze(Raum startRaum)
	{
		_raum = startRaum;
	}

	@Override
	public void tick(ServerKontext kontext)
	{
		// TODO: fix me.
		if(_raum != null)
		{
			_raum.setKatze(null);
			_raum = bewegeKatze(kontext, _raum);
			_raum.setKatze(this);
		}
	}

	// TODO: Wenn ein Spieler den Raum verlässt, -> Schaden
	
	/**
	 * 
	 */
	private Raum bewegeKatze(ServerKontext kontext, Raum raum)
	{
		// Normaler Weise zwei Felder weiter, es sei denn, im ersten Feld ist
		// der Spieler
		Raum neuerRaum1 = FancyFunction.getRandomEntry(raum.getAusgaenge());

		// Katze auf Spieler getroffen?
		if(istEinSpielerImRaum(kontext, neuerRaum1))
			return neuerRaum1;

		ArrayList<Raum> ausgaenge = neuerRaum1.getAusgaenge();

		// Nicht in den aktuellen Raum zurückgehen !
		ausgaenge.remove(raum);

		Raum neuerRaum2 = FancyFunction.getRandomEntry(ausgaenge);
		if(neuerRaum2 == null)
			return neuerRaum1;
		return neuerRaum2;
	}

	private boolean istEinSpielerImRaum(ServerKontext kontext, Raum raum)
	{
		List<Raum> raeume = kontext.getRaeumeInDemSichSpielerAufhalten();
		return raeume.contains(raum);
	}

	public void fuettere(ServerKontext kontext, Spieler spieler, Item kuchen)
	{
		switch (kuchen)
		{
		case Giftkuchen:
			// TODO: Katze vom Tick deregistieren.
			_raum.setKatze(null);
			_raum = null;
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KATZE_STIRBT);
			break;
		case Kuchen:
			_satt = true;
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KATZE_IST_SATT_GEWORDEN);
		}
	}

	public Raum getRaum()
	{
		return _raum;
	}

	public boolean isSatt()
	{
		return _satt;
	}
}
