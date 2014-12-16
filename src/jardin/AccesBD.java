package jardin;

import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

public final class AccesBD {

	public static String LOCATION = "res/";

	private static AccesBD instance = null;
	private Connection connection;
	private Statement statement;
	private SortedListModel planteList;

	private AccesBD() {
		this.planteList = new SortedListModel(); // a construire !
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			this.connect("ifexists=true");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("Creating database..... ");
			try {
				this.connect("");
				this.statement.executeQuery(this.getSQL());
				System.out.print("DataBase created");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getSQL() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(LOCATION
					+ "/db.sql"));
			String s;
			String sql = "";
			while ((s = in.readLine()) != null)
				sql += s + " ";
			in.close();
			return sql;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void connect(String option) throws SQLException {
		if (option != "")
			option = ";" + option;
		this.connection = DriverManager.getConnection("jdbc:hsqldb:file:"
				+ LOCATION + "/db/db;shutdown=true" + option, "SA", "");
		this.statement = this.connection.createStatement();
	}

	public static AccesBD getInstance() {
		if (instance == null) {
			instance = new AccesBD();
		}
		return instance;
	}

	public void close() {
		try {
			this.connection.close();
			this.statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private String escape(String s) {
		return " \"" + s + "\"";
	}

	public static long datee(int year, int month, int day) {
		return new GregorianCalendar(year, month, day).getTimeInMillis();
	}

	/*
	 * Insertion d'une plante dans la base de donnée
	 */
	public void insertPlante(Plante p) {
		String sql = "INSERT INTO PLANTE VALUES (null, ?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.planteList.add(p);
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setString(1, escape(p.getNom()));
			stat.setString(2, escape(p.getNomL()));
			stat.setInt(3, p.getType());
			stat.setInt(4, p.getTailleFin());
			stat.setInt(5, p.getEnsoleillement());
			stat.setString(6, escape("p.getImgFleurie()"));
			stat.setString(7, escape("p.getImgNonFleurie()"));
			stat.setInt(8, p.getCouleur().getRGB());
			stat.setInt(9, p.getTypeSol());
			stat.setDate(10, (Date) p.getDatePlantation());
			stat.setDate(11, (Date) p.getDateFloraison());
			stat.setBoolean(12, p.isVivace());
			stat.setString(13, escape(p.getDescription()));
			stat.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void insertZone(Zone z) {
		String sql = "INSERT INTO ZONE VALUES (null,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setInt(1, z.getId());
			// stat.set(2, z.xpoints);
			// stat.set(3, z.ypoints);
			stat.setInt(4, z.getEnsoleillement());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertZonePlantable(ZonePlantable z) {
		String sql = "INSERT INTO ZONEPLANTABLE VALUES(null,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setInt(1,z.getPlante().getId());
			stat.setInt(2,z.getId()); 
			// stat.setArray(3,z.xpoints);
			// stat.setInt(4, z.ypoints);
			stat.setInt(5, z.getTypeSol());
			stat.setInt(6, z.getEnsoleillement());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertJardin(Jardin j) {
		String sql = "INSERT INTO JARDIN VALUES(null,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setString(1, escape(j.getName()));
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePlante(Plante p) throws IllegalArgumentException {
		//if (p.getId() != -1) {
			String sql = "UPDATE PLANTE SET nom_Usuel =?, nom_Latin = ?, type_Plante = ?, hauteur = ?, luminosite = ?, url_Image_En_Fleur = ?, url_Image_Sans_Fleur = ?, couleur_RGB = ?, type_Sol = ?,	date_Debut = ?,	date_Fin = ?, vivace = ?, description = ? WHERE id="
					+ p.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setString(1, escape(p.getNom()));
				stat.setString(2, escape(p.getNomL()));
				stat.setInt(3, p.getType());
				stat.setInt(4, p.getTailleFin());
				stat.setInt(5, p.getEnsoleillement());
				stat.setString(6, escape("p.getImgFleurie()"));
				stat.setString(7, escape("p.getImgNonFleurie()"));
				stat.setInt(8, p.getCouleur().getRGB());
				stat.setInt(9, p.getTypeSol());
				stat.setDate(10, (Date) p.getDatePlantation());
				stat.setDate(11, (Date) p.getDateFloraison());
				stat.setBoolean(12, p.isVivace());
				stat.setString(13, escape(p.getDescription()));
				stat.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}/*
		} else {
			throw new IllegalArgumentException(
					"Plante pas dans la base de données");
		}*/

	}

	private void updateZone(Zone z) throws IllegalArgumentException {
		if (z.getId() != -1) {
			String sql = "UPDATE INTO ZONE SET id_Jardin =?, x =?, y =?, luminosite =? WHERE id = "+ z.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setInt(1, z.getId());
				// stat.set(2, z.xpoints);
				// stat.set(3, z.ypoints);
				stat.setInt(4, z.getEnsoleillement());
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Zone pas dans la base de données");
		}
	}

	private void updateZonePlantable(ZonePlantable z) throws IllegalArgumentException {
		if (z.getId() != -1) {
			String sql = "UPDATE ZONEPLANTABLE SET id_Plante =?, id_Zone=?,	x =?, y =?,	type_Sol =?, luminosite =? WHERE id = "+ z.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setInt(1,z.getPlante().getId());
				stat.setInt(2,z.getId());
				// stat.setArray(3,z.xpoints);
				// stat.setInt(4, z.ypoints);
				stat.setInt(5, z.getTypeSol());
				stat.setInt(6, z.getEnsoleillement());
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Zone pas dans la base de données");
		}
	}

	public void updateJardin(Jardin j) throws IllegalArgumentException {
		if (j.getId() != -1) {
			String sql = "UPDATE JARDIN SET name=? WHERE id ="+j.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setString(1, escape(j.getName()));
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Jardin pas dans la base de données");
		}
	}

	public void deletePlante(int id) {
		String sql = "DELETE FROM PLANTE WHERE id =" + id;
		this.planteList.remove(id);
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteZone(int id) {
		String sql = "DELETE FROM ZONE WHERE id =" + id;
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteZonePlantable(int id) {
		String sql = "DELETE FROM ZONEPLANTABLE PLANTE WHERE id =" + id;
		try {
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void deleteJardin(int id) {
		String sql = "DELETE FROM JARDIN WHERE id =" + id;
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Plante getPlante(int id) throws IllegalArgumentException{
		return this.planteList.getElementById(id);
	}

	public Jardin getJardin(int id) {
		return null;
	}

	private ArrayList<Zone> getZones() {
		return null;
	}

	private ArrayList<Zone> getZonePlantables() {
		return null;
	}

	public SortedListModel getPlantes() {
		return this.planteList;
	}

	public ArrayList<Jardin> getJardins() {
		return null;
	}

	public static void main(String[] args) {
		AccesBD bd = AccesBD.getInstance();
		Plante p = new Plante(10, new Date(datee(2010, Calendar.FEBRUARY, 15)),
				new Date(datee(2010, Calendar.AUGUST, 15)), Color.blue, true,
				"popol", "popolus patatus", new ImageIcon("/bla/img1.png"),
				new ImageIcon("/bla/img2.png"), TypePlante.FLEUR,
				Ensoleillement.SOLEIL, TypeSol.LIMONEUX,
				"c'est une zolie fleur");
		//bd.insertPlante(p);
		//bd.updatePlante(p);
		
		bd.deletePlante(7);
		bd.close();
	}

	
	// Pense bête --------------->
	// Reprendre les id lors des updates
}
