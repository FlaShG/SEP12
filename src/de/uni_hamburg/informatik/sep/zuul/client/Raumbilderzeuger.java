package de.uni_hamburg.informatik.sep.zuul.client;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;

public class Raumbilderzeuger
{

	private final String PATH = getClass().getResource("bilder/").getPath();

	private final BufferedImage RAUMNORMAL = ladeBild(PATH + "raum_normal.png");
	private final BufferedImage RAUMSTART = ladeBild(PATH + "raum_labor.png");
	private final BufferedImage RAUMENDE = ladeBild(PATH + "raum_ende.png");
	private final BufferedImage RAUMTUERNORD = ladeBild(PATH + "door_n.png");
	private final BufferedImage RAUMTUEROSTD = ladeBild(PATH + "door_e.png");
	private final BufferedImage RAUMTUERSUED = ladeBild(PATH + "door_s.png");
	private final BufferedImage RAUMTUERWEST = ladeBild(PATH + "door_w.png");

	private final BufferedImage MAUS = ladeBild(PATH + "maus.png");
	private final BufferedImage KATZE = ladeBild(PATH + "katze.png");
	private final BufferedImage KRUEMEL = ladeBild(PATH + "kruemel.png");
	private final BufferedImage GEGENGIFT = ladeBild(PATH + "gegengift.png");
	private final BufferedImage DRLITLE = ladeBild(PATH + "drlittle.png");
	private final BufferedImage DREVENBIGGER = ladeBild(PATH
			+ "drevenbigger.png");

	private BufferedImage _currentDrlittle;
	private BufferedImage _currentMaus;
	private BufferedImage _currentKatze;
	private BufferedImage _currentDrevenbigger;

	private BufferedImage _currentKruemel;
	private BufferedImage _currentGegengift;

	private LinkedList<Tupel> _drlittlepositionen;
	private LinkedList<Tupel> _mauspositionen;
	private LinkedList<Tupel> _itemPositionen;

	private int _breitehoehe;
	private Random _random;

	private ClientPaket _paket;

	public Raumbilderzeuger(ClientPaket paket)
	{
		_paket = paket;
		
		_drlittlepositionen = new LinkedList<>();
		_mauspositionen = new LinkedList<>();
		_itemPositionen = new LinkedList<>();
		
		_random = new Random();

	}

	public BufferedImage getRaumansicht(int breitehoehe)
	{
		if(breitehoehe == 0)
			_breitehoehe = 3;
		_breitehoehe = breitehoehe;

		return erzeugeRaumansicht();

	}

	private BufferedImage erzeugeRaumansicht()
	{
		BufferedImage raum = null;

		//Grundraum nach Raumtyp erstellen : TODO skalieren...

		RaumArt raumArt = _paket.getRaumArt();
		switch (raumArt)
		{
		case Normal:
			raum = RAUMNORMAL;
			break;
		case Start:
			raum = RAUMSTART;
			break;
		case Ende:
			raum = RAUMENDE;
			break;
		}

		
		
		
		setPositionen();

		// Male Dr.Little
		raum = maleAufBild(raum, DRLITLE,
				_drlittlepositionen.remove(getRandomZahl(_drlittlepositionen
						.size())));

		//Male Maus

		if(_paket.hasMaus())
		{
			raum = maleAufBild(
					raum,
					MAUS,
					_mauspositionen.remove(getRandomZahl(_mauspositionen.size())));
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
				anzahlKruemel++;
				break;
			case IGiftkuchen:
			case UGiftkuchen:
				anzahlKruemel++;
				break;
			case Gegengift:
				gegengiftDa = true;
				;
				break;
			default:
				break;
			}
			
		}
		maleKruemel(anzahlKruemel, raum);

		if(gegengiftDa)
		{
			maleGegengiftundEvenBigger(raum);
		}
		
		raum = skaliereBild(raum, _breitehoehe);
		
		return raum;

	}

	private int getRandomZahl(int size)
	{
		return _random.nextInt(size);
	}

	private void skaliereBilder()
	{
		_currentDrlittle = skaliereBild(DRLITLE, _breitehoehe / 7);
		_currentKatze = skaliereBild(KATZE, _breitehoehe / 7);
		_currentMaus = skaliereBild(MAUS, _breitehoehe / 7);
		_currentKruemel = skaliereBild(KRUEMEL, _breitehoehe / 14);
		_currentGegengift = skaliereBild(GEGENGIFT, _breitehoehe / 14);
	}

	private void setPositionen()
	{

		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel((int) (_breitehoehe / 1.7),
				(int) (_breitehoehe / 3.6)));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));
		_itemPositionen.add(new Tupel(_breitehoehe / 3, _breitehoehe / 3));

		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));
		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));
		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));
		_mauspositionen.add(new Tupel(_breitehoehe / 7, _breitehoehe / 7));

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

	private void maleKruemel(int anzahlKruemel, BufferedImage raum)
	{
		for(int i = 0; i < anzahlKruemel; i++)
		{

			maleAufBild(
					raum,
					KRUEMEL,
					_itemPositionen.remove(getRandomZahl(_itemPositionen.size())));
		}
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

	//	private BufferedImage faerbeEin(Color[] quellenfarben,
	//			Color[] ersatzfarben, BufferedImage quelle)
	//	{
	//		for(int x = 0; x < quellenfarben.length; x++)
	//		{
	//
	//			for(int i = 0; i < quelle.getHeight(); i++)
	//			{
	//				for(int j = 0; j < quelle.getWidth(); j++)
	//				{
	//					if(quelle.getRGB(i, j) == quellenfarben[x].getRGB())
	//						quelle.setRGB(i, j, ersatzfarben[x].getRGB());
	//				}
	//			}
	//		}
	//
	//		return quelle;
	//	}

	private class Tupel
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
