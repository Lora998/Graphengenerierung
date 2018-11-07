package GraphischeDarstellung;



import Graph.Kugel;
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
	
	}
	
	private void zeichneKugeln() {
		for(Kugel kugel: raum.getKugeln()) {
			double radius = kugel.getRadius();
			this.gc.strokeOval(kugel.getPosition().getX()-radius, 
					kugel.getPosition().getY()-radius,
					radius, radius);
		}
	}
	
	public void neuerRaum(Raum r) {
		this.raum = r;
		this.setWidth(raum.getBreite());
		this.setHeight(raum.getHoehe());
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		zeichneKugeln();
	}
	
}
