/**
 * 
 */
package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author l.hofer
 *
 */
public class Floodfill {
	
	private Raum r;
	private List<Position> graphenPunkte;
	private long knotenAnzahl;
	
	public Floodfill(Raum r) {
		this.r = r;
		this.graphenPunkte = new ArrayList<>();
		this.knotenAnzahl = 0;
		this.findeGraphen();
	}
	
	/**
	 * @return the graphenPunkte
	 */
	public List<Position> getGraphenPunkte() {
		return graphenPunkte;
	}
	
	/**
	 * @return the knotenAnzahl
	 */
	public long getKnotenAnzahl() {
		return knotenAnzahl;
	}

	/**
	 * berechnet den Graphen durch die Kugeln
	 */
	private void findeGraphen() {
		HashMap<Integer, Kugel> kugeln = (HashMap<Integer, Kugel>) r.getKugeln();
		int[][]  knoten = new int[(int)(r.getBreite()*10)][(int) (r.getHoehe()*10)];
		
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
		bestimmeKnotenAnzahl(knoten);
	}
	
private void bestimmeKnotenAnzahl(int knoten[][]){
        
        for(int i = 0; i < knoten.length - 1; i++){
            for(int j = 0; j < knoten[0].length - 1; j++){
                int a = knoten[i][j], b = knoten[i+1][j], c = knoten[i][j+1], d = knoten[i+1][j+1]; 

                if( a != b){
                    
                    if( (a != c && b != c) || (a != d && b != d)){
                        this.knotenAnzahl ++;
                    }
                    
                }
                else{   // a und b sind gleich
                    
                    if( c != d && ( a != c && a != d) ){
                        this.knotenAnzahl ++;
                    }
                    
                }
            }
        }
        
	}

}
