package jardin.ui;


import jardin.AccesBD;
import jardin.Jardin;
import jardin.plante.Plante;

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
	private JMenuItem enregistrer;
	private JMenuItem annuler;
	private JMenuItem refaire;
	private JMenuItem selectionnerTout;
	private JMenuItem tracer;
	private JMenuItem supprimer;

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
		
		JMenuItem nouveau = new JMenuItem("Nouveau");
		nouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String [] values  = InputNewJardin.showInputNewJardin(MainFrame.getInstance());
				//lancer méthode création de nouveau jardin
				if (values != null) {
					MainFrame.getInstance().getJardinPanel().setJardin(new Jardin(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2])));
					MenuBar.this.enableItems();
				}
			}
		});
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		
		enregistrer = new JMenuItem("Enregistrer");
		enregistrer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode enregistrement
				 Jardin jardin = MainFrame.getInstance().getJardinPanel().getJardin();
				 if (jardin == null) {
					 JOptionPane.showMessageDialog(MenuBar.this,"Aucun jardin à enregistrer","Jardin inexistant", JOptionPane.WARNING_MESSAGE);
				 } else {
					 try {
						 //Si le jardin existe on le mets a jour
						 AccesBD.getInstance().updateJardin(jardin);
					 } catch (IllegalArgumentException exception) {
						 //Sinon on l'ajoute
						 AccesBD.getInstance().insertJardin(jardin);
					 }					 
				 }
			}
		});
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		JMenuItem ouvrirJardin = new JMenuItem("Ouvrir un jardin");
		ouvrirJardin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode ouverture de jardin
				int jardin = OpenGardenFrame.showOpenGardenFrame(MainFrame.getInstance());
				if (jardin != -1) {				
					MainFrame.getInstance().getJardinPanel().setJardin(AccesBD.getInstance().getJardin(jardin));
					MenuBar.this.enableItems();
				}
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
					//lancer méthode pour changer le fond du jardin par l'image fileChooser.getSelectedFile();
				}
			}
		});
		
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(e -> {
			MainFrame.getInstance().dispose();
		});
		
		fichier.add(nouveau);
		fichier.add(enregistrer);
		fichier.add(new JSeparator());
		fichier.add(ouvrirJardin);
		fichier.add(importImage);
		fichier.add(new JSeparator());
		fichier.add(quitter);
		this.add(fichier);

		//Sous-menu edition
		JMenu edition = new JMenu("Edition");
		annuler = new JMenuItem("Annuler");
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode annulement de la dernière action
			}
		});
		annuler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

		refaire = new JMenuItem("Refaire");
		refaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuBar.this,"Action non implémentée pour l'instant","Erreur", JOptionPane.ERROR_MESSAGE); // ligne à supprimer après implémentation de la méthode
				//lancer méthode pour refaire la dernière action annulée
			}
		});
		refaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

		selectionnerTout = new JMenuItem("Selectionner tout");
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
		tracer = new JMenuItem("Tracer zone");
		tracer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode traçage de zone
				MainFrame.getInstance().getJardinPanel().startDrawing();
			}
		});
		tracer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

		
		supprimer = new JMenuItem("Supprimer zone");
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
				//lancer méthode qui affiche la fenêtre de la planthothèque
				PlantothequeFrame plantoframe = new PlantothequeFrame();
				plantoframe.setVisible(true);
			}
		});
		
		JMenuItem creerPlante = new JMenuItem("Créer une plante");
		creerPlante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode qui affiche la fenêtre des paramètres d'affichages
				Plante p = CreateNewPlante.showCreateNewPlante();
				if (p != null) {
					AccesBD.getInstance().insertPlante(p);
				}
			}
		});
	

		plantes.add(planthotheque);
		plantes.add(creerPlante);
		this.add(plantes);
		
		//desactivation des item de menu a ne pas utiliser sans jardin existant
		this.annuler.setEnabled(false);
		this.enregistrer.setEnabled(false);
		this.refaire.setEnabled(false);
		this.selectionnerTout.setEnabled(false);
		this.supprimer.setEnabled(false);
		this.tracer.setEnabled(false);
	}
	
	/**
	 * Methode pour pouvoir utiliser les items de menus
	 */
	private void enableItems() {
		this.annuler.setEnabled(true);
		this.enregistrer.setEnabled(true);
		this.refaire.setEnabled(true);
		this.selectionnerTout.setEnabled(true);
		this.supprimer.setEnabled(true);
		this.tracer.setEnabled(true);
	}
}
