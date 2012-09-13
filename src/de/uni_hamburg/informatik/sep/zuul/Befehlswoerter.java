package de.uni_hamburg.informatik.sep.zuul;

import java.util.Arrays;

/**
 * Kennt alle gültigen Befehle.
 */
public class Befehlswoerter
{
	final String[] _gueltigeBefehle;

	/**
	 * Erzeugt ein Objekt der Klasse Befehlswoerter.
	 */
	public Befehlswoerter(String[] gueltigeBefehle)
	{
		Arrays.sort(_gueltigeBefehle = gueltigeBefehle.clone());
	}

	/**
	 * Prüft, ob eine gegebene Zeichenkette ein gültiger Befehl ist. Liefert
	 * 'true', wenn das der Fall ist, 'false' sonst.
	 */
	public boolean istBefehl(String eingabe)
	{
		return Arrays.binarySearch(_gueltigeBefehle, eingabe) >= 0;

		/*
		//binary search ist bessertoller
		for(int i = 0; i < gueltigeBefehle.length; i++)
		{
			if(gueltigeBefehle[i].equals(eingabe))
			{
				return true;
			}
		}
		// Wenn wir hierher gelangen, wurde die Eingabe nicht
		// in den gültigen Befehlswörtern gefunden.
		return false;
		*/
	}
	
	public String[] gibGueltigeBefehlswoerter()
	{
		return _gueltigeBefehle.clone();
	}
}
