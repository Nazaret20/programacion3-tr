
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LibretaDirecciones {
    public static ArrayList<Contacto> listaContactos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opc;
        // try (ObjectInputStream ois = new ObjectInputStream(
        // new FileInputStream("persona.dat"))) {

        // Contacto c = (Contacto) ois.readObject();
        // System.out.println(c.getNombre() + " - " + c.getTelefono());

        // } catch (IOException | ClassNotFoundException e) {
        // e.printStackTrace();
        // }

        do {
            opc = menu(sc);
            switch (opc) {
                case 1:
                    anadirContacto(sc);
                    break;

                case 2:
                    buscarContacto(sc);
                    break;

                case 3:
                    listarContacto();
                    break;

                case 4:
                    eliminarContacto(sc);
                    break;

                case 5:
                    guardarLibreta();
                    break;
                case 6:
                    cargarLibreta();
                    break;

                default:
                    System.out.println("Esta opción no es válida, inténtalo de nuevo.");
                    break;
            }
        } while (opc != 7);
    }

    public static void anadirContacto(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Teléfono: ");
        double tlf = Integer.parseInt(sc.nextLine());

        Contacto c = new Contacto(nombre, tlf);
        listaContactos.add(c);
    }

    public static void buscarContacto(Scanner sc) {
        System.out.print("Contacto a buscar: ");
        String nombre = sc.nextLine();

        for (Contacto contacto : listaContactos) {
            if (nombre.equals(contacto.getNombre())) {
                System.out.println(contacto);
            } else {
                System.out.println("El contacto no existe.");
            }
        }
    }

    public static void listarContacto() {
        for (Contacto contacto : listaContactos) {
            System.out.println(contacto);
        }
    }

    public static void eliminarContacto(Scanner sc) {
        System.out.print("Contacto a eliminar: ");
        String nombre = sc.nextLine();

        for (Contacto contacto : listaContactos) {
            if (nombre.equals(contacto.getNombre())) {
                listaContactos.remove(contacto);
                System.out.println("Contacto eliminado.");
            } else {
                System.out.println("Error al eliminar.");
            }
        }
    }

    public static void guardarLibreta() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libreta.bin", true))) {
            oos.writeObject(listaContactos);
            System.out.println("Archivo guardado. Tamaño de la lista: " + listaContactos.size());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void cargarLibreta() {
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("libreta.bin"))) {
            ArrayList<Contacto> listaCargada = (ArrayList<Contacto>) lector.readObject();

            for (Contacto contacto : listaCargada) {
                System.out.print(contacto.toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static int menu(Scanner sc) {
        System.out.println("\n1. Añadir contacto\r\n" + //
                "2. Buscar contacto\r\n" + //
                "3. Listar contactos\r\n" + //
                "4. Eliminar contacto\r\n" + //
                "5. Guardar libreta\r\n" + //
                "6.Cargar libreta\r\n" + //
                "7. Salir");
        System.out.print("\tIntroduce una opción: ");
        return Integer.parseInt(sc.nextLine());
    }
}
