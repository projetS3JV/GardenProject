package jardin.ui;

import jardin.plante.Plante;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoPlante extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public InfoPlante(Plante p) {
		JLabel name = new JLabel(p.getNom() + " / " + p.getNomL());
		JLabel desc = new JLabel("Description : " + p.getDescription());
		JLabel typeEns = new JLabel("Ensoleillement : " + p.getEnsoleillement());
		
		//Les différents types de sol
		String tmp = "Type(s) de sol : ";
		for (int i = 0; i < p.getTypeSol().length; i++); {
			tmp += p.getTypeSol() + ", ";
		}
		JLabel typeSol = new JLabel(tmp);
		JLabel hauteur = new JLabel("Hauteur : " + p.getTailleFin());
		
		//Condition sur la vivacité potentielle de la plante
		JLabel vivace = new JLabel();
		if(p.isVivace()) vivace.setText("Vivace ? : Oui");
		else vivace.setText("Vivace ? : Non");
		
		//Concernant les couleurs
		JLabel cnf = new JLabel("Couleur non fleuri : ");
		JButton bnf = new JButton("     ");
		bnf.setBackground(p.getCouleur_non_fleuris());
		
		JLabel cf = new JLabel("Couleur en fleur : ");
		JButton bf = new JButton("     ");
		bf.setBackground(p.getCouleur_en_fleur());
	}
}
