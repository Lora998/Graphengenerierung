package GraphischeDarstellung;

import java.awt.Canvas;

import Graph.Raum;
public class Zeichenwand extends Canvas{
	
	private Raum raum;
	
	public Zeichenwand(Raum r) {
		super();
		this.raum = r;
		setSize(raum.getBreite(), raum.getHoehe());
	}
	
	
}
