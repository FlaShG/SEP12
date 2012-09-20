package de.uni_hamburg.informatik.sep.zuul.spiel;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.features.AusgängeAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.GewonnenTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.features.KuchenImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.Lebensenergie;
import de.uni_hamburg.informatik.sep.zuul.features.MausImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.RaumBeschreibungAnzeigen;

public class SpielLogik
{
	private static String _level;

	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	public static SpielKontext erstelleKontext(String level)
	{
		_level = level;

		final SpielKontext kontext = new SpielKontext(legeRaeumeAn(),
				START_ENERGIE, new Inventar());

		new RaumBeschreibungAnzeigen().registerToKontext(kontext);

		new GewonnenTextAnzeigen().registerToKontext(kontext);

		new Lebensenergie().registerToKontext(kontext);

		new KuchenImRaumTextAnzeigen().registerToKontext(kontext);

		new MausImRaumTextAnzeigen().registerToKontext(kontext);

		new AusgängeAnzeigen().registerToKontext(kontext);

		new Katze(kontext.getAktuellerRaum().getAusgang("süd").getAusgang("süd")).registerToKontext(kontext);

		return kontext;
	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private static Raum legeRaeumeAn()
	{
		IOManager manager = new IOManager();
		if(_level == null)
		{
			manager.readLevel("./xml_dateien/testStruktur.xml");
		}
		else
		{
			manager.readLevel(_level);
		}
		// TODO: noch statisch - datei mit filechooser auswählen!!

		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		RaumBauer raumbauer = new RaumBauer(struktur);
		return raumbauer.getStartRaum();
	}

	/**
	 * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
	 * befindet.
	 */
	public static void zeigeRaumbeschreibung(SpielKontext kontext)
	{
		Spiel.getInstance().schreibeNL(
				kontext.getAktuellerRaum().getBeschreibung());
	}

	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an.
	 */
	public static void zeigeAusgaenge(SpielKontext kontext)
	{
		Spiel.getInstance().schreibe(TextVerwalter.AUSGAENGE + ": ");

		for(String s : kontext.getAktuellerRaum().getMoeglicheAusgaenge())
		{
			Spiel.getInstance().schreibe(s + " ");
		}

		Spiel.getInstance().schreibeNL("");
	}

	/**
	 * Gibt eine Nachricht aus und beendet das Spiel
	 * 
	 * @param nachricht
	 *            die Nachricht, die vor dem Spielende ausgegeben werden soll
	 */
	public static void beendeSpiel(SpielKontext kontext, String nachricht)
	{
		kontext.spielZuende();
		Spiel.getInstance().schreibeNL(nachricht);
		Spiel.getInstance().beendeSpiel();
	}

	public static boolean isRaumZielRaum(Raum raum)
	{
		// TODO: Ugly !!!
		return raum.getRaumart() == RaumArt.Ende;
	}
}
