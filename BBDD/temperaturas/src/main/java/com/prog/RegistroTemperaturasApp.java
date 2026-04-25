package com.prog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegistroTemperaturasApp {
    private Scanner sc;
    private GestorDatos datos;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    RegistroTemperaturasApp() {
        this.sc = new Scanner(System.in);
        this.datos = new GestorDatos();
    }

    public static void main(String[] args) throws Exception {
        RegistroTemperaturasApp app = new RegistroTemperaturasApp();
        app.menu();
        app.sc.close();
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    private void menu() {
        int opc;

        do {
            cls();
            System.out.println("===== Registro de Temperaturas =====\n");
            System.out.println("1.- Añadir nuevo registro\r\n" + //
                    "2.- Modificar temperatura de una fecha\r\n" + //
                    "3.- Consultar temperaturas por fecha\r\n" + //
                    "4.- Calcular promedio de temperaturas por mes\r\n" + //
                    "0.- Salir");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    addRegistro();
                    esperaIntro();
                    break;
                case 2:
                    updateRegistro();
                    esperaIntro();
                    break;
                case 3:
                    showRegistro();
                    esperaIntro();
                    break;
                case 4:
                    calcularPromedio();
                    esperaIntro();
                    break;
                case 0:
                    System.out.println("Finalizado correctamente.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    esperaIntro();
                    break;
            }

        } while (opc != 0);
    }

    private void addRegistro() {
        cls();
        System.out.println("--- Añadir Registro ---");
        System.out.print("Dame la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);
        System.out.print("Dame la temperatura máxima: ");
        double tMax = Double.parseDouble(sc.nextLine());
        System.out.print("Dame la temperatura mínima: ");
        double tMin = Double.parseDouble(sc.nextLine());

        datos.addRegistro(fecha, tMax, tMin);
    }

    private void updateRegistro() {
        cls();
        System.out.println("--- Modificar Registro ---");
        System.out.print("Dame la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);
        System.out.print("Dame la nueva temperatura máxima: ");
        double tMax = Double.parseDouble(sc.nextLine());
        System.out.print("Dame la nueva temperatura mínima: ");
        double tMin = Double.parseDouble(sc.nextLine());

        datos.updateRegistro(fecha, tMax, tMin);
    }

    private void showRegistro() {
        cls();
        System.out.println("--- Consultar Registro ---");
        System.out.print("Dame la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);

        datos.showRegistro(fecha);
    }

    private void calcularPromedio() {
        cls();
        System.out.println("--- Calcular Promedio ---");
        System.out.print("Dame el mes (en número): ");
        int mes = Integer.parseInt(sc.nextLine());

        double[] promedios = datos.calcularPromedio(mes);

        if (promedios != null) {
            System.out.printf("Los promedios son: [%.2f, %.2f]",
                    promedios[0],
                    promedios[1]);
        }
        else
            System.out.println("No se encontraron datos");

    }
}