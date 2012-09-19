package de.uni_hamburg.informatik.sep.zuul.editor;

import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumStruktur;

public class SpeicherWerkzeug
{

	private VerbindungsWerkzeug _verbindungen;
	private EditorFensterUI _ui;

	public SpeicherWerkzeug(EditorFensterUI ui)
	{
		_ui = ui;
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
		_verbindungen.verbindeRaeume(_ui.getMap());

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());
		IOManager manager = new IOManager();

		manager.schreibeLevelStruktur(path.concat("struktur.xml"), raumstruktur);

		manager.schreibeLevelRaeume(_verbindungen.getRaumListe());

	}

}
