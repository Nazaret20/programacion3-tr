package com.prog;

import java.util.Scanner;

public class AppEmpleados {
    private Scanner sc;
    private GestorDatos datos;
    
    AppEmpleados() {
        this.sc = new Scanner(System.in);
        this.datos = new GestorDatos();
    }

    // private void cls() {
    //     System.out.print("\033[H\033[2J");
    // }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    /*------------------------------------------- */
    public static void main(String[] args) {
        AppEmpleados app = new AppEmpleados();
        app.menu();
        app.sc.close();
    }
    /*------------------------------------------- */

    private void menu() {
        int opc;

        do {
            System.out.println("\n1. Añadir nuevos empleados (nombre, apellido y salario)\r\n" + //
                                "2. Eliminar empleados existentes\r\n" + //
                                "3. Modificar datos de empleados\r\n" + //
                                "4. Listar todos los empleados registrados\r\n" + //
                                "5. Salir del programa");
            System.out.print("\tSeleccione opción: ");
             opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: addEmpleado(); break;
                case 2: rmvEmpleado(); break;
                case 3: updateEmpleado(); break;
                case 4: showEmpleados(); break;
                case 5: System.err.println("Finalizado correctamente."); esperaIntro(); break;
                default: System.out.println("Opción no válida."); esperaIntro(); break;
            }
        } while (opc != 5);
    }

    private void addEmpleado() {
        System.out.println("\n--- Añadir empleado ---");
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Introduce salario: ");
        double salario = Double.parseDouble(sc.nextLine());

        datos.addEmpleado(nombre, apellido, salario);;
    }

    private void rmvEmpleado() {
        System.out.println("\n--- Eliminar empleado ---");
        System.out.print("Introdude nombre: ");
        String nombre = sc.nextLine();

        datos.rmvEmpleado(nombre);
    }

    private void updateEmpleado() {
        System.out.println("\n--- Modificar empleado ---");
        System.out.print("Empleado a modificar: ");
        String nombre = sc.nextLine();
        
        System.out.print("Dato a modificar (nombre, apellido, salario): ");
        String columna = sc.nextLine();

        Object datoAModificar = null;

        if (columna.equals("nombre")) {
            System.out.print("Introduce nombre: ");
            datoAModificar = sc.nextLine();
        } else if (columna.equals("apellido")) {
            System.out.print("Introduce apellido: ");
            datoAModificar = sc.nextLine();
        } else {
            System.out.print("Introduce salario: ");
            datoAModificar = Double.parseDouble(sc.nextLine());
        }

        datos.updateEmpleado(nombre, columna, datoAModificar);
    }   

    private void showEmpleados() {

    }
}