package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Titulo;

public class TituloDAOImpl implements TituloDAO{

	private Connection c;
	
	public TituloDAOImpl() throws ClassNotFoundException, SQLException {
		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	

	@Override
	public void adicionar(Titulo t) throws DAOException {
		try {
			String sql = "INSERT INTO titulos " 
					+ "(titulo, autor, id)"
					+ "VALUES (?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, t.getTitulo());
			ps.setString(2, t.getAutor());
			ps.setInt(3, t.getId()); 
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	@Override
	public List<Titulo> pesquisarPorTipo(String titulo) throws DAOException {
		List<Titulo> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM titulos "
					+ "WHERE titulo LIKE ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + titulo + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setAutor(rs.getString("autor"));
				lista.add(t);
			}
			rs.close();
			ps.close();
		}catch(SQLException e){
				e.printStackTrace();
				throw new DAOException(e);
			}
	  return lista;
		}

	@Override
	public void atualizaTitulo(Titulo t) throws DAOException {
		try {
		String sql = "UPDATE titulos SET titulo = ?, autor = ?"
				+ "WHERE id = ?"
				+ "VALUES (?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, t.getTitulo());
				ps.setString(2, t.getAutor());
				ps.setInt(3, t.getId()); // Talvez tenha q criar um Id no titulo
				ps.execute();
				ps.close();
				}
		catch(SQLException e){
			e.printStackTrace();
			throw new DAOException(e);
		}
				
	}

	@Override
	public void excluiTitulo(Titulo t) throws DAOException {
		try {
		String sql = "DELETE titulos WHERE id = ?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, t.getId()); // Talvez tenha q criar um Id no titulo
				ps.execute();
				ps.close();
		} catch(SQLException e){
			e.printStackTrace();
			throw new DAOException(e);
		}
				
	}


	@Override
	public int proximoId() throws DAOException{
		String sql = "SELECT MAX(id) + 1 AS proximo_id FROM titulos";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("proximo_id");
			} else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	}


