package jardin;

import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.ui.PlantothequeFrame;
import jardin.ui.PlantothequePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlgoSearch implements ActionListener{
	private AccesBD instance = AccesBD.getInstance();
	private SortedListModel modelList = instance.getPlantes();
	private ArrayList<Plante> listePlante;
	private PlantothequePanel pn;
	private PlantothequeFrame pf;
	
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
	
	public ArrayList algoSearch(PlantothequeFrame pf){
		Ensoleillement e = (Ensoleillement) pf.getEnsoleillementComboBox().getSelectedItem();
		TypeSol ts = (TypeSol)pf.getTypeSolComboBox().getSelectedItem();
		TypePlante tp = (TypePlante)pf.getTypePlanteComboBox().getSelectedItem();
		String nom = pf.getNomF().getSelectedText();
		//String fam = pf.getFamilleF().getText();
		
		if(e != null && ts != null && tp != null && nom != null /*&& fam != null*/){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i) && AlgoTypePlante(tp, i) ){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}		}else if (e != null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)&& AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}
		return this.listePlante;
	}
	
	public ArrayList algoSearch(PlantothequePanel pf){
		Ensoleillement e = (Ensoleillement) pf.getEnsoleillementComboBox().getSelectedItem();
		TypeSol ts = (TypeSol)pf.getTypeSolComboBox().getSelectedItem();
		TypePlante tp = (TypePlante)pf.getTypePlanteComboBox().getSelectedItem();
		String nom = pf.getNomF().getSelectedText();
		//String fam = pf.getFamilleF().getText();
		
		if(e != null && ts != null && tp != null && nom != null /*&& fam != null*/){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)  && AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp == null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoEnsol(e, i) && AlgoTypePlante(tp, i) ){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}		}else if (e != null && ts != null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp == null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e == null && ts != null && tp != null && nom == null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoTypeSol(ts, i) && AlgoTypePlante(tp, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}else if (e != null && ts == null && tp != null && nom != null){
			for(int i = 0 ; i<modelList.getSize(); i++){
				if (AlgoEnsol(e, i)&& AlgoTypePlante(tp, i) && AlgoNom(nom, i)){
					listePlante.add(modelList.getElementAt(i));
				}
			}
		}
		return this.listePlante;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
