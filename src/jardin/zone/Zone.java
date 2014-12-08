package jardin.zone;

import java.util.LinkedList;

public class Zone extends AbstractZone{
	
	private static final long serialVersionUID = 0L;
	private LinkedList<AbstractZone> zones = new LinkedList<AbstractZone>();
	
	/**
	 * Crée une zone vide
	 */
	public Zone() {
		super();
	}
	
	/**
	 * Crée une zone avec un Point
	 * @param x la coordonnée en x
	 * @param y la coordonnée en y
	 */
	public Zone(int x, int y) {
		super(x,y);
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
	 * Ajoute une zone dans la liste de zones si cela est possible, lance une exception sinon
	 * @param z la zone à ajouter
	 * @throws IllegalArgumentException Si un des points est en dehors de la zone courante
	 */
	public void addZone(AbstractZone zone) throws IllegalArgumentException {
		
		//verification si  la zone peut etre ajouter
		
		//Verification de l'ensoleillement
		if (this.ensoleillement != zone.ensoleillement)
			throw new IllegalArgumentException("l'ensoleillement n'est pas possible");
		
		
		//Si les points sonts dans la zone
		for(int i = 0; i < this.xpoints.length; i++) {
			
			// Si un point est en dehors de la zone, on lance une exception
			if (!this.contains(zone.xpoints[i], zone.ypoints[i]))
				throw new IllegalArgumentException("La zone à ajouter ne doit pas dépasser la zone courante");
		}
		
		//ajout de la zone
		this.zones.add(zone);
	}
	
	/**
	 * Supprime une zone
	 * @param z la zone à supprimer
	 */
	public void deleteZone(AbstractZone zone) {
		this.zones.remove(zone);
	}
}
