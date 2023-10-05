package acadt_ut2_p5;

import java.io.Serializable;
import java.util.List;

public class Libro implements Serializable {

	private String ISBN;
	private String titulo;
	private List<Autor> autores;

	public Libro(String iSBN, String titulo, List<Autor> autores) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autores = autores;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	// MOSTRAR
	public void mostrar() {
		System.out.println("----- LIBRO -----");
		System.out.println("ISBN:" + ISBN);
		System.out.println("Título:" + titulo);
		System.out.println("Autores:");
		for (Autor autor : autores) {
			System.out.println("\t" + autor.getNombre());
		}
	}
}
