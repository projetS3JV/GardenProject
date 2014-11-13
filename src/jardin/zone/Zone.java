package jardin.zone;

import java.util.LinkedList;

import jardin.Ensoleillement;

public class Zone {
	
	private LinkedList<Zone> zones = new LinkedList<Zone>();
	protected Ensoleillement ensoleillement;
	
	public Zone() {
		
	}
	
	/**
	 * Ajoute une zone dans la liste de zones si cela est possible
	 * @param z la zone � ajouter
	 */
	public void addZone(Zone z) {
		//verification si  la zone peut etre ajouter
		
		//ajout de la zone
		this.zones.add(z);
	}
	
	/**
	 * Supprime une zone
	 * @param z la zone � supprimer
	 */
	public void deleteZone(Zone z) {
		this.zones.remove(z);
	}
}
