package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Maus;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;



public class RaumBearbeitenPanel extends JPanel implements Observer
{
	private JTextArea _beschreibung;
	private RaumEigenschaftenPanel _eigenschaften;
	private JButton _verbinden;
	
	private Raum _raum;
	
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
		add(_verbinden = new JButton("verbinden mit"), BorderLayout.EAST);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		_raum.setMaus(_eigenschaften.getMaus() ? new Maus(_raum) : null);
		
		Stack<Item> items = new Stack<Item>();
		for(int i = 0; i < _eigenschaften.getKuchenzahl(); ++i)
			items.push(Item.Kuchen);
		for(int i = 0; i < _eigenschaften.getGiftkuchenzahl(); ++i)
			items.push(Item.Giftkuchen);
			
		_raum.setItems(items);
	}
}
