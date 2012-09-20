package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.LinkedList;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettereMaus implements Befehl
{

	public static final String BEFEHLSNAME = TextVerwalter.BEFEHL_FEED + " "
			+ "maus";

	/**
	 * Bestimmt die Richtung, die die Maus empfiehlt abhängig davon, ob der Kuchen giftig ist.
	 */
	static String bestimmeRichtung(Item kuchen, String richtigeRichtung,
			String[] moeglicheRichtungen)
	{
		if(kuchen == Item.Kuchen)
		{
			return richtigeRichtung;
		}
		if(kuchen == Item.Giftkuchen)
		{
			LinkedList<String> richtungen = new LinkedList<String>();

			for(String richtung : moeglicheRichtungen)
				richtungen.add(richtung);

			richtungen.remove(richtigeRichtung);

			String falscheRichtung = FancyFunction.getRandomEntry(richtungen);

			// Falls der Raum nur einen Ausgang hat.
			if(falscheRichtung == null)
				falscheRichtung = richtigeRichtung;

			return falscheRichtung;
		}

		return null;
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);
		return raum.hasMaus() && spieler.getInventar().hasAnyKuchen();
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		Item kuchen = spieler.getInventar().getAnyKuchen();
		Raum aktuellerRaum = kontext.getAktuellenRaumZu(spieler);

		String richtigeRichtung = aktuellerRaum.getMaus().getRichtung();

		String[] moeglicheRichtungen = aktuellerRaum.getMoeglicheAusgaenge();

		String richtung = bestimmeRichtung(kuchen, richtigeRichtung,
				moeglicheRichtungen);

		String richtungsangabe = String.format(
				TextVerwalter.MAUS_RICHTUNGSANGABE, richtung);

		BefehlFactory.schreibeNL(kontext, spieler, richtungsangabe);

		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.MAUS_KEIN_KRUEMEL);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}

}
