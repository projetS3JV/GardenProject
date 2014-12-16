package jardin.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OutilPanel extends JPanel{

	private PlantothequePanel plantothequePanel= new PlantothequePanel();
	private JPanel outilsPanel = new JPanel();
	public OutilPanel(){
		super();
		this.setLayout(new GridLayout( 2,1));
		
		//Création du pannel qui va recevoir les boutons déclenchant les outils
		JButton tracer = new JButton("Tracer zone", new ImageIcon());
	
		//this.outilsPanel
		
		
	}
}
