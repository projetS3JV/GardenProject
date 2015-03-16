package jardin.ui;

import jardin.plante.Plante;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class PlantePanel extends JPanel implements ListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	private JLabel image;
	private JLabel name, desc, vide;
	private JTextArea d;
	
	public PlantePanel() {
		super();
		name = new JLabel("test", SwingConstants.LEFT);
		d = new JTextArea();
		d.setLineWrap(true);
		d.setWrapStyleWord(true);
		image = new JLabel();
		vide = new JLabel();
		this.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Plante p = (Plante) value;
		
		if (isSelected){
			this.setBackground(list.getSelectionBackground());
			PlantePanel tmp = PlantePanel.this;
			PlantothequeFrame.setSelected(tmp, p);
		}else 
			this.setBackground(Color.WHITE);
		
		this.name.setText((p.getNom() + " / " + p.getNomL()).toUpperCase());
		this.name.setHorizontalAlignment(JLabel.CENTER);
		this.d.setText("Description : " + p.getDescription() + "\n" + "Ensoleillement : ");
		
		//Label contenant l'image de la plante.
		//ImageIcon img = p.getImgFleurie();
		image.setIcon(p.getImgFleurie());
		
		this.setLayout(new GridLayout(2,2));
		this.add(name);
		this.add(vide);
		this.add(d);
		this.add(image);
		
		return this;
	}	
}
