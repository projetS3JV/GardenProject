package jardin.zone;

import java.awt.Polygon;

public class AbstractZone extends Polygon {

	private static final long serialVersionUID = 0L;
	protected int ensoleillement;
	public static final int interval = 10;
	private int id = -1;
	
	public AbstractZone() {
		super();
	}
	
	/**
	 * Constructeur d'une AbstractZone vide
	 * @param ensoleillement
	 */
	public AbstractZone(int ensoleillement) {
		super();
		this.ensoleillement = ensoleillement;
	}
	
	/**
	 * Constructeur d'une AbstractZone avec un point
	 * @param ensoleillement l'ensoleillement de la zone
	 * @param x la coordonnée en x du premier point
	 * @param y la coordonnée en y du premier poin
	 */
	public AbstractZone(int ensoleillement, int x, int y) {
		this(ensoleillement);
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
		this.ensoleillement = z.ensoleillement;
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

		if (this.npoints > 1 && (Math.abs(this.xpoints[0] - xCoord) <= AbstractZone.interval && Math.abs(this.ypoints[0] - yCoord) <= AbstractZone.interval))
			super.addPoint(this.xpoints[0], this.ypoints[0]);
		else 	
			super.addPoint(xCoord, yCoord);
	}
	
	/**
	 * Retourne si la zone est fermé donc si la zone.
	 * Une zone est fermé si sont premier point = sont dernier point
	 * @return true si la zone est fermé false sinon
	 */
	public boolean isClosed() {
		return npoints > 1 && this.xpoints[0] == this.xpoints[this.npoints-1] && this.ypoints[0] == this.ypoints[this.npoints -1];
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEnsoleillement() {
		return this.ensoleillement;
	}
	
	public void setEnsoleillement(int ensoleillement) {
		this.ensoleillement = ensoleillement;
	}
	
}
