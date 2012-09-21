package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;

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
		IOManager manager = new IOManager();
		vergebeIDs(manager);

		_verbindungen.verbindeRaeume(_ef.getUI().getMap());

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());

		manager.schreibeLevelStruktur(path, raumstruktur, _ef.getEditorLevel());

		manager.schreibeLevelRaeume(_verbindungen.getRaumListe());

	}

	private void vergebeIDs(IOManager manager)
	{
		manager.readRaeume();
		List<Raum> rlist = manager.getRaeume();
		Set<Integer> idList = new HashSet<Integer>();

		for(Raum r : rlist)
		{
			idList.add(r.getId());
		}

		Random rand = new Random();
		int newid;

		GridButton[][] buttons = _ef.getUI().getMap().getButtonArray();
		for(int y = 0; y < buttons[0].length; ++y)
		{
			for(int x = 0; x < buttons.length; ++x)
			{
				Raum raum = buttons[x][y].getRaum();
				if(raum != null && raum.getId() == 0)
				{
					do
					{
						newid = rand.nextInt();
					} while(newid == 0 || idList.contains(newid));

					raum.setId(newid);
					idList.add(newid);
				}
			}
		}
	}
}
