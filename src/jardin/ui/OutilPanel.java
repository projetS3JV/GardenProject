package jardin.ui;

import jardin.Ensoleillement;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

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
	private JLabel typeZone = new JLabel();
	private JLabel ensoleillement = new JLabel();	
	private JLabel typeSol = new JLabel();
	private JLabel plante = new JLabel();
	private JButton change = new JButton("modifier");
	
	private AbstractZone selected;
	
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
		
		
		zonePanel.setLayout(new GridLayout(5, 1));
		typeZone.setFont(getFont().deriveFont(Font.BOLD, 25));
		
		zonePanel.add(typeZone);
		zonePanel.add(ensoleillement);
		zonePanel.add(typeSol);
		zonePanel.add(plante);
		zonePanel.add(change);
		zonePanel.setVisible(false);
		
		change.addActionListener(e->{
			EditZoneFrame.showEditZoneFrame(MainFrame.getInstance());
			OutilPanel.this.updateZonePanel(selected);
			MainFrame.getInstance().getJardinPanel().repaint();
		});

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
		selected = z;
		boolean b = z == null;
		this.zonePanel.setVisible(b);
		
		//Si z n'est pas null on met a jour le panel
		if(!b) {
			this.zonePanel.setVisible(true);
			if (z instanceof Zone) {
				this.typeZone.setText("Zone");
				this.typeSol.setText("");
				this.plante.setText("");
			} else { // donc c'est une zone plantable
				ZonePlantable p = (ZonePlantable) z;
				this.typeZone.setText("Zone Plantable");
				this.typeSol.setText("Type de sol : " + TypeSol.getTypeSol(p.getTypeSol()));
				
				Plante pl = p.getPlante();
				if (pl != null) {
					this.plante.setText("Plante : " + pl.getNom() + " " + pl.getNomL());
				} else {
					this.plante.setText("Plante : aucune");
				}
			}
			this.ensoleillement.setText("Ensoleillement : " + Ensoleillement.getEnsoleillement(z.getEnsoleillement()));
			
		} else { // sinon la zone est nulle
			this.zonePanel.setVisible(false);
		}
	}
}
