package DAO;

import java.util.List;

import entity.Funcionario;

public interface FuncionarioDao {
	void  adicionar ( Funcionario  f )  throws  DAOException ;
	
	List<Funcionario> pesquisar(String CPF) throws DAOException;

	void removerPorCPF(String CPF) throws DAOException;

	
}
