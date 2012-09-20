package de.uni_hamburg.informatik.sep.zuul.multiplayer.server;

import de.uni_hamburg.informatik.sep.zuul.spiel.Inventar;

public class Spieler {
	private int _lebensEnergie;
	private Inventar _inventar;
	private boolean _aktiv; // zustand des Spielers (Bein gestellt - disabled,
							// sonst enabled).

	/**
	 * Erzeuge einen neuen Spieler, der ein Inventar, Lebensenergie und einen
	 * Zustand (aktiv oder inaktiv) hält.
	 */
	public Spieler() {
		_aktiv = true;
	}

	public Spieler(int lebensEnergie, Inventar inventar) {
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
