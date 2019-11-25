package DAO;

import java.util.List;

import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;

public interface VendaDao {
	
	List<Exemplar> buscarExemplares(Edicao ed);
}
