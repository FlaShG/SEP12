package de.uni_hamburg.informatik.sep.zuul.spiel;



import java.awt.Image;
import java.awt.Toolkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.List;

import javax.imageio.ImageIO;

import sun.awt.image.ImageRepresentation;
import sun.awt.image.ToolkitImage;


import de.uni_hamburg.informatik.sep.zuul.ISchreiber;

public class SpielKontext
{
	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	private Raum _aktuellerRaum;
	private BufferedImage _aktuelleRaumansicht;
	private boolean _spielZuende;
	private int _lebensEnergie;
	private final ISchreiber _schreiber;
	private Inventar _inventar;

	public SpielKontext(ISchreiber schreiber)
	{
		_schreiber = schreiber;
		setLebensEnergie(START_ENERGIE);
		_inventar = new Inventar();
		legeRaeumeAn();
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
		IOManager manager = new IOManager();
		manager.readLevel("./xml_dateien/testStruktur.xml");
		//TODO: noch statisch - datei mit filechooser auswählen!!

		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		RaumBauer raumbauer = new RaumBauer(struktur);
		_aktuellerRaum = raumbauer.getStartRaum();
	}

	/**
	 * Gibt den aktuellen Raum zurück, in dem sich der Spieler befindet.
	 * 
	 * @return
	 */
	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
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
		_aktuellerRaum = aktuellerRaum;
		zeigeRaumbeschreibung();
		raumBetreten();

		if(!isSpielZuende())
		{
			zeigeAusgaenge();
		}
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

	public static boolean IsRaumZielRaum(Raum raum)
	{
		return raum.getNaechstesItem() == Item.Gegengift;
	}
	
	/**
	 * Arbeitet alle Ereignisse ab, die beim Betrete eines Raumes auftreten
	 * können, wie z.B. das Finden und Essen von Items.
	 */
	private void raumBetreten()
	{


		aktualisiereRaumansicht();
		

		if(SpielKontext.IsRaumZielRaum(getAktuellerRaum()))

		{
			beendeSpiel(TextVerwalter.SIEGTEXT + "\n"
					+ TextVerwalter.BEENDENTEXT);
			return;
		}

		_lebensEnergie -= RAUMWECHSEL_ENERGIE_KOSTEN;
		schreibeNL(TextVerwalter.RAUMWECHSELTEXT + _lebensEnergie);

		switch (getAktuellerRaum().getNaechstesItem())
		{
		case Kuchen:
		case Giftkuchen:
			schreibeNL(TextVerwalter.KUCHENIMRAUMTEXT);
			break;
		}

		// Maus
		if(getAktuellerRaum().hasMaus())
		{
			schreibeNL(TextVerwalter.MAUS_GEFUNDEN);
			schreibeNL(TextVerwalter.MAUS_FRAGE);
		}

		if(!isSpielZuende() && _lebensEnergie <= 0)
		{
			beendeSpiel(TextVerwalter.NIEDERLAGETEXT);
		}
	}

	/**
	 * Gibt das Inventar
	 */
	public Inventar getInventar()
	{
		return _inventar;
	}

	public int getLebensEnergie()
	{
		return _lebensEnergie;
	}

	public void setLebensEnergie(int lebensEnergie)
	{
		_lebensEnergie = lebensEnergie;
	}

	private void aktualisiereRaumansicht()
	{
		BufferedImage raum = ladeBild("Z:\\SEP\\test.png");
		BufferedImage maus = null;

		if(_aktuellerRaum.hasMaus())
		{
			maus = ladeBild("Z:\\SEP\\maus.png");

			for(int x = 0; x < maus.getWidth(); x++)
			{
				for(int y = 0; y < maus.getHeight(); y++)
				{
					raum.setRGB(x + 50, y + 25, maus.getRGB(x, y));
				}
			}
		}

		_aktuelleRaumansicht = raum;

	}

	private BufferedImage ladeBild(String pfad)
	{
		File f = new File(pfad);
		BufferedImage img = null;
		try
		{
			 img = ImageIO.read(f);
		}
		catch(IOException e)
		{
			
		}
		
		return img;
		
		
	}
	
	public BufferedImage getAktuelleRaumansicht()
	{
		return _aktuelleRaumansicht;
	}

}
