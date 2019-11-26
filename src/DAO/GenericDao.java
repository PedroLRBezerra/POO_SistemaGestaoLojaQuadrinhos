package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao implements IGenericDao {
	
	private Connection c;

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=quadrinhos;namedPipes=true",
				"raj", "123");
		System.out.println("Conexão OK");
		return c;
	}

}
