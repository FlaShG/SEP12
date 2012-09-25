package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JPanel;

/**
 * Zeigt die Einstellmöglichkeiten für levelglobale Einstellungen an. Siehe {@link EditorLevel}.
 * @author 0graeff
 *
 */
public class LevelPanel extends JPanel
{
	private EigenschaftIntPanel _leben;
	private EigenschaftIntPanel _maeuse;
	private EigenschaftIntPanel _katzen;

	/**
	 * Erstellt ein neues {@link LevelPanel}.
	 * @param beobachter ein {@link EditorBeobachter}, der über Änderungen informiert wird.
	 * @param level das {@link EditorLevel}, das die Startwerte hält. Wird nicht verändert! Das muss der beobachter machen.
	 */
	public LevelPanel(EditorBeobachter beobachter, EditorLevel level)
	{
		add(_leben = new EigenschaftIntPanel("Lebenspunkte", level.getLeben(), beobachter));
		add(_maeuse = new EigenschaftIntPanel("Mäuse", level.getMaeuse(), beobachter));
		add(_katzen = new EigenschaftIntPanel("Katzen", level.getKatzen(), beobachter));
	}

	/**
	 * Setzt die Mauszahl.
	 */
	public void setMauszahl(int maeuse)
	{
		_maeuse.setWert(maeuse);
	}

	/**
	 * Gibt die eingestellte Anzahl der Mäuse zurück.
	 */
	public int getMauszahl()
	{
		return _maeuse.getWert();
	}
	
	/**
	 * Setzt die Katzenzahl.
	 */
	public void setKatzenzahl(int katzen)
	{
		_katzen.setWert(katzen);
	}

	/**
	 * Gibt die Anzahl der Katzen zurück.
	 */
	public int getKatzenzahl()
	{
		return _katzen.getWert();
	}

	/**
	 * Setzt die Anzahl der Lebenspunkte.
	 */
	public void setLebenspunkte(int leben)
	{
		_leben.setWert(leben);
	}

	/**
	 * Gibt die eingestellte Anzahl der Lebenspunkte zurück.
	 */
	public int getLebenspunkte()
	{
		return _leben.getWert();
	}
}
