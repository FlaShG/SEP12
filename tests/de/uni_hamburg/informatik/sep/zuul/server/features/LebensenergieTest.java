package de.uni_hamburg.informatik.sep.zuul.server.features;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

public class LebensenergieTest {
	
	Lebensenergie _leben;
	ServerKontext _kon;
	
	@Before
	public void setUp()
	{
		_leben = new Lebensenergie();
		Raum startRaum = mock(Raum.class);
		_kon = new ServerKontext(startRaum);
	}

	@Test
	public void testRaumGeaendert() {
		Inventar inv = mock(Inventar.class);
		Spieler spieler = new Spieler("name", 8, inv);
		Raum startRaum = mock(Raum.class);
		assertEquals(8, spieler.getLebensEnergie());
		
		_leben.raumGeaendert(_kon, spieler, startRaum, null);
		
		assertEquals(7, spieler.getLebensEnergie());
		
		spieler = new Spieler("name", Integer.MIN_VALUE, inv);
		
		_leben.raumGeaendert(_kon, spieler, startRaum, null);
		assertEquals(Integer.MAX_VALUE, spieler.getLebensEnergie());
		
		spieler = new Spieler("name", Integer.MAX_VALUE, inv);
		_leben.raumGeaendert(_kon, spieler, startRaum, null);
		assertEquals(Integer.MAX_VALUE -1 , spieler.getLebensEnergie());
	}

	@Test
	public void testBefehlAusgefuehrt() {
		Spieler spieler = new Spieler("name");
		Befehl bef = mock(Befehl.class);
		_leben.befehlAusgefuehrt(_kon, spieler, bef, true);
		
		assertEquals(TextVerwalter.NIEDERLAGETEXT+"\n", _kon.getNachrichtFuer(spieler));
	
		Inventar inv = mock(Inventar.class);
		spieler = new Spieler("name", 1, inv);
		
		_leben.befehlAusgefuehrt(_kon, spieler, bef, true);
		assertEquals(TextVerwalter.RAUMWECHSELTEXT + spieler.getLebensEnergie() +
				"\n", _kon.getNachrichtFuer(spieler));
	}

}
