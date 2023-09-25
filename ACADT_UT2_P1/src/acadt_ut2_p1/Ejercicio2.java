package acadt_ut2_p1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio2 {

	public static File fichero = new File("contactos.obj");

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(fichero));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
		Contacto contacto;

		//Escribimos
		do {
			contacto = new Contacto();
			contacto.rellenarContacto();
			escribirFichero(dos, contacto);
		} while (seguir());
		
		//Leemos y escribimos en xml
		
	}

	public static void escribirFichero(ObjectOutputStream dos, Contacto contacto) throws IOException {
		dos.writeObject(contacto);
	}

	public static void leerFichero(ObjectInputStream ois) {
		while(true) {
			
		}
	}
	
	public static boolean seguir() {
		Scanner s = new Scanner(System.in);
		System.out.println("Desea seguir?");
		return s.nextLine().equalsIgnoreCase("si");
	}
}
