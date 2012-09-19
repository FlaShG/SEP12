package de.uni_hamburg.informatik.sep.zuul;

import java.util.List;
import java.util.Random;

public final class FancyFunction
{
	public static <T> T getRandomEntry(List<T> entries)
	{
		int size = entries.size();
		if(size == 0)
			return null;

		int nextInt = new Random().nextInt(size);
		return entries.get(nextInt);
	}
}
