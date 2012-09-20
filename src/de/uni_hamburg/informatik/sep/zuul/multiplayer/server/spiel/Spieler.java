package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.inventar.Inventar;

public class Spieler {

	private String _name;
	private int _lebensEnergie;
	private Inventar _inventar;
	private boolean _aktiv; // zustand des Spielers (Bein gestellt - disabled,
							// sonst enabled).

	/**
	 * Erzeuge einen neuen Spieler, der ein Inventar, Lebensenergie und einen
	 * Zustand (aktiv oder inaktiv) hält.
	 */
	public Spieler(String name) {
		_aktiv = true;
		_name = name;
	}

	public Spieler(String name, int lebensEnergie, Inventar inventar) {
		_name = name;
		_lebensEnergie = lebensEnergie;
		_inventar = inventar;
		_aktiv = true;
	}

	public int getLebensEnergie() {
		return _lebensEnergie;
	}

	public void setLebensEnergie(int lebensEnergie) {
		_lebensEnergie = lebensEnergie;
	}

	public Inventar getInventar() {
		return _inventar;
	}

	public void setInventar(Inventar inventar) {
		_inventar = inventar;
	}

	public void setAktiv(boolean aktiv) {
		_aktiv = aktiv;
	}

	public boolean getAktiv() {
		return _aktiv;
	}

}
