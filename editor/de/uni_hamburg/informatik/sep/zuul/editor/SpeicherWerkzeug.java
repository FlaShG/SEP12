package de.uni_hamburg.informatik.sep.zuul.editor;

import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumStruktur;

public class SpeicherWerkzeug
{

	private VerbindungsWerkzeug _verbindungen;

	public SpeicherWerkzeug(EditorMap map)
	{
		_verbindungen = new VerbindungsWerkzeug(map);
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
		_verbindungen.verbindeRaeume();

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());
		IOManager manager = new IOManager();

		manager.schreibeLevelStruktur(path.concat("testStruktur.xml"), raumstruktur);

		manager.schreibeLevelRaeume(_verbindungen.getRaumListe());

	}

}
