package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class StartfensterUI extends JFrame
{
	private JButton _singlePlayer;
	private JButton _multiPlayer;
	private JTextField _benutzerName;
	private JTextField _eingabeIP;
	private JTextField _eingabePort;
	private JButton _eingabeBestaetigen;
	private JButton _abbrechen;
	private JButton _serverStarten;
	private JButton _fileChooserButton;
	private JButton _defaultMapButton;
	private JButton _editorButton;
	
	public StartfensterUI()
	{
		super("Zuul-Spielmodus Auswahl");

		setLayout(new GridLayout(0, 1));

		_singlePlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_SINGLEPLAYER);
		_multiPlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_MULTIPLAYER);
		_serverStarten = new JButton("Erstelle öffentliches Spiel");
		_fileChooserButton = new JButton("Level aus Datei laden");
		_defaultMapButton = new JButton("StandardLevel");
		_editorButton = new JButton("Editor Starten");

		add(_singlePlayer);
		add(_multiPlayer);
		add(_serverStarten);
		add(_editorButton);

		setMinimumSize(new Dimension(300, 130));
		_benutzerName = new JTextField("Dr.Little");
		_eingabeIP = new JTextField("127.0.0.1");
		_eingabePort = new JTextField("1090");
		_eingabeBestaetigen = new JButton("Bestaetigen");
		_abbrechen = new JButton("Abbrechen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
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
		add(_eingabeBestaetigen);
		add(_abbrechen);

	}
	
	public void levelWaehlen()
	{
		//JPanel panel = new JPanel();
		
		//panel.setLayout(new GridLayout(0,1));
		
		//panel.add(_fileChooserButton);
		//panel.add(_defaultMapButton);
		
		remove(_singlePlayer);
		remove(_multiPlayer);
		remove(_benutzerName);
		remove(_serverStarten);
		remove(_eingabeIP);
		remove(_eingabeBestaetigen);
		remove(_eingabePort);
		remove(_multiPlayer);
		remove(_singlePlayer);
		
		add(getFileChooserButton());
		add(getDefaultMapButton());
		
		//setContentPane(panel);
		setMinimumSize(new Dimension(300, 40));
		setSize(new Dimension(300, 40));

		
		pack();
	}

	public JTextField getIPTextField()
	{
		return _eingabeIP;
	}

	public JTextField getSpielerNameTextField()
	{
		return _benutzerName;
	}

	public JTextField getPortTextField()
	{
		return _eingabePort;
	}

	public JButton getAbbrechenButton()
	{
		return _abbrechen;
	}

	public JButton getBestaetigen()
	{
		return _eingabeBestaetigen;
	}

	public JButton getServerButton()
	{
		return _serverStarten;
	}

	public JButton getDefaultMapButton()
	{
		return _defaultMapButton;
	}

	public JButton getFileChooserButton()
	{
		return _fileChooserButton;
	}
	
	public JButton getEditorButton()
	{
		return _editorButton;
	}


}
