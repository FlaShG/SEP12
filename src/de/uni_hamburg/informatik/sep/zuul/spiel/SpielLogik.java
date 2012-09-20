package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.Katze;
import de.uni_hamburg.informatik.sep.zuul.Spiel;

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

		final SpielKontext kontext = new SpielKontext(legeRaeumeAn(), START_ENERGIE, new Inventar());

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(hasRoomChanged)
					zeigeRaumbeschreibung(kontext);
				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(kontext.getAktuellerRaum().getNaechstesItem() == Item.Gegengift)
				{
					beendeSpiel(kontext, TextVerwalter.SIEGTEXT + "\n"
							+ TextVerwalter.BEENDENTEXT);
					return false;
				}
				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(hasRoomChanged)
					Spiel.getInstance().schreibeNL(
							TextVerwalter.RAUMWECHSELTEXT
									+ kontext.getLebensEnergie());
				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(hasRoomChanged) // TODO: || KuchenAufgehoben
					switch (kontext.getAktuellerRaum().getNaechstesItem())
					{
					case UKuchen:
					case UGiftkuchen:
					case IKuchen:
					case IGiftkuchen:
						Spiel.getInstance().schreibeNL(
								TextVerwalter.KUCHENIMRAUMTEXT);
						break;
					}
				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				// Maus
				if(hasRoomChanged && kontext.getAktuellerRaum().hasMaus())
				{
					Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_GEFUNDEN);
					Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_FRAGE);
				}
				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(kontext.getLebensEnergie() <= 0)
				{
					beendeSpiel(kontext, TextVerwalter.NIEDERLAGETEXT);
					return false;
				}

				return true;
			}
		});

		kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(hasRoomChanged)
					zeigeAusgaenge(kontext);
				return true;
			}
		});

		kontext.addPropertyChangeListener("AktuellerRaum",
				new PropertyChangeListener()
				{
					@Override
					public void propertyChange(PropertyChangeEvent evt)
					{
						kontext.setLebensEnergie(kontext.getLebensEnergie()
								- RAUMWECHSEL_ENERGIE_KOSTEN);
						
						
					}
				});
		
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
