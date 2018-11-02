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
	String databaseName = "postgres";          //The database name e.g. LADM_COLTable
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

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		DBConnector dbConnector = new DBConnector();
		Connection conn1 = dbConnector.connect();
		//conn1.
	}
}

