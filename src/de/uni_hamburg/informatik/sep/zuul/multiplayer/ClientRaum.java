package de.uni_hamburg.informatik.sep.zuul.multiplayer;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public class ClientRaum
{
	boolean aktuellerRaumHatKatze;
	
	public ClientRaum(SpielKontext kontext)
	{
		aktuellerRaumHatKatze = kontext.isKatzeImAktuellenRaum();
	}
	
	
	
}
