package acadt_ut3_p2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static acadt_ut3_p2.Main.PUERTO;

public class Utileria {
    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() throws ClassNotFoundException, SQLException {
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/empresa", "root", "");
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

    //SIMULACIÓN ESPERA
    public static void simulacionEspera() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            Thread.sleep(1000);
        }
    }
}
