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

public class PlantothequeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	private SortedListModel modelList = instance.getPlantes();
	
	public PlantothequeFrame() {
		this.setResizable(false);
		this.setSize(800, 600);
		this.setTitle("Plantothèque");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		//Cr�ation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().getSize();
		
		Plante p = new Plante(10, null,null,null, Color.blue, Color.black, true,
				"popol", "popolus patatus", new ImageIcon("res/Img/test.png"), TypePlante.FLEUR,
				Ensoleillement.SOLEIL, TypeSol.LIMONEUX,
				"c'est une zolie fleur");
		modelList.add(p);
		
		listePlante = new JList<Plante>(modelList);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante));

		
		//Cr�ation de la barre de d�filement et des boutons Ajouter et Annuler.
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
				//R�cup�rer la zone s�lectionn�
				//ZonePlantable.setPlante(select);
			}
		});
		
		fermer.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				PlantothequeFrame.this.setVisible(false);
			}
		});
		
		//this.pack();
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