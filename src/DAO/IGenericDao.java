package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface IGenericDao {
	public Connection getConnection() throws ClassNotFoundException, SQLException;
}
