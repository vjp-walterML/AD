package practica_entregable;

import java.sql.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import static practica_entregable.Main.*;

public class Utileria {
    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() {
        //Cargamos el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        //Establecemos la conexión
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/" + BD, "root", "");
        } catch (SQLException e) {
            System.out.println("\n ERROR: CONEXIÓN FALLIDA.");
            return null;
        }
        System.out.println("\n ¡CONEXIÓN REALIZADA CORRECTAMENTE!");
        return conexion;
    }

    //PEDIR ENTERO
    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR FLOAT
    public static float pedirFloat(String texto) {
        Scanner s = new Scanner(System.in);
        float entero;
        System.out.println(texto);
        try {
            entero = s.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR STRING
    public static String pedirString(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine();
    }

    //PEDIR DATE
    public static Date pedirDate() {
        Scanner s = new Scanner(System.in);
        LocalDate fecha;
        String fechaString = pedirString("Introduce la fecha en formato '1998-08-27':");
        String[] fechaArray = fechaString.split("-");
        try {
            fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]), Integer.valueOf(fechaArray[2]));
        } catch (DateTimeException e) {
            System.out.println("ERROR:Por favor, introduzca una fecha válida.");
            return pedirDate();
        }
        return Date.valueOf(fecha);
    }

    //SIMULACIÓN ESPERA
    public static void simulacionEspera(String texto) {
        System.out.print("\n" + texto);
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(" .");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
        System.out.println();//Salto de línea
    }

    //SI O NO
    public static boolean seguir(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine().equalsIgnoreCase("si");
    }
}
