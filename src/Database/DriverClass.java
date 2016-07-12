package Database;

import java.sql.*;

public class DriverClass {
	Connection c;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost/minimarket2", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}
}
