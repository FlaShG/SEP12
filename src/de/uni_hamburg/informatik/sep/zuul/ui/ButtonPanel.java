package de.uni_hamburg.informatik.sep.zuul.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public class ButtonPanel extends JPanel
{
	private JButton _northButton;
	private JButton _southButton;
	private JButton _eastButton;
	private JButton _westButton;

	private JButton _quitButton;
	private JButton _helpButton;

	private JLabel _platzhalter;

	private final Dimension BUTTONGROESSE = new Dimension(120, 80);
	private final int HOEHE = 260;

	public ButtonPanel(int breite)
	{
		super(new GridBagLayout());
		setSize(breite, HOEHE);
		setPreferredSize(new Dimension(breite, HOEHE));

		_platzhalter = new JLabel(
				"                                                 ");
		_platzhalter.setVisible(true);

		ImageIcon north = new ImageIcon("./res/north.png");
		ImageIcon south = new ImageIcon("./res/south.png");
		ImageIcon west = new ImageIcon("./res/west.png");
		ImageIcon east = new ImageIcon("./res/east.png");

		//Buttons initialisieren:
		_northButton = new JButton(TextVerwalter.RICHTUNG_NORDEN, north);
		_northButton.setPreferredSize(BUTTONGROESSE);
		_northButton.setFocusable(false);
		_southButton = new JButton(TextVerwalter.RICHTUNG_SUEDEN, south);
		_southButton.setFocusable(false);
		_southButton.setPreferredSize(BUTTONGROESSE);
		_westButton = new JButton(TextVerwalter.RICHTUNG_WESTEN, west);
		_westButton.setFocusable(false);
		_westButton.setPreferredSize(BUTTONGROESSE);
		_eastButton = new JButton(TextVerwalter.RICHTUNG_OSTEN, east);
		_eastButton.setPreferredSize(BUTTONGROESSE);
		_eastButton.setFocusable(false);

		_quitButton = new JButton(TextVerwalter.BEFEHL_BEENDEN);
		_quitButton.setMinimumSize(BUTTONGROESSE);
		_quitButton.setFocusable(false);

		_helpButton = new JButton(TextVerwalter.BEFEHL_HILFE);
		_helpButton.setMinimumSize(BUTTONGROESSE);
		_helpButton.setFocusable(false);

		initialisiereUI();

	}

	/**
	 * Belege die UI mit Standardwerten und ordne die Elemente in einem
	 * Gridbaglayout an.
	 */
	private void initialisiereUI()
	{

		GridBagConstraints c = new GridBagConstraints();
		Insets insets = new Insets(5, 5, 5, 5);
		c.insets = insets;

		c.gridx = 0;
		c.gridy = 1;
		add(_westButton, c);

		c.gridx = 1;
		c.gridy = 0;
		add(_northButton, c);

		c.gridy = 2;
		add(_southButton, c);

		c.gridx = 2;
		c.gridy = 1;
		add(_eastButton, c);

		c.gridx = 3;
		add(_platzhalter, c);

		c.gridx = 4;
		add(_helpButton, c);

		c.gridx = 5;
		add(_quitButton, c);

	}

	//Getter und Setter für die Buttons

	/**
	 * @return den _northButton
	 */
	public JButton getNorthButton()
	{
		return _northButton;
	}

	/**
	 * @return den _southButton
	 */
	public JButton getSouthButton()
	{
		return _southButton;
	}

	/**
	 * @return den _eastButton
	 */
	public JButton getEastButton()
	{
		return _eastButton;
	}

	/**
	 * @return den _westButton
	 */
	public JButton getWestButton()
	{
		return _westButton;
	}

	/**
	 * @return den _quitButton
	 */
	public JButton getQuitButton()
	{
		return _quitButton;
	}

	/**
	 * @return den _helpButton
	 */
	public JButton getHelpButton()
	{
		return _helpButton;
	}

}
