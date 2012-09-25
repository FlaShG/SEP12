package de.uni_hamburg.informatik.sep.zuul.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction.SuperFancyReproducibleRandomEntryPicker;

/**
 * 
 * @author 1roebe
 * 
 */
class Raumbilderzeuger
{

	//	private final String PATH = getClass().getResource("bilder/").getPath();

	private final BufferedImage WIN = ladeBild(getClass().getResource(
			"bilder/win.png"));
	private final BufferedImage GAMEOVER = ladeBild(getClass().getResource(
			"bilder/gameover.png"));
	private final BufferedImage RAUMTUERNORD = ladeBild(getClass().getResource(
			"bilder/door_n.png"));
	private final BufferedImage RAUMTUEROST = ladeBild(getClass().getResource(
			"bilder/door_e.png"));
	private final BufferedImage RAUMTUERSUED = ladeBild(getClass().getResource(
			"bilder/door_s.png"));
	private final BufferedImage RAUMTUERWEST = ladeBild(getClass().getResource(
			"bilder/door_w.png"));
	private final BufferedImage SCHAUENSCHATTEN = ladeBild(getClass()
			.getResource("bilder/peek.png"));

	private final BufferedImage MAUS = ladeBild(getClass().getResource(
			"bilder/maus.png"));
	private final BufferedImage KATZE = ladeBild(getClass().getResource(
			"bilder/katze.png"));
	private final BufferedImage KRUEMEL = ladeBild(getClass().getResource(
			"bilder/kruemel.png"));
	private final BufferedImage GEGENGIFT = ladeBild(getClass().getResource(
			"bilder/gegengift.png"));
	private final BufferedImage DRLITTLE = ladeBild(getClass().getResource(
			"bilder/drlittle.png"));
	private final BufferedImage DREVENBIGGER = ladeBild(getClass().getResource(
			"bilder/drevenbigger.png"));

	private LinkedList<Tupel> _drlittlepositionen;
	private LinkedList<Tupel> _mauspositionen;
	private LinkedList<Tupel> _itemPositionen;

	private int _breitehoehe;
	private String _gegangeneRichtung = "nord";

	private ClientPaket _paket;
	private boolean _schauenAnsicht;
	private ArrayList<String> _aktuelleSpieler;
	private HashMap<String, Color> _spielerfarben;

	private LinkedList<Color> _verfuegbareFarben;
	private final Color[] KITTELFARBEN = new Color[] { Color.BLUE, Color.RED,
			Color.GREEN, Color.YELLOW, Color.PINK, Color.MAGENTA };

	public Raumbilderzeuger()
	{
		_drlittlepositionen = new LinkedList<Tupel>();
		_mauspositionen = new LinkedList<Tupel>();
		_itemPositionen = new LinkedList<Tupel>();
		_spielerfarben = new HashMap<>();
		_verfuegbareFarben = new LinkedList<Color>();
		_verfuegbareFarben.add( Color.BLUE);
		_verfuegbareFarben.add( Color.RED);
		_verfuegbareFarben.add( Color.GREEN);
		_verfuegbareFarben.add( Color.YELLOW);
		_verfuegbareFarben.add( Color.PINK);
		_verfuegbareFarben.add( Color.GRAY);
		_verfuegbareFarben.add( Color.BLACK);
		_verfuegbareFarben.add( Color.MAGENTA);
		_verfuegbareFarben.add( Color.ORANGE);
		_verfuegbareFarben.add( Color.CYAN);

	}

	/**
	 * Malt den aktuellen Raum sowie die Items und Charaktere die sich in ihm
	 * befinden
	 * 
	 * @param breitehoehe
	 *            die Höhe/Breite des Raumbildes
	 * @param paket
	 *            Das Paket mit den Informationen zum Raum
	 * @param vorschau
	 *            Gibt an ob in einen benachbarten Raum geschaut wird
	 * @return
	 */
	public BufferedImage getRaumansicht(int breitehoehe, ClientPaket paket,
			boolean vorschau)
	{
		_paket = paket;
		if(breitehoehe == 0)
			_breitehoehe = 3;
		_breitehoehe = breitehoehe;
		_schauenAnsicht = vorschau;

		return erzeugeRaumansicht();
	}

	private BufferedImage erzeugeRaumansicht()
	{
		SuperFancyReproducibleRandomEntryPicker entryPicker = new SuperFancyReproducibleRandomEntryPicker(
				_paket.buildUniqueID());

		BufferedImage raum = null;
		_aktuelleSpieler = (ArrayList<String>) _paket.getAndereSpieler();
		
		if(!_spielerfarben.containsKey(_paket.getSpielerName()))
		{
			_spielerfarben.put(_paket.getSpielerName(), Color.white);
		}

		RaumArt raumArt = _paket.getRaumArt();
		switch (raumArt)
		{
		case Normal:
			raum = ladeBild(getClass().getResource("bilder/raum_normal.png"));
			break;
		case Start:
			raum = ladeBild(getClass().getResource("bilder/raum_labor.png"));
			break;
		case Ende:
			raum = ladeBild(getClass().getResource("bilder/raum_ende.png"));
			break;
		}

		Graphics2D g2d = (Graphics2D) raum.getGraphics();

		for(String richtung : _paket.getMoeglicheAusgaenge())
		{

			if(richtung.equals("nord"))
			{
				g2d.drawImage(RAUMTUERNORD, 272, 20, 97, 50, null);
			}
			else if(richtung.equals("ost"))
			{
				g2d.drawImage(RAUMTUEROST, 570, 272, 50, 97, null);
			}
			else if(richtung.equals("süd"))
			{
				g2d.drawImage(RAUMTUERSUED, 272, 570, 97, 50, null);
			}
			else if(richtung.equals("west"))
			{
				g2d.drawImage(RAUMTUERWEST, 20, 272, 50, 97, null);
			}

		}

		setPositionen();
		int x = 0;
		int y = 0;
		Tupel position;

		//male DRLittle
		if(!_schauenAnsicht)
		{
			if(_gegangeneRichtung.equals("nord"))
			{
				g2d.drawImage(getFarbigenDrLittle(_paket.getSpielerName()),
						320, 520, 54, 54, null);
			}
			else if(_gegangeneRichtung.equals("ost"))
			{
				g2d.drawImage(getFarbigenDrLittle(_paket.getSpielerName()), 73,
						320, 54, 54, null);
			}
			else if(_gegangeneRichtung.equals("süd"))
			{
				g2d.drawImage(getFarbigenDrLittle(_paket.getSpielerName()),
						320, 70, 54, 54, null);
			}
			else if(_gegangeneRichtung.equals("west"))
			{
				g2d.drawImage(getFarbigenDrLittle(_paket.getSpielerName()),
						520, 320, 54, 54, null);
			}
		}

		
	
		
		for(int i = 0; i < _paket.getAndereSpieler().size(); i++)
		{
			if(!_paket.getAndereSpieler().get(i)
					.equals(_paket.getSpielerName()))
			{
				
				if(!_spielerfarben.containsKey(_paket.getAndereSpieler().get(i)))
				{
					_spielerfarben.put(_paket.getAndereSpieler().get(i), getUnverbrauchteFarbe());
				}
				
				
				position = entryPicker.pick(_drlittlepositionen);
				_drlittlepositionen.remove(position);
				
				if(position == null)
					position = new Tupel(245, 238);
				
				x = position.getX();
				y = position.getY();
				g2d.drawImage(getFarbigenDrLittle(_paket.getAndereSpieler()
						.get(i)), x, y, 54, 54, null);
			}

		}

		//Male Maus

		if(_paket.hasMaus())
		{
			Tupel mausposition = entryPicker
					.pickAndRemoveFromList(_mauspositionen);
			if(mausposition == null);
				mausposition = new Tupel(70, 70);
			x = mausposition.getX();
			y = mausposition.getY();

			g2d.drawImage(MAUS, x, y, 100, 51, null);
		}

		//Male Katze
		if(_paket.hasKatze())
		{
			Tupel pos = entryPicker.pickAndRemoveFromList(_mauspositionen);
			if(pos == null)
				pos = new Tupel(70, 470);
				
			x = pos.getX();
			y = pos.getY();
			g2d.drawImage(KATZE, x, y, 100, 100, null);
		}

		// Male Gegenstände
		int anzahlKruemel = 0;
		boolean gegengiftDa = false;

		Collection<Item> raumItems = _paket.getItems();

		for(Item item : raumItems)
		{
			if(item.isAnyKuchen())
				anzahlKruemel++;
			if(item == Item.Gegengift)
				gegengiftDa = true;
		}

		for(int i = 0; i < anzahlKruemel; i++)
		{
			Tupel itempos = entryPicker.pickAndRemoveFromList(_itemPositionen);
			if(itempos == null)
				itempos = new Tupel(200, 180);
				
			x = itempos.getX();
			y = itempos.getY();

			g2d.drawImage(KRUEMEL, x, y, 30, 30, null);
		}

		if(gegengiftDa)
		{
			Tupel itempos = entryPicker.pickAndRemoveFromList(_itemPositionen);
			_itemPositionen.remove(itempos);
			if(itempos == null)
				itempos = new Tupel(200, 180);
			
			x = itempos.getX();
			y = itempos.getY();

			g2d.drawImage(GEGENGIFT, x, y, 30, 30, null);

			Tupel pos = entryPicker.pickAndRemoveFromList(_mauspositionen);
			x = pos.getX();
			y = pos.getY();
			g2d.drawImage(DREVENBIGGER, x, y, 100, 100, null);

		}

		if(_schauenAnsicht)
		{
			g2d.drawImage(SCHAUENSCHATTEN, 0, 0, 640, 640, null);
		}

		raum = skaliereBild(raum, _breitehoehe);

		return raum;

	}

	private Color getUnverbrauchteFarbe()
	{
		
		return _verfuegbareFarben.remove();
	}

	private Image getFarbigenDrLittle(String name)
	{
		BufferedImage drlittle = ladeBild(getClass().getResource(
				"bilder/drlittle.png"));
		Color farbe = _spielerfarben.get(name);

		if(farbe != null)
		{
			for(int i = 17; i < 54; i++)
			{
				for(int j = 0; j < 54; j++)
				{
					if(drlittle.getRGB(j, i) == Color.white.getRGB())
					{
						drlittle.setRGB(j, i,
								farbe.getRGB());
					}
				}
			}

		}
		else
		{
			for(int i = 17; i < 54; i++)
			{
				for(int j = 0; j < 54; j++)
				{
					if(drlittle.getRGB(j, i) == Color.white.getRGB())
					{
						drlittle.setRGB(j, i, Color.black.getRGB());
					}
				}
			}
		}

		return drlittle;
	}

	private void setPositionen()
	{
		_itemPositionen = new LinkedList<Tupel>();

		_itemPositionen.add(new Tupel(200, 180));
		_itemPositionen.add(new Tupel(155, 250));
		_itemPositionen.add(new Tupel(220, 300));
		_itemPositionen.add(new Tupel(165, 395));
		_itemPositionen.add(new Tupel(380, 155));
		_itemPositionen.add(new Tupel(234, 419));
		_itemPositionen.add(new Tupel(430, 205));
		_itemPositionen.add(new Tupel(300, 375));
		_itemPositionen.add(new Tupel(400, 330));
		_itemPositionen.add(new Tupel(430, 440));

		_mauspositionen = new LinkedList<Tupel>();
		_mauspositionen.add(new Tupel(70, 70));
		_mauspositionen.add(new Tupel(70, 470));
		_mauspositionen.add(new Tupel(470, 70));
		_mauspositionen.add(new Tupel(470, 470));

		_drlittlepositionen = new LinkedList<Tupel>();
		_drlittlepositionen.add(new Tupel(245,238));
		_drlittlepositionen.add(new Tupel(252,325));
		_drlittlepositionen.add(new Tupel(324,214));
		_drlittlepositionen.add(new Tupel(346,285));
		_drlittlepositionen.add(new Tupel(340,355));

	}

	/**
	 * Skaliert ein Bild neu und gib es dann zurück
	 * 
	 * @param img
	 *            Das zu skalierende Bild
	 * @param breiteHoehe
	 *            Die neue Höhe/Breite
	 * @return Das skalierte Bild
	 */
	private BufferedImage skaliereBild(BufferedImage img, int breiteHoehe)
	{

		BufferedImage ergebnis = new BufferedImage(breiteHoehe, breiteHoehe,
				BufferedImage.TYPE_INT_ARGB); // bzw. TYPE_INT_RGB falls du kein Alphakanal brauchst
		ergebnis.getGraphics().drawImage(img, 0, 0, breiteHoehe, breiteHoehe,
				null); // oder ein anderer drawImage Befehl

		return ergebnis;
	}

	/**
	 * Lädt ein Bild aus einer Datei und gibt dieses Bild zurück
	 * 
	 * @param url
	 * @return -Das geladene Bild
	 */

	private BufferedImage ladeBild(URL url)
	{
		try
		{
			BufferedImage tmp = ImageIO.read(url);
			return tmp;

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gibt ein Bild von dem zuletzt gemalten Raum zurück
	 * 
	 * @param hoehebreite
	 * @return ein neu skaliertes Bild vom zuletzt gemaltem Raum
	 */

	public BufferedImage zeichneBildErneut(int hoehebreite)
	{
		_breitehoehe = hoehebreite;
		return erzeugeRaumansicht();
	}

	/**
	 * Malt die Verloren-Anzeige auf den aktuellen Raum und gibt das entstandene
	 * Bild zurück
	 * 
	 * @param breitehoehe
	 * @return Den Verlierenbildschirm
	 */
	public BufferedImage getGameOverScreen(int breitehoehe)
	{
		BufferedImage raum = getRaumansicht(breitehoehe, _paket,
				_schauenAnsicht);
		Graphics2D g2d2 = (Graphics2D) raum.getGraphics();
		g2d2.drawImage(GAMEOVER, 0, 0, breitehoehe, breitehoehe, null);
		return skaliereBild(raum, breitehoehe);
	}

	/**
	 * Malt die Gewonnen-Anzeige auf den aktuellen Raum und gibt das entstandene
	 * Bild zurück
	 * 
	 * @param hoehebreite
	 *            -die Hoehe bzw Breite des Raumbildes
	 * @return Den Gewinnbildschirm
	 */
	public BufferedImage getWinScreen(int hoehebreite)
	{
		BufferedImage raum = getRaumansicht(hoehebreite, _paket,
				_schauenAnsicht);
		Graphics2D g2d2 = (Graphics2D) raum.getGraphics();
		g2d2.drawImage(WIN, 0, 0, hoehebreite, hoehebreite, null);
		return skaliereBild(raum, hoehebreite);

	}

	private static class Tupel
	{
		private int _x;
		private int _y;

		public Tupel(int x, int y)
		{
			_x = x;
			_y = y;
		}

		public int getX()
		{
			return _x;
		}

		public int getY()
		{
			return _y;
		}
	}

	public void setPaket(ClientPaket paket)
	{
		_paket = paket;
	}

	public void setGehRichtung(String richtungsbefehl)
	{
		if(richtungsbefehl.contains("nord"))
		{
			_gegangeneRichtung = "nord";
		}
		else if(richtungsbefehl.contains("ost"))
		{
			_gegangeneRichtung = "ost";
		}
		else if(richtungsbefehl.contains("süd"))
		{
			_gegangeneRichtung = "süd";
		}
		else if(richtungsbefehl.contains("west"))
		{
			_gegangeneRichtung = "west";
		}
	}

}
