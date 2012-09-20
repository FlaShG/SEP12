package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Random;

import javax.imageio.ImageIO;

public class Raumbilderzeuger
{

	
	private final String PATH = getClass().getResource("bilder/").getPath();
	private final BufferedImage RAUM = ladeBild(PATH + "raum.png");
	private final BufferedImage MAUS = ladeBild(PATH + "maus.png");
	private final BufferedImage KATZE = ladeBild(PATH + "katze.png"); 
	private final BufferedImage KRUEMEL = ladeBild(PATH + "kruemel.png");
	private final BufferedImage GEGENGIFT = ladeBild(PATH + "gegengift.png");
	private final BufferedImage DRLITLE = ladeBild(PATH + "drlittle.png");
	private final BufferedImage DREVENBIGGER = ladeBild(PATH + "drevenbigger.png");
	private final Color WANDFARBE = Color.white;
	private final Color BODENFARBE = Color.GREEN;
	private final Tupel KATZENPOSITION = new Tupel(140,136);
	private final Tupel MAUSPOSITION = new Tupel(140, 25);
	private final LinkedList<Tupel> _itemPositionen = new LinkedList<Tupel>();

	private BufferedImage _raumansicht;
	private SpielKontext _kontext;

	
	public Raumbilderzeuger(SpielKontext kontext)
	{
		_kontext = kontext;

		_itemPositionen.add(new Tupel(25, 25));
		_itemPositionen.add(new Tupel(62, 25));
		_itemPositionen.add(new Tupel(99, 25));
		_itemPositionen.add(new Tupel(25, 62));
		_itemPositionen.add(new Tupel(62, 62));
		_itemPositionen.add(new Tupel(99, 62));
		_itemPositionen.add(new Tupel(25, 99));
		_itemPositionen.add(new Tupel(62, 99));
		_itemPositionen.add(new Tupel(99, 99));
		_itemPositionen.add(new Tupel(136, 99));
		_itemPositionen.add(new Tupel(25, 136));
		_itemPositionen.add(new Tupel(62, 136));
		_itemPositionen.add(new Tupel(99, 136));
		_itemPositionen.add(new Tupel(136, 136));

	}

	public BufferedImage getRaumansicht()
	{
		erzeugeRaumansicht();
		return _raumansicht;
	}

	private void erzeugeRaumansicht()
	{
		_raumansicht = new BufferedImage(245, 245, BufferedImage.TYPE_INT_RGB);
		_raumansicht = RAUM;

		//Färbe den Raum ein je nach RaumTyp
		if(_kontext.getAktuellerRaum().getRaumart() != null)
		{
			switch (_kontext.getAktuellerRaum().getRaumart())
			{
			case Start:
				faerbeEin(new Color[] { BODENFARBE, WANDFARBE }, new Color[] {
						Color.darkGray, Color.LIGHT_GRAY }, _raumansicht);
				break;
			case Ende:
				faerbeEin(new Color[] { BODENFARBE, WANDFARBE }, new Color[] {
						new Color(255, 170, 85), new Color(153, 249, 249) },
						_raumansicht);
				break;
			case  Normal:faerbeEin(new Color[] { BODENFARBE, WANDFARBE }, new Color[] {
					new Color(170, 85, 0), new Color(128, 128, 64) },
					_raumansicht); break;
			default:
				break;

			}
		}
		

		// Male Dr.Little
		_raumansicht = maleAufBild(_raumansicht, DRLITLE, new Tupel(77, 77));
		
		
		//Male Maus

		if(_kontext.getAktuellerRaum().hasMaus())
		{
			_raumansicht = maleAufBild(_raumansicht, MAUS, MAUSPOSITION);
		}
		
		//Male Katze
		if(_kontext.isKatzeImAktuellenRaum())
		{
			_raumansicht = maleAufBild(_raumansicht, KATZE, KATZENPOSITION);
		}
		
		
		// Male Gegenstände
		int anzahlKruemel = 0;
		boolean gegengiftDa = false;

		Stack<Item> raumItems = (Stack<Item>) _kontext.getAktuellerRaum()
				.getItems().clone();

		for(int i = 0; i < raumItems.size(); i++)
		{
			switch (raumItems.get(i))
			{
			case IKuchen: case UKuchen:
				anzahlKruemel++;
				break;
			case IGiftkuchen: case UGiftkuchen:
				anzahlKruemel++;
				break;
			case Gegengift:
				gegengiftDa = true;;
				break;
			default:
				break;
			}
		}

		maleKruemel(anzahlKruemel);
		if(gegengiftDa)
		{
			maleGegengiftundEvenBigger();
		}
		
		
		
		
		

	}

	private void maleGegengiftundEvenBigger()
	{
		Tupel position = getFreiePosition();
		maleAufBild(_raumansicht, GEGENGIFT, position);
		maleAufBild(_raumansicht, DREVENBIGGER, position);
		
		
	}

	private void maleKruemel(int anzahlKruemel)
	{
		for(int i = 0; i < anzahlKruemel; i++)
		{

			Tupel position = getFreiePosition();

			maleAufBild(_raumansicht, KRUEMEL, position);
		}
	}

	private Tupel getFreiePosition()
	{
		Random random = new Random();

		//		return _itemPositionen.remove(random.nextInt(_itemPositionen.size()));
		if(_itemPositionen.get(0) != null)
			return _itemPositionen.remove(0);
		else
			return new Tupel(0, 0);

	}

	private BufferedImage ladeBild(String pfad)
	{
		try
		{
			BufferedImage tmp = new BufferedImage(245, 245,
					BufferedImage.TYPE_INT_RGB);
			tmp = ImageIO.read(new File(pfad));
			return tmp;

		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
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

	private BufferedImage faerbeEin(Color[] quellenfarben,
			Color[] ersatzfarben, BufferedImage quelle)
	{
		for(int x = 0; x < quellenfarben.length; x++)
		{

			for(int i = 0; i < quelle.getHeight(); i++)
			{
				for(int j = 0; j < quelle.getWidth(); j++)
				{
					if(quelle.getRGB(i, j) == quellenfarben[x].getRGB())
						quelle.setRGB(i, j, ersatzfarben[x].getRGB());
				}
			}
		}

		return quelle;
	}

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
