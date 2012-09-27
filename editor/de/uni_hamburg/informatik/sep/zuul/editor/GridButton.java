package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

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
	 * Erstellt einen neuen {@link GridButton} und setzt dessen Position auf dem
	 * Grid.
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

		setForeground(Color.white);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
	}

	/**
	 * Gibt die übergebene x-Position auf dem Grid zurück.
	 */
	public int getGridX()
	{
		return _x;
	}

	/**
	 * Gibt die übergebene y-Position auf dem Grid zurück.
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
		Color[] mitRaum = new Color[] { new Color(0.4f, 0.4f, 0.8f),
				new Color(0.2f, 0.2f, 0.5f) };
		Color[] ohneRaum = new Color[] { Color.lightGray, Color.gray };
		Color[] startRaum = new Color[] { new Color(0.4f, 0.8f, 0.4f),
				new Color(0.2f, 0.5f, 0.2f) };
		Color[] endRaum = new Color[] { new Color(0.8f, 0.4f, 0.4f),
				new Color(0.5f, 0.2f, 0.2f) };

		Color[] set = ohneRaum;

		if(_raum != null)
		{
			switch (_raum.getRaumart())
			{
			case Start:
				set = startRaum;
				break;
			case Ende:
				set = endRaum;
				break;
			default:
				set = mitRaum;
			}
		}

		setBackground(set[aktiv ? 0 : 1]);
		//setBorderPainted(aktiv);
		//setBorder(aktiv ? BorderFactory.createLineBorder(Color.white, 3)
		//				: BorderFactory.createLineBorder(Color.lightGray, 1));

		//setBorder(aktiv ? BorderFactory.createBevelBorder(BevelBorder.RAISED, set[1], Color.black)
		//				: BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));

		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				_raum != null ? set[aktiv ? 1 : 0]
						: new Color(0.6f, 0.6f, 0.6f), Color.black));
	}

	/**
	 * Legt einen neuen {@link Raum} an und lässt den {@link GridButton} ihn
	 * referenzieren.
	 * 
	 * @ensure getRaum() != null
	 */
	public void fuegeLeerenRaumHinzu()
	{
		setRaum(new Raum("", ""));
		setAusgewaehlt(true);
		_raum.setKoordinaten(_x, _y);
	}

	/**
	 * Setzt den {@link Raum} dieses {@link GridButton}s neu.
	 * 
	 * @param raum
	 *            Der neue Raum. Kann null sein, um den Raum zu löschen, ohne
	 *            dessen Markierung zu ändern.
	 * 
	 * @ensure getRaum() == raum
	 */
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
	 * Gibt den referenzierten {@link Raum} zurück. Ist null, wenn keiner
	 * referenziert wird.
	 */
	public Raum getRaum()
	{
		return _raum;
	}

	/**
	 * Setzt die Raumreferenz auf null und ändert die Färbung auf "ausgewählt".
	 */
	public void loescheRaumUndSetzeAufAusgewaehlt()
	{
		setRaum(null);
		setAusgewaehlt(true);
	}
}
