package de.uni_hamburg.informatik.sep.zuul.editor;

import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumStruktur;

public class SpeicherWerkzeug
{

	private VerbindungsWerkzeug _verbindungen;
	private EditorFenster _ef;
	public SpeicherWerkzeug(EditorFenster ef)
	{
		_ef = ef;
		_verbindungen = new VerbindungsWerkzeug();
	}

	/**
	 * Speichere alle Räume als Struktur an den angegebenen Pfad.
	 * 
	 * @param path
	 *            Directory Only! darf nur zwei XMLs beinhalten oder muss leer
	 *            sein.
	 */
	public void speichern(String path)
	{
		vergebeIDs();

		_verbindungen.verbindeRaeume(_ef.getUI().getMap());

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());

		IOManager manager = new IOManager();

		manager.schreibeLevelStruktur(path.concat("testStruktur.xml"),
				raumstruktur, _ef.getEditorLevel());

		manager.schreibeLevelRaeume(_verbindungen.getRaumListe());

	}

	private void vergebeIDs()
	{
		//TODO: unique ids vergeben. diese methode funzt auch nicht...
		int id = 0;
		GridButton[][] buttons = _ef.getUI().getMap().getButtonArray();
		for(int y = 0; y < buttons.length; ++y)
		{
			for(int x = 0; x < buttons[0].length; ++x)
			{
				Raum raum = buttons[x][y].getRaum();
				if(raum != null)
				{
					raum.setId(id++);
				}
			}
		}
	}
}
