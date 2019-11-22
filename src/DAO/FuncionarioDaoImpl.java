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
		Connection con = DBUtil.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO Funcionario (CPF,Nome,Telefone,Login,Senha,Nascimento,Salario) " +
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
	
	public List<Funcionario> pesquisar (String CPF) throws DAOException {
		Connection con = DBUtil.getInstance().getConnection();
		List<Funcionario> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Funcionario WHERE CPF LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + CPF + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setCPF(rs.getString("CPF"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getLong("telefone"));
				f.setNascimento(rs.getDate("data_nascimento"));
				f.setCargo(rs.getString("cargo"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salário"));
				lista.add(f);
			}
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void removerPorCPF(String  CPF) throws DAOException {
		Connection con = DBUtil.getInstance().getConnection();
		try {
			String sql = "DELETE FROM Funcionario WHERE CPF=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, CPF);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}	
}
