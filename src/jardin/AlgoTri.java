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
	private SortedListModel newModelList = new SortedListModel();
	
	
	/*
	 * m=0 : Nom
	 * m=1 : Plante
	 * m=2 : ensoleillement
	 */
	public JList<Plante> algoTri(PlantothequeFrame pf, int m){
		Plante[] p = new Plante[modelList.getSize()];
		if(m==0){
			p = new Plante[modelList.getSize()];
			for(int i =0; i< modelList.getSize();i++){
				p[i] = modelList.getElementAt(i);
			}
			for(int i =0; i<p.length -1; i++){
				if(p[i].getNom().compareTo(p[i+1].getNom()) >0){
					Plante t = p[i];
					p[i]= p[i+1];
					p[i+1]= t;
				}
			}
		}else if(m==1){
			int tmp =0;
			for(int j=0; j<3;j++){
				for(int i =0; i<p.length -1; i++){
					if(j==0 && modelList.getElementAt(i).getType() == 0){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}else if(j==1 && modelList.getElementAt(i).getType() == 1){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}else if(j==2 && modelList.getElementAt(i).getType() == 2){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}
				}
			}
		}else if (m==2){
			int tmp =0;
			for(int j=0; j<3;j++){
				for(int i =0; i<p.length -1; i++){
					if(j==0 && modelList.getElementAt(i).getEnsoleillement() == 0){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}else if(j==1 && modelList.getElementAt(i).getEnsoleillement() == 1){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}else if(j==2 && modelList.getElementAt(i).getEnsoleillement() == 2){
						p[tmp]= modelList.getElementAt(i);
						tmp++;
					}
				}
			}
		}
		for(int i =0; i< p.length;i++){
			newModelList.add(p[i]);
		}
		
		
		
		return new JList<Plante>(newModelList);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
