package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Cliente;

public class ClienteControl {
	private ObservableList<Cliente> lista = 
			FXCollections.observableArrayList();

	public void adicionar(Cliente c) { 
		getLista().add(c);
	}

	public Cliente pesquisarPorNomes(String nome) { 
		for (Cliente c : getLista()) { 
			if (c.getNome().contains(nome)) { 
				return c;
			}
		}
		return null;
	}

	public ObservableList<Cliente> getLista() {
		return lista;
	}
}

