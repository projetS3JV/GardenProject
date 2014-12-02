package jardin.zone;

import jardin.TypeSol;
import jardin.plante.Plante;

import java.util.LinkedList;



public class ZonePlantable extends Zone {


	private static final long serialVersionUID = 0L;
	private LinkedList<Plante> plantes = new LinkedList<Plante>();
	private TypeSol typeSol;
	
	/**
	 * Crée une zone plantable vide
	 */
	public ZonePlantable() {
		super();		
	}
	
	/**
	 * Crée une zone plantable avec un point
	 * @param x la coordonnée en x du point
	 * @param y la coordonnée en y du point
	 */
	public ZonePlantable(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Ajoute une plante dans la zone si elle peut etre planter
	 * @param p la plante à ajouter
	 */
	public void addPlante(Plante plante) {
		
		// verification si la plante peut etre planté
		
		// ajout de la plante
		this.plantes.add(plante);
	}
	
	/**
	 * Retire une plante de la zone
	 * @param p la plante à retirer
	 */
	public void deletePlante(Plante p) {
		this.plantes.remove(p);
	}
	
	/**
	 * Accesseurs au plantes
	 * @return une linkedList de plantes
	 */
	public LinkedList<Plante> getPlantes() {
		return this.plantes;
	}
	
	/**
	 * Accesseur au Type de sol de la zone
	 * @return le TypeSol de la zone
	 */
	public TypeSol getTypeSol() {
		return this.typeSol;
	}
	
}
