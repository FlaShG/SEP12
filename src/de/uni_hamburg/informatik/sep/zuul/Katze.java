package de.uni_hamburg.informatik.sep.zuul;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Maus;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.spiel.TickListener;

public class Katze implements TickListener, PropertyChangeListener
{
	public static final int KATZE_SCHADEN = 2;
	Raum _raum;
	boolean _satt = true;
	
	public Katze(Raum startRaum)
	{
		_raum = startRaum;
	}

	@Override
	public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
	{
		boolean katzeAufSpielerGetroffen = false;
		
		if(_raum != kontext.getAktuellerRaum()) 
		{
			// Setzt die Katze zwei Felder weiter.
			_raum = bewegeKatze(kontext, _raum);
			
			if(_raum == kontext.getAktuellerRaum())
				katzeAufSpielerGetroffen = true;
		}
		
		// Katze im Raum des Spielers?
		if(katzeAufSpielerGetroffen || (_raum == kontext.getAktuellerRaum() && hasRoomChanged))
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_IM_AKTUELLEN_RAUM);
			if(_raum.hasMaus())
				Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_VERJAGT_DIE_MAUS);
		}

		// Katze im Raum mit einer Maus?
		if(_raum.hasMaus())
		{
			_raum.setMaus(null);
			
			
			// Maus neu platzieren. Alle Räume außer Start, Ende, Maus
			ArrayList<Raum> ausgaenge = _raum.getAusgaenge();
			for( Raum ausgang : (ArrayList<Raum>) ausgaenge.clone())
			{
				if(ausgang.getRaumart() == RaumArt.Start)
					ausgaenge.remove(ausgang);
				if(ausgang.getRaumart() == RaumArt.Ende)
					ausgaenge.remove(ausgang);
				if(ausgang.hasMaus())
					ausgaenge.remove(ausgang);
			}
			if(ausgaenge.size() == 0)
			{
				// panic!
				return true;
			}
			Raum neuerMausRaum = FancyFunction.getRandomEntry(ausgaenge);
			neuerMausRaum.setMaus(new Maus(neuerMausRaum));
			
		}

		return true;
	}

	/**
	 * 
	 */
	private Raum bewegeKatze(SpielKontext kontext, Raum raum)
	{
		// Normaler Weise zwei Felder weiter, es sei denn, im ersten Feld ist der Spieler
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

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		SpielKontext kontext = (SpielKontext) evt.getSource();
		
		if(!_satt && evt.getOldValue().equals(_raum))
		{
			kontext.setLebensEnergie(kontext.getLebensEnergie()-KATZE_SCHADEN);
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_GREIFT_AN);
		}

		_satt = false;
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
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_IST_SATT_GEWORDEN);
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
}
