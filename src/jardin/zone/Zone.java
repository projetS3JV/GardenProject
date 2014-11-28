package jardin.zone;

import jardin.Ensoleillement;

import java.awt.Polygon;
import java.util.LinkedList;

public class Zone extends Polygon{
	
	private static final long serialVersionUID = 0L;
	private LinkedList<Zone> zones = new LinkedList<Zone>();
	protected Ensoleillement ensoleillement;
	
	/**
	 * Crée une zone vide
	 */
	public Zone() {
		
	}
	
	/**
	 * Accesseur au zones contenus dans celle ci
	 * @return une LinkedList de zones
	 */
	public LinkedList<Zone> getZones() {
		return this.zones;
	}
	
	/**
	 * Ajoute une zone dans la liste de zones si cela est possible, lance une exception sinon
	 * @param z la zone à ajouter
	 * @throws IllegalArgumentException Si un des points est en dehors de la zone courante
	 */
	public void addZone(Zone zone) throws IllegalArgumentException {
		
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
	public void deleteZone(Zone zone) {
		this.zones.remove(zone);
	}
	
	/**
	 * Ajoute un point ayant les coordonnées passé à la zone
	 * @param xCoord la coordonée en x
	 * @param yCoord la coordonnée en y
	 */
	public void addPoint(int xCoord, int yCoord) {
		this.addPoint(xCoord, yCoord);
		this.invalidate();
	}
}
