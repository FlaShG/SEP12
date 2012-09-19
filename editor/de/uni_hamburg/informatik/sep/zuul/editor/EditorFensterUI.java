package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

/**
 * Die UI-Klasse des Editor-Fensters.
 * @author 0graeff
 *
 */
public class EditorFensterUI
{
	private JFrame _frame;
	
	private EditorMenuBar _menubar;
	private EditorMap _map;
	private JButton _raumhinzu;
	private RaumBearbeitenPanel _bearbeiten;
	
	private Observer _beobachter;
	
	/**
	 * Erzeugt eine neue EditorFensterUI.
	 * @param o Ein Observer, der über alle Änderungen in der UI informiert wird.
	 */
	public EditorFensterUI(Observer o)
	{
		_beobachter = o;
		
		
		_frame = new JFrame("Zuul-Editor");
		
		_frame.getContentPane().setLayout(new BorderLayout());
		
		
		_frame.add(_menubar = new EditorMenuBar(), BorderLayout.NORTH);
		_frame.add(_map = new EditorMap(8, 8), BorderLayout.CENTER);
		_map.setBeobachter(o);
		
		_raumhinzu = new JButton("Raum anlegen");
		
		//fancy größenwahn
		//_frame.pack();
		//_frame.setMinimumSize(_frame.getSize());
		//_frame.setSize(_frame.getSize().width, _frame.getSize().width);
		_frame.setMinimumSize(new Dimension(900, 600));
		
		
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);
		
		//--------------- 1griese
			//
				//DO NOT PUT TOOLS OR LISTENERS HERE
				//gooby pls
		
		//----------------
		
	}
	
	/**
	 * Gibt die obere Menüleiste zurück
	 * @return
	 */
	public EditorMenuBar getMenuBar()
	{
		return _menubar;
	}

	/**
	 * Setzt die Map
	 * @return
	 */
	public void setMap(EditorMap map)
	{
		_frame.remove(_map);
		_frame.add(_map = map, BorderLayout.CENTER);
		_map.setBeobachter(_beobachter);
		_frame.setVisible(true);
	}
	
	/**
	 * Gibt die Map (GridButton-Grid) zurück	
	 * @return
	 */
	public EditorMap getMap()
	{
		return _map;
	}
	
	/**
	 * Gibt den Raum-hinzu-Button zurück
	 */
	public JButton getRaumhinzu()
	{
		return _raumhinzu;
	}
	
	/**
	 * Gibt das RaumBearbeitenPanel zurück
	 */
	public RaumBearbeitenPanel getBearbeitenPanel()
	{
		return _bearbeiten;
	}
	
	/**
	 * Gibt den JFrame zurück, den diese Klasse erstellt 
	 */
	public JFrame getFrame()
	{
		return _frame;
	}

	/**
	 * Legt ein neues RaumBearbeitenPanel an, speichert die Referenz und gibt sie zurück.
	 * @param raum der Raum, der bearbeitet werden soll
	 * @return Das neue RaumBearbeitenPanel
	 */
	public RaumBearbeitenPanel neuesBearbeitenPanel(Raum raum)
	{
		 return _bearbeiten = new RaumBearbeitenPanel(raum);
	}
}
