package jardin.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	
	public MenuPanel(){
		super();
		JMenuBar menubar = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenuItem enregistrer = new JMenuItem("Enregistrer");
	}
}
/*
fichier 
enregistrer
enregister sous
import jardin
import image

edition
annuler
refaire
selectionner tout


outil
tracer zone 
zupprimer zoone
add plante
change plante
supprimer plante

preference
langue
param d'affichage
taiolle de l'interface
affficher barres d'outil
afficher barres des mois

plantes
voir la planthotheque
créer plante
*/