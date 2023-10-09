package acadt_ut2_p5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaAutores implements Serializable {
	
	private List<Autor> autores = new ArrayList<>();

	public ListaAutores(List<Autor> autores) {
		super();
		this.autores = autores;
	}

	public void add(Autor autor) {
		autores.add(autor);
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void mostrar() {
		for (Autor autor : autores) {
			autor.mostrar();
		}
	}
}
