package de.uni_hamburg.informatik.sep.zuul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;

public class ClientConsole extends Client
{

	public ClientConsole(String serverName, String serverIP)
	{
		super(serverName, serverIP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void schreibeText(String text)
	{
		System.out.println(text);
	}
	
	@Override
	public void run()
	{
		while(!_isSpielzuEnde)
		{
			String nachricht = leseZeileEin();
			
			verarbeiteEingabe(nachricht);
			
			//TODO warten einbauen
		}
	}
	
	
	/**
	 * @return
	 */
	protected String leseZeileEin()
	{
		System.out.print("> ");

		String eingabezeile = "";
		BufferedReader eingabe = new BufferedReader(new InputStreamReader(
				System.in));
		try
		{
			eingabezeile = eingabe.readLine();
		}
		catch(IOException exc)
		{
			System.out.println("There was an error during reading: "
					+ exc.getMessage());
		}
		return eingabezeile;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
