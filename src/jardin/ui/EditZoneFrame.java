package jardin.ui;

import jardin.zone.AbstractZone;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditZoneFrame extends JDialog{
private static final long serialVersionUID = 1L;
	
	private String[] res = null;

	private EditZoneFrame(JFrame f, AbstractZone z) {
		super(f, "Nouveau jardin", true);
		this.setSize(230, 200);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel largeur = new JLabel("largeur  :");
		JLabel hauteur = new JLabel("hauteur :");
		JLabel nom = new JLabel("nom        :");
		
		JTextField largeurText = new JTextField(10);
		JTextField hauteurText = new JTextField(10);
		JTextField nomText = new JTextField(10);
		
		JPanel hauteurPanel = new JPanel();
		JPanel largeurPanel = new JPanel();
		JPanel nomPanel = new JPanel();
		
		hauteurPanel.add(hauteur);
		hauteurPanel.add(hauteurText);
		
		largeurPanel.add(largeur);
		largeurPanel.add(largeurText);
		
		nomPanel.add(nom);
		nomPanel.add(nomText);
		
		this.add(nomPanel);
		this.add(hauteurPanel);
		this.add(largeurPanel);
				
		JPanel resultPanel = new JPanel();
		JButton annuler = new JButton("Annuler");
		JButton valider = new JButton("Valider");
		
		
		annuler.addActionListener(e -> {
			EditZoneFrame.this.setVisible(false);
			EditZoneFrame.this.dispose();
			});
		
		valider.addActionListener(e -> {
			if (true) {
				EditZoneFrame.this.res = new String[3];
				EditZoneFrame.this.res[0] = nomText.getText();
				EditZoneFrame.this.res[1] = largeurText.getText();
				EditZoneFrame.this.res[2] = hauteurText.getText();
				EditZoneFrame.this.setVisible(false);
				EditZoneFrame.this.dispose();
			} else {
				nomText.setText("");
				largeurText.setText("");
				hauteurText.setText("");
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
	public static void showEditZoneFrame(JFrame f, AbstractZone z) {
		new EditZoneFrame(f, z);
	}
}
