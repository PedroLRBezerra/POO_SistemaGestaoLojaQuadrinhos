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
	public void adicionar(Cliente c) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			String sql = "INSERT INTO Clientes (Nome,CPF,telefone,email,dataNascimento) " +
			"VALUES ( ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(4, c.getCPF());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getemail());
			stmt.setDate(5, new java.sql.Date(c.getdataNascimento().getTime()));
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}
	
	public List<Cliente> pesquisar (String Nome) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Cliente> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Clientes WHERE Nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + Nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Cliente c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setCPF(rs.getString("CPF"));
				c.setTelefone(rs.getString("telefone"));
				c.setemail(rs.getString("email"));
				c.setdataNascimento(rs.getDate("data_nascimento"));
				lista.add(c);
			}
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}

	public void removerPorNome(String  Nome) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
