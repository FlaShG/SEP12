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
public class RaumBearbeitenPanel extends JPanel implements Observer
{
	private JTextArea _beschreibung;
	private RaumEigenschaftenPanel _eigenschaften;
	private JButton _loeschen;
	
	private final Raum _raum;
	
	/**
	 * Erzeugt ein RaumBearbeitenPanel für einen bestimmten Raum.
	 * @param raum
	 */
	public RaumBearbeitenPanel(Raum raum)
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
		
		add(_eigenschaften = new RaumEigenschaftenPanel(raum, this), BorderLayout.CENTER);
		
		add(_loeschen = new JButton("löschen"), BorderLayout.EAST);
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
	 * Die Methode, die von den Membern dieses Panels aufgerufen wird,
	 * um über Änderungen zu informieren.
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		_raum.setName(_eigenschaften.getRaumname());
		_raum.setRaumart(_eigenschaften.getTyp());
		
		Stack<Item> items = new Stack<Item>();
		for(int i = 0; i < _eigenschaften.getKuchenzahl(); ++i)
			items.push(Item.Kuchen);
		for(int i = 0; i < _eigenschaften.getGiftkuchenzahl(); ++i)
			items.push(Item.Giftkuchen);
			
		_raum.setItems(items);
	}
}
