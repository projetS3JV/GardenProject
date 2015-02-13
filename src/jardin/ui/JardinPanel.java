package jardin.ui;

import jardin.AccesBD;
import jardin.Ensoleillement;
import jardin.Jardin;
import jardin.TypeSol;
import jardin.plante.Plante;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private AbstractZone selected = null;
	private AccesBD bd = AccesBD.getInstance();
	private boolean plantable = false;
	private boolean remove = false;
	private CalendarPanel cal;


	public JardinPanel(Jardin j) {
		this.jardin = j;
		this.zone = new AbstractZone();
		this.setFocusable(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (jardin != null && !draw) {
					selected = jardin.getZone(e.getX(), e.getY());
					MainFrame.getInstance().getOutilPanel().updateZonePanel(selected);
					repaint();
				}
				if (jardin != null && remove) {
					if (selected != null)
						remove = false;
					if (!remove) {
						JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						deleteSelected();
					}
				}
				if (draw & e.getButton() == MouseEvent.BUTTON1) {
					px = e.getX();
					py = e.getY();

					zone.addPoint(px, py);
					draw = !zone.isClosed();
					if (!draw) {
						JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						if (plantable)
							saveZonePlantable(zone);
						else 
							saveZone(zone);
						zone = new AbstractZone();
					}
					if (zone.npoints == 1 && plantable) {
						AbstractZone supZone = jardin.getZone(px, py);
						if (!(supZone instanceof Zone)) {
							JOptionPane.showMessageDialog(JardinPanel.this, "Impossible de mettre une zone plantable hors d'une zone", "error",JOptionPane.ERROR_MESSAGE);
							draw = false;
							zone = new AbstractZone();
							JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
				}
				if (zone.npoints > 1)
					repaint();
			}
		});

		/**
		 * Listener sur la touche Echap pour annuler une zone en pleine création
		 */
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE && JardinPanel.this.draw){
					JardinPanel.this.draw = false;
					JardinPanel.this.zone = new AbstractZone();
					JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					JardinPanel.this.repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && remove) {
					JardinPanel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					remove = false;
				}
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
	}

	private void drawJardin(Graphics g) {
		for (AbstractZone zone : jardin.getZones()) {
			drawZone(g, zone);
		}
	}

	private void drawZone(Graphics g, AbstractZone z) {
		if (z instanceof ZonePlantable) {
			ZonePlantable zone = (ZonePlantable) z;
			if (selected != null && z.equals(this.selected)) 
				g.setColor(Color.red);
			else g.setColor(Color.black);	
			g.drawPolygon(zone);
			if (zone.getPlante() != null) {
				int d = MainFrame.getInstance().getCalendarPanel().getJour();
				for(int i = 0; i < zone.getPlante().getDateFloraison().size(); i++){
					int d1 = sqlDateToCalendar(zone.getPlante().getDateFloraison().get(i)).get(Calendar.DAY_OF_YEAR);
					int d2 = sqlDateToCalendar(zone.getPlante().getDateFinFloraison().get(i)).get(Calendar.DAY_OF_YEAR);
					if (d >= d1 && d <= d2) {
						g.setColor(zone.getPlante().getCouleur_en_fleur());			
						g.fillPolygon(zone);
					}
					else {
						g.setColor(zone.getPlante().getCouleur_non_fleuris());			
						g.fillPolygon(zone);
					}
				}
			}
		}
		else {
			Zone zone = (Zone) z;
			if (selected != null && z.equals(this.selected))
				g.setColor(Color.red);	
			else g.setColor(Color.black);		
			g.drawPolygon(zone);
			for (AbstractZone i : zone.getZones())
				drawZone(g, i);
		}
	}

	public AbstractZone getSelected() {
		return selected;
	}

	public Jardin getJardin() {
		return jardin;
	}	

	public void setJardin(Jardin j) {
		this.jardin = j;
		if (this.jardin == null)
			this.setBackground(Color.GRAY);
		else
			this.setBackground(Color.WHITE);
		this.repaint();
	}

	public void startDrawing(boolean plantable) {
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		this.draw = true;
		this.plantable = plantable;
		this.requestFocus();
	}


	@Deprecated
	public AbstractZone getZone() {
		if (draw) return null; 
		return zone;
	}

	private void saveZone(AbstractZone z) {
		z.setEnsoleillement(this.setEnsoleillement());
		Zone zone = new Zone(z);
		jardin.addZone(zone);	
		if (jardin.getId() != -1)
			bd.insertZone(zone, jardin.getId());
		this.repaint();
	}

	private void saveZonePlantable(AbstractZone z) {
		z.setEnsoleillement(this.setEnsoleillement());
		int sol = this.setTypeSol();
		ZonePlantable zone = new ZonePlantable(z,sol);
		Zone supZone = (Zone) jardin.getZone(z.xpoints[0], z.ypoints[0]);
		supZone.addZone(zone);
		if (jardin.getId() != -1)
			bd.insertZonePlantable(zone, supZone.getId());
		this.repaint();
	}

	private int setEnsoleillement() {
		JDialog j = new JDialog(MainFrame.getInstance(), "Ensoleillement", true);
		j.setSize(400, 200);
		j.setLayout(new BoxLayout(j.getContentPane(), BoxLayout.Y_AXIS));

		JLabel ensoleillement = new JLabel("Ensoleillement");
		Ensoleillement[] ensoleillementBoxItems = {Ensoleillement.MIOMBRE, Ensoleillement.OMBRE, Ensoleillement.SOLEIL};
		JComboBox<Ensoleillement> ensoleillementComboBox = new JComboBox<>(ensoleillementBoxItems);
		JButton validButton = new JButton("Valider");
		validButton.addActionListener(e -> {
			j.dispose();	
		});
		j.add(ensoleillement);
		j.add(ensoleillementComboBox);
		j.add(validButton);
		j.pack();
		j.setVisible(true);
		return ((Ensoleillement)ensoleillementComboBox.getSelectedItem()).getValue();
	}

	private int setTypeSol() {
		JDialog j = new JDialog(MainFrame.getInstance(), "Ensoleillement", true);
		j.setSize(400, 200);
		j.setLayout(new BoxLayout(j.getContentPane(), BoxLayout.Y_AXIS));

		JLabel typeSol = new JLabel("Type de sol");
		TypeSol[] typeSolBoxItems = {TypeSol.ARGILEUX, TypeSol.CALCAIRE, TypeSol.HUMIFERE, TypeSol.LIMONEUX, TypeSol.SABLEUX};
		JComboBox<TypeSol> typeSolComboBox = new JComboBox<>(typeSolBoxItems);
		JButton validButton = new JButton("Valider");
		validButton.addActionListener(e -> {
			j.dispose();	
		});
		j.add(typeSol);
		j.add(typeSolComboBox);
		j.add(validButton);
		j.pack();
		j.setVisible(true);
		return ((TypeSol)typeSolComboBox.getSelectedItem()).getValue();
	}

	public void deleteSelected() {
		if (this.selected != null) {
			if (selected instanceof Zone) {
				this.jardin.deleteZone((Zone)this.selected);
				this.bd.deleteZone((Zone)this.selected);
			}
			else {
				ZonePlantable zp = (ZonePlantable) selected;
				zp.getZoneConteneur().deleteZone(selected);
				this.bd.deleteZonePlantable(zp);
			}
			repaint();
		}
		else {
			this.requestFocus();
			this.remove = true;
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	public static GregorianCalendar sqlDateToCalendar(Date d) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(d.getTime());
		return cal;
	}

	public void setPlante(Plante p) {
		if (p != null && this.selected instanceof ZonePlantable) {
			((ZonePlantable) this.selected).setPlante(p);
		}
	}


}
