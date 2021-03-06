package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.XmlRaum;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;

/**
 * Lädt ein bestehendes Level in den Editor.
 * 
 * @author 1griese, 0ortmann
 * 
 */
public class LadenWerkzeug
{

	private IOManager _manager;
	private EditorMap _map;

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
	private EditorMap erstelleEditorMapAusListe(List<XmlRaum> xmlRaumListe,
			List<Raum> raumListe)
	{
		raumListe = setRaumKoordinaten(xmlRaumListe, raumListe);
		Raum[][] raumArray = erzeugeRaumArray(raumListe, xmlRaumListe);
		return erstelleEditorMap(raumArray);
	}

	/**
	 * Parse ein RaumArray in ein {@link GridButton}Array mit den gleichen
	 * Koordinaten im Array.
	 * 
	 * @param raumArray
	 *            das RaumArray
	 * @return das analoge GridButtonArray zu dem RaumArray
	 */
	private EditorMap erstelleEditorMap(Raum[][] raumArray)
	{
		EditorMap result = new EditorMap(raumArray.length, raumArray[0].length);
		GridButton[][] buttons = result.getButtonArray();

		for(int y = 0; y < raumArray[0].length; ++y)
		{
			for(int x = 0; x < raumArray.length; ++x)
			{
				Raum raum = raumArray[x][y];
				if(raum != null)
				{
					buttons[x][y].setRaum(raum);
					buttons[x][y].setAusgewaehlt(false);
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
	private Raum[][] erzeugeRaumArray(List<Raum> raumListe,
			List<XmlRaum> raumPositionen)
	{
		Raum[][] result = new Raum[getMaximumX(raumListe) + 1][getMaximumY(raumListe) + 1];
		for(Raum raum : raumListe)
		{
			for(XmlRaum xmlraum : raumPositionen)
			{
				if(xmlraum.getID() == raum.getId())
				{
					result[raum.getX()][raum.getY()] = raum;
					break;
				}
			}
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
		for(XmlRaum xmlRaum : xmlRaumListe)
		{
			for(Raum raum : raumListe)
			{
				if(raum.getId() == xmlRaum.getID())
				{
					raum.setKoordinaten(xmlRaum.getX(), xmlRaum.getY());
					raum.setItems(xmlRaum.getItems());
					break;
				}
			}
		}

		return raumListe;
	}

	public void lade(String path)
	{
		_manager.readLevel(path);
		_map = erstelleEditorMapAusListe(_manager.getXmlRaeume(),
				_manager.getRaeume());
	}

	public EditorMap getMap()
	{
		return _map;
	}

	public IOManager getManager()
	{
		return _manager;
	}
}
