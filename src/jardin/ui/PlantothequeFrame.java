package jardin.ui;

import jardin.AccesBD;
import jardin.plante.Plante;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PlantothequeFrame extends JFrame {
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	
	public PlantothequeFrame() {
		
		this.setSize(800, 600);
		this.setTitle("Plantoth�que");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridLayout grille = new GridLayout(1, 2);
		
		//Cr�ation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().getSize();
		
		listePlante = new JList<Plante>(instance.getPlantes());
		listePlante.setCellRenderer(new PlantePanel());
		//grille.add(listePlante);
		for(int i = 0; i < nbPlante; i++) {
			PlantePanel tmp = new PlantePanel();
			listePlante.add(new JScrollPane(tmp));
		}
		
		//Cr�ation de la barre de d�filement et des boutons Ajouter et Annuler.
		
		JButton ajouter = new JButton("Ajouter");
		JButton fermer = new JButton("Fermer");
		
		//Ajout des actionListener sur les boutons.
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				//R�cup�rer la zone s�lectionn�
				//ZonePlantable.setPlante(select);
			}
		});
		
		fermer.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				PlantothequeFrame.this.setVisible(false);
			}
		});;
		
		this.pack();
	}
	
	/**
	 * M�thode mettant en m�moire la plante s�lectionn�e.
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
	}
	
	public static void main(String arg[]) {
		PlantothequeFrame p = new PlantothequeFrame();
		p.setVisible(true);
	}
	
}