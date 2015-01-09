package jardin.ui;

import jardin.AccesBD;
import jardin.SortedListModel;
import jardin.plante.Plante;
import jardin.zone.AbstractZone;
import jardin.zone.ZonePlantable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PlantothequePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	private SortedListModel modelList = instance.getPlantes();
	
	public PlantothequePanel() {
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		
		
		//Creation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		listePlante = new JList<Plante>(modelList);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante));

		
		//Creation de la barre de defilement et des boutons Ajouter et Annuler.
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JButton ajouter = new JButton("Ajouter");
		JButton fermer = new JButton("Fermer");
		buttons.add(ajouter);
		buttons.add(fermer);
		this.add(buttons, BorderLayout.SOUTH);
		
		//Ajout des actionListener sur les boutons.
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				AbstractZone zone = MainFrame.getInstance().getJardinPanel().getSelected();
				((ZonePlantable) zone).setPlante(select);
			}
		});
		
		fermer.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				PlantothequePanel.this.setVisible(false);
			}
		});
		
		//this.pack();
	}
	
	/**
	 * Methode ettant en memoire la plante si selectionne
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
	}
	
	public static void main(String arg[]) {
		JFrame frame = new JFrame();
		PlantothequePanel p = new PlantothequePanel();
		frame.add(p);
		p.setVisible(true);
	}
	
}