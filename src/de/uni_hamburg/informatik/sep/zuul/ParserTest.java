package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;

public class ParserTest
{
	Parser parser;
	InputStream inputStream;

	@Before
	public void setUp() throws Exception
	{
		inputStream = new InputStream()
		{
			private int position = 0;
			public String text = "abc def";

			@Override
			public int read() throws IOException
			{
				try
				{
					return text.codePointAt(position++);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					return -1;
				}
			}
		};
		
		
		
		parser = new Parser(inputStream, System.out);
	}

	@Test
	public void testLiefereBefehl()
	{
		Befehl befehl = parser.liefereBefehl();
		assertNull(befehl.getBefehlsname());
	}

	@Test
	public void testParseEingabezeile()
	{
		Befehl befehl = parser.parseEingabezeile("go b");

		assertEquals("go", befehl.getBefehlsname());
	}

	@Test
	public void testLeseEin()
	{
		assertEquals("abc def", parser.leseEin());
	}

}
