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
		istTeilDerKugel = k.istTeilderKugel();
	}
	
	public void addKugel(Kugel k) {
		this.zugehoerigeKugeln.add(k);
	}
	
	public void addKugeln(Set<Kugel> set) {
		for(Kugel k : set) {
			this.addKugel(k);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (istTeilDerKugel ? 1231 : 1237);
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((zugehoerigeKugeln == null) ? 0 : zugehoerigeKugeln.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Knoten other = (Knoten) obj;
		if (istTeilDerKugel != other.istTeilDerKugel)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (zugehoerigeKugeln == null) {
			if (other.zugehoerigeKugeln != null)
				return false;
		} else if (!zugehoerigeKugeln.equals(other.zugehoerigeKugeln))
			return false;
		return true;
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
