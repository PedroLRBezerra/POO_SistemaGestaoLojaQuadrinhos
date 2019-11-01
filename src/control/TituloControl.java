package control;

import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TituloControl {
	
	private ObservableList<Titulo> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Titulo t) { 
		getLista().add(t);
	}
	
	public Titulo pesquisarPorTipo(String titulo) { 
		for (Titulo t : getLista()) { 
			if (t.getTitulo().contains(titulo)){ 
				return t;
			}
		}
		return null;
	}

	public ObservableList<Titulo> getLista() {
		return lista;
	}

}
