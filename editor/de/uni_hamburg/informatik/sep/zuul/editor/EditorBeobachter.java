package de.uni_hamburg.informatik.sep.zuul.editor;

public interface EditorBeobachter
{
	public void raumwahlUpdate();

	public void eigenschaftUpdate();

	public void verschiebenUpdate();
	public void verschiebenUpdate(int x, int y);
	
	public void warpUpdate();
}
