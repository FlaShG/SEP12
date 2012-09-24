package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Arrays;
import java.util.Stack;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public final class BefehlSchauen implements Befehl
{

	String[] _richtungen = new String[] { TextVerwalter.RICHTUNG_NORDEN,
			TextVerwalter.RICHTUNG_OSTEN, TextVerwalter.RICHTUNG_SUEDEN,
			TextVerwalter.RICHTUNG_WESTEN };

	// TODO: broken

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		String richtung = extrahiereRichtung(befehlszeile);
		return befehlszeile.getGeparsteZeile().size() == 2 && richtung != null
				&& getRaumFuerRichtung(kontext, spieler, richtung) != null;
	}

	/**
	 * @param befehlszeile
	 */
	private String extrahiereRichtung(Befehlszeile befehlszeile)
	{
		String richtung = befehlszeile.getGeparsteZeile().get(1);
		if(Arrays.asList(_richtungen).contains(richtung))
			return richtung;
		return null;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		//TODO auslagern in Methoden
		String richtung = extrahiereRichtung(befehlszeile);
		Raum nebenRaum = getRaumFuerRichtung(kontext, spieler, richtung);

		BefehlFactory.schreibeNL(kontext, spieler, "Dr.Little schaut nach "
				+ richtung + "en.");
		BefehlFactory.schreibeNL(kontext, spieler,
				"Er sieht: " + nebenRaum.getName());

		Stack<Item> raumItems = (Stack<Item>) nebenRaum.getItems().clone();
		BefehlFactory.schreibeNL(kontext, spieler,
				"Zu sehen " + (raumItems.size() == 1 ? "ist" : "sind") + ":");

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
			BefehlFactory.schreibeNL(kontext, spieler, "Das Gegengift!");
			hatDinge = true;
		}

		if(anzahlKruemel != 0)
		{
			BefehlFactory.schreibeNL(kontext, spieler, anzahlKruemel
					+ " Krümel");
			hatDinge = true;
		}

		if(nebenRaum.hasMaus())
		{
			BefehlFactory.schreibeNL(kontext, spieler, "Eine Maus");
			hatDinge = true;
		}

		if(nebenRaum.hasKatze())
		{
			BefehlFactory.schreibeNL(kontext, spieler, "Eine Katze");
			hatDinge = true;
		}

		if(!hatDinge)
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					"nur uninteressante Sachen");
		}
		return false;
	}

	/**
	 * @param kontext
	 * @param spieler
	 * @param richtung
	 * @return
	 */
	private Raum getRaumFuerRichtung(ServerKontext kontext, Spieler spieler,
			String richtung)
	{
		return kontext.getAktuellenRaumZu(spieler).getAusgang(richtung);
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		if(befehlszeile.getGeparsteZeile().size() < 2)
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KEINESCHAURICHTUNG);
		}
		// Keine gültige Richtung!
		else if(extrahiereRichtung(befehlszeile) == null)
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KEINRAUMZUMSCHAUN);

	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_SCHAUEN };
	}

	@Override
	public String getHilfe()
	{
		// TODO Auto-generated method stub
		return TextVerwalter.HILFE_LOOK;
	}

}
