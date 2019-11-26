package DAO;

import java.util.List;

import entity.Funcionario;

public interface FuncionarioDao {
	void  adicionar ( Funcionario  f )  throws  DAOException ;
	
	Funcionario pesquisar(String CPF) throws DAOException;

	void removerPorCPF(String CPF) throws DAOException;
	
	List<Funcionario> buscar() throws DAOException;

	
}
