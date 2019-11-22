package DAO;

import java.util.List;

import entity.Titulo;


public interface TituloDAO {
	public void adicionar(Titulo t) throws DAOException;
	public void atualizaTitulo(Titulo t) throws DAOException;
	public void excluiTitulo(Titulo t) throws DAOException;
	List<Titulo> pesquisarPorTipo(String tipo) throws DAOException;
	public int proximoId() throws DAOException;
}
