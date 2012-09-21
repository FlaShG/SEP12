package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Hauptfenster extends JFrame
{
	private KonsolenPanel _konsolenPanel;
	private BildPanel _bildPanel;
	private BefehlsPanel _befehlsPanel;

	public Hauptfenster(BildPanel bildPanel, KonsolenPanel konsolenPanel,
			BefehlsPanel befehlsPanel)
	{
		super("Zuul");
		_bildPanel = bildPanel;
		_konsolenPanel = konsolenPanel;
		_befehlsPanel = befehlsPanel;

		initialisiereUI();
	}

	private void initialisiereUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1024, 720);

		getContentPane().add(_bildPanel);
		getContentPane().add(_konsolenPanel);
		getContentPane().add(_befehlsPanel);

		this.addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentShown(ComponentEvent arg0)
			{
				super.componentShown(arg0);

			}

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				super.componentResized(arg0);
				int breite = (int) Hauptfenster.this.getWidth();
				int hoehe = Hauptfenster.this.getHeight();

				_bildPanel.setSize(breite - (breite / 5), hoehe / 2);
				_konsolenPanel.setSize(breite - (breite / 5), hoehe / 2);
				_befehlsPanel.setSize(breite / 5, hoehe);

				_bildPanel.setLocation(0, 0);
				_konsolenPanel.setLocation(0, _bildPanel.getHeight());
				_befehlsPanel.setLocation(_bildPanel.getWidth(), 0);

			}

		});

		setLocationRelativeTo(null);
		setVisible(false);

	}

}
