package control;

import java.util.List;

import DAO.DAOException;
import DAO.TituloDAO;
import DAO.TituloDAOImpl;
import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TituloControl {
	
	private ObservableList<Titulo> lista = 
			FXCollections.observableArrayList();
	
	private TituloDAO tDAO = new TituloDAOImpl();
	
	public void adicionar(Titulo t) { 
		getLista().add(t);
		try {
			tDAO.adicionar(t);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisarPorTipo(String titulo) { 
		try {
		List<Titulo> listaTitulos = tDAO.pesquisarPorTipo(titulo);
		for (Titulo t : listaTitulos) {
				lista.add(t);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
}

	public ObservableList<Titulo> getLista() {
		return lista;
	}

}
