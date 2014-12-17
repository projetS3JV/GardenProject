package jardin.ui;

import jardin.Jardin;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author robin021
 *
 */
public class JardinPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private Jardin jardin;
	private AbstractZone zone;
	private boolean draw = false;
	private int px, py, tx, ty;
	
	
	public JardinPanel(Jardin jardin) {
		this.jardin = jardin;
		this.zone = new AbstractZone();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (draw) {
					px = e.getX();
					py = e.getY();
					zone.addPoint(px, py);
					draw = !zone.isClosed();
					if (!draw)
						JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				if (zone.npoints > 1)
					repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (draw) {
					if (zone.npoints > 0) {
						tx = e.getX();
						ty = e.getY();
						repaint();
					}
					
				}
			}
			
		});
	}
	
	public JardinPanel() {
		this(null);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (this.jardin != null) {			
			drawJardin(g);
		}
		if(draw) {
			g.drawLine(px, py, tx, ty);
			int n = zone.npoints;
			for (int i = 0; i < n; i++) {
				int x1 = zone.xpoints[i];
				int y1 = zone.ypoints[i];
				if (i < n - 1) {
					int x2 = zone.xpoints[i + 1];
					int y2 = zone.ypoints[i + 1];
					g.drawLine(x1 - 4, y1 - 4, x2 - 4, y2 - 4);
				}
				g.fillOval(x1 - 3, y1 - 3, 6, 6);
			}
		}
		else g.drawPolygon(this.zone); // a enlever
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
		this.setBackground(Color.WHITE);
		this.repaint();
	}
	
	public void startDrawing() {
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		this.draw = true;
	}
	
	public AbstractZone getZone() {
		if (draw) return null; 
		return zone;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			JardinPanel test = new JardinPanel();
			test.startDrawing();
			frame.add(test);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);			
			
		});
	}
	

}
