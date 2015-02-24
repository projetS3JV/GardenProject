package jardin.ui;

import jardin.Ensoleillement;
import jardin.plante.Plante;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class InfoPlante extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel name, desc, typeEns, typeSol, hauteur, vivace, cnf, cf, dates, imageLabel, vide;
	JTextArea d;
	JButton bnf, bf, fermer;
	JPanel tmp1, tmp2;
	
	public InfoPlante(Plante p) {
		//this.setSize(400, 400);
		//this.setResizable(false);
		name = new JLabel(p.getNom() + " / " + p.getNomL());
		desc = new JLabel("Description : " + p.getDescription());
		
		d = new JTextArea("Description : " + p.getDescription());
		d.setLineWrap(true);
		d.setWrapStyleWord(true);
		
		vide = new JLabel();
		
		//Gestion de l'ensoleillement
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		String tmpEns = "Ensoleillement : ";
		     if(p.getEnsoleillement()==0) tmpEns += "Mi-ombre";
		else if(p.getEnsoleillement()==0) tmpEns += "Ombre";
		else                              tmpEns += "Soleil";
		typeEns = new JLabel(tmpEns);
		
		//Les différents types de sol
		ArrayList<Integer> typeSolPlante = p.getTypeSol();
		String tmpTS = "Type(s) de sol : ";
		for(int i = 0; i < typeSolPlante.size(); i++)
		{
			if(typeSolPlante.get(i)==0)
			{
				tmpTS += "Argileux, ";
			}
			else if(typeSolPlante.get(i)==1)
			{
				tmpTS += "Humifère, ";
			}
			else if(typeSolPlante.get(i)==2)
			{
				tmpTS += "Limonieux, ";
			}
			else
			{
				tmpTS += "Sableux";
			}
		}
		typeSol = new JLabel(tmpTS);
		hauteur = new JLabel("Hauteur : " + p.getTailleFin());
		
		//Condition sur la vivacité potentielle de la plante
		vivace = new JLabel();
		if(p.isVivace()) vivace.setText("Vivace ? : Oui");
		else vivace.setText("Vivace ? : Non");
		
		//Concernant les couleurs
		cnf = new JLabel("Couleur non fleuri : ");
		bnf = new JButton("     ");
		bnf.setBackground(p.getCouleur_non_fleuris());
		tmp1 = new JPanel();
		tmp1.setLayout(new BoxLayout(tmp1, BoxLayout.X_AXIS));
		tmp1.add(cnf);
		tmp1.add(bnf);
		
		cf = new JLabel("Couleur en fleur : ");
		bf = new JButton("     ");
		bf.setBackground(p.getCouleur_en_fleur());
		tmp2 = new JPanel();
		tmp2.setLayout(new BoxLayout(tmp2, BoxLayout.X_AXIS));
		tmp2.add(cf);
		tmp2.add(bf);
		
		//Concernant les dates de floraison.
		ArrayList<Date> copieDateFloraison = p.getDateFloraison();
		ArrayList<Date> copieDateFinFloraison = p.getDateFinFloraison();
		String tmpDate = "Date(s) de floraison : \n";
		for(int i=0; i < copieDateFloraison.size(); i++) {
			tmpDate += copieDateFloraison.get(i) +"/"+ copieDateFloraison.get(i)
					   + " au " + 
					   copieDateFinFloraison.get(i) + "/" + copieDateFinFloraison.get(i);
			System.out.print(tmpDate);
		}
		dates = new JLabel("<html>" + tmpDate + "</html>");
		
		//Concernant l'image de la plante
		imageLabel = new JLabel();
		//ImageIcon img = p.getImgFleurie();
		imageLabel.setIcon(p.getImgFleurie());
		
		//Bouton fermer
		fermer = new JButton("Fermer");
		fermer.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				InfoPlante.this.setVisible(false);
				InfoPlante.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
		
		//Mise en page
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		name.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(name, BorderLayout.NORTH);
		p1.add(d, BorderLayout.CENTER);
		this.add(p1);
		
	}
}
