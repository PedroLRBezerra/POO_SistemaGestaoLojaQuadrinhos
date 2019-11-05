package control;

import entity.Edição;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EdicaoControl {
	
	// criacao de uma lista observavel de Ediçoes
	private ObservableList<Edição> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Edição e) { 
		getLista().add(e);
	}
	
	public Edição pesquisarPorTipo(String titulo , int edicao) { 
		for (Edição e : getLista()) { 
			if(e.getTitulo().toString().equals(titulo)) {
				if (e.getEdicao() == edicao){ 
					return e;
				}
			}
		}
		return null;
	}

	public ObservableList<Edição> getLista() {
		return lista;
	}
}
