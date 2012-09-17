package de.uni_hamburg.informatik.sep.zuul.spiel;

import static org.junit.Assert.*;

import org.junit.Test;

public class InventarTest
{

	@Test
	public void testInventar()
	{
		Inventar inv = new Inventar();
		assertNotNull(inv.getInhaltsliste());
	}

	@Test
	public void testFuegeItemHinzu()
	{
		Inventar inv = new Inventar();
		Item it = Item.Kuchen;
		
		inv.fuegeItemHinzu(Item.Keins);
		assertFalse(inv.isGefuellt());
		
		inv.fuegeItemHinzu(it);
		
		assertTrue(inv.isGefuellt());
		assertEquals(it, inv.nehmeLetztesItem());
	}

	@Test
	public void testNehmeLetztesItem()
	{
		Inventar inv = new Inventar();
		Item it = Item.Kuchen;
		
		inv.fuegeItemHinzu(it);
		
		assertEquals(it, inv.nehmeLetztesItem());
		assertFalse(inv.isGefuellt());
		
		assertEquals(Item.Keins, inv.nehmeLetztesItem());
	}

	@Test
	public void testIsGefuellt()
	{
		Inventar inv  = new Inventar();
		assertFalse(inv.isGefuellt());
		
		inv.fuegeItemHinzu(Item.Kuchen);
		assertTrue(inv.isGefuellt());
	}

}
