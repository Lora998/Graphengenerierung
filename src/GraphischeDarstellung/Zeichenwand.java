package GraphischeDarstellung;



import Graph.Kante;
import Graph.Kugel;
import Graph.Position;
import Graph.Raum;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Zeichenwand extends Canvas{
	
	private Raum raum;
	private GraphicsContext gc;
	
	public Zeichenwand(Raum r) {
		super(r.getBreite(), r.getHoehe());
		this.raum = r;
		this.gc = this.getGraphicsContext2D();
		zeichneKugeln();
		zeichneGraphen();
	
	}
	
	private void zeichneKugeln() {
		this.gc.setStroke(Color.BLACK);
		for(Kugel kugel: raum.getKugeln().values()) {
			double radius = kugel.getRadius();
			this.gc.strokeOval(kugel.getPosition().getX()-0.5*radius, 
					kugel.getPosition().getY()-0.5*radius,
					radius, radius);
		}
	}
	
	private void zeichneGraphen() {
		this.gc.setStroke(Color.RED);
		for(Position p : raum.getFloodfill().getGraphenPunkte()) {
			this.gc.strokeOval((double)(p.getX() / 10.), (double)(p.getY() / 10.), 0.1, 0.1);
			//System.out.println(p.getX()+" / "+p.getY());
		}
		/*for(Kante k : raum.getVoronoi().getKanten()) {
			System.out.println(k.start.getX()+" / "+ k.ende.getX());
			this.gc.strokeLine(k.start.getX(), k.start.getY(),
					k.ende.getX(), k.ende.getY());
		}*/
		
	}
	
	private void zeichneDaten() {
		this.gc.setStroke(Color.BLUE);
		this.gc.strokeText("Knoten Anzahl n = "+this.raum.getFloodfill().getKnotenAnzahl(), 0, 12);
	}
	
	public void neuerRaum(Raum r) {
		this.raum = r;
		this.setWidth(raum.getBreite());
		this.setHeight(raum.getHoehe());
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		zeichneKugeln();
		zeichneGraphen();
		zeichneDaten();
	}
	
}
