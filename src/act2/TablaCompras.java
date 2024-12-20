package act2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaCompras {

	/**
	 * Crea la tabla en la BD
	 */
	public static void creacionTablaCompras() {
		String sql = "CREATE TABLE IF NOT EXISTS Compras (" + "idCompra INT AUTO_INCREMENT  PRIMARY KEY, "
				+ "idPlayer INT NOT NULL, " + "idGames INT NOT NULL, " + "Cosa VARCHAR(25), " + "Precio DECIMAL(6,2), "
				+ "FechaCompra DATE, " + "FOREIGN KEY (idPlayer) REFERENCES Player(idPlayer), "
				+ "FOREIGN KEY (idGames) REFERENCES Games(idGames)  " + ");";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println("Error al crear la tabla Compras.");
		}
	}

	/**
	 * iiNSERTA UNA SERIE DE VALORES EN LA TABLA compras (SIMPLEMENTE PARA TENER
	 * ALGO CON LO QUE TRABAJAR )
	 * 
	 * @throws Exception
	 */
	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		String sql = "";
		creacionTablaCompras();
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Compras (idPlayer, idGames, Cosa, Precio, FechaCompra) VALUES "
					+ "( 1, 2, 'Skin', 9.99, '2024-11-01'), " + "( 2, 3, 'Pase de Temporada', 19.99, '2024-11-05'), "
					+ "( 3, 1, 'DLC', 14.99, '2024-11-07'), " + "( 4, 5, 'Arma', 4.99, '2024-11-10'), "
					+ "( 5, 4, 'Mapa', 7.49, '2024-11-15'), " + "( 6, 8, 'Vehículo', 12.99, '2024-11-20'), "
					+ "( 7, 7, 'Personaje', 11.49, '2024-11-22'), " + "( 8, 10, 'Accesorio', 5.99, '2024-11-25'), "
					+ "( 9, 9, 'Boost', 3.99, '2024-11-26'), " + "( 10, 6, 'Expansion', 24.99, '2024-11-27');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			System.err.println("Error al inicializar tabla.");
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
	 * Realiza una consulta de todos los datos en la tabla compras
	 */
	public static void consultaTablaCompras(String filtro) {
		String sql = "SELECT idCompra, idPlayer, idGames, Cosa, Precio, FechaCompra FROM Compras";

		if (filtro != null && !filtro.trim().isEmpty()) {
			sql += " WHERE idCompra = ?";
		}

		creacionTablaCompras();

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			if (filtro != null && !filtro.trim().isEmpty()) {
				try {
					pstmt.setInt(1, Integer.parseInt(filtro));
				} catch (NumberFormatException e) {
					System.err.println(
							"El filtro proporcionado no es un número válido. Por favor, ingrese un ID numérico.");
					return;
				}
			}

			System.out.println("Consultando la tabla Compras...");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int idCompra = rs.getInt("idCompra");
					int idPlayer = rs.getInt("idPlayer");
					int idGames = rs.getInt("idGames");
					String cosa = rs.getString("Cosa");
					double precio = rs.getDouble("Precio");
					Date fechaCompra = rs.getDate("FechaCompra");

					System.out.println("ID Compra: " + idCompra + ", ID Player: " + idPlayer + ", ID Games: " + idGames
							+ ", Cosa: " + cosa + ", Precio: " + precio + ", Fecha de Compra: " + fechaCompra);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al consultar la tabla Compras.");

		}
	}

	/**
	 * Inserta una nueva fila en la tabla compras los valores pasados po parametros
	 * son los valores delos campos de la tabla
	 * 
	 * @param idPlayer
	 * @param idGames
	 * @param cosa
	 * @param precio
	 * @param fechaCompra
	 * @throws Exception
	 */
	public static void insertarTabla(int idPlayer, int idGames, String cosa, double precio, Date fechaCompra)
			throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			creacionTablaCompras();
			String sql = "INSERT INTO Compras (idPlayer, idGames, cosa, precio, FechaCompra) VALUES (" + idPlayer + ", "
					+ idGames + ", '" + cosa + "', " + precio + ", '" + fechaCompra + "');";

			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			System.err.println("Error al insertar la tabla.");
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
	 * Actualiza la tabla compras recibe por parametro el campo,un nuevo valor y el
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
			creacionTablaCompras();
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "UPDATE Compras SET " + campo + " = " + nuevoValor + " WHERE " + filtro;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			System.out.println("Registro actualizado correctamente.");
		} catch (SQLException se) {
			System.err.println("Error al actuaizar la tabla.");
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
	 * elimna un registro
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void eliminarRegsitro(String id) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "delete from Compras where idPlayer= " + id + " ;";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			System.err.println("Error al eliminar en tabla Compras.");
		} finally {
			stmt.close();
			conn.close();
			System.out.println("Eliminacion correcta");
		}
	}

	/**
	 * Elimina la tabla
	 */
	public static void eliminarTablaCompras() {
		String sql = "DROP TABLE IF EXISTS Compras;";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			System.out.println("Nos hemos conectado a la BBDD");
			stmt.executeUpdate(sql);
			System.out.println("Eliminación de la tabla Compras completada correctamente.");

		} catch (SQLException e) {
			System.err.println("Error al eliminar la tabla Compras.");
		}
	}
}
