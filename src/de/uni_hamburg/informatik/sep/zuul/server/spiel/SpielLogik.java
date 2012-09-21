package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.Feature;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumBauer;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class SpielLogik
{
	private ServerKontext _kontext;
	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	public SpielLogik()
	{
		erstelleKontext();
	}

	public void erstelleKontext()
	{
		_kontext = new ServerKontext(legeRaeumeAn());

	}

	/**
	 * Reistriere einen neuen Spieler
	 * 
	 * @param spieler
	 */
	public void registriereSpieler(Spieler spieler)
	{
		_kontext.fuegeNeuenSpielerHinzu(spieler);
	}

	/**
	 * Entfernde den Spieler aus dem Spiel.
	 * 
	 * @param name
	 *            der name des Spielers
	 */
	public void meldeSpielerAb(String name)
	{
		_kontext.entferneSpieler(name);
	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private RaumStruktur legeRaeumeAn()
	{
		IOManager manager = new IOManager();
		if(ServerKontext._levelPfad == null)
		{
			manager.readLevel("./xml_dateien/testStruktur.xml");
		}
		else
		{
			manager.readLevel(ServerKontext._levelPfad);
		}
		// TODO: noch statisch - datei mit filechooser auswählen!!

		
		//TODO relocate
		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		
		RaumBauer.initialisiereRaeume(struktur);
		
		return struktur;
	}


	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an. Gibt diese als Liste von
	 * Räumen zurück.
	 */
	public List<Raum> zeigeAktuelleAusgaenge(Spieler spieler)
	{
		Raum raum = _kontext.getAktuellenRaumZu(spieler);
		return raum.getAusgaenge();
	}

	/**
	 * Gibt eine Nachricht aus und beendet das Spiel
	 * 
	 */
	public void beendeSpiel(Spieler spieler)
	{
		// TODO nachricht ausgeben.
		// TODO spiel beenden (Kontext?)

	}

	/**
	 * Beende das Spiel für alle Spieler.
	 */
	public void beendeSpiel()
	{
		for(Spieler spieler : _kontext.getSpielerListe())
		{
			beendeSpiel(spieler);
		}
	}

	/**
	 * Zeige allen Spielern den Willkommenstext.
	 * 
	 */
	public void zeigeWillkommensText()
	{
		for(Spieler spieler : _kontext.getSpielerListe())
		{
			_kontext.zeigeWillkommensText(spieler);
		}
	}

	public boolean isRaumZielRaum(Raum raum)
	{
		// TODO: Ugly !!!
		return raum.getRaumart() == RaumArt.Ende;
	}

	/**
	 * Gibt den Zielraum zurück oder null, wenn kein Zielraum gesetzt wurde.
	 * 
	 * @return der Zielraum.
	 */
	public Raum getZielRaum()
	{
		for(Raum raum : _kontext.getRaumStruktur().getConnections().keySet())
		{
			if(isRaumZielRaum(raum))
			{
				return raum;
			}
		}
		return null;

	}

	/**
	 * Gehe mit dem Spieler in einen anderen Raum
	 * 
	 * @param spieler
	 *            der Spieler der Laufe soll
	 * @param raum
	 *            der Raum in den es gehen soll
	 */
	public void wechseleRaum(Spieler spieler, Raum raum)
	{
		_kontext.setAktuellenRaumZu(spieler, raum);
	}

	/**
	 * Getter für den Serverkontext.
	 * 
	 * @return der Serverkontext
	 */
	public ServerKontext getKontext()
	{
		return _kontext;
	}

	/**
	 * @return the struktur
	 */
	public RaumStruktur getStruktur()
	{
		return _kontext.getRaumStruktur();
	}
	
}
