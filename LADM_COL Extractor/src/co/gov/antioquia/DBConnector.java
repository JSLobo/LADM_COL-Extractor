/**
 * 
 */
package co.gov.antioquia;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 */

import java.sql.*;
import java.util.Properties;


/**
 * @author JLOBOR
 *
 */
public class DBConnector {
	Boolean conexion = false;
	String databaseHost = "localhost"; //The address of the PostgreSQL database server e.g., localhost
	int port = 5432;					   //The port on listen the PostgreSQL database server e.g., 5432
	String databaseName = "ladm_col";          //The database name e.g. LADM_COLTable
	String username = "postgres";              //The username of the account that you will use to connect to the database.
	String password = "C4tastr0";			   //The password of the account that you will use to connect to the database.


	private String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;

	/**
	 * Connect to the PostgreSQL database
	 * @return a Connection object
	 */
	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl, username, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void disconnect(Connection connection) {
		try { connection.close();
		System.out.println("Disconnected from the PostgreSQL server successfully.");
		} catch (Exception e) { /* ignored */ }
	}


	public ResultSet execQuery(String query, Connection connection) {
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		}catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return resultSet;
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		DBConnector dbConnector = new DBConnector();
		Connection conn = dbConnector.connect();
		ResultSet resultSet = dbConnector.execQuery("SELECT * FROM public.col_derecho", conn);//conn1.

		try {
			while (resultSet.next()) {
				System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("t_id"), resultSet.getString("tipo"));

			}
			dbConnector.disconnect(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		}

		
	}
}

