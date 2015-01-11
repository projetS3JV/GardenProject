package jardin.ui;

import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * 
 * @author ROBINET félix
 *
 */
public class NewZoneOptionPanel extends JPanel {
	
	private JButton valider;
	private JComboBox<String> typeZone;
	private PlantothequePanel plante;
	
	public NewZoneOptionPanel(JardinPanel j) {
		String[] T = {"Plantable", "Zone"};
		typeZone = new JComboBox<String>(T);
		
		valider = new JButton("valider");
		valider.addActionListener(e -> {
			AbstractZone z = j.getZone();
			if(typeZone.getSelectedItem().equals("Plantable")) {
				// On recupere la plante
				// on sauvegarde un zone plantable
			}
			else {
				// on sauvegarde une zone
			}
		});
		
	}
	
	

}
