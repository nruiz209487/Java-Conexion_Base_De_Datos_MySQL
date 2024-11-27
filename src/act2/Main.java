/**
 * 
 */
package act2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public class Main {

	public static final String URL = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_nruiz";
	public static final String USUARIO = "nruiz";
	public static final String CONTRASENYA = "12345";

	public static final void main(String[] args) throws Exception {

		String str = "2024-11-27";
		Date date = Date.valueOf(str);

		probarConexion();

		TablaGame.creacionTablaGames();
		TablaPlayer.creacionTablaPlayer();
		TablaCompras.creacionTablaCompras();

		TablaGame.inicializarTabla();
		TablaPlayer.inicializarTabla();
		TablaCompras.inicializarTabla();

		TablaGame.insertarTabla(11, "S.T.A.L.K.E.R 2", "40");
		TablaPlayer.insertarTabla(22, "xxsniper", "CONTRASENYA123", "xxsniper@gmail.com");
		TablaCompras.insertarTabla(12, 22, 11, "Juego fisisico", 22.2, date);

		TablaGame.updateTabla("Nombre", "'GTA VI'", "idGames=8");
		TablaPlayer.updateTabla("Nick", "'xxsniper2'", "idPlayer=1");
		TablaCompras.updateTabla("cosa", "'Edicion Especial'", "idCompra=1");

		TablaGame.consultaTablaGames();
		TablaPlayer.consultaTablaPlayer();
		TablaCompras.consultaTablaCompras();

		TablaCompras.eliminarTablaCompras();
		TablaGame.eliminarTablaGames();
		TablaPlayer.eliminarTablaPlayer();
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

}
