package Graph;

/**
 * nur fuer Voronoi
 * @author l.hofer
 *
 */

public class Parabel{
	
	public static int IS_FOCUS = 0;
	public static int IS_VERTEX = 1;
	int typ;
	Position p; //wenn Fokus
	Kante kante; // wenn vertex
	Event event; // Parabel mit fokus kannn im Kugel event verschwinden
	Parabel vater;
	Parabel linkesKind;
	Parabel rechtesKind;
	
	public Parabel(Position p) {
		this.p = p;
		typ = IS_FOCUS;
	}
	
	public Parabel() {
		typ = IS_VERTEX;
	}
	
	public void setLinkesKind(Parabel p) {
		this.linkesKind = p;
		p.vater = this;
	}
	
	public void setRechtesKind(Parabel p) {
		this.rechtesKind = p;
		p.vater = this;
	}
	
	public static Parabel getLinks(Parabel p) {
		return getLinkesKind(getLinkerVater(p));
	}
	
	public static Parabel getRechts(Parabel p) {
		return getRechtesKind(getRechterVater(p));
	}
	
	/**
	 * 
	 * @param p
	 * @return naechsten vater links von p
	 */
	public static Parabel getLinkerVater(Parabel p) {
		
		Parabel vater = p.vater;
		if(vater == null) {
			return null;
		}
		
		Parabel letzte = p;
		
		while(vater.linkesKind == letzte) {
			if(vater.vater == null) {
				return null;
			}
			letzte = vater;
			vater = vater.vater;
		}
		return vater;
	}
	
	/**
	 * 
	 * @param p
	 * @return naechsten vater rechts von p
	 */
	public static Parabel getRechterVater(Parabel p) {
		
		Parabel vater = p.vater;
		if(vater == null) {
			return null;
		}
		
		Parabel letzte = p;
		
		while(vater.rechtesKind == letzte) {
			if(vater.vater == null) {
				return null;
			}
			letzte = vater;
			vater = vater.vater;
		}
		return vater;
	}
	
	public static Parabel getLinkesKind(Parabel p) {
		
		if(p==null) {
			return null;
		}
		
		Parabel kind = p.linkesKind;
		
		while(kind.typ == IS_VERTEX) {
			kind = kind.linkesKind;
		}
		
		return kind;
	}
	
	public static Parabel getRechtesKind(Parabel p) {
		
		if(p==null) {
			return null;
		}
		
		Parabel kind = p.rechtesKind;
		
		while(kind.typ == IS_VERTEX) {
			kind = kind.rechtesKind;
		}
		
		return kind;
	}
}