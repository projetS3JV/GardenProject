package jardin.ui;

import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
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
	private Plante p = null;
	
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
		JLabel desc = new JLabel("Description");
		JTextArea descArea = new JTextArea();
		JButton annul1 = new JButton("Annuler");
		JButton next1 = new JButton("Suivant");
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		panel1.add(name);
		panel1.add(nameField);
		panel1.add(latinName);
		panel1.add(latinNameField);
		panel1.add(desc);
		panel1.add(descArea);
		panel1.add(annul1);
		panel1.add(next1);
		
		next1.addActionListener(e -> CreateNewPlante.this.showPanel("panel2"));
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
		JButton annul2 = new JButton("Annuler");
		JButton previous2 = new JButton("Retour");
		JButton next2 = new JButton("Suivant");
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		panel2.add(typePlante);
		panel2.add(typePlanteComboBox);
		panel2.add(typeSol);
		panel2.add(typeSolComboBox);
		panel2.add(ensoleillement);
		panel2.add(ensoleillementComboBox);
		panel2.add(vivaceCheck);
		panel2.add(next2);
		panel2.add(previous2);
		panel2.add(annul2);
		
		next2.addActionListener(e -> CreateNewPlante.this.showPanel("panel3"));
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
		CreateNewPlante np = new CreateNewPlante();
		return np.p;
	}
	
	public static void main(String[] args) {
		CreateNewPlante c = new CreateNewPlante();
		c.showPanel("panel1");
		c.setVisible(true);
	}
}
