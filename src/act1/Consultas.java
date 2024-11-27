package act1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
	public static void consultaTablaAct1() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "SELECT id, nombre, apellido, edad FROM personas order by edad asc;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct2() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "SELECT id, nombre, apellido, edad FROM personas order by Nombre,Apellido asc;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct3() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "SELECT id, nombre, apellido, edad FROM personas WHERE EDAD>30;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct4() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "SELECT id, nombre, apellido, edad FROM personas WHERE Nombre LIKE  'J%' order by apellido asc";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct5() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = " SELECT id, nombre, apellido, edad FROM personas WHERE Nombre LIKE  'J%' AND apellido LIKE  'E%'   order by edad asc";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct6() throws SQLException {

		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "  SELECT AVG(edad) AS edadMedia FROM personas ";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String edadMedia = rs.getString("edadMedia");
				System.out.println("EdadMedia: " + edadMedia);
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

	public static void consultaTablaAct7() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "  SELECT apellido FROM personas WHERE apellido LIKE  '%pa%' or  apellido LIKE  '%ma%'  order by apellido asc";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String apellido = rs.getString("apellido");

				System.out.println("Apellido: " + apellido);
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

	public static void consultaTablaAct8() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = " SELECT id, nombre, apellido, edad FROM personas WHERE edad>=24 and edad<=32  order by edad asc;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct9() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = " SELECT id, nombre, apellido, edad FROM personas WHERE edad>44 order by edad asc;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

	public static void consultaTablaAct10() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			stmt = conn.createStatement();
			String sql = "ALTER TABLE personas \r\n"
					+ "ADD laboral VARCHAR(20), \r\n"
					+ "ADD CONSTRAINT chk_valores_validos CHECK (laboral IN ('estudiantes', 'jubilados', 'parados', 'ocupados'));";
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

	public static void consultaTablaAct11() throws Exception {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		PreparedStatement stmt = null;
		try {
			System.out.println("Nos hemos conectado a la BBDD");
			String sql = "UPDATE personas SET laboral = 'estudiantes' WHERE edad < 18;";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			sql = "UPDATE personas SET laboral = 'jubilados' WHERE edad > 65 ;";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			sql = "UPDATE personas SET laboral = 'parados' WHERE edad < 65 and edad > 18 and edad%2=0;";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			sql = "UPDATE personas SET laboral = 'ocupados' WHERE edad < 65 and edad > 18 and edad%2!=0";
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
			Consultas.consultaTabla();
		}
	}

	public static void consultaTabla() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = "SELECT id, nombre, apellido, edad, laboral FROM personas order by edad ;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");
				String laboral = rs.getString("laboral");
				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad+ ", Laboral: " + laboral);
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
	
	public static void consultaTablaAct12() throws SQLException {
		Connection conn = DriverManager.getConnection(Main.URL, Main.USUARIO, Main.CONTRASENYA);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Consultando la tabla personas...");
			String sql = " SELECT id, nombre, apellido, edad FROM personas WHERE edad>44 AND apellido LIKE  '%pa%' or  apellido LIKE  '%ma%' order by edad asc;";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad);
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

}
