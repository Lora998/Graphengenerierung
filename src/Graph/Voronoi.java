/**
 * 
 */
package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author l.hofer
 *
 */
public class Voronoi {

	
	private Raum r;
	private List<Kante> kanten;
	private PriorityQueue<Event> events;
	private Parabel wurzel;
	private double yAktuell; // fuer die sweep Line
	private List<Position> punkte;
	
	public Voronoi(Raum r) {
		
		this.r = r;
		this.punkte = new ArrayList<>();
		
		for(Kugel k : r.getKugeln().values()) {
			this.punkte.add(k.getPosition());
		}
		
		this.kanten = new ArrayList<Kante>();
		this.generiereVoronoi();
		
	}
	
	private void generiereVoronoi() {
		
		this.events = new PriorityQueue<Event>();
		for(Position p : this.punkte) {
			this.events.add(new Event(p, Event.SITE_EVENT));
		}
		
		int zaehler = 0;
		
		while(!this.events.isEmpty()) {
			
			Event e = events.remove();
			yAktuell = e.p.getY();
			zaehler++;
			
			if(e.typ == Event.SITE_EVENT) {
				handleSite(e.p);
			}
			else {
				handleCircle(e);
			}
		}
		
		yAktuell = r.getBreite()+r.getHoehe();
		
		schliesseKanten(this.wurzel);
		
		for(Kante k : this.kanten) {
			
			if(k.nachbar != null) {
				
				k.start = k.nachbar.ende;
				k.nachbar = null;
				
			}
			
		}
		
	}
	
	private void schliesseKanten(Parabel p) {
		
		if(p.typ == Parabel.IS_FOCUS) {
			p = null;
			return;
		}
		
		double x = getXKante(p);
		p.kante.ende = new Position(x, p.kante.steigung*x+p.kante.yPosition);
		this.kanten.add(p.kante);
		
		schliesseKanten(p.linkesKind);
		schliesseKanten(p.rechtesKind);
		p = null;
		
	}
	
	private void handleSite(Position p) {
		
		if(this.wurzel == null) {
			this.wurzel = new Parabel(p);
			return;
		}
		
		Parabel par = getParabelX(p.getX());
		if(par.event != null) {
			this.events.remove(par.event);
			par.event = null;
		}
		
		Position start = new Position(p.getX(), getY(par.p, p.getX()));
		Kante kLinks = new Kante(start, par.p, p);
		Kante kRechts = new Kante(start, p, par.p);
		kLinks.nachbar = kRechts;
		kRechts.nachbar = kLinks;
		par.kante = kLinks;
		par.typ = Parabel.IS_VERTEX;
		
		Parabel p0 = new Parabel(par.p);
		Parabel p1 = new Parabel(p);
		Parabel p2 = new Parabel(par.p);
		
		par.setLinkesKind(p0);;
		par.setRechtesKind(new Parabel());
		par.rechtesKind.kante = kRechts;
		par.rechtesKind.setLinkesKind(p1);
		par.rechtesKind.setRechtesKind(p2);
		
		checkCircleEvent(p0);
		checkCircleEvent(p2);
	}
	
	private void handleCircle(Event e ) {
		
		Parabel p1 = e.bogen;
		Parabel xl = Parabel.getLinkerVater(p1);
		Parabel xr = Parabel.getRechterVater(p1);
		Parabel p0 = Parabel.getLinkesKind(xl);
		Parabel p2 = Parabel.getRechtesKind(xr);
		
		if(p0.event != null){
			this.events.remove(p0.event);
			p0.event = null;
		}
		if(p2.event != null) {
			this.events.remove(p2);
			p2.event = null;
		}
		
		Position p = new Position (e.p.getX(), getY(p1.p, e.p.getX())); // neuer vertex
		
		xl.kante.ende = p;
		xr.kante.ende = p;
		this.kanten.add(xl.kante);
		this.kanten.add(xr.kante);
		
		Parabel ueber = new Parabel();
		Parabel par = p1;
		
		while(par != this.wurzel) {
			
			par = par.vater;
			if(par == xl) {
				ueber = xl;
			}
			if(par == xr) {
				ueber = xr;
			}
			
		}
		ueber.kante = new Kante(p, p0.p, p2.p);
		
		Parabel gVater = p1.vater.vater;
		
		if(p1.vater.linkesKind == p1) {
			if(gVater.linkesKind == p1.vater) {
				gVater.setLinkesKind(p1.vater.rechtesKind);
			}
			if(gVater.rechtesKind == p1.vater){
				gVater.setRechtesKind(p1.vater.rechtesKind);
			}
		}
		else {
			if(gVater.linkesKind == p1.vater) {
				gVater.setLinkesKind(p1.vater.linkesKind);
			}
			if(gVater.rechtesKind == p1.vater) {
				gVater.setRechtesKind(p1.vater.linkesKind);
			}
		}
		
		Position op = p1.p;
		p1.vater = null;
		p1 = null;
		
		checkCircleEvent(p0);
		checkCircleEvent(p2);
		
	}
	
	private void checkCircleEvent(Parabel b) {
		
	}
}
