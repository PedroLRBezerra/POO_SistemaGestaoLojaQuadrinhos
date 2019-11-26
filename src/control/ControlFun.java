package control;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.DAOException;
import DAO.FuncionarioDaoImpl;
import entity.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFun {
	
	private ObservableList<Funcionario> lista = 
			FXCollections.observableArrayList();
	
	public void adicionar(Funcionario f) throws SQLException, ClassNotFoundException { 
		try {
			FuncionarioDaoImpl fDAO =new FuncionarioDaoImpl();
			getLista().add(f);
			fDAO.adicionar(f);
		} catch (DAOException f1) {
			f1.printStackTrace();
		}
	}
	public Funcionario pesquisarPorCPF(String cpf) throws ClassNotFoundException, SQLException { 
		try {
			FuncionarioDaoImpl fDAO = new FuncionarioDaoImpl();
			return fDAO.pesquisar(cpf);	
		} catch (DAOException f1) {
			f1.printStackTrace();
		}
		return null;
	}
	
	public void exclui(Funcionario f) throws DAOException, SQLException, ClassNotFoundException {
		FuncionarioDaoImpl fDAO = new FuncionarioDaoImpl();
		getLista().remove(f);	
	}

	public ObservableList<Funcionario> getLista() {
		return lista;
	}
	
	public void buscarFuncionarios() {
		ObservableList<Funcionario> l = 
				FXCollections.observableArrayList();
		
		FuncionarioDaoImpl fDAO = new FuncionarioDaoImpl();
		
		List<Funcionario> ltemp = new LinkedList<Funcionario>();
		try {
			ltemp=fDAO.buscar();
			for (Funcionario funcionario : ltemp) {
				l.add(funcionario);
			}
			lista=l;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
