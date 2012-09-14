package de.uni_hamburg.informatik.sep.zuul.gui;

import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;

public class HauptfensterWerkzeug
{
	private HauptfensterUI _hauptfensterUI;
	private KonsolenausgabeWerkzeug _konsolenausgabeWerkzeug;
	private KonsoleneingabeWerkzeug _konsoleneingabeWerkzeug;
	private SteuerungWerkzeug _steuerungWerkzeug;

	public HauptfensterWerkzeug()
	{
		_hauptfensterUI = new HauptfensterUI();

		_konsolenausgabeWerkzeug = new KonsolenausgabeWerkzeug();
		_konsoleneingabeWerkzeug = new KonsoleneingabeWerkzeug();
		_steuerungWerkzeug = new SteuerungWerkzeug();

		_hauptfensterUI.addComponent(_konsolenausgabeWerkzeug.getUI());
		_hauptfensterUI.addComponent(_konsoleneingabeWerkzeug.getUI());
		_hauptfensterUI.addComponent(_steuerungWerkzeug.getUI());

		_hauptfensterUI.zeigeFenter();
	}

	/**
	 * Zeigt das Fenster an
	 */
	public void zeigeFenster()
	{
		//TODO: WTF
	}

	/**
	 * Fügr einen neuen Listener hinzu, der auf Eingaben reagiert
	 * 
	 * @param arg0
	 *            der hinzuzufügende Listener
	 */
	public void addNeueEingabeListener(ActionListener arg0)
	{
		_konsoleneingabeWerkzeug.addNeueZeileListener(arg0);
	}

	/**
	 * Liest den Eingabestring aus und leert das Eingabefeld anschließend
	 * 
	 * @return die Eingabe
	 */
	public String leseEingabeString()
	{
		return _konsoleneingabeWerkzeug.leseEingabeUndLeere();
	}

	/**
	 * Schreibt nachricht in den Output, hänge einen Zeilenumbruch an.
	 * Vergleichbar mit PrintStream.println()
	 * 
	 * @param nachricht
	 *            Die auszugebende Nachricht
	 */
	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_konsolenausgabeWerkzeug.schreibeNewLine();

	}

	/**
	 * Schreibt nachricht in den Output. Vergleichbar mit PrintStream.print()
	 * 
	 * @param nachricht
	 *            Die auszugebende Nachricht
	 */
	public void schreibe(String nachricht)
	{
		_konsolenausgabeWerkzeug.schreibeString(nachricht);

	}

}
