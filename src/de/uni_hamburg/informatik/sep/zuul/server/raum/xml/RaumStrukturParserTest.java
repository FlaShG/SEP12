package de.uni_hamburg.informatik.sep.zuul.server.raum.xml;

import static org.junit.Assert.*;

import org.junit.Test;

public class RaumStrukturParserTest
{

	@Test
	public void testValidiere()
	{
		assertTrue(RaumStrukturParser
				.validiere("./xml_dateien/testStruktur.xml"));
		assertFalse(RaumStrukturParser
				.validiere("./xml_dateien/RaumSammlung.xml"));
	}
}
