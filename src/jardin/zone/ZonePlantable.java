package jardin.zone;

import jardin.TypeSol;
import jardin.plante.Plante;

import java.util.LinkedList;



public class ZonePlantable extends Zone {

	private LinkedList<Plante> plantes = new LinkedList<Plante>();
	private TypeSol typeSol;
	
	public ZonePlantable() {
		super();
		
	}
	
	/**
	 * Ajoute une plante dans la zone si elle peut etre planter, lance une exception sinon
	 * @param p la plante à ajouter
	 */
	public void addPlante(Plante p) {
		// verification si la plante peut etre planté
		
		// ajout de la plante
		this.plantes.add(p);
	}
	
	/**
	 * Retire une plante de la zone
	 * @param p la plante à retirer
	 */
	public void deletePlante(Plante p) {
		this.plantes.remove(p);
	}
	
	public LinkedList<Plante> getPlantes() {
		return this.plantes;
	}
	
}
