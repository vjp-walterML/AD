package acadt_ut2_p5;

import java.io.Serializable;

public class Autor implements Serializable {

	private String nombre;

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void mostrar() {
		System.out.println(nombre);
	}

}
