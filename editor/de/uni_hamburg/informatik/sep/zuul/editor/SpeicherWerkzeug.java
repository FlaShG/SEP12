package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.server.util.PathFinder;

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

		if(valide())
		{
			manager.schreibeLevelStruktur(path, raumstruktur,
					_ef.getEditorLevel());

			manager.schreibeLevelRaeume(_verbindungen.getRaumListe());
		}
		else
		{
			JOptionPane.showMessageDialog(new JPanel(), "Level erfüllt nicht die Anforderungen.", "Ungültiges Level", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Validiert die Räume.
	 * Folgendes gilt als valide:<br>
	 * - Ein einzelner Startraum vorhanden<br>
	 * - Ein einzelner Endraum vorhanden<br>
	 * - Verbindung zw. Start- und Endraum besteht<br>
	 * - Mäuse lassen sich setzen ((|Räume| - 2 - |Katzen|) >= |Mäuse|)
	 */
	private boolean valide()
	{
		boolean valid = false;
		int start = 0;
		int ende = 0;
		Raum startRaum = null;
		
		for (Raum r : _verbindungen.getRaumListe())
		{
			if (r.getRaumart() == RaumArt.Start)
			{
				startRaum = r;
				start++;
			}
			else if (r.getRaumart() == RaumArt.Ende)
				ende++;
		}
		
		if (start == 1 && ende == 1)
		{//^-- überprüfe Anzahl von start- und endräumen
			
			PathFinder pf = new PathFinder()
			{
				@Override
				protected boolean isZielRaum(Raum raum)
				{
					return raum.getRaumart() == RaumArt.Ende;
				}
			};
			
			if(_ef.getEditorLevel().getLeben() > 0)
				valid = false;
			
			if(pf.findPath(startRaum) != null)
			{//^-- überprüfe Verbindung von start- und endraum
				
				int anzahlKatzen = _ef.getEditorLevel().getKatzen();
				int zulAnzahlMaeuse = _verbindungen.getRaumListe().size() - 2 - anzahlKatzen;
				valid = zulAnzahlMaeuse >= _ef.getEditorLevel().getMaeuse();
			}
			else
				valid = false;
			
		}
		else
			valid = false;
		
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
