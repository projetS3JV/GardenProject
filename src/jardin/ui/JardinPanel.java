package jardin.ui;

import jardin.Jardin;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author robin021
 *
 */
public class JardinPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private Jardin jardin;
	
	public JardinPanel(Jardin jardin) {
		this.jardin = jardin;
	}
	
	public JardinPanel() {
		this.jardin = null;
	}
	
	@Override
	public void paint(Graphics g) {
		if (this.jardin != null) {
			super.paint(g);
			drawJardin(g);
		}
	}
	
	private void drawJardin(Graphics g) {
		for (AbstractZone zone : jardin.getZones()) {
			drawZone(g, zone);
		}
	}
	
	private void drawZone(Graphics g, AbstractZone z) {
		if (z instanceof ZonePlantable) {
			ZonePlantable zone = (ZonePlantable) z;
			g.setColor(Color.black);			
			g.drawPolygon(zone);
			g.setColor(zone.getPlante().getCouleur_en_fleur());			
			g.fillPolygon(zone);
		} 
		else {
			Zone zone = (Zone) z;
			g.setColor(Color.black);			
			g.drawPolygon(zone);
			for (AbstractZone i : zone.getZones())
				drawZone(g, i);
		}
	}
	
	public Jardin getJardin() {
		return jardin;
	}	
	
	public void setJardin(Jardin j) {
		this.jardin = j;
	}

}
