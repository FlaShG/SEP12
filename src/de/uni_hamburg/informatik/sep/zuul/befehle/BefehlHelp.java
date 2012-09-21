package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlHelp extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_HILFE;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		Spiel.getInstance().schreibeNL(TextVerwalter.HILFETEXT);

		// essen | hilfe | nehmen | ost | n | o | w | süd | s | gib | gehe | nord | beenden | west 
		// gehe nehme gib essen | schauen
		// ost süd nord west
		// o s n w
		// hilfe beenden
		// 

		if(getParameters().length == 0)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.HILFETEXT);
		}
		else if(getParameters().length >= 1)
		{
			switch (getParameters()[0])
			{
			case "essen":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_EAT);
				break;
			case "gehen":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_GO);
				break;
			case "gib":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_GIVE);
				break;
			case "füttern":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_FEED);
				break;
			case "hilfe":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_HELP);
				break;
			case "beenden":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_QUIT);
				break;
			case "laden":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_LOAD);
				break;
			case "inventar":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_INVENTAR);
				break;
			case "ablegen":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_ABLEGEN);
				break;
			case "schauen":
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFE_LOOK);
				break;
			default:
				Spiel.getInstance().schreibeNL(TextVerwalter.HILFETEXT);
			}
		}
		else
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.HILFETEXT);
		}
	}
}