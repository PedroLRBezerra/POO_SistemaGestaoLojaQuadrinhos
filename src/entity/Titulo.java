package entity;

public class Titulo {
	
	private int id;
	private String titulo;
	private String Autor;
	private String titulo_alt;
	
	public String getTitulo(){
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	
	@Override
	public String toString() {
		return titulo ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo_alt() {
		return titulo_alt;
	}
	public void setTitulo_alt(String titulo_alt) {
		this.titulo_alt = titulo_alt;
	}
	

}
