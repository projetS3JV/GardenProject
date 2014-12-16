package jardin.ui;

import jardin.plante.Plante;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;

public class PlantePanel extends JLabel implements ListCellRenderer{
	private JPanel plantePanel;
	private JLabel image = new JLabel();
	private JLabel name, desc;
	
	public PlantePanel() {
		super();
		plantePanel = new JPanel();
		plantePanel.setSize(100, 50);
		
		JSplitPane split = new JSplitPane();
	    split.setLeftComponent(image);
	    split.setRightComponent(name);
	    split.setRightComponent(desc);
	    plantePanel.add(split);
	    this.add(plantePanel);
	}

	//@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		Plante p = (Plante) value;
		//Récupération de l'image de la plante.
		//ImageIcon img = p.getImgFleurie();
		ImageIcon img = new ImageIcon("res/Img/test.png");
		
		//Création des labels pour l'affichage du nom et la description des plantes.
		//name = new JLabel("Nom : " + p.getNom());
		//desc = new JLabel("Description : " + p.getDescription());
		name = new JLabel("Nom : RaindowDash");
		desc = new JLabel("Description : Poney");
		
		//Label contenant l'image de la plante.
		image.setIcon(img);
		return this;
	}
	
}
