package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {

		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) {

		String myURL = SampleURL.replace("${dbServer}", this.serverName).replace("${dbName}", this.databaseName)
				.replace("${user}", user).replace("${pass}", pass);
		try {
			System.out.println(myURL);
			this.connection = DriverManager.getConnection(myURL);
		} catch (SQLException e) {
			System.out.println("failed to set db connection");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
