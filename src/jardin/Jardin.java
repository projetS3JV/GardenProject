package jardin;

import jardin.zone.AbstractZone;
import jardin.zone.Zone;

import java.util.LinkedList;

public class Jardin {

	/**
	 * Le nom du jardin
	 */
	private String name;
	private LinkedList<AbstractZone> zones = new LinkedList<AbstractZone>();
	
	public Jardin(String name) {
		this.name = name;
	}
	
	/**
	 * Accesseur au nom
	 * @return le nom du jardin
	 */
	public String getName() {
		return this.name;
	}
	
	/////////////////////////////////////////////////
	//					Le nom
	/////////////////////////////////////////////////
	
	/**
	 * Modificateur du nom
	 * @param name le nouveau nom du jardin
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	///////////////////////////////////////////////////
	//					Les Zones
	///////////////////////////////////////////////////
	
	/**
	 * Accesseur au zones du jardin
	 * @return les zones
	 */
	public LinkedList<AbstractZone> getZones() {
		return this.zones;
	}
	
	/**
	 * Ajoute une zone dans la liste de zones
	 * @param zone la zone à ajouter
	 */
	public void addZone(Zone zone) {
		this.zones.add(zone);
	}
	
	/**
	 * Retire une zone de la liste si elle est présente. Sinon ne fait rien
	 * @param zone la zone à retirer
	 */
	public void deleteZone(Zone zone) {
		this.zones.remove(zone);
	}
	
	
}
