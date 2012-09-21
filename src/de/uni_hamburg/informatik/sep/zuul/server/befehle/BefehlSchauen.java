package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Stack;

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

			Stack<Item> raumItems = (Stack<Item>) nebenRaum.getItems().clone();
			Spiel.getInstance().schreibeNL(
					"Zu sehen " + (raumItems.size() == 1 ? "ist" : "sind")
							+ ":");

			int anzahlKruemel = 0;
			boolean gegengift = false;
			boolean hatDinge = false;

			if(raumItems.size() != 0)
			{

				while(raumItems.size() != 0)
				{
					if(raumItems.get(0) == Item.UKuchen
							|| raumItems.get(0) == Item.UGiftkuchen
							|| raumItems.get(0) == Item.IKuchen
							|| raumItems.get(0) == Item.UKuchen)
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
