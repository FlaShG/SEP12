package de.uni_hamburg.informatik.sep.zuul.server.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;

public class PathFinderTest {
	
	Raum _raum1 = new Raum("1", "beschreibung");
	Raum _raum2 = new Raum("2", "beschreibung");
	Raum _raum3 = new Raum("3", "nicht gebracuht");
	Raum _raum4 = new Raum("4", "nicht gebraucht");
	ArrayList<Raum> _raumliste = new ArrayList<>();
	
	PathFinder _finder;
	
	@Before
	public void setUp()
	{
		_raum1.verbindeZweiRaeume("nord", _raum2, "sued");
		_raum2.verbindeZweiRaeume("nord", _raum3, "sued");
		_raum3.verbindeZweiRaeume("nord", _raum4, "sued");
		_raum1.setRaumart(RaumArt.Start);
		_raum2.setRaumart(RaumArt.Normal);
		_raum3.setRaumart(RaumArt.Normal);
		_raum4.setRaumart(RaumArt.Ende);
		_raumliste.add(0, _raum1);
		_raumliste.add(1, _raum2);
		_raumliste.add(2, _raum3);
		_raumliste.add(3, _raum4);
		
		_finder = new PathFinder() {
			
			//gebraucht für findpath
			@Override
			protected boolean isZielRaum(Raum raum) {
				if(raum.getRaumart() == RaumArt.Ende)
					return true;
				
				return false;
			}
		};
	
	}
	
	
	@Test
	public void testIsZielRaum() {
		assertTrue(_finder.isZielRaum(_raum4));
		assertFalse(_finder.isZielRaum(_raum1));
		assertFalse(_finder.isZielRaum(_raum2));
		assertFalse(_finder.isZielRaum(_raum3));
	
	}

	@Test
	public void testFindPath() {
		assertEquals(_raumliste, _finder.findPath(_raum1));
		
	}

	@Test
	public void testGetRichtung() {
		assertEquals("nord", PathFinder.getRichtung(_raumliste));
	}

}
