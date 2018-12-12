package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Queue;

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
	private HashMap<Integer, Kugel> kugeln;
	private Floodfill floodfill;
	private Voronoi v;
	
	
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
		kugeln = new HashMap<>();
		if(!verteileKugeln()) {
			throw new Exceptions.KugelException("Die Kugeln konnten nicht"
					+ " akkurat verteilt werden.");
		}
		this.floodfill = new Floodfill(this);
	}
	
	public double getHoehe() {
		return hoehe;
	}

	public double getBreite() {
		return breite;
	}

	public Map<Integer, Kugel> getKugeln() {
		return kugeln;
	}
	
	public int getN() {
		return n;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 * @return the floodfill
	 */
	public Floodfill getFloodfill() {
		return floodfill;
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
		int aktuellIndex = 1;
		
		 while(kugeln.size() < this.n && versuche > 0) {
			 
			 double x = verteilung.nextDouble() * this.breite;
			 double y = verteilung.nextDouble() * this.hoehe;
			 
			 if(istImRaum(x, y, this.radius)) {
				kugeln.put(aktuellIndex, new Kugel(x, y, this.radius, aktuellIndex));
				aktuellIndex++;
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
	
	
}
