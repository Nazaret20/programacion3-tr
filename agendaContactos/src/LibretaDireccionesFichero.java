import java.io.*;
import java.util.*;

public class LibretaDireccionesFichero {
    private ArrayList<Contacto> contactos;
    private Scanner sc;

    LibretaDireccionesFichero() {
        contactos = new ArrayList<Contacto>();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LibretaDireccionesFichero ldf = new LibretaDireccionesFichero();
        ldf.mostrarMenu();
        ldf.sc.close();
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    private void mostrarMenu() {
        int opcion;
        do {
            cls();
            System.out.println("---- Libreta de direcciones ----\n");
            System.out.println("1.- Añadir contacto\r\n" + //
                    "2.- Buscar contacto\r\n" + //
                    "3.- Listar contactos\r\n" + //
                    "4.- Eliminar contacto\r\n" + //
                    "5.- Guardar libreta\r\n" + //
                    "6.- Cargar libreta\r\n" + //
                    "7.- Salir");

            System.out.print("\tSeleccione opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    addContacto();
                    break;

                case 2:
                    findContacto();
                    break;

                case 3:
                    listContactos();
                    break;

                case 4:
                    deleteContacto();
                    break;

                case 5:
                    writeLibreta();
                    break;

                case 6:
                    readLibreta();
                    break;

                case 7:
                    System.out.println("\nGracias por usar la libreta.");
                    break;

                default:
                    System.out.println("\nOpción no válida.\n(Pulse intro para continuar)");
                    sc.nextLine();
                    break;
            }

        } while (opcion != 7);
    }

    private void addContacto() {
        cls();
        System.out.println("-- Añadir Contacto --\n");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Dime el teléfono: ");
        String telefono = sc.nextLine();
        contactos.add(new Contacto(nombre, telefono));
        System.out.println("\nCreado correctamente.\n(Pulse intro para continuar)");
        sc.nextLine();
    }

    private void findContacto() {
        cls();
        System.out.println("-- Buscar Contacto --\n");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                System.out.println("\nContacto encontrado: " + contacto);
                encontrado = true;
            }
        }
        if (!encontrado)
            System.out.println("\nContacto no encontrado.");

        System.out.println("\n(Pulse intro para continuar)");
        sc.nextLine();
    }

    private void listContactos() {
        cls();
        System.out.println("-- Lista de Contactos --\n");

        if (contactos.size() == 0)
            System.out.println("No hay contactos.");
        else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
        System.out.println("\n(Pulse intro para continuar)");
        sc.nextLine();
    }

    private void deleteContacto() {
        cls();
        System.out.println("-- Eliminar Contacto --\n");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;
        Contacto contEliminar = null;

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                System.out.println("\nContacto encontrado: " + contacto);
                encontrado = true;
                System.out.print("¿Quieres eliminarlo? (s/n): ");
                String eliminar = sc.nextLine();
                if (eliminar.equalsIgnoreCase("s"))
                    contEliminar = contacto;
            }
        }
        if (contEliminar != null) {
            contactos.remove(contEliminar);
            System.out.println("Eliminado correctamente");
        }
        if (!encontrado)
            System.out.println("\nContacto no encontrado.");

        System.out.println("\n(Pulse intro para continuar)");
        sc.nextLine();
    }

    private void writeLibreta() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File("libreta.bin")))) {

            for (Contacto contacto : contactos) {
                oos.writeObject(contacto);
            }
            System.out.println("\nLibreta guardada correctamente.");

            System.out.println("\n(Pulse intro para continuar)");
            sc.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readLibreta() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File("libreta.bin")))) {
            
            contactos.clear();
            Contacto contIn;
            while((contIn = (Contacto) ois.readObject()) != null){
                contactos.add(contIn);
            }
         

        } catch (EOFException eofe) {
           System.out.println("\nLibreta cargada correctamente.");

            System.out.println("\n(Pulse intro para continuar)");
            sc.nextLine();  
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}