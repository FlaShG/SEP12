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
	 */
	public void speichern(String path)
	{
		_verbindungen.verbindeRaeume();

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());
		IOManager manager = new IOManager();

		manager.writeLevel(path, raumstruktur);

		//TODO: prüfen wie das mit neuen Räumen ist und die evtl auch schreiben - andere write methode?

	}

}
