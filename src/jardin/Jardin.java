package jardin;

import jardin.zone.AbstractZone;
import jardin.zone.Zone;

import java.util.LinkedList;

public class Jardin {

	/**
	 * Le nom du jardin
	 */
	private String name;
	private LinkedList<Zone> zones = new LinkedList<Zone>();
	private int id = -1;
	private int width;
	private int height;
	
	/**
	 * Constructeur de jardin si les entiers sont negatifs, il seront transformés en positif
	 * @param name le nom du jardin
	 * @param width la largeur du jardin
	 * @param height la hauteur du jardin
	 */
	public Jardin(String name, int width, int height) {
		this.name = name;
		this.width = Math.abs(width);
		this.height = Math.abs(height);
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
	public LinkedList<Zone> getZones() {
		return this.zones;
	}
	
	/**
	 * Ajoute une zone dans la liste de zones (n'autorise pas 2 zones plantable qui se croisent)
	 * @param zone la zone à ajouter
	 * @throws IllegalArgumentException c'est impossible de l'ajouter
	 */
	public void addZone(Zone zone) throws IllegalArgumentException{
		AbstractZone z = this.getZone(zone.xpoints[0], zone.ypoints[0]);
		if (z != null && z instanceof Zone)  {
			((Zone)z).addZone(zone);
		} else if (z == null) {
			this.zones.add(zone);
		} else throw new IllegalArgumentException("impossible d'ajouter une zone plantable directement sans le jardin");
	}
	
	/**
	 * Retire une zone de la liste si elle est présente. Sinon ne fait rien
	 * @param zone la zone à retirer
	 */
	public void deleteZone(Zone zone) {
		this.zones.remove(zone);
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Methode qui retourne la plus petite zone trouvé.
	 * @param x la coordonnée en x du point
	 * @param y la coordonnée en y du point
	 * @return la plus petite zone où le point est dedans null sinon.
	 */
	public AbstractZone getZone(int x, int y) {
		AbstractZone z = null;
		
		for (int i = 0; i < this.zones.size() && z == null; i++) {
			if (this.zones.get(i).contains(x, y))
				z = this.zones.get(i);		
		}
		
		if (z != null && z instanceof Zone) {
			Zone k = (Zone) z;
			AbstractZone temp = k;
			while (temp instanceof Zone && (temp = k.getZone(x, y)) != null) {
				if(temp instanceof Zone) {
					k = (Zone) temp;
				}
				z = temp;
			}
		}
		return z;
	}
}
