package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.Stack;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public final class BefehlSchauen extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINESCHAURICHTUNG);
			return;
		}
		String richtung = getParameters()[0];

		Raum nebenRaum = kontext.getAktuellerRaum().getAusgang(richtung);

		if(nebenRaum == null)
		{

		}
		else
		{
			Spiel.getInstance().schreibeNL(
					"Dr.Little schaut nach " + richtung + "en.");
			Spiel.getInstance().schreibeNL("Er sieht: " + nebenRaum.getName());
			Spiel.getInstance().schreibeNL("Zusehen sind :");

			Stack<Item> raumItems = (Stack<Item>) nebenRaum.getItems().clone();

			int anzahlKruemel = 0;
			boolean gegengift = false;
			boolean hatDinge = false;

			if(raumItems.size() != 0)
			{

				while(raumItems.size() != 0)
				{
					if(raumItems.get(0) == Item.Kuchen
							|| raumItems.get(0) == Item.Giftkuchen)
					{
						anzahlKruemel++;
					}
					else if(raumItems.get(0) == Item.Gegengift)
					{
						gegengift = true;
					}

					raumItems.remove(0);
				}

			}
			if(gegengift)
			{
				Spiel.getInstance().schreibeNL("Das Gegengift!");
				hatDinge = true;
			}

			if(anzahlKruemel != 0)
			{
				Spiel.getInstance().schreibeNL(anzahlKruemel + " Krümel");
				hatDinge = true;
			}

			if(nebenRaum.hasMaus())
			{
				Spiel.getInstance().schreibeNL("Eine Maus");
				hatDinge = true;
			}

//			if(nebenRaum.hasKatze())
//			{
//				Spiel.getInstance().schreibeNL("Eine Katze");
//				hatDinge = true;
//			}

			
			if(!hatDinge)
			{
				Spiel.getInstance().schreibeNL("nur uninteressante Sachen");
			}
			
		}

	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_SCHAUEN;
	}

}
