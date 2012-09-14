package de.uni_hamburg.informatik.sep.zuul.gui;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HauptfensterUI
{

	private JFrame _frame;
	private JPanel _panel;

	public HauptfensterUI()
	{
		//TODO Name
		_frame = new JFrame();
		
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.PAGE_AXIS));
		
		_frame.setContentPane(_panel);
		
		
	}
	public void addFenster(JComponent component)
	{
		_panel.add(component);
	}
	
	public void zeigeFenter()
	{
		_frame.setVisible(true);
	}
	
	public void versteckeFenster()
	{
		_frame.setVisible(false);
	}

}
