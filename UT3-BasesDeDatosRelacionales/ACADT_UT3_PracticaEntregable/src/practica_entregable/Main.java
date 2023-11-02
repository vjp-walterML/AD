package practica_entregable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;

public class Main {

    //    Variables globales
    public final static int PUERTO = 3306;
    public final static String BD = "nombreBD";

    public static void main(String[] args) {
//        Muestro bienvenida
        mostrarBienvenida();
        Utileria.simulacionEspera("ESTABLECIENDO CONEXIÓN CON LA BASE DE DATOS");
//        Establezco la conexión
        Connection conexion = Utileria.establecerConexion();
//        Fuerzo la finalización del programa si la conexión ha sido fallida.
        if (conexion == null) return;
//        Creo estructura de tablas automaticamente e introduzco datos de ejemplo
        crearEstructuraTablas(conexion);
        insertarDatosEjemplo(conexion);
//        MENÚ GENERAL================================================================
        int opcion;
        do {
            opcion = mostrarMenuGeneral();
            switch (opcion) {
                case 1:
//                    GESTIÓN DE CLIENTES
                    break;
                case 2:
//                    GESTIÓN DE VEHICULOS
                    break;
                case 3:
//                    GESTIÓN DE VENTAS
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 4);

        //Liberamos recursos
        try {
            conexion.close();
        } catch (SQLException e) {
        }
    }

    //    Bienvenida
    public static void mostrarBienvenida() {
        System.out.println("*********************************************************");
        System.out.println("***************** Verstappen Inc. S.A. ******************");
        System.out.println("*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("* Bienvenido a la aplicación de gestión denuestra       *");
        System.out.println("* empresa, con esta herramienta podrás gestionar        *");
        System.out.println("* todo lo relacionado con la compra-venta de vehículos. *");
        System.out.println("*                                                       *");
        System.out.println("--------- [APLICACIÓN DESARROLLADA POR WALTER] ----------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //    Menú general
    public static int mostrarMenuGeneral() {
        System.out.println("*********************************************");
        System.out.println("********** M E N U   G E N E R A L **********");
        System.out.println("*********************************************");
        System.out.println("1.- Gestión de clientes.");
        System.out.println("2.- Gestión de vehículos.");
        System.out.println("3.- Gestión de ventas.");
        System.out.println("4.- Salir.");
        System.out.println("*********************************************");
        return Utileria.pedirEntero("Introduzca una opción: ");
    }

    //    Crear estructura de tablas
    public static void crearEstructuraTablas(Connection conexion) {
        try {
            //TABLA VEHÍCULOS
            Statement sentencia = null;
            sentencia = conexion.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS VEHICULOS( ";
            sql += "matricula VARCHAR(7) PRIMARY KEY, ";
            sql += "marca VARCHAR(23) NOT NULL, ";
            sql += "color VARCHAR(23) NOT NULL, ";
            sql += "precio FLOAT(6,2) NOT NULL);";
            sentencia.executeUpdate(sql);
            //TABLA CLIENTES
            sql = "CREATE TABLE IF NOT EXISTS CLIENTES( ";
            sql += "nif VARCHAR(9) PRIMARY KEY, ";
            sql += "nombre VARCHAR(23) NOT NULL, ";
            sql += "apellidos VARCHAR(23) NOT NULL); ";
            sentencia.executeUpdate(sql);
            //TABLA VENTAS
            sql = "CREATE TABLE IF NOT EXISTS VENTAS( ";
            sql += "id INT PRIMARY KEY, ";
            sql += "matricula VARCHAR(7) NOT NULL, ";
            sql += "nifCliente VARCHAR(9) NOT NULL, ";
            sql += "descuento FLOAT(6,2), ";
            sql += "motivoDescuento VARCHAR(50), ";
            sql += "fechaVenta DATE, ";
            sql += "CONSTRAINT fk_matricula FOREIGN KEY (matricula) REFERENCES VEHICULOS(matricula) ON DELETE CASCADE ON UPDATE CASCADE,";
            sql += "CONSTRAINT fk_nifCliente FOREIGN KEY (nifCliente) REFERENCES CLIENTES(nif) ON DELETE CASCADE ON UPDATE CASCADE;)";
            sentencia.executeUpdate(sql);

            //LIBERAMOS RECURSOS
            sentencia.close();
        } catch (SQLException e) {
        }
    }

    //    Insertar datos de ejemplo
    public static void insertarDatosEjemplo(Connection conexion) {
        try {
            Statement sentencia = conexion.createStatement();
            String sql;
            //INSERT VEHÍCULOS
            sql = "INSERT INTO VEHICULOS (matricula, marca, color, precio) VALUES ";
            sql += "('ABC1234', 'Toyota', 'Rojo', 20000.50), ";
            sql += "('XYZ5678', 'Ford', 'Azul', 25000.75), ";
            sql += "('LMN9012', 'Honda', 'Blanco', 23000.00), ";
            sql += "('DEF3456', 'Chevrolet', 'Negro', 27000.30), ";
            sql += "('GHI7890', 'Nissan', 'Gris', 21000.20); ";
            sentencia.executeUpdate(sql);

            //INSERT CLIENTES
            sql = "INSERT INTO CLIENTES (nif, nombre, apellidos) VALUES ";
            sql += "('12345678A', 'Juan', 'Pérez García'), ";
            sql += "('23456789B', 'María', 'López Torres'), ";
            sql += "('34567890C', 'Carlos', 'González Ruiz'), ";
            sql += "('45678901D', 'Ana', 'Fernández Morales'), ";
            sql += "('56789012E', 'David', 'Martínez Jiménez');";
            sentencia.executeUpdate(sql);

            //INSERT VENTAS
            sql = "INSERT INTO VENTAS (id, matricula, nifCliente, descuento, motivoDescuento, fechaVenta) VALUES ";
            sql += "(1, 'ABC1234', '12345678A', 500.00, 'Promoción de verano', '2023-11-02'), ";
            sql += "(2, 'XYZ5678', '23456789B', 0.00, NULL, '2023-11-02'), ";
            sql += "(3, 'LMN9012', '34567890C', 300.00, 'Descuento por lealtad', '2023-11-02'), ";
            sql += "(4, 'DEF3456', '45678901D', 0.00, NULL, '2023-11-02'), ";
            sql += "(5, 'GHI7890', '56789012E', 200.00, 'Promoción de fin de año', '2023-11-02'); ";
            sentencia.executeUpdate(sql);

            //LIBERAMOS RECURSOS
            sentencia.close();
        } catch (SQLException e) {
        }
    }
}
