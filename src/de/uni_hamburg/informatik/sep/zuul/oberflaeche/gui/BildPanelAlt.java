package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BildPanelAlt extends JPanel
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

	public BildPanelAlt()
	{
		super();

		initialisiereUI();
	}

	private void initialisiereUI()
	{
		_tuerNordButton = new JButton();
		_tuerOstButton = new JButton();
		_tuerSuedButton = new JButton();
		_tuerWestButton = new JButton();

		_LabelFuerIcon = new JLabel();
		_LabelFuerIcon.setLayout(null);
		_LabelFuerIcon.setSize(205, 205);
		_LabelFuerIcon.setVisible(true);
		_LabelFuerIcon.setBackground(Color.red);
		
		
		this.add(_LabelFuerIcon);
		this.setBackground(Color.BLUE);

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

	/**
	 * aktualisiert die Raumanzeige auf den nächsten Raum
	 * 
	 * @param img
	 *            Das neue Raumbild
	 */
	public void setRaumanzeige(BufferedImage img)
	{
		if(img != null)
		{
			_LabelFuerIcon.setIcon(new ImageIcon(img));
			repaint();
		}
	}

}
