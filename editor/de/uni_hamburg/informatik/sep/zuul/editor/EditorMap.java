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
	 * @param breite
	 * @param hoehe
	 */
	public EditorMap(int breite, int hoehe)
	{
		setGroesse(breite, hoehe);
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
	
	/**
	 * Gibt zurück, ob keine Räume gelöscht würde, sollte die Map auf die gegebene
	 * Größe abgeändert werden.
	 * @param breite die neue Breite
	 * @param hoehe die neue Höhe
	 * @return
	 */
	public boolean istGroesseAendernUnproblematisch(int breite, int hoehe)
	{
		if(breite < _buttons.length)
		{
			for(int y = 0; y < getHoehe(); ++y)
			{
				for(int x = breite; x < getBreite(); ++x)
				{
					if(_buttons[x][y].getRaum() != null)
					{
						return false;
					}
				}
			}
		}
		
		if(hoehe < _buttons[0].length)
		{
			for(int y = hoehe; y < getHoehe(); ++y)
			{
				for(int x = 0; x < getBreite(); ++x)
				{
					if(_buttons[x][y].getRaum() != null)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Setzt die Größe der Map.
	 * @param breite die neue Breite
	 * @param hoehe die neue Höhe
	 */
	public void setGroesse(int breite, int hoehe)
	{
		//alte entfernen
		if(_buttons != null)
		{
			for(int y = 0; y < getHoehe(); ++y)
			{
				for(int x = 0; x < getBreite(); ++x)
				{
					remove(_buttons[x][y]);
				}
			}
		}
		
		setLayout(new GridLayout(hoehe, breite)); //dammit kids
		
		GridButton[][] neueButtons = new GridButton[breite][hoehe];

		for(int y = 0; y < hoehe; ++y)
		{
			for(int x = 0; x < breite; ++x)
			{
				neueButtons[x][y] = new GridButton(x, y);
				add(neueButtons[x][y]);
				initialisiereButton(neueButtons[x][y], x, y);
			}
		}
		
		_activeX = -1;
		_activeY = -1;
		
		if(_buttons != null)
		{
			for(int y = 0; y < hoehe && y < getHoehe(); ++y)
			{
				for(int x = 0; x < breite && x < getBreite(); ++x)
				{
					neueButtons[x][y].setRaum(_buttons[x][y].getRaum());
					neueButtons[x][y].setAusgewaehlt(false);
				}
			}
		}
		
		_buttons = neueButtons;
	}
	
	private void initialisiereButton(GridButton button, int x, int y)
	{
		button.addActionListener(new ActionListener()
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
		
		//drag&drop
		button.addMouseListener(new MouseAdapter()
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
								if(dragDropSource == getAktivenButton())
								{
									dragDropTarget.setAusgewaehlt(true);
									_activeX = dragDropTarget.getGridX();
									_activeY = dragDropTarget.getGridY();
								}
								else
								{
									dragDropTarget.setAusgewaehlt(false);
									_activeX = -1;
									_activeY = -1;
								}
								
								_beobachter.raumwahlUpdate();
							}
							else
							{
								dragDropTarget.setAusgewaehlt(false);
							}
							
							_beobachter.verschiebenUpdate();
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

	public int getBreite()
	{
		return _buttons.length;
	}

	public int getHoehe()
	{
		return _buttons[0].length;
	}
}
