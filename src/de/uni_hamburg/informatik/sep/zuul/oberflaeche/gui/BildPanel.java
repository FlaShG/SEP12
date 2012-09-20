package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BildPanel extends JPanel
{
	
	private JButton _tuerNordButton;
	private JButton _tuerOstButton;
	private JButton _tuerSuedButton;
	private JButton _tuerWestButton;

	private JButton _schaueNordButton;
	private JButton _schaueOstButton;
	private JButton _schaueSuedButton;
	private JButton _schaueWestButton;
	
	private JLabel _LabelFuerIcon;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BildPanel()
	{
	
		setLayout(null);
		_LabelFuerIcon = new JLabel();
		_LabelFuerIcon.setLocation(0, 0);
		
		_tuerNordButton = new JButton();
		_tuerNordButton.setBackground(new Color(180,90,0));
		_tuerNordButton.setFocusable(false);
		_tuerNordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		_tuerOstButton = new JButton();
		_tuerOstButton.setBackground(new Color(180,90,0));
		_tuerOstButton.setFocusable(false);
		_tuerOstButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		_tuerSuedButton = new JButton();
		_tuerSuedButton.setBackground(new Color(180,90,0));
		_tuerSuedButton.setFocusable(false);
		_tuerSuedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		_tuerWestButton = new JButton();
		_tuerWestButton.setBackground(new Color(180,90,0));
		_tuerWestButton.setFocusable(false);
		_tuerWestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		_schaueNordButton = new JButton();
		_schaueOstButton = new JButton();
		_schaueSuedButton = new JButton();
		_schaueWestButton = new JButton();
		
		
		
		add(_tuerNordButton);
		add(_tuerOstButton);
		add(_tuerSuedButton);
		add(_tuerWestButton);
		add(_schaueNordButton);
		add(_schaueOstButton);
		add(_schaueSuedButton);
		add(_schaueWestButton);
		
		
		add(_LabelFuerIcon);
		addComponentListener(new ComponentAdapter()
		{
			
			
			
			@Override
			public void componentResized(ComponentEvent e)
			{
				_LabelFuerIcon.setLocation(0, 0);
				_LabelFuerIcon.setSize(205, 205);
				
				_tuerNordButton.setSize(25, 20);
				_tuerOstButton.setSize(20, 25);
				_tuerSuedButton.setSize(25, 20);
				_tuerWestButton.setSize(20, 25);
				
				_tuerNordButton.setLocation(90, 0);
				_tuerOstButton.setLocation(185, 85);
				_tuerSuedButton.setLocation(90, 185);
				_tuerWestButton.setLocation(0, 85);
				
				_schaueNordButton.setSize(25, 20);
				_schaueOstButton.setSize(20, 25);
				_schaueSuedButton.setSize(25, 20);
				_schaueWestButton.setSize(20, 25);
				
				_schaueNordButton.setLocation(115, 0);
				_schaueOstButton.setLocation(185, 60);
				_schaueSuedButton.setLocation(115, 185);
				_schaueWestButton.setLocation(0, 60);
				
				
				
			}
			
			
		});
		
		setVisible(true);
		
	}
	
	public void setRaumanzeige(BufferedImage img)
	{
		_LabelFuerIcon.setIcon(new ImageIcon(img));
		repaint();
	}

	public JButton getTuerNordButton()
	{
		return _tuerNordButton;
	}

	

	public JButton getTuerOstButton()
	{
		return _tuerOstButton;
	}

	

	public JButton getTuerSuedButton()
	{
		return _tuerSuedButton;
	}

	

	public JButton getTuerWestButton()
	{
		return _tuerWestButton;
	}

	

	public JButton getSchaueNordButton()
	{
		return _schaueNordButton;
	}

	
	public JButton getSchaueOstButton()
	{
		return _schaueOstButton;
	}

	

	public JButton getSchaueSuedButton()
	{
		return _schaueSuedButton;
	}

	

	public JButton getSchaueWestButton()
	{
		return _schaueWestButton;
	}

	
}
