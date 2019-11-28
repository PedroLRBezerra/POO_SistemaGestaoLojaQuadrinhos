package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.ClienteDaoImpl;
import DAO.DAOException;
import entity.Cliente;

public class ClienteControl {
	private ObservableList<Cliente> lista = 
			FXCollections.observableArrayList();

	public void adicionar(Cliente c) {
			ClienteDaoImpl cDAO;
			try {
				cDAO = new ClienteDaoImpl();
				getLista().add(c);
				cDAO.adicionar(c);
			} catch (ClassNotFoundException | SQLException | DAOException e) {
				e.printStackTrace();
			}
	}

	public Cliente pesquisarPorNomes(String nome) {
		try {
			ClienteDaoImpl cDAO = new ClienteDaoImpl();
			return cDAO.pesquisar(nome);
		} catch (ClassNotFoundException | SQLException | DAOException e){
			e.printStackTrace();
			}
		return null;
	}
	
	public void exclui(Cliente c) {
		try {
			ClienteDaoImpl cDAO = new ClienteDaoImpl();
			cDAO.removerPorNome(c.getNome());
		} catch (ClassNotFoundException | SQLException | DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarClientes() {
		ObservableList<Cliente> c = 
				FXCollections.observableArrayList();
		try {
			ClienteDaoImpl cDAO = new ClienteDaoImpl();
			List<Cliente> ltemp = new LinkedList<Cliente>();
			ltemp = cDAO.buscarClientes();
			for (Cliente cliente :  ltemp) {
				c.add(cliente);
			}
			lista = c;
		} catch (ClassNotFoundException | SQLException  | DAOException e) {
			e.printStackTrace();
		}
		
		
	}

	public ObservableList<Cliente> getLista() {
		return lista;
	}
}

