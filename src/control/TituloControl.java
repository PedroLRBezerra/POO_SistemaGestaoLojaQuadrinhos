package control;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOException;
import DAO.TituloDAO;
import DAO.TituloDAOImpl;
import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class TituloControl {
	
	private ObservableList<Titulo> lista = 
			FXCollections.observableArrayList();
	
	private TextField tfId;
	
	
	public void adicionar(Titulo t) {
		TituloDAOImpl tDAO;
		try {
			tDAO = new TituloDAOImpl();
			getLista().add(t);
			tDAO.adicionar(t);
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisarPorTipo(String titulo) { 
		TituloDAOImpl tDAO;
		try {
			tDAO = new TituloDAOImpl();
		List<Titulo> listaTitulos = tDAO.pesquisarPorTipo(titulo);
		for (Titulo t : listaTitulos) {
				lista.add(t);
			}
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();		
		}
		
		
}
	// Possivel teste para escrever o ID automaticamente
  /*	public void proximoId() {
		try {
			TituloDAOImpl tDAO = new TituloDAOImpl();
			tfId.setText(String.valueOf(tDAO.proximoId()));
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
	}
*/
	public ObservableList<Titulo> getLista() {
		return lista;
	}

}
