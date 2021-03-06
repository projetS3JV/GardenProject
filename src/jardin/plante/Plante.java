package jardin.plante;

import jardin.Ensoleillement;
import jardin.TypeSol;

import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Plante implements Comparable<Integer> {
	
	// Attributs d'instance ---------------------------------------------------------------------------
	
	private int id = -1;
	
	/*
	 * La taille finale de la plante
	 */
	private int tailleFin;

	private ArrayList<Date> dateFinFloraison;
	
	/*
	 * La date de la floraison
	 */
	private ArrayList<Date> dateFloraison;
	
	/*
	 * La date de plantation
	 */
	private Date datePlantation;
	
	/*
	 * La couleur de la plante
	 */
	private Color couleur_en_fleur;
	
	private Color couleur_non_fleuris;
	
	
	/* 
	 * True si la plante est vivace
	 */
	private boolean vivace;
	
	
	/*
	 * Nom de la plante
	 */
	private String nom;
	
	/*
	 * Nom latin de la plante
	 */
	private String nomL;
	
	
	/*
	 * L'image de la plante fleurie
	 */
	private ImageIcon ImgFleurie;
	private String pathImg;
	

	/*
	 * Type de plante (Enumeration)
	 */
	private int type;
	
	private int ensoleillement ;
	
	private ArrayList<Integer> typeSol;
	
	private String description;
	
	private int nbDate;
	
	// Constructeurs ---------------------------------------------------------------------------------
	
	/*
	 * Constructeur par defaut
	 */
	public Plante(){
		this.dateFloraison = null;
		this.dateFinFloraison = null;
		this.datePlantation = null;
		this.tailleFin = 0;
		this.couleur_en_fleur = null;
		this.couleur_non_fleuris = null;
		this.vivace = false;
		this.nom = "";
		this.nomL = "";
		this.ImgFleurie = null;
		this.type = 0;
		this.ensoleillement = 0;
		this.typeSol = null;
		this.description = null;
		this.nbDate = 0;
	}
	

	public Plante(int tailleFin, ArrayList<Date> dateFloraison, ArrayList<Date> dateFinFloraison,
			Date datePlantation, Color fleuris, Color nonFleuris, boolean vivace,
			String nom, String nomL, String imgFleurie, TypePlante type,
			Ensoleillement ensoleillement, ArrayList<Integer> typeSol, String description) {
		this.tailleFin = tailleFin;
		this.dateFloraison = dateFloraison;
		this.dateFinFloraison = dateFinFloraison;
		this.datePlantation = datePlantation;
		this.couleur_en_fleur = fleuris;
		this.couleur_non_fleuris = nonFleuris;
		this.vivace = vivace;
		this.nom = nom;
		this.nomL = nomL;
		this.ImgFleurie = new ImageIcon(imgFleurie);
		this.pathImg = imgFleurie;
		this.type = type.getValue();
		this.ensoleillement = ensoleillement.getValue();
		this.typeSol = typeSol;
		this.description = description;
		this.nbDate = this.dateFloraison.size();
	}

	





	// Getter et Setter ---------------------------------------------------------------------------------
	
	
	public String getPathImg() {
		return pathImg;
	}


	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}


	public ArrayList<Date> getDateFinFloraison() {
		return dateFinFloraison;
	}


	public void setDateFinFloraison(ArrayList<Date> dateFinFloraison) {
		this.dateFinFloraison = dateFinFloraison;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Integer> getTypeSol() {
		return typeSol;
	}

	public void setTypeSol(ArrayList<Integer> typeSol) {
		this.typeSol = typeSol;
	}

	public int getEnsoleillement() {
		return ensoleillement;
	}

	public void setEnsoleillement(int ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTailleFin() {
		return tailleFin;
	}
	public void setTailleFin(int tailleFin) {
		this.tailleFin = tailleFin;
	}
	public ArrayList<Date> getDateFloraison() {
		return dateFloraison;
	}
	public void setDateFloraison(ArrayList<Date> dateFloraison) {
		this.dateFloraison = dateFloraison;
		this.nbDate= dateFloraison.size();
	}
	public int getNbDate() {
		return nbDate;
	}


	public void setNbDate(int nbDate) {
		this.nbDate = nbDate;
	}


	public Date getDatePlantation() {
		return datePlantation;
	}
	public void setDatePlantation(Date datePlantation) {
		this.datePlantation = datePlantation;
	}
	public boolean isVivace() {
		return vivace;
	}
	public void setVivace(boolean vivace) {
		this.vivace = vivace;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomL() {
		return nomL;
	}
	public Color getCouleur_en_fleur() {
		return couleur_en_fleur;
	}


	public void setCouleur_en_fleur(Color couleur_en_fleur) {
		this.couleur_en_fleur = couleur_en_fleur;
	}


	public Color getCouleur_non_fleuris() {
		return couleur_non_fleuris;
	}


	public void setCouleur_non_fleuris(Color couleur_non_fleuris) {
		this.couleur_non_fleuris = couleur_non_fleuris;
	}


	public void setNomL(String nomL) {
		this.nomL = nomL;
	}
	public ImageIcon getImgFleurie() {
		return ImgFleurie;
	}
	public void setImgFleurie(ImageIcon imgFleurie) {
		ImgFleurie = imgFleurie;
	}
	
	@Override
	public int compareTo(Integer id) {
		return this.id - id;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o instanceof Integer) return ((Integer) o) == this.id;
		if (o instanceof Plante) {
			Plante p = (Plante) o;
			return p.id == this.id;
		}
		return false;
	}
}
