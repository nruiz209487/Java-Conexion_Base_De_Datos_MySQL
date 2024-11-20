/**
 * 
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

/**
 * 
 */
public class Main {

	public static final String URL = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_nruiz";
	public static final String USUARIO = "nruiz";
	public static final String CONTRASENYA = "12345";

	public static final void main(String[] args) throws Exception {
		updateTabla();
		// crecionTabla(); inicializarTabla() ;
		consultaTabla();
	}

	public static void consultaTabla() throws SQLException {
		// Probar conexión y obtener conexión a la base de datos
		probarConexion();
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			// Consulta SQL para seleccionar todos los registros
			String sql = "SELECT id, nombre, apellido, edad FROM personas;";

			// Crear Statement y ejecutar la consulta
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// Procesar los resultados
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				// Imprimir los datos de cada registro
				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// Cerrar recursos
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

	public static void probarConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
			System.out.println("¡Conexión exitosa!");

			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No se encontró el driver JDBC.");
			e.printStackTrace();
		}
	}

	public static void crecionTabla() throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "create table  personas(\r\n" + "id int  primary key,\r\n" + "nombre  varchar(255),\r\n"
					+ "apellido varchar(255),\r\n" + " edad int\r\n" + ")\r\n" + ";";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
			System.out.println("Creacion correcta");
		}
	}

	public static void eliminarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "DROP TABLE personas;";
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

	public static void inicializarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO personas (id, nombre, apellido, edad) VALUES \r\n"
					+ "(1, 'Juan', 'Pérez', 34),\r\n" + "(2, 'María', 'López', 28),\r\n"
					+ "(3, 'Carlos', 'Martínez', 42),\r\n" + "(4, 'Ana', 'Gómez', 25),\r\n"
					+ "(5, 'Luis', 'Hernández', 37),\r\n" + "(6, 'Sofía', 'Ramírez', 31),\r\n"
					+ "(7, 'José', 'Torres', 45),\r\n" + "(8, 'Laura', 'Castro', 29),\r\n"
					+ "(9, 'Miguel', 'Domínguez', 33),\r\n" + "(10, 'Lucía', 'Morales', 26),\r\n"
					+ "(11, 'Fernando', 'Núñez', 40),\r\n" + "(12, 'Paula', 'Vargas', 22),\r\n"
					+ "(13, 'Andrés', 'Rojas', 36),\r\n" + "(14, 'Carmen', 'Ortega', 50),\r\n"
					+ "(15, 'Javier', 'Santos', 41),\r\n" + "(16, 'Patricia', 'Mendoza', 27),\r\n"
					+ "(17, 'Ricardo', 'Vega', 38),\r\n" + "(18, 'Gabriela', 'Cruz', 32),\r\n"
					+ "(19, 'Héctor', 'Suárez', 47),\r\n" + "(20, 'Daniela', 'Pacheco', 24),\r\n"
					+ "(21, 'Alberto', 'Aguilar', 39),\r\n" + "(22, 'Rosa', 'Delgado', 35),\r\n"
					+ "(23, 'Francisco', 'Montes', 44),\r\n" + "(24, 'Mónica', 'Reyes', 30),\r\n"
					+ "(25, 'Diego', 'Campos', 33),\r\n" + "(26, 'Elena', 'Carrillo', 29),\r\n"
					+ "(27, 'Álvaro', 'Flores', 41),\r\n" + "(28, 'Verónica', 'Peña', 26),\r\n"
					+ "(29, 'Sebastián', 'Ortiz', 40),\r\n" + "(30, 'Victoria', 'Cárdenas', 23),\r\n"
					+ "(31, 'Raúl', 'Jiménez', 50),\r\n" + "(32, 'Adriana', 'Figueroa', 28),\r\n"
					+ "(33, 'Santiago', 'Mora', 34),\r\n" + "(34, 'Carolina', 'Navarro', 31),\r\n"
					+ "(35, 'Jorge', 'Escobar', 48),\r\n" + "(36, 'Isabel', 'Serrano', 37),\r\n"
					+ "(37, 'Roberto', 'Valencia', 46),\r\n" + "(38, 'Natalia', 'Araya', 27),\r\n"
					+ "(39, 'Camila', 'Guzmán', 25),\r\n" + "(40, 'Enrique', 'Bermúdez', 43),\r\n"
					+ "(41, 'Cecilia', 'Soto', 36),\r\n" + "(42, 'Guillermo', 'Parra', 39),\r\n"
					+ "(43, 'Alejandra', 'Vargas', 33),\r\n" + "(44, 'Marcos', 'Mejía', 31),\r\n"
					+ "(45, 'Beatriz', 'Chacón', 29),\r\n" + "(46, 'Cristian', 'Ríos', 45),\r\n"
					+ "(47, 'Julia', 'Velázquez', 42),\r\n" + "(48, 'Esteban', 'Arrieta', 40),\r\n"
					+ "(49, 'Claudia', 'Lara', 28),\r\n" + "(50, 'Manuel', 'Zamora', 38);\r\n";
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

	public static void insertarTabla(int id, String nombre, String apellido, int edad) throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO personas (id, nombre, apellido, edad) VALUES (" + id + ", '" + nombre + "', '"
					+ apellido + "', " + edad + ");";

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

	public static void updateTabla() throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "UPDATE personas SET nombre = 'adfhdfbu', apellido = 'asfdufhau' WHERE id = 1;";
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

}
