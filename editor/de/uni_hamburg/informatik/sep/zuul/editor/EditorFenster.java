package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

/**
 * Die Werkzeugklasse für das Editorfenser.
 * @author 0graeff
 *
 */
public class EditorFenster implements EditorBeobachter
{
	private EditorFensterUI _ui;
	private EditorLevel _leveldaten;
	private SpeicherWerkzeug _speicherWerkzeug;
	private LadenWerkzeug _ladenWerkzeug;
	
	public EditorFenster()
	{
		_leveldaten = new EditorLevel();
		_ui = new EditorFensterUI(this);
		_speicherWerkzeug = new SpeicherWerkzeug(this);
		_ladenWerkzeug = new LadenWerkzeug(this);
		
		registriereUIAktionen();
	}
	
	private void registriereUIAktionen()
	{
		_ui.getRaumhinzu().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_ui.getMap().fuegeRaumZuAktivemButtonHinzu();
				if(_ui.getMap().getAktivenRaum() != null)
				{
					 raumwahlUpdate();
				}
			}
		});
		
		_ui.getMenuBar().getSpeicherButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_speicherWerkzeug.speichern("./xml_dateien/");
			}
		});
		
		_ui.getMenuBar().getLadenButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ladenWerkzeug.lade("./xml_dateien/testStruktur.xml");
			}
		});
	}
	
	@Override
	public void raumwahlUpdate()
	{
		_ui.getFrame().remove(_ui.getRaumhinzu());
		RaumBearbeitenPanel bearbeitenPanel = _ui.getBearbeitenPanel();
		if(bearbeitenPanel != null)
			_ui.getFrame().remove(bearbeitenPanel);
			
		_ui.getFrame().setVisible(true);
		
		if(_ui.getMap().buttonAusgewaehlt())
		{
			Raum raum = _ui.getMap().getAktivenRaum();
			
			if(raum == null)
			{
				_ui.getFrame().add(_ui.getRaumhinzu(), BorderLayout.SOUTH);
			}
			else
			{
				_ui.getFrame().add(_ui.neuesBearbeitenPanel(raum), BorderLayout.SOUTH);
				_ui.getBearbeitenPanel().getLoeschenButton().addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						_ui.getMap().loescheRaumDesAktivenButtons();
					}
				});
			}
		}
		_ui.getFrame().setVisible(true);
	}

	@Override
	public void eigenschaftUpdate()
	{
		RaumBearbeitenPanel bearbeitenPanel = _ui.getBearbeitenPanel();
		if(bearbeitenPanel != null)
		{
			Raum raum = bearbeitenPanel.getRaum();
			
			RaumEigenschaftenPanel eigenschaften = bearbeitenPanel.getEigenschaftenPanel();
			raum.setName(eigenschaften.getRaumname());
			_ui.getMap().getAktivenButton().setText(eigenschaften.getRaumname());
			
			raum.setRaumart(eigenschaften.getTyp());
			
			Stack<Item> items = new Stack<Item>();
			for(int i = 0; i < eigenschaften.getKuchenzahl(); ++i)
				items.push(Item.Kuchen);
			for(int i = 0; i < eigenschaften.getGiftkuchenzahl(); ++i)
				items.push(Item.Giftkuchen);
				
			raum.setItems(items);
		}
		
		_leveldaten.setMaeuse(_ui.getLevelPanel().getMauszahl());
	}

	public EditorFensterUI getUI()
	{
		return _ui;
	}

	public EditorLevel getEditorLevel()
	{
		return _leveldaten;
	}

	public void setEditorLevel(EditorLevel editorLevel)
	{
		_leveldaten = editorLevel;
	}
}
