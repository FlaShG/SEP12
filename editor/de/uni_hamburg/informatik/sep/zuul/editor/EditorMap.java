package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorMap extends JPanel
{
	private JButton[][] _buttons;
	
	public EditorMap(int width, int height)
	{		
		setLayout(new GridLayout(width, height));
	
		_buttons = new JButton[width][height];
		
		for(int y = 0; y < height; ++y)
		{
			for(int x = 0; x < width; ++x)
			{
				_buttons[x][y] = new JButton();
				_buttons[x][y].setBackground(Color.gray);
				add(_buttons[x][y]);
			}
		}
	}
	
	public JButton getButton(int x, int y)
	{
		return _buttons[x][y];
	}
}
