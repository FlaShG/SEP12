package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Befehlszeile
{
	private List<String> _geparsteZeile;
	private String _zeile;

	public Befehlszeile(String zeile)
	{
		_zeile = zeile;
		_geparsteZeile = Befehlszeile.split(zeile);
	}

	private Befehlszeile(List<String> geparsteZeile)
	{
		_geparsteZeile = geparsteZeile;
		_zeile = join(geparsteZeile);
	}

	public static List<String> split(String zeile)
	{
		String[] input = zeile.split(" ");
		return Arrays.asList(input);
	}

	public static String join(List<String> zeile)
	{
		StringBuilder builder = new StringBuilder();
		for(String string : zeile)
		{
			builder.append(string);
			builder.append(" ");
		}
		if(builder.length() > 0)
			builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * @return the geparsteZeile
	 */
	public List<String> getGeparsteZeile()
	{
		return _geparsteZeile;
	}

	/**
	 * @return the zeile
	 */
	public String getZeile()
	{
		return _zeile;
	}

	public boolean beginntMit(String str)
	{
		return _zeile.startsWith(str);
	}
	
}
