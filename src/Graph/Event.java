package Graph;

/**
 * nur fuer Voronoi
 * @author l.hofer
 *
 */

public class Event implements Comparable<Event>{
	/**
	 * etweder Kugel oder Kante
	 */
	public static int SITE_EVENT = 0;
	public static int CIRCLE_EVENT = 1;
	public Position p;
	public int typ;
	public Parabel bogen;
	
	public Event(Position p, int typ) {
		
		this.p = p;
		this.typ = typ;
		this.bogen = null;
	}
	@Override
	public int compareTo(Event arg0) {
		return this.p.compareTo(arg0.p);
	}
	
}