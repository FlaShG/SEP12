package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class ParserTest
{
	Parser parser = new Parser();

	@Test
	public void testLiefereBefehl()
	{
//		//Teste gehe norden
//		BefehlGo gehe = new BefehlGo();
//		String[] parN = { TextVerwalter.RICHTUNG_NORDEN };
//
//		gehe.setParameter(parN);
//
//		Befehl bef = parser.liefereBefehl("gehe nord");
//
//		assertEquals(gehe.getBefehlsname(), bef.getBefehlsname());
//		assertArrayEquals(gehe.getParameters(), bef.getParameters());
//
//		//Teste gehe ost
//		gehe = new BefehlGo();
//		String[] parO = { TextVerwalter.RICHTUNG_OSTEN };
//
//		gehe.setParameter(parO);
//		bef = parser.liefereBefehl("gehe ost");
//
//		assertEquals(gehe.getBefehlsname(), bef.getBefehlsname());
//		assertArrayEquals(gehe.getParameters(), bef.getParameters());
//
//		//Teste gehe west
//		gehe = new BefehlGo();
//		String[] parW = { TextVerwalter.RICHTUNG_WESTEN };
//
//		gehe.setParameter(parW);
//		bef = parser.liefereBefehl("gehe west");
//
//		assertEquals(gehe.getBefehlsname(), bef.getBefehlsname());
//		assertArrayEquals(gehe.getParameters(), bef.getParameters());
//
//		//Teste gehe süd
//		gehe = new BefehlGo();
//		String[] parS = { TextVerwalter.RICHTUNG_SUEDEN };
//
//		gehe.setParameter(parS);
//		bef = parser.liefereBefehl("gehe süd");
//
//		assertEquals(gehe.getBefehlsname(), bef.getBefehlsname());
//		assertArrayEquals(gehe.getParameters(), bef.getParameters());
//
//		//Teste beenden
//		Befehl quit = new BefehlQuit();
//		String[] q = { TextVerwalter.BEFEHL_BEENDEN };
//
//		bef = parser.liefereBefehl("beenden");
//
//		assertEquals(quit.getBefehlsname(), bef.getBefehlsname());
//		assertNull(quit.getParameters());
//		
//		
//		//Teste hilfe
//		Befehl help = new BefehlHelp();
//		String[] h = { TextVerwalter.BEFEHL_HILFE };
//
//		bef = parser.liefereBefehl("hilfe");
//
//		assertEquals(help.getBefehlsname(), bef.getBefehlsname());
//		assertNull(help.getParameters());

	}

}
