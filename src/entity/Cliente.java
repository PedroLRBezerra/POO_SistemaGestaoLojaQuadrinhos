package entity;

import java.util.Date;

public class Cliente {
	private long id;
	private String nome;
	private String CPF;
	private String telefone;
	private String email; 
	private Date dataNascimento;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String  getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public Date getdataNascimento() {
		return dataNascimento;
	}
	public void setdataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}


