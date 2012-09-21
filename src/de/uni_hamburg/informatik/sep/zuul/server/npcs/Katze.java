package de.uni_hamburg.informatik.sep.zuul.server.npcs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.util.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class Katze implements TickListener, PropertyChangeListener
{
	public static final int KATZE_SCHADEN = 2;
	Raum _raum;
	boolean _satt = true;

	public Katze(Raum startRaum)
	{
		_raum = startRaum;
	}

	/**
	 * 
	 */
	private Raum bewegeKatze(Raum raum)
	{
		// Normaler Weise zwei Felder weiter, es sei denn, im ersten Feld ist
		// der Spieler
		Raum neuerRaum1 = FancyFunction.getRandomEntry(raum.getAusgaenge());

		// Katze auf Spieler getroffen?
		if(neuerRaum1 == kontext.getAktuellerRaum())
			return neuerRaum1;

		ArrayList<Raum> ausgaenge = neuerRaum1.getAusgaenge();

		// Nicht in den aktuellen Raum zurückgehen !
		ausgaenge.remove(raum);

		Raum neuerRaum2 = FancyFunction.getRandomEntry(ausgaenge);
		if(neuerRaum2 == null)
			return neuerRaum1;
		return neuerRaum2;
	}

	public void fuettere(SpielKontext kontext, Item kuchen)
	{
		switch (kuchen)
		{
		case Giftkuchen:
			deregisterFromKontext(kontext);
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_STIRBT);
			break;
		case Kuchen:
			_satt = true;
			Spiel.getInstance().schreibeNL(
					TextVerwalter.KATZE_IST_SATT_GEWORDEN);
		}
	}

	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
		kontext.addPropertyChangeListener("AktuellerRaum", this);
		kontext.getKatzen().add(this);
	}

	public void deregisterFromKontext(SpielKontext kontext)
	{
		kontext.removeTickListener(this);
		kontext.removePropertyChangeListener("AktuellerRaum", this);
		kontext.getKatzen().remove(this);
	}

	public Raum getRaum()
	{
		// TODO Auto-generated method stub
		return _raum;
	}

	public boolean isSatt()
	{
		// TODO Auto-generated method stub
		return _satt;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
