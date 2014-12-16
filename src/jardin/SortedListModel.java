package jardin;

import jardin.plante.Plante;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;

/**
 * Modèle de liste pour Swing.
 * Les éléments sont triés par ordre croissant (suivant l'ordre défini par
 * la méthode compareTo de l'interface Comparable)
 * @param <T> type d'élément de la liste (doit implémenter Comparable<T>)
 */
public class SortedListModel extends AbstractListModel<Plante> {
    private static final long serialVersionUID = 1L;
	private ArrayList<Plante> sortedList;
    
    /**
     * Constructeur	
     */
    public SortedListModel() {
        super();
        this.sortedList = new ArrayList<>();
    }
    
    /**
     * ajoute l'élément element à la liste (en tenant compte de l'ordre
     * défini par la méthode compareTo de l'interface Comparable)
     * @param element élément à ajouter
     */
    public void add(Plante element) {
    	int pos = Collections.binarySearch(this.sortedList, element.getId());
    	pos = (pos < 0) ? -(pos + 1) : pos;
        this.sortedList.add(pos,element);
        this.fireIntervalAdded(this, pos, pos);
    }

    /**
     * supprime l'élément element de la liste
     * @param element élément à supprimer
     */
    public void remove(int id) {
        int pos = this.sortedList.indexOf(id);
        if (pos < 0) throw new IllegalArgumentException("ID non existant");
        this.sortedList.remove(pos);
        this.fireIntervalRemoved(this, pos, pos);
    }
    
    /**
     * retourne le nombre d'éléments de la liste
     * Cette méthode est définie par l'interface ListModel
     * @return nombre d'éléments de la liste
     */
    public int getSize() {
        return this.sortedList.size();
    }

    /**
     * retourne l'élément d'indice index
     * Cette méthode est définie par l'interface ListModel
     * @param index indice de l'élément
     * @return élément d'indice index
     */
    public Plante getElementAt(int index) {
        return this.sortedList.get(index);
    }

	public Plante getElementById(int id) throws IllegalArgumentException {
		int pos = Collections.binarySearch(this.sortedList, id);
		if (pos < 0) throw new IllegalArgumentException("ID non existant");
		return 	getElementAt(pos);
	}
      
}
