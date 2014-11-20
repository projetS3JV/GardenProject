package jardin.ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

public class CalendarPanel extends JPanel{
	private JScrollBar scrollbar;
	private int jour = 1; //récupère la valeur de la scrollbar
	private int jourDuMois= 1; // sert à l'affichage donne le numero du jour dans le mois
	private String mois = "Janvier";
	private static String[] moisTab = new String[]{"Janvier", "Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};

	public CalendarPanel(){
		super();
		this.scrollbar =new JScrollBar(java.awt.Adjustable.HORIZONTAL, 1, 10, 1, 365);
		this.scrollbar.setEnabled(true);
		this.scrollbar.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				CalendarPanel.this.jour = CalendarPanel.this.scrollbar.getValue();
				CalendarPanel.this.updateDate();
				//méthode à ajouter pour que la changement de date entraine un changement sur les fleurs
			}
		});
		this.add(this.scrollbar);
	}
	
/**
 * Permet de mettre à jour le mois sous forme de texte et le jourDuMois. 
 * Appelée à chaque fois que la valeur de la scrollbar est modifiée.
 */
	private void updateDate(){
		//Janvier
		if(this.jour<=31){
			this.jourDuMois = this.jour;
			this.mois = moisTab[0];
		}
		//Février
		else if(this.jour>31 && this.jour<=59)
		{
			this.jourDuMois = this.jour- 31;
			this.mois = moisTab[1];
		}
		//Mars
		else if(this.jour>59 && this.jour<=90)
		{
			this.jourDuMois = this.jour-59;
			this.mois = moisTab[2];
		}
		//Avril
		else if(this.jour>90 && this.jour<=120)
		{
			this.jourDuMois = this.jour-90;
			this.mois = moisTab[3];
		}
		//Mai
		else if(this.jour>120 && this.jour<=151)
		{
			this.jourDuMois = this.jour -120;
			this.mois = moisTab[4];
		}
		//Juin
		else if(this.jour>151 && this.jour<=181)
		{
			this.jourDuMois = this.jour -151;
			this.mois = moisTab[5];
		}
		//Juillet
		else if(this.jour>181 && this.jour<=212)
		{
			this.jourDuMois = this.jour-181;
			this.mois = moisTab[6];
		}
		//Aout
		else if(this.jour>212 && this.jour<=243)
		{
			this.jourDuMois = this.jour -212;
			this.mois = moisTab[7];
		}
		//Septembre
		else if(this.jour>243 && this.jour<=273)
		{
			this.jourDuMois = this.jour - 243;
			this.mois = moisTab[8];
		}
		//Octobre
		else if(this.jour>273 && this.jour<=304)
		{
			this.jourDuMois = this.jour -273;
			this.mois = moisTab[9];
		}
		//Novembre
		else if(this.jour>304 && this.jour<=334)
		{
			this.jourDuMois = this.jour -304;
			this.mois = moisTab[10];
		}
		//Décembre
		else if(this.jour>334 && this.jour<=365)
		{
			this.jourDuMois = this.jour -334;
			this.mois = moisTab[11];
		}
	}

/*
 * retourne le mois sous forme d'un int pour faciliter le traitement
 * retourne -1 en cas de disfonctionnement.
 */
	public int getMois(){
		for(int i=0; i<this.moisTab.length; i++){
			if(this.moisTab[i].equals(this.mois)) return i+1;
		}
		return -1;
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame m = new JFrame();
			m.add(new CalendarPanel());
			m.setVisible(true);
		});
}
}