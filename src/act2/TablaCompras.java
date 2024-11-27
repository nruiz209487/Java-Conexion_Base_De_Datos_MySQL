package act2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaCompras {

	// Método para crear la tabla Compras
	public static void creacionTablaCompras() {
		String sql = "CREATE TABLE IF NOT EXISTS Compras (" + "idCompra INT PRIMARY KEY, " + // Sin AUTO_INCREMENT
				"idPlayer INT NOT NULL, " + "idGames INT NOT NULL, " + "Cosa VARCHAR(25), " + "Precio DECIMAL(6,2), "
				+ "FechaCompra DATE, " + "FOREIGN KEY (idPlayer) REFERENCES Player(idPlayer), "
				+ "FOREIGN KEY (idGames) REFERENCES Games(idGames)" + ");";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			System.out.println("Nos hemos conectado a la BBDD");
			stmt.executeUpdate(sql);
			System.out.println("Creación de la tabla Compras completada correctamente.");

		} catch (SQLException e) {
			System.err.println("Error al crear la tabla Compras.");
			e.printStackTrace();
		}
	}

	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		String sql = "";
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			sql = "INSERT INTO Compras (idCompra, idPlayer, idGames, Cosa, Precio, FechaCompra) VALUES "
					+ "(1, 1, 2, 'Skin', 9.99, '2024-11-01'), "
					+ "(2, 2, 3, 'Pase de Temporada', 19.99, '2024-11-05'), "
					+ "(3, 3, 1, 'DLC', 14.99, '2024-11-07'), " + "(4, 4, 5, 'Arma', 4.99, '2024-11-10'), "
					+ "(5, 5, 4, 'Mapa', 7.49, '2024-11-15'), " + "(6, 6, 8, 'Vehículo', 12.99, '2024-11-20'), "
					+ "(7, 7, 7, 'Personaje', 11.49, '2024-11-22'), " + "(8, 8, 10, 'Accesorio', 5.99, '2024-11-25'), "
					+ "(9, 9, 9, 'Boost', 3.99, '2024-11-26'), " + "(10, 10, 6, 'Expansion', 24.99, '2024-11-27');";

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

	// Método para consultar la tabla Compras
	public static void consultaTablaCompras() {
		String sql = "SELECT idCompra, idPlayer, idGames, Cosa, Precio, FechaCompra FROM Compras;";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Consultando la tabla Compras...");
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

		} catch (SQLException e) {
			System.err.println("Error al consultar la tabla Compras.");
			e.printStackTrace();
		}
	}

	public static void insertarTabla(int idCompra, int idPlayer, int idGames, String cosa, double precio,
			Date fechaCompra) throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO Compras (idCompra, idPlayer, idGames, cosa, precio) VALUES (" + idCompra + ", "
					+ idPlayer + ", " + idGames + ", '" + cosa + "', " + precio + ");";

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
			String sql = "UPDATE Compras SET " + campo + " = " + nuevoValor + " WHERE " + filtro;
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

	// Método para eliminar la tabla Compras
	public static void eliminarTablaCompras() {
		String sql = "DROP TABLE IF EXISTS Compras;";

		try (Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
				Statement stmt = conn.createStatement()) {

			System.out.println("Nos hemos conectado a la BBDD");
			stmt.executeUpdate(sql);
			System.out.println("Eliminación de la tabla Compras completada correctamente.");

		} catch (SQLException e) {
			System.err.println("Error al eliminar la tabla Compras.");
			e.printStackTrace();
		}
	}
}
