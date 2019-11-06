package control;

import entity.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFun {
	
	private ObservableList<Funcionario> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Funcionario f) { 
		getLista().add(f);
	}
	
	public Funcionario pesquisarPorCPF(String cpf) { 
		for (Funcionario f : getLista()) { 
			if (f.getCPF().contains(cpf)){ 
				return f;
			}
		}
		return null;
	}

	public ObservableList<Funcionario> getLista() {
		return lista;
	}

}
