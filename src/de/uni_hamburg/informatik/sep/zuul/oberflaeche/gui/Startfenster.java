package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class Startfenster extends JFrame
{
	private JButton _singlePlayer;
	private JButton _multiPlayer;
	private JTextField _eingabeIP;

	public Startfenster()
	{
		super("Zuul-Spielmodus Auswahl");

		setLayout(new GridLayout(0, 1));

		_singlePlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_SINGLEPLAYER);
		_multiPlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_MULTIPLAYER);

		add(_singlePlayer);
		add(_multiPlayer);

		setMinimumSize(new Dimension(300, 100));
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		_eingabeIP = new JTextField("127.0.0.1");
	}

	public JButton getSinglePlayerButton()
	{
		return _singlePlayer;
	}

	public JButton getMultiPlayerButton()
	{
		return _multiPlayer;
	}

	public void serverIPeingabe()
	{
		JLabel label = new JLabel(TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
		setSize(new Dimension(300, 170));
		add(label);
		add(_eingabeIP);
		_eingabeIP.setCaretPosition(_eingabeIP.getText().length());
		_eingabeIP.requestFocus();
	}

	public JTextField getTextField()
	{
		return _eingabeIP;
	}

}
