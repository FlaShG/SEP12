package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class Startfenster extends JFrame
{
	private JButton _singlePlayer;
	private JButton _multiPlayer;

	public Startfenster()
	{
		super("Zuul-Spielmodus Auswahl");

		setLayout(new GridLayout(0, 1));

		_singlePlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_SINGLEPLAYER);
		_multiPlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_MULTIPLAYER);

		add(_singlePlayer);
		add(_multiPlayer);

		setMinimumSize(new Dimension(200, 100));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getSinglePlayerButton()
	{
		return _singlePlayer;
	}

	public JButton getMultiPlayerButton()
	{
		return _multiPlayer;
	}

}
