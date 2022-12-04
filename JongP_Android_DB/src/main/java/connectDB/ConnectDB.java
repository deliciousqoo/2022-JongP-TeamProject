package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	//private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER_UNIVERSITY ="jongP";
	private static final String USER_PASSWD ="jongP123";
		
	private Connection conn;
	private boolean connected;
	
	public ConnectDB() {
		this.connected = false;
		this.conn = null; // Connection object
		
		try {
			// Load a JDBC driver for Oracle DBMS
			System.out.println("Connecting");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object 
			System.out.println("Success!");
		}catch(ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try{
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD); 
			conn.setAutoCommit(false);
			this.connected = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public Connection getConn() {
		return conn;
	}

	public boolean isConnected() {
		return connected;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}