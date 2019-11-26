package control;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOException;
import DAO.EdicaoDAOImpl;
import DAO.ExemplarDAOImpl;
import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.EdicaoBoundary;
import view.TituloBoundary;


public class ExemplarControl {
	private ObservableList<Exemplar> lista = 
			FXCollections.observableArrayList();
	
	public ObservableList<Edicao> edicoes = FXCollections.observableArrayList();
	
	public void adicionar(Exemplar e) { 
		try {
			ExemplarDAOImpl eDAO = new ExemplarDAOImpl();
			lista.clear();
			getLista().add(e);
			eDAO.adicionar(e);
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void pesquisarPorTipo(String titulo) { 
		try {
			ExemplarDAOImpl eDAO = new ExemplarDAOImpl();
			lista.clear();
			List<Exemplar> listaEdicao = eDAO.pesquisarPorTipo(titulo);
			for(Exemplar e : listaEdicao) {
				lista.add(e);
			}
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
	}

	public ObservableList<Exemplar> getLista() {
		return lista;
	}
	
	public List<Edicao> updateEdicaoList(String titulo) {
		EdicaoDAOImpl edDao;
		try {
			edDao = new EdicaoDAOImpl();
			List<Edicao> ed = edDao.pesquisarPorTipo(titulo);
			return ed;
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
}
