package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JButton _abbrechen2; // leider nötig... sonst müssten die Panels jedesmal neu gemacht werden 
	private JButton _serverStarten;
	private JButton _fileChooserButton;
	private JButton _defaultMapButton;
	private JButton _editorButton;
	
	private JPanel _hauptPanel;
	//Paneeeele ^^
	private JPanel _multiPlayerPanel;
	private JPanel _serverPanel;
	

	public StartfensterUI()
	{
		super("Zuul-Spielmodus Auswahl");

		setMinimumSize(new Dimension(300, 130));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		_hauptPanel = new JPanel();
		_multiPlayerPanel = new JPanel();
		_serverPanel = new JPanel();
		
		_singlePlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_SINGLEPLAYER);
		_multiPlayer = new JButton(TextVerwalter.MODUS_AUSWAHL_MULTIPLAYER);
		_serverStarten = new JButton("Erstelle öffentliches Spiel");
		_fileChooserButton = new JButton("Level aus Datei laden");
		_defaultMapButton = new JButton("StandardLevel");
		_editorButton = new JButton("Editor Starten");

		_benutzerName = new JTextField("Dr.Little");
		_eingabeIP = new JTextField("127.0.0.1");
		_eingabePort = new JTextField("1090");
		_eingabeBestaetigen = new JButton("Bestaetigen");
		_abbrechen = new JButton("Abbrechen");
		_abbrechen2 = new JButton("Abbrechen");
		_abbrechen2.addActionListener(new ActionListener()
		{	// unsauber, aber funktioniert!
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_abbrechen.doClick();
			}
		});

		// hauptpanel zu Anfang
		initHauptPanel();
		add(_hauptPanel);
		
		// initialisiere alle anderen auch,
		// füge sie aber noch nicht hinzu
		initMultiPanel();
		initServerPanel();
		
		setVisible(true);
	}

	private void initHauptPanel()
	{
		_hauptPanel.setLayout(new GridLayout(0, 1));
		_hauptPanel.add(_singlePlayer);
		_hauptPanel.add(_multiPlayer);
		_hauptPanel.add(_serverStarten);
		_hauptPanel.add(_editorButton);
	}
	
	private void initMultiPanel()
	{
		_multiPlayerPanel.setLayout(new GridLayout(0, 1));
		JLabel labelserver = new JLabel(
				TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
		JLabel labelname = new JLabel(TextVerwalter.MODUS_AUSWAHL_NAMEPLABEL);
		
		_multiPlayerPanel.add(labelname);
		_multiPlayerPanel.add(_benutzerName);
		_benutzerName.setCaretPosition(_benutzerName.getText().length());
		_multiPlayerPanel.add(labelserver);
		_multiPlayerPanel.add(_eingabeIP);
		_eingabeIP.setCaretPosition(_eingabeIP.getText().length());
		_eingabeIP.requestFocus();
		_multiPlayerPanel.add(_eingabeBestaetigen);
		_multiPlayerPanel.add(_abbrechen);
	}
	
	private void initServerPanel()
	{
		_serverPanel.setLayout(new GridLayout(0, 1));
		_serverPanel.add(_fileChooserButton);
		_serverPanel.add(_defaultMapButton);
		_serverPanel.add(_abbrechen2);
	}
	
	public void startDarstellung()
	{
		this.getContentPane().removeAll();
		this.add(_hauptPanel);
		this.pack();
	}

	public void serverIPeingabe()
	{
		this.getContentPane().removeAll();
		this.add(_multiPlayerPanel);
		this.pack();
	}

	public void levelWaehlen()
	{
		this.getContentPane().removeAll();
		this.add(_serverPanel);
		this.pack();
	}
	
	public JButton getSinglePlayerButton()
	{
		return _singlePlayer;
	}

	public JButton getMultiPlayerButton()
	{
		return _multiPlayer;
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
