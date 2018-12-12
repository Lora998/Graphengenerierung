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
	private List<Position> graphenPunkte;
	
	
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
		this.graphenPunkte = new ArrayList<>();
		kugeln = new HashMap<>();
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
	 * @return the graphenPunkte
	 */
	public List<Position> getGraphenPunkte() {
		return graphenPunkte;
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
	
	/**
	 * berechnet den Graphen durch die Kugeln
	 */
	private void findeGraphen() {
		int[][]  knoten = new int[(int)(this.breite*10)][(int) (this.hoehe*10)];
		
		Queue<Position> remaining = new LinkedList<>();
		
		for(Kugel k : kugeln.values()) {
			
			
			int x = (int)k.getPosition().getX()*10;
			int y = (int)k.getPosition().getY()*10;
			knoten[x][y] = k.getIndex();
			remaining.offer(new Position(x,y));
			
			for(Kugel l: kugeln.values()) {
				if(k.equals(l)) {
					continue;
				}
				if(Kugel.sindNachbarn(k, l)) {
					k.addNachbar(l.getIndex());
				}
			}
		}
		
		while(!remaining.isEmpty()) {
			Position tmp = remaining.poll();
			int x = (int)tmp.getX();
			int y = (int)tmp.getY();
			int index = knoten[x][y];
			
			int sin[] = {0, 1, 1, 1, 0, -1 , -1, 1};
			int cos[] = {1, 1, 0, -1, -1, -1, 0, 1};
			//int sin[] = {0, 1, 0, -1};
			//int cos[] = {1, 0, -1, 0};
			
			for(int i = 0; i < sin.length; ++i) {
				int x1 = x+sin[i];
				int y1 = y+cos[i];
				if(x1 < 0 || x1 >= knoten.length || y1 < 0 || y1 >= knoten[0].length) {
					continue;
				}
				if(knoten[x1][y1] == 0) {
					knoten[x1][y1] = index;
					remaining.offer(new Position(x1, y1));
				}
				else {
					if(knoten[x1][y1] != index && ! kugeln.get(index).istNachbarVon(knoten[x1][y1])) {
						this.graphenPunkte.add(new Position(x1, y1));
					}
				}
			}
	
	
		}
	}
}
