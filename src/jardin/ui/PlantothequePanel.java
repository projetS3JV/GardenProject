package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.SortedListModel;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.ZonePlantable;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	private Plante select = null;
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
		listePlante.setCellRenderer(new PlantePanel());
		this.add(new JScrollPane(this.listePlante));

		JLabel research = new JLabel("Rechercher :");

		JLabel nom = new JLabel("Nom :");
		JTextField nomF = new JTextField();

		JLabel vide = new JLabel();

		JLabel famille = new JLabel("Famille :");
		JTextField familleF = new JTextField();

		JLabel typePlante = new JLabel("Type de plante");
		TypePlante[] typePlanteBoxItems = {TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE, null};
		JComboBox<TypePlante> typePlanteComboBox = new JComboBox(typePlanteBoxItems);
		typePlanteComboBox.setSelectedItem(null);

		//Concernant le type de sol
		typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX, null};
		typeSolComboBox = new JComboBox(typeSolBoxItems);
		typeSolComboBox.setSelectedItem(null);

		//Concernant l'ensoleillement
		ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL, null};
		ensoleillementComboBox = new JComboBox(ensoleillementBoxItems);
		ensoleillementComboBox.setSelectedItem(null);

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
		JButton recherButton = new JButton("Rechercher");
		form.add(recherButton);

		this.add(form, BorderLayout.SOUTH);
	}

	/**
	 * Methode ettant en memoire la plante si selectionne
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
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