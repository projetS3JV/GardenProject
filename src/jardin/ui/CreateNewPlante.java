package jardin.ui;

import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.TypePlante;

import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateNewPlante extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private CardLayout cl;
	
	public CreateNewPlante() {
		
		super();
	
		panel3 = new JPanel();
		cl = new CardLayout();
		
		this.setLayout(cl);
		
		//Création du premier panel
		panel1 = new JPanel();
		JLabel name = new JLabel("Nom usuel");
		JTextField nameField = new JTextField();
		JLabel latinName = new JLabel("Nom latin");
		JTextField latinNameField = new JTextField();
		JLabel desc = new JLabel("Description");
		JTextArea descArea = new JTextArea();
		JButton annul = new JButton("Annuler");
		JButton next = new JButton("Suivant");
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		panel1.add(name);
		panel1.add(nameField);
		panel1.add(latinName);
		panel1.add(latinNameField);
		panel1.add(desc);
		panel1.add(descArea);
		panel1.add(annul);
		panel1.add(next);
		
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
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		panel2.add(typePlante);
		panel2.add(typePlanteComboBox);
		panel2.add(typeSol);
		panel2.add(typeSolComboBox);
		panel2.add(ensoleillement);
		panel2.add(ensoleillementComboBox);
		panel2.add(vivaceCheck);
		
		//Ajout du deuxième panel au JDialog
		this.add(panel2, "panel2");
		
		//Création du troisième panel
		panel3 = new JPanel();
		JLabel dateFloraison = new JLabel("Date de floraison");
		
	}
	
	public void showPanel(String panel) {
		this.cl.show(this.getContentPane(), panel);
	}
	
	public static void main(String[] args) {
		CreateNewPlante c = new CreateNewPlante();
		c.setSize(200, 200);
		c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.showPanel("panel2");
		c.setVisible(true);
	}
}
