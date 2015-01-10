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
		pLeft.setLayout(new BoxLayout(MainFrame.getInstance().getContentPane(), BoxLayout.X_AXIS));
		pLeft.add(image);
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new BoxLayout(MainFrame.getInstance().getContentPane(), BoxLayout.X_AXIS));
		pRight.add(name);
		pRight.add(desc);
		
		this.setLayout(new BoxLayout(MainFrame.getInstance().getContentPane(), BoxLayout.Y_AXIS));
		this.add(pLeft);
		this.add(pRight);
		return this;
	}	
}
