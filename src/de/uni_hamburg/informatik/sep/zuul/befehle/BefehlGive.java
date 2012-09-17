package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.LinkedList;
import java.util.Random;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import de.uni_hamburg.informatik.sep.zuul.Inventar;
import de.uni_hamburg.informatik.sep.zuul.Item;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public class BefehlGive extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{

		if(!kontext.getAktuellerRaum().hasMaus())
		{
			kontext.schreibeNL(TextVerwalter.MAUS_KEINE_MAUS);
			return;
		}

		if(!kontext.getInventar().hasAnyKuchen())
		{
			kontext.schreibeNL(TextVerwalter.MAUS_KEIN_KRUEMEL);
			return;
		}

		Item kuchen = kontext.getInventar().getAnyKuchen();

		String richtigeRichtung = kontext.getAktuellerRaum().getMaus()
				.getRichtung();
		
		String[] moeglicheRichtungen = kontext.getAktuellerRaum().getMoeglicheAusgaenge();

		String richtung = bestimmeRichtung(kuchen, richtigeRichtung, moeglicheRichtungen);

		String richtungsangabe = String.format(
				TextVerwalter.MAUS_RICHTUNGSANGABE, richtung);
		kontext.schreibeNL(richtungsangabe);
	}

	/**
	 * @param kuchen
	 * @param richtigeRichtung
	 * @return
	 */
	static String bestimmeRichtung(Item kuchen, String richtigeRichtung, String[] moeglicheRichtungen)
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

			int randomInt = new Random().nextInt(richtungen.size());

			return richtungen.get(randomInt);
		}
		
		return null;
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_GIB;
	}

}
