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
		if(getParameters().length > 0 && getParameters()[0].equals("krümel"))
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

			String richtung = bestimmeRichtung(kuchen, richtigeRichtung);

			String richtungsangabe = String.format(
					TextVerwalter.MAUS_RICHTUNGSANGABE, richtung);
			kontext.schreibeNL(richtungsangabe);

		}
		else
		{
			BefehlFactory.unbekannnterBefehl.ausfuehren(kontext);
		}
	}

	/**
	 * @param kuchen
	 * @param richtigeRichtung
	 * @return
	 */
	static String bestimmeRichtung(Item kuchen, String richtigeRichtung)
	{
		String richtung = null;

		if(kuchen == Item.Kuchen)
		{
			richtung = richtigeRichtung;
		}
		else if(kuchen == Item.Giftkuchen)
		{
			LinkedList<String> Richtungen = new LinkedList<String>();
			Richtungen.add(TextVerwalter.RICHTUNG_NORDEN);
			Richtungen.add(TextVerwalter.RICHTUNG_OSTEN);
			Richtungen.add(TextVerwalter.RICHTUNG_SUEDEN);
			Richtungen.add(TextVerwalter.RICHTUNG_WESTEN);

			Richtungen.remove(richtigeRichtung);

			int randomInt = new Random().nextInt(3);

			richtung = Richtungen.get(randomInt);
		}
		return richtung;
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_GIB;
	}

}
