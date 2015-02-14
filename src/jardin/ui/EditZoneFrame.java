package jardin.ui;

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
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JardinPanel panel = MainFrame.getInstance().getJardinPanel();
		AbstractZone z = panel.getSelected();

		PlantothequePanel plantes = new PlantothequePanel();

		JLabel ensoleillement = new JLabel("Ensoleillement : ");
		JLabel typeSol = new JLabel("Type de sol : ");


		//Concernant le type de sol
		typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		typeSolComboBox = new JComboBox(typeSolBoxItems);
		//Copie de la Zone Plantable sélectionnée pour avoir accès à ses attributs.
		ZonePlantable tmp = (ZonePlantable)JardinPanel.getSelected();
		typeSolComboBox.setSelectedIndex(tmp.getTypeSol());
		int typeS = tmp.getTypeSol();
		String typeS2 = "";
		if(typeS == 0) typeS2 = "ARGILEUX";
		else if(typeS == 1) typeS2 = "CALCAIRE";
		else if(typeS == 2) typeS2 = "HUMIFERE";
		else if(typeS == 3) typeS2 = "LIMONIEUX";
		else                typeS2 = "SABLEUX";


		//Concernant l'ensoleillement
		ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		int typeE = tmp.getTypeSol();
		String typeE2 = "";
		     if(typeE == 0) typeE2 = "MIOMBRE";
		else if(typeE == 1) typeE2 = "OMBRE";
		else                typeE2 = "SOLEIL";
		ensoleillementComboBox.setSelectedItem(typeE2);


		if (z instanceof Zone) {
			typeSol.setVisible(false);
			typeSolComboBox.setVisible(false);
			plantes.setVisible(false);
		}

		JPanel soleilPanel = new JPanel();
		JPanel solPanel = new JPanel();

		soleilPanel.add(ensoleillement);
		soleilPanel.add(ensoleillementComboBox);

		soleilPanel.add(typeSol);
		soleilPanel.add(typeSolComboBox);

		JPanel resultPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton valider = new JButton("Valider");


		annuler.addActionListener(e -> {
			EditZoneFrame.this.setVisible(false);
			EditZoneFrame.this.dispose();
		});

		valider.addActionListener(e -> {

			boolean valid = true;
			z.setEnsoleillement(((Ensoleillement)ensoleillementComboBox.getSelectedItem()).getValue());

			if (z instanceof ZonePlantable) {
				ZonePlantable zone = (ZonePlantable) z;
				try {
					panel.setPlante(plantes.getSelected());
					zone.setTypeSol(((TypeSol)typeSolComboBox.getSelectedItem()).getValue());					
					//Mise a jour de la zone
					AccesBD.getInstance().updateZonePlantable(zone);
				} catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(EditZoneFrame.this, "La plante ne peut etre mise dans cette zone. Vérifier son type de sol et son ensoleillement.", "Plante incompatible", JOptionPane.ERROR_MESSAGE);;
					valid = false;
				}
			} else {
				AccesBD.getInstance().updateZone((Zone)z);
			}

			if(valid) {
				EditZoneFrame.this.setVisible(false);
				EditZoneFrame.this.dispose();
			}
		});

		resultPanel.add(annuler);
		resultPanel.add(valider);

		this.add(plantes);
		this.add(soleilPanel);
		this.add(solPanel);
		this.add(resultPanel);
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
