package jardin.zone;

import jardin.Ensoleillement;

import java.awt.Polygon;

public class AbstractZone extends Polygon {

	private static final long serialVersionUID = 0L;
	protected Ensoleillement ensoleillement;
	
	public AbstractZone() {
		super();
	}
	
	public AbstractZone(int x, int y) {
		super();
		this.addPoint(x, y);
	}
	
	/**
	 * Ajoute un point ayant les coordonnées passé à la zone
	 * @param xCoord la coordonée en x
	 * @param yCoord la coordonnée en y
	 */
	public void addPoint(int xCoord, int yCoord) {
		super.addPoint(xCoord, yCoord);
	}
	
	
}
