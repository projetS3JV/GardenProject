package jardin.ui;

import jardin.AccesBD;
import jardin.AlgoSearch;
import jardin.Ensoleillement;
import jardin.SortedListModel;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.ZonePlantable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PlantothequePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private AccesBD instance = AccesBD.getInstance();
	private static Plante selectedPlante = null;
	private JList listePlante;
	private SortedListModel modelList = instance.getPlantes();
	private JLabel  rechercher, nom, vide, famille, typePlante, typeSol, ensoleillement;
	private JTextField nomF, familleF;
	private JComboBox<TypePlante> typePlanteComboBox;
	private JComboBox<TypeSol> typeSolComboBox;
	private JComboBox<Ensoleillement> ensoleillementComboBox;

	public PlantothequePanel() {
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());

		//Creation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		listePlante = new JList<Plante>(modelList);
		//SortedListModel.sortedByName((List)listePlante);
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante), BorderLayout.WEST);

		JLabel rechercher = new JLabel("Rechercher :");

		JLabel nom = new JLabel("Nom :");
		JTextField nomF = new JTextField();

		JLabel vide = new JLabel();

		JLabel famille = new JLabel("Famille :");
		JTextField familleF = new JTextField();

		JLabel typePlante = new JLabel("Type de plante");
		TypePlante[] typePlanteBoxItems = {null, TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE};
		JComboBox<TypePlante> typePlanteComboBox = new JComboBox(typePlanteBoxItems);
		typePlanteComboBox.setSelectedItem(null);

		//Concernant le type de sol
		typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {null, TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		typeSolComboBox = new JComboBox(typeSolBoxItems);
		typeSolComboBox.setSelectedItem(null);

		//Concernant l'ensoleillement
		ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {null, Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		ensoleillementComboBox = new JComboBox(ensoleillementBoxItems);
		ensoleillementComboBox.setSelectedItem(null);

		JPanel form = new JPanel();
		form.setLayout(new GridLayout(9, 2, 5, 5));
		//Bouton de création de plante
		JButton creerNewPlante = new JButton("Créer une plante");
		form.add(creerNewPlante);
		//Bouton d'affichage de fiche
		JButton afficherFiche = new JButton("Fiche de la plante");
		form.add(afficherFiche);
		//Bouton de recherche
		JButton recherButton = new JButton("Rechercher");
		recherButton.addActionListener(new AlgoSearch() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				JList<Plante> algo = new JList<Plante>();
				algo = algoSearch(PlantothequePanel.this);
				listePlante = null;
				PlantothequePanel.this.update(getGraphics());
				listePlante = algo;
				PlantothequePanel.this.update(getGraphics());
				listePlante.setCellRenderer(new PlantePanel());
				PlantothequePanel.this.update(getGraphics());
			}
		});
		
		JButton ajouter = new JButton("Ajouter plante");
		//Ajout des actionListener sur les boutons.
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				((ZonePlantable) JardinPanel.getSelected()).setPlante(selectedPlante);
			}
		});
		
		creerNewPlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plante p = CreateNewPlante.showCreateNewPlante();
				if (p != null) {
					AccesBD.getInstance().insertPlante(p);
				}
			}
		});
		
		afficherFiche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedPlante != null) {
					InfoPlante info = new InfoPlante(selectedPlante);
					info.setAlwaysOnTop(true);
					info.setSize(500, 400);
					info.setVisible(true);
				}
			}
		});
		
		
		//Champs de recherche
		form.add(rechercher);
		form.add(recherButton);
		form.add(nom);
		form.add(nomF);
		form.add(famille);
		form.add(familleF);
		form.add(typePlante); form.add(typePlanteComboBox);
		form.add(typeSol); form.add(typeSolComboBox);
		form.add(ensoleillement); form.add(ensoleillementComboBox);
		form.add(ajouter);

		this.add(form, BorderLayout.SOUTH);


	}



	public JLabel getTypePlante() {
		return typePlante;
	}

	public void setTypePlante(JLabel typePlante) {
		this.typePlante = typePlante;
	}

	public JLabel getTypeSol() {
		return typeSol;
	}

	public void setTypeSol(JLabel typeSol) {
		this.typeSol = typeSol;
	}

	public JLabel getEnsoleillement() {
		return ensoleillement;
	}

	public void setEnsoleillement(JLabel ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	public JTextField getNomF() {
		return nomF;
	}

	public void setNomF(JTextField nomF) {
		this.nomF = nomF;
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

	public void setEnsoleillementComboBox(JComboBox<Ensoleillement> ensoleillementComboBox) {
		this.ensoleillementComboBox = ensoleillementComboBox;
	}

	/**
	 * Methode mettant en memoire la plante si selectionne
	 */
	public static void setSelected(PlantePanel plantePanel, Plante p) {
		//selectedPlante = (Plante) this.listePlante.getSelectedValue();
		selectedPlante = p;
	}

	public Plante getSelected() {
		return (Plante) this.listePlante.getSelectedValue();
	}

	public static void main(String arg[]) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(400, 500);
		frame.setTitle("Plantotheque");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		PlantothequePanel p = new PlantothequePanel();
		frame.add(p);
		frame.setVisible(true);
	}

}