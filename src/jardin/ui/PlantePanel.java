package jardin.ui;

import jardin.plante.Plante;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class PlantePanel extends JPanel implements ListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	private JPanel plantePanel;
	private JLabel image;
	private JLabel name, desc, vide;
	
	public PlantePanel() {
		super();
		name = new JLabel("test", SwingConstants.LEFT);
		desc = new JLabel();
		image = new JLabel();
		vide = new JLabel();
		this.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Plante p = (Plante) value; //res/img/test.png
		
		if (isSelected)
			this.setBackground(list.getSelectionBackground());
		else 
			this.setBackground(Color.WHITE);
		
		ImageIcon img = p.getImgFleurie();
		this.name.setText((p.getNom() + " / " + p.getNomL()).toUpperCase());
		this.name.setHorizontalAlignment(JLabel.CENTER);
		this.desc.setText("Description : " + p.getDescription() + "\n" + "Ensoleillement : ");
		
		//Label contenant l'image de la plante.
		image.setIcon(img);
		
		this.setLayout(new GridLayout(2,2));
		this.add(name);
		this.add(vide);
		this.add(desc);
		this.add(image);
		
		
		return this;
	}	
}
