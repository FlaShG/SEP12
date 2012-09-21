package de.uni_hamburg.informatik.sep.zuul;

import java.util.Arrays;

public class SpielMüll
{

	/**
	 * Schablonenmethode für Aktionen bei beendetem Spiel.
	 */
	public void beendeSpiel()
	{

	}

	public void spielen(String level)
	{
		_kontext = SpielLogik.erstelleKontext(level);

		zeigeWillkommenstext(_kontext);
	}

	/**
	 * Gibt einen Begrüßungstext für den Spieler aus.
	 */
	protected void zeigeWillkommenstext(SpielKontext kontext)
	{
		schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		schreibeNL("");
		SpielLogik.zeigeRaumbeschreibung(kontext);
		SpielLogik.zeigeAusgaenge(kontext);
	}

	protected void verarbeiteEingabe(String eingabezeile)
	{
		//		String eingabezeile = leseZeileEin();

		Befehl befehl = parseEingabezeile(eingabezeile);
		befehl.ausfuehren(_kontext);

		if(!_kontext.isSpielZuende())
			_kontext.fireTickEvent();
	}

	//	protected abstract String leseZeileEin();

	protected void restart(String level)
	{
		spielen(level);
	}

	/**
	 * @param eingabezeile
	 * @return geparster Befehl
	 */
	public static Befehl parseEingabezeile(String eingabezeile)
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

}
