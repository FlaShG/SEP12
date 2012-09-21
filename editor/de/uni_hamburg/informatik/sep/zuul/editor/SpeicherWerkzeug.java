package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.PathFinder;
import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;
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
			
			if(pf.findPath(startRaum) != null)
			{//^-- überprüfe Verbindung von start- und endraum
				
				int anzahlKatzen = 1;	// TODO ändern, wenn Katzenanzahl einstellbar ist bzw. sich ändert!!!
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
