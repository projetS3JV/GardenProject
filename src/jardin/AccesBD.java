package jardin;

import jardin.plante.Plante;
import jardin.plante.TypePlante;
import jardin.zone.AbstractZone;
import jardin.zone.Zone;
import jardin.zone.ZonePlantable;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.types.Types;

public final class AccesBD {

	public static String LOCATION = "res/";

	private static AccesBD instance = null;
	private Connection connection;
	private Statement statement;
	private SortedListModel planteList;
	private String path = "res/img/";
	private String ext = ".png";

	private AccesBD() {
		this.planteList = new SortedListModel();
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			this.connect("ifexists=true");			
			this.fillPlanteList();
		} catch (ClassNotFoundException e) {    
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("Creating database..... ");
			try {
				this.connect("");
				this.statement.executeQuery(this.getSQL());
				System.out.println("DataBase created");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getSQL() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(LOCATION
					+ "db.sql"));
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
				+ LOCATION + "db/db;shutdown=true" + option, "SA", "");
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

	public static long datee(int year, int month, int day) {
		return new GregorianCalendar(year, month, day).getTimeInMillis();
	}

	/**
	 * Insertion d'une plante dans la base de donnée
	 * @param p la plante a ajouter
	 */
	public void insertPlante(Plante p) {
		//Il faut trouver une solution pour les images des plantes
		String sql = "INSERT INTO PLANTE VALUES (null, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setString(1, p.getNom());
			stat.setString(2, p.getNomL());
			stat.setInt(3, p.getType());
			stat.setInt(4, p.getTailleFin());
			stat.setInt(5, p.getEnsoleillement());
			stat.setString(6, p.getNom());
			stat.setInt(7, p.getCouleur_en_fleur().getRGB());
			stat.setInt(8, p.getCouleur_non_fleuris().getRGB());
			stat.setArray(9, (Array) p.getTypeSol());
			stat.setDate(10, (Date) p.getDatePlantation());
			stat.setArray(11, dateArrayToJDBXArray(p.getDateFloraison(), p.getNbDate()));
			stat.setArray(12, dateArrayToJDBXArray(p.getDateFinFloraison(), p.getNbDate()));
			stat.setBoolean(13, p.isVivace());
			stat.setString(14, p.getDescription());
			stat.executeUpdate();
			
			// On met un id a la plante et on l'ajoute a la liste
			ResultSet r = this.statement.executeQuery("SELECT id FROM plante");
			// on va sur le dernier indice
			while (!r.isLast())	r.next();
			p.setId(r.getInt(1));
			this.planteList.add(p);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * M�thode appel� au d�marrage de la BD pour remplir le tableau de plante
	 * By felix ROBINET QUi met des commentaires ....
	 */
	private void fillPlanteList() {
		String sql = "SELECT * FROM Plante";
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			while(rs.next()) {
				@SuppressWarnings("unchecked")
				Plante p = new Plante(
						rs.getInt(5),
						JDBCArrayTodateArray1(rs.getArray(12)),
						JDBCArrayTodateArray1(rs.getArray(13)),
						rs.getDate(11), 
						new Color(rs.getInt(8)), 
						new Color(rs.getInt(9)), 
						rs.getBoolean(14),
						rs.getString(2),
						rs.getString(3),
						new ImageIcon(path + rs.getString(7) + ext), 
						TypePlante.values()[rs.getInt(4)], 
						Ensoleillement.values()[rs.getInt(6)],
						JDBCArrayTointArray1(rs.getArray(10)),
						rs.getString(15)
						);//rs.getArray(10) JDBCArrayTointArray1
				p.setId(rs.getInt(1));
				this.planteList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertZone(Zone z, int idJardin) {
		String sql = "INSERT INTO ZONE VALUES (null,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.setInt(1, idJardin);
			stat.setArray(2,intArrayToJDBXArray(z.xpoints,z.npoints));
			stat.setArray(3, intArrayToJDBXArray(z.ypoints,z.npoints));
			stat.setInt(4, z.getEnsoleillement());
			stat.executeUpdate();
			
			// On met un id a la zone
			ResultSet r = this.statement.executeQuery("SELECT id FROM Zone");
			// On va sur le dernier indice
			while (!r.isLast())	r.next();
			z.setId(r.getInt(1));

			for (AbstractZone zone : z.getZones()) {
				if (zone instanceof Zone)
					insertZone((Zone) zone, idJardin);
				else
					insertZonePlantable((ZonePlantable) zone, z.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertZonePlantable(ZonePlantable z, int idZone) {
		String sql = "INSERT INTO ZONEPLANTABLE VALUES(null,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			if (z.getPlante() == null || z.getPlante().getId() == -1)
				stat.setNull(1,Types.NULL);
			else
				stat.setInt(1,z.getPlante().getId());
			stat.setInt(2, idZone); 
			stat.setArray(3,intArrayToJDBXArray(z.xpoints,z.npoints));
			stat.setArray(4,intArrayToJDBXArray(z.ypoints,z.npoints));
			stat.setInt(5, z.getTypeSol());
			stat.setInt(6, z.getEnsoleillement());
			stat.executeUpdate();
			
			// On met un id a la zone
			ResultSet r = this.statement.executeQuery("SELECT id FROM ZonePlantable");
			// On va sur le dernier indice
			while (!r.isLast())	r.next();
			z.setId(r.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajoute un jardin a la base de données
	 * @param j le jardin a inserer
	 */
	public void insertJardin(Jardin j) {
		String sql = "INSERT INTO JARDIN VALUES(null,?,?,?)";
		String sql2 = "SELECT id FROM jardin";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			PreparedStatement stat2 = this.connection.prepareStatement(sql2);
			stat.setString(1, j.getName());
			stat.setInt(2, j.getWidth());
			stat.setInt(3, j.getHeight());
			stat.executeUpdate();
			
			// On met un id au jardin
			ResultSet r = stat2.executeQuery();
			// on va sur le dernier indice
			while (!r.isLast())	r.next();
			j.setId(r.getInt(1));
			
			for(Zone zone : j.getZones()){
				insertZone(zone,j.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mets a jour les données d'une plante
	 * @param p la plante a mettre a jour
	 * @throws IllegalArgumentException si la mise a jour a echoué
	 */
	public void updatePlante(Plante p) throws IllegalArgumentException {
		if (p.getId() != -1) {
			String sql = "UPDATE PLANTE SET nom_Usuel =?, nom_Latin = ?, type_Plante = ?, hauteur = ?, luminosite = ?,url_Image_En_Fleur=?, couleur_en_fleur = ?, couleur_non_fleuris = ?, type_Sol = ?,	date_Plantation = ?,	date_DebutFloraison = ?, date_FinFloraison= ?, vivace = ?, description = ? WHERE id="
					+ p.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setString(1, p.getNom());
				stat.setString(2, p.getNomL());
				stat.setInt(3, p.getType());
				stat.setInt(4, p.getTailleFin());
				stat.setInt(5, p.getEnsoleillement());
				stat.setString(6, p.getNom());
				stat.setInt(7, p.getCouleur_en_fleur().getRGB());
				stat.setInt(8, p.getCouleur_non_fleuris().getRGB());
				stat.setArray(9, (intArrayToJDBXArray(p.getTypeSol(),p.getTypeSol().size())));
				stat.setDate(10, (Date) p.getDatePlantation());
				stat.setArray(11, dateArrayToJDBXArray(p.getDateFloraison(), p.getNbDate()));
				stat.setArray(12, dateArrayToJDBXArray(p.getDateFinFloraison(), p.getNbDate()));
				stat.setBoolean(13, p.isVivace());
				stat.setString(14, p.getDescription());
				stat.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Plante pas dans la base de données");
		}

	}

	public void updateZone(Zone z) throws IllegalArgumentException {
		if (z.getId() != -1) {
			String sql = "UPDATE ZONE SET id_Jardin =?, x =?, y =?, luminosite =? WHERE id = "+ z.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setInt(1, z.getId());
				stat.setArray(2, intArrayToJDBXArray(z.xpoints,z.npoints));
				stat.setArray(3, intArrayToJDBXArray(z.ypoints,z.npoints));
				stat.setInt(4, z.getEnsoleillement());
				stat.executeUpdate();
				for (AbstractZone zone : z.getZones()) {
					if (zone instanceof Zone)
						updateZone((Zone) zone);
					else
						updateZonePlantable((ZonePlantable) zone);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Zone pas dans la base de données");
		}
	}

	public void updateZonePlantable(ZonePlantable z) throws IllegalArgumentException {
		if (z.getPlante() == null || z.getId() != -1) {
			String sql = "UPDATE ZONEPLANTABLE SET id_Zone=?, x =?, y =?, type_Sol =?, luminosite =? , id_Plante =? WHERE id = "+ z.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				if (z.getPlante() == null || z.getPlante().getId() == -1)
					stat.setNull(6,Types.NULL);
				else
					stat.setInt(6,z.getPlante().getId());
				stat.setInt(1,z.getId());
				stat.setArray(2,intArrayToJDBXArray(z.xpoints,z.npoints));
				stat.setArray(3, intArrayToJDBXArray(z.ypoints,z.npoints));
				stat.setInt(4, z.getTypeSol());
				stat.setInt(5, z.getEnsoleillement());
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Zone pas dans la base de données");
		}
	}

	/**
	 * Mets a jour le jardin dans la base de données
	 * @param j le jardin a mettre a jour
	 * @throws IllegalArgumentException si la mise a jour est impossible
	 */
	public void updateJardin(Jardin j) throws IllegalArgumentException {
		if (j.getId() != -1) {
			String sql = "UPDATE JARDIN SET nom=?,width = ?,height= ? WHERE id ="+j.getId();
			try {
				PreparedStatement stat = this.connection.prepareStatement(sql);
				stat.setString(1, j.getName());
				stat.setInt(2, j.getWidth());
				stat.setInt(3, j.getHeight());
				stat.executeUpdate();
				for(Zone zone : j.getZones()){
					if (zone.getId() != -1) {
						updateZone(zone);
					} else {
						insertZone(zone,j.getId());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Jardin pas dans la base de données");
		}
	}

	/**
	 * Supprime une plante dans la base de données
	 * @param id l'identifiant de la plante a suppimer
	 */
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

	public void deleteZone(Zone z) {
		for (AbstractZone zone : z.getZones()) {
			if (zone instanceof Zone)
				deleteZone((Zone) zone);
			else
				deleteZonePlantable( (ZonePlantable) zone);
		}
		String sql = "DELETE FROM ZONE WHERE id =" + z.getId();
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteZonePlantable(ZonePlantable z) {
		String sql = "DELETE FROM ZONEPLANTABLE PLANTE WHERE id =" + z.getId();
		try {
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {e.printStackTrace();}
	}

	/**
	 * Supprime un jardin de la base de données
	 * @param id l'identifiant du jardin a supprimer
	 */
	public void deleteJardin(Jardin j) {
		String sql = "DELETE FROM JARDIN WHERE id =" + j.getId();
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
			stat.executeUpdate();
			for(Zone zone : j.getZones()){
				deleteZone(zone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Supprime un jardin de la base de données
	 * @param id l'identifiant du jardin a supprimer
	 */
	public void deleteJardin(int idJardin) {
		Jardin j = getJardin(idJardin);
		for(Zone zone : j.getZones()){
			deleteZone(zone);
		}

		String sql = "DELETE FROM JARDIN WHERE id =" + idJardin;
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
		Jardin j = null;
		try {
			ResultSet rs = this.statement.executeQuery("SELECT * FROM JARDIN WHERE id = " + id);
			rs.next();
			j = new Jardin(rs.getString(2), rs.getInt(3), rs.getInt(4));
			j.setId(id);
			for (Zone z : this.getZones(id))
				j.addZone(z);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 
	 * @param idJardin
	 * @return tableau zones
	 */
	private ArrayList<Zone> getZones(int idJardin) {
		ArrayList<Zone> zones = new ArrayList<Zone>();
		try {
			ResultSet rs = this.statement.executeQuery("SELECT * FROM ZONE WHERE id_jardin = " + idJardin);
			while (rs.next()) {
				int[] x = JDBCArrayTointArray(rs.getArray(3));
				int[] y = JDBCArrayTointArray(rs.getArray(4));
				Zone z = new Zone(x, y, rs.getInt(5));
				z.setId(rs.getInt(1));
				for (ZonePlantable zp : this.getZonePlantables(z.getId()))
					z.addZone(zp);
				zones.add(z);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return zones;
	}

	private ArrayList<ZonePlantable> getZonePlantables(int idZone) {
		ArrayList<ZonePlantable> zones = new ArrayList<ZonePlantable>();
		try {
			ResultSet rs = this.statement.executeQuery("SELECT * FROM ZONEPLANTABLE WHERE id_Zone = " + idZone);
			while (rs.next()) {
				int[] x = JDBCArrayTointArray(rs.getArray(4));
				int[] y = JDBCArrayTointArray(rs.getArray(5));
				ZonePlantable z = new ZonePlantable(x, y, rs.getInt(7), rs.getInt(6));				
				z.setId(rs.getInt(1));
				if (rs.getInt(2) != Types.NULL)
					z.setPlante(this.getPlante(rs.getInt(2)));
				zones.add(z);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return zones;
	}

	public SortedListModel getPlantes() {
		return this.planteList;
	}

	/**
	 * Utilise les methode deja d�fini plus haut pour la recherche de methode
	 * ce sera plus facile a debigger et a tester !
	 * @return les jardins complets
	 */
	public HashMap<Integer, String> getJardins() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		ResultSet rs;
		try {
			rs = this.statement.executeQuery("SELECT id, nom FROM JARDIN");
			while (rs.next()) 
				map.put(rs.getInt(1), rs.getString(2));	
		} catch (SQLException e) {e.printStackTrace();}	
		return map;
	}

	public static void main(String[] args) {
		AccesBD bd = AccesBD.getInstance();
		
		/*Date[] debut = new Date[2];
		debut[0] = new Date(datee(2010, Calendar.MAY, 12));
		debut[1] = new Date(datee(2010, Calendar.AUGUST, 19));
		
		Date[] fin = new Date[2];
		fin[0] = new Date(datee(2010, Calendar.JULY, 20));
		fin[1] = new Date(datee(2010, Calendar.SEPTEMBER, 20));
		
		int[] typeSol1 = new int[2];
		typeSol1[0] = TypeSol.ARGILEUX.getValue();
		typeSol1[1] = TypeSol.SABLEUX.getValue();
		*/
		
		ArrayList<Date> debut = new ArrayList<>();
		debut.add(new Date(datee(2010, Calendar.MAY, 12)));
		debut.add(new Date(datee(2010, Calendar.AUGUST, 19)));
		
		ArrayList<Date> fin = new ArrayList<>();
		fin.add(new Date(datee(2010, Calendar.JULY, 20)));
		fin.add(new Date(datee(2010, Calendar.SEPTEMBER, 20)));
		
		ArrayList<Integer> typeSol1 = new ArrayList<Integer>();
		typeSol1.add(TypeSol.ARGILEUX.getValue());
		typeSol1.add(TypeSol.SABLEUX.getValue());
		
		Plante p1 = new Plante(180, debut, fin, new Date(datee(2010, Calendar.JANUARY, 20)), Color.red, Color.green, true,
				"Rose des peintres", "Rosa x centifolia", new ImageIcon("res/img/popol.png"), TypePlante.BUISSON,
				Ensoleillement.MIOMBRE, typeSol1,
				"Ce rosier centfeuilles forme un buisson souple et harmonieux, portant de grosses fleurs globuleuses rose vif, au port retombant, s'épanouissant en coupes bien pleines, au parfum puissant.");
		
		/*debut[0] = new Date(datee(2010, Calendar.MARCH, 5));
		debut[1] = null;
		
		fin[0] = new Date(datee(2010, Calendar.AUGUST, 31));
		fin[1] = null;*/
		
		Plante p2 = new Plante(10, debut, fin, new Date(datee(2010, Calendar.JANUARY, 20)), Color.blue, Color.green, true,
				"Trèfle blanc", "(Trifolium repens", new ImageIcon("res/img/popol.png"), TypePlante.FLEUR,
				Ensoleillement.SOLEIL, typeSol1,"Le trèfle blanc, aussi appelé trèfle rampant, est une plante fourragère très commune dans les prairies et les jardins.");
		
		/*debut[0] = new Date(datee(2010, Calendar.JUNE, 5));
		debut[1] = null;
		
		fin[0] = new Date(datee(2010, Calendar.OCTOBER, 31));
		fin[1] = null;*/
		
		Plante p3 = new Plante(80, debut, fin, new Date(datee(2010, Calendar.JANUARY, 20)), new Color(15,15,15), Color.green, true,
						"Marguerite commune", "Leucanthemum vulgare", new ImageIcon("res/img/popol.png"), TypePlante.FLEUR,
						Ensoleillement.SOLEIL, typeSol1,"");
		bd.insertPlante(p1);
		bd.insertPlante(p2);
		bd.insertPlante(p3);
	
		
		/*
		Jardin j = new Jardin("Ma maison",100,100);
		Jardin j2 = new Jardin("null", 20, 20);
		Jardin j3 = new Jardin("blabla", 20, 20);
		Jardin j4 = new Jardin("blabla", 20, 20);
		
		Zone z = new Zone(5, 100, 100);
		//ZonePlantable z3 = new ZonePlantable(5, 100, 100, 2);
		
		//z.addZone(z3);
		j.addZone(z);
		
		bd.insertJardin(j);
		bd.insertJardin(j2);
		bd.insertJardin(j3);
		bd.insertJardin(j4);
		j4.setName("bla");
		bd.updateJardin(j4);
		
		bd.deleteJardin(j2);
		System.out.println("Jardins OK");
		
		/*Zone z = new Zone(5, 100, 100);
		Zone z2 = new Zone(5, 10, 10);
		ZonePlantable z3 = new ZonePlantable(5, 10, 10, 2);
		ZonePlantable z4 = new ZonePlantable(5, 10, 10, 3);
		ZonePlantable z5 = new ZonePlantable(5, 10, 10, 2);
		bd.insertZone(z,j.getId());
		bd.insertZone(z2,j.getId());
		bd.insertZonePlantable(z3,z.getId());
		bd.insertZonePlantable(z4,z.getId());
		bd.insertZonePlantable(z5,z.getId());
		z5.setEnsoleillement(8);
		bd.updateZonePlantable(z5);
		
		bd.deleteZone(z2);
		System.out.println("Zone OK");*/
		
		/*for(Plante p : bd.getPlantes()) {
			System.out.println(p.getId() + "," + p.getNom());
		}
		Plante p = bd.getPlante(1);
		System.out.println(p.getId() + "," + p.getNom());*/
		
		bd.close();
	}

	//-----------------------------------------------------------------------------------------
	
	public static int[] JDBCArrayTointArray(Array T) {
		ResultSet rs;
		ArrayList<Integer> x = new ArrayList<Integer>();
		try {
			rs = T.getResultSet();
			while (rs.next()) 
				x.add(rs.getInt(2));	
		} catch (SQLException e) {e.printStackTrace();}
		int[] ret = new int[x.size()];
		for (int i = 0 ; i < x.size() ; i++) {
			ret[i] = x.get(i);
		}
		return ret;
	}
	
	public static ArrayList<Integer> JDBCArrayTointArray1(Array T) {
		ResultSet rs;
		ArrayList<Integer> x = new ArrayList<Integer>();
		try {
			rs = T.getResultSet();
			while (rs.next()) 
				x.add(rs.getInt(2));	
		} catch (SQLException e) {e.printStackTrace();}
		return x;
	}

	
	
	public static JDBCArrayBasic intArrayToJDBXArray(int[] T, int size) {
		org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_INTEGER;
		Object[] o = new Object[size];
		for (int i = 0 ; i< size ; i++) 
			o[i] = new Integer(T[i]);
		return new JDBCArrayBasic(o, type);
	}
	
	public static JDBCArrayBasic intArrayToJDBXArray(ArrayList<Integer> T, int size) {
		org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_INTEGER;
		Object[] o = new Object[size];
		for (int i = 0 ; i< size ; i++) 
			o[i] = new Integer(T.get(i));
		return new JDBCArrayBasic(o, type);
	}
	
	
	@SuppressWarnings("deprecation")
	public static JDBCArrayBasic dateArrayToJDBXArray(Date[] d, int size){
		org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_DATE;
		Object[] o = new Object[size];
		for (int i = 0 ; i< size ; i++){
			o[i] = new Date(datee(d[i].getYear(), d[i].getMonth(), d[i].getDay()));
		}
		return new JDBCArrayBasic(o, type);
	}
	
	public static Date[] JDBCArrayTodateArray(Array T) {
		ResultSet rs;
		ArrayList<Date> x = new ArrayList<Date>();
		try {
			rs = T.getResultSet();
			while (rs.next()) 
				x.add((Date)rs.getDate(2));	
		} catch (SQLException e) {e.printStackTrace();}
		Date[] ret = new Date[x.size()];
		for (int i = 0 ; i < x.size() ; i++) {
			ret[i] = x.get(i);
		}
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	public static JDBCArrayBasic dateArrayToJDBXArray(ArrayList<Date> d, int size){
		org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_DATE;
		Object[] o = new Object[size];
		for (int i = 0 ; i< size ; i++){
			o[i] = new Date(datee(d.get(i).getYear(), d.get(i).getMonth(), d.get(i).getDay()));
		}
		return new JDBCArrayBasic(o, type);
	}
	
	public static ArrayList<Date> JDBCArrayTodateArray1(Array T) {
		ResultSet rs;
		ArrayList<Date> x = new ArrayList<Date>();
		try {
			rs = T.getResultSet();
			while (rs.next()) 
				x.add((Date)rs.getDate(2));	
		} catch (SQLException e) {e.printStackTrace();}
		return x;
	}
	
}
