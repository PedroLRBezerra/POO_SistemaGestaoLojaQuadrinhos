package entity;

import java.util.Date;

public class Edicao {
	private int id;
	private int edicao;
	private Date lançamento;
	private String descricao;
	private Titulo titulo;
	
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public Date getLançamento() {
		return lançamento;
	}
	public void setLançamento(Date lançamento) {
		this.lançamento = lançamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(getEdicao());
	}
}
