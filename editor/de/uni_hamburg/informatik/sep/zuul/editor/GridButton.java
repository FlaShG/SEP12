package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * JButton, der seine Position auf einem Grid (vornehmlich auf einer EditorMap)
 * kennt und eine Referenz auf einen Raum halten kann.
 * 
 * @author 0graeff
 * 
 */
public class GridButton extends JButton
{
	private final int _x;
	private final int _y;
	private Raum _raum;

	/**
	 * Erstellt einen neuen GridButton und übergibt dessen Position
	 * 
	 * @param x
	 * @param y
	 */
	public GridButton(int x, int y)
	{
		super();
		_x = x;
		_y = y;

		Dimension dim = new Dimension(30, 30);
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);

		setFocusable(false);

		setAusgewaehlt(false);
	}

	/**
	 * Gibt die übergebene x-Position auf dem Grid zurück
	 * 
	 * @return
	 */
	public int getGridX()
	{
		return _x;
	}

	/**
	 * Gibt die übergebene y-Position auf dem Grid zurück
	 * 
	 * @return
	 */
	public int getGridY()
	{
		return _y;
	}

	/**
	 * Färbt den Button in Abhängigkeit davon, ob er einen Raum referenziert und
	 * ob er markiert ist.
	 * 
	 * @param aktiv
	 *            ob der Button gerade markiert ist.
	 */
	public void setAusgewaehlt(boolean aktiv)
	{
		setBackground(aktiv ? (_raum != null ? new Color(0.8f, 0.8f, 1f)
				: Color.lightGray) : (_raum != null ? new Color(0.3f, 0.3f, 1f)
				: Color.gray));
	}

	/**
	 * Legt einen neuen Raum an und lässt den GridButton ihn referenzieren.
	 */
	public void fuegeLeerenRaumHinzu()
	{
		_raum = new Raum("", "");
		setAusgewaehlt(true);
		_raum.setKoordinaten(_x, _y);
	}

	public void setRaum(Raum raum)
	{
		_raum = raum;
		if(raum != null)
		{
			setText(raum.getName());
		}
		else
		{
			setText("");
		}
	}

	/**
	 * Gibt den referenzierten Raum zurück. Ist null, wenn keiner referenziert
	 * wird.
	 * 
	 * @return
	 */
	public Raum getRaum()
	{
		return _raum;
	}

	/**
	 * Setzt die Raumreferenz auf null.
	 */
	public void loescheRaum()
	{
		setRaum(null);
		setAusgewaehlt(true);
	}
}
