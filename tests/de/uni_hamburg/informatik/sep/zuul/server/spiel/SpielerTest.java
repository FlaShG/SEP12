package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;

public class SpielerTest {
	Spieler _tester;
	Inventar inv = mock(Inventar.class);
	
	@Before
	public void setUp()
	{
		//standardspieler hat mehr als nur namen.
		_tester = new Spieler("testname", 8, inv);
	}
	
	@Test
	public void testSpielerString() {
		_tester = new Spieler("testname");
		
		assertEquals("testname", _tester.getName());
	}

	@Test
	public void testSpielerStringIntInventar() {
		_tester = new Spieler("testname", 8, inv);
		
		assertEquals("testname", _tester.getName());
		assertEquals(8, _tester.getLebensEnergie());
		
//		when(_tester.getInventar()).thenReturn(inv);		
		assertEquals(inv, _tester.getInventar());
	}
	
	@Test
	public void testGetLebensEnergie() {
		assertEquals(8, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, _tester.getLebensEnergie());
	}

	@Test
	public void testSetLebensEnergie() {
	}

	@Test
	public void testGetInventar() {
	}

	@Test
	public void testSetInventar() {
	}

	@Test
	public void testSetAktiv() {
	}

	@Test
	public void testGetAktiv() {
	}
	@Test
	public void testGetName() {
	}

	@Test
	public void testDie() {
	}

	@Test
	public void testLebendig() {
	}

	@Test
	public void testGewinnt() {
	}

	@Test
	public void testHatGewonnen() {
	}

}
