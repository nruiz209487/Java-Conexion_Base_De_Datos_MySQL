package act2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaGame {
	/**
	 * Crea la tabla en la BD compras
	 */
	public static void creacionTablaGames() {
		String sql = "CREATE TABLE IF NOT EXISTS Games (" + "idGames INT AUTO_INCREMENT  PRIMARY KEY, " + // Sin
																											// AUTO_INCREMENT
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

	/**
	 * INSERTA UNA SERIE DE VALORES EN LA TABLA (SIMPLEMENTE PARA TENER ALGO CON LO
	 * QUE TRABAJAR )
	 * 
	 * @throws Exception
	 */
	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		String sql = "";
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Games (Nombre, tiempoJugado) VALUES " + "( 'Call of Duty', '01:30:00'), "
					+ "( 'Minecraft', '05:20:00'), " + "('FIFA 23', '02:15:00'), " + "( 'Among Us', '00:45:00'), "
					+ "('Fortnite', '03:10:00'), " + "( 'The Sims 4', '04:05:00'), "
					+ "('Cyberpunk 2077', '06:00:00'), " + "('GTA V', '08:45:00'), "
					+ "('League of Legends', '10:00:00'), " + "( 'Valorant', '07:30:00');";
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

	/**
	 * Realiza una consulta de todos los datos en la tabla
	 */
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

	/**
	 * Inserta una nueva fila en la tabla compras los valores pasados po parametros
	 * son los valores delos campos de la tabla
	 * 
	 * @param nombre
	 * @param tiempoJugado
	 * @throws Exception
	 */
	public static void insertarTabla(String nombre, String tiempoJugado) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO Games ( Nombre, tiempoJugado) VALUES ( '" + nombre + "', ' " + tiempoJugado
					+ "');";

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

	/**
	 * * Actualiza la tabla compras recibe por parametro el campo ,un nuevo valor y
	 * el id de la fila a actualizaar
	 * 
	 * @param campo
	 * @param nuevoValor
	 * @param filtro
	 * @throws Exception
	 */
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

	/**
	 * Elimina la tabla
	 */
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
