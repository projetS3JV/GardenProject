package jardin.ui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jardin.Jardin;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import javax.swing.JPanel;

import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.types.Type;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 
 * @author robin021
 *
 */
public class JardinPanel extends JPanel{
	
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
			g.setColor(zone.getPlante().getCouleur());			
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

}
