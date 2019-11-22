package entity;

import java.util.Date;

public class Edicao {
	private int edicao;
	private Date lançamento;
	//private double valorCompra;
	//private double valorVenda;
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
	//	public double getValorCompra() {
	//		return valorCompra;
	//	}
	//	public void setValorCompra(double valorCompra) {
	//		this.valorCompra = valorCompra;
	//	}
	//	public double getValorVenda() {
	//		return valorVenda;
	//	}
	//	public void setValorVenda(double valorVenda) {
	//		this.valorVenda = valorVenda;
	//}
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

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(getEdicao());
	}
}
