package entity;

public class Exemplar {
	private int exemplar;
	private Edicao edicao;
	private double valorCompra;
	
	
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	private double valorVenda;
	
	public Edicao getEdicao() {
		return edicao;
	}
	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}
	public int getExemplar() {
		return exemplar;
	}
	public void setExemplar(int exemplar) {
		this.exemplar = exemplar;
	}
	

}
