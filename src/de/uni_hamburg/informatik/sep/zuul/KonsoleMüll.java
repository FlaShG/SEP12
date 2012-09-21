package de.uni_hamburg.informatik.sep.zuul;

import java.io.IOException;

public class KonsoleMüll
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
