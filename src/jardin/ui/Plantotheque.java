package jardin.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jardin.AccesBD;
import jardin.plante.Plante;
import jardin.zone.ZonePlantable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class Plantotheque extends JFrame {
	AccesBD instance = AccesBD.getInstance();
	Plante select = null;
	
	public Plantotheque() {
		
		//Création de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().size();
		GridLayout grillePlantes = new GridLayout((nbPlante/2) + 1, 2, 20, 10);
		this.setLayout(grillePlantes);
		
		JList listePlante = new JList<PlantePanel>();
		for(int i = 0; i < nbPlante; i++) {
			PlantePanel tmp = new PlantePanel(i);
			listePlante.add(tmp);
			//grillePlantes.addLayoutComponent(instance.getPlante(i).getNom(), tmp);
		}
		
		//Création de la barre de défilement et des boutons Ajouter et Annuler.
		JScrollBar scrollbare = new JScrollBar();
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		//Ajout des actionListener sur les boutons.
		
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				ZonePlantable.setPlante(select);
			}
		});
		
		annuler.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				Plantotheque.this.setVisible(false);
			}
		});;
	}
}