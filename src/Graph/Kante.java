package Graph;

/**
 * nur fuer Voronoi
 * @author l.hofer
 *
 */


public class Kante{
	
	public Position start;
	public Position ende;
	Position linkeSeite;
	Position rechteSeite;
	Position richtung;
	Kante nachbar;
	double steigung;
	double yPosition;
	
	public Kante(Position start, Position links, Position rechts){
		
		this.start = start;
		this.linkeSeite = links;
		this.rechteSeite = rechts;
		this.richtung = new Position(rechts.getY()-links.getY(), -(rechts.getX()-links.getX()));
		this.ende = null;
		this.steigung = (rechts.getX()-links.getX()) / (links.getY()-rechts.getY());
		Position mitte = new Position((rechts.getX()+links.getX()) / 2, (rechts.getY()+links.getY()) /2);
		yPosition = mitte.getY() - this.steigung*mitte.getX();
	
	}
	
}