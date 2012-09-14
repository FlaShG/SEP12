package de.uni_hamburg.informatik.sep.zuul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.befehle.BefehlFactory;

/**
 * Dieser Parser liest Benutzereingaben und wandelt sie in Befehle für das
 * Adventure-Game um. Bei jedem Aufruf liest er eine Zeile von der Konsole und
 * versucht, diese als einen Befehl aus bis zu zwei Wörtern zu interpretieren.
 * Er liefert den Befehl als ein Objekt der Klasse Befehl zurück.
 * 
 * Der Parser verfügt über einen Satz an bekannten Befehlen. Er vergleicht die
 * Eingabe mit diesen Befehlen. Wenn die Eingabe keinen bekannten Befehl
 * enthält, dann liefert der Parser ein als unbekannter Befehl gekennzeichnetes
 * Objekt zurück.
 * 
 */
public class Parser
{
	/**
	 * Liest einen Befehl vom Benutzer ein und gibt ihn zurück.
	 * 
	 * @ensure Ergebnis != null
	 */
//	public Befehl liefereBefehl()
//	{
//		String eingabezeile = leseEin();
//
//		return parseEingabezeile(eingabezeile);
//	}

	/**
	 * @param eingabezeile
	 * @return geparster Befehl
	 */
	Befehl parseEingabezeile(String eingabezeile)
	{
		String[] input = eingabezeile.split(" +");

		String[] parameter = new String[0];
		String befehl = "";

		if(input.length > 0)
		{
			befehl = input[0];
			if(input.length > 1)
				parameter = Arrays.copyOfRange(input, 1, input.length);

		}

		return BefehlFactory.get(befehl, parameter);
	}

	/**
	 * 
	 * @return die gelesene Eingabezeile
	 */
	//String leseEin()
//	{
//		String result = null;
//		//_out.print("> "); // Eingabeaufforderung //TODO fixme
//
//		//BufferedReader eingabe = new BufferedReader(new InputStreamReader(_in));
//		try
//		{
//			result = eingabe.readLine();
//		}
//		catch(IOException exc)
//		{
//			//_out.println("There was an error during reading: "
//			//		+ exc.getMessage());
//		}
//		return result;
//	}

	public Befehl liefereBefehl(String str)
	{
		return parseEingabezeile(str);
		
	}
}
