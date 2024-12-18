package act2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaPlayer {

	/**
	 * * Crea la tabla en la BD
	 */
	public static void creacionTablaPlayer() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		String sql;

		try {
			conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
			stmt = conn.createStatement();

			System.out.println("Nos hemos conectado a la BBDD");
			sql = "CREATE TABLE IF NOT EXISTS Player (" + "idPlayer INT AUTO_INCREMENT PRIMARY KEY, "
					+ "Nick VARCHAR(45) NOT NULL, " + "password VARCHAR(128) NOT NULL, " + "email VARCHAR(100) NOT NULL"
					+ ")";

			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			System.err.println("SQL Error al crear la tabla Player:");
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.err.println("SQL Error al crear la tabla Player:");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.err.println("SQL Error al crear la tabla Player:");
			}
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
			creacionTablaPlayer();
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Player (Nick, password, email) VALUES "
					+ "('GamerX', 'securePass123', 'gamerx@example.com'), "
					+ "('ProPlayer77', 'propass77', 'proplayer77@example.com'), "
					+ "('NoobMaster', 'noob12345', 'noobmaster@example.com'), "
					+ "('EliteGamer', 'eliteG@me!', 'elitegamer@example.com'), "
					+ "('FastClicker', 'clicker9876', 'fastclicker@example.com'), "
					+ "('SharpShooter', 'sh@rpShooter', 'sharpshooter@example.com'), "
					+ "('StealthKing', 'ste@lthRocks', 'stealthking@example.com'), "
					+ "('BattleQueen', 'queenBattle9', 'battlequeen@example.com'), "
					+ "('Strategist99', 'planAhead99', 'strategist99@example.com'), "
					+ "('SpeedyGonzalez', 'speedPass!23', 'speedygonzalez@example.com');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			System.err.println("SQL Error al inicializar la tabla Player:");
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
	 * Realiza una consulta de todos los datos en la tabla Player con la opción de
	 * filtrar por Nick.
	 * 
	 * @param filtro
	 * @throws Exception
	 */
	public static void consultaTablaPlayer(String filtro) throws Exception {
		String sql = "SELECT idPlayer, Nick, password, email FROM Player";

		if (filtro != null && !filtro.trim().isEmpty()) {
			sql += " WHERE Nick LIKE ?";
		}

		creacionTablaPlayer();

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			if (filtro != null && !filtro.trim().isEmpty()) {
				pstmt.setString(1, "%" + filtro + "%");
			}

			System.out.println("Consultando la tabla Player...");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int idPlayer = rs.getInt("idPlayer");
					String nick = rs.getString("Nick");
					String password = rs.getString("password");
					String email = rs.getString("email");

					System.out.println("ID Player: " + idPlayer + ", Nick: " + nick + ", Password: " + password
							+ ", Email: " + email);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al consultar la tabla Player.");
		}
	}

	/**
	 * Inserta una nueva fila en la tabla compras los valores pasados po parametros
	 * son los valores delos campos de la tabla
	 * 
	 * @param nick
	 * @param password
	 * @param email
	 * @throws Exception
	 */
	public static void insertarTabla(String nick, String password, String email) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		creacionTablaPlayer();
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO Player (Nick, password, email) VALUES ( '" + nick + "', '" + password + "', '"
					+ email + "');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			System.err.println("Error al insertar la tabla Player.");
		}
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

	/**
	 * Actualiza la tabla compras recibe por parametro el campo ,un nuevo valor y el
	 * id de la fila a actualizaar
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
			creacionTablaPlayer();
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "UPDATE Player SET " + campo + " = " + nuevoValor + " WHERE " + filtro;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			System.out.println("Registro actualizado correctamente.");
		} catch (SQLException se) {
			System.err.println("Error al actualizar la tabla Player.");
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
	 * 
	 * @throws Exception
	 */
	public static void eliminarRegsitro(String id) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "delete from Player where idPlayer= " + id + " ;";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			System.err.println("Error al eliminar de  la tabla Player.");
		} finally {
			stmt.close();
			conn.close();
			System.out.println("Eliminacion correcta");
		}
	}

	/**
	 * Elimina la tabla
	 * 
	 * @throws Exception
	 */
	public static void eliminarTablaPlayer() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS Player ;";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
			System.out.println("Eliminacion correcta");
		}
	}
}
