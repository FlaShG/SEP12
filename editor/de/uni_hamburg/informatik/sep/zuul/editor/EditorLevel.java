package de.uni_hamburg.informatik.sep.zuul.editor;

public class EditorLevel
{
	private int _leben = 10;
	private int _katzen = 1;
	private int _maeuse = 2;

	public int getMaeuse()
	{
		return _maeuse;
	}

	public void setMaeuse(int maeuse)
	{
		_maeuse = maeuse;
	}
	
	public int getKatzen()
	{
		return _katzen;
	}
	
	public void setKatzen(int katzen)
	{
		_katzen = katzen;
	}

	public int getLeben()
	{
		return _leben;
	}
	
	public void setLeben(int leben)
	{
		_leben = leben;
	}
}
