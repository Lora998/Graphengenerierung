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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
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
