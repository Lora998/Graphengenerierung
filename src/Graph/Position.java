package Graph;
/**
 * Klasse, um eine Poition im zwei-dimensionalen darzustellen
 * @author l.hofer
 *
 */

public class Position {
	private double x;
	private double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Copy-Konstruktor
	 * @param c
	 */
	public Position(Position c) {
		this.x = c.getX();
		this.y = c.getY();
	}
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	/**
	 * berechnet den Abstand zwischen zwei Punkten
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDistance(Position a, Position b) {
		return Math.sqrt(((a.getX()-b.getX()) * (a.getX()-b.getX()) + (a.getY()-b.getY()) * (a.getY()-b.getY())));
	}
}
