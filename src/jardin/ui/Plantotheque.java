package jardin.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jardin.AccesBD;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class Plantotheque extends JFrame {
	JFrame planto;
	AccesBD instance = AccesBD.getInstance();
	
	public Plantotheque() {
		planto = new JFrame();
		
		//Création de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().size();
		GridLayout grillePlantes = new GridLayout((nbPlante/2) + 1, 2, 20, 10);
		
		//Remplissage du gridLayout avec les instance de PlantePanel.
		for(int i = 0; i < nbPlante; i++) {
			PlantePanel tmpi = new PlantePanel(i);
			grillePlantes.addLayoutComponent(instance.getPlante(i).getNom(), tmpi);
		}
		
		//Création de la barre de défilement et des boutons Ajouter et Annuler.
		JScrollBar scrollbare = new JScrollBar();
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		//Ajout des actionListener sur les boutons.
		/*
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				instance.insertPlante(instance.getPlante(id));
			}
		});
		*/
		annuler.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				Plantotheque.this.planto.setVisible(false);
			}
		});;
	}
}