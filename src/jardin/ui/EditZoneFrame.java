package jardin.ui;

import java.awt.Color;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class EditZoneFrame extends JDialog{
	private static final long serialVersionUID = 1L;
	private JComboBox<TypeSol> typeSolComboBox;
	private JComboBox<Ensoleillement> ensoleillementComboBox;

	private EditZoneFrame(JFrame f) {
		super(f, "Modifier Zone", true);
		this.setSize(300, 100);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		AbstractZone z = JardinPanel.getSelected();

		//Concernant l'ensoleillement
		JPanel soleilPanel = new JPanel();

		JLabel ensoleillement = new JLabel("Ensoleillement : ");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.SOLEIL, Ensoleillement.MIOMBRE, Ensoleillement.OMBRE};
		ensoleillementComboBox = new JComboBox(ensoleillementBoxItems);
		ensoleillementComboBox.setSelectedIndex(((ZonePlantable)z).getZoneConteneur().getEnsoleillement());
		//System.out.println(((ZonePlantable)z).getZoneConteneur().getEnsoleillement());

		soleilPanel.add(ensoleillement);
		soleilPanel.add(ensoleillementComboBox);

		JPanel solPanel = new JPanel();
		JPanel plantPanel = new JPanel();
		
		if(z instanceof ZonePlantable) {
			this.setSize(300, 200);
			
			//Concernant le type de sol
			solPanel = new JPanel();

			JLabel typeSol = new JLabel("Type de sol : ");
			TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
			typeSolComboBox = new JComboBox(typeSolBoxItems);
			typeSolComboBox.setSelectedIndex(((ZonePlantable) z).getTypeSol());
			solPanel.add(typeSol);
			solPanel.add(typeSolComboBox);
			
			//Concernant la plante
			plantPanel = new JPanel();
			JLabel plant = new JLabel("Plante : ");
			JButton changePlant = new JButton();
			if(((ZonePlantable) z).getPlante() == null) {
				changePlant.setText("Ajouter");
			} else {
				changePlant.setText("Modifier");
			}
			plantPanel.add(plant);
			changePlant.addActionListener(e->{
				((ZonePlantable) z).setPlante(EditPlantDialog.showPlantDialog(this));
			});
			plantPanel.add(changePlant);
		}
		
		//Useless ?
		/*int typeS = tmp.getTypeSol();
		String typeS2 = "";
		if(typeS == 0) typeS2 = "ARGILEUX";
		else if(typeS == 1) typeS2 = "CALCAIRE";
		else if(typeS == 2) typeS2 = "HUMIFERE";
		else if(typeS == 3) typeS2 = "LIMONIEUX";
		else                typeS2 = "SABLEUX";
		
		int typeE = tmp.getTypeSol();
		String typeE2 = "";
		     if(typeE == 0) typeE2 = "MIOMBRE";
		else if(typeE == 1) typeE2 = "OMBRE";
		else                typeE2 = "SOLEIL";
		ensoleillementComboBox.setSelectedItem(typeE2);*/

		JPanel buttonsPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton valider = new JButton("Valider");


		annuler.addActionListener(e -> {
			EditZoneFrame.this.setVisible(false);
			EditZoneFrame.this.dispose();
		});

		valider.addActionListener(e -> {

			z.setEnsoleillement(((Ensoleillement)ensoleillementComboBox.getSelectedItem()).getValue());

			if (z instanceof ZonePlantable) {
				ZonePlantable zoneP = (ZonePlantable) z;
				zoneP.setTypeSol(((TypeSol)typeSolComboBox.getSelectedItem()).getValue());					
				try {
					//Mise a jour de la zone
					AccesBD.getInstance().updateZonePlantable(zoneP);
				} catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(EditZoneFrame.this, "La plante ne peut etre mise dans cette zone. Vérifier son type de sol et son ensoleillement.", "Plante incompatible", JOptionPane.ERROR_MESSAGE);;
				}
			} else {
				AccesBD.getInstance().updateZone((Zone)z);
			}
				EditZoneFrame.this.setVisible(false);
				EditZoneFrame.this.dispose();
		});

		buttonsPanel.add(annuler);
		buttonsPanel.add(valider);

		this.add(soleilPanel);
		if(z instanceof ZonePlantable) {
			this.add(solPanel);
			this.add(plantPanel);
		}
		this.add(buttonsPanel);
		this.setVisible(true);
	}	


	/**
	 * Retourne null si l'utilisateur a cliquer su annuler
	 * un tableau de 3 cases avec : nom, hauteur, largeur 
	 * @param f la fenetre qui cree
	 * @return null ou un tableau de String avec les valeurs
	 */
	public static void showEditZoneFrame(JFrame f) {
		new EditZoneFrame(f);
	}
}
