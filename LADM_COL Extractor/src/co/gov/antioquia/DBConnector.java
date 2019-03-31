/**
 * 
 */
package co.gov.antioquia;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 */

import java.sql.*;

/**
 * @author Juan Sebastián Lobo R.
 *
 */
public class DBConnector {
	Connection conn = null;
	Connection connTest = null;

	/**
	 * Conecta a la base de datos PostgreSQL usando datos de conexión.
	 * 
	 * @param databaseHost String que contiene la dirección del servidor de base de
	 *                     datos PostgreSQL. E.j.: 'localhost' o '127.0.0.1'.
	 * @param port         Entero que contiene el puerto de escucha del servidor de
	 *                     base de datos PostgreSQL. E.j.: '5432'.
	 * @param databaseName String que contiene el nombre de la base de datos. E.j.:
	 *                     'ladm_col'.
	 * @param username     String que contiene el nombre de usuario. E.j.:
	 *                     'postgres'.
	 * @param password     String que contiene la contraseña.
	 * @return String con el estado de conexión.
	 */
	public String connect(String databaseHost, int port, String databaseName, String username, String password) {
		String status = "";
		String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;

		try {
			conn = DriverManager.getConnection(connectionUrl, username, password);
			status = "Connected to the PostgreSQL server successfully.";
		} catch (SQLException e) {
			status = e.getMessage();
		}
		return status;
	}

	/**
	 * Cierra la conexión abierta en el momento.
	 * @param connection Instancia de conexión del JDBC.
	 * @return String con estado de conexión.
	 */
	public String disconnect(Connection connection) {
		String status = "";
		try {
			connection.close();
			status = "Disconnected from the PostgreSQL server successfully.";
		} catch (Exception e) {
			status = e.getMessage();
			/* ignored */ }
		return status;
	}

	/**
	 * Ejecuta consulta a partir de una sentencia SQL por medio de conexión abierta.
	 * 
	 * @param query Sentencia SQL de interés.
	 * @param connection Instancia de conexión del JDBC.
	 * @return ResultSet instancia que contiene el resultado de la consulta.
	 */
	public ResultSet execQuery(String query, Connection connection) {
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {
			// System.out.println(e.getMessage());

		}
		return resultSet;
	}

	/**
	 * Realiza prueba de conexión, creando una conexión a partir de los datos de
	 * entrada y cerrando la misma posteriormente.
	 * 
	 * @param databaseHost String que contiene la dirección del servidor de base de
	 *                     datos PostgreSQL. E.j.: 'localhost' o '127.0.0.1'.
	 * @param port         Entero que contiene el puerto de escucha del servidor de
	 *                     base de datos PostgreSQL. E.j.: '5432'.
	 * @param databaseName String que contiene el nombre de la base de datos. E.j.:
	 *                     'ladm_col'.
	 * @param username     String que contiene el nombre de usuario. E.j.:
	 *                     'postgres'.
	 * @param password     String que contiene la contraseña.
	 * @return String con estado de prueba de conexión.
	 */
	public String testConnection(String databaseHost, int port, String databaseName, String username, String password) {
		String status = "";
		String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;
		try {
			DriverManager.getConnection(connectionUrl, username, password);
			status = "¡Success!";
			// System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			status = e.getMessage();
		}
		return status;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DBConnector dbConnector = new DBConnector();
		String status = dbConnector.connect("localhost", 5432, "ladm_col", "postgres", "C4tastr0");
		System.out.println(status);
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String MUTA_BARRIO = "";
		try {
			// resultSet = dbConnector.execQuery("SELECT * FROM public.col_derecho",
			// dbConnector.conn);
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			resultSet.next();
			resultSetTemp = dbConnector.execQuery(
					"SELECT barrio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
					dbConnector.conn);
			resultSetTemp.next();
			MUTA_BARRIO = resultSetTemp.getString("barrio");
			System.out.printf("Barrio: " + MUTA_BARRIO + "%n");

			// int count = resultSet.getInt(1);
			// while (resultSet.next()) {
			// System.out.printf("%-30.30s %-30.30s%n", resultSet.getString("t_id"),
			// resultSet.getString("tipo"));
			// System.out.printf("%-30.30s %-30.30s%n", resultSet.getString(1),
			// resultSet.getString(5));
			// System.out.printf("Cantidad de registros: " + total + "%n");
			// result = resultSet.getString("count");
			// }
			// }
			System.out.println(dbConnector.disconnect(dbConnector.conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// try { rs.close(); } catch (Exception e) { /* ignored */ }
			try {
				resultSet.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		System.out.printf("Status: " + "%s",
				dbConnector.testConnection("localhost", 5432, "ladm_col", "postgres", "C4tastr0"));
	}
}
