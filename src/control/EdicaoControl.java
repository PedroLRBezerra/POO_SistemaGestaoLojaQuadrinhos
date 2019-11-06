package control;

import entity.Edicao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EdicaoControl {
	
	// criacao de uma lista observavel de Ediçoes
	private ObservableList<Edicao> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Edicao e) { 
		getLista().add(e);
	}
	
	public Edicao pesquisarPorTipo(String titulo , int edicao) { 
		for (Edicao e : getLista()) { 
			if(e.getTitulo().toString().equals(titulo)) {
				if (e.getEdicao() == edicao){ 
					return e;
				}
			}
		}
		return null;
	}

	public ObservableList<Edicao> getLista() {
		return lista;
	}
}
