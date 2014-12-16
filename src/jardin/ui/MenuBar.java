package jardin.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
 * Dans ce contructeur le code se présente de la façon suivante pour chaque menu de la barre
 * 1-création d'un JMenu
 * 2-création d'un JMenuItem
 * 3-ajout d'un ActionListener au JMenuItem
 * 4-(facultatif) ajout d'un raccourci clavier 
 * répétions des étapes 2 à 4 autant de fois que nécessaire
 * 5-ajout des JMenuItem au JMenu dans l'ordre de création avec des JSeparator si besoin
 * 6-ajout du JMenu à MenuBar
 */
	public MenuBar(){
		super();
		//Sous-menu fichier
		JMenu fichier = new JMenu("Fichier");
		JMenuItem enregistrer = new JMenuItem("Enregistrer");
		enregistrer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode enregistrement
				
			}
		});
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		JMenuItem ouvrirJardin = new JMenuItem("Ouvrir un jardin");
		ouvrirJardin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode ouverture de jardin
			}
		});
		ouvrirJardin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		JMenuItem importImage = new JMenuItem("Importer un fond");
		importImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int reponse = fileChooser.showOpenDialog(MenuBar.this);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
					//lancer méthode pour changer la fond du jardin par l'image fileChooser.getSelectedFile();
				}
			}
		});
		fichier.add(enregistrer);
		fichier.add(new JSeparator());
		fichier.add(ouvrirJardin);
		fichier.add(importImage);
		this.add(fichier);

		//Sous-menu edition
		JMenu edition = new JMenu("Edition");
		JMenuItem annuler = new JMenuItem("Annuler");
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode annulement de la dernière action
			}
		});
		annuler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

		JMenuItem refaire = new JMenuItem("Refaire");
		refaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode pour refaire la dernière action annulée
			}
		});
		refaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

		JMenuItem selectionnerTout = new JMenuItem("Selectionner tout");
		selectionnerTout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode pour sélectionner toutes les zones
			}
		});
		selectionnerTout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));


		edition.add(annuler);
		edition.add(refaire);
		edition.add(selectionnerTout);
		this.add(edition);

		//Sous-menu outil
		JMenu outils = new JMenu("Outils");
		JMenuItem tracer = new JMenuItem("Tracer zone");
		tracer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode traçage de zone
			}
		});
		tracer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

		
		JMenuItem supprimer = new JMenuItem("Supprimer zone");
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode suppression de zone
			}
		});
		supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));


		outils.add(tracer);
		outils.add(supprimer);
		this.add(outils);
		//Sous-menu préférences
		JMenu preferences = new JMenu("Préférences");
		JMenuItem affichage = new JMenuItem("Paramètres d'affichage");
		affichage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode qui affiche la fenêtre des paramètres d'affichages
			}
		});
		
		JMenuItem chercherMaJ = new JMenuItem("Chercher des mises à jours ");

		preferences.add(affichage);
		preferences.add(new JSeparator());
		preferences.add(chercherMaJ);
		this.add(preferences);

		//Sous-menu plantes
		JMenu plantes = new JMenu("Plantes");
		JMenuItem planthotheque = new JMenuItem("Voir la planthothèque");
		planthotheque.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode qui affiche la fenêtre de la planthothèque
			}
		});
		
		JMenuItem creerPlante = new JMenuItem("Créer une plante");
		creerPlante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode qui affiche la fenêtre des paramètres d'affichages
			}
		});
	

		plantes.add(planthotheque);
		plantes.add(creerPlante);
		this.add(plantes);
	}
}
