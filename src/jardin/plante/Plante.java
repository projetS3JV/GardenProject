package jardin.plante;

import jardin.Ensoleillement;
import jardin.TypeSol;

import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

public class Plante {
	
	// Attributs d'instance ---------------------------------------------------------------------------
	
	/*
	 * La taille finale de la plante
	 */
	private int tailleFin;
	
	/*
	 * La date de la floraison
	 */
	private GregorianCalendar dateFloraison;
	
	/*
	 * La date de plantation
	 */
	private GregorianCalendar datePlantation;
	
	/*
	 * La couleur de la plante
	 */
	private String couleur;
	
	
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
	
	/*
	 * L'image de la fleur non fleurie
	 */
	private ImageIcon ImgNonFleurie;
	
	
	/*
	 * Type de plante (Enumeration)
	 */
	private TypePlante type;
	
	private Ensoleillement ensoleillement ;
	
	private TypeSol typeSol;
	
	// Constructeurs ---------------------------------------------------------------------------------
	
	/*
	 * Constructeur par defaut
	 */
	public Plante(){
		this.dateFloraison = null;
		this.datePlantation = null;
		this.tailleFin = 0;
		this.couleur = null;
		this.vivace = false;
		this.nom = "";
		this.nomL = "";
		this.ImgFleurie = null;
		this.ImgNonFleurie = null;
		this.type = null;
		this.ensoleillement = null;
		this.typeSol = null;
	}
	
	
	



	// Getter et Setter ---------------------------------------------------------------------------------
	
	
	public TypeSol getTypeSol() {
		return typeSol;
	}

	public void setTypeSol(TypeSol typeSol) {
		this.typeSol = typeSol;
	}

	public Ensoleillement getEnsoleillement() {
		return ensoleillement;
	}

	public void setEnsoleillement(Ensoleillement ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	public TypePlante getType() {
		return type;
	}
	public void setType(TypePlante type) {
		this.type = type;
	}
	public int getTailleFin() {
		return tailleFin;
	}
	public void setTailleFin(int tailleFin) {
		this.tailleFin = tailleFin;
	}
	public GregorianCalendar getDateFloraison() {
		return dateFloraison;
	}
	public void setDateFloraison(GregorianCalendar dateFloraison) {
		this.dateFloraison = dateFloraison;
	}
	public GregorianCalendar getDatePlantation() {
		return datePlantation;
	}
	public void setDatePlantation(GregorianCalendar datePlantation) {
		this.datePlantation = datePlantation;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
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
	public void setNomL(String nomL) {
		this.nomL = nomL;
	}
	public ImageIcon getImgFleurie() {
		return ImgFleurie;
	}
	public void setImgFleurie(ImageIcon imgFleurie) {
		ImgFleurie = imgFleurie;
	}
	public ImageIcon getImgNonFleurie() {
		return ImgNonFleurie;
	}
	public void setImgNonFleurie(ImageIcon imgNonFleurie) {
		ImgNonFleurie = imgNonFleurie;
	}
}
