package jardin;

import jardin.ui.PlantothequeFrame;
import jardin.Ensoleillement;

import javax.swing.JFrame;
import javax.swing.JList;

import sun.swing.UIAction;

public class AlgoSearch {
	private AccesBD instance = AccesBD.getInstance();
	private SortedListModel modelList = instance.getPlantes();
	private JList listePlante;
	
	JList AlgoSearch(PlantothequeFrame pf){
				
		/////////Recherche par NOM
		if(pf.getNomF().getText() != null);
		
		/////////Recherche par Famille
		if(pf.getFamilleF().getText() != null);
		
		////////Recherche par Type de plante
		if(pf.getTypePlanteComboBox().getSelectedItem() !=null);
		
		////////Recherche par type de sol
		if(pf.getTypeSolComboBox().getSelectedItem() !=null);
		
		////////Recherche par ensoleillement
		if(pf.getEnsoleillementComboBox().getSelectedItem() != null){
			Ensoleillement e = (Ensoleillement) pf.getEnsoleillementComboBox().getSelectedItem();
			for(int i=0;i<modelList.getSize();i++){
				if(modelList.getElementAt(i).getEnsoleillement() == e.getValue()){
					listePlante.add();
				}
			}
		}
		
		
		return this.listePlante;
	}
	
	
}
