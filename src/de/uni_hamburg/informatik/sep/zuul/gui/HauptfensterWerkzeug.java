package de.uni_hamburg.informatik.sep.zuul.gui;

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
	
}
