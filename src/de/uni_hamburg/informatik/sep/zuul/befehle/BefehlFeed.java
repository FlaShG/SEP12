package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Katze;
import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlFeed extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		Katze katze = findeKatze(kontext);
		if(katze == null)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_NICHT_DA_ZUM_FUETTERN);
			return;
		}
		if(!kontext.getInventar().hasAnyKuchen())
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_KEIN_KRUEMEL);
			return;
		}
		if(katze.isSatt())
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KATZE_HAT_KEINEN_HUNGER);
			return;
		}
		Item kuchen = kontext.getInventar().getAnyKuchen();
		katze.fuettere(kontext, kuchen);
		
		
	}

	/**
	 * @param kontext
	 * @return 
	 */
	private Katze findeKatze(SpielKontext kontext)
	{
		for(Katze k: kontext.getKatzen())
		{
			if(k.getRaum() == kontext.getAktuellerRaum())
				return k;
		}
		return null;
	}

	@Override
	public String getBefehlsname()
	{
		// TODO Auto-generated method stub
		return TextVerwalter.BEFEHL_FEED;
	}

}
