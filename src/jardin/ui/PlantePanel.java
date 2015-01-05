package jardin.ui;

import jardin.plante.Plante;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;

public class PlantePanel extends JPanel implements ListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	private JPanel plantePanel;
	private JLabel image;
	private JLabel name, desc;
	
	public PlantePanel() {
		super();
		name = new JLabel();
		desc = new JLabel();
		image = new JLabel();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		Plante p = (Plante) value;
		//Recuperation de l'image de la plante.
		//ImageIcon img = p.getImgFleurie();
		ImageIcon img = new ImageIcon("res/Img/test.png");
		
		//Creation des labels pour l'affichage du nom et la description des plantes.
		//name = new JLabel("Nom : " + p.getNom());
		//desc = new JLabel("Description : " + p.getDescription());
		name.setText(p.getNom());
		desc.setText(p.getDescription());
		
		//Label contenant l'image de la plante.
		image.setIcon(img);
		this.setLayout(new FlowLayout());
		this.add(image);
		this.add(name);
		this.add(desc);
		return this;
	}
	
}
