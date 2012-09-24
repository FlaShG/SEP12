package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class Startfenster extends JFrame
{
	private JButton _singlePlayer;
	private JButton _multiPlayer;
	private JTextField _benutzerName;
	private JTextField _eingabeIP;
	private JTextField _eingabePort;

	
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
		_benutzerName = new JTextField("Dr.Little");
		_eingabeIP = new JTextField("127.0.0.1");
		_eingabePort = new JTextField("1090");
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
		JLabel labelport = new JLabel(
				TextVerwalter.MODUS_AUSWAHL_SERVERPORTLABEL);
		setSize(new Dimension(300, 290));
		add(labelname);
		add(_benutzerName);
		_benutzerName.setCaretPosition(_benutzerName.getText().length());
		add(labelserver);
		add(_eingabeIP);
		_eingabeIP.setCaretPosition(_eingabeIP.getText().length());
		_eingabeIP.requestFocus();
		add(labelport);
		add(_eingabePort);
		_eingabePort.setCaretPosition(_eingabePort.getText().length());
	}

	public JTextField getSpielerNameTextField()
	{
		return _benutzerName;
	}

	public JTextField getIPTextField()
	{
		return _eingabeIP;
	}

	public JTextField getNameTextField()
	{
		return _eingabePort;
	}

}
