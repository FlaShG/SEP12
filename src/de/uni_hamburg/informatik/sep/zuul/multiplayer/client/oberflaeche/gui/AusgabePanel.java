package de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public class AusgabePanel extends JPanel
{
	private JTextArea _anzeige;
	private JScrollPane _scrollPane;

	private final int HOEHE = 330;

	public AusgabePanel(int breite)
	{
		super();

		_anzeige = new JTextArea();
		_anzeige.setEditable(false);
		_anzeige.setLineWrap(true);
		_anzeige.setWrapStyleWord(true);
		//_anzeige.setAutoscrolls(true);
		_anzeige.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		_scrollPane = new JScrollPane();

		_scrollPane.setViewportView(_anzeige);
		_scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		_scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//_scrollPane.setAutoscrolls(true);

		//setSize(breite, HOEHE);
		//setPreferredSize(new Dimension(breite, HOEHE));
		setGroesse(breite, HOEHE);
		//_scrollPane.setPreferredSize(new Dimension(breite, (HOEHE - 20)));

		add(_scrollPane);
	}

	/**
	 * @return die _anzeige JTextArea
	 */
	public JTextArea getAnzeigeArea()
	{
		return _anzeige;
	}

	/**
	 * Setzt die Größe des Inhalts neu
	 */
	public void setGroesse(int breite, int hoehe)
	{
		setSize(breite, hoehe);
		setPreferredSize(new Dimension(breite, hoehe));

		_scrollPane.setPreferredSize(new Dimension(breite - 16, hoehe - 20));
	}
}
