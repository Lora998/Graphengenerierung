package Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasse, um Kanten einer Kugel zu ermitteln
 * @author l.hofer
 *
 */

public class Knoten {
	private Position position;
	private Set<Kugel> zugehoerigeKugeln;
	
	public Knoten(Position p, Set<Kugel> k) {
		position = p;
		zugehoerigeKugeln = k;
	}
	
	public Knoten(Position p, Kugel k) {
		position = p;
		zugehoerigeKugeln = new HashSet<>();
		zugehoerigeKugeln.add(k);
	}
	
	public Knoten(Position p, Kugel a, Kugel b) {
		position = p;
		zugehoerigeKugeln = new HashSet<>();
		zugehoerigeKugeln.add(a);
		zugehoerigeKugeln.add(b);
	}
}
