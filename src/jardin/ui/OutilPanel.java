package jardin.ui;

import jardin.zone.AbstractZone;
import jardin.zone.Zone;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OutilPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton tracer = new JButton(new ImageIcon("res/img/tracer.gif"));
	private JButton tracerP = new JButton(new ImageIcon("res/img/tracerP.gif"));
	private JButton effacer = new JButton(new ImageIcon("res/img/effacer.gif"));
	
	private JPanel zonePanel = new JPanel();
	
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
		
		
		zonePanel.setLayout(new GridLayout(1, 4));
		JLabel typeZone = new JLabel("coucou");
		typeZone.setFont(getFont().deriveFont(Font.BOLD, 25));
		
		JLabel typeSol = new JLabel();
		
		
		
		zonePanel.add(typeZone);
		zonePanel.add(typeSol);
		

		outilsMiseEnPage.add(outils);
		this.add(outilsMiseEnPage);
		this.add(zonePanel);
		
		this.enableItems(false);
		//this.outilsPanel
	}
	
	public void enableItems(boolean b) {
		this.tracer.setEnabled(b);
		this.tracerP.setEnabled(b);
		this.effacer.setEnabled(b);
	}
	
	/**
	 * Methode a apeller a chaque fois qu'une zone est selectionné ou que aucune zone est deselectionne.
	 * Affiche ou cache la partiepour la zone et met a jour ses données
	 * @param z la zonesélectionné. Peut etre a null, dans ce cas le panel ne sera pas rendu.
	 */
	public void updateZonePanel(AbstractZone z) {
		boolean b = z == null;
		this.zonePanel.setVisible(b);
		
		//Si z n'est pas null on met a jour le panel
		if(!b) {
			//TODO mise a jour du panel
			if (z instanceof Zone) {
				
			} else { // donc c'est une zone plantable
				
			}
		}
		
	}
}
