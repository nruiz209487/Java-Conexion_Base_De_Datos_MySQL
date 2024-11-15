/**
 * 
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

		insertarTabla();

	}

	/**
	 * 
	 */
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
		}
	}

	public static void insertarTabla() throws Exception {
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "INSERT INTO personas (id, nombre, apellido, edad) VALUES (22,'Zara', 'Ali', 18)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

}