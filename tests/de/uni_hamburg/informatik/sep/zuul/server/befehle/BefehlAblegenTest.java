/**
 * 
 */
package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * @author 0klein
 *
 */
public class BefehlAblegenTest
{
	
	BefehlAblegen ablegen = new BefehlAblegen();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
    Inventar inventar = new Inventar();
    Inventar inventar2 = new Inventar();
    Befehlszeile befehlszeile= new Befehlszeile("ablegen");
    
  
	@Before
	public void setUp() throws Exception
	{
		   kontext.setAktuellenRaumZu(spieler, raum);
		   inventar.fuegeItemHinzu(Item.UKuchen);
		   spieler.setInventar(inventar);
		   spielerleeresInventar.setInventar(inventar2);
		     
	}

	
	
	/**
	 * Test method for {@link de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlAblegen#ausfuehren(de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext, de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler, de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile)}.
	 */
	@Test
	public void testAusfuehren()
	{
		assertTrue(ablegen.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(Item.UKuchen, raum.getNaechstesItem());
		assertEquals(TextVerwalter.ABLEGEN_TEXT, kontext.getNachrichtFuer(spieler).substring(0, 26));
	}
	
	
	

	/**
	 * Test method for {@link de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlAblegen#getBefehlsnamen()}.
	 */
	@Test
	public void testGetBefehlsnamen()
	{
		
		assertEquals( TextVerwalter.BEFEHL_ABLEGEN, ablegen.getBefehlsnamen()[0]);
	}

	
	
	/**
	 * Test method for {@link de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlAblegen#vorbedingungErfuellt(de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext, de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler, de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile)}.
	 */
	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(ablegen.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		assertFalse(ablegen.vorbedingungErfuellt(kontext, spielerleeresInventar, befehlszeile));
	}

	
	
	/**
	 * Test method for {@link de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlAblegen#gibFehlerAus(de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext, de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler, de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile)}.
	 */
	@Test
	public void testGibFehlerAus()
	{
		ablegen.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.NICHTS_ZUM_ABLEGEN, kontext.getNachrichtFuer(spieler).substring(0, 31));
	}

	
	
	/**
	 * Test method for {@link de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlAblegen#getHilfe()}.
	 */
	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_ABLEGEN, ablegen.getHilfe());
	}

}
