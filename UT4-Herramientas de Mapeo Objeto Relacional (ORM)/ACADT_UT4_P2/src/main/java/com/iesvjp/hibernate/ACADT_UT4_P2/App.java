package com.iesvjp.hibernate.ACADT_UT4_P2;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {
	public static void main(String[] args) {
		// Quitar comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACADT_UT4_P2");
		EntityManager em = emf.createEntityManager();

		// Menu1
		menu1(em);
		// Libero recursos
		em.close();
		emf.close();
	}

	// PRIMER MENÚ
	// ====================================================================
	public static void menu1(EntityManager em) {
		char opcion;
		do {
			opcion = mostrarMenu1();
			switch (opcion) {
			case 'A':
				menu1OpcionA(em);
				break;
			case 'B':
				menu1OpcionB(em);
				break;
			case 'C':
				menu1OpcionC(em);
				break;
			case 'D':
				menu1OpcionD(em);
				break;
			case 'E':
				menu1OpcionE(em);
				break;
			case 'F':
				menu1OpcionF(em);
				break;
			case 'G':
				menu1OpcionG(em);
				break;
			case 'H':
				break;
			case 'I':
				break;
			case 'J':
				break;
			case 'K':
				break;
			case 'L':
				break;
			case 'M':
				System.out.println("Hasta pronto!");
				break;

			default:
				System.out.println("ERROR. Introduce una opción válida.");
				break;
			}
		} while (opcion != 'M');

	}

	// Mostrar menú
	private static char mostrarMenu1() {
		System.out.println("\n***********************************************");
		System.out.println("***************** M E N U   1 *****************");
		System.out.println("***********************************************");
		System.out.println("A. Muestra la cantidad de productos que hay en el catálogo.\r\n"
				+ "B. Muestra todos aquellos pedidos (orders) cuyo estado esté en “disputed”\r\n"
				+ "C. Muestra el pago de mayor cuantía realizado\r\n"
				+ "D. Muestra aquello empleados de la oficina de “San Francisco”\r\n"
				+ "E. Muestra el número de empleados que hay por oficina. Muestra el código de \r\n"
				+ "oficina, la ciudad y el número total de empleados. Ordena las oficinas por orden \r\n"
				+ "creciente de número de empleados.\r\n\n"
				+ "F. Muestra aquellos clientes (customer) que hayan sido atendidos por el \r\n"
				+ "empleado Gerard Hernandez\r\n\n" + "G. Muestra al empleado/s que haya/n atendido a más clientes.\r\n"
				+ "H. Aumenta un 5% el precio de venta (campo MSRP) de todos los productos cuya \r\n"
				+ "línea de productos sea Motorcycles. Redondea el precio de venta resultante a \r\n"
				+ "2 decimales\r\n\n" + "I. Muestra el total recaudado en ventas del año 2005\r\n"
				+ "J. Muestra el nombre de los clientes que se hayan gastado más de 120.000€\r\n"
				+ "K. Muestra el mejor cliente (aquel que más se haya gastado comprando). Utiliza \r\n"
				+ "setMaxResults(1)\r\n\n"
				+ "L. Muestra el mejor cliente (aquel que más se haya gastado comprando). Utiliza \r\n" + "SQL nativo");
		System.out.println("M. Salir.");
		System.out.println("*********************************************");
		return pedirChar("Introduzca una opción: ");
	}

	// OPCIONES MENÚ 1
	// ===========================================================================
	public static void menu1OpcionA(EntityManager em) {
		String sql = "select count(p) from Product p";
		Query consulta = em.createQuery(sql);
		long numProductos = (long) consulta.getSingleResult();
		System.out.println("El número total de productos es " + numProductos);
	}

	public static void menu1OpcionB(EntityManager em) {
		String sql = "select o from Order o where o.status = 'disputed'";
		Query consulta = em.createQuery(sql);
		List<Order> result = consulta.getResultList();

		for (Order order : result) {
			System.out.println(order.toString());
		}

	}

	public static void menu1OpcionC(EntityManager em) {
		String sql = "select max(p.amount) from Payment p";
		Query consulta = em.createQuery(sql);
		BigDecimal maxPago = (BigDecimal) consulta.getSingleResult();
		System.out.println("El pago de mayor cuantía es " + maxPago);
	}

	public static void menu1OpcionD(EntityManager em) {
		String hql = "select e from Employee e where e.office.city = 'San Francisco'";
		Query query = em.createQuery(hql);
		List<Employee> resultado = query.getResultList();

		for (Employee employee : resultado) {
			System.out.println(employee.toString());
		}
	}

	public static void menu1OpcionE(EntityManager em) {
		String hql = "select o.officeCode, o.city, size(o.employees) from Office o group by o.officeCode order by size(o.employees)";
		Query query = em.createQuery(hql);
		List<Object[]> resultado = query.getResultList();
		for (Object[] o : resultado) {
			System.out.println("- OfficeCode:" + o[0] + ", City:" + o[1] + ", NumEmpleados:" + o[2]);
		}
	}

	public static void menu1OpcionF(EntityManager em) {
		String hql = "select c from Customer c where c.employee.firstName = 'Gerard' and c.employee.lastName = 'Hernandez'";
		Query query = em.createQuery(hql);
		List<Customer> resultado = query.getResultList();
		for (Customer customer : resultado) {
			System.out.println(customer.toString());
		}
	}

	public static void menu1OpcionG(EntityManager em) {
		String hql = "select e.employeeNumber, e.firstName, size(e.customers) from Employee e "
				+ "group by e.employeeNumber "
				+ "having size(e.customers) >= ALL (select size(em.customers) from Employee em group by em.employeeNumber)";
		Query query = em.createQuery(hql);
		List<Object[]> resultado = query.getResultList();
		for (Object[] o : resultado) {
			System.out.println("- NumEmpleado:" + o[0] + ", Nombre:" + o[1] + ", NumClientes:" + o[2]);
		}
	}

	public static void menu1OpcionH(EntityManager em) {
		String hql
		Query query = em.createQuery(hql);
	}

	// UTILERIA
	// =======================================================================
	// PEDIR ENTERO
	public static int pedirEntero(String texto) {
		Scanner s = new Scanner(System.in);
		int entero;
		System.out.println(texto);
		try {
			entero = s.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			entero = pedirEntero(texto);
		}
		return entero;
	}

	// PEDIR CHAR
	public static char pedirChar(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine().charAt(0);
	}

	// PEDIR FLOAT
	public static float pedirFloat(String texto) {
		Scanner s = new Scanner(System.in);
		float flotante;
		System.out.println(texto);
		try {
			flotante = s.nextFloat();
		} catch (InputMismatchException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			flotante = pedirFloat(texto);
		}
		return flotante;
	}

	// PEDIR STRING
	public static String pedirString(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine();
	}

	// PEDIR DATE
	public static Date pedirDate(String texto) {
		LocalDate fecha;
		String fechaString = pedirString(texto);
		String[] fechaArray = fechaString.split("-");
		try {
			fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]),
					Integer.valueOf(fechaArray[2]));
		} catch (DateTimeException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			return pedirDate(texto);
		}
		return Date.valueOf(fecha);
	}

	// SI O NO
	public static boolean seguir(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine().equalsIgnoreCase("si");
	}
}
