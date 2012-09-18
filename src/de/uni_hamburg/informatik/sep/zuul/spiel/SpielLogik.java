package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.Spiel;

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

		kontext.addPropertyChangeListener("AktuellerRaum",
				new PropertyChangeListener()
				{
					@Override
					public void propertyChange(PropertyChangeEvent evt)
					{
						zeigeRaumbeschreibung(kontext);

						if(kontext.getAktuellerRaum().getNaechstesItem() == Item.Gegengift)
						{
							beendeSpiel(kontext, TextVerwalter.SIEGTEXT + "\n"
									+ TextVerwalter.BEENDENTEXT);
							return;
						}

						kontext.setLebensEnergie(kontext.getLebensEnergie()
								- RAUMWECHSEL_ENERGIE_KOSTEN);
						Spiel.getInstance().schreibeNL(
								TextVerwalter.RAUMWECHSELTEXT
										+ kontext.getLebensEnergie());

						switch (kontext.getAktuellerRaum().getNaechstesItem())
						{
						case Kuchen:
						case Giftkuchen:
							Spiel.getInstance().schreibeNL(
									TextVerwalter.KUCHENIMRAUMTEXT);
							break;
						}

						// Maus
						if(kontext.getAktuellerRaum().hasMaus())
						{
							Spiel.getInstance().schreibeNL(
									TextVerwalter.MAUS_GEFUNDEN);
							Spiel.getInstance().schreibeNL(
									TextVerwalter.MAUS_FRAGE);
						}

						if(!kontext.isSpielZuende()
								&& kontext.getLebensEnergie() <= 0)
						{
							beendeSpiel(kontext, TextVerwalter.NIEDERLAGETEXT);
						}

						if(!kontext.isSpielZuende())
						{
							zeigeAusgaenge(kontext);
						}

					}
				});
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
	}

	public static boolean isRaumZielRaum(Raum raum)
	{
		return raum.getRaumart() == RaumArt.Ende;
	}
}
