package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.features.AusgaengeAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusfuehrenListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.BeinStellen;
import de.uni_hamburg.informatik.sep.zuul.server.features.Feature;
import de.uni_hamburg.informatik.sep.zuul.server.features.GewonnenTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.features.KuchenImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.features.Lebensenergie;
import de.uni_hamburg.informatik.sep.zuul.server.features.MausImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.features.RaumBeschreibungAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.server.features.RaumGeaendertListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumBauer;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class SpielLogik
{
	public static String _levelPfad;
	public ServerKontext _kontext;
	private RaumStruktur _struktur;

	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	public SpielLogik()
	{
		erstelleKontext();

		registriereListeners();
	}

	private void registriereListeners()
	{
		registriereFeature(new GewonnenTextAnzeigen());
		registriereFeature(new Lebensenergie());
		registriereFeature(new RaumBeschreibungAnzeigen());
		registriereFeature(new AusgaengeAnzeigen());
		registriereFeature(new KuchenImRaumTextAnzeigen());
		registriereFeature(new MausImRaumTextAnzeigen());
		registriereFeature(new BeinStellen());
	}

	public void erstelleKontext()
	{
		Raum start = legeRaeumeAn();
		_kontext = new ServerKontext(start);

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
	private Raum legeRaeumeAn()
	{
		IOManager manager = new IOManager();
		if(_levelPfad == null)
		{
			manager.readLevel("./level/testStruktur."+FileChooser.ZUUL_ENDUNG);
		}
		else
		{
			manager.readLevel(_levelPfad);
		}
		// TODO: immernoch statisch - datei mit NUR NOCH filechooser auswählen!!

		_struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		RaumBauer raumbauer = new RaumBauer(_struktur,
				manager.getAnzahlMaeuse());

		for(int i = 0; i < 1 /*manager.getAnzahlKatzen()*/; i++)
			// TODO mehr als eine katze nicht unterstützt atm
			Katze.erzeugeKatze(this);

		return raumbauer.getStartRaum();
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
		for(Raum raum : _struktur.getConnections().keySet())
		{
			if(isRaumZielRaum(raum))
			{
				return raum;
			}
		}
		return null;

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
		return _struktur;
	}

	public void registriereFeature(Feature feature)
	{
		if(feature instanceof TickListener)
			_tickListeners.add((TickListener) feature);
		if(feature instanceof BefehlAusgefuehrtListener)
			_befehlAusgefuehrtListeners
					.add((BefehlAusgefuehrtListener) feature);
		if(feature instanceof RaumGeaendertListener)
			_kontext.getRaumGeaendertListeners().add(
					(RaumGeaendertListener) feature);
		if(feature instanceof BefehlAusfuehrenListener)
			_befehlAusfuehrenListeners.add((BefehlAusfuehrenListener) feature);

		// TODO: Feature registrieren ( Lebenspunkte, ... )
	}

	ArrayList<TickListener> _tickListeners = new ArrayList<TickListener>();
	ArrayList<BefehlAusgefuehrtListener> _befehlAusgefuehrtListeners = new ArrayList<BefehlAusgefuehrtListener>();
	ArrayList<BefehlAusfuehrenListener> _befehlAusfuehrenListeners = new ArrayList<BefehlAusfuehrenListener>();

	void fuehreTickListenerAus()
	{
		// Führe alle TickListener aus.
		for(TickListener tickListener : _tickListeners)
		{
			tickListener.tick(_kontext);
		}

	}

	void fuehreBefehlAusgefuehrtListenerAus(Spieler spieler, Befehl befehl,
			boolean hasRoomChanged)
	{
		// Führe alle BefehlAusgefuehrtListener aus.
		for(BefehlAusgefuehrtListener befehlAusgefuehrtListener : _befehlAusgefuehrtListeners)
		{
			if(!befehlAusgefuehrtListener.befehlAusgefuehrt(_kontext, spieler,
					befehl, hasRoomChanged))
				return;
		}
	}

	boolean fuehreBefehlAusgefuehrenListenerAus(Spieler spieler, Befehl befehl)
	{
		boolean befehlAusfuehren = true;
		// Führe alle BefehlAusgefuehrtListener aus.
		for(BefehlAusfuehrenListener befehlAusfuehrenListener : _befehlAusfuehrenListeners)
		{
			befehlAusfuehren &= befehlAusfuehrenListener
					.befehlSollAusgefuehrtWerden(_kontext, spieler, befehl);
		}
		return befehlAusfuehren;
	}

}
