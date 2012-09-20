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
			 leseZeileEin();
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
	public void spielen(String level)
	{
		_kontext = SpielLogik.erstelleKontext(level);

		zeigeWillkommenstext(_kontext);

		while(!_kontext.isSpielZuende())
		{
			verarbeiteEingabe(leseZeileEin());
		}

		System.out
				.println("Wollen Sie noch einmal spielen? Dann antworten Sie mit 'Ja'");
		String zeile = leseZeileEin();
		if(zeile.equals("Ja"))
			restart(level);

	}

	protected void restart(String level)
	{
		try
		{
			Runtime.getRuntime().exec("cls");
		}
		catch(IOException e)
		{
		}

		spielen(level);
	}

	

}
