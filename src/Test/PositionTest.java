/**
 * 
 */
package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Graph.Position;

/**
 * @author l.hofer
 *
 */
class PositionTest {

	/**
	 * Test method for {@link Graph.Position#Position(Graph.Position)}.
	 */
	@Test
	void testPositionPosition() {
		
		Position original = new Position(1, 2);
		Position kopie = new Position(original);
		
		assertEquals(1, kopie.getX());
		assertEquals(2, kopie.getY());
	}

	/**
	 * Test method for {@link Graph.Position#Position()}.
	 */
	@Test
	void testPosition() {
		
		Position ohneZuweisung = new Position();
		
		assertEquals(0, ohneZuweisung.getX());
		assertEquals(0, ohneZuweisung.getY());
	}

	/**
	 * Test method for {@link Graph.Position#getDistance(Graph.Position, Graph.Position)}.
	 */
	@Test
	void testGetDistance() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(3,4);
		Position p3 = new Position(-3, -4);
		
		assertEquals(5, Graph.Position.getDistance(p1, p2));
		assertEquals(0, Graph.Position.getDistance(p1, p1));
		assertEquals(5, Graph.Position.getDistance(p1, p3));
	}

}
