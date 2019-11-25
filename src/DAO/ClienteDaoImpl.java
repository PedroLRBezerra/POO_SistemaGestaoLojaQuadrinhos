package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;

public class ClienteDaoImpl {
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
			String sql = "INSERT INTO Cliente (Id,Nome,CPF,telefone,email,dataNascimento) " +
			"VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, c.getId());
			stmt.setString(2, c.getNome());
			stmt.setString(5, c.getCPF());
			stmt.setString(3, c.getTelefone());
			stmt.setString(4, c.getemail());
			stmt.setDate(7, new java.sql.Date(c.getdataNascimento().getTime()));
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
			String sql = "SELECT * FROM Cliente WHERE Nome LIKE ?";
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
			String sql = "DELETE FROM Cliente WHERE Nome=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Nome);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}	
}
