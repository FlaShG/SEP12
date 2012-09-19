package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class Raumbilderzeuger
{
	private final String PATH = "Z:\\SEP\\";
	private final BufferedImage RAUM = ladeBild(PATH + "raum.png");
	private final BufferedImage MAUS = ladeBild(PATH + "maus.png");
//	private final BufferedImage KRUEMEL = ladeBild(PATH + "kruemel.png");
	private BufferedImage _raumansicht;
	private SpielKontext _kontext;

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

		if(_kontext.getAktuellerRaum().hasMaus())
		{
			_raumansicht = maleAufBild(_raumansicht, MAUS,100,50);
		}
		

	}

	private BufferedImage ladeBild(String pfad)
	{
		try
		{
			BufferedImage tmp = new BufferedImage(245, 245, BufferedImage.TYPE_INT_RGB);
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
					zielBild.setRGB(i + offsetB, j + offsetH, quelle.getRGB(i, j));
			}
		}

		return zielBild;

	}

}
