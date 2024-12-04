/**
 * 
 */
package act2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 */
public class Main {
	private static Scanner scanner;
	public static final String URL = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_nruiz";
	public static final String USUARIO = "nruiz";
	public static final String CONTRASENYA = "12345";

	public static final void main(String[] args) {
		scanner = new Scanner(System.in);

		boolean salida = false;
		int opcion = 0;
		probarConexion(); // Verificamos la conexión a la base de datos antes de empezar el menú

		while (!salida) {
			menu(); // Mostrar el menú de opciones
			System.out.println("Escoja una opción:" + "\n");
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 0:
				salida = true;
				break;
			case 1:
				try {
					TablaGame.creacionTablaGames();
					TablaPlayer.creacionTablaPlayer();
					TablaCompras.creacionTablaCompras();
				} catch (Exception e) {
					System.err.println("Error al crear las tablas puebe a eliminar todas las tablas");
				}
				break;
			case 2:
				try {
					TablaGame.creacionTablaGames();
				} catch (Exception e) {
					System.err.println("Error al crear la TablaGame: La tabla ya esta creada  ");
				}
				break;
			case 3:
				try {
					TablaPlayer.creacionTablaPlayer();
				} catch (Exception e) {
					System.err.println("Error al crear la TablaPlayer:  La tabla ya esta creada" + e.getMessage());
				}
				break;
			case 4:
				try {
					TablaCompras.creacionTablaCompras();
				} catch (Exception e) {
					System.err.println(
							"Error al crear la TablaCompras: No puede ser creada sin las tablas  Payer y Game o  La tabla ya esta creada"
									+ e.getMessage());
				}
				break;
			case 5:
				try {
					TablaGame.creacionTablaGames();
					TablaGame.inicializarTabla();
					TablaPlayer.inicializarTabla();
					TablaCompras.inicializarTabla();
				} catch (Exception e) {
					System.err.println("Error al inicializar las tablas: Pruebe a voler a crearlas ");
				}
				break;
			case 6:
				try {
					TablaGame.inicializarTabla();
				} catch (Exception e) {
					System.err.println("Error al inicializar la TablaGame: tabla no creada ");
				}
				break;
			case 7:
				try {
					TablaPlayer.inicializarTabla();
				} catch (Exception e) {
					System.err.println("Error al inicializar la TablaPlayer: tabla no creada ");
				}
				break;
			case 8:
				try {
					TablaCompras.inicializarTabla();
				} catch (Exception e) {
					System.err.println("Error al inicializar la TablaCompras: tabla no creada ");
				}
				break;
			case 9:
				try {
					insertarTablaGame();
				} catch (Exception e) {
					System.err.println("Error al insertar en la TablaGame:  tabla no creada");
				}
				break;
			case 10:
				try {
					insertarTablaPlayer();
				} catch (Exception e) {
					System.err.println("Error al insertar en la TablaPlayer: tabla no creada ");
				}
				break;
			case 11:
				try {
					insertarTablaCompra();
				} catch (Exception e) {
					System.err.println("Error al insertar en la TablaCompras: tabla no creada ");
				}
				break;
			case 12:
				try {
					updateTablaGame();
				} catch (Exception e) {
					System.err.println("Error al actualizar la TablaGame: El id no existe  ");
				}
				break;
			case 13:
				try {
					updateTablaPlayer();
				} catch (Exception e) {
					System.err.println("Error al actualizar la TablaPlayer:  El id no existe");
				}
				break;
			case 14:
				try {
					updateTablaCompras();
				} catch (Exception e) {
					System.err.println("Error al actualizar la TablaCompras: El id no existe ");
				}
				break;
			case 15:
				try {
					TablaGame.consultaTablaGames();
				} catch (Exception e) {
					System.err.println("Error al consultar la TablaGame: La tabla no existe ");
				}
				break;
			case 16:
				try {
					TablaPlayer.consultaTablaPlayer();
				} catch (Exception e) {
					System.err.println("Error al consultar la TablaPlayer: La tabla no existe");
				}
				break;
			case 17:
				try {
					TablaCompras.consultaTablaCompras();
				} catch (Exception e) {
					System.err.println("Error al consultar la TablaCompras: La tabla no existe");
				}
				break;
			case 18:
				try {
					TablaGame.consultaTablaGames();
					TablaPlayer.consultaTablaPlayer();
					TablaCompras.consultaTablaCompras();
				} catch (Exception e) {
					System.err.println("Error al consultar todas las tablas: ");
				}
				break;
			case 19:
				try {
					TablaCompras.eliminarTablaCompras();
					TablaGame.eliminarTablaGames();
					TablaPlayer.eliminarTablaPlayer();
				} catch (Exception e) {
					System.err.println("Error al eliminar las tablas:Las tabls no existen ");
				}
				break;
			default:
				System.err.println("Opción no válida.");
				break;
			}
		}
		scanner.close();
	}

	/**
	 * Compruba que la conexion con la DB este correcta
	 */
	private static void probarConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENYA);
			System.out.println("¡Conexión exitosa!");

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("No se encontró el driver JDBC.");
			e.printStackTrace();
		}
	}

	/**
	 * Imptime el menu
	 */
	private static void menu() {
		System.out.println("\n" + "1-Crear todas las tablas");
		System.out.println("2-Crear TablaGame");
		System.out.println("3-Crear TablaPlayer");
		System.out.println("4-Crear TablaCompras");
		System.out.println("5-Inicializar todas las tablas");
		System.out.println("6-Inicializar TablaGame");
		System.out.println("7-Inicializar TablaPlayer");
		System.out.println("8-Inicializar TablaCompras");
		System.out.println("9-Insertar en TablaGame");
		System.out.println("10-Insertar en TablaPlayer");
		System.out.println("11-Insertar en TablaCompras");
		System.out.println("12-Actualizar TablaGame");
		System.out.println("13-Actualizar TablaPlayer");
		System.out.println("14-Actualizar TablaCompras");
		System.out.println("15-Consultar TablaGame");
		System.out.println("16-Consultar TablaPlayer");
		System.out.println("17-Consultar TablaCompras");
		System.out.println("18-Consultar todas las tablas");
		System.out.println("19-Eliminar todas las tablas");
		System.out.println("0- Salir");
	}

	/**
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaCompras.insertarTabla(idPlayer, idGames, cosa, precio, fechaCompra);
	 * 
	 * @throws Exception
	 */
	private static void insertarTablaCompra() throws Exception {
		int idPlayer;
		int idGames;
		String cosa;
		double precio;
		String fechaStr;
		Date fechaCompra;
		System.out.println("Ingrese el ID del Player:");
		idPlayer = scanner.nextInt();
		System.out.println("Ingrese el ID del Juego:");
		idGames = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese la descripción (cosa):");
		cosa = scanner.nextLine();
		System.out.println("Ingrese el precio:");
		precio = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Ingrese la fecha de compra (YYYY-MM-DD):");
		fechaStr = scanner.nextLine();
		fechaCompra = Date.valueOf(fechaStr);
		TablaCompras.insertarTabla(idPlayer, idGames, cosa, precio, fechaCompra);
		System.out.println("Compra insertada correctamente.");
	}

	/**
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaGame.insertarTabla(nombre, tiempoJugado); fechaCompra);
	 * 
	 * @throws Exception
	 */
	private static void insertarTablaGame() throws Exception {
		String nombre;
		String tiempoJugado;
		System.out.println("Ingrese el nombre del juego:");
		nombre = scanner.nextLine();
		System.out.println("Ingrese el tiempo jugado (en horas o formato deseado):");
		tiempoJugado = scanner.nextLine();
		TablaGame.insertarTabla(nombre, tiempoJugado);
		System.out.println("Juego insertado correctamente.");
	}

	/**
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaPlayer.insertarTabla(nick, password, email);
	 * 
	 * @throws Exception
	 */
	private static void insertarTablaPlayer() throws Exception {
		String nick;
		String password;
		String email;
		System.out.println("Ingrese el Nick:");
		nick = scanner.nextLine();
		System.out.println("Ingrese la contraseña:");
		password = scanner.nextLine();
		System.out.println("Ingrese el email:");
		email = scanner.nextLine();
		TablaPlayer.insertarTabla(nick, password, email);
		System.out.println("Player insertado correctamente.");
	}

	/**
	 * 
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaCompras.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idCompra = " +
	 * idCompra);
	 * 
	 * (EL ARRAY ES SIMPLMENTE PARA QUE NO PUEDA ESCRIBIR EL CAMPO MAL
	 * 
	 * @throws Exception
	 */
	private static void updateTablaCompras() throws Exception {
		int eleccion;
		String nombreCampo;
		String nuevoValor;
		int idCompra;
		String[] camposValidos = { "idPlayer", "idGames", "Cosa", "Precio", "FechaCompra" };
		System.out.println("Seleccione el campo que desea actualizar: (1,2,3,4,5)");
		System.out.println(Arrays.toString(camposValidos));
		do {
			System.out.print("Ingrese el número correspondiente al campo: ");
			eleccion = scanner.nextInt();
		} while (eleccion < 1 || eleccion > camposValidos.length);
		nombreCampo = camposValidos[eleccion - 1];
		System.out.print("Ingrese el nuevo valor para " + nombreCampo + ": ");
		scanner.nextLine();
		nuevoValor = scanner.nextLine();
		System.out.print("Ingrese el ID de la compra a actualizar (idCompra): ");
		idCompra = scanner.nextInt();
		TablaCompras.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idCompra = " + idCompra);
		System.out.println("Actualización realizada correctamente.");
	}

	/**
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaPlayer.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idPlayer = " +
	 * idPlayer);
	 * 
	 * (EL ARRAY ES SIMPLMENTE PARA QUE NO PUEDA ESCRIBIR EL CAMPO MAL
	 * 
	 * @throws Exception
	 */
	private static void updateTablaPlayer() throws Exception {
		String nuevoValor;
		int idPlayer;
		int eleccion;
		String nombreCampo;
		String[] camposValidos = { "Nick", "password", "email" };
		System.out.println("Seleccione el campo que desea actualizar: (1,2,3)");
		System.out.println(Arrays.toString(camposValidos));
		do {
			System.out.print("Ingrese el número correspondiente al campo: ");
			eleccion = scanner.nextInt();
		} while (eleccion < 1 || eleccion > camposValidos.length);

		nombreCampo = camposValidos[eleccion - 1];
		System.out.print("Ingrese el nuevo valor para " + nombreCampo + ": ");
		scanner.nextLine();
		nuevoValor = scanner.nextLine();
		System.out.print("Ingrese el ID del jugador a actualizar (idPlayer): ");
		idPlayer = scanner.nextInt();
		TablaPlayer.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idPlayer = " + idPlayer);
		System.out.println("Actualización realizada correctamente.");
	}

	/**
	 * Pide por parametro los campos de la tabla y llama a la funcion
	 * TablaGame.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idGames = " +
	 * idGames);
	 * 
	 * (EL ARRAY ES SIMPLMENTE PARA QUE NO PUEDA ESCRIBIR EL CAMPO MAL
	 * 
	 * @throws Exception
	 */
	private static void updateTablaGame() throws Exception {

		String[] camposValidos = { "Nombre", "tiempoJugado" };
		int eleccion;
		String nuevoValor;
		int idGames;
		String nombreCampo;
		System.out.println("Seleccione el campo que desea actualizar: (1,2)");
		System.out.println(Arrays.toString(camposValidos));
		do {
			System.out.print("Ingrese el número correspondiente al campo: ");
			eleccion = scanner.nextInt();
		} while (eleccion < 1 || eleccion > camposValidos.length);

		nombreCampo = camposValidos[eleccion - 1];
		System.out.print("Ingrese el nuevo valor para " + nombreCampo + ": ");
		scanner.nextLine();
		nuevoValor = scanner.nextLine();
		if (nombreCampo.equals("tiempoJugado")) {
			if (!nuevoValor.matches("\\d{2}:\\d{2}:\\d{2}")) {
				System.out.println("El formato de tiempo debe ser HH:MM:SS.");
				return;
			}
		}
		System.out.print("Ingrese el ID del juego a actualizar (idGames): ");
		idGames = scanner.nextInt();
		TablaGame.updateTabla(nombreCampo, "'" + nuevoValor + "'", "idGames = " + idGames);
		System.out.println("Actualización realizada correctamente.");
	}

}
