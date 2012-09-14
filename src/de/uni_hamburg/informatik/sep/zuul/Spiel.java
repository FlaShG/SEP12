package de.uni_hamburg.informatik.sep.zuul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.ui.AusgabePanel;
import de.uni_hamburg.informatik.sep.zuul.ui.ButtonPanel;
import de.uni_hamburg.informatik.sep.zuul.ui.EingabePanel;
import de.uni_hamburg.informatik.sep.zuul.ui.Hauptfenster;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * Das Ausgangssystem basiert auf einem Beispielprojekt aus dem Buch
 * "Java lernen mit BlueJ" von D. J. Barnes und M. Kölling.
 */
public class Spiel
{
	private Parser _parser;
	private SpielKontext _kontext;

	private Hauptfenster _hf;
	private EingabePanel _ep;
	private AusgabePanel _ap;
	private ButtonPanel _bp;

	/**
	 * Erzeugt ein Spiel und initialisiert die interne Raumkarte.
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStream
	 */
	public Spiel()
	{

		_bp = new ButtonPanel(1024);
		_ep = new EingabePanel(1024);
		_ap = new AusgabePanel(1024);
		
		_hf = new Hauptfenster(_ap, _ep, _bp);


		_ep.getEnterButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String str = _ep.getEingabeZeile().getText();
				verarbeiteEingabe(str);
				
			}
		});
		
		_ep.getEingabeZeile().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ep.getEnterButton().doClick();				
			}
		});

		
		_bp.getNorthButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("go north");
			}
		});
		
		_bp.getSouthButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("go south");
			}
		});
		
		_bp.getEastButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("go east");
			}
		});
		
		_bp.getWestButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("go west");
			}
		});
		
		_bp.getQuitButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("quit");
			}
		});
		
		_bp.getHelpButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				verarbeiteEingabe("help");
			}
		});
		
		_parser = new Parser();

		_kontext = new SpielKontext(this);


	}
	


	private void beendeSpiel()
	{

		_kontext.schreibeNL("Danke für dieses Spiel. Auf Wiedersehen.");
		//TODO: Hauptfenster ausschalten (wie auch immer) Buttons + Eingabe sperren
	}
	
	/**
	 * Führt das Spiel aus.
	 */
	public void spielen()
	{
//		_hauptwerkzeug.zeigeFenster();
		
		zeigeWillkommenstext();
	}

	/**
	 * Gibt einen Begrüßungstext für den Spieler aus.
	 */
	private void zeigeWillkommenstext()
	{
		_kontext.schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		_kontext.schreibeNL("");
		_kontext.zeigeRaumbeschreibung();
		_kontext.zeigeAusgaenge();
	}

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{			
			@Override
			public void run()
			{
				Spiel spiel = new Spiel();

				spiel.spielen();				
			}
		});

	}



	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_ap.getAnzeigeArea().append("\n");
		
	}



	public void schreibe(String nachricht)
	{
		_ap.getAnzeigeArea().append(nachricht);		
	}



	private void verarbeiteEingabe(String str)
	{
		_ep.getEingabeZeile().setText("");
		
		
		schreibeNL("> "+ str);
		
		Befehl befehl = _parser.liefereBefehl(str);
		befehl.ausfuehren(_kontext);
		
		if(_kontext.isSpielZuende())
			beendeSpiel();
	}
}
