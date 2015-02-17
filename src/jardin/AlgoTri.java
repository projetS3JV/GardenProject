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
	private ArrayList<Plante> listePlante;
	private ArrayList<Plante> newListePlante;
	private PlantothequePanel pn;
	private PlantothequeFrame pf;
	
	public ArrayList<Plante> algoTri(PlantothequeFrame pf){
		JList jl = pf.getListePlante();
		
		
		
		
		
		
		return null;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
