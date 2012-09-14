package de.uni_hamburg.informatik.sep.zuul.gui;

import javax.swing.JComponent;

public class KonsoleneingabeWerkzeug
{

	private KonsoleneingabeUI _konsoleneingabeUI;

	public KonsoleneingabeWerkzeug()
	{
		_konsoleneingabeUI = new KonsoleneingabeUI();
	}
	
	public JComponent getUI()
	{
		return _konsoleneingabeUI.getPanel();
	}


}
