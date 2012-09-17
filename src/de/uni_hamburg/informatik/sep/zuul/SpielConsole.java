package de.uni_hamburg.informatik.sep.zuul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;


public class SpielConsole extends Spiel
{

	@Override
	public void schreibeNL(String nachricht)
	{
		System.out.println(nachricht);

	}

	@Override
	public void schreibe(String nachricht)
	{
		System.out.println(nachricht);
	}
	
	@Override
	protected void spielen()
	{
		zeigeWillkommenstext();
		
		while(!_kontext.isSpielZuende())
		{
			String eingabezeile = leseZeileEin();
	        
	        Befehl befehl = _parser.liefereBefehl(eingabezeile);
	        
	        befehl.ausfuehren(_kontext);

		}
		
	}

	/**
	 * @return
	 */
	String leseZeileEin()
	{
		System.out.print("> ");
		
		String eingabezeile = "";
		BufferedReader eingabe = new BufferedReader(new InputStreamReader(
		        System.in));
		try {
		    eingabezeile = eingabe.readLine();
		} catch (IOException exc) {
		    System.out.println("There was an error during reading: "
		            + exc.getMessage());
		}
		return eingabezeile;
	}

}
