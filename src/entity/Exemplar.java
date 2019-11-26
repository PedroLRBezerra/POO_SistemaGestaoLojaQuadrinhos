package entity;

public class Exemplar {
	private String exemplar;
	private Edicao edicao;
	private double valorCompra;
	private double valorVenda;
	
	
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
	
	public Edicao getEdicao() {
		return edicao;
	}
	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}
	public String getExemplar() {
		return exemplar;
	}
	public void setExemplar(String exemplar) {
		this.exemplar = exemplar;
	}
	
	@Override
	public String toString() {
		return getExemplar();
	}

}
