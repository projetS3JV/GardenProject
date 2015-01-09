package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		this.setSize(500, 300);
		this.setTitle("Nouvelle plante");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		panel3 = new JPanel();
		cl = new CardLayout();
		
		this.setLayout(cl);
		
		//Création du premier panel
		panel1 = new JPanel();
		JLabel name = new JLabel("Nom usuel");
		JTextField nameField = new JTextField();
		//JFormattedTextField nameField1 = new JFormattedTextField(format)
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
		next1.addActionListener(e -> {    this.nom = nameField.getText();
										  this.nomLatin = latinNameField.getText();
										  this.description = descArea.getText();
										  if(tailleFinaleField.getText().isEmpty()){
											  this.tailleFinale = 0;
										  }else{
											  this.tailleFinale = Integer.parseInt(tailleFinaleField.getText());
										  }
										  CreateNewPlante.this.showPanel("panel2");
									  }									  
		);
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
									  try {
										  this.typePlante = (TypePlante) typePlanteComboBox.getSelectedItem();
										  this.typeSol = (TypeSol) typeSolComboBox.getSelectedItem();
										  this.ensoleillement = (Ensoleillement) ensoleillementComboBox.getSelectedItem();
									  }catch(Exception e3){
										  e3.printStackTrace();
									  }
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
		JLabel datesFloraison = new JLabel("Date de floraison");
		JPanel sousPanel1 = new JPanel();
		JLabel dateDebutFloraison = new JLabel("Date de début de la floraison"); 
		JTextField dateFloraisonField1 = new JTextField("jj/mm");
		dateFloraisonField1.setColumns(4);
		JPanel sousPanel4 = new JPanel();
		JLabel dateFinFloraison = new JLabel("Date de fin de la floraison"); 
		JTextField dateFloraisonField2 = new JTextField("jj/mm");
		dateFloraisonField2.setColumns(4);
		//Bouton pour ajouter une date à la liste
		//JButton ajouterDatesFloraisons = new JButton("Ajouter");
		sousPanel1.add(dateDebutFloraison);
		sousPanel1.add(dateFloraisonField1);
		sousPanel4.add(dateFinFloraison);
		sousPanel4.add(dateFloraisonField2);
		//Ajout du bouton pour ajouter une date à la liste au sous panel
		//sousPanel1.add(ajouterDatesFloraisons);
		JLabel imageFleurie = new JLabel("Image de la plante lorsqu'elle est en fleur");
		JPanel sousPanel2 = new JPanel();
		JTextField imageFleurieField = new JTextField(10);
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
		
		panel3.add(datesFloraison);
		panel3.add(sousPanel1);
		panel3.add(sousPanel4);
		panel3.add(imageFleurie);
		panel3.add(sousPanel2);
		panel3.add(sousPanel3);
		panel3.add(buttonsPanel3);
		
		//Définition des listeners des boutons
		dateFloraisonField1.addMouseListener(new  MouseAdapter(){			 
			 public void mouseClicked(MouseEvent e) {
			    dateFloraisonField1.setText("");  
			 }
		});
		dateFloraisonField2.addMouseListener(new  MouseAdapter(){			 
			 public void mouseClicked(MouseEvent e) {
			    dateFloraisonField2.setText("");  
			 }
		});
		//Listener du bouton pour ajouter une date à la liste
		//ajouterDatesFloraisons.addActionListener(e -> {});
		JFileChooser fileChooser = new JFileChooser();
		fetch.addActionListener(e -> {fileChooser.showOpenDialog(panel3);
									  imageFleurieField.setText(fileChooser.getName(fileChooser.getSelectedFile()));
		}); 
		couleurPasFleurieButton.addActionListener(e -> this.couleurNonFleurie = JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante non-fleurie", this.couleurNonFleurie));
		couleurFleurieButton.addActionListener(e -> this.couleurFleurie = JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante fleurie", this.couleurFleurie));
		previous3.addActionListener(e -> CreateNewPlante.this.showPanel("panel2"));
		annul3.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});
		finish.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
			try {
				this.debutFloraison = new Date(sdf.parse(dateFloraisonField1.getText()).getTime());
				this.finFloraison = new Date(sdf.parse(dateFloraisonField2.getText()).getTime());
			} catch (Exception e2) {
				e2.printStackTrace();
			}	
			this.imageFleurie = imageFleurieField.getText();			
			Date datePlantation = new Date((long) 0);
			try {
				datePlantation = new Date(sdf.parse("02/10").getTime());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.p = new Plante(this.tailleFinale, this.debutFloraison, this.finFloraison, datePlantation, this.couleurFleurie, this.couleurNonFleurie, this.vivace, this.nom, this.nomLatin, new ImageIcon(this.imageFleurie), this.typePlante, this.ensoleillement, this.typeSol, this.description);
			System.out.print("Nom de la plante : " + this.p.getNom());
			AccesBD.getInstance().insertPlante(this.p);
			
			/*Image img = this.p.getImgFleurie().getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, 0, 0, null);
			g2.dispose();
			try {
				ImageIO.write(bi, "png", new File("./res/img" + this.p.getNom() + ".png"));
			} catch (IOException eI) {
				eI.printStackTrace();
			}*/
			
		});
		
		//Ajout du troisième panel au JDialog		
		this.add(panel3, "panel3");
		
		//Affichage de la fenetre
		this.showPanel("panel1");
		this.setVisible(true);
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
		System.out.print(cnp.p.getNom());
		return cnp.p;
	}
	
	public static void main(String[] args) {
		CreateNewPlante cnp = new CreateNewPlante();	
	}
}
