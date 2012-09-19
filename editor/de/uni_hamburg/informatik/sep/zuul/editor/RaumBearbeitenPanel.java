package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;


/**
 * Das Panel, das das Einstellen aller Eigenschaften eines Raumes ermöglicht.
 * Inklusive Löschen-Button.
 * @author 0graeff
 */
public class RaumBearbeitenPanel extends JPanel
{
	private JTextArea _beschreibung;
	private RaumEigenschaftenPanel _eigenschaften;
	private JButton _loeschen;
	
	private final Raum _raum;
	
	/**
	 * Erzeugt ein RaumBearbeitenPanel für einen bestimmten Raum.
	 * @param raum
	 */
	public RaumBearbeitenPanel(Raum raum, Observer beobachter)
	{
		_raum = raum;
		
		setLayout(new BorderLayout());
		
		add(_beschreibung = new JTextArea(), BorderLayout.WEST);
		Dimension dim = new Dimension(400,80);
		_beschreibung.setSize(dim);
		_beschreibung.setPreferredSize(dim);
		_beschreibung.setMinimumSize(dim);
		_beschreibung.setText(_raum.getBeschreibung());
		_beschreibung.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				_raum.setBescheibung(_beschreibung.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				_raum.setBescheibung(_beschreibung.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0){}
		});
		
		add(_eigenschaften = new RaumEigenschaftenPanel(raum, beobachter), BorderLayout.CENTER);
		
		add(_loeschen = new JButton("löschen"), BorderLayout.EAST);
	}
	
	/**
	 * Gibt den bearbeiteten Raum zurück
	 */
	public Raum getRaum()
	{
		return _raum;
	}
	
	/**
	 * Gibt den Löschen-Button des Panels zurück
	 * @return
	 */
	public JButton getLoeschenButton()
	{
		return _loeschen;
	}
	
	/**
	 * Gibt das Eigenschaften-Panel zurück
	 */
	public RaumEigenschaftenPanel getEigenschaftenPanel()
	{
		return _eigenschaften;
	}
}
