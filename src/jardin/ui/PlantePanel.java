package jardin.ui;

import jardin.plante.Plante;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
		if (isSelected)
			this.setBackground(list.getSelectionBackground());
		else 
			this.setBackground(Color.WHITE);
		
		Plante p = (Plante) value; //res/img/test.png
		ImageIcon img = p.getImgFleurie();
		this.name.setText(p.getNom() + " / " + p.getNomL());
		this.desc.setText("Description : " + p.getDescription());
		
		//Label contenant l'image de la plante.
		image.setIcon(img);
		
		JPanel pLeft = new JPanel();
		pLeft.add(image);
		pLeft.setSize(50, 50);
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new GridLayout(2,1));
		pRight.add(name);
		pRight.add(desc);
		pRight.setSize(200, 50);
		
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(pLeft);
		this.add(pRight);
		return this;
	}	
}
