package jardin.ui;

import jardin.plante.Plante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class PlantePanel extends JPanel implements ListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	private JPanel plantePanel;
	private JLabel image;
	private JLabel name, desc;
	
	public PlantePanel() {
		super();
		name = new JLabel("test", SwingConstants.CENTER);
		desc = new JLabel();
		image = new JLabel();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		Plante p = (Plante) value;
		//Recuperation de l'image de la plante.res/Img/test.png
		ImageIcon img = p.getImgFleurie();
		
		//Creation des labels pour l'affichage du nom et la description des plantes.
		name = new JLabel(p.getNom());
		desc = new JLabel("Description : " + p.getDescription());
		
		//Label contenant l'image de la plante.
		image.setIcon(img);
		this.setLayout(new BorderLayout());
		this.add(image, BorderLayout.WEST);
		this.add(name, BorderLayout.NORTH);
		this.add(desc, BorderLayout.CENTER);
		return this;
	}
	
}
