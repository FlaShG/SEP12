package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;

import de.uni_hamburg.informatik.sep.zuul.Katze;
import de.uni_hamburg.informatik.sep.zuul.features.TickListener;

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
	{
	}

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

	/**
	 * Feuert das Tick Event. Soll nur von Spiel genutzt werden!
	 */
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

	public boolean isKatzeImAktuellenRaum()
	{
		for(Katze katze : _katzen)
			if(katze.getRaum() == _aktuellerRaum)
				return true;
		return false;
	}

	//	private void aktualisiereRaumansicht()
	//	{
	//		
	//		_aktuelleRaumansicht = new BufferedImage(245, 245, BufferedImage.TYPE_INT_RGB);
	//		_aktuelleRaumansicht = ladeBild("Z:\\SEP\\test2.png");
	//		BufferedImage maus = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
	//		
	//
	//		if(_aktuellerRaum.hasMaus())
	//		{
	//			maus = ladeBild("Z:\\SEP\\maus.png");
	//
	//			maleAufRaumansicht(maus);
	//		}
	//
	//		
	//
	//	}
	//
	//	private BufferedImage ladeBild(String pfad)
	//	{
	//		File f = new File(pfad);
	//		BufferedImage img = null;
	//		try
	//		{
	//			 img = ImageIO.read(f);
	//		}
	//		catch(IOException e)
	//		{
	//			
	//		}
	//		
	//		return img;
	//		
	//		
	//	}
	//	
	//	
	//	private void maleAufRaumansicht(BufferedImage img)
	//	{
	//		int[] imgData = new int[img.getWidth() * img.getHeight()];
	//		img.getRGB(0, 0, img.getWidth(), img.getHeight(), imgData, 0, img.getWidth());
	//		
	//		for(int i = 0; i < img.getHeight(); i++)
	//        {
	//            for(int j = 0; j < img.getWidth(); j++)
	//            {
	//                if(img.getRGB(i, j) != new Color(255,128,255).getRGB())
	//                    _aktuelleRaumansicht.setRGB(i+50, j+50, img.getRGB(i, j));
	//            }
	//        }	
	//	}
	//	
	//	public BufferedImage getAktuelleRaumasnsicht()
	//	{
	//		return _aktuelleRaumansicht;
	//	}

}
