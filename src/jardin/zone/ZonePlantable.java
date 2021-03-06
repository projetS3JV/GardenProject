package jardin.zone;

import javax.swing.JOptionPane;

import jardin.plante.Plante;

public class ZonePlantable extends AbstractZone {


	private static final long serialVersionUID = 0L;
	private Plante plante = null;
	private int typeSol;
	private Zone zoneConteneur = null;

	/**
	 * Crée une zone plantable vide
	 */
	public ZonePlantable(int ensoleillement, int sol) {
		super(ensoleillement);
		this.typeSol = sol;
	}

	/**
	 * Crée une zone plantable avec un point
	 * @param x la coordonnée en x du point
	 * @param y la coordonnée en y du point
	 */
	public ZonePlantable(int ensoleillement, int x, int y, int sol) {
		super(ensoleillement, x, y);
		this.typeSol = sol;
	}

	/**
	 * Constructeur par copie qui permet d'obtenir une ZonePlantable a partir d'une AbstractZone
	 * @param zone la zone a changer
	 * @param sol le type de sol de la zone
	 */
	public ZonePlantable(AbstractZone zone, int sol) {
		super(zone);
		this.typeSol = sol;
	}

	public ZonePlantable(int[] xPoints, int[] yPoints, int ensoleillement, int sol) {
		super(xPoints,yPoints, ensoleillement);
		this.typeSol = sol;
	}

	/**
	 * Constructeur par copie qui permet d'obtenir une ZonePlantable a partir d'une AbstractZone
	 * Si la plante ne peut etre mise une exception est lancé
	 * @param zone la zone a changer
	 * @param sol le type de sol de la zone
	 * @throws IllegalArgumentException si la plante ne peut etre mise
	 */
	public ZonePlantable(AbstractZone zone, int sol, Plante plante) throws IllegalArgumentException{
		super(zone);
		this.typeSol = sol;
		this.setPlante(plante);
	}

	/**
	 * Ajoute une plante dans la zone si elle peut etre planter
	 * @param p la plante à ajouter
	 */
	public void setPlante(Plante plante) throws IllegalArgumentException{
		int k =0;
		// verification si la plante peut être planté
		for(int i =0; i< plante.getTypeSol().size() ; i++)
			if(plante.getTypeSol().get(i) != this.typeSol)
				k++;
				//JOptionPane.showMessageDialog(null, "Type de sol incompatible", "Erreur", JOptionPane.ERROR_MESSAGE);
				//throw new IllegalArgumentException("impossible de mettre une plante, le type de sol ne correspond pas");
		if (plante.getEnsoleillement() != this.ensoleillement)
			//throw new IllegalArgumentException("impossible de mettre une plante, l'ensoleillement ne correspond pas");
		// ajout de la plante
			if(plante.getTypeSol().size()== k){
				JOptionPane.showMessageDialog(null, "Type de sol incompatible", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else{
				this.plante = plante;
			}
	}

	/**
	 * Accesseurs à la plante de la zone
	 * @return une plante
	 */
	public Plante getPlante() {
		return this.plante;
	}

	/**
	 * Accesseur au Type de sol de la zone
	 * @return le TypeSol de la zone
	 */
	public int getTypeSol() {
		return this.typeSol;
	}

	/**
	 * Change le type de sol si possible, lance une exception sinon
	 * @param nouveau le nouveau type de sol
	 * @throws IllegalArgumentException si c'est impossible
	 */
	public void setTypeSol(int nouveau) throws IllegalArgumentException {
		int k=0;
		if(this.plante == null) {
			this.typeSol = nouveau;
		} else {
			for(int i =0; i < plante.getTypeSol().size() ; i++)
				if(plante.getTypeSol().get(i) != nouveau)
					k++;
			
					//throw new IllegalArgumentException("impossible de mettre ce type de sol, la plante de cette zone est incompatible");

			this.typeSol = nouveau;
		}
	}

	public void setZoneConteneur(Zone zoneConteneur) {
		this.zoneConteneur = zoneConteneur;
	}

	public Zone getZoneConteneur() {
		return zoneConteneur;
	}

}
