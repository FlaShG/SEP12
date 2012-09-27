package de.uni_hamburg.informatik.sep.zuul.server.raum.xml;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;

public class RaumStrukturParserTest
{

	@Test
	public void testValidiere()
	{
		assertTrue(RaumStrukturParser.validiere("./level/testStruktur."
				+ FileChooser.ZUUL_ENDUNG));
		assertFalse(RaumStrukturParser
				.validiere("./xml_dateien/RaumSammlung.xml"));
	}
}
