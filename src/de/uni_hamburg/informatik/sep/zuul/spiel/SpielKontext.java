package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SpielKontext
{
	private Raum _aktuellerRaum;
	private int _lebensEnergie;
	private Inventar _inventar;

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);


	public SpielKontext()
	{
	}

	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
	}

	public void setAktuellerRaum(Raum aktuellerRaum)
	{
		Raum alterRaum = _aktuellerRaum;
		_aktuellerRaum = aktuellerRaum;
		changes.firePropertyChange("AktuellerRaum", alterRaum, aktuellerRaum);
	}

	public int getLebensEnergie()
	{
		return _lebensEnergie;
	}

	public void setLebensEnergie(int lebensEnergie)
	{
		int alteLebensEnergie = _lebensEnergie;
		_lebensEnergie = lebensEnergie;
		changes.firePropertyChange("LebensEnergie", alteLebensEnergie, lebensEnergie);
	}

	public Inventar getInventar()
	{
		return _inventar;
	}

	public void setInventar(Inventar inventar)
	{
		Inventar altesInventar = _inventar;
		_inventar = inventar;
		changes.firePropertyChange("Inventar", altesInventar, inventar);
	}

	public void addPropertyChangeListener(PropertyChangeListener l)
	{
		changes.addPropertyChangeListener(l);
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener l)
	{
		changes.addPropertyChangeListener(property, l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		changes.removePropertyChangeListener(l);
	}

	public void removePropertyChangeListener(String property, PropertyChangeListener l)
	{
		changes.removePropertyChangeListener(property, l);
	}
}