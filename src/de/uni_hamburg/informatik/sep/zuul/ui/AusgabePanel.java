package de.uni_hamburg.informatik.sep.zuul.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public class AusgabePanel extends JPanel
{
	private JTextArea _anzeige;

	private final int HOEHE = 570;

	public AusgabePanel(int breite)
	{
		super();

		_anzeige = new JTextArea();
		_anzeige.setEditable(false);
		_anzeige.setWrapStyleWord(true);
		_anzeige.setAutoscrolls(true);

		setSize(breite, HOEHE);
		setPreferredSize(new Dimension(breite, HOEHE));
		_anzeige.setPreferredSize(new Dimension(breite, (HOEHE - 20)));

		add(_anzeige);
	}

	/**
	 * @return die _anzeige JTextArea
	 */
	public JTextArea getAnzeigeArea()
	{
		return _anzeige;
	}
}
