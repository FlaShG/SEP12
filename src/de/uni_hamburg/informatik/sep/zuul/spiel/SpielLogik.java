package de.uni_hamburg.informatik.sep.zuul.spiel;


import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.features.AusgängeAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.GewonnenTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.KuchenImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.Lebensenergie;
import de.uni_hamburg.informatik.sep.zuul.features.MausImRaumTextAnzeigen;
import de.uni_hamburg.informatik.sep.zuul.features.RaumBeschreibungAnzeigen;

public class SpielLogik
{
	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	public static SpielKontext erstelleKontext()
	{
		final SpielKontext kontext = new SpielKontext();
		kontext.setLebensEnergie(START_ENERGIE);
		kontext.setInventar(new Inventar());
		legeRaeumeAn(kontext);

		new RaumBeschreibungAnzeigen().registerToKontext(kontext);

		new GewonnenTextAnzeigen().registerToKontext(kontext);

		new Lebensenergie().registerToKontext(kontext);

		new KuchenImRaumTextAnzeigen().registerToKontext(kontext);

		new MausImRaumTextAnzeigen().registerToKontext(kontext);

		new AusgängeAnzeigen().registerToKontext(kontext);

		return kontext;
	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private static void legeRaeumeAn(SpielKontext kontext)
	{
		IOManager manager = new IOManager();
		manager.readLevel("./xml_dateien/testStruktur.xml");
		//TODO: noch statisch - datei mit filechooser auswählen!!

		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		RaumBauer raumbauer = new RaumBauer(struktur);
		kontext.setAktuellerRaum(raumbauer.getStartRaum());
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
	}

	public static boolean isRaumZielRaum(Raum raum)
	{
		return raum.getRaumart() == RaumArt.Ende;
	}
}
