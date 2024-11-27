package act2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaGame {

	// Método para crear la tabla Games
	public static void creacionTablaGames() {
		String sql = "CREATE TABLE IF NOT EXISTS Games (" + "idGames INT PRIMARY KEY, " + // Sin AUTO_INCREMENT
				"Nombre VARCHAR(45) NOT NULL, " + "tiempoJugado TIME" + ");";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			System.out.println("Nos hemos conectado a la BBDD");
			stmt.executeUpdate(sql);
			System.out.println("Creación de tabla Games completada correctamente.");

		} catch (SQLException e) {
			System.err.println("Error al crear la tabla Games.");
			e.printStackTrace();
		}
	}

	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		String sql = "";
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Games (idGames, Nombre, tiempoJugado) VALUES " + "(1, 'Call of Duty', '01:30:00'), "
					+ "(2, 'Minecraft', '05:20:00'), " + "(3, 'FIFA 23', '02:15:00'), "
					+ "(4, 'Among Us', '00:45:00'), " + "(5, 'Fortnite', '03:10:00'), "
					+ "(6, 'The Sims 4', '04:05:00'), " + "(7, 'Cyberpunk 2077', '06:00:00'), "
					+ "(8, 'GTA V', '08:45:00'), " + "(9, 'League of Legends', '10:00:00'), "
					+ "(10, 'Valorant', '07:30:00');";
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

	// Método para consultar los datos de la tabla Games
	public static void consultaTablaGames() {
		String sql = "SELECT idGames, Nombre, tiempoJugado FROM Games;";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Consultando la tabla Games...");
			while (rs.next()) {
				int idGames = rs.getInt("idGames");
				String nombre = rs.getString("Nombre");
				String tiempoJugado = rs.getString("tiempoJugado");

				System.out.println("ID Game: " + idGames + ", Nombre: " + nombre + ", Tiempo Jugado: " + tiempoJugado);
			}

		} catch (SQLException e) {
			System.err.println("Error al consultar la tabla Games.");
			e.printStackTrace();
		}
	}

	public static void insertarTabla(int idGames, String nombre, String tiempoJugado) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO Games (idGames, Nombre, tiempoJugado) VALUES (" 
			           + idGames + ", '" + nombre + "', '" + tiempoJugado + "');";

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
			String sql = "UPDATE Games SET " + campo + " = " + nuevoValor + " WHERE " + filtro; 
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

	// Método para eliminar la tabla Games
	public static void eliminarTablaGames() {
		String sql = "DROP TABLE IF EXISTS Games;";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			System.out.println("Nos hemos conectado a la BBDD");
			stmt.executeUpdate(sql);
			System.out.println("Eliminación de la tabla Games completada correctamente.");

		} catch (SQLException e) {
			System.err.println("Error al eliminar la tabla Games.");
			e.printStackTrace();
		}
	}
}
