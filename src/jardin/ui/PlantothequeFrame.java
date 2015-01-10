package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.SortedListModel;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.AbstractZone;
import jardin.zone.ZonePlantable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PlantothequeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	private SortedListModel modelList = instance.getPlantes();
	
	public PlantothequeFrame() {
		this.setResizable(false);
		this.setSize(400, 400);
		this.setTitle("Plantotheque");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		//Creation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		listePlante = new JList<Plante>(modelList);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante));

		
		//Creation de la barre de defilement et des boutons Ajouter et Annuler.
		JLabel research = new JLabel("Rechercher :");
		
		JLabel nom = new JLabel("Nom :");
		JTextField nomF = new JTextField();
		
		JLabel vide = new JLabel();
		
		JLabel famille = new JLabel("Famille :");
		JTextField familleF = new JTextField();
		
		JLabel typePlante = new JLabel("Type de plante");
		TypePlante[] typePlanteBoxItems = {TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE};
		JComboBox<TypePlante> typePlanteComboBox = new JComboBox(typePlanteBoxItems);
		
		JLabel typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		JComboBox<TypeSol> typeSolComboBox = new JComboBox<>(typeSolBoxItems);
		
		JLabel ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		JComboBox<Ensoleillement> ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(9, 2, 5, 5));
		form.add(research);
		form.add(vide);
		form.add(nom);
		form.add(nomF);
		form.add(famille);
		form.add(familleF);
		form.add(typePlante); form.add(typePlanteComboBox);
		form.add(typeSol); form.add(typeSolComboBox);
		form.add(ensoleillement); form.add(ensoleillementComboBox);
		
		JButton ajouter = new JButton("Ajouter");
		JButton fermer = new JButton("Fermer");
		form.add(ajouter);
		form.add(fermer);
		
		this.add(form, BorderLayout.SOUTH);
		
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
				PlantothequeFrame.this.setVisible(false);
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
		PlantothequeFrame p = new PlantothequeFrame();
		p.setVisible(true);
	}
	
}