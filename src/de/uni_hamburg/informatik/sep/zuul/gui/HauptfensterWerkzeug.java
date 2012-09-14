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
		
		_hauptfensterUI.addFenster(_konsolenausgabeWerkzeug.getUI());
		_hauptfensterUI.addFenster(_konsoleneingabeWerkzeug.getUI());
		_hauptfensterUI.addFenster(_steuerungWerkzeug.getUI());
		
		_hauptfensterUI.zeigeFenter();
	}
	
	public void zeigeFenster()
	{
		
	}
	
	public void addNeueEingabeListener(ActionListener arg0)
	{
		_konsoleneingabeWerkzeug.addNeueZeileListener(arg0);
	}

	public String leseEingabeString()
	{
		return _konsoleneingabeWerkzeug.leseEingabeUndLeere();
	}

	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_konsolenausgabeWerkzeug.schreibeNewLine();
		
	}

	public void schreibe(String nachricht)
	{
		_konsolenausgabeWerkzeug.schreibeString(nachricht);
		
	}
	
}
