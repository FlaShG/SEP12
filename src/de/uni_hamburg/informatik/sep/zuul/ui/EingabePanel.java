package de.uni_hamburg.informatik.sep.zuul.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EingabePanel extends JPanel
{
	private JTextField _eingabeZeile;
	private JButton _enterButton;

	private final Dimension BUTTONGROESSE = new Dimension(80, 27);
	private final int HOEHE = 30;

	/**
	 * Ein Panel zur Texteingabe. Es besteht aus einem JTextfield und einem
	 * Bestätigenbutton. Bei Initialisierung sollte eine anfangsbreite übergeben
	 * werden.
	 * 
	 * @param breite
	 *            die Anfangsbreite
	 */
	public EingabePanel(int breite)
	{
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(new Dimension(breite, HOEHE));
		setPreferredSize(new Dimension(breite, HOEHE));

		//Feldinitialisierung
		_eingabeZeile = new JTextField();
		Dimension eingabeZeileGroesse = new Dimension((int) (breite - BUTTONGROESSE.getWidth() - 30), 28);
		_eingabeZeile.setSize(eingabeZeileGroesse);
		_eingabeZeile.setPreferredSize(eingabeZeileGroesse);

		_enterButton = new JButton("Enter");
		_enterButton.setSize(BUTTONGROESSE);
		_enterButton.setPreferredSize(BUTTONGROESSE);
		_enterButton.setFocusable(false);

		initialisiereUI();

	}

	/**
	 * @return den _eingabeZeile
	 */
	public JTextField getEingabeZeile()
	{
		return _eingabeZeile;
	}

	/**
	 * @return den _enterButton
	 */
	public JButton getEnterButton()
	{
		return _enterButton;
	}

	/**
	 * ordnet die Widgets auf dem Panel an.
	 */
	private void initialisiereUI()
	{
		add(_eingabeZeile);
		add(_enterButton);
	}
}
