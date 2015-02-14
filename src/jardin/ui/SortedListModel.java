package jardin.ui;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;

    public class SortedListModel<T extends Comparable<T>> extends AbstractListModel<T> {
        private ArrayList<T> sortedList;
        
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
        public void add(T element) {
            int pos = Collections.binarySearch(this.sortedList, element);
            if (pos < 0) {
            	pos = - pos -1;
            }
            this.sortedList.add(pos,element);
            this.fireIntervalAdded(this, pos, pos);
        }
        
        /**
         * supprime l'élément element de la liste
         * @param element élément à supprimer
         */
        public void remove(T element) {
        	int pos = this.sortedList.indexOf(element);
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
        public T getElementAt(int index) {
            return this.sortedList.get(index);
        }

}
