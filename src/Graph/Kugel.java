package Graph;


import java.util.Set;

/**
 * Klasse um Kugelobjekte darzustellen
 * @author l.hofer
 *
 */
public class Kugel {
	private double radius;
	private Position position;
	private Set<Kante> kanten;
	
	public Kugel(double x, double y, double radius) {
		this.position = new Position(x, y);
		this.radius = radius;
	}
	
	/**
	 * Copy-Konstruktor
	 * @param c
	 */
	public Kugel(Kugel c) {
		this.position = new Position(c.getPosition());
		this.radius = c.getRadius();
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public double getRadius() {
		return this.radius;
	}
	 /**
	  * berechnet, ob sich zwei Kugeln schneiden, bzw. beruehren
	  * @param a 1. Kugel
	  * @param b 2. Kugel
	  * @return Wahrheitswert, ob a und b Nachbarkugel sind
	  */
	public static boolean sindNachbarn(Kugel a, Kugel b) {
		double abstand = Position.getDistance(a.getPosition(), b.getPosition());
		if((a.getRadius() + b.getRadius()) >= abstand) {
			return true;
		}
		return false;
	}
	
}
