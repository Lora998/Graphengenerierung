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
	boolean istTeilDerKugel;
	
	public Knoten(Position p, Set<Kugel> k, boolean i) {
		position = p;
		zugehoerigeKugeln = k;
		istTeilDerKugel = i;
	}
	
	public Knoten(Position p, Kugel k, boolean i) {
		position = p;
		zugehoerigeKugeln = new HashSet<>();
		zugehoerigeKugeln.add(k);
		istTeilDerKugel = i;
	}
	
	public Knoten(Position p, Kugel a, Kugel b, boolean i) {
		position = p;
		zugehoerigeKugeln = new HashSet<>();
		zugehoerigeKugeln.add(a);
		zugehoerigeKugeln.add(b);
		istTeilDerKugel = i;
	}
	
	public Knoten(Knoten k) {
		this.position = new Position(k.getPosition());
		zugehoerigeKugeln = new HashSet<>(k.getZugehoerigeKugeln());
		istTeilDerKugel = k.istTeilDerKugel();
	}
	
	public boolean istTeilderKugel() {
		return this.istTeilDerKugel;
	}

	public Position getPosition() {
		return position;
	}

	public Set<Kugel> getZugehoerigeKugeln() {
		return zugehoerigeKugeln;
	}
	
	
}
