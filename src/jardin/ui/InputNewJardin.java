package jardin.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputNewJardin extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private String[] res = null;

	private InputNewJardin(JFrame f) {
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
		JButton creer = new JButton(" Créer ");
		
		
		annuler.addActionListener(e -> {
			InputNewJardin.this.setVisible(false);
			InputNewJardin.this.dispose();
			});
		
		creer.addActionListener(e -> {
			if (validate(nomText.getText(),largeurText.getText(),hauteurText.getText())) {
				InputNewJardin.this.res = new String[3];
				InputNewJardin.this.res[0] = nomText.getText();
				InputNewJardin.this.res[1] = largeurText.getText();
				InputNewJardin.this.res[2] = hauteurText.getText();
				InputNewJardin.this.setVisible(false);
				InputNewJardin.this.dispose();
			} else {
				nomText.setText("");
				largeurText.setText("");
				hauteurText.setText("");
			}
			});
		
		resultPanel.add(annuler);
		resultPanel.add(creer);
		
		this.add(resultPanel);
		this.setVisible(true);
	}
	
	private String[] getValues() {
		return this.res;
	}
	
	/**
	 * Methode qui verifie le bon format des valeurs entrées
	 * @param nom le champ nom
	 * @param largeur le champs hauteur
	 * @param hauteur la champs largeur
	 * @return si les champs sont au bon format
	 */
	private static boolean validate(String nom, String largeur, String hauteur) {
		if (nom.equals("")) {
			return false;
		}
		
		try {
			Integer.parseInt(largeur);
			Integer.parseInt(hauteur);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Retourne null si l'utilisateur a cliquer su annuler
	 * un tableau de 3 cases avec : nom, hauteur, largeur 
	 * @param f la fenetre qui cree
	 * @return null ou un tableau de String avec les valeurs
	 */
	public static String[] showInputNewJardin(JFrame f) {
		InputNewJardin in = new InputNewJardin(f);
		return in.getValues();
	}
}
