package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.LinkedList;

import de.uni_hamburg.informatik.sep.zuul.FancyFunction;
import de.uni_hamburg.informatik.sep.zuul.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlFeed extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{

		if(kontext.getAktuellerRaum().hasMaus())
		{
			if(!kontext.getInventar().hasAnyKuchen())
			{
				Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_KEIN_KRUEMEL);
				return;
			}

			Item kuchen = kontext.getInventar().getAnyKuchen();

			String richtigeRichtung = kontext.getAktuellerRaum().getMaus()
					.getRichtung();

			String[] moeglicheRichtungen = kontext.getAktuellerRaum()
					.getMoeglicheAusgaenge();

			String richtung = bestimmeRichtung(kuchen, richtigeRichtung,
					moeglicheRichtungen);

			String richtungsangabe = String.format(
					TextVerwalter.MAUS_RICHTUNGSANGABE, richtung);
			Spiel.getInstance().schreibeNL(richtungsangabe);
			return;
		}

		if(kontext.isKatzeImAktuellenRaum())
		{

			Katze katze = findeKatze(kontext);
			if(katze == null)
			{
				// Sollte nicht passieren…
				return;
			}
			if(!kontext.getInventar().hasAnyKuchen())
			{
				Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_KEIN_KRUEMEL);
				return;
			}
			if(katze.isSatt())
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.KATZE_HAT_KEINEN_HUNGER);
				return;
			}
			Item kuchen = kontext.getInventar().getAnyKuchen();
			katze.fuettere(kontext, kuchen);

			return;
		}

		Spiel.getInstance().schreibeNL(
				TextVerwalter.BEFEHL_FEED_NICHTS_DA_ZUM_FUETTERN);
	}

	/**
	 * @param kontext
	 * @return
	 */
	private Katze findeKatze(SpielKontext kontext)
	{
		for(Katze k : kontext.getKatzen())
		{
			if(k.getRaum() == kontext.getAktuellerRaum())
				return k;
		}
		return null;
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_FEED;
	}

	/**
	 * @param kuchen
	 * @param richtigeRichtung
	 * @return
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

}
