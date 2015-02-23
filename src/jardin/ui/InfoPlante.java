package jardin.ui;

import jardin.Ensoleillement;
import jardin.plante.Plante;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoPlante extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel name, desc, typeEns, typeSol, hauteur, vivace, cnf, cf, dates, imageLabel;
	JButton bnf, bf, fermer;
	JPanel tmp1, tmp2;
	
	public InfoPlante(Plante p) {
		name = new JLabel(p.getNom() + " / " + p.getNomL());
		desc = new JLabel("Description : " + p.getDescription());
		
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
		tmp1.setLayout(new FlowLayout());
		tmp1.add(cnf);
		tmp1.add(bnf);
		
		cf = new JLabel("Couleur en fleur : ");
		bf = new JButton("     ");
		bf.setBackground(p.getCouleur_en_fleur());
		tmp2 = new JPanel();
		tmp2.setLayout(new FlowLayout());
		tmp2.add(cf);
		tmp2.add(bf);
		
		//Concernant les dates de floraison.
		ArrayList<String> tabDates = new ArrayList<String>(p.getDateFloraison().size());
		ArrayList<Date> copieDateFloraison = p.getDateFloraison();
		ArrayList<Date> copieDateFinFloraison = p.getDateFinFloraison();
		String tmpDate = "Date(s) de floraison : \n";
		for(int i=0; i < tabDates.size(); i++) {
			tmpDate += "-" + copieDateFloraison.get(i) + " au " + copieDateFinFloraison.get(i) + '\n';
		}
		dates = new JLabel(tmpDate);
		
		//Concernant l'image de la plante
		imageLabel = new JLabel();
		ImageIcon img = p.getImgFleurie();
		imageLabel.setIcon(img);
		
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
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5,1, 5, 5));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(name);
		mainPanel.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(5, 2));
		p2.add(desc);
		p2.add(imageLabel);
		p2.add(typeSol);
		p2.add(typeEns);
		p2.add(tmp1);
		p2.add(tmp2);
		p2.add(dates);
		mainPanel.add(p2);
		
		this.add(mainPanel);
		this.setSize(400, 600);
		this.setResizable(false);
		this.pack();
	}
}
