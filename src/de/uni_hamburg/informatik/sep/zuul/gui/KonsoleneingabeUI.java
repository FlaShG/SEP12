package de.uni_hamburg.informatik.sep.zuul.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KonsoleneingabeUI
{

	private JPanel _panel;
	private JTextField _eingabeFeld;
	private JButton _enterButton;
	
	public KonsoleneingabeUI()
	{
		_eingabeFeld = new JTextField(20);
		
		_enterButton = new JButton("Enter");
		
		_panel = new JPanel();
		_panel.add(_eingabeFeld);
		_panel.add(_enterButton);
	}
	
	public JComponent getPanel()
	{
		// TODO Auto-generated method stub
		return _panel;
	}
	
	public JButton getEnterButton()
	{
		return _enterButton;
	}

	public JTextField getTextField()
	{
		return _eingabeFeld;
	}

}
