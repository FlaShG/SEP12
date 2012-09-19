package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Observer;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Ein Panel, das ein mit JLabel beschriftetes JTextField anzeigt.
 * @author 0graeff
 */
public class EigenschaftTextPanel extends EigenschaftsPanel
{
	private JTextField _text;
	
	public EigenschaftTextPanel(String beschriftung, String starttext)
	{
		this(beschriftung, starttext, null);
	}
	
	public EigenschaftTextPanel(String beschriftung, String starttext, Observer beobachter)
	{
		super(beschriftung, beobachter);
		add(_text = new JTextField(starttext));
		_text.getDocument().addDocumentListener(new DocumentListener()
		{
			
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				informiereBeobachter();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				informiereBeobachter();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				
			}
		});
	}
	
	public String getText()
	{
		return _text.getText();
	}
}
