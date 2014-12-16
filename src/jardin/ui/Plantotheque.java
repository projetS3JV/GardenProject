package jardin.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jardin.AccesBD;
import jardin.plante.Plante;
import jardin.zone.ZonePlantable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Plantotheque extends JFrame {
	private AccesBD instance = AccesBD.getInstance();
	private Plante select = null;
	private JList listePlante;
	
	public Plantotheque() {
		
		//Cr�ation de la grille contenant le descriptif des plantes en fonction du nombre de la plante.
		int nbPlante = instance.getPlantes().size();
		
		listePlante = new JList<PlantePanel>(/*instance.getPlanteListeModel()*/);
		listePlante.setCellRenderer(new PlantePanel());
		for(int i = 0; i < nbPlante; i++) {
			PlantePanel tmp = new PlantePanel();
			listePlante.add(new JScrollPane(tmp));
		}
		
		//Cr�ation de la barre de d�filement et des boutons Ajouter et Annuler.
		
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		//Ajout des actionListener sur les boutons.
		
		ajouter.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				//R�cup�rer la zone s�lectionn�
				//ZonePlantable.setPlante(select);
			}
		});
		
		annuler.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				Plantotheque.this.setVisible(false);
			}
		});;
	}
	
	/**
	 * M�thode mettant en m�moire la plante s�lectionn�e.
	 */
	public void setSelected(PlantePanel plantePanel, int id) {
		select = (Plante) this.listePlante.getSelectedValue();
	}
	
	public static void main(String arg[]) {
		Plantotheque p = new Plantotheque();
		p.setVisible(true);
	}
	
}