package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public class BefehlGib extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length>0 && getParameters()[0].equals("krümel"))
		{
			if(!kontext.getAktuellerRaum().hasMaus())
			{
				kontext.schreibeNL(TextVerwalter.MAUS_KEINE_MAUS);
				return;
			}
			if(false) // TODO Inventar.has(Kruemel)
			{
				kontext.schreibeNL(TextVerwalter.MAUS_KEIN_KRUEMEL);
				return;
			}
			// TODO Inventar.remove(kruemel)
			
			String richtungsangabe = String.format(TextVerwalter.MAUS_RICHTUNGSANGABE, kontext.getAktuellerRaum().getMaus().getRichtung());
			kontext.schreibeNL(richtungsangabe);
		}
		else
		{
			BefehlFactory.unbekannnterBefehl.ausfuehren(kontext);
		}
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_GIB;
	}

}
