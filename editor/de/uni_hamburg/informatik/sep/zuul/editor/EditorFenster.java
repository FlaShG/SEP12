package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Stack;

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

	private WindowListener _windowListener;

	/**
	 * Erstellt ein neues {@link EditorFenster}.
	 */
	public EditorFenster()
	{
		_leveldaten = new EditorLevel();
		_ui = new EditorFensterUI(this);

		_speicherWerkzeug = new SpeicherWerkzeug(this);
		_ladenWerkzeug = new LadenWerkzeug(this);

		resetEditorFenster(8, 8);
	}

	private void resetEditorFenster(int width, int height)
	{
		if(_windowListener != null)
		{
			_ui.getFrame().removeWindowListener(_windowListener);
		}
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
						String str = FileChooser.speichereDatei(FileChooser.konfiguriereFileChooser(true));
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
						if(!_unsavedChanges
								|| BestaetigungsDialog
										.erstelle(
												"Level laden",
												"Möchten Sie wirklich ein anderes Level laden? Ungespeicherte Änderungen werden verloren gehen."))
						{
							String str = FileChooser.oeffneDatei(FileChooser.konfiguriereFileChooser(false));
							if(str != null && !str.equals(""))
							{
								resetEditorFenster(1, 1);
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
				if(!_unsavedChanges
						|| BestaetigungsDialog
								.erstelle(
										"Neues Level",
										"Möchten Sie wirklich ein neues Level erstellen? Ungespeicherte Änderungen werden verloren gehen."))
				{
					MapSizeDialog mapsize = new MapSizeDialog();
					if(!mapsize.getClickedOK())
						return;
					resetEditorFenster(mapsize.getBreite(), mapsize.getHoehe());
					unsavedChanges(false);
				}
			}
		});

		_ui.getMenuBar().getResizeButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						MapSizeDialog mapsize = new MapSizeDialog(_ui.getMap()
								.getBreite(), _ui.getMap().getHoehe());
						if(!mapsize.getClickedOK())
							return;

						boolean problematisch = !_ui
								.getMap()
								.istGroesseAendernUnproblematisch(
										mapsize.getBreite(), mapsize.getHoehe());
						if(!problematisch
								|| BestaetigungsDialog
										.erstelle("Kartengröße ändern",
												"Durch diese Änderung werden Räume gelöscht. Wollen Sie wirklich fortfahren?"))
						{
							_ui.getMap().setGroesse(mapsize.getBreite(),
									mapsize.getHoehe());
							_ui.getFrame().setVisible(true);
							if(problematisch)
								unsavedChanges(true);
						}
					}
				});

		_ui.getFrame().addWindowListener(_windowListener = new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				if(!_unsavedChanges
						|| BestaetigungsDialog
								.erstelle(
										"Editor beenden",
										"Möchten Sie wirklich den Editor beenden? Ungespeicherte Änderungen werden verloren gehen."))
				{
					System.exit(0);
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
				//geht net.
				_ui.getBearbeitenPanel().setzeFokusAufNamensFeld();
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
		if(_ui.getMap().buttonAusgewaehlt() && bearbeitenPanel != null)
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
	public void verschiebenUpdate()
	{
		unsavedChanges(true);
	}

	/**
	 * Gibt die UI dieses Werkzeugs zurück.
	 */
	public EditorFensterUI getUI()
	{
		return _ui;
	}

	/**
	 * Gibt den aktuellen {@link EditorLevel} zurück, der die levelglobalen
	 * Infos hält.
	 */
	public EditorLevel getEditorLevel()
	{
		return _leveldaten;
	}

	/**
	 * Setzt den {@link EditorLevel} und passt die GUI daran an.
	 * 
	 * @param editorLevel
	 *            der neue {@link EditorLevel}
	 * 
	 * @require editorLevel != null
	 */
	public void setEditorLevel(EditorLevel editorLevel)
	{
		assert editorLevel != null : "Vorbedingung verletzt: editorLevel != null";

		_ui.getLevelPanel().setLebenspunkte(editorLevel.getLeben());
		_ui.getLevelPanel().setMauszahl(editorLevel.getMaeuse());
		_ui.getLevelPanel().setKatzenzahl(editorLevel.getKatzen());
		//ganz zum Schluss, damit beim Laden null.
		//Obige Anweisungen würden sonst Dinge triggern.
		_leveldaten = editorLevel;
	}

	/**
	 * Setzt, ob es ungespeicherte Änderungen im Editor gibt und zeigt diese
	 * Info im Fenstzer an.
	 * 
	 * @param yes
	 *            ob es ungespeicherte Änderungen gibt
	 */
	public void unsavedChanges(boolean yes)
	{
		_unsavedChanges = yes;
		_ui.getFrame().setTitle(
				EDITOR_TITEL + (yes ? " (ungespeicherte Änderungen)" : ""));
	}
}
