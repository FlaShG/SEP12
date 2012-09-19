package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;


public class GridButton extends JButton
{
	private final int _x;
	private final int _y;
	private Raum _raum;
	
	public GridButton(int x, int y)
	{
		super();
		_x = x;
		_y = y;
		
		Dimension dim = new Dimension(30,30);
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		
		setAusgewaehlt(false);
	}
	
	public int getGridX()
	{
		return _x;
	}
	
	public int getGridY()
	{
		return _y;
	}
	
	public void setAusgewaehlt(boolean b)
	{
		setBackground(b ?	(_raum != null ? new Color(0.6f, 0.6f, 0.9f) : Color.lightGray)
						:	(_raum != null ? Color.blue : Color.gray));
	}
	
	public void fuegeRaumHinzu()
	{
		_raum = new Raum("", "");
		setAusgewaehlt(true);
	}
	
	public Raum getRaum()
	{
		return _raum;
	}
	
	public void loescheRaum()
	{
		_raum = null;
	}
}
