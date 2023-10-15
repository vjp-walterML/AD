package ejercicio5;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import ejercicio4.*;

public class Ejercicio5 {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// Instanciamos File con la ruta del fichero que queremos leer
		File fichero = new File("esdla.xml");
		// Instanciamos un objeto Esdla y lo recuperamos del XML usando el ejercicio4
		Esdla esdla = Ejercicio4.leerXML_Eslda(fichero);
		// Escribimos mediante DOM un nuevo xml añadiendo las modificaciones requeridas
		escribir_esdla_actualizado(esdla);
	}

	public static void escribir_esdla_actualizado(Esdla esdla) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		//AÑADO AL OBJETO LA NUEVA CIUDAD Y ELIMINO EL ULTIMO PERSONAJE DE CADA LISTA DE PERSONAJES COMO ME PIDE EL ENUNCIADO
		Ciudad ciudad = new Ciudad("Osgiliath", "Gondor");
		esdla.getCiudades().add(ciudad);
		esdla.getPersonajes().getHobbits().remove(esdla.getPersonajes().getHobbits().size()-1);
		esdla.getPersonajes().getElfos().remove(esdla.getPersonajes().getElfos().size()-1);
		esdla.getPersonajes().getEnanos().remove(esdla.getPersonajes().getEnanos().size()-1);
		esdla.getPersonajes().getHombres().remove(esdla.getPersonajes().getHombres().size()-1);
		esdla.getPersonajes().getMagos().remove(esdla.getPersonajes().getMagos().size()-1);
		esdla.getPersonajes().getVillanos().remove(esdla.getPersonajes().getVillanos().size()-1);
		// Instanciamos un DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// Creamos un builder
		DocumentBuilder builder = dbf.newDocumentBuilder();
		// DomImplementation
		DOMImplementation implementacion = builder.getDOMImplementation();
		// Creamos un Document
		Document esdlaXML = implementacion.createDocument(null, "esdla", null);
		// Establecemos la version XML
		esdlaXML.setXmlVersion("1.0");
		// ================== E S C R I B I M O S ==================
		Text texto;
		// AUTOR con sus modificaciones
		Element autor = esdlaXML.createElement("autor");
		Element nombre = esdlaXML.createElement("nombre");
		texto = esdlaXML.createTextNode(esdla.getAutor());
		nombre.appendChild(texto);
		Element diminutivo = esdlaXML.createElement("diminutivo");
		texto = esdlaXML.createTextNode("J. R. R. Tolkien");
		diminutivo.appendChild(texto);
		Element nacimiento = esdlaXML.createElement("nacimiento");
		texto = esdlaXML.createTextNode("1892");
		nacimiento.appendChild(texto);
		Element fallecimiento = esdlaXML.createElement("fallecimiento");
		texto = esdlaXML.createTextNode("1892");
		fallecimiento.appendChild(texto);
		autor.appendChild(nombre);
		autor.appendChild(diminutivo);
		autor.appendChild(nacimiento);
		autor.appendChild(fallecimiento);
		esdlaXML.getDocumentElement().appendChild(autor);
		
		//CIUDADES
		
		//PERSONAJES
		// ================== F I N   E S C R I T U R A ==================
		Source origen = new DOMSource(esdlaXML);
		Result resultado = new StreamResult(System.out);
		Transformer transformador =  TransformerFactory.newInstance().newTransformer();
		transformador.transform(origen, resultado);
	}
}
