package jardin.ui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class ListePlanteModel <T extends Comparable<T>> extends AbstractListModel<T> {
	private ArrayList<T> listePlante;
	private int size;
	
	public ListePlanteModel() {
		ArrayList<T> listePlante = new ArrayList<T>();
	}

	@Override
	public T getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
