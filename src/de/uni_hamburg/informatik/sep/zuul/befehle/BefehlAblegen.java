package de.uni_hamburg.informatik.sep.zuul.befehle;

import com.sun.org.apache.xml.internal.security.keys.content.SPKIData;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
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
	//TODO ABlegen neues ITem enums!!!
	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(kontext.getInventar().isGefuellt())
		{
			if(getParameters().length == 0
					|| (getParameters().length == 1 && getParameters()[1]
							.equals("krümel")))
			{
				Item ablegenItem = kontext.getInventar().getAnyKuchen();
				switch (ablegenItem)
				{
				case IKuchen:
				case UKuchen:
					kontext.getAktuellerRaum().addItem(Item.UKuchen);
					break;
				case IGiftkuchen: case UGiftkuchen:
					kontext.getAktuellerRaum().addItem(Item.UGiftkuchen);
				}

				Spiel.getInstance().schreibeNL(TextVerwalter.ABLEGEN_TEXT);
			}
			else if(getParameters().length == 2)
			{
				if(getParameters()[0].equals("guter")
						&& getParameters()[1].equals("krümel"))
				{
					if(kontext.getInventar().hatDiesenKuchen(Item.IKuchen))
					{
						kontext.getAktuellerRaum().addItem(Item.UKuchen);
						kontext.getInventar().getKuchen(Item.IKuchen);
						Spiel.getInstance().schreibeNL(
								TextVerwalter.ABLEGEN_TEXT);
						return;
					}
					Spiel.getInstance().schreibeNL(
							TextVerwalter.NICHTS_ZUM_ABLEGEN);
				}
				else if(getParameters()[0].equals("schlechter")
						&& getParameters()[1].equals("krümel"))
				{
					if(kontext.getInventar().hatDiesenKuchen(Item.IGiftkuchen))
					{
						kontext.getAktuellerRaum().addItem(Item.UGiftkuchen);
						kontext.getInventar().getKuchen(Item.IGiftkuchen);
						Spiel.getInstance().schreibeNL(
								TextVerwalter.ABLEGEN_TEXT);
						return;
					}
					Spiel.getInstance().schreibeNL(
							TextVerwalter.NICHTS_ZUM_ABLEGEN);
				}
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
