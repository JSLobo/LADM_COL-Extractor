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
    Connection conn = null;
    Connection connTest = null;

	private String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;

	/**
	 * Connect to the PostgreSQL database
	 * @return a Connection object
	 */
	public String connect(String databaseHost, int port, String databaseName, String username, String password) {
		String status = "";
		String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;

		try {
			conn = DriverManager.getConnection(connectionUrl, username, password);
			status = "Connected to the PostgreSQL server successfully.";
		}catch (SQLException e) {
			status = e.getMessage();
		}
		return status;
	}
	
	public String disconnect(Connection connection) {
		String status = "";
		try { connection.close();
		status = "Disconnected from the PostgreSQL server successfully.";
		} catch (Exception e) { 
			status = e.getMessage();/* ignored */ }
		return status;
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
	
	public String testConnection(String databaseHost, int port, String databaseName, String username, String password) {
		String status = "";
		String connectionUrl = "jdbc:postgresql://" + databaseHost + ":" + port + "/" + databaseName;
		try {
			DriverManager.getConnection(connectionUrl, username, password);
			status = "¡Success!";
			//System.out.println("Connected to the PostgreSQL server successfully.");
		}catch (SQLException e) {
			status = e.getMessage();
		}
		return status;
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		DBConnector dbConnector = new DBConnector();
		String status = dbConnector.connect("localhost", 5432, "ladm_col", "postgres", "C4tastr0");
		System.out.println(status);
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String result = "";
		String total = "";
		String MUTA_NRO_FICHA = "";
		String MUTA_BARRIO = "";
		int summatory = 0;
		try {
			//resultSet = dbConnector.execQuery("SELECT * FROM public.col_derecho", dbConnector.conn);
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			resultSet.next();
			//while(resultSet.next()) {
			//resultSet.next();
			//total = Integer.toString((resultSet.getInt(1) * 7) + 1);
			MUTA_NRO_FICHA = resultSet.getString("nupre");// (9)
			resultSetTemp = dbConnector
					.execQuery("SELECT barrio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
							dbConnector.conn);
			resultSetTemp.next();
			MUTA_BARRIO = resultSetTemp.getString("barrio");
			System.out.printf("Barrio: " + MUTA_BARRIO + "%n");
			
			//int count = resultSet.getInt(1);	
			//while (resultSet.next()) {
				//System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("t_id"), resultSet.getString("tipo"));
				//System.out.printf("%-30.30s %-30.30s%n", resultSet.getString(1), resultSet.getString(5));
				//System.out.printf("Cantidad de registros: " + total + "%n");
				 //result = resultSet.getString("count");
			//}
			//}
			System.out.println(dbConnector.disconnect(dbConnector.conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		}

		
		System.out.printf("Status: " + "%s",dbConnector.testConnection("localhost", 5432, "ladm_col", "postgres", "C4tastr0"));
	}
}

