package control;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOException;
import DAO.EdicaoDAOImpl;
import DAO.TituloDAO;
import DAO.TituloDAOImpl;
import entity.Edicao;
import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class TituloControl {
	
	private ObservableList<Titulo> lista = 
			FXCollections.observableArrayList();
	
	private TextField tfId;
	
	
	public TituloControl(TextField tfId) {
		this.tfId = tfId;
	}
	
	public void adicionar(Titulo t) {
		TituloDAOImpl tDAO;
		try {
			tDAO = new TituloDAOImpl();
			getLista().add(t);
			tDAO.adicionar(t);
			lista.clear();
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisarPorTipo(String titulo) { 
		TituloDAOImpl tDAO;
		try {
			tDAO = new TituloDAOImpl();
			lista.clear();
		List<Titulo> listaTitulos = tDAO.pesquisarPorTipo(titulo);
		for (Titulo t : listaTitulos) {
				lista.add(t);
			}
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();		
		}
		
	}
	
	public void exclui(Titulo t) {
		try {
			TituloDAOImpl tDAO = new TituloDAOImpl();
			getLista().remove(t);
			tDAO.excluiTitulo(t);
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	// Possivel teste para escrever o ID automaticamente
  	public void proximoId() {
		try {
			TituloDAOImpl tDAO = new TituloDAOImpl();
			tfId.setText(String.valueOf(tDAO.proximoId()));
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Titulo> getLista() {
		return lista;
	}

}
