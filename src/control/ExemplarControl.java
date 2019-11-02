package control;

import entity.Exemplar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExemplarControl {
	private ObservableList<Exemplar> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Exemplar e) { 
		getLista().add(e);
	}
	
	public Exemplar pesquisarPorTipo(int exemplar) { 
		for (Exemplar e : getLista()) { 
			if (e.getExemplar() == exemplar){ 
				return e;
			}
		}
		return null;
	}

	public ObservableList<Exemplar> getLista() {
		return lista;
	}
}
