package jardin.ui;

import jardin.AccesBD;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PlantePanel extends JPanel {
	private JPanel plantePanel;
	private int id;
	
	public PlantePanel() {
		plantePanel = new JPanel();
		
		//Récupération de l'image de la plante.
		ImageIcon img = AccesBD.getInstance().getPlante(id).getImgFleurie();
		
		//Création des labels pour l'affichage du nom et la description des plantes.
		JLabel name = new JLabel("Nom : " + AccesBD.getInstance().getPlante(id).getNom());
		JLabel desc = new JLabel("Description : " + AccesBD.getInstance().getPlante(id).getDescription());
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
