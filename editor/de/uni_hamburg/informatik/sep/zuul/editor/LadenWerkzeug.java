package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.xml.XmlRaum;

/**
 * Lädt ein bestehendes Level in den Editor.
 * 
 * @author 1griese, 0ortmann
 * 
 */
public class LadenWerkzeug
{

	private IOManager _manager;

	public LadenWerkzeug()
	{
		_manager = new IOManager();
	}

	/**
	 * Erstelle ein GridButtonArray aus einer XMLRaumListe und einer RaumListe.
	 * 
	 * @param xmlRaumListe
	 * @param raumListe
	 * @return ein GridButtonArray
	 */
	private GridButton[][] erstelleButtonsAusListe(List<XmlRaum> xmlRaumListe,
			List<Raum> raumListe)
	{
		raumListe = setRaumKoordinaten(xmlRaumListe, raumListe);
		Raum[][] raumArray = erzeugeRaumArray(raumListe);
		return erstelleButtonArray(raumArray);
	}

	/**
	 * Parse ein RaumArray in ein {@link GridButton}Array mit den gleichen
	 * Koordinaten im Array.
	 * 
	 * @param raumArray
	 *            das RaumArray
	 * @return das analoge GridButtonArray zu dem RaumArray
	 */
	private GridButton[][] erstelleButtonArray(Raum[][] raumArray)
	{
		GridButton[][] result = new GridButton[raumArray.length][raumArray[0].length];
		for(int i = 0; i < raumArray.length; ++i)
		{
			for(int j = 0; j < raumArray[0].length; ++j)
			{
				Raum raum = raumArray[i][j];
				if(raum != null)
				{
					result[i][j] = new GridButton(raum.getX(), raum.getY());
					result[i][j].setRaum(raum);
				}
				else
				{
					result[i][j] = new GridButton(i, j);
				}
			}
		}

		return result;

	}

	/**
	 * Schreibe alle Räume der liste entsprechend ihrer Koordinaten in ein
	 * Array.
	 * 
	 * @param raumListe
	 * @return
	 */
	private Raum[][] erzeugeRaumArray(List<Raum> raumListe)
	{
		Raum[][] result = new Raum[getMaximumX(raumListe)][getMaximumY(raumListe)];
		for(Raum raum : raumListe)
		{
			result[raum.getX()][raum.getY()] = raum;
		}

		return result;
	}

	/**
	 * Getter für die groesste X-Koordinate aller Räume.
	 * 
	 * @param raumListe
	 * @return die größte X-koordinate
	 */
	private int getMaximumX(List<Raum> raumListe)
	{
		int max = 0;

		for(Raum raum : raumListe)
		{
			if(raum.getX() > max)
			{
				max = raum.getX();
			}
		}

		return max;
	}

	/**
	 * Getter für die groesste Y-Koordinate aller Räume.
	 * 
	 * @param raumListe
	 * @return die größte y-koordinate
	 */
	private int getMaximumY(List<Raum> raumListe)
	{
		int max = 0;

		for(Raum raum : raumListe)
		{
			if(raum.getY() > max)
			{
				max = raum.getY();
			}
		}

		return max;
	}

	/**
	 * Setze zu jedem Raum aus der Raumliste seine zugehörigen Koordinaten.
	 * 
	 * @param xmlRaumListe
	 * @param raumListe
	 */
	private List<Raum> setRaumKoordinaten(List<XmlRaum> xmlRaumListe,
			List<Raum> raumListe)
	{
		for(Raum raum : raumListe)
		{
			for(XmlRaum xmlRaum : xmlRaumListe)
			{
				if(raum.getId() == xmlRaum.getID())
				{
					raum.setKoordinaten(xmlRaum.getX(), xmlRaum.getY());
				}
			}
		}

		return raumListe;
	}

	public void lade(String path)
	{
		_manager.readLevel(path);
		erstelleButtonsAusListe(_manager.getXmlRaeume(), _manager.getRaeume());

	}

}
