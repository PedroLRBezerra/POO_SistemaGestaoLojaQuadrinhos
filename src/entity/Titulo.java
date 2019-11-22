package entity;

public class Titulo {
	
	private int id;
	private String titulo;
	private String Autor;
	
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
	

}
