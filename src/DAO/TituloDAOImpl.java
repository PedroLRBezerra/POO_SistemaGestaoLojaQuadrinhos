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
	private static final String URL = "";
	private static final String USUARIO = "";
	private static final String SENHA = "";
	

	@Override
	public void adicionar(Titulo t) throws DAOException {
		try {
			Connection con = DriverManager.getConnection(URL,USUARIO,SENHA);
			String sql = "INSERT INTO titulo " 
					+ "(titulo, autor)"
					+ "VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getTitulo());
			ps.setString(2, t.getAutor());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	@Override
	public List<Titulo> pesquisarPorTipo(String titulo) throws DAOException {
		List<Titulo> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USUARIO, SENHA);
			String sql = "SELECT * FROM titulo "
					+ "WHERE titulo LIKE ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + titulo + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Titulo t = new Titulo();
				t.setTitulo(rs.getString("titulo"));
				t.setAutor(rs.getString("autor"));
				lista.add(t);
			}
		}catch(SQLException e){
				e.printStackTrace();
				throw new DAOException(e);
			}
	  return lista;
		}
	}


