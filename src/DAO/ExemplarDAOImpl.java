package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Exemplar;

public class ExemplarDAOImpl implements ExemplarDAO{
	
private Connection c;
	
	public  ExemplarDAOImpl() throws ClassNotFoundException, SQLException {
		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void adicionar(Exemplar e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Exemplar> pesquisarPorTipo(String titulo) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


}
