package control;

import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.EdicaoBoundary;
import view.TituloBoundary;


public class ExemplarControl {
	private ObservableList<Exemplar> lista = 
			FXCollections.observableArrayList();
	
	public ObservableList<Edicao> edicoes = FXCollections.observableArrayList();
	
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
	
	public void updateEdicaoList(Titulo titulo) {
		ObservableList<Edicao> ed = FXCollections.observableArrayList();
		
		for (Edicao edicao : EdicaoBoundary.controlEd.getLista()) {
			if(titulo==edicao.getTitulo()) {
				ed.add(edicao);
				System.out.println("aa");
			}
		}
		
		this.edicoes=ed;
	}
}
