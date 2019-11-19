package DAO;

import java.util.List;

import entity.Titulo;


public interface TituloDAO {
	void adicionar(Titulo t) throws DAOException;
	List<Titulo> pesquisarPorTipo(String tipo) throws DAOException;
}
