package entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Venda {
	private Date data;
	private double ValorTotal;
	public List<Exemplar> exemplarSelecionados = new LinkedList<Exemplar>();
	private Cliente cliente;
	
	public List<Exemplar> getExemplarSelecionados() {
		return exemplarSelecionados;
	}

	public void setExemplarSelecionados(List<Exemplar> exemplarSelecionados) {
		this.exemplarSelecionados = exemplarSelecionados;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(double valorTotal) {
		ValorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
