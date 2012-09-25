package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Hält ein zweidimensionales Feld auf GridButtons, die die Räume auf der Karte
 * darstellen.
 * 
 * @author 0graeff
 */
public class EditorMap extends JPanel
{
	private EditorBeobachter _beobachter;
	private GridButton[][] _buttons;
	private int _activeX = -1;
	private int _activeY = -1;
	
	private GridButton dragDropSource = null;
	private GridButton dragDropTarget = null;

	/**
	 * Erstellt eine neue EditorMap mit gegebener Höhe und Breite
	 * 
	 * @param width
	 * @param height
	 */
	public EditorMap(int width, int height)
	{
		setLayout(new GridLayout(height, width)); //dammit kids

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
							_buttons[_activeX][_activeY].setAusgewaehlt(false);
						_activeX = ((GridButton) arg0.getSource()).getGridX();
						_activeY = ((GridButton) arg0.getSource()).getGridY();
						_buttons[_activeX][_activeY].setAusgewaehlt(true);
						informiereBeobachter();
					}
				});
				
				_buttons[x][y].addMouseListener(new MouseAdapter()
				{
					
					@Override
					public void mouseReleased(MouseEvent arg0)
					{
						boolean moved = false;
						if(dragDropSource != null && dragDropTarget != null)
						{
							if(dragDropSource != dragDropTarget)
							{
								if(dragDropTarget.getRaum() == null)
								{
									dragDropTarget.setRaum(dragDropSource.getRaum());
									dragDropSource.loescheRaum();
									
									dragDropSource.setAusgewaehlt(false);
									if(buttonAusgewaehlt())
									{
										getAktivenButton().setAusgewaehlt(false);
										_activeX = dragDropTarget.getGridX();
										_activeY = dragDropTarget.getGridY();
										dragDropTarget.setAusgewaehlt(true);
										_beobachter.verschiebenUpdate();
									}
									else
									{
										dragDropTarget.setAusgewaehlt(false);
									}
									
									moved = true;
								}
							}
						}
						if(!moved && dragDropSource != null)
						{
							dragDropSource.setAusgewaehlt(true);
						}
						dragDropSource = null;
						dragDropTarget = null;
					}
					
					@Override
					public void mousePressed(MouseEvent arg0)
					{
						if(dragDropSource == null)
						{
							GridButton source = ((GridButton)arg0.getSource());
							if(source.getRaum() != null)
							{
								dragDropSource = source;
								source.setAusgewaehlt(false);
							}
						}
					}
					
					@Override
					public void mouseExited(MouseEvent arg0)
					{
						if(dragDropSource != null && dragDropSource != arg0.getSource())
						{
							((GridButton)arg0.getSource()).setAusgewaehlt(false);
						}
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0)
					{
						if(dragDropSource != null && dragDropSource != arg0.getSource())
						{
							GridButton destination = ((GridButton)arg0.getSource());
							if(destination.getRaum() == null)
							{
								dragDropTarget = destination;
								destination.setBackground(new Color(1, 0.6f, 0.4f));
							}
							else
							{
								dragDropTarget = null;
							}
						}
					}
				});
			}
		}
	}

	/**
	 * Setzt den Beobachter, der über Änderungen im Grid informiert werden soll
	 */
	public void setBeobachter(EditorBeobachter beobachter)
	{
		_beobachter = beobachter;
	}

	private void informiereBeobachter()
	{
		if(_beobachter != null)
		{
			_beobachter.raumwahlUpdate();
		}
	}

	/**
	 * Gibt zurück, ob gerade ein GridButton ausgewählt ist
	 */
	public boolean buttonAusgewaehlt()
	{
		return _activeX >= 0;
	}

	/**
	 * Gibt den aktiven Button zurück
	 * 
	 * @return
	 */
	public GridButton getAktivenButton()
	{
		if(!buttonAusgewaehlt())
			return null;
		return _buttons[_activeX][_activeY];
	}

	/**
	 * Gibt den Raum des aktuell angewählten GridButtons zurück.
	 */
	public Raum getAktivenRaum()
	{
		if(!buttonAusgewaehlt())
			return null;
		return getAktivenButton().getRaum();
	}

	/**
	 * Fügt dem aktuell ausgewählten GridButton einen Raum hinzu, wenn einer
	 * ausgewählt ist.
	 */
	public void fuegeRaumZuAktivemButtonHinzu()
	{
		if(buttonAusgewaehlt())
			_buttons[_activeX][_activeY].fuegeLeerenRaumHinzu();
	}

	/**
	 * Löscht den Raum des aktiven Butons
	 */
	public void loescheRaumDesAktivenButtons()
	{
		if(buttonAusgewaehlt())
		{
			_buttons[_activeX][_activeY].loescheRaum();
			informiereBeobachter();
		}
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

	public void verschiebeAktuellenRaumRelativ(int x, int y)
	{
		if(!buttonAusgewaehlt())
			return;
		
		if(_activeX + x < 0
		|| _activeX + x >= _buttons.length
		|| _activeY + y < 0
		|| _activeY + y >= _buttons[0].length)
			return;
		
		GridButton vorherigerButton = getAktivenButton();
		
		if(vorherigerButton != null)
		{
			GridButton neuerButton = _buttons[_activeX+x][_activeY+y];
			
			Raum raum = vorherigerButton.getRaum();
			if(raum != null)
			{
				if(neuerButton.getRaum() == null)
				{
					_activeX += x;
					_activeY += y;
					
					neuerButton.setRaum(raum);
					//setRaum statt loscheRaum wegen setAusgewaehlt()
					vorherigerButton.setRaum(null);
	
					vorherigerButton.setAusgewaehlt(false);
					neuerButton.setAusgewaehlt(true);
				}
			}
		}
			
	}
}
