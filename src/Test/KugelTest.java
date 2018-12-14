/**
 * 
 */
package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Graph.Kugel;

/**
 * @author l.hofer
 *
 */
class KugelTest {

	/**
	 * Test method for {@link Graph.Kugel#Kugel(Graph.Kugel)}.
	 */
	@Test
	void testKugelKugel() {
		
		Kugel original = new Kugel(2, 3, 1., 0);
		Kugel kopie = new Kugel(original);
		
		assertEquals(2, kopie.getPosition().getX());
		assertEquals(3, kopie.getPosition().getY());
		assertEquals(1., kopie.getRadius());
	}

	/**
	 * Test method for {@link Graph.Kugel#sindNachbarn(Graph.Kugel, Graph.Kugel)}.
	 */
	@Test
	void testSindNachbarn() {
		Kugel k1 = new Kugel(4, 4, 1., 0);
		Kugel k2 = new Kugel(4, 4, 3., 1); 	// k1 liegt in k2
		Kugel k3 = new Kugel(4, 6, 2., 2);		// k2 und k3 schneiden sich
		Kugel k4 = new Kugel(6, 4, 1., 3);		// k1 und k4 beruehren sich in einem Punkt
		Kugel k5 = new Kugel(20, 20, 1, 4);	// k1 und k5 schneiden sich nirgendwo
		
		assertTrue(Graph.Kugel.sindNachbarn(k1, k2));
		assertTrue(Graph.Kugel.sindNachbarn(k2, k3));
		//assertTrue(Graph.Kugel.sindNachbarn(k1, k4));
		assertFalse(Graph.Kugel.sindNachbarn(k1, k5));
	}

}
