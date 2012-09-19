package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;

import de.uni_hamburg.informatik.sep.zuul.Katze;

public class SpielKontext
{
	private Raum _aktuellerRaum;
	private int _lebensEnergie;
	private Inventar _inventar;
	private ArrayList<Katze> _katzen = new ArrayList<Katze>();

	private final PropertyChangeSupport changes = new PropertyChangeSupport(
			this);
	private boolean _spielZuende;

	public SpielKontext()
	{}
	
	public SpielKontext(Raum aktuellerRaum, int lebensEnergie, Inventar inventar)
	{
		_aktuellerRaum = aktuellerRaum;
		_lebensEnergie = lebensEnergie;
		_inventar = inventar;
	}
	
	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
	}

	// TODO: addTickListener() 

	private LinkedList<TickListener> tickListeners = new LinkedList<>();

	public void addTickListener(TickListener tickListener)
	{
		tickListeners.add(tickListener);
	}

	public void removeTickListener(TickListener tickListener)
	{
		tickListeners.remove(tickListener);
	}

	
	private boolean _hasRoomChanged = false;
	public void fireTickEvent()
	{
		boolean hasRoomChanged = _hasRoomChanged;
		
		_hasRoomChanged = false;
		
		for(TickListener tickListener : tickListeners)
			if(!tickListener.tick(this, hasRoomChanged))
				return;
	}

	public void setAktuellerRaum(Raum aktuellerRaum)
	{
		Raum alterRaum = _aktuellerRaum;
		_aktuellerRaum = aktuellerRaum;
		
		_hasRoomChanged = true;
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
		changes.firePropertyChange("LebensEnergie", alteLebensEnergie,
				lebensEnergie);
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

	public void addPropertyChangeListener(String property,
			PropertyChangeListener l)
	{
		changes.addPropertyChangeListener(property, l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		changes.removePropertyChangeListener(l);
	}

	public void removePropertyChangeListener(String property,
			PropertyChangeListener l)
	{
		changes.removePropertyChangeListener(property, l);
	}

	/**
	 * @return the spielZuende
	 */
	public boolean isSpielZuende()
	{
		return _spielZuende;
	}

	/**
	 * @param spielZuende
	 *            the spielZuende to set
	 */
	public void spielZuende()
	{
		_spielZuende = true;
		changes.firePropertyChange("SpielZuende", false, true);
	}

	/**
	 * @return the katzen
	 */
	public ArrayList<Katze> getKatzen()
	{
		return _katzen;
	}
}
