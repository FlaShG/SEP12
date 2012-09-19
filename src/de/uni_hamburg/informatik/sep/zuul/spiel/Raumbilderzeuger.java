package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Stack;


import javax.imageio.ImageIO;

public class Raumbilderzeuger
{

	private final String PATH = getClass().getResource("bilder/").getPath();
	private final BufferedImage RAUM = ladeBild(PATH + "raum.png");
	private final BufferedImage MAUS = ladeBild(PATH + "maus.png");
	private final BufferedImage KRUEMEL = ladeBild(PATH + "kruemel.png");
	private final BufferedImage GEGENGIFT = ladeBild(PATH + "gegengift.png");
	private final BufferedImage DRLITLE = ladeBild(PATH + "drlittle.png");

	private BufferedImage _raumansicht;
	private SpielKontext _kontext;
	private int anzahlItemsImRaum = 0;

	public Raumbilderzeuger(SpielKontext kontext)
	{
		_kontext = kontext;
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

		if(_kontext.getAktuellerRaum().getRaumart() != null)
		{
			switch (_kontext.getAktuellerRaum().getRaumart())
			{
			case Start:
				faerbeEin(new Color[] { Color.GREEN, Color.white },
						new Color[] { Color.darkGray, Color.LIGHT_GRAY },
						_raumansicht);
				break;
			case Ende:
				faerbeEin(new Color[] { Color.GREEN, Color.white },
						new Color[] { new Color(255, 170, 85),
								new Color(153, 249, 249) }, _raumansicht);
				break;
			default:
				break;

			}
		}

		if(_kontext.getAktuellerRaum().hasMaus())
		{
			_raumansicht = maleAufBild(_raumansicht, MAUS, 100, 50);
		}

		int anzahlKruemel = 0;
		int anzahlGegengift = 0;

		Stack<Item> raumItems = (Stack<Item>) _kontext.getAktuellerRaum()
				.getItems().clone();

		for(int i = 0; i < raumItems.size(); i++)
		{
			switch (raumItems.get(i))
			{
			case Kuchen:
				anzahlKruemel++;
				break;
			case Giftkuchen:
				anzahlKruemel++;
				break;
			case Gegengift:
				anzahlGegengift++;
				break;
			default:
				break;
			}
		}

		maleKruemel(anzahlKruemel);

	}

	private void maleKruemel(int anzahlKruemel)
	{
		for(int i = 0; i < anzahlKruemel; i++)
		{
			maleAufBild(_raumansicht, KRUEMEL, getZufaelligeDimension(),
					getZufaelligeDimension());
		}
	}

	private int getZufaelligeDimension()
	{
		// TODO Auto-generated method stub
		return 0;
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
			BufferedImage quelle, int offsetH, int offsetB)
	{
		int quellH = quelle.getHeight();
		int quellB = quelle.getWidth();

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

}
