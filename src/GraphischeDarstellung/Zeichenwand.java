package GraphischeDarstellung;



import Graph.Raum;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
public class Zeichenwand extends Canvas{
	
	private Raum raum;
	
	public Zeichenwand(Raum r) {
		super(r.getBreite(), r.getHoehe());
		this.raum = r;
		this.getGraphicsContext2D().setFill(Color.ALICEBLUE);
		this.getGraphicsContext2D().strokeOval(8, 8, 2, 80);
	}
	
	
}
