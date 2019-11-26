package DAO;

import java.util.List;

import entity.Exemplar;

public interface ExemplarDAO {
	
	public void adicionar(Exemplar  e) throws DAOException;
	List<Exemplar> pesquisarPorTipo(String titulo) throws DAOException;
	List<Exemplar> pesquisarPorEdicao(int id) throws DAOException;

}
