package jardin;

import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.ui.PlantothequeFrame;
import jardin.ui.PlantothequePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;

public class AlgoSearch implements ActionListener{
	private AccesBD instance = AccesBD.getInstance();
	private SortedListModel modelList = instance.getPlantes();
	private SortedListModel newModelList = instance.getPlantes();
	
/////////Recherche par NOM
	boolean AlgoNom(String nom,int index){
		if(modelList.getElementAt(index).getNom().substring(0, nom.lastIndexOf(nom)).equals(nom)){
			return true;	
		}
		return false;
	}

/////////Recherche par Famille
	/*
	boolean AlgoFamille(String fam,int index){
		if(modelList.getElementAt(index).get) == fam){
			return true;	
		}
		return false;
	}*/

////////Recherche par type de sol
	boolean AlgoTypeSol(TypeSol ts,int index){
		for( int j = 0; j < modelList.getElementAt(index).getTypeSol().size() ; j++){
			if(modelList.getElementAt(index).getTypeSol().get(j) == ts.getValue()){
				return true;	
			}
		}
		return false;
	}
	
////////Recherche par Type de plante	
	boolean AlgoTypePlante(TypePlante tp,int index){
		if(modelList.getElementAt(index).getType() == tp.getValue()){
			return true;	
		}
		return false;
	}
	
////////Recherche par ensoleillement
	boolean AlgoEnsol(Ensoleillement e,int index){
		if(modelList.getElementAt(index).getEnsoleillement() == e.getValue()){
			return true;	
		}
		return false;
	}
	
	public JList<Plante> algoSearch(PlantothequeFrame pf){
		Ensoleillement e = (Ensoleillement) pf.getEnsoleillementComboBox().getSelectedItem();
		TypeSol ts = (TypeSol)pf.getTypeSolComboBox().getSelectedItem();
		TypePlante tp = (TypePlante)pf.getTypePlanteComboBox().getSelectedItem();
		String nom = pf.getNomF().getSelectedText();
		 
		//String fam = pf.getFamilleF().getText();
		int size = modelList.getSize();
		ArrayList<Integer> tab = new ArrayList<Integer>();
		
		
		if(e != null && ts != null && tp != null && nom != null){
			for(int i = 0 ; i< size; i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts != null && tp != null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
		}else if (e == null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts == null && tp == null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoEnsol(e, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i< size; i++){
				if (AlgoTypePlante(tp, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i) && AlgoTypePlante(tp, i) ){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoEnsol(e, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e == null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<size; i++){
				if (AlgoTypeSol(ts, i) && AlgoTypePlante(tp, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}else if (e != null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<size; i++){
				if (AlgoEnsol(e, i)&& AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					tab.add(i);
				}
			}
			for(int j =0; j< tab.size();j++){
				newModelList.add(modelList.getElementAt(tab.get(j)));
			}
		}
		
		
		this.modelList = instance.getPlantes();
		
		return new JList<Plante>(newModelList);
	}
	
	public JList<Plante> algoSearch(PlantothequePanel pf){
		Ensoleillement e = (Ensoleillement) pf.getEnsoleillementComboBox().getSelectedItem();
		TypeSol ts = (TypeSol)pf.getTypeSolComboBox().getSelectedItem();
		TypePlante tp = (TypePlante)pf.getTypePlanteComboBox().getSelectedItem();
		String nom = pf.getNomF().getSelectedText();
		//String fam = pf.getFamilleF().getText();
		
		if(e != null && ts != null && tp != null && nom != null /*&& fam != null*/){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i) && AlgoTypePlante(tp, i) ){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}		}else if (e != null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoTypePlante(tp, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)&& AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					newModelList.add(modelList.getElementAt(i));
				}
			}
		}
		return new JList<Plante>(newModelList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
