package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.SortedListModel;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PlantothequePanel extends JPanel {
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	private SortedListModel modelList = instance.getPlantes();
	
	public PlantothequePanel() {
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());
		
		
		//Création de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().getSize();
		
		Plante p = new Plante(10, null,null,null, Color.blue, Color.black, true,
				"popol", "popolus patatus", new ImageIcon("res/Img/test.png"), TypePlante.FLEUR,
				Ensoleillement.SOLEIL, TypeSol.LIMONEUX,
				"c'est une zolie fleur");
		modelList.add(p);
		
		listePlante = new JList<Plante>(modelList);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante));

		
		//Création des boutons Ajouter et Annuler.
		JButton ajouter = new JButton("Ajouter");
		this.add(ajouter, BorderLayout.SOUTH);
		
		//Ajout des actionListener sur les boutons.
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				//Récupérer la zone sélectionné
				//ZonePlantable.setPlante(select);
			}
		});
	}
	
	/**
	 * Méthode mettant en mémoire la plante sélectionnée.
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
	}
	
	public static void main(String arg[]) {
		PlantothequePanel p = new PlantothequePanel();
		p.setVisible(true);
	}
	
}