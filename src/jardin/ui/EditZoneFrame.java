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
import javax.swing.JPanel;


public class EditZoneFrame extends JDialog{
private static final long serialVersionUID = 1L;


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
		

		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		JComboBox<TypeSol> typeSolComboBox = new JComboBox<>(typeSolBoxItems);
		
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		JComboBox<Ensoleillement> ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		
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
				
				
				z.setEnsoleillement(((Ensoleillement)ensoleillementComboBox.getSelectedItem()).getValue());
				
				if (z instanceof ZonePlantable) {
					ZonePlantable zone = (ZonePlantable) z;
					zone.setTypeSol(((TypeSol)typeSolComboBox.getSelectedItem()).getValue());
					panel.setPlante(plantes.getSelected());
					//Mise a jour de la zone
					AccesBD.getInstance().updateZonePlantable(zone);
				} else {
					AccesBD.getInstance().updateZone((Zone)z);
				}
				
				EditZoneFrame.this.setVisible(false);
				EditZoneFrame.this.dispose();
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
