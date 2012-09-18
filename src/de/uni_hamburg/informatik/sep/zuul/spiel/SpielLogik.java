package de.uni_hamburg.informatik.sep.zuul.spiel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.ISchreiber;

public class SpielLogik
{
	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	private boolean _spielZuende;
	private final ISchreiber _schreiber;
	private SpielKontext _kontext = new SpielKontext();

	public SpielLogik(ISchreiber schreiber)
	{
		_schreiber = schreiber;
		_kontext.setLebensEnergie(START_ENERGIE);
		_kontext.setInventar(new Inventar());
		legeRaeumeAn();
		
		_kontext.addPropertyChangeListener("AktuellerRaum", new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{				
				zeigeRaumbeschreibung();
				
				if(getAktuellerRaum().getNaechstesItem() == Item.Gegengift)
				{
					beendeSpiel(TextVerwalter.SIEGTEXT + "\n" + TextVerwalter.BEENDENTEXT);
					return;
				}
				
				_kontext.setLebensEnergie(_kontext.getLebensEnergie() - RAUMWECHSEL_ENERGIE_KOSTEN);
				schreibeNL(TextVerwalter.RAUMWECHSELTEXT + _kontext.getLebensEnergie());	
				
				switch(getAktuellerRaum().getNaechstesItem())
				{
					case Kuchen: case Giftkuchen:
						schreibeNL(TextVerwalter.KUCHENIMRAUMTEXT);
					break;
				}
				
				// Maus
				if(getAktuellerRaum().hasMaus())
				{
					schreibeNL(TextVerwalter.MAUS_GEFUNDEN);
					schreibeNL(TextVerwalter.MAUS_FRAGE);
				}
				
				if(!isSpielZuende() && _kontext.getLebensEnergie() <= 0)
				{
					beendeSpiel(TextVerwalter.NIEDERLAGETEXT);
				}
				
				if(!isSpielZuende())
				{
					zeigeAusgaenge();
				}
				
			}
		});
	}

	/**
	 * Schreibt nachricht in den Output, hänge einen Zeilenumbruch an.
	 * Vergleichbar mit PrintStream.println()
	 * 
	 * @param nachricht
	 *            Die auszugebende Nachricht
	 */
	public void schreibeNL(String nachricht)
	{
		_schreiber.schreibeNL(nachricht);
	}

	/**
	 * Schreibt nachricht in den Output. Vergleichbar mit PrintStream.print()
	 * 
	 * @param nachricht
	 *            Die auszugebende Nachricht
	 */
	public void schreibe(String nachricht)
	{
		_schreiber.schreibe(nachricht);
	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private void legeRaeumeAn()
	{
		RaumBauer raumbauer = new RaumBauer();
		_kontext.setAktuellerRaum(raumbauer.getStartRaum());
	}

	/**
	 * Gibt den aktuellen Raum zurück, in dem sich der Spieler befindet.
	 * 
	 * @return
	 */
	public Raum getAktuellerRaum()
	{
		return _kontext.getAktuellerRaum();
	}

	/**
	 * Ändert den aktuellen Raum, in dem sich der Spieler befindet. Zeigt dessen
	 * Beschreibung an, welche Items eingesammelt werden und zum Abschluss die
	 * Ausgänge.
	 * 
	 * @param aktuellerRaum
	 *            der neue Raum, der betreten wird
	 */
	public void setAktuellerRaum(Raum aktuellerRaum)
	{
		_kontext.setAktuellerRaum(aktuellerRaum);
	}

	/**
	 * Gibt zurück, ob das Spiel zuende ist
	 * 
	 * @return true, wenn das Spiel zuende ist
	 */
	public boolean isSpielZuende()
	{
		return _spielZuende;
	}

	/**
	 * Gibt eine Nachricht aus und beendet das Spiel
	 * 
	 * @param nachricht
	 *            die Nachricht, die vor dem Spielende ausgegeben werden soll
	 */
	public void beendeSpiel(String nachricht)
	{
		schreibeNL(nachricht);
		_spielZuende = true;
	}

	/**
	 * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
	 * befindet.
	 */
	public void zeigeRaumbeschreibung()
	{
		schreibeNL(getAktuellerRaum().getBeschreibung());
	}

	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an.
	 */
	public void zeigeAusgaenge()
	{
		schreibe(TextVerwalter.AUSGAENGE + ": ");

		for(String s : getAktuellerRaum().getMoeglicheAusgaenge())
		{
			schreibe(s + " ");
		}

		schreibeNL("");
	}

	public SpielKontext getKontext()
	{
		return _kontext;
	}
}
