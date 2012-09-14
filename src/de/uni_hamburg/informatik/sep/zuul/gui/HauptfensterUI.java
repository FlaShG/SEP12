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
	
	/**
	 * Fügt eine Komponente zum Panel hinzu
	 * @param component Die hinzuzufügende Komponente
	 */
	public void addComponent(JComponent component)
	{
		_panel.add(component);
	}
	
	/**
	 * Zeigt das Fenster an
	 */
	public void zeigeFenter()
	{
		_frame.setVisible(true);
	}
	
	/**
	 * Versteckt das Fenster
	 */
	public void versteckeFenster()
	{
		_frame.setVisible(false);
	}

}
