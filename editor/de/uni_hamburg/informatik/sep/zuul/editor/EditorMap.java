package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Hält ein zweidimensionales Feld aus {@link GridButton}s, die die Räume auf
 * der Karte darstellen.
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
	 * Erstellt eine neue {@link EditorMap} mit gegebener Höhe und Breite.
	 * 
	 * @require breite > 0
	 * 1require hoehe > 0
	 */
	public EditorMap(int breite, int hoehe)
	{
		assert breite > 0 : "Vorbedingung verletzt: breite > 0";
		assert hoehe > 0 : "Vorbedingung verletzt: hoehe > 0";
		
		setGroesse(breite, hoehe);
	}

	/**
	 * Setzt den {@link EditorBeobachter}, der über Änderungen im Grid
	 * informiert werden soll.
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
	 * Gibt zurück, ob gerade ein {@link GridButton} ausgewählt ist.
	 */
	public boolean buttonAusgewaehlt()
	{
		return _activeX >= 0;
	}

	/**
	 * Gibt den aktiven {@link GridButton} zurück.
	 */
	public GridButton getAktivenButton()
	{
		if(!buttonAusgewaehlt())
			return null;
		return _buttons[_activeX][_activeY];
	}

	/**
	 * Gibt den {@link Raum} des aktuell angewählten {@link GridButton}s zurück.
	 */
	public Raum getAktivenRaum()
	{
		if(!buttonAusgewaehlt())
			return null;
		return getAktivenButton().getRaum();
	}

	/**
	 * Fügt dem aktuell ausgewählten {@link GridButton} einen Raum hinzu, wenn
	 * einer ausgewählt ist.
	 */
	public void fuegeRaumZuAktivemButtonHinzu()
	{
		if(buttonAusgewaehlt())
			_buttons[_activeX][_activeY].fuegeLeerenRaumHinzu();
	}

	/**
	 * Löscht den {@link Raum} des aktiven {@link GridButton}s.
	 */
	public void loescheRaumDesAktivenButtons()
	{
		if(buttonAusgewaehlt())
		{
			_buttons[_activeX][_activeY].loescheRaumUndSetzeAufAusgewaehlt();
			informiereBeobachter();
		}
	}

	/**
	 * Getter für das zweidimensionale ButtonArray, in dem alle {@link Raum}-
	 * {@link GridButton}s gehalten werden.
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
	 * @return ob bei einer Größenänderung Räume flöten gehen würden.
	 * 
	 * @require breite > 0
	 * @require hoehe > 0
	 */
	public boolean istGroesseAendernUnproblematisch(int breite, int hoehe)
	{
		assert breite > 0 : "Vorbedingung verletzt: breite > 0";
		assert hoehe > 0 : "Vorbedingung verletzt: hoehe > 0";
		
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
	 * 
	 * @require breite > 0
	 * @require hoehe > 0
	 */
	public void setGroesse(int breite, int hoehe)
	{
		assert breite > 0 : "Vorbedingung verletzt: breite > 0";
		assert hoehe > 0 : "Vorbedingung verletzt: hoehe > 0";
		
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

			}
		});
		
		button.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				switch(arg0.getClickCount())
				{
					case 1:
						if(buttonAusgewaehlt())
						{
							_buttons[_activeX][_activeY].setAusgewaehlt(false);
						}
						_activeX = ((GridButton) arg0.getSource()).getGridX();
						_activeY = ((GridButton) arg0.getSource()).getGridY();
						_buttons[_activeX][_activeY].setAusgewaehlt(true);
					break;
					
					case 2:
						if(_buttons[_activeX][_activeY].getRaum() == null)
						{
							_buttons[_activeX][_activeY].fuegeLeerenRaumHinzu();
						}
					break;
					
					default: return;
				}
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
							dragDropSource.loescheRaumUndSetzeAufAusgewaehlt();

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
					GridButton source = ((GridButton) arg0.getSource());
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
					((GridButton) arg0.getSource()).setAusgewaehlt(false);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				if(dragDropSource != null)
				{
					GridButton destination = ((GridButton) arg0.getSource());
					if(destination.getRaum() == null && dragDropSource != arg0.getSource())
					{
						dragDropTarget = destination;
						destination.setBackground(new Color(1, 0.6f, 0.4f));
						dragDropSource.setAusgewaehlt(false);
					}
					else
					{
						dragDropTarget = null;
						dragDropSource.setAusgewaehlt(true);
					}
				}
			}
		});
	}

	/**
	 * Gibt die Breite der Map zurück.
	 */
	public int getBreite()
	{
		return _buttons.length;
	}

	/**
	 * Gibt die Höhe der Map zurück.
	 */
	public int getHoehe()
	{
		return _buttons[0].length;
	}
}
