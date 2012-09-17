package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public class AusgabePanel extends JPanel
{
	private JTextArea _anzeige;
	private JScrollPane _scrollPane;

	private final int HOEHE = 390;

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
		
		
		setSize(breite, HOEHE);
		setPreferredSize(new Dimension(breite, HOEHE));
		_scrollPane.setPreferredSize(new Dimension(breite, (HOEHE - 20)));

		add(_scrollPane);
	}

	/**
	 * @return die _anzeige JTextArea
	 */
	public JTextArea getAnzeigeArea()
	{
		return _anzeige;
	}
}
