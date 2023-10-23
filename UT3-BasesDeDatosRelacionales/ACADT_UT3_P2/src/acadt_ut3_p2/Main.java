package acadt_ut3_p2;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static acadt_ut3_p2.Utileria.*;

public class Main {
    //Variables globales
    final static int PUERTO = 3306;

    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        int opcion;
        Connection conexion;
//        Mensaje de espera ---------------------------------------
        System.out.println("CONECTANDO A LA BASE DE DATOS");
        simulacionEspera();
        conexion = establecerConexion();
        System.out.println("\n¡CONEXIÓN REALIZADA CORRECTAMENTE!");
        Thread.sleep(1000);
//        Fin de mensaje de espera ---------------------------------

        do {
            opcion = mostrarMenuGeneral();
            switch (opcion) {
                case 1:
                    gestionEmpleados(conexion);
                    break;
                case 2:
                    gestionDepartamentos(conexion);
                    break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 3);

        //Liberamos recursos
        conexion.close();
    }
    
    //MENÚ GENERAL
    public static int mostrarMenuGeneral() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("*********************************************");
        System.out.println("********** M E N U   G E N E R A L **********");
        System.out.println("*********************************************");
        System.out.println("1.- Gestión de empleados.");
        System.out.println("2.- Gestión de departamentos.");
        System.out.println("3.- Salir.");
        System.out.println("*********************************************");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGestionEmpleados();
        }
        return opcion;
    }

    //MENÚ GESTIÓN DE EMPLEADOS
    public static int mostrarMenuGestionEmpleados() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("==== GESTIÓN DE EMPLEADOS ====");
        System.out.println("1.- Insertar empleado.");
        System.out.println("2.- Modificar empleado.");
        System.out.println("3.- Borrar empleado.");
        System.out.println("4.- Consultar todos los empleados.");
        System.out.println("5.- Consultar todos los empleados de un departamento.");
        System.out.println("6.- Consultar empleados por el número de empleado.");
        System.out.println("7.- Consultar empleados que tengan un salario superior al introducido.");
        System.out.println("8.- Consultar empleados que tengan un salario igual o inferior al introducido.");
        System.out.println("9.- Salir al menú general.");
        System.out.println("===============");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGestionEmpleados();
        }
        return opcion;
    }

    public static void gestionEmpleados(Connection conexion) throws SQLException {
        int opcion;
        do {
            opcion = mostrarMenuGestionEmpleados();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    opcion4GestionEmpleados(conexion);

                    break;
                case 5:

                    break;
                case 6:
                    opcion6GestionEmpleados(conexion);
                    break;
                case 7:
                    opcion7GestionEmpleados(conexion);
                    break;
                case 8:
                    opcion8GestionEmpleados(conexion);
                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;

            }
        } while (opcion != 9);
    }

    //MENÚ GESTIÓN DE DEPARTAMENTOS
    public static int mostrarMenuGestionDepartamentos() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("==== GESTIÓN DE DEPARTAMENTOS ====");
        System.out.println("1.- Insertar departamento.");
        System.out.println("2.- Modificar departamento.");
        System.out.println("3.- Eliminar departamento.");
        System.out.println("4.- Consultar todos los departamentos.");
        System.out.println("5.- Consultar departamento por nombre.");
        System.out.println("6.- Consultar departamento por ID.");
        System.out.println("7.- Salir al menú general.");
        System.out.println("===============");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: No puedes introducir una letra.");
            opcion = mostrarMenuGestionEmpleados();
        }
        return opcion;
    }

    public static void gestionDepartamentos(Connection conexion) throws SQLException {
        int opcion;
        do {
            opcion = mostrarMenuGestionDepartamentos();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    System.out.println("Error:Introduzca una opción válida.");
                    break;
            }
        } while (opcion != 7);
    }

    //GESTIÓN DE EMPLEADOS ==================================================
    public static void opcion4GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO: Consultar todos los empleados
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS";
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        System.out.println("=== LISTADO DE EMPLEADOS===");
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion6GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:  Consultar empleados por el campo “emp_no”.
        System.out.println("========= EMPLEADOS =========");
        int emp_no = pedirEntero("Introduzca el número de empleado: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE emp_no = ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setInt(1, emp_no);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion7GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario superior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario > ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setFloat(1, salario);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    public static void opcion8GestionEmpleados(Connection conexion) throws SQLException {
//       ENUNCIADO:   Consultar empleados que tengan un salario igual o inferior al introducido por el usuario.
        System.out.println("========= EMPLEADOS =========");
        float salario = pedirFloat("Introduzca un salario: ");
        //Ejecutamos sentencias
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT emp_no,apellido,oficio,fecha_alt,salario,dept_no FROM EMPLEADOS WHERE salario <= ?";
        PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
        sentenciaPreparada.setFloat(1, salario);
        ResultSet resultado = sentencia.executeQuery(sql);//Resultado es como un iterador
        //Recorremos el resultado
        while (resultado.next()) {
            System.out.println("------------------------");
            System.out.println("Número de empleado: " + resultado.getInt(1));
            System.out.println("Apellido: " + resultado.getString(2));
            System.out.println("Oficio: " + resultado.getString(3));
            System.out.println("Fecha de alta: " + resultado.getDate(4));
            System.out.println("Salario: " + resultado.getFloat(5));
            System.out.println("Departamento: " + resultado.getInt(6));
        }

//        Liberamos recursos
        sentencia.close();
        resultado.close();
    }

    //=======================================================================

}
