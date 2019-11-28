package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Cliente;

public class ClienteDaoImpl implements ClienteDao{
	
	private Connection con;
	public ClienteDaoImpl() throws ClassNotFoundException, SQLException {
		
	IGenericDao gDao = new GenericDao();
    con = gDao.getConnection();
	}	
	
	public void adicionar(Cliente c) throws DAOException {
		try {
			String sql = "INSERT INTO Clientes (Nome,CPF,telefone,email,nascimento) " +
			"VALUES ( ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getCPF());
			stmt.setString(3, c.getTelefone());
			stmt.setString(4, c.getemail());
			stmt.setDate(5, new java.sql.Date(c.getdataNascimento().getTime()));
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}
	
	public Cliente pesquisar (String Nome) throws DAOException {
		Cliente c = new Cliente();
		try {
			String sql = "SELECT * FROM Clientes WHERE Nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + Nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setCPF(rs.getString("CPF"));
				c.setTelefone(rs.getString("telefone"));
				c.setemail(rs.getString("email"));
				c.setdataNascimento(rs.getDate("nascimento"));
			}
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return c;
	}

	public void removerPorNome(String  Nome) throws DAOException {
		try {
			String sql = "DELETE FROM Clientes WHERE Nome=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Nome);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}	
	
	@Override
	public List<Cliente> buscarClientes() throws DAOException {
		List<Cliente> l = new LinkedList<Cliente>();
		try {
			String sql = "SELECT * FROM Clientes ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Cliente c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setCPF(rs.getString("CPF"));
				c.setTelefone(rs.getString("telefone"));
				c.setemail(rs.getString("email"));
				c.setdataNascimento(rs.getDate("nascimento"));
				l.add(c);
			}
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return l;
		
	}
}
