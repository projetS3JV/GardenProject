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
import java.util.List;

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
	private JLabel  rechercher, nom, vide, famille, typePlante, typeSol, ensoleillement;
	private JTextField nomF, familleF;
	private JComboBox<TypePlante> typePlanteComboBox;
	private JComboBox<TypeSol> typeSolComboBox;
	private JComboBox<Ensoleillement> ensoleillementComboBox;
	
	public PlantothequeFrame() {
		this.setResizable(false);
		this.setSize(800, 500);
		this.setTitle("Plantotheque");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		//Creation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		listePlante = new JList<Plante>(modelList);
		SortedListModel.sortedByName((List)listePlante);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante), BorderLayout.CENTER);

		
		//Creation de la barre de defilement et des boutons Ajouter et Annuler.
		rechercher.setText("Rechercher :");
		
		nom.setText("Nom :");
		
		famille.setText("Famille :");
		
		typePlante.setText("Type de plante");
		TypePlante[] typePlanteBoxItems = {TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE};
		typePlanteComboBox = new JComboBox(typePlanteBoxItems);
		
		typeSol.setText("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		typeSolComboBox = new JComboBox<>(typeSolBoxItems);
		//Copie de la Zone Plantable sélectionnée pour avoir accès à ses attributs.
		ZonePlantable tmp = (ZonePlantable)JardinPanel.getSelected();
		typeSolComboBox.setSelectedIndex(tmp.getTypeSol());
		
		ensoleillement.setText("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		ensoleillementComboBox.setSelectedIndex(tmp.getEnsoleillement());
		
		
		//Panel contenant les outils de recherche
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(9, 2, 5, 5));
		form.add(rechercher);
		form.add(vide);
		form.add(nom);
		form.add(nomF);
		form.add(famille);
		form.add(familleF);
		form.add(typePlante); form.add(typePlanteComboBox);
		form.add(typeSol); form.add(typeSolComboBox);
		form.add(ensoleillement); form.add(ensoleillementComboBox);
		
		JButton fermer = new JButton("Fermer");
		form.add(fermer);
		JButton ajouterNewPlante = new JButton("Créer une plante");
		form.add(ajouterNewPlante);
		
		this.add(form, BorderLayout.SOUTH);
		
		//Ajout des actionListener sur les boutons.
		
		fermer.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				PlantothequeFrame.this.setVisible(false);
			}
		});
		
		ajouterNewPlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plante p = CreateNewPlante.showCreateNewPlante();
				if (p != null) {
					AccesBD.getInstance().insertPlante(p);
				}
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

	public JTextField getNomF() {
		return nomF;
	}

	public void setNomF(JTextField nomF) {
		this.nomF = nomF;
	}

	public JTextField getFamilleF() {
		return familleF;
	}

	public void setFamilleF(JTextField familleF) {
		this.familleF = familleF;
	}

	public JComboBox<TypePlante> getTypePlanteComboBox() {
		return typePlanteComboBox;
	}

	public void setTypePlanteComboBox(JComboBox<TypePlante> typePlanteComboBox) {
		this.typePlanteComboBox = typePlanteComboBox;
	}

	public JComboBox<TypeSol> getTypeSolComboBox() {
		return typeSolComboBox;
	}

	public void setTypeSolComboBox(JComboBox<TypeSol> typeSolComboBox) {
		this.typeSolComboBox = typeSolComboBox;
	}

	public JComboBox<Ensoleillement> getEnsoleillementComboBox() {
		return ensoleillementComboBox;
	}

	public void setEnsoleillementComboBox(
			JComboBox<Ensoleillement> ensoleillementComboBox) {
		this.ensoleillementComboBox = ensoleillementComboBox;
	}
	
	public static void main(String arg[]) {
		PlantothequeFrame p = new PlantothequeFrame();
		p.setVisible(true);
	}
	
}
