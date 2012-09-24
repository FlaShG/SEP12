package de.uni_hamburg.informatik.sep.zuul.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;

public class Raumbilderzeuger
{


	private final String PATH = getClass().getResource("bilder/").getPath();

	private final BufferedImage RAUMTUERNORD = ladeBild(PATH + "door_n.png");
	private final BufferedImage RAUMTUEROST = ladeBild(PATH + "door_e.png");
	private final BufferedImage RAUMTUERSUED = ladeBild(PATH + "door_s.png");
	private final BufferedImage RAUMTUERWEST = ladeBild(PATH + "door_w.png");
	private final BufferedImage SCHAUENSCHATTEN = ladeBild(PATH + "peek.png");

	private final BufferedImage MAUS = ladeBild(PATH + "maus.png");
	private final BufferedImage KATZE = ladeBild(PATH + "katze.png");
	private final BufferedImage KRUEMEL = ladeBild(PATH + "kruemel.png");
	private final BufferedImage GEGENGIFT = ladeBild(PATH + "gegengift.png");
	private final BufferedImage DRLITLE = ladeBild(PATH + "drlittle.png");
	private final BufferedImage DREVENBIGGER = ladeBild(PATH
			+ "drevenbigger.png");


	private LinkedList<Tupel> _drlittlepositionen;
	private LinkedList<Tupel> _mauspositionen;
	private LinkedList<Tupel> _itemPositionen;

	private int _breitehoehe;
	private Random _random;

	private ClientPaket _paket;
	private boolean _schauenAnsicht;

	public Raumbilderzeuger()
	{
		_drlittlepositionen = new LinkedList<Tupel>();
		_mauspositionen = new LinkedList<Tupel>();
		_itemPositionen = new LinkedList<Tupel>();
		_random = new Random();

	}

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
		BufferedImage raum = null;

		RaumArt raumArt = _paket.getRaumArt();
		switch (raumArt)
		{
		case Normal:
			raum = ladeBild(PATH + "raum_normal.png");
			break;
		case Start:
			raum = ladeBild(PATH + "raum_labor.png");
			break;
		case Ende:
			raum = ladeBild(PATH + "raum_ende.png");
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

		// Male Dr.Little
		raum = maleAufBild(raum, DRLITLE,
				_drlittlepositionen.remove(getRandomZahl(_drlittlepositionen
						.size())));

		//Male Maus

		if(_paket.hasMaus())
		{
			g2d.drawImage(MAUS, 70, 70, 100, 51, null);
		}

		//Male Katze
		else if(_paket.hasKatze())
		{
			raum = maleAufBild(
					raum,
					KATZE,
					_mauspositionen.remove(getRandomZahl(_mauspositionen.size())));

		}

		// Male Gegenstände
		int anzahlKruemel = 0;
		boolean gegengiftDa = false;

		Collection<Item> raumItems = _paket.getItems();

		for(Item item : raumItems)
		{
			switch (item)
			{
				case IKuchen:
				case UKuchen:
				case IGiftkuchen:
				case UGiftkuchen:
					anzahlKruemel++;
					break;
				case Gegengift:
					gegengiftDa = true;
					break;
				default:
					break;
			}

		}

		
		for(int i = 0; i < anzahlKruemel; i++)
		{
			int rand = getRandomZahl(_itemPositionen.size());
			Tupel itempos = _itemPositionen.remove(rand);
			int x = itempos.getX();
			int y = itempos.getY();
			
			g2d.drawImage(KRUEMEL, x, y, 30, 30, null);
		}
		
		

		if(gegengiftDa)
		{
			maleGegengiftundEvenBigger(raum);
		}

		if(_schauenAnsicht)
		{
			g2d.drawImage(SCHAUENSCHATTEN, 0, 0, 640, 640, null);
		}
		
		raum = skaliereBild(raum, _breitehoehe);

		return raum;

	}

	private int getRandomZahl(int size)
	{
		return _random.nextInt(size);
	}

	private void setPositionen()
	{
		_itemPositionen = new LinkedList<Tupel>();

		_itemPositionen.add(new Tupel(200,180));
		_itemPositionen.add(new Tupel(155,250));
		_itemPositionen.add(new Tupel(220,300));
		_itemPositionen.add(new Tupel(165,395));
		_itemPositionen.add(new Tupel(380,155));
		_itemPositionen.add(new Tupel(330,230));
		_itemPositionen.add(new Tupel(430,205));
		_itemPositionen.add(new Tupel(300,375));
		_itemPositionen.add(new Tupel(400,330));
		_itemPositionen.add(new Tupel(430,440));

		_mauspositionen.add(new Tupel(70,70));
//		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));
//		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));
//		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));

		_drlittlepositionen.add(new Tupel(73, 320));
		//		_drlittlepositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 2));
		//		_drlittlepositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 2));
		//		_drlittlepositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 2));
		//		_drlittlepositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 2));

	}

	private BufferedImage skaliereBild(BufferedImage img, int breiteHoehe)
	{

		BufferedImage ergebnis = new BufferedImage(breiteHoehe, breiteHoehe,
				BufferedImage.TYPE_INT_ARGB); // bzw. TYPE_INT_RGB falls du kein Alphakanal brauchst
		ergebnis.getGraphics().drawImage(img, 0, 0, breiteHoehe, breiteHoehe,
				null); // oder ein anderer drawImage Befehl

		return ergebnis;
	}

	private void maleGegengiftundEvenBigger(BufferedImage raum)
	{

		maleAufBild(raum, GEGENGIFT,
				_itemPositionen.remove(getRandomZahl(_itemPositionen.size())));
		//		maleAufBild(raum, DREVENBIGGER,
		//				_mauspositionen.remove(getRandomZahl(_mauspositionen.size())));

	}


	private BufferedImage ladeBild(String pfad)
	{
		try
		{
			BufferedImage tmp = ImageIO.read(new File(pfad));
			return tmp;

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private BufferedImage maleAufBild(BufferedImage zielBild,
			BufferedImage quelle, Tupel offset)
	{
		int quellH = quelle.getHeight();
		int quellB = quelle.getWidth();
		int offsetH = offset.getY();
		int offsetB = offset.getX();

		int[] quellDaten = new int[quellH * quellB];

		quelle.getRGB(0, 0, quellB, quellH, quellDaten, 0, quellB);

		for(int i = 0; i < quellH; i++)
		{
			for(int j = 0; j < quellB; j++)
			{
				if(quelle.getRGB(i, j) != new Color(255, 128, 255).getRGB())
					zielBild.setRGB(i + offsetB, j + offsetH,
							quelle.getRGB(i, j));
			}
		}

		return zielBild;

	}

	public BufferedImage ZeichneBildErneut(int hoehebreite)
	{
		_breitehoehe = hoehebreite;
		return erzeugeRaumansicht();
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

}
