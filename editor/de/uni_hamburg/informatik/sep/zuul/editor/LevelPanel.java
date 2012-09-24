package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JPanel;

public class LevelPanel extends JPanel
{
	private EigenschaftIntPanel _leben;
	private EigenschaftIntPanel _maeuse;
	private EigenschaftIntPanel _katzen;

	public LevelPanel(EditorBeobachter beobachter, EditorLevel level)
	{
		add(_leben = new EigenschaftIntPanel("Lebenspunkte", level.getLeben(), beobachter));
		add(_maeuse = new EigenschaftIntPanel("Mäuse", level.getMaeuse(), beobachter));
		add(_katzen = new EigenschaftIntPanel("Katzen", level.getKatzen(), beobachter));
	}

	public void setMauszahl(int maeuse)
	{
		_maeuse.setWert(maeuse);
	}

	public int getMauszahl()
	{
		return _maeuse.getWert();
	}
	
	public void setKatzenzahl(int katzen)
	{
		_katzen.setWert(katzen);
	}

	public int getKatzenzahl()
	{
		return _katzen.getWert();
	}
	
	public void setLebenspunkte(int leben)
	{
		_leben.setWert(leben);
	}

	public int getLebenspunkte()
	{
		return _leben.getWert();
	}
}
