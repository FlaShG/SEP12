package de.uni_hamburg.informatik.sep.zuul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;

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
		_kontext = SpielLogik.erstelleKontext();

		zeigeWillkommenstext(_kontext);

		while(!_kontext.isSpielZuende())
		{
			verarbeiteEingabe(leseZeileEin());
		}

		System.out
				.println("Wollen Sie noch einmal spielen? Dann antworten Sie mit 'Ja'");
		String zeile = leseZeileEin();
		if(zeile.equals("Ja"))
			restart();

	}

	protected void restart()
	{
		try
		{
			Runtime.getRuntime().exec("cls");
		}
		catch(IOException e)
		{
		}

		spielen();
	}

	/**
	 * Liest eine Zeile von der Console.
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
