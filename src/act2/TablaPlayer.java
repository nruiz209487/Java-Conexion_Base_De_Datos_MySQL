package act2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaPlayer {
	public static void creacionTablaPlayer() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		String sql;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			sql = "CREATE TABLE Player (" + "idPlayer INT PRIMARY KEY, " + 
					"Nick VARCHAR(45) NOT NULL, " + "password VARCHAR(128) NOT NULL, " + "email VARCHAR(100) NOT NULL"
					+ ");";
			stmt.executeUpdate(sql);

			System.out.println("Creación de tablas completada correctamente.");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		String sql = "";
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Player (idPlayer ,Nick, password, email) VALUES "
					+ "(1,'GamerX', 'securePass123', 'gamerx@example.com'), "
					+ "(2,'ProPlayer77', 'propass77', 'proplayer77@example.com'), "
					+ "(3,'NoobMaster', 'noob12345', 'noobmaster@example.com'), "
					+ "(4,'EliteGamer', 'eliteG@me!', 'elitegamer@example.com'), "
					+ "(5,'FastClicker', 'clicker9876', 'fastclicker@example.com'), "
					+ "(6,'SharpShooter', 'sh@rpShooter', 'sharpshooter@example.com'), "
					+ "(7,'StealthKing', 'ste@lthRocks', 'stealthking@example.com'), "
					+ "(8,'BattleQueen', 'queenBattle9', 'battlequeen@example.com'), "
					+ "(9,'Strategist99', 'planAhead99', 'strategist99@example.com'), "
					+ "(10,'SpeedyGonzalez', 'speedPass!23', 'speedygonzalez@example.com');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					conn.close();
					System.out.println("se ha  insertado correctamente");
				}

			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	public static void consultaTablaPlayer() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		try {
			System.out.println("Consultando la tabla Player...");
			sql = "SELECT idPlayer, Nick, password, email FROM Player;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idPlayer = rs.getInt("idPlayer");
				String nick = rs.getString("Nick");
				String password = rs.getString("password");
				String email = rs.getString("email");

				System.out.println(
						"ID Player: " + idPlayer + ", Nick: " + nick + ", Password: " + password + ", Email: " + email);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión o el ResultSet.");
				se.printStackTrace();
			}
		}
	}

	public static void insertarTabla(int idPlayer, String nick, String password, String email) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO Player (idPlayer, Nick, password, email) VALUES (" + idPlayer + ", '" + nick
					+ "', '" + password + "', '" + email + "');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					conn.close();
					System.out.println("se ha  insertado correctamente");
				}

			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	public static void updateTabla(String campo, String nuevoValor, String filtro) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;

		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "UPDATE Player SET " + campo + " = " + nuevoValor + " WHERE " + filtro; 
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			System.out.println("Registro actualizado correctamente.");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	public static void eliminarTablaPlayer() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "DROP TABLE  Player ;";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
			System.out.println("Eliminacion correcta");
		}
	}
}
