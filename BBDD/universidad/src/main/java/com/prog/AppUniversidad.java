package com.prog;

import java.util.Scanner;

public class AppUniversidad {
    private Scanner sc;
    private Matricula matricula;

    public AppUniversidad() {
        this.sc = new Scanner(System.in);
        this.matricula = new Matricula();
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    /*-------------------------------- */
    public static void main(String[] args) {
        AppUniversidad app = new AppUniversidad();
        app.menu();
        app.sc.close();
    }
    /*-------------------------------- */

    private void menu() {
        cls();
        int opc;

        do {
            System.out.println("--- Sistema de Universidad ---");
            System.out.println("\n1. Matricular estudiantes en cursos\r\n" + //
                                "2. Registrar calificaciones\r\n" + //
                                "3. Calcular promedios por estudiante\r\n" + //
                                "4. Generar lista de estudiantes por curso\r\n" + //
                                "5. Mostrar historial académico completo");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: matricularEst(); esperaIntro(); break;
                case 2: registrarCalf(); esperaIntro(); break;
                case 3: promedios(); esperaIntro(); break;
                case 4: showEst(); esperaIntro(); break;
                case 5: historialAcad(); esperaIntro(); break;
                case 6: System.err.println("Finalizado correctamente."); esperaIntro(); break;
                default: System.out.println("Opción no válida."); esperaIntro(); break;
            }
        } while (opc != 6);
    }

    public void matricularEst() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        matricula.matricularEst(nombre);
    }

    public void registrarCalf() {

    }

    public void promedios() {

    }

    public void showEst() {
        System.out.println("Introduce la clave del curso: ");
        String claveCurso = sc.nextLine();
        matricula.showEst(claveCurso);

    }

    public void historialAcad() {

    }
}