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

	private Befehlswoerter _befehle; // hält die gültigen Befehlswörter

	private InputStream _in;
	private PrintStream _out;

	/**
	 * Erzeugt einen Parser.
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStream
	 */
	public Parser(InputStream in, PrintStream out, Befehlswoerter befehlswoerter)
	{
		_befehle = befehlswoerter;

		this._in = in;
		this._out = out;
	}

	/**
	 * Liest einen Befehl vom Benutzer ein und gibt ihn zurück.
	 * 
	 * @ensure Ergebnis != null
	 */
	public Befehl liefereBefehl()
	{
		String eingabezeile = leseEin();

		return parseEingabezeile(eingabezeile);
	}

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
		//		String wort1;
		//		String wort2;
		//		StringTokenizer tokenizer = new StringTokenizer(eingabezeile);
		//
		//		if(tokenizer.hasMoreTokens())
		//		{
		//			wort1 = tokenizer.nextToken(); // erstes Wort 
		//		}
		//		else
		//		{
		//			wort1 = null;
		//		}
		//		if(tokenizer.hasMoreTokens())
		//		{
		//			wort2 = tokenizer.nextToken(); // zweites Wort
		//		}
		//		else
		//		{
		//			wort2 = null;
		//		}
		//
		//		// Hinweis: Wir ignorieren den Rest der Zeile einfach.
		//
		//		// Jetzt prüfen, ob der Befehl bekannt ist. Wenn ja, erzeugen
		//		// wir das passende Befehl-Objekt. Wenn nicht, erzeugen wir
		//		// einen unbekannten Befehl mit 'null'.
		//
		//		if(_befehle.istBefehl(wort1))
		//		{
		//			return new Befehl(wort1, wort2);
		//		}
		//		return new Befehl(null, wort2);
	}

	/**
	 * 
	 * @return die gelesene Eingabezeile
	 */
	String leseEin()
	{
		String result = null;
		_out.print("> "); // Eingabeaufforderung 

		BufferedReader eingabe = new BufferedReader(new InputStreamReader(_in));
		try
		{
			result = eingabe.readLine();
		}
		catch(IOException exc)
		{
			_out.println("There was an error during reading: "
					+ exc.getMessage());
		}
		return result;
	}
}
