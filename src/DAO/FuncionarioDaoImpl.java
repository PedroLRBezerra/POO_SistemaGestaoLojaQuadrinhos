package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Funcionario;

public class FuncionarioDaoImpl implements FuncionarioDao {
	
	@Override
	public void adicionar(Funcionario f) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String sql = "INSERT INTO Funcionarios (CPF,Nome,Telefone,Login,Senha,cargo,nascimento,Salario) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, f.getCPF());
			stmt.setString(2, f.getNome());
			stmt.setLong(3, f.getTelefone());
			stmt.setString(4, f.getLogin());
			stmt.setString(5, f.getSenha());
			stmt.setString(6, f.getCargo());
			stmt.setDate(7, new java.sql.Date(f.getNascimento().getTime()));
			stmt.setLong(8, (long) f.getSalario());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}
	
	public Funcionario pesquisar (String CPF) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Funcionario f = new Funcionario();
		try {
			String sql = "SELECT * FROM Funcionarios WHERE CPF = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,CPF);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				f.setCPF(rs.getString("CPF"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getLong("telefone"));
				f.setNascimento(rs.getDate("nascimento"));
				f.setCargo(rs.getString("cargo"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salario"));
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return f;
	}

	@Override
	public void removerPorCPF(String  CPF) throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String sql = "DELETE FROM Funcionarios WHERE CPF=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, CPF);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<Funcionario> buscar() throws DAOException {
		GenericDao gDao = new GenericDao();
		Connection con=null;
		try {
			con = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Funcionario> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM funcionarios";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setCPF(rs.getString("CPF"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getLong("telefone"));
				f.setNascimento(rs.getDate("nascimento"));
				f.setCargo(rs.getString("cargo"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salario"));
				lista.add(f);
			}
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;	}	
}
