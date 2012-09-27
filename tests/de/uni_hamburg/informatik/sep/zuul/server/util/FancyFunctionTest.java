package de.uni_hamburg.informatik.sep.zuul.server.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FancyFunctionTest
{

	private List<Object> _linkedListe;
	private List<Object> _arrayListe;
	private Object o;

	@Before
	public void setUp()
	{
		_linkedListe = new LinkedList<Object>();
		_arrayListe = new ArrayList<Object>();
		o = new Object();
		_linkedListe.add(o);
		_arrayListe.add(o);
	}

	@Test
	public void testGetRandomEntry()
	{
		Object entry = FancyFunction.getRandomEntry(_linkedListe);

		assertEquals(o, entry);

		entry = FancyFunction.getRandomEntry(_arrayListe);

		assertEquals(o, entry);

		_linkedListe.remove(0); //leereListe
		entry = FancyFunction.getRandomEntry(_linkedListe);
		assertNull(entry);

		String s = "bla";
		_arrayListe.add(s);

		assertNotNull(FancyFunction.getRandomEntry(_arrayListe));
	}

}
