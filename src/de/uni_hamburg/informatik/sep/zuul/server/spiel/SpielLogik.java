package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.Feature;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumBauer;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class SpielLogik
{
//	private ServerKontext _kontext;
	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;
	public static String LEVEL_PFAD;

	private SpielLogik()
	{
		erstelleKontext();
	}

	public static ServerKontext erstelleKontext()
	{
		ServerKontext kontext = new ServerKontext(legeRaeumeAn());
		return kontext;

	}

	/**
	 * Reistriere einen neuen Spieler
	 * 
	 * @param spieler
	 */
	public static void registriereSpieler(ServerKontext kontext, Spieler spieler)
	{
		kontext.getSpielerPosition().put(spieler, kontext.getStartRaum());
	}

	/**
	 * Entfernde den Spieler aus dem Spiel.
	 * 
	 * @param name
	 *            der name des Spielers
	 */
	public static void meldeSpielerAb(ServerKontext kontext, String name)
	{
		for(Spieler spieler : kontext.getSpielerListe())
		{
			if(name.equals(spieler.getName()))
			{
				kontext.getSpielerPosition().remove(spieler);
			}
		}
	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	public static RaumStruktur legeRaeumeAn()
	{
		IOManager manager = new IOManager();
		if(SpielLogik.LEVEL_PFAD == null)
		{
			manager.readLevel("./xml_dateien/testStruktur.xml");
		}
		else
		{
			manager.readLevel(SpielLogik.LEVEL_PFAD);
		}
		// TODO: noch statisch - datei mit filechooser auswählen!!

		
		//TODO relocate
		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		
		RaumBauer.initialisiereRaeume(struktur);
		
		return struktur;
	}


	/**
	 * Gibt eine Nachricht aus und beendet das Spiel
	 * 
	 */
	public static void beendeSpiel(ServerKontext kontext, Spieler spieler)
	{
		// TODO nachricht ausgeben.
		// TODO spiel beenden (Kontext?)

	}

	/**
	 * Beende das Spiel für alle Spieler.
	 */
	public static void beendeSpiel(ServerKontext kontext)
	{
		for(Spieler spieler : kontext.getSpielerListe())
		{
			beendeSpiel(kontext, spieler);
		}
	}

	/**
	 * Zeige allen Spielern den Willkommenstext.
	 * 
	 */
	public static void zeigeWillkommensText(ServerKontext kontext)
	{
		for(Spieler spieler : kontext.getSpielerListe())
		{
			zeigeWillkommensText(spieler);
		}
	}
	
	/**
	 * Zeige dem Spieler den Willkommenstext.
	 * 
	 * @param spieler
	 */
	public static void zeigeWillkommensText(Spieler spieler)
	{
		// TODO impl!!
		// schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		// schreibeNL("");
		// zeigeRaumbeschreibung(spieler);
		// zeigeAktuelleAusgaenge(spieler);
	}

	public static boolean isRaumZielRaum(Raum raum)
	{
		// TODO: Ugly !!!
		return raum.getRaumart() == RaumArt.Ende;
	}

	/**
	 * Gibt den Zielraum zurück oder null, wenn kein Zielraum gesetzt wurde.
	 * 
	 * @return der Zielraum.
	 */
	public static Raum getZielRaum(ServerKontext kontext)
	{
		for(Raum raum : kontext.getRaumStruktur().getConnections().keySet())
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
	public static void wechseleRaum(ServerKontext kontext, Spieler spieler, Raum raum)
	{
		assert (kontext.getSpielerPosition().containsKey(spieler));
		kontext.getSpielerPosition().remove(spieler);
		kontext.getSpielerPosition().put(spieler, raum);
	}
	
	public static void registriereFeature(ServerKontext kontext, Feature feature)
	{

		if(feature instanceof TickListener)
			kontext.getTickListeners().add((TickListener) feature);
		if(feature instanceof BefehlAusgefuehrtListener)
			kontext.getBefehlAusgefuehrtListeners().add((BefehlAusgefuehrtListener) feature);
		
		// TODO: Feature registrieren ( Lebenspunkte, ... )
	}
	
	public static void deregisterFeature(ServerKontext kontext, Feature feature)
	{
		if(feature instanceof TickListener)
			kontext.getTickListeners().remove((TickListener) feature);
		if(feature instanceof BefehlAusgefuehrtListener)
			kontext.getBefehlAusgefuehrtListeners().remove((BefehlAusgefuehrtListener) feature);
	}

	public static void fuehreBefehlAusgefuehrtListenerAus(ServerKontext kontext, Spieler spieler, Befehl befehl, boolean hasRoomChanged)
	{
		// Führe alle BefehlAusgefuehrtListener aus.
		for(BefehlAusgefuehrtListener befehlAusgefuehrtListener : kontext.getBefehlAusgefuehrtListeners())
		{
			if(!befehlAusgefuehrtListener.befehlAusgefuehrt(kontext, spieler, befehl, hasRoomChanged))
				return;
		}
	}
	public static Spieler getSpielerByName(ServerKontext kontext, String benutzerName)
	{
		for(Spieler s : kontext.getSpielerPosition().keySet())
		{
			if(benutzerName.equals(s.getName()))
			{
				return s;
			}
		}
		return null;
		
	}

	public static void fuehreTickListenerAus(ServerKontext kontext)
	{
		System.out.println("Tick");
		
		// Führe alle TickListener aus.
		for(TickListener tickListener : kontext.getTickListeners())
		{
			tickListener.tick(kontext);
		}
	}

	/**
	 * Gibt eine Liste von Spielern aus dem aktuellen Raum.
	 * 
	 * @param aktuellerRaum
	 *            der Raum
	 * @return alle Spieler in dem Raum
	 */
	public static List<String> getSpielerNamenInRaum(ServerKontext kontext, Raum aktuellerRaum)
	{
		ArrayList<String> result = new ArrayList<String>();
		assert kontext.getSpielerPosition().containsValue(aktuellerRaum);

		for(Spieler s : kontext.getSpielerPosition().keySet())
		{
			Raum raum = kontext.getSpielerPosition().get(s);
			if(raum.equals(aktuellerRaum))
			{
				result.add(s.getName());
			}
		}
		return result;
	}

	/**
	 * Gibt den Raum zurück, in dem der Spieler sich befindet.
	 * 
	 * @param spieler
	 *            der Spieler zu dem wir den Raum suchen
	 * @return der Raum in dem der Spieler ist
	 */
	public static Raum getAktuellenRaumZu(ServerKontext kontext, Spieler spieler)
	{
		return kontext.getSpielerPosition().get(spieler);
	}

	public static List<Raum> getRaeumeInDemSichSpielerAufhalten(
			ServerKontext kontext)
	{
		return new ArrayList<Raum>(kontext.getSpielerPosition().values());
	}
}
