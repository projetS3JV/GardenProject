package jardin.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditZoneFrame extends JDialog{
private static final long serialVersionUID = 1L;
	
	private String[] res = null;

	private EditZoneFrame(JFrame f) {
		super(f, "ModifierZone", true);
		this.setSize(230, 200);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		PlantePanel plantes = new PlantePanel();
		
		JLabel ensoleillement = new JLabel("Ensoleillement : ");
		JLabel typeSol = new JLabel(" type de sol : ");
		
		
		JPanel soleilPanel = new JPanel();
		JPanel solPanel = new JPanel();
		
		JPanel resultPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton valider = new JButton("Valider");
		
		
		annuler.addActionListener(e -> {
			EditZoneFrame.this.setVisible(false);
			EditZoneFrame.this.dispose();
			});
		
		valider.addActionListener(e -> {
			if (true) {
				
				EditZoneFrame.this.setVisible(false);
				EditZoneFrame.this.dispose();
			} else {
				
			}
			});
		
		resultPanel.add(annuler);
		resultPanel.add(valider);
		
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
