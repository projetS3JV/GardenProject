package jardin.zone;

import java.util.LinkedList;

public class Zone extends AbstractZone{
	
	private static final long serialVersionUID = 0L;
	private LinkedList<AbstractZone> zones = new LinkedList<AbstractZone>();
	
	/**
	 * Crée une zone vide
	 */
	public Zone(int ensoleillement) {
		super(ensoleillement);
	}
	
	/**
	 * Crée une zone avec un Point
	 * @param x la coordonnée en x
	 * @param y la coordonnée en y
	 */
	public Zone(int ensoleillement, int x, int y) {
		super(ensoleillement, x,y);
	}
	
	public Zone(int[] xPoints,int [] yPoints, int ensoleillement) {
		super(xPoints, yPoints, ensoleillement);
	}
	
	/**
	 * Constructeur par copie qui permet d'obtenir une zone a partir d'une AbstractZone
	 * @param zone la zone a copier
	 */
	public Zone(AbstractZone zone) {
		super(zone);
	}
	
	/**
	 * Accesseur au zones contenus dans celle ci
	 * @return une LinkedList de zones
	 */
	public LinkedList<AbstractZone> getZones() {
		return this.zones;
	}
	
	/**
	 * Ajoute une zone dans la liste de zones si cela est possible, lance une exception sinon.
	 * Quelque soit l'ensoleillement, la zone obtient l'ensoleillement de la zone parent
	 * @param z la zone à ajouter
	 * @throws IllegalArgumentException Si un des points est en dehors de la zone courante
	 */
	public void addZone(AbstractZone zone) throws IllegalArgumentException {
		
		//verification si  la zone peut etre ajouter		
		//Si les points sonts dans la zone
		for(int i = 0; i < this.xpoints.length; i++) {
			
			// Si un point est en dehors de la zone, on lance une exception
			if (!this.contains(zone.xpoints[i], zone.ypoints[i]))
				throw new IllegalArgumentException("La zone à ajouter ne doit pas dépasser la zone courante");
		}
		
		//ajout de la zone et mise a jour de l'ensoleillement
		this.zones.add(zone);
		zone.ensoleillement = this.ensoleillement;
	}
	
	/**
	 * Supprime une zone
	 * @param z la zone à supprimer
	 */
	public void deleteZone(AbstractZone zone) {
		this.zones.remove(zone);
	}
	
	/**
	 * Methode qui prend les coordonnées d'un point en parametre
	 * et retourne la premiere zone trouvé dans où le point est dedan
	 * @param x coordonnée en x du point
	 * @param y coordonnée en y du point
	 * @return la zone trouvé, null si aucune ne correspond;
	 */
	public AbstractZone getZone(int x, int y) {
		AbstractZone z = null;
		for (int i = 0; i < this.zones.size() && z == null; i++) {
			if (this.zones.get(i).contains(x, y))
				z = this.zones.get(i);
		}
		return z;
	}
}

	