package DAO;

import java.util.List;

import entity.Cliente;
import entity.Funcionario;

public interface ClienteDao {
void  adicionar ( Cliente c )  throws  DAOException ;
	
	Cliente pesquisar(String Nome) throws DAOException;

	void removerPorNome(String Nome) throws DAOException;
	
	List<Cliente> buscarClientes() throws DAOException;
}
