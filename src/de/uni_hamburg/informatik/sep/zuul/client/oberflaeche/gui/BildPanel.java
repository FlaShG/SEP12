package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

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

	private final String PATH = getClass().getResource("bilder/").getPath();
	private final ImageIcon FENSTERNS = new ImageIcon(getClass().getResource(
			"bilder/").getPath()
			+ "fenster25.png");
	private final ImageIcon FENSTEROW = new ImageIcon(getClass().getResource(
			"bilder/").getPath()
			+ "fenster20.png");
	private JButton _tuerNordButton;
	private JButton _tuerOstButton;
	private JButton _tuerSuedButton;
	private JButton _tuerWestButton;
	
	private JLabel _schauenLabel;
	private JLabel _LabelFuerIcon;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BildPanel()
	{

		setLayout(null);
		setSize(341, 512);
		setDoubleBuffered(true);
		_LabelFuerIcon = new JLabel();
		_LabelFuerIcon.setSize(this.getWidth(), this.getHeight());
		_LabelFuerIcon.setLocation(0, 0);
		
		_schauenLabel = new JLabel();
		_schauenLabel.setVisible(false);
		_schauenLabel.setSize(this.getWidth(), this.getHeight());
		_schauenLabel.setLocation(0, 0);

		

		_tuerNordButton = new JButton();
		_tuerNordButton.setContentAreaFilled(false);
		_tuerNordButton.setBorderPainted(false);
		_tuerNordButton.setFocusable(false);
		_tuerNordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		_tuerOstButton = new JButton();
		_tuerOstButton.setContentAreaFilled(false);
		_tuerOstButton.setBorderPainted(false);
		_tuerOstButton.setFocusable(false);
		_tuerOstButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		_tuerSuedButton = new JButton();
		_tuerSuedButton.setContentAreaFilled(false);
		_tuerSuedButton.setBorderPainted(false);
		_tuerSuedButton.setFocusable(false);
		_tuerSuedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		_tuerWestButton = new JButton();
		_tuerWestButton.setContentAreaFilled(false);
		_tuerWestButton.setBorderPainted(false);
		_tuerWestButton.setFocusable(false);
		_tuerWestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


		add(_tuerNordButton);
		add(_tuerOstButton);
		add(_tuerSuedButton);
		add(_tuerWestButton);
		add(_schauenLabel);
		add(_LabelFuerIcon);
	

		

		addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent e)
			{
				_LabelFuerIcon.setLocation(0, 0);

				if(BildPanel.this.getWidth() > BildPanel.this.getHeight())
					_LabelFuerIcon.setSize(BildPanel.this.getHeight(),
							BildPanel.this.getHeight());
				else
					_LabelFuerIcon.setSize(BildPanel.this.getWidth(),
							BildPanel.this.getWidth());

				
				
				if(BildPanel.this.getWidth() > BildPanel.this.getHeight())
					_schauenLabel.setSize(BildPanel.this.getHeight(),
							BildPanel.this.getHeight());
				else
					_schauenLabel.setSize(BildPanel.this.getWidth(),
							BildPanel.this.getWidth());
				
				
				

				int b = _LabelFuerIcon.getWidth();
				int h = _LabelFuerIcon.getHeight();

				_tuerNordButton.setSize(
						(int) (_LabelFuerIcon.getWidth() / 6.4),
						_LabelFuerIcon.getHeight() / 8);
				_tuerOstButton.setSize(_LabelFuerIcon.getHeight() / 7,
						(int) (_LabelFuerIcon.getWidth() / 6.4));
				_tuerSuedButton.setSize(
						(int) (_LabelFuerIcon.getWidth() / 6.4),
						_LabelFuerIcon.getHeight() / 8);
				_tuerWestButton.setSize(_LabelFuerIcon.getHeight() / 7,
						(int) (_LabelFuerIcon.getWidth() / 6.4));

				_tuerNordButton.setLocation(_LabelFuerIcon.getWidth() / 2
						- (_tuerNordButton.getWidth() / 2), 0);
				_tuerOstButton.setLocation(_LabelFuerIcon.getWidth()
						- _tuerOstButton.getWidth(), _LabelFuerIcon.getWidth()
						/ 2 - (_tuerOstButton.getHeight() / 2));
				_tuerSuedButton.setLocation(
						_LabelFuerIcon.getWidth() / 2
								- (_tuerSuedButton.getWidth() / 2),
						_LabelFuerIcon.getHeight()
								- (_tuerSuedButton.getHeight()));
				_tuerWestButton.setLocation(0, _LabelFuerIcon.getWidth() / 2
						- (_tuerWestButton.getHeight() / 2));

				

				
				
				
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

	public JLabel getLabelFuerIcon()
	{
		return _LabelFuerIcon;
	}

	public void zeigeSchauen(BufferedImage img)
	{
		_LabelFuerIcon.setVisible(false);
		_schauenLabel.setVisible(true);
		_schauenLabel.setIcon(new ImageIcon(img));
		repaint();
	}
	
	public void versteckeSchauen()
	{
		_schauenLabel.setVisible(false);
		_LabelFuerIcon.setVisible(true);
	}

	public JLabel getSchauenLabel()
	{
		return _schauenLabel;
	}



}
