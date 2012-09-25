package de.uni_hamburg.informatik.sep.zuul.editor;

/**
 * Hält levelglobale (raumunabhängige) Eigenschaften eines Levels.
 * @author 0graeff
 */
public class EditorLevel
{
	private int _leben = 10;
	private int _katzen = 1;
	private int _maeuse = 2;

	/**
	 * Gibt die Anzahl der Mäuse des Levels zurück.
	 */
	public int getMaeuse()
	{
		return _maeuse;
	}

	/**
	 * Setzt die Anzahl der Mäuse des Levels.
	 */
	public void setMaeuse(int maeuse)
	{
		_maeuse = maeuse;
	}
	
	/**
	 * Gibt die Anzahl der Katzen des Levels zurück.
	 */
	public int getKatzen()
	{
		return _katzen;
	}
	
	/**
	 * Setzt die Anzahl der Katzen des Levels.
	 */
	public void setKatzen(int katzen)
	{
		_katzen = katzen;
	}

	/**
	 * Gibt die Start-Lebespunkte-Anzahl zurück, die der Spieler in diesem Level hat.
	 */
	public int getLeben()
	{
		return _leben;
	}
	
	/**
	 * Setzt die Start-Lebensenergie, die der Spieler in diesem Level haben wird. 
	 */
	public void setLeben(int leben)
	{
		_leben = leben;
	}
}
