package jardin;

import jardin.plante.Plante;
import jardin.ui.PlantothequeFrame;
import jardin.ui.PlantothequePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;

public class AlgoTri implements ActionListener{
	private AccesBD instance = AccesBD.getInstance();
	private SortedListModel modelList = instance.getPlantes();
	
	public ArrayList<Plante> algoTri(PlantothequeFrame pf){
		SortedListModel newModelList = modelList;
		Plante[] p = new Plante[modelList.getSize()];
		
		
		for(int i =0; i< modelList.getSize();i++){
			p[i] = modelList.getElementAt(i);
		}
		
		for(int i =0; i<p.length ; i++){
			
		}
		
		
		
		
		
		
		return null;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
