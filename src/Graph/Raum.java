package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author l.hofer
 *
 */
public class Raum {
	
	private double hoehe;
	private double breite;
	private int n; 		// Anzahl der Kugeln
	private double radius;
	private Set<Kugel> kugeln;
	
	/**
	 * 
	 * @param n
	 * @param hoehe
	 * @param breite
	 * @param radius
	 * @throws Exception
	 */
	public Raum(int n, double hoehe, double breite, double radius) throws Exception{
		
		this.n = n;
		this.radius = radius;
		this.breite = breite;
		this.hoehe = hoehe;
		
		kugeln = new HashSet<>();
		if(!verteileKugeln()) {
			throw new Exceptions.KugelException("Die Kugeln konnten nicht"
					+ " akkurat verteilt werden.");
		}
		findeGraphen();
	}
	
	public double getHoehe() {
		return hoehe;
	}

	public double getBreite() {
		return breite;
	}

	public Set<Kugel> getKugeln() {
		return kugeln;
	}
	
	public int getN() {
		return n;
	}

	public double getRadius() {
		return radius;
	}


	/**
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return Wahrheitswert, ob die Kugel innerhalb der Grenzen liegen
	 */
	private boolean istImRaum(double x, double y, double radius) {
		if(x-radius < 0 || y-radius < 0 || x+radius > this.breite || y+radius > hoehe) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return Wahrheitswert, ob das Verteilen erfolgreich war
	 */
	private boolean verteileKugeln() {
		
		int versuche = 1000;
		Random verteilung  = new Random(); 		// verwendet automatisch die aktuelle Systemzeit als Seed

		
		 while(kugeln.size() < this.n && versuche > 0) {
			 
			 double x = verteilung.nextDouble() * this.breite;
			 double y = verteilung.nextDouble() * this.hoehe;
			 
			 if(istImRaum(x, y, this.radius)) {
				kugeln.add(new Kugel(x, y, this.radius));
			} else {
				versuche--;
				System.out.println("x = "+x+"\t y = "+y);
			}
		 }
		 
		if(kugeln.size() == this.n) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * berechnet den Graphen durch die Kugeln
	 */
	private void findeGraphen() {
		Knoten[][]  knoten = new Knoten[(int)(this.breite*10)][(int) (this.hoehe*10)];
		for(Kugel k: kugeln) {
			
			/**
			 * nutzt Bresenham-Algorithmus um auf dem Array die Außenlgrenzen der Kugeln zu makieren
			 */
			
			int x0 = (int)k.getPosition().getX()*10, y0 = (int)k.getPosition().getY()*10, radius = (int) k.getRadius()*10;
			int f = 1-radius, ddF_x = 0, ddf_y = -2*radius, x = 0, y = radius;
			
			knoten[x0][y0+radius] = new Knoten(new Position(x0, y0+radius), k);
			knoten[x0][y0-radius] = new Knoten(new Position(x0, y0-radius), k);
			knoten[x0+radius][y0] = new Knoten(new Position(x0+radius, y0), k);
			knoten[x0-radius][y0] = new Knoten(new Position(x0-radius, y0), k);
			
			while(x < y) {
				if(f >= 0) {
					y--;
					ddf_y +=2;
					f+= ddf_y;
				}
				x++;
				ddF_x += 2;
				f += ddF_x+1;
				
				knoten[x0+x][y0+y] = new Knoten(new Position(x0+x, y0+y), k);
				knoten[x0-x][y0+y] = new Knoten(new Position(x0-x, y0+y), k);
				knoten[x0+x][y0-y] = new Knoten(new Position(x0+x, y0-y), k);
				knoten[x0-x][y0-y] = new Knoten(new Position(x0-x, y0-y), k);
				knoten[x0+y][y0+x] = new Knoten(new Position(x0+y, y0+x), k);
				knoten[x0-y][y0+x] = new Knoten(new Position(x0-y, y0+x), k);
				knoten[x0+y][y0-x] = new Knoten(new Position(x0+y, y0-x), k);
				knoten[x0-y][y0-x] = new Knoten(new Position(x0-y, y0-x), k);
			}
			
		}
		
		
		
		
	}
	
	private void fuelleInnereKugel(Kugel k, Knoten[][] knoten) {
		
	}
}
