
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
            System.out.print("\tSeleccione una opción");
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

    // -----------------Gestion alumnos----------------
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
            System.out.print("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    altaAlumnos();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    listaAlumnos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);

    }

    private void altaAlumnos() {
        System.out.print("Nombre del alumno: ");
        String nombre = sc.nextLine();

        System.out.print("Curso al que pertenecerá: ");
        String curso = sc.nextLine();

        Alumno a = new Alumno(nombre, curso);
        datos.getAlumnos().add(a);
    }

    private void bajaAlumno() {
        System.out.print("Nombre del alumno: ");
        String nombre = sc.nextLine();

        for (Alumno a : datos.getAlumnos()) {
            if (nombre.equalsIgnoreCase(a.getNombre())) {
                datos.getAlumnos().remove(a);
            }
        }
    }

    private void listaAlumnos() {
        for (Alumno a : datos.getAlumnos()) {
            System.out.println(a);
        }

    }

    // ----------------Gestion asignatura----------------

    private void menuAsignatura() {
        int opc;
        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("---- Menú Asignaturas ----\n");
            System.out.println("1.- Nueva asignatura \r\n" + //
                    "2.- Eliminar asigntaura \r\n" + //
                    "3.- Listar asignaturas \r\n" + //
                    "0.- volver");
            System.out.print("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    nuevaAsigntura();
                    break;
                case 2:
                    elimiAsigntura();
                    break;
                case 3:
                    listaAsignturas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);

    }

    private void nuevaAsigntura() {
        System.out.print("Nombre de la asignatura: ");
        String nombre = sc.nextLine();

        Asignatura a = new Asignatura(nombre);
        datos.getAsignaturas().add(a);
    }

    private void elimiAsigntura() {
        System.out.print("Nombre de la asignatura: ");
        String nombre = sc.nextLine();

        for (Asignatura a : datos.getAsignaturas()) {
            if (nombre.equalsIgnoreCase(a.getNombre())) {
                datos.getAsignaturas().remove(a);
            }
        }
    }

    private void listaAsignturas() {
        for (Asignatura a : datos.getAsignaturas()) {
            System.out.println(a);
        }
    }

    // ----------------Gestion notas----------------
    private void menuNotas() {
        int opc;
        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("---- Menú Notas ----\n");
            System.out.println("1.- Asignar nota a alumno \r\n" + //
                    "2.- Eliminar notas de alumno \r\n" + //
                    "3.- Listar alumno y sus notas \r\n" + //
                    "0.- volver");
            System.out.print("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    asignarNotaAAlumno();
                    break;
                case 2:
                    elimiNotaDeAlumno();
                    break;
                case 3:
                    listaAlumnoYNotas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);
    }

    private void asignarNotaAAlumno() {
        System.out.print("Alumno a añadir la nota: ");
        String nombre = sc.nextLine();

        System.out.print("Asignatura: ");
        String asignatura = sc.nextLine();

        Asignatura asignaturaEncontrada = null;
        for (Asignatura a : datos.getAsignaturas()) {
            if (a.getNombre().equalsIgnoreCase(asignatura)) {
                asignaturaEncontrada = a;
                break;
            }
        }

        if (asignaturaEncontrada == null) {
            System.out.println("Error: La asignatura no existe.");
            return; // Cortamos el método aquí
        }

        System.out.print("Nota: ");
        double nota = Double.parseDouble(sc.nextLine());

        boolean alumnoEncontrado = false;
        for (Alumno a : datos.getAlumnos()) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                a.getNotas().put(asignaturaEncontrada, nota);
                alumnoEncontrado = true;
                System.out.println("Nota registrada correctamente.");
                break;
            }
        }

        if (!alumnoEncontrado) {
            System.out.println("Error: El alumno no existe.");
        }
    }

    private void elimiNotaDeAlumno() {
        System.out.print("Alumno a eliminar la nota: ");
        String nombre = sc.nextLine();

        System.out.print("Asignatura: ");
        String asignatura = sc.nextLine();

        Asignatura asigAEliminar = null;
        for (Asignatura a : datos.getAsignaturas()) {
            if (a.getNombre().equalsIgnoreCase(asignatura)) {
                asigAEliminar = a;
                break;
            }
        }

        if (asigAEliminar == null) {
            System.out.println("Error: La asignatura no existe.");
            return;
        }

        boolean encontrado = false;
        for (Alumno a : datos.getAlumnos()) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;

                // 3. Intentamos eliminar del Map
                // remove() devuelve el valor eliminado o null si no existía
                Double notaEliminada = a.getNotas().remove(asigAEliminar);

                if (notaEliminada != null) {
                    System.out.println("Nota de " + asignatura + " eliminada correctamente.");
                } else {
                    System.out.println("El alumno no tenía nota en esa asignatura.");
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Error: El alumno no existe.");
        }
    }

    private void listaAlumnoYNotas() {
        for (Alumno a : datos.getAlumnos()) {
            System.out.println(a.toString());
        }
    }

    // ----------------Gestion datos----------------
    private void menuDatos() {
         int opc;
        do {
            cls();
            System.out.println("---- Gestión de Alumnos ----\n");
            System.out.println("---- Menú Datos ----\n");
            System.out.println("1.- Guardar datos\r\n" + //
                    "2.- Cargar datos");
            System.out.print("\tSeleccione una opción");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    guardarDatos();
                    break;
                case 2:
                    cargarDatos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción invalida.");
                    esperaIntro();
                    break;
            }
        } while (opc != 0);
    }

    private void guardarDatos() {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("boletin.bin"))) {

        oos.writeObject(datos.getAlumnos());
        
        System.out.println("\nLibreta guardada correctamente.");
    } catch (IOException e) {
        System.err.println("Error al guardar: " + e.getMessage());
    }

    System.out.println("\n(Pulse intro para continuar)");
    sc.nextLine();
}

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("boletin.bin"))) {
        
        ArrayList<Alumno> listaCargada = (ArrayList<Alumno>) ois.readObject();
        
        datos.setAlumnos(listaCargada);
        
        System.out.println("Datos cargados: " + listaCargada.size() + " alumnos encontrados.");

    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error al cargar los datos: " + e.getMessage());
    }
    }
}
