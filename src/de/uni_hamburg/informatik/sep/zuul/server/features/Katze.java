package de.uni_hamburg.informatik.sep.zuul.server.features;

import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class Katze implements Feature, TickListener
{
	public static final int KATZE_SCHADEN = 2;
	Raum _raum;
	boolean _satt = true;

	private Katze(Raum startRaum)
	{
		_raum = startRaum;
	}

	@Override
	public void tick(ServerKontext kontext)
	{
		_raum.setKatze(null);
		_raum = bewegeKatze(kontext, _raum);
		_raum.setKatze(this);

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

		// Kann sich die Katze nicht bewegen?
		if(neuerRaum1 == null)
			return raum;
		
		// Katze auf Spieler getroffen?
		if(istEinSpielerImRaum(kontext, neuerRaum1))
			return neuerRaum1;

		Raum neuerRaum2 = FancyFunction.getRandomEntry(neuerRaum1.getAusgaenge());
		
		// Hat neuerRaum1 keine Ausgänge?
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
		case IGiftkuchen:
		case UGiftkuchen:
			// TODO: Katze vom Tick deregistieren.
			_raum.setKatze(null);
			_raum = null;
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KATZE_STIRBT);
			break;
		case IKuchen:
		case UKuchen:
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
	
	/**
	 * Erzeugt eine Katze, registriert sie bei der SpielLogik und setzt sie zufällig in einen Raum.
	 */
	public static Katze erzeugeKatze(SpielLogik spielLogik)
	{
		RaumStruktur raumStruktur = spielLogik.getStruktur();
		List<Raum> raeume = raumStruktur.getRaeume();
		Raum raum = FancyFunction.getRandomEntry(raeume);
		
		Katze katze = new Katze(raum);
		raum.setKatze(katze);
		spielLogik.registriereFeature(katze);
		return katze;
	}
}
