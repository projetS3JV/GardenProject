package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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

	//Le nom du compte est : ProjetS3JV
	//et le mot de passe est : jvinfs3
	
	private static final long serialVersionUID = 1L;
	
	//Les trois fenêtres pour créer la plante affichées les unes après les autres
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private CardLayout cl;

	private Plante p = null;

	//Attributs de la plante
	private String nom, nomLatin, description, imageFleurie;
	private int tailleFinale;
	private TypePlante typePlante;
	private TypeSol typeSol;
	private Ensoleillement ensoleillement;
	private boolean vivace;
	private Date debutFloraison, finFloraison;
	private Color couleurFleurie, couleurNonFleurie;
	
	
	private boolean couleurFleurieIsNull = true;
	private boolean couleurNonFleurieIsNull = true;

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
		JLabel tailleFinale = new JLabel("Taille finale moyenne arrondie (en cm)");
		JTextField tailleFinaleField = new JTextField();
		JLabel desc = new JLabel("Description");
		JTextArea descArea = new JTextArea();
		descArea.setLineWrap(true);
		descArea.setWrapStyleWord(true);
		JPanel buttonsPanel1 = new JPanel();
		JButton annul1 = new JButton("Annuler");
		JButton next1 = new JButton("Suivant");
		buttonsPanel1.add(annul1);
		buttonsPanel1.add(next1);

		panel1.setLayout(new GridLayout(9, 1));

		/*name.setAlignmentX(RIGHT_ALIGNMENT);
		latinName.setAlignmentX(LEFT_ALIGNMENT);
		tailleFinale.setAlignmentX(LEFT_ALIGNMENT);
		desc.setAlignmentX(LEFT_ALIGNMENT);*/
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
		next1.addActionListener(e -> {    int i = 0;
		this.nom = nameField.getText();
		this.nomLatin = latinNameField.getText();
		this.description = descArea.getText();
		boolean isTailleCorrect = true;
		try {
			i = Integer.parseInt(tailleFinaleField.getText());
		} catch (Exception notIntException) {
			isTailleCorrect = false;
		}
		if(tailleFinaleField.getText().isEmpty() || nameField.getText().isEmpty() || latinNameField.getText().isEmpty() || descArea.getText().isEmpty()){
			JOptionPane.showMessageDialog(panel1, "Un des champs est vide", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else if(!isTailleCorrect){
			JOptionPane.showMessageDialog(panel1, "Taille incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			this.tailleFinale = i;
			CreateNewPlante.this.showPanel("panel2");
		}
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

		panel2.setLayout(new GridLayout(8, 1));

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
		JLabel datesFloraison = new JLabel("Dates de floraison");
		JPanel sousPanel1 = new JPanel();
		JLabel dateDebutFloraison = new JLabel("Date de début de la floraison (jj/mm)"); 
		JTextField dateFloraisonField1 = new JTextField();
		dateFloraisonField1.setColumns(5);
		JPanel sousPanel4 = new JPanel();
		JLabel dateFinFloraison = new JLabel("Date de fin de la floraison (jj/mm)"); 
		JTextField dateFloraisonField2 = new JTextField();
		dateFloraisonField2.setColumns(5);
		sousPanel1.add(dateDebutFloraison);
		sousPanel1.add(dateFloraisonField1);
		//Bouton pour ajouter une date à la liste
		//JButton ajouterDatesFloraisons = new JButton("Ajouter");
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
		JPanel sousPanelCouleurPasEnfleur = new JPanel();
		JLabel couleurPasEnFleur = new JLabel("Sélectionnez la couleur de la plante non-fleurie");
		JButton couleurPasFleurieButton = new JButton("   ");
		sousPanelCouleurPasEnfleur.add(couleurPasEnFleur);
		sousPanelCouleurPasEnfleur.add(couleurPasFleurieButton);
		JPanel sousPanelCouleurEnfleur = new JPanel();
		JLabel couleurEnFleur = new JLabel("Sélectionnez la couleur de la plante en fleur");
		JButton couleurFleurieButton = new JButton("   ");
		sousPanelCouleurEnfleur.add(couleurEnFleur);
		sousPanelCouleurEnfleur.add(couleurFleurieButton);
		JPanel buttonsPanel3 = new JPanel();
		JButton annul3 = new JButton("Annuler");
		JButton previous3 = new JButton("Retour");
		JButton finish = new JButton("Terminer");
		buttonsPanel3.add(annul3);
		buttonsPanel3.add(previous3);
		buttonsPanel3.add(finish);

		panel3.setLayout(new GridLayout(8, 1));

		panel3.add(datesFloraison);
		panel3.add(sousPanel1);
		panel3.add(sousPanel4);
		panel3.add(imageFleurie);
		panel3.add(sousPanel2);
		panel3.add(sousPanelCouleurPasEnfleur);
		panel3.add(sousPanelCouleurEnfleur);
		panel3.add(buttonsPanel3);

		//Listener du bouton pour ajouter une date à la liste
		//ajouterDatesFloraisons.addActionListener(e -> {});
		JFileChooser fileChooser = new JFileChooser();
		fetch.addActionListener(e -> {fileChooser.showOpenDialog(panel3);
		imageFleurieField.setText(fileChooser.getName(fileChooser.getSelectedFile()));
		}); 

		couleurPasFleurieButton.addActionListener(e -> {this.couleurNonFleurie = JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante non-fleurie", this.couleurNonFleurie);
		this.couleurNonFleurieIsNull = false;
		couleurPasFleurieButton.setBackground(this.couleurNonFleurie);
		});
		couleurFleurieButton.addActionListener(e -> {this.couleurFleurie = JColorChooser.showDialog(panel3, "Choisissez la couleur pour la plante fleurie", this.couleurFleurie);
		this.couleurFleurieIsNull = false;
		couleurFleurieButton.setBackground(this.couleurFleurie);
		});

		previous3.addActionListener(e -> CreateNewPlante.this.showPanel("panel2"));

		annul3.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});

		finish.addActionListener(e -> {
			String date1 = dateFloraisonField1.getText();
			String date2 = dateFloraisonField2.getText();
			boolean isDatesCorect = (this.isDatesValid(date1,date2));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");

			this.imageFleurie = imageFleurieField.getText();

			Date datePlantation = new Date((long) 0);
			try {
				datePlantation = new Date(sdf.parse("02/10").getTime());
			} catch (Exception e1) {}

			if(!isDatesCorect && this.couleurFleurieIsNull && this.couleurNonFleurieIsNull) {
				JOptionPane.showMessageDialog(panel3, "Dates incorrectes et couleurs non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(!isDatesCorect && this.couleurFleurieIsNull){
				JOptionPane.showMessageDialog(panel3, "Dates incorrectes et couleur en fleur non sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(!isDatesCorect && this.couleurNonFleurieIsNull){
				JOptionPane.showMessageDialog(panel3, "Dates incorrectes et couleur non fleurie pas sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(this.couleurFleurieIsNull && this.couleurNonFleurieIsNull){
				JOptionPane.showMessageDialog(panel3, "Couleurs non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(!isDatesCorect){
				JOptionPane.showMessageDialog(panel3, "Dates incorrectes", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(this.couleurFleurieIsNull){
				JOptionPane.showMessageDialog(panel3, "Couleur en fleur non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			}  else if(this.couleurNonFleurieIsNull){ 
				JOptionPane.showMessageDialog(panel3, "Couleurs non fleurie pas sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				CreateNewPlante.this.setVisible(false);
				CreateNewPlante.this.dispose();
				ImageIcon img = new ImageIcon();
				if(!this.imageFleurie.isEmpty()){
					img = new ImageIcon(this.imageFleurie);
				}
				this.p = new Plante(this.tailleFinale, this.debutFloraison, this.finFloraison, datePlantation, this.couleurFleurie, this.couleurNonFleurie, this.vivace, this.nom, this.nomLatin, img, this.typePlante, this.ensoleillement, this.typeSol, this.description);
				AccesBD.getInstance().insertPlante(this.p);
			}
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

	private boolean isDatesValid(String date1, String date2) {		
		boolean isDatesCorect = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
		try {
			int joursDate1 = Integer.parseInt(date1.split("/")[0]);
			int joursDate2 = Integer.parseInt(date2.split("/")[0]);
			int moisDate1 = Integer.parseInt(date1.toString().split("/")[1]);
			int moisDate2 = Integer.parseInt(date2.toString().split("/")[1]);

			if(moisDate1<1 || moisDate2<1 || moisDate1>12 || moisDate2>12){
				isDatesCorect = false;
			} else if(moisDate1>moisDate2) {
				isDatesCorect = false;
			} else {			
				switch (moisDate1) {
				case 1: case 3: case 5:
				case 7: case 8: case 10:
				case 12:
					if(joursDate1>31){
						isDatesCorect=false;
					};
					break;
				case 4: case 6:
				case 9: case 11:
					if(joursDate1>30){
						isDatesCorect=false;
					};
					break;
				case 2:
					if(joursDate1>28){
						isDatesCorect=false;
					};
				}

				switch (moisDate2) {
				case 1: case 3: case 5:
				case 7: case 8: case 10:
				case 12:
					if(joursDate2>31){
						isDatesCorect=false;
					};
					break;
				case 4: case 6:
				case 9: case 11:
					if(joursDate2>30){
						isDatesCorect=false;
					};
					break;
				case 2:
					if(joursDate2>28){
						isDatesCorect=false;
					};
				}

				if(isDatesCorect) {
					if( (moisDate1==moisDate2) && (joursDate1>joursDate2)) {
						isDatesCorect = false;
					} else {
						moisDate1--;
						moisDate2--;
						this.debutFloraison = new Date(new GregorianCalendar(1970,moisDate1,joursDate1).getTimeInMillis());
						this.finFloraison = new Date(new GregorianCalendar(1970,moisDate2,joursDate2).getTimeInMillis());
						System.out.println(debutFloraison);
						System.out.println(finFloraison);
					}
				}
			}
		} catch (Exception e2) {
			isDatesCorect = false;
		}
		return isDatesCorect;
	}

	/**
	 * Crée une plante et la retourne. Elle est nulle si on a annuler
	 * @return la plante ou null
	 */
	public static Plante showCreateNewPlante() {
		CreateNewPlante cnp = new CreateNewPlante();
		return cnp.p;
	}

	public static void main(String[] args) {
		CreateNewPlante cnp = new CreateNewPlante();	
	}
}
