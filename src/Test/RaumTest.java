/**
 * 
 */
package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exceptions.KugelException;
import Graph.Raum;

/**
 * @author l.hofer
 *
 */
class RaumTest {

	/**
	 * Test method for {@link Graph.Raum#Raum(int, int, int, double)}.
	 * @throws Exception 
	 */
	@Test
	void testRaum() throws Exception {
		
		Raum raum1 = new Raum(20, 100, 100, 1.);
		Raum raum2 = new Raum(20, 100, 100, 1.);
		
		
		assertEquals(20, raum1.getKugeln().size());
		assertEquals(100, raum1.getBreite());
		assertEquals(100, raum1.getHoehe());;
		
	}
	
	
	/**
	 * Test method for {@link Graph.Raum#Raum(int, int, int, double)}.
	 * @throws Exception 
	 */
	@Test
	void tetsteNegativenRaum() {
		Raum raum4 = null;
		try {
			raum4 = new Raum(-2, -100, -100, -1);
		}
		catch(KugelException k) {
			//assertEquals(0, raum4.getN());
		}

	}
	
	/**
	 * Test method for {@link Graph.Raum#Raum(int, int, int, double)}.
	 * @throws Exception 
	 */
	@Test
	void testeOKnoten() {
		Raum raum3 = new Raum(1, 100, 100, 1.);
		assertEquals(0, raum3.getFloodfill().getKnotenAnzahl());
	}

}
