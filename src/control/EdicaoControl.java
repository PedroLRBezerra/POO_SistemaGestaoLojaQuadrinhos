package control;

import java.sql.SQLException;
import java.util.List;

import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;

import DAO.DAOException;
import DAO.EdicaoDAOImpl;
import entity.Edicao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EdicaoControl {
	
	// criacao de uma lista observavel de Ediçoes
	private ObservableList<Edicao> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Edicao e) { 
		try {
			EdicaoDAOImpl eDAO = new EdicaoDAOImpl();
			lista.clear();
			getLista().add(e);
			eDAO.adicionar(e);
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void pesquisarPorTipo(String titulo) {
		try {
			EdicaoDAOImpl eDAO = new EdicaoDAOImpl();
			lista.clear();
			List<Edicao> listaEdicao = eDAO.pesquisarPorTipo(titulo);
			for(Edicao e : listaEdicao) {
				lista.add(e);
			}
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void exclui(Edicao e) {
		try {
			EdicaoDAOImpl eDAO = new EdicaoDAOImpl();
			getLista().remove(e);
			eDAO.excluiTitulo(e);
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
		
	}

	public ObservableList<Edicao> getLista() {
		return lista;
	}

	public int pegarID(Edicao e) {
		int id = 0;
		try {
			EdicaoDAOImpl eDao = new EdicaoDAOImpl();
			id = eDao.pegarID(e);
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
		return id;
	}
}
