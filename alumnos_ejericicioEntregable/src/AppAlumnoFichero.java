import java.util.Scanner;

public class AppAlumnoFichero {
    private GestorDatos datos;
    private Scanner sc;

    AppAlumnoFichero() {
        datos = new GestorDatos();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        AppAlumnoFichero app = new AppAlumnoFichero();
        app.mostrarMenu();
    }

    private void cls() {
        System.out.println("\\033[H\\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar.]");
        sc.nextLine();
    }

    private void mostrarMenu() {
        int opc;

        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("1.- Gestionar alumnos \r\n" + //
                    "2.- Gestionar asignaturas \r\n" + //
                    "3.- Gestionar notas \r\n" + //
                    "4.- Guardar y cargar datos del sistema\r\n" + //
                    "0.- Salir");
            System.out.println("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    menuAlumnos();
                    break;
                case 2:
                    menuAsignatura();
                    break;
                case 3:
                    menuNotas();
                    break;
                case 4:
                    menuDatos();
                    break;
                case 0:
                    System.out.println("Gracias por usar el programa.");
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);

    }

    // Gestion alumnos
    private void menuAlumnos() {
        int opc;
        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("---- Menú Alumnos ----\n");
            System.out.println("1.- Alta alumnos \r\n" + //
                    "2.- Baja alumnos \r\n" + //
                    "3.- Listar alumnos \r\n" + //
                    "0.- volver");
            System.out.println("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    altaAlumnos();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    listaNotas();
                    break;
                case 0:
                    System.out.println("Gracias por usar el programa.");
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);

    }   
        private void altaAlumnos(){
            
        }

        private void bajaAlumno(){

        }

        private void listaNotas(){

        }

    // Gestion asignatura

    private void menuAsignatura() {
        int opc;
        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("---- Menú Alumnos ----\n");
            System.out.println("1.- Alta alumnos \r\n" + //
                    "2.- Baja alumnos \r\n" + //
                    "3.- Listar alumnos \r\n" + //
                    "0.- volver");
            System.out.println("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    altaAlumnos();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    listaNotas();
                    break;
                case 0:
                    System.out.println("Gracias por usar el programa.");
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);


    }

    // Gestion notas
    private void menuNotas() {

    }

    // gestion datos
    private void menuDatos() {

    }
}

