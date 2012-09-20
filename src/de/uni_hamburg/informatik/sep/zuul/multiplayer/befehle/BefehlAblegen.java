package de.uni_hamburg.informatik.sep.zuul.multiplayer.befehle;

import com.sun.org.apache.xml.internal.security.keys.content.SPKIData;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

/**
 * Legt ein Item in den aktuellen Raum, wenn eines im INventar vorhanden ist.
 * 
 * @author 1fechner
 * 
 */
final class BefehlAblegen extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(kontext.getInventar().isGefuellt())
		{
			if(getParameters().length == 0)
			{
				kontext.getAktuellerRaum().addItem(
						kontext.getInventar().getAnyKuchen());
				Spiel.getInstance().schreibeNL(TextVerwalter.ABLEGEN_TEXT);
			}
		}
		else
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.NICHTS_ZUM_ABLEGEN);
		}

	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_ABLEGEN;
	}

}
