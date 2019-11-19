package entity;

public class Cartao extends Pagamento {
	private int QtdParcela;
	private String Agencia;
	
	public int getQtdParcela() {
		return QtdParcela;
	}
	public void setQtdParcela(int qtdParcela) {
		QtdParcela = qtdParcela;
	}
	public String getAgencia() {
		return Agencia;
	}
	public void setAgencia(String agencia) {
		Agencia = agencia;
	}

}
