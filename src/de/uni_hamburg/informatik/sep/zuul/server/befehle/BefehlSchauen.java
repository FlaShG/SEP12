package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Arrays;
import java.util.Stack;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public final class BefehlSchauen implements Befehl
{

	String[] _richtungen = new String[] { TextVerwalter.RICHTUNG_NORDEN,
			TextVerwalter.RICHTUNG_OSTEN, TextVerwalter.RICHTUNG_SUEDEN,
			TextVerwalter.RICHTUNG_WESTEN };

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		if(befehlszeile.getGeparsteZeile().size() != 2)
			return false;

		String richtung = extrahiereRichtung(befehlszeile);
		return richtung != null
				&& getRaumFuerRichtung(kontext, spieler, richtung) != null;
	}

	/**
	 * @param befehlszeile
	 */
	public String extrahiereRichtung(Befehlszeile befehlszeile)
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

		kontext.schreibeAnSpieler(spieler, "Dr.Little schaut nach " + richtung
				+ "en.");
		kontext.schreibeAnSpieler(spieler, "Er sieht: " + nebenRaum.getName());

		Stack<Item> raumItems = nebenRaum.getItems();
		kontext.schreibeAnSpieler(spieler, "Zu sehen "
				+ (raumItems.size() == 1 ? "ist" : "sind") + ":");

		int anzahlKruemel = 0;
		boolean gegengift = false;
		boolean hatDinge = false;

		for(Item item : raumItems)
		{

			if(item.isAnyKuchen())
			{
				anzahlKruemel++;
			}
			else if(item == Item.Gegengift)
			{
				gegengift = true;
			}
		}

		if(gegengift)
		{
			kontext.schreibeAnSpieler(spieler, "Das Gegengift!");
			hatDinge = true;
		}

		if(anzahlKruemel != 0)
		{
			kontext.schreibeAnSpieler(spieler, anzahlKruemel + " Krümel");
			hatDinge = true;
		}

		if(nebenRaum.hasMaus())
		{
			kontext.schreibeAnSpieler(spieler, "Eine Maus");
			hatDinge = true;
		}

		if(nebenRaum.hasKatze())
		{
			kontext.schreibeAnSpieler(spieler, "Eine Katze");
			hatDinge = true;
		}

		if(!hatDinge)
		{
			kontext.schreibeAnSpieler(spieler, "nur uninteressante Sachen");
		}
		return true;
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
			kontext.schreibeAnSpieler(spieler, TextVerwalter.KEINESCHAURICHTUNG);
		}
		// Keine gültige Richtung!
		else if(extrahiereRichtung(befehlszeile) == null)
			kontext.schreibeAnSpieler(spieler, TextVerwalter.KEINRAUMZUMSCHAUN);

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
