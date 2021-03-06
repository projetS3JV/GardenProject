package jardin.ui;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class CalendarPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int jour = 1; //récupère la valeur de la scrollbar, le nième jour de l'année
	private int jourDuMois = 1; //numero du jour dans le mois, utilisé pour le getDate et texteDate
	private  String mois = "Janvier";
	private String texteDate = this.jourDuMois + " " +this.mois; //La date sous la forme "DD Month" pour l'affichage
	private static String[] moisTab = new String[]{"Janvier", "Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
	private int month = 0;
	
	//éléments graphiques
	private JScrollBar scrollbar = new JScrollBar(java.awt.Adjustable.HORIZONTAL, 1, 1, 1, 366);
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
		//Janvier
		if(this.jour<=31){
			this.jourDuMois = this.jour;
			this.mois = moisTab[0];
			this.month = 1;
		}
		//Février
		else if(this.jour>31 && this.jour<=59)
		{
			this.jourDuMois = this.jour- 31;
			this.mois = moisTab[1];
			this.month = 2;
		}
		//Mars
		else if(this.jour>59 && this.jour<=90)
		{
			this.jourDuMois = this.jour-59;
			this.mois = moisTab[2];
			this.month = 3;
		}
		//Avril
		else if(this.jour>90 && this.jour<=120)
		{
			this.jourDuMois = this.jour-90;
			this.mois = moisTab[3];
			this.month = 4;
		}
		//Mai
		else if(this.jour>120 && this.jour<=151)
		{
			this.jourDuMois = this.jour -120;
			this.mois = moisTab[4];
			this.month = 5;
		}
		//Juin
		else if(this.jour>151 && this.jour<=181)
		{
			this.jourDuMois = this.jour -151;
			this.mois = moisTab[5];
			this.month = 6;
		}
		//Juillet
		else if(this.jour>181 && this.jour<=212)
		{
			this.jourDuMois = this.jour-181;
			this.mois = moisTab[6];
			this.month = 7;
		}
		//Aout
		else if(this.jour>212 && this.jour<=243)
		{
			this.jourDuMois = this.jour -212;
			this.mois = moisTab[7];
			this.month = 8;
		}
		//Septembre
		else if(this.jour>243 && this.jour<=273)
		{
			this.jourDuMois = this.jour - 243;
			this.mois = moisTab[8];
			this.month = 9;
		}
		//Octobre
		else if(this.jour>273 && this.jour<=304)
		{
			this.jourDuMois = this.jour -273;
			this.mois = moisTab[9];
			this.month = 10;
		}
		//Novembre
		else if(this.jour>304 && this.jour<=334)
		{
			this.jourDuMois = this.jour -304;
			this.mois = moisTab[10];
			this.month = 11;
		}
		//Décembre
		else
		{
			this.jourDuMois = this.jour -334;
			this.mois = moisTab[11];
			this.month = 12;
		}
		this.texteDate = this.jourDuMois + " " + this. mois;
	}

    /**
	 * Méthode appelée lorsque l'on bouge la scrollbar.
	 * Appelle la méthode updateDate() puis modifie le JLabel à coté de la scrollbar en conséquence
	 */
		private void update(){
			this.updateDate();
			this.labelDate.setText(this.texteDate);	
			MainFrame.getInstance().getJardinPanel().repaint();
		}

/**
  * retourne la date sous forme d'une date pour faciliter le traitement
  * retourne -1 en cas de disfonctionnement.
  */
	public Date getDate(){
		
		for(int i=0; i < CalendarPanel.moisTab.length; i++){
			if(CalendarPanel.moisTab[i].equals(this.mois)){
				long timeStamp = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), i, this.jourDuMois).getTimeInMillis();
				return new Date(timeStamp);
			}
		}
		return null;
	}
	
	public int getJour() {
		return jour;
	}

}