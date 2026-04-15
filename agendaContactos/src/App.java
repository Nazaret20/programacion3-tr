import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opc;
        // try (ObjectInputStream ois = new ObjectInputStream(
        //         new FileInputStream("persona.dat"))) {

        //     Contacto c = (Contacto) ois.readObject();
        //     System.out.println(c.getNombre() + " - " + c.getTelefono());

        // } catch (IOException | ClassNotFoundException e) {
        //     e.printStackTrace();
        // }

        do {
            opc = menu(sc);
            switch (opc) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }
        } while (opc != 0);
    }

    public static int menu(Scanner sc) {
        System.out.println("1. Añadir contacto\r\n" + //
                        "2. Buscar contacto\r\n" + //
                        "3. Listar contactos\r\n" + //
                        "4. Eliminar contacto\r\n" + //
                        "5. Guardar libreta\r\n" + //
                        "6.Cargar libreta\r\n" + //
                        "7. Salir");
        return Integer.parseInt(sc.nextLine());
    }
}
