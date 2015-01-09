package jardin.ui;

import jardin.plante.Plante;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OutilPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//private PlantothequePanel plantothequePanel= new PlantothequePanel();
	
	private JButton tracer = new JButton(new ImageIcon("res/img/tracer.gif"));
	private JButton tracerP = new JButton(new ImageIcon("res/img/tracerP.gif"));
	private JButton effacer = new JButton(new ImageIcon("res/img/effacer.gif"));
	public OutilPanel(){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Création du pannel qui va recevoir les boutons déclenchant les outils
		JPanel outilsMiseEnPage = new JPanel();
		outilsMiseEnPage.setMaximumSize(new Dimension(300,400));
		outilsMiseEnPage.setMinimumSize(new Dimension(300,400));
		outilsMiseEnPage.setPreferredSize(new Dimension(300,400));
		JPanel outils = new JPanel();
		outils.setLayout(new GridLayout( 2,1));
		
		this.tracer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode traçage de zone
				MainFrame.getInstance().getJardinPanel().startDrawing(false);
			}
		});
		
		this.tracerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lancer méthode traçage de zone
				MainFrame.getInstance().getJardinPanel().startDrawing(true);
			}
		});
		
		this.effacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().getJardinPanel().deleteSelected();
			}
		});
		outils.add(this.tracer);
		outils.add(this.tracerP);
		outils.add(this.effacer);

		outilsMiseEnPage.add(outils);
		this.add(outilsMiseEnPage);
		
		this.tracer.setEnabled(false);
		this.effacer.setEnabled(false);
		//this.outilsPanel
	}
	
	public void enableItems() {
		this.tracer.setEnabled(true);
		this.effacer.setEnabled(true);
	}
}
