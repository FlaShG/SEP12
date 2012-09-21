package de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.util.StringUtils;

import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class EingabePanel extends JPanel
{
	private JTextField _eingabeZeile;
	private JButton _enterButton;

	private final Dimension BUTTONGROESSE = new Dimension(80, 27);
	private final int HOEHE = 40;

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
		setBreite(breite);

		_enterButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BUTTON_EINGEBEN));
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

	/**
	 * Setzt die Breite des Inhalts
	 */
	public void setBreite(int breite)
	{
		Dimension eingabeZeileGroesse = new Dimension((int) (breite
				- BUTTONGROESSE.getWidth() - 30), 28);
		_eingabeZeile.setSize(eingabeZeileGroesse);
		_eingabeZeile.setPreferredSize(eingabeZeileGroesse);
	}
}
