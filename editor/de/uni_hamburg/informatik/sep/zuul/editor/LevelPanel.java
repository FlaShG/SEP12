package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JPanel;

public class LevelPanel extends JPanel
{
	private EigenschaftIntPanel _maeuse;

	public LevelPanel(EditorBeobachter beobachter, EditorLevel level)
	{
		add(_maeuse = new EigenschaftIntPanel("Mäuse", level.getMaeuse(),
				beobachter));
	}

	public void setMauszahl(int maeuse)
	{
		_maeuse.setWert(maeuse);
	}

	public int getMauszahl()
	{
		return _maeuse.getWert();
	}
}
