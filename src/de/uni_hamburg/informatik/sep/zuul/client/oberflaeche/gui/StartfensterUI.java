package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class StartfensterUI extends JFrame
{
	private JButton _singlePlayer;
	private JButton _multiPlayer;
	private JTextField _eingabeIP;
	private JTextField _benutzerName;

	public StartfensterUI()
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
		_benutzerName = new JTextField("Dr.Little");
		_eingabeIP = new JTextField("127.0.0.1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		JLabel labelserver = new JLabel(
				TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
		JLabel labelname = new JLabel(TextVerwalter.MODUS_AUSWAHL_NAMEPLABEL);
		setSize(new Dimension(300, 230));
		add(labelname);
		add(_benutzerName);
		_benutzerName.setCaretPosition(_benutzerName.getText().length());
		add(labelserver);
		add(_eingabeIP);
		_eingabeIP.setCaretPosition(_eingabeIP.getText().length());
		_eingabeIP.requestFocus();
	}

	public JTextField getIPTextField()
	{
		return _eingabeIP;
	}

	public JTextField getSpielerNameTextField()
	{
		return _benutzerName;
	}

}
