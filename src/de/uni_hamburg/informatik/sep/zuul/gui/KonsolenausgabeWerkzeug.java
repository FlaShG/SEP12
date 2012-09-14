package de.uni_hamburg.informatik.sep.zuul.gui;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JComponent;
import javax.swing.JTextArea;

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

	public void schreibeString(String str)
	{
		_konsolenausgabeUI.getTextArea().append(str);
	}

	public void schreibeNewLine()
	{
		_konsolenausgabeUI.getTextArea().append("\n");		
	}

}
