package de.uni_hamburg.informatik.sep.zuul.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	private JButton _northButton;
	private JButton _southButton;
	private JButton _eastButton;
	private JButton _westButton;

	private JButton _quitButton;
	private JButton _helpButton;

	private final Dimension BUTTONGROESSE = new Dimension(80, 27);
	private final int HOEHE = 200;

	public ButtonPanel(int breite)
	{
		super(new GridBagLayout());
		setSize(breite, HOEHE);
		setPreferredSize(new Dimension(breite, HOEHE));

		//Buttons initialisieren:
		_northButton = new JButton("North");
		_northButton.setPreferredSize(BUTTONGROESSE);
		_southButton = new JButton("South");
		_southButton.setPreferredSize(BUTTONGROESSE);
		_westButton = new JButton("West");
		_westButton.setPreferredSize(BUTTONGROESSE);
		_eastButton = new JButton("East");
		_eastButton.setPreferredSize(BUTTONGROESSE);

		_quitButton = new JButton("Quit");
		_quitButton.setPreferredSize(BUTTONGROESSE);
		_helpButton = new JButton("Help");
		_helpButton.setPreferredSize(BUTTONGROESSE);

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
