package entity;

public class Dinheiro extends Pagamento {
	private double ValorPago;
	private double ValorTroco;
	
	public double getValorPago() {
		return ValorPago;
	}
	public void setValorPago(double valorPago) {
		ValorPago = valorPago;
	}
	public double getValorTroco() {
		return ValorTroco;
	}
	public void setValorTroco(double valorTroco) {
		ValorTroco = valorTroco;
	}

}
