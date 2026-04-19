import java.util.Scanner;

public class GestorTemperaturasApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        menu(sc);
        sc.close();
    }

    public static void anadirReg(Scanner sc) {
        System.out.print("Día: ");
        int dia = Integer.parseInt(sc.nextLine());

        System.out.print("Mes: ");
        int mes = Integer.parseInt(sc.nextLine());

        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());

        System.out.print("Temperatura máxima: ");
        double tempMax = Double.parseDouble(sc.nextLine());

        System.out.print("Temperatura mínima: ");
        double tempMin = Double.parseDouble(sc.nextLine());

        Temperatura t = new Temperatura(dia, mes, anio, tempMax, tempMin);
    }

    public static void modificarT() {

    }

    public static void consultarT() {

    }

    public static void calcMedia() {

    }

    public static void menu(Scanner sc) {
        int opc;

        do {
            System.out.println("\n1. Nuevo registro\r\n" + //
                    "2. Modificar temperatura de una fecha\r\n" + //
                    "3. Consultar temperaturas por fecha\r\n" + //
                    "4. Calcular promedio de temperaturas por mes\r\n" + //
                    "0. Salir");
            System.out.print("\tIntroduce una opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                        anadirReg(sc);
                    break;

                case 2:
                        modificarT();
                    break;

                case 3:
                        verT();
                    break;

                case 4:
                        media();
                    break;

                case 0:
                    System.out.println("¡Hasta la próxima!");
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opc != 0);

    }
}
