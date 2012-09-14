package de.uni_hamburg.informatik.sep.zuul.gui;

import javax.swing.JComponent;

public class SteuerungWerkzeug
{


	private SteuerungUI _SteuerungUI;

	public SteuerungWerkzeug()
	{
		_SteuerungUI = new SteuerungUI();
	}
	
	public JComponent getUI()
	{
		return _SteuerungUI.getPanel();
	}


}
