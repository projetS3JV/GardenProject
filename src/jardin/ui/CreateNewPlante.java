package jardin.ui;

import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sun.tools.jar.resources.jar_pt_BR;

public class CreateNewPlante extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private CardLayout cl;
	private Plante p = null;
	private String nom, nomLatin, description, imageFleurie;
	private int tailleFinale;
	private TypePlante typePlante;
	private TypeSol typeSol;
	private Ensoleillement ensoleillement;
	private boolean vivace;
	private Date debutFloraison, finFloraison;
	private Color couleurFleurie, couleurNonFleurie;
	
	private boolean finished = false;
	
	private CreateNewPlante() {
		
		super();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		panel3 = new JPanel();
		cl = new CardLayout();
		
		this.setLayout(cl);
		
		//Création du premier panel
		panel1 = new JPanel();
		JLabel name = new JLabel("Nom usuel");
		JTextField nameField = new JTextField();
		JLabel latinName = new JLabel("Nom latin");
		JTextField latinNameField = new JTextField();
		JLabel tailleFinale = new JLabel("Taille finale moyenne");
		JTextField tailleFinaleField = new JTextField();
		JLabel desc = new JLabel("Description");
		JTextArea descArea = new JTextArea();
		JPanel buttonsPanel1 = new JPanel();
		JButton annul1 = new JButton("Annuler");
		JButton next1 = new JButton("Suivant");
		buttonsPanel1.add(annul1);
		buttonsPanel1.add(next1);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		panel1.add(name);
		panel1.add(nameField);
		panel1.add(latinName);
		panel1.add(latinNameField);
		panel1.add(tailleFinale);
		panel1.add(tailleFinaleField);
		panel1.add(desc);
		panel1.add(descArea);
		panel1.add(buttonsPanel1);
		
		//Définition des listeners des boutons
		next1.addActionListener(e -> {CreateNewPlante.this.showPanel("panel2");
									  this.nom = nameField.getText();
									  this.nomLatin = latinNameField.getText();
									  this.description = descArea.getText();
									  this.tailleFinale = new Integer(tailleFinaleField.getText());
		});
		annul1.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});
		
		//Ajout du premier panel au JDialog
		this.add(panel1, "panel1");
		
		//Création du deuxième panel
		panel2 = new JPanel();
		JLabel typePlante = new JLabel("Type de plante");
		TypePlante[] typePlanteBoxItems = {TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE};
		JComboBox<TypePlante> typePlanteComboBox = new JComboBox(typePlanteBoxItems);
		JLabel typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		JComboBox<TypeSol> typeSolComboBox = new JComboBox<>(typeSolBoxItems);
		JLabel ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		JComboBox<Ensoleillement> ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		JCheckBox vivaceCheck = new JCheckBox("Vivace ?");
		JPanel buttonsPanel2 = new JPanel();
		JButton annul2 = new JButton("Annuler");
		JButton previous2 = new JButton("Retour");
		JButton next2 = new JButton("Suivant");
		buttonsPanel2.add(annul2);
		buttonsPanel2.add(previous2);
		buttonsPanel2.add(next2);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		panel2.add(typePlante);
		panel2.add(typePlanteComboBox);
		panel2.add(typeSol);
		panel2.add(typeSolComboBox);
		panel2.add(ensoleillement);
		panel2.add(ensoleillementComboBox);
		panel2.add(vivaceCheck);
		panel2.add(buttonsPanel2);
		
		//Définition des listeners des boutons
		next2.addActionListener(e -> {CreateNewPlante.this.showPanel("panel3");
									  this.typePlante = (TypePlante) typePlanteComboBox.getSelectedItem();
									  this.typeSol = (TypeSol) typeSolComboBox.getSelectedItem();
									  this.ensoleillement = (Ensoleillement) ensoleillementComboBox.getSelectedItem();
									  this.vivace = vivaceCheck.isSelected();
		});
		previous2.addActionListener(e -> CreateNewPlante.this.showPanel("panel1"));
		annul2.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});
		
		//Ajout du deuxième panel au JDialog
		this.add(panel2, "panel2");
		
		//Création du troisième panel
		panel3 = new JPanel();
		JLabel dateFloraison = new JLabel("Date de floraison");
		JPanel sousPanel1 = new JPanel();
		JTextField dateFloraisonField1 = new JTextField("jj/mm");
		JTextField dateFloraisonField2 = new JTextField("jj/mm");
		JButton ajouterDatesFloraisons = new JButton("Ajouter");
		sousPanel1.add(dateFloraisonField1);
		sousPanel1.add(dateFloraisonField2);
		sousPanel1.add(ajouterDatesFloraisons);
		JLabel imageFleurie = new JLabel("Image de la plante lorsqu'elle est en fleur");
		JPanel sousPanel2 = new JPanel();
		JTextField imageFleurieField = new JTextField(10);
		JFileChooser fileChooser = new JFileChooser();
		JButton fetch = new JButton("Parcourir");
		sousPanel2.add(imageFleurieField);
		sousPanel2.add(fetch);
		JPanel sousPanel3 = new JPanel();
		JButton couleurPasFleurieButton = new JButton("Couleur non-fleurie");
		JButton couleurFleurieButton = new JButton("Couleur fleurie");
		sousPanel3.add(couleurPasFleurieButton);
		sousPanel3.add(couleurFleurieButton);
		JPanel buttonsPanel3 = new JPanel();
		JButton annul3 = new JButton("Annuler");
		JButton previous3 = new JButton("Retour");
		JButton finish = new JButton("Terminer");
		buttonsPanel3.add(annul3);
		buttonsPanel3.add(previous3);
		buttonsPanel3.add(finish);
		
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		
		panel3.add(dateFloraison);
		panel3.add(sousPanel1);
		panel3.add(imageFleurie);
		panel3.add(sousPanel2);
		panel3.add(sousPanel3);
		panel3.add(buttonsPanel3);
		
		//Définition des listeners des boutons
		fetch.addActionListener(e -> fileChooser.showOpenDialog(panel3));
		couleurPasFleurieButton.addActionListener(e -> JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante non-fleurie", Color.WHITE));
		couleurFleurieButton.addActionListener(e -> JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante fleurie", Color.WHITE));
		previous3.addActionListener(e -> CreateNewPlante.this.showPanel("panel2"));
		annul3.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});
		finish.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
			this.finished = true;
			//this.debutFloraison = GregorianCalendar.getInstance().
		});
		
		//Ajout du troisième panel au JDialog		
		this.add(panel3, "panel3");
		
	}
	
	private void showPanel(String panel) {
		this.cl.show(this.getContentPane(), panel);
	}
	
	/**
	 * Crée une plante et la retourne. Elle est nulle si on a annuler
	 * @return la plante ou null
	 */
	public static Plante showCreateNewPlante() {
		CreateNewPlante cnp = new CreateNewPlante();
		cnp.showPanel("panel1");
		cnp.setVisible(true);
		if(cnp.finished) {
		//	cnp.p.set;
		}
		return cnp.p;
	}
	
	public static void main(String[] args) {
		CreateNewPlante c = new CreateNewPlante();
		c.showPanel("panel1");
		c.setVisible(true);
	}
}
