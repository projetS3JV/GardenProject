package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.plante.TypePlante;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class CreateNewPlante extends JDialog{

	//Le nom du compte est : ProjetS3JV
	//et le mot de passe est : jvinfs3

	private static final long serialVersionUID = 1L;

	//Les trois fenêtres pour créer la plante affichées les unes après les autres
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private CardLayout cl;

	private Plante p = null;

	//Attributs de la plante
	private String nom, nomLatin, description, imageFleurie;
	private int tailleFinale;
	private TypePlante typePlante;
	private ArrayList<Integer> typesSol = new ArrayList<>();
	private Ensoleillement ensoleillement;
	private boolean vivace;
	private ArrayList<Date> debutsFloraison = new ArrayList<>();
	private ArrayList<Date>finsFloraison = new ArrayList<>();
	private Color couleurFleurie, couleurNonFleurie;
	private SortedListModel<String> datesListModel = new SortedListModel<>();	
	private boolean couleurFleurieIsNull = true;
	private boolean couleurNonFleurieIsNull = true;

	private CreateNewPlante() {

		super();
		this.setSize(500, 300);
		this.setTitle("Nouvelle plante");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel4 = new JPanel();
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
		typePlante.setAlignmentX(RIGHT_ALIGNMENT);
		TypePlante[] typePlanteBoxItems = {TypePlante.BUISSON, TypePlante.FLEUR, TypePlante.HERBE};
		JComboBox<TypePlante> typePlanteComboBox = new JComboBox(typePlanteBoxItems);

		JLabel typeSol = new JLabel("Type de sol");
		typeSol.setAlignmentX(RIGHT_ALIGNMENT);

		SortedListModel<TypeSol> typesSolListModel = new SortedListModel<>();
		typesSolListModel.add(TypeSol.ARGILEUX);
		typesSolListModel.add(TypeSol.CALCAIRE);
		typesSolListModel.add(TypeSol.HUMIFERE);
		typesSolListModel.add(TypeSol.LIMONEUX);
		typesSolListModel.add(TypeSol.SABLEUX);
		JList<TypeSol> typesSolList = new JList<>(typesSolListModel);
		typesSolList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		typesSolList.setPreferredSize(new Dimension(150, 85));
		JPanel listOfSols = new JPanel();
		listOfSols.add(typesSolList);

		//TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		//JComboBox<TypeSol> typeSolComboBox = new JComboBox<>(typeSolBoxItems);

		JLabel ensoleillement = new JLabel("Ensoleillement");
		ensoleillement.setAlignmentX(RIGHT_ALIGNMENT);
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
		panel2.add(listOfSols);
		panel2.add(ensoleillement);
		panel2.add(ensoleillementComboBox);
		panel2.add(vivaceCheck);
		panel2.add(buttonsPanel2);

		//Définition des listeners des boutons
		next2.addActionListener(e -> {
			if(typesSolList.getSelectedValuesList().size()==0){
				JOptionPane.showMessageDialog(panel2, "Aucun type de sol sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				this.typePlante = (TypePlante) typePlanteComboBox.getSelectedItem();
				for(int i=0; i<typesSolList.getSelectedValuesList().size(); i++){
					this.typesSol.add(typesSolList.getSelectedValuesList().get(i).getValue());
				}
				this.ensoleillement = (Ensoleillement) ensoleillementComboBox.getSelectedItem();
				this.vivace = vivaceCheck.isSelected();
				CreateNewPlante.this.showPanel("panel3");
			}
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

		JPanel datesFloraisonPanel = new JPanel();		
		JLabel datesFloraison = new JLabel("Dates d'une floraison (jj/mm - jj/mm) : "); 
		JTextField datesFloraisonField = new JTextField();
		datesFloraisonField.setColumns(10);				
		JButton ajouterDatesFloraisons = new JButton("Ajouter");		
		datesFloraisonPanel.add(datesFloraison);
		datesFloraisonPanel.add(datesFloraisonField);
		datesFloraisonPanel.add(ajouterDatesFloraisons);

		JPanel datesPanel = new JPanel();
		JList<String> dates = new JList<>(this.datesListModel);
		dates.setPreferredSize(new Dimension(200,200));
		JScrollPane scrollPane = new JScrollPane(dates);
		JPanel listPanel = new JPanel();
		listPanel.add(scrollPane);
		JButton supprimerDate = new JButton("Supprimer date");
		JPanel deleteButtonPanel = new JPanel();
		deleteButtonPanel.add(supprimerDate);
		datesPanel.setLayout(new BoxLayout(datesPanel, BoxLayout.X_AXIS));
		datesPanel.add(listPanel);
		datesPanel.add(deleteButtonPanel);

		JPanel buttonsPanel3 = new JPanel();
		JButton annul3 = new JButton("Annuler");
		JButton previous3 = new JButton("Retour");
		JButton next3 = new JButton("Suivant");
		buttonsPanel3.add(annul3);
		buttonsPanel3.add(previous3);
		buttonsPanel3.add(next3);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		panel3.add(datesFloraisonPanel);
		panel3.add(datesPanel);
		panel3.add(buttonsPanel3);

		//Définition des listeners des boutons
		ajouterDatesFloraisons.addActionListener(e->{
			if(!isFormatCorrect(datesFloraisonField.getText())){
				JOptionPane.showMessageDialog(panel3, "Format incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				String date1 = datesFloraisonField.getText().split(" - ")[0];
				String date2 = datesFloraisonField.getText().split(" - ")[1];
				if(areDatesValid(getListFromListModel(this.datesListModel) ,date1, date2)){
					this.datesListModel.add(datesFloraisonField.getText());
					datesFloraisonField.setText("");
				} else {
					JOptionPane.showMessageDialog(panel3, "Dates incorrectes", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} 
		});
		supprimerDate.addActionListener(e->{
			if(JOptionPane.showConfirmDialog(this, "Supprimer ces dates ?") == 0) {
				this.datesListModel.remove(dates.getSelectedValue());
			}
		});
		next3.addActionListener(e -> {
			if(this.datesListModel.getSize()==0){
				JOptionPane.showMessageDialog(panel3, "Aucune date rentrée", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
				try {
					for(int i=0; i<this.datesListModel.getSize(); i++){
						this.debutsFloraison.add(new Date(sdf.parse(this.datesListModel.getElementAt(i).split(" - ")[0]).getTime()));
						this.finsFloraison.add(new Date(sdf.parse(this.datesListModel.getElementAt(i).split(" - ")[1]).getTime()));
					}
				} catch (Exception e1) {}
				CreateNewPlante.this.showPanel("panel4");
			}
		});
		previous3.addActionListener(e -> CreateNewPlante.this.showPanel("panel2"));
		annul3.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});

		//Ajout du deuxième panel au JDialog
		this.add(panel3, "panel3");

		//Création du troisième panel
		panel4 = new JPanel();

		JLabel imageFleurie = new JLabel("Image de la plante lorsqu'elle est en fleur");
		imageFleurie.setAlignmentX(RIGHT_ALIGNMENT);
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
		JPanel buttonsPanel4 = new JPanel();
		JButton annul4 = new JButton("Annuler");
		JButton previous4 = new JButton("Retour");
		JButton finish = new JButton("Terminer");
		buttonsPanel4.add(annul4);
		buttonsPanel4.add(previous4);
		buttonsPanel4.add(finish);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

		panel4.add(imageFleurie);
		panel4.add(sousPanel2);
		panel4.add(sousPanelCouleurPasEnfleur);
		panel4.add(sousPanelCouleurEnfleur);
		panel4.add(buttonsPanel4);

		//Listener du bouton pour ajouter une date à la liste
		//ajouterDatesFloraisons.addActionListener(e -> {});
		JFileChooser fileChooser = new JFileChooser();
		fetch.addActionListener(e -> {fileChooser.showOpenDialog(panel4);
		imageFleurieField.setText(fileChooser.getName(fileChooser.getSelectedFile()));
		}); 

		couleurPasFleurieButton.addActionListener(e -> {this.couleurNonFleurie = JColorChooser.showDialog(panel4, "Choisissez la couleur pour la plante non-fleurie", this.couleurNonFleurie);
		this.couleurNonFleurieIsNull = false;
		couleurPasFleurieButton.setBackground(this.couleurNonFleurie);
		});
		couleurFleurieButton.addActionListener(e -> {this.couleurFleurie = JColorChooser.showDialog(panel4, "Choisissez la couleur pour la plante fleurie", this.couleurFleurie);
		this.couleurFleurieIsNull = false;
		couleurFleurieButton.setBackground(this.couleurFleurie);
		});

		previous4.addActionListener(e -> {CreateNewPlante.this.showPanel("panel3");});

		annul4.addActionListener(e -> {
			CreateNewPlante.this.setVisible(false);
			CreateNewPlante.this.dispose();
		});

		finish.addActionListener(e -> {
			this.imageFleurie = imageFleurieField.getText();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
			Date datePlantation = new Date((long) 0);
			try {
				datePlantation = new Date(sdf.parse("02/10").getTime());
			} catch (Exception e1) {}

			if(this.couleurFleurieIsNull && this.couleurNonFleurieIsNull) {
				JOptionPane.showMessageDialog(panel4, "Couleurs non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(this.couleurFleurieIsNull){
				JOptionPane.showMessageDialog(panel4, "Couleur en fleur non sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(this.couleurNonFleurieIsNull){
				JOptionPane.showMessageDialog(panel4, "Couleur non fleurie pas sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(this.couleurFleurieIsNull && this.couleurNonFleurieIsNull){
				JOptionPane.showMessageDialog(panel4, "Couleurs non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(this.couleurFleurieIsNull){
				JOptionPane.showMessageDialog(panel4, "Couleur en fleur non sélectionnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			}  else if(this.couleurNonFleurieIsNull){ 
				JOptionPane.showMessageDialog(panel4, "Couleurs non fleurie pas sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				CreateNewPlante.this.setVisible(false);
				CreateNewPlante.this.dispose();
				ImageIcon img = new ImageIcon();
				if(!this.imageFleurie.isEmpty()){
					img = new ImageIcon(this.imageFleurie);
				}
				this.p = new Plante(this.tailleFinale, this.debutsFloraison, this.finsFloraison, datePlantation, this.couleurFleurie, this.couleurNonFleurie, this.vivace, this.nom, this.nomLatin, this.imageFleurie, this.typePlante, this.ensoleillement, this.typesSol, this.description);
				AccesBD.getInstance().insertPlante(this.p);
			}
		});

		//Ajout du troisième panel au JDialog		
		this.add(panel4, "panel4");

		//Affichage de la fenetre
		this.showPanel("panel1");
		this.setVisible(true);
	}

	private void showPanel(String panel) {
		this.cl.show(this.getContentPane(), panel);
	}

	public boolean isInteger(String input) {
		try {
			Integer.parseInt( input );
			return true;
		}
		catch(NumberFormatException e ) {
			return false;
		}
	}

	public ArrayList<String> getListFromListModel(ListModel<String> l){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<l.getSize(); i++){
			list.add(l.getElementAt(i));
		}
		return list;
	}

	private boolean isFormatCorrect(String d){		
		if(d.length()!=13){
			return false;
		} else {
			if(!(d.charAt(2)+"").equals("/") || !(d.charAt(10)+"").equals("/") || !d.substring(5, 8).equals(" - ")){
				return false;
			} else if(!isInteger(d.charAt(0)+"") || !isInteger(d.charAt(1)+"") || !isInteger(d.charAt(3)+"") || !isInteger(d.charAt(4)+"")){
				return false;
			} else if(!isInteger(d.charAt(8)+"") || !isInteger(d.charAt(9)+"") || !isInteger(d.charAt(11)+"") || !isInteger(d.charAt(12)+"")){
				return false;
			} else {
				return true;
			}
		}
	}

	public int getDayOfDate(String d){
		try{
			 return Integer.parseInt(d.substring(0, 2));
		} catch (NumberFormatException e){
			return -1;
		}
	}

	public int getMonthOfDate(String d){
		try{
			return Integer.parseInt(d.substring(3, 5));
		} catch (NumberFormatException e){
			return -1;
		}
	}

	public int beforeOrAfter(String d1, String d2){
		if(getMonthOfDate(d1)<getMonthOfDate(d2)){
			return -1;
		} else if(getMonthOfDate(d1)>getMonthOfDate(d2)){
			return 1;
		} else {
			if(getDayOfDate(d1)<getDayOfDate(d2)){
				return -1;
			} else if(getDayOfDate(d1)>getDayOfDate(d2)){
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	/*	
	 	01/01
		01/02
		
		05/05
		10/10
	*/

	public boolean datesCroisees(String dates1, String dates2){
		boolean croisees = false;
		String dateDebut1 = dates1.split(" - ")[0];
		String dateFin1 = dates1.split(" - ")[1];
		String dateDebut2 = dates2.split(" - ")[0];
		String dateFin2 = dates2.split(" - ")[1];

		if( (beforeOrAfter(dateDebut2, dateFin1)!=1 && beforeOrAfter(dateDebut2, dateDebut1)!=-1) || (beforeOrAfter(dateFin2, dateFin1)!=1 && beforeOrAfter(dateFin2, dateDebut1)!=-1)){
			croisees = true;
		}		
		//System.out.println("Là : " + !croisees);
		return croisees;
	}

	private boolean areDatesValid(List<String> dates, String date1, String date2) {		
		boolean areDatesCorect = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
		try {
			int joursDate1 = Integer.parseInt(date1.split("/")[0]);
			int joursDate2 = Integer.parseInt(date2.split("/")[0]);
			int moisDate1 = Integer.parseInt(date1.toString().split("/")[1]);
			int moisDate2 = Integer.parseInt(date2.toString().split("/")[1]);

			if(moisDate1 == 0 || moisDate2 == 0 || joursDate1 == 0 || joursDate2 == 0) {
				areDatesCorect = false;
			} else if(moisDate1<1 || moisDate2<1 || moisDate1>12 || moisDate2>12){
				areDatesCorect = false;
			} else if(moisDate1>moisDate2) {
				areDatesCorect = false;
			} else {			
				switch (moisDate1) {
				case 1: case 3: case 5:
				case 7: case 8: case 10:
				case 12:
					if(joursDate1>31){
						areDatesCorect=false;
					};
					break;
				case 4: case 6:
				case 9: case 11:
					if(joursDate1>30){
						areDatesCorect=false;
					};
					break;
				case 2:
					if(joursDate1>28){
						areDatesCorect=false;
					};
				}

				switch (moisDate2) {
				case 1: case 3: case 5:
				case 7: case 8: case 10:
				case 12:
					if(joursDate2>31){
						areDatesCorect=false;
					};
					break;
				case 4: case 6:
				case 9: case 11:
					if(joursDate2>30){
						areDatesCorect=false;
					};
					break;
				case 2:
					if(joursDate2>28){
						areDatesCorect=false;
					};
				}

				if(areDatesCorect) {
					if( (moisDate1==moisDate2) && (joursDate1>joursDate2)) {
						areDatesCorect = false;
						//} else {
						//moisDate1--;
						//moisDate2--;
						//this.debutFloraison = new Date(new GregorianCalendar(1970,moisDate1,joursDate1).getTimeInMillis());
						//this.finFloraison = new Date(new GregorianCalendar(1970,moisDate2,joursDate2).getTimeInMillis());
					}
				}
			}
			if(dates.size()!=0){
				for(int i=0; i<dates.size(); i++){
					if(datesCroisees(dates.get(i), date1 + " - " + date2)){
						areDatesCorect = false;
					}
				}
				//System.out.println("Ici : " + areDatesCorect);
			}
		} catch (Exception e2) {
			areDatesCorect = false;
		}
		return areDatesCorect;
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
		cnp.showPanel("panel3");
	}
}