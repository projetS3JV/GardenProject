package jardin.ui;

import jardin.plante.Plante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
		for (int i = 0; i < p.getTypeSol().size(); i++); {
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
		JPanel tmp1 = new JPanel();
		tmp1.setLayout(new FlowLayout());
		tmp1.add(cnf);
		tmp1.add(bnf);
		
		JLabel cf = new JLabel("Couleur en fleur : ");
		JButton bf = new JButton("     ");
		bf.setBackground(p.getCouleur_en_fleur());
		
		//Concernant les dates de floraison.
		ArrayList<String> tabDates = new ArrayList<String>(p.getDateFloraison().size());
		ArrayList<Date> copieDateFloraison = p.getDateFloraison();
		ArrayList<Date> copieDateFinFloraison = p.getDateFinFloraison();
		String tmpDate = "Date(s) de floraison : \n";
		for(int i=0; i < tabDates.size(); i++) {
			tmpDate += "-" + copieDateFloraison.get(i) + " au " + copieDateFinFloraison.get(i) + '\n';
		}
		JLabel dates = new JLabel(tmpDate);
		
		//Concernant l'image de la plante
		JLabel imageLabel = new JLabel();
		ImageIcon img = p.getImgFleurie();
		imageLabel.setIcon(img);
		
		//Mise en page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(5, 5));
		
		mainPanel.add(name, BorderLayout.NORTH);
		mainPanel.add(desc, BorderLayout.CENTER);
		mainPanel.add(typeEns, BorderLayout.CENTER);
		mainPanel.add(typeSol, BorderLayout.CENTER);
		mainPanel.add(imageLabel, BorderLayout.EAST);
		mainPanel.add(cf, BorderLayout.EAST);
		
	}
}
