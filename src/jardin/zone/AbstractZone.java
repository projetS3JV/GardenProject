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
	 * Constructeur par copie de Zone
	 * @param z la zone a copier
	 */
	public AbstractZone(AbstractZone z) {
		super();
		this.npoints = z.npoints;
		for (int i = 0; i < this.npoints; i++) {
			this.addPoint(z.xpoints[i], z.ypoints[i]);
		}
		z.ensoleillement = this.ensoleillement;
	}
	
	/**
	 * Ajoute un point ayant les coordonnées passé à la zone
	 * Si le point est proche du premier, la zone se ferme
	 * @param xCoord la coordonée en x
	 * @param yCoord la coordonnée en y
	 */
	public void addPoint(int xCoord, int yCoord) throws IllegalArgumentException{
		if (this.isClosed())
			throw new IllegalArgumentException("impossible d'ajouter un point, la zone est fermé");
		if (Math.abs(this.xpoints[0] - xCoord) <= 10 && Math.abs(this.ypoints[0] - yCoord) <= 10)
			super.addPoint(this.xpoints[0], this.ypoints[0]);
		else 	
			super.addPoint(xCoord, yCoord);
	}
	
	public boolean isClosed() {
		return this.xpoints[0] == this.xpoints[this.npoints-1] && this.ypoints[0] == this.ypoints[this.npoints -1];
	}
	
	
}
