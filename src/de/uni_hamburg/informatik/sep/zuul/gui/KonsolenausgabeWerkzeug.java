package de.uni_hamburg.informatik.sep.zuul.gui;

import javax.swing.JComponent;

public class KonsolenausgabeWerkzeug
{

	private KonsolenausgabeUI _konsolenausgabeUI;

	public KonsolenausgabeWerkzeug()
	{
		_konsolenausgabeUI = new KonsolenausgabeUI();
	}
	
	public JComponent getUI()
	{
		return _konsolenausgabeUI.getPanel();
	}

}
