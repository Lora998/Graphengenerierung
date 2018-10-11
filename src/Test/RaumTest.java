/**
 * 
 */
package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		
		assertEquals(20, raum1.getKugeln().size());
		assertEquals(100, raum1.getBreite());
		assertEquals(100, raum1.getHoehe());
	}

}
