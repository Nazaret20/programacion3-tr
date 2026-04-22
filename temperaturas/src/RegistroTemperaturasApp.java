import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegistroTemperaturasApp {
    private Scanner sc;
    private GestorDatos datos;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    
    public RegistroTemperaturasApp() {
        this.sc = new Scanner(System.in);
        this.datos = new GestorDatos();
    }

    private void cls() {
        System.out.println("\\033[H\\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar.]");
        sc.nextLine();
    }

    public static void main(String[] args) throws Exception {
        RegistroTemperaturasApp app = new RegistroTemperaturasApp();
        app.menu();
        app.sc.close();
        
    }

    private void menu() {
        int opc;

        do {
            cls();
            System.out.println("--- Registro de temperatura ---");
            System.out.println("\n1. Nuevo registro\r\n" + //
                    "2. Modificar temperatura de una fecha\r\n" + //
                    "3. Consultar temperaturas por fecha\r\n" + //
                    "4. Calcular promedio de temperaturas por mes\r\n" + //
                    "0. Salir");
            System.out.print("\tIntroduce una opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: anadirReg(); esperaIntro(); break;
                case 2: modificarT(); esperaIntro(); break;
                case 3: consultarT(); esperaIntro(); break;
                case 4: calcMedia(); esperaIntro(); break;
                case 0:
                    System.out.println("¡Hasta la próxima!"); break;
                default:
                    System.out.println("Opción inválida"); esperaIntro(); break;
            }
        } while (opc != 0);

    }

    private void anadirReg() {
        cls();
        System.out.println("--- Añadir registro ---");
        System.out.print("Fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);

        System.out.print("Temperatura máxima: ");
        double tempMax = Double.parseDouble(sc.nextLine());

        System.out.print("Temperatura mínima: ");
        double tempMin = Double.parseDouble(sc.nextLine());

        datos.anadir(fecha, tempMax, tempMin);
    }

    private void modificarT() {
        cls();
        System.out.println("--- Modificar registro ---");
        System.out.print("Fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);

        System.out.print("Temperatura máxima nueva: ");
        double tempMax = Double.parseDouble(sc.nextLine());

        System.out.print("Temperatura mínima nueva: ");
        double tempMin = Double.parseDouble(sc.nextLine());

        datos.modificarDatos(fecha, tempMax, tempMin);;
    }

    private void consultarT() {
        cls();
        System.out.println("--- Consultar registro ---");
        System.out.print("Fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);
        datos.consultarDatos(fecha);
    }

    private void calcMedia() {
        cls();
        System.out.println("--- Calcular media ---");
        System.out.print("Mes (en número): ");
        int mes = Integer.parseInt(sc.nextLine());

        double[] media = datos.calcularMedia(mes);

        if (media != null) {
            System.out.printf("La media es: [%.2f,%.2f]", media[0], media[1]);

        } else {
            System.out.println("No hay datos");
        }
    }
}
