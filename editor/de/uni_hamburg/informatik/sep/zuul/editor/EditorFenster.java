package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Die Werkzeugklasse für das Editorfenser.
 * 
 * @author 0graeff
 * 
 */
public class EditorFenster implements EditorBeobachter
{
	public static final String EDITOR_TITEL = "Zuul-Editor";
	
	private EditorFensterUI _ui;
	private EditorLevel _leveldaten;
	private SpeicherWerkzeug _speicherWerkzeug;
	private LadenWerkzeug _ladenWerkzeug;
	private boolean _unsavedChanges;

	public EditorFenster()
	{
		_leveldaten = new EditorLevel();
		_ui = new EditorFensterUI(this);

		_speicherWerkzeug = new SpeicherWerkzeug(this);
		_ladenWerkzeug = new LadenWerkzeug(this);

		resetEditorFenster(8,8);
	}

	private void resetEditorFenster(int width, int height)
	{
		_ui.init(_leveldaten, width, height);

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
					unsavedChanges(true);
				}
			}
		});

		_ui.getMenuBar().getSpeicherButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String str = FileChooser.speichereDatei();
						if(str != null)
						{
							_speicherWerkzeug.speichern(str);
							unsavedChanges(false);
						}
					}
				});

		_ui.getMenuBar().getLadenButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Object[] options = {"Ja", "Nein"};
						int jp = 0;
						if(_unsavedChanges)
						{
							jp = JOptionPane.showOptionDialog(new JPanel(), "Möchten Sie wirklich ein anderes Level laden? Ungespeicherte Änderungen werden verloren gehen.", "Level laden", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						}
						if (jp == 0)
						{
							String str = FileChooser.oeffneDatei();
							if(str != null && !str.equals(""))
							{
								resetEditorFenster(1,1);
								//markiert auch, dass geladen wird
								_leveldaten = null;
								_ladenWerkzeug.lade(str);
								unsavedChanges(false);
							}
						}
					}
				});

		_ui.getMenuBar().getNeuButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object[] options = {"Ja", "Nein"};
				int jp = 0;
				if(_unsavedChanges)
				{
					jp = JOptionPane.showOptionDialog(new JPanel(), "Möchten Sie wirklich ein neues Level erstellen? Ungespeicherte Änderungen werden verloren gehen.", "Neues Level", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				}
				if (jp == 0)
				{
					MapSizeDialog mapsize = new MapSizeDialog();
					resetEditorFenster(mapsize.getWidth(), mapsize.getHeight());
					unsavedChanges(false);
				}
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
				_ui.zeigeVerschiebenPanel(false);
			}
			else
			{
				_ui.getFrame().add(_ui.neuesBearbeitenPanel(raum),
						BorderLayout.SOUTH);
				_ui.getBearbeitenPanel().getLoeschenButton()
						.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent arg0)
							{
								_ui.getMap().loescheRaumDesAktivenButtons();
								unsavedChanges(true);
							}
						});
				_ui.zeigeVerschiebenPanel(true);
			}
		}
		
		_ui.getFrame().setVisible(true);
	}

	@Override
	public void eigenschaftUpdate()
	{
		//early out, wenn wir gerade laden.
		if(_leveldaten == null)
			return;
		
		
		RaumBearbeitenPanel bearbeitenPanel = _ui.getBearbeitenPanel();
		if(bearbeitenPanel != null)
		{
			Raum raum = bearbeitenPanel.getRaum();

			RaumEigenschaftenPanel eigenschaften = bearbeitenPanel
					.getEigenschaftenPanel();
			raum.setName(eigenschaften.getRaumname());
			_ui.getMap().getAktivenButton()
					.setText(eigenschaften.getRaumname());

			raum.setRaumart(eigenschaften.getTyp());

			Stack<Item> items = new Stack<Item>();
			for(int i = 0; i < eigenschaften.getKuchenzahl(); ++i)
				items.push(Item.UKuchen);
			for(int i = 0; i < eigenschaften.getGiftkuchenzahl(); ++i)
				items.push(Item.UGiftkuchen);

			raum.setItems(items);
			
			raum.setBescheibung(bearbeitenPanel.getBeschreibung().getText());
		}

		_leveldaten.setLeben(_ui.getLevelPanel().getLebenspunkte());
		_leveldaten.setMaeuse(_ui.getLevelPanel().getMauszahl());
		_leveldaten.setKatzen(_ui.getLevelPanel().getKatzenzahl());
		
		unsavedChanges(true);
	}
	
	@Override
	public void verschiebenUpdate(int x, int y)
	{
		_ui.getMap().verschiebeAktuellenRaumRelativ(x, y);
		
		unsavedChanges(true);
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
		_ui.getLevelPanel().setLebenspunkte(editorLevel.getLeben());
		_ui.getLevelPanel().setMauszahl(editorLevel.getMaeuse());
		_ui.getLevelPanel().setKatzenzahl(editorLevel.getKatzen());
		//ganz zum Schluss, damit beim Laden null.
		//Obige Anweisungen würden sonst Dinge triggern.
		_leveldaten = editorLevel;
	}
	
	public void unsavedChanges(boolean yes)
	{
		_unsavedChanges = yes;
		_ui.getFrame().setTitle(EDITOR_TITEL+(yes ? " (ungespeicherte Änderungen)" : ""));
	}
}
