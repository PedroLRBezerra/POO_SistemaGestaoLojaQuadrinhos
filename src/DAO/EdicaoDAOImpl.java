package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Edicao;
import entity.Titulo;

public class EdicaoDAOImpl implements EdicaoDAO {
	
	private Connection c;
	
	public  EdicaoDAOImpl() throws ClassNotFoundException, SQLException {
		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
		

	@Override
	public void adicionar(Edicao e) throws DAOException {
		String sql = "INSERT INTO edicao "
				+ "(num_edicao,lancamento,descricao,titulo_id)"
				+ " VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getEdicao());
			long t = e.getLançamento().getTime();
			java.sql.Date d = new java.sql.Date(t);
			ps.setDate(2, d);
			ps.setString(3, e.getDescricao());
			ps.setInt(4, e.getTitulo().getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		
	}

	@Override
	public void excluiTitulo(Edicao e) throws DAOException {
		String sql = "DELETE e "
				+ "FROM edicao e, titulo t "
				+ "WHERE t.id = e.titulo_id "
				+ "AND t.titulo = ?"
				+ "AND e.num_edicao = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getTitulo().getTitulo()); 
			ps.setInt(2, e.getEdicao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public List<Edicao> pesquisarPorTipo(String titulo) throws DAOException {
		List<Edicao> lista = new ArrayList<Edicao>();
		try {
			String sql = "SELECT * FROM edicao e , titulo t " + 
					"WHERE t.id = e.titulo_id AND " + 
					"t.titulo LIKE ?";
			PreparedStatement ps = c.prepareStatement(sql);
		//	ps.setInt(1, edicao);
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Edicao e = new Edicao();
				e.setEdicao(rs.getInt("num_edicao"));
				e.setDescricao(rs.getString("descricao"));
				e.setLançamento(rs.getDate("lancamento"));
				
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setId(rs.getInt("id"));
				t.setAutor(rs.getString("autor"));
				t.setTitulo_alt(rs.getString("titulo_alt"));
				
				e.setTitulo(t);
				lista.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		return lista;

	}


	@Override
	public List<Edicao> pesquisarPorTitulo(String titulo) throws DAOException {
		List<Edicao> lista = new ArrayList<Edicao>();
		try {
			String sql = "SELECT * FROM edicao e , titulo t " + 
					"WHERE t.id = e.titulo_id AND " + 
					" t.titulo LIKE ?";
			PreparedStatement ps = c.prepareStatement(sql);
		
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Edicao e = new Edicao();
				e.setEdicao(rs.getInt("num_edicao"));
				e.setDescricao(rs.getString("descricao"));
				e.setLançamento(rs.getDate("lancamento"));
				
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setId(rs.getInt("id"));
				t.setAutor(rs.getString("autor"));
				t.setTitulo_alt(rs.getString("titulo_alt"));
				
				e.setTitulo(t);
				lista.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		return lista;

	}
}
