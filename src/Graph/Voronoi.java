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
		if(this.punkte.size() > 1) {
			this.generiereVoronoi();
		}
		
		
	}
	
	
	
	/**
	 * @return the kanten
	 */
	public List<Kante> getKanten() {
		return kanten;
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
		
		Parabel lp = Parabel.getLinkerVater(b);
		Parabel rp = Parabel.getLinkerVater(b);
		
		if(lp == null || rp == null) {
			return;
		}
		
		Parabel a = Parabel.getLinkesKind(lp);
		Parabel c = Parabel.getRechtesKind(rp);
		
		if( a == null || c == null || a.p == c.p) {
			return;
		}
		
		if(ccw(a.p, b.p, c.p) != 1) {
			return;
		}
		
		Position start = getKantenSchnitt(lp.kante, rp.kante);
		if(start == null) {
			return;
		}
		
		double dx = b.p.getX() - start.getX();
		double dy = b.p.getY() - start.getY();
		double d = Math.sqrt((dx*dx) + (dy*dy));
		
		if(start.getY() + d < this.yAktuell) {
			return;
		}
		
		Position ep = new Position(start.getX(), start.getY() + d);
		
		Event e = new Event(ep, Event.CIRCLE_EVENT);
		e.bogen = b;
		b.event = e;
		this.events.add(e);
		
	}
	
	public int ccw(Position a, Position b, Position c) {
		
		double area2 = (b.getX()-a.getX())*(c.getY()-a.getY()) - 
				(b.getY()-a.getY())*(c.getX()-a.getX());
		
		if(area2 < 0) {
			return -1;
		}
		else if(area2 > 0) {
			return 1;
		}
		return 0;
		
	}
	
	private Position getKantenSchnitt(Kante a, Kante b) {
		
		if(b.steigung == a.steigung && b.yPosition != a.yPosition) {
			return null;
		}
		
		double x = (b.yPosition - a.yPosition) / (a.steigung-b.steigung);
		double y = a.steigung*x + a.yPosition;
		
		return new Position(x, y);
		
	}
	
	private double getXKante(Parabel par) {
		
		Parabel links = Parabel.getLinkesKind(par);
		Parabel rechts = Parabel.getRechtesKind(par);
		
		Position p = links.p;
		Position r = rechts.p;
		
		double dp = 2*(p.getY() - this.yAktuell);
		double a1 = 1/dp;
		double b1 = -2*p.getX() / dp;
		double c1 = (p.getX()*p.getX() + p.getY()*p.getY() - this.yAktuell*this.yAktuell) / dp;
		
		double dp2 = 2*(r.getY() - this.yAktuell);
		double a2 = 1/dp2;
		double b2 = -2*r.getX() / dp2;
		double c2 = (r.getX()*r.getX() + r.getY() * r.getY() - this.yAktuell*this.yAktuell) / dp2;
		
		double a = a1-a2;
		double b = b1-b2;
		double c = c1-c2;
		
		double disc = b*b - 4*a*c;
		double x1 = (-b + Math.sqrt(disc)) / (2*a);
		double x2 = (-b - Math.sqrt(disc)) / (2*a);
		
		double ry;
		if(p.getY() > r.getX()) {
			ry = Math.max(x1,  x2);
		}
		else {
			ry = Math.min(x1, x2);
		}
		
		return ry;
		
	}
	
	private Parabel getParabelX(double xx) {
		
		Parabel par = this.wurzel;
		double x = 0;
		
		while(par.typ == Parabel.IS_VERTEX) {
			
			x = getXKante(par);
			if(x > xx) {
				par = par.linkesKind;
			}
			else {
				par = par.rechtesKind;
			}
			
		}
		
		return par;
		
	}
	
	private double getY(Position p, double x) {
		
		double dp = 2*(p.getY() - this.yAktuell);
		double a1 = 1/dp;
		double b1 = -2*p.getX() / dp;
		double c1 = (p.getX()*p.getX() + p.getY()*p.getY() - this.yAktuell*this.yAktuell) / dp;
		
		return (a1*x*x + b1*x + c1);
		
	}
	
}
