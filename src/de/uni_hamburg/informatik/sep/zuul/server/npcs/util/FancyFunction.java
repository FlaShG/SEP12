package de.uni_hamburg.informatik.sep.zuul.server.npcs.util;

import java.util.List;
import java.util.Random;

public final class FancyFunction {
	/**
	 * Gib ein zufälliges Element aus einer Liste.
	 * 
	 * @param entries
	 *            Die Liste
	 * @return ein zufälliges Element aus der Liste
	 */
	public static <T> T getRandomEntry(List<T> entries) {
		int size = entries.size();
		if (size == 0)
			return null;

		int nextInt = new Random().nextInt(size);
		return entries.get(nextInt);
	}
}
