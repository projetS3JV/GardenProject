package jardin;

import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

public final class AccesBD {
	
	public static String LOCATION = "./res";

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
			BufferedReader in = new BufferedReader(new FileReader(LOCATION + "/db.sql"));
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
		return " \"" + s +" \"";
	}
	
	public static long datee (int year, int month, int day){
		return new GregorianCalendar(year,month,day).getTimeInMillis();
	}
	/*
	 * Insertion d'une plante dans la base de donnée
	 */
	public void insertPlante(Plante p) {
		String sql = "INSERT INTO plante values(null, " + escape(p.getNom()) + ","
				+ escape(p.getNomL()) + ","
				+ p.getType() + ","
				+ p.getTailleFin() + ","
				+ p.getEnsoleillement() + ","
				+ p.getImgFleurie() + ","
				+ p.getImgNonFleurie() + ","
				+ escape(p.getCouleur()) + ","
				+ p.getTypeSol() + ","
				+ p.getDatePlantation() + ","
				+ p.getDateFloraison() + ","
				+ p.isVivace() + ","
				+ escape(p.getDescription()) + ")"; 
		try {
			System.out.println(sql);
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public ArrayList<Plante> getPlantes(int id) {return null;}
	public ArrayList<Jardin> getJardins(int id) {return null;}
	

	public static void main(String[] args) {
		AccesBD bd = AccesBD.getInstance();
		Plante p = new Plante(10, new Date(datee(2010,03,15)), new Date(datee(2010,07,15)), 
								"bleu", true, "popol","popolus patatus", new ImageIcon("/bla/img1.png"), 
								new ImageIcon("/bla/img2.png") , TypePlante.FLEUR, Ensoleillement.SOLEIL, 
								TypeSol.LIMONEUX, "c'est une zolie fleur");
		bd.insertPlante(p);
		bd.close();
	}
	
	
	
}
