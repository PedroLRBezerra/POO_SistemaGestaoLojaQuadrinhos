package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;

public class ExemplarDAOImpl implements ExemplarDAO{
	
private Connection c;
	
	public  ExemplarDAOImpl() throws ClassNotFoundException, SQLException {
		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void adicionar(Exemplar e) throws DAOException {
		String sql = "INSERT INTO exemplar"
				+ " ((exemplar,valor_compra,valor_venda,id_edicao)"
				+ " VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getExemplar());
			ps.setDouble(2, e.getValorCompra());
			ps.setDouble(3, e.getValorVenda());
			ps.setInt(4, e.getEdicao().getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		
	}

	@Override
	public List<Exemplar> pesquisarPorTipo(String titulo) throws DAOException {
		List<Exemplar> lista = new ArrayList<Exemplar>();
		String sql = "SELECT * FROM titulo t , edicao e, exemplar ex " + 
				"WHERE ex.id_edicao = e.id_edicao AND e.titulo_id = t.id  AND " + 
				"t.titulo LIKE ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Exemplar ex = new Exemplar();
				ex.setExemplar(rs.getString("exemplar"));
				ex.setValorCompra(rs.getDouble("valor_compra"));
				ex.setValorVenda(rs.getDouble("valor_venda"));
				
				Edicao e = new Edicao();
				e.setEdicao(rs.getInt("num_edicao"));
				e.setDescricao(rs.getString("descricao"));
				e.setLançamento(rs.getDate("lancamento"));
				e.setId(rs.getInt("id_edicao"));
				
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setId(rs.getInt("id"));
				t.setAutor(rs.getString("autor"));
				t.setTitulo_alt(rs.getString("titulo_alt"));
				
				e.setTitulo(t);
				ex.setEdicao(e);
				lista.add(ex);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		return lista;
	}

	@Override
	public List<Exemplar> pesquisarPorEdicao(int id) throws DAOException {
		List<Exemplar> lista = new ArrayList<Exemplar>();
		String sql = "SELECT * FROM titulo t , edicao e, exemplar ex " + 
				"WHERE ex.id_edicao = e.id_edicao AND e.titulo_id = t.id  AND " + 
				"ex.id_edicao LIKE ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Exemplar ex = new Exemplar();
				ex.setExemplar(rs.getString("exemplar"));
				ex.setValorCompra(rs.getDouble("valor_compra"));
				ex.setValorVenda(rs.getDouble("valor_venda"));
				
				Edicao e = new Edicao();
				e.setEdicao(rs.getInt("num_edicao"));
				e.setDescricao(rs.getString("descricao"));
				e.setLançamento(rs.getDate("lancamento"));
				e.setId(rs.getInt("id_edicao"));
				
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setId(rs.getInt("id"));
				t.setAutor(rs.getString("autor"));
				t.setTitulo_alt(rs.getString("titulo_alt"));
				
				e.setTitulo(t);
				ex.setEdicao(e);
				lista.add(ex);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		}
		return lista;
	}


}
