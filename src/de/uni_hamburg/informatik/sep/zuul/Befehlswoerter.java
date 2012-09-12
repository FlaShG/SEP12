package de.uni_hamburg.informatik.sep.zuul;

import java.util.Arrays;

/**
 * Kennt alle gültigen Befehle.
 */
public class Befehlswoerter
{
	private static final String gueltigeBefehle[] = { "go", "quit", "help" };

	/**
	 * Erzeugt ein Objekt der Klasse Befehlswoerter.
	 */
	public Befehlswoerter()
	{
		Arrays.sort(gueltigeBefehle);
	}

	/**
	 * Prüft, ob eine gegebene Zeichenkette ein gültiger Befehl ist. Liefert
	 * 'true', wenn das der Fall ist, 'false' sonst.
	 */
	public boolean istBefehl(String eingabe)
	{
		return Arrays.binarySearch(gueltigeBefehle, eingabe) >= 0;

		/*
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
}
