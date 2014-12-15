package jardin.ui;

import jardin.AccesBD;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PlantePanel extends JLabel {
	private JPanel plantePanel;
	private AccesBD instance = AccesBD.getInstance();
	
	public PlantePanel(int id) {
		plantePanel = new JPanel();
		
		//Récupération de l'image de la plante.
		ImageIcon img = instance.getPlante(id).getImgFleurie();
		
		//Création des labels pour l'affichage du nom et la description des plantes.
		JLabel name = new JLabel("Nom : " + instance.getPlante(id).getNom());
		JLabel desc = new JLabel("Description : " + instance.getPlante(id).getDescription());
		//Label contenant l'image de la plante.
		JLabel image = new JLabel();
		image.setIcon(img);
		
		JSplitPane split = new JSplitPane();
	    split.setLeftComponent(image);
	    split.setRightComponent(name);
	    split.setRightComponent(desc);
	    plantePanel.add(split);
	}
}
