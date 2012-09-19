package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

public class EditorMap extends JPanel
{
	Observer _observer;
	private GridButton[][] _buttons;
	int activeX = -1;
	int activeY = -1;

	public EditorMap(int width, int height)
	{
		setLayout(new GridLayout(width, height));

		_buttons = new GridButton[width][height];

		for(int y = 0; y < height; ++y)
		{
			for(int x = 0; x < width; ++x)
			{
				_buttons[x][y] = new GridButton(x, y);
				add(_buttons[x][y]);

				_buttons[x][y].addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						if(buttonAusgewaehlt())
							_buttons[activeX][activeY].setAusgewaehlt(false);
						activeX = ((GridButton) arg0.getSource()).getGridX();
						activeY = ((GridButton) arg0.getSource()).getGridY();
						_buttons[activeX][activeY].setAusgewaehlt(true);
						informiereBeobachter();
					}
				});
			}
		}
	}

	public void setBeobachter(Observer o)
	{
		_observer = o;
	}

	private void informiereBeobachter()
	{
		if(_observer != null)
		{
			_observer.update(null, null);
		}
	}

	public boolean buttonAusgewaehlt()
	{
		return activeX >= 0;
	}

	public Raum getAktivenRaum()
	{
		if(!buttonAusgewaehlt())
			return null;
		return _buttons[activeX][activeY].getRaum();
	}

	public void fuegeRaumZuAktivemButtonHinzu()
	{
		if(buttonAusgewaehlt())
			_buttons[activeX][activeY].fuegeRaumHinzu();
	}

	/**
	 * Getter für das zweidimensionale ButtonArray, in dem alle Raum-Buttons
	 * gehalten werden.
	 * 
	 * @return
	 */
	public GridButton[][] getButtonArray()
	{
		return _buttons;
	}
}
