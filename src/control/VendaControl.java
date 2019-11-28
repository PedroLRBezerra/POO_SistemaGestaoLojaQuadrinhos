package control;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.ClienteDaoImpl;
import DAO.DAOException;
import DAO.EdicaoDAOImpl;
import DAO.ExemplarDAOImpl;
import DAO.FuncionarioDaoImpl;
import DAO.VendaDaoImp;
import entity.Cliente;
import entity.Edicao;
import entity.Exemplar;
import entity.Funcionario;
import entity.Venda;

public class VendaControl {
	
	public List<Edicao> buscarEdicoesPorTitulo(String t){
		try {
			EdicaoDAOImpl edDao = new EdicaoDAOImpl();
			try {
				List<Edicao> ed = edDao.pesquisarPorTipo(t);
				return ed;
			} catch (DAOException e) {
				e.printStackTrace();
				System.out.println("A");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("B");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("C");
		}
		return null;
	}
	
	public List<Exemplar> buscarExemplaresPorEdicao(int id){
		List<Exemplar> lex = new LinkedList<Exemplar>();
		
		ExemplarDAOImpl exDao=null;
		try {
			exDao = new ExemplarDAOImpl();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			lex=exDao.pesquisarPorEdicao(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lex;
	}
	
	public List<Cliente> buscarClientes(){
		List<Cliente> lc = new LinkedList<Cliente>();
		ClienteDaoImpl cliDao;
		try {
			cliDao = new ClienteDaoImpl();
			lc=cliDao.buscarClientes();
		} catch (ClassNotFoundException | SQLException | DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lc;
	}
	
	public Funcionario getFunc() {
		FuncionarioDaoImpl fDao= new FuncionarioDaoImpl();
		try {
			return fDao.pesquisar("25186533710");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void finalizarVenda(Venda v) {
		
	}

}
