package jardin.ui;

import jardin.AccesBD;
import jardin.plante.Plante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PlantothequePanel extends JPanel {
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	
	public PlantothequePanel() {
		
		//Création de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().getSize();
		
		listePlante = new JList<Plante>(instance.getPlantes());
		listePlante.setCellRenderer(new PlantePanel());
		for(int i = 0; i < nbPlante; i++) {
			PlantePanel tmp = new PlantePanel();
			listePlante.add(new JScrollPane(tmp));
		}
		
		//Création de la barre de défilement et des boutons Ajouter et Annuler.
		
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		//Ajout des actionListener sur les boutons.
		
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				//Récupérer la zone sélectionné
				//ZonePlantable.setPlante(select);
			}
		});
		
		annuler.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
			}
		});;
	}
	
	/**
	 * Méthode mettant en mémoire la plante sélectionnée.
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
	}
	
	public static void main(String arg[]) {
		PlantothequePanel p = new PlantothequePanel();
	}
	
}