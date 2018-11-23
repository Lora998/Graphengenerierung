package Graph;


import java.util.HashSet;
import java.util.Set;

/**
 * Klasse um Kugelobjekte darzustellen
 * @author l.hofer
 *
 */
public class Kugel {
	private double radius;
	private Position position;
	private int index;
	private Set<Integer> nachbarn; 
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param index
	 */
	public Kugel(double x, double y, double radius, int index) {
		this.position = new Position(x, y);
		this.radius = radius;
		this.nachbarn = new HashSet<>();
		this.index = index;
	}
	
	/**
	 * Copy-Konstruktor
	 * @param c
	 */
	public Kugel(Kugel c) {
		this.position = new Position(c.getPosition());
		this.radius = c.getRadius();
		this.nachbarn = new HashSet<>(c.getNachbarn());
		this.index = c.getIndex();
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the nachbarn
	 */
	public Set<Integer> getNachbarn() {
		return nachbarn;
	}

	public void addNachbar(Integer i) {
		this.nachbarn.add(i);
	}
	
	public boolean istNachbarVon(Integer k) {
		return this.nachbarn.contains(k);
	}
	
	/*	public void setAussenrand(Set<Position> a) {
		this.aussenrand = a;
	}
	
	public Set<Position> getAussenrand(){
		return this.aussenrand;
	}*/
	 /**
	  * berechnet, ob sich zwei Kugeln schneiden, bzw. beruehren
	  * @param a 1. Kugel
	  * @param b 2. Kugel
	  * @return Wahrheitswert, ob a und b Nachbarkugel sind
	  */
	public static boolean sindNachbarn(Kugel a, Kugel b) {
		double abstand = Position.getDistance(a.getPosition(), b.getPosition());
		if((a.getRadius()*0.5 + b.getRadius()*0.5) >= abstand) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.index; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Kugel other = (Kugel) obj;
		return other.getIndex() == this.index;
	}
	
	
	
}
