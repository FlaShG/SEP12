package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.util.FancyFunction;

/**
 * Die Werkzeugklasse für das Editorfenser.
 * 
 * @author 0graeff
 * 
 */
public class EditorFenster implements EditorBeobachter
{
	private EditorFensterUI _ui;
	private EditorLevel _leveldaten;
	private SpeicherWerkzeug _speicherWerkzeug;
	private LadenWerkzeug _ladenWerkzeug;
	private boolean _unsavedChanges;
	private boolean _pathDisplay;

	private WindowListener _windowListener;

	/**
	 * Erstellt ein neues {@link EditorFenster}.
	 */
	public EditorFenster()
	{
		_leveldaten = new EditorLevel();
		_ui = new EditorFensterUI(this);

		_speicherWerkzeug = new SpeicherWerkzeug(_leveldaten, _ui);
		_ladenWerkzeug = new LadenWerkzeug();

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
					raumwahlUpdate(true);
				}
			}
		});

		_ui.getMenuBar().getSpeicherButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(_speicherWerkzeug.valide())
						{
							String str = FileChooser.speichereDatei(FileChooser
									.konfiguriereFileChooser(true));
							if(str != null)
							{
								_speicherWerkzeug.speichern(str);
								unsavedChanges(false);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(new JPanel(),
									EditorTextVerwalter.UNGUELTIGES_LEVEL_TEXT,
									EditorTextVerwalter.UNGUELTIGES_LEVEL,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

		_ui.getMenuBar().getPathfindingButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						_pathDisplay = !_pathDisplay;
						if(_pathDisplay)
						{
							//macht Pathfinding
							_speicherWerkzeug.valide();
						}

						GridButton[][] buttons = _ui.getMap().getButtonArray();
						for(GridButton[] line : buttons)
						{
							for(GridButton button : line)
							{
								Raum raum = button.getRaum();
								if(raum != null)
								{
									button.setText(_pathDisplay ? raum
											.getPathToFinishLength() : raum
											.getName());
									button.setEnabled(!_pathDisplay);
								}
							}
						}

						((JButton) e.getSource())
								.setText(_pathDisplay ? "zurück" : "Pathfinder");
					}
				});

		_ui.getMenuBar().getLadenButton()
				.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(!_unsavedChanges
								|| BestaetigungsDialog.erstelle(
										EditorTextVerwalter.LEVEL_LADEN,
										EditorTextVerwalter.LEVEL_LADEN_CHECK))
						{
							String str = FileChooser.oeffneDatei(FileChooser
									.konfiguriereFileChooser(false));
							if(str != null && !str.equals(""))
							{
								resetEditorFenster(1, 1);
								//markiert auch, dass geladen wird
								_leveldaten = null;
								_ladenWerkzeug.lade(str);

								getUI().setMap(_ladenWerkzeug.getMap());
								setEditorLevel(_ladenWerkzeug.getManager()
										.getEditorLevel());

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
						|| BestaetigungsDialog.erstelle(
								EditorTextVerwalter.NEUES_LEVEL,
								EditorTextVerwalter.NEUES_LEVEL_CHECK))
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
										.erstelle(
												EditorTextVerwalter.KARTENGROESSE_AENDERN,
												EditorTextVerwalter.KARTENGROESSE_AENDERN_CHECK))
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
						|| BestaetigungsDialog.erstelle(
								EditorTextVerwalter.EDITOR_BEENDEN,
								EditorTextVerwalter.EDITOR_BEENDEN_CHECK))
				{
					System.exit(0);
				}
			}
		});
	}

	@Override
	public void raumwahlUpdate(boolean neuerRaum)
	{
		_ui.getFrame().remove(_ui.getRaumhinzu());
		RaumBearbeitenPanel bearbeitenPanel = _ui.getBearbeitenPanel();
		if(bearbeitenPanel != null)
			_ui.getFrame().remove(bearbeitenPanel);
		_ui.getFrame().remove(_ui.getFueller());

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
				if(neuerRaum)
				{
					unsavedChanges(true);
				}
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
		else
		{
			_ui.getFrame().add(_ui.getFueller(), BorderLayout.SOUTH);
			_ui.getFueller().setText(randomHilfetext());
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

			_ui.getMap().getAktivenButton().setAusgewaehlt(true);
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
				EditorTextVerwalter.EDITOR_TITEL
						+ (yes ? " (ungespeicherte Änderungen)" : ""));
	}

	private String randomHilfetext()
	{
		return FancyFunction.getRandomEntry(EditorTextVerwalter.EDITOR_TIPPS);
	}
}
