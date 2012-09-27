package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.server.util.PathFinder;

public class SpeicherWerkzeug
{

	private VerbindungsWerkzeug _verbindungen;
	private EditorLevel _level;
	private EditorFensterUI _ui;

	public SpeicherWerkzeug(EditorLevel level, EditorFensterUI ui)
	{
		_level = level;
		_ui = ui;
		_verbindungen = new VerbindungsWerkzeug();
	}

	/**
	 * Speichere alle Räume als Struktur an den angegebenen Pfad.
	 * 
	 * @param path
	 *            Directory Only! darf nur zwei XMLs beinhalten oder muss leer
	 *            sein.
	 * 
	 * @require valide()
	 */
	public void speichern(String path)
	{
		assert valide() : "Vorbedingung verletzt: valide()";

		IOManager manager = new IOManager();
		vergebeIDs(manager);

		//hier war
		//_verbindungen.verbindeRaeume(_ef.getUI().getMap());

		RaumStruktur raumstruktur = new RaumStruktur(
				_verbindungen.getRaumListe());

		manager.schreibeLevelStruktur(path, raumstruktur, _level);

		manager.schreibeLevelRaeume(_verbindungen.getRaumListe());

		//Gegen Garbage-Räume, die von anderen Räumen referenziert werden.
		_verbindungen.loescheAlleVerbindungen();
	}

	/**
	 * Validiert die Räume. Folgendes gilt als valide:<br>
	 * - Ein einzelner Startraum vorhanden<br>
	 * - Ein einzelner Endraum vorhanden<br>
	 * - Verbindung zw. Start- und Endraum besteht<br>
	 * - Mäuse lassen sich setzen ((|Räume| - 2 - |Katzen|) >= |Mäuse|)
	 */
	public boolean valide()
	{
		boolean valid = false;
		int start = 0;
		int ende = 0;
		Raum startRaum = null;

		_verbindungen.verbindeRaeume(_ui.getMap());

		for(Raum r : _verbindungen.getRaumListe())
		{
			if(r.getRaumart() == RaumArt.Start)
			{
				startRaum = r;
				start++;
			}
			else if(r.getRaumart() == RaumArt.Ende)
				ende++;
		}

		if(start == 1 && ende == 1)
		{//^-- überprüfe Anzahl von start- und endräumen

			PathFinder pf = new PathFinder()
			{
				@Override
				protected boolean isZielRaum(Raum raum)
				{
					return raum.getRaumart() == RaumArt.Ende;
				}
			};

			if(_level.getLeben() > 0)
				valid = false;

			List<Raum> rs = pf.findPath(startRaum);
			if(pf.findPath(startRaum) != null)
			{//^-- überprüfe Verbindung von start- und endraum

				int anzahlKatzen = _level.getKatzen();
				int zulAnzahlMaeuse = _verbindungen.getRaumListe().size() - 2
						- anzahlKatzen;
				valid = zulAnzahlMaeuse >= _level.getMaeuse();
			}
		}

		//Gegen Garbage-Räume, die von anderen Räumen dank unseres
		//Pathfindings eben referenziert wurden.
		//Wäre valid true, bräuchten wir die Infos noch und die
		//Verbindungen werden in speichern() gelöscht.
		if(!valid)
		{
			_verbindungen.loescheAlleVerbindungen();
		}

		return valid;
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

		GridButton[][] buttons = _ui.getMap().getButtonArray();
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
