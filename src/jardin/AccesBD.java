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
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

public final class AccesBD {
	
	public static String LOCATION = "res/";

	private static AccesBD instance = null;
	private Connection connection;
	private Statement statement;
	
	private AccesBD() {
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
			} catch (SQLException e1) {e1.printStackTrace();}
		}
	}
	
	private String getSQL() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.home") + "/db.sql"));
			String s;
			String sql = "";
			while ((s = in.readLine()) != null)
				sql += s + " ";
			in.close();
			return sql;
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	private void connect(String option) throws SQLException {
		if (option != "") option = ";" + option;
		this.connection = DriverManager.getConnection(
				"jdbc:hsqldb:file:" + LOCATION + "/db/db;shutdown=true"
				+ option, "SA", "");
		this.statement = this.connection.createStatement();
	}
	
	public static AccesBD getInstance() {
		if (instance == null) {
			instance =  new AccesBD();
		}
		return instance;
	}
	
	public void close() {
		try {
			this.connection.close();
			this.statement.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	private String escape (String s){
		return " \"" + s +"\"";
	}
	
	public static long datee (int year, int month, int day){
		return new GregorianCalendar(year,month,day).getTimeInMillis();
	}
	/*
	 * Insertion d'une plante dans la base de donnée
	 * + escape(p.getNomL()) + ","
				+ p.getType() + ","
				+ p.getTailleFin() + ","
				+ p.getEnsoleillement() + ","
				+ escape("p.getImgFleurie()") + "," // a modifier plus tard
				+ escape("p.getImgNonFleurie()") + ","
				+ p.getCouleur().getRGB() + ","
				+ p.getTypeSol() + ","
				+ p.getDatePlantation() + ","
				+ p.getDateFloraison() + ","
				+ p.isVivace() + ","
				+ escape(p.getDescription()) + ")"; 
	 */
	public void insertPlante(Plante p) {
		String sql = "INSERT INTO PLANTE VALUES (null, ?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setString(1,escape(p.getNom()));
			stat.setString(2,escape(p.getNomL()));
			stat.setInt(3, p.getType());
			stat.setInt(4, p.getTailleFin());
			stat.setInt(5, p.getEnsoleillement());
			stat.setString(6, escape("p.getImgFleurie()"));
			stat.setString(7, escape("p.getImgNonFleurie()"));
			stat.setInt(8, p.getCouleur().getRGB());
			stat.setInt(9,p.getTypeSol());
			stat.setDate(10, (Date) p.getDatePlantation()); 
			stat.setDate(11,(Date) p.getDateFloraison());
			stat.setBoolean(12, p.isVivace());
			stat.setString(13, escape(p.getDescription()));
			try {
				System.out.println(sql);
				stat.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	private void insertZone(Zone z) {}
	private void insertZonePlantable(ZonePlantable z) {}
	public void insertJardin(Jardin j) {}
	
	public void updatePlante(Plante p) {}
	private void updateZone(Zone z) {}
	private void updateZonePlantable(ZonePlantable z) {}
	public void updateJardin(Jardin j) {}
	
	public void deletePlante(int id) {}
	private void deleteZone(int id) {}
	private void deleteZonePlantable(int id) {}
	public void deleteJardin(int id) {}
	
	public Plante getPlante(int id) {return null;}
	public Jardin getJardin(int id) {return null;}
	
	private ArrayList<Zone> getZones() {return null;}
	private ArrayList<Zone> getZonePlantables() {return null;}
	public ArrayList<Plante> getPlantes() {return null;}
	public ArrayList<Jardin> getJardins() {return null;}
	

	public static void main(String[] args) {
		AccesBD bd = AccesBD.getInstance();
		Plante p = new Plante(10, new Date(datee(2010,03,15)), new Date(datee(2010,07,15)), 
								Color.blue, true, "popol","popolus patatus", new ImageIcon("/bla/img1.png"), 
								new ImageIcon("/bla/img2.png") , TypePlante.FLEUR, Ensoleillement.SOLEIL, 
								TypeSol.LIMONEUX, "c'est une zolie fleur");
		bd.insertPlante(p);
		bd.close();
	}
	
	
	
}
