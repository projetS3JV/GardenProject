package jardin.ui;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class CalendarPanel extends JPanel{
	private int jour = 1; //récupère la valeur de la scrollbar, le nième jour de l'année
	private  String mois = "Janvier";
	private String texteDate = this.jour + " " +this.mois; //La date sous la forme "DD Month" pour l'affichage
	private static String[] moisTab = new String[]{"Janvier", "Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
	//attributs graphiques
	private JScrollBar scrollbar = new JScrollBar(java.awt.Adjustable.HORIZONTAL, 1, 10, 1, 365);;
	private JLabel labelDate = new JLabel(this.texteDate);
	
	public CalendarPanel(){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.scrollbar.setEnabled(true);
		this.scrollbar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				CalendarPanel.this.jour = CalendarPanel.this.scrollbar.getValue();
				CalendarPanel.this.update();
				//méthode à ajouter pour que la changement de date entraine un changement sur les fleurs
			}
		});
		//sets des tailles du label pour qu'elle soit fixe.
		this.labelDate.setMaximumSize(new Dimension(100,50));
		this.labelDate.setMinimumSize(new Dimension(100,50));
		this.labelDate.setPreferredSize(new Dimension(100,50));
		
		//ajout des composants graphiques au pannel
		this.add(this.scrollbar);
		this.add(labelDate);
		

	}
	
/**
 * Permet de mettre à jour le mois sous forme de texte et le texteDate . 
 * Appelée à chaque fois que la valeur de la scrollbar est modifiée.
 */
	private void updateDate(){
		int jourDuMois= 1; // numero du jour dans le mois
		//Janvier
		if(this.jour<=31){
			jourDuMois = this.jour;
			this.mois = moisTab[0];
		}
		//Février
		else if(this.jour>31 && this.jour<=59)
		{
			jourDuMois = this.jour- 31;
			this.mois = moisTab[1];
		}
		//Mars
		else if(this.jour>59 && this.jour<=90)
		{
			jourDuMois = this.jour-59;
			this.mois = moisTab[2];
		}
		//Avril
		else if(this.jour>90 && this.jour<=120)
		{
			jourDuMois = this.jour-90;
			this.mois = moisTab[3];
		}
		//Mai
		else if(this.jour>120 && this.jour<=151)
		{
			jourDuMois = this.jour -120;
			this.mois = moisTab[4];
		}
		//Juin
		else if(this.jour>151 && this.jour<=181)
		{
			jourDuMois = this.jour -151;
			this.mois = moisTab[5];
		}
		//Juillet
		else if(this.jour>181 && this.jour<=212)
		{
			jourDuMois = this.jour-181;
			this.mois = moisTab[6];
		}
		//Aout
		else if(this.jour>212 && this.jour<=243)
		{
			jourDuMois = this.jour -212;
			this.mois = moisTab[7];
		}
		//Septembre
		else if(this.jour>243 && this.jour<=273)
		{
			jourDuMois = this.jour - 243;
			this.mois = moisTab[8];
		}
		//Octobre
		else if(this.jour>273 && this.jour<=304)
		{
			jourDuMois = this.jour -273;
			this.mois = moisTab[9];
		}
		//Novembre
		else if(this.jour>304 && this.jour<=334)
		{
			jourDuMois = this.jour -304;
			this.mois = moisTab[10];
		}
		//Décembre
		else if(this.jour>334 && this.jour<=365)
		{
			jourDuMois = this.jour -324;
			this.mois = moisTab[11];
		}
		this.texteDate = jourDuMois + " " + this. mois;
	}

    /**
	 * Méthode appelée lorsque l'on bouge la scrollbar.
	 * Appelle la méthode updateDate() puis modifie le JLabel à coté de la scrollbar en conséquence
	 */
		private void update(){
			this.updateDate();
			this.labelDate.setText(this.texteDate);	
		}

/**
  * retourne le mois sous forme d'un int pour faciliter le traitement
  * retourne -1 en cas de disfonctionnement.
  */
	public int getMois(){
		for(int i=0; i<this.moisTab.length; i++){
			if(this.moisTab[i].equals(this.mois)) return i+1;
		}
		return -1;
	}

}