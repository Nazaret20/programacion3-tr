import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class GestorDatos {

    private ArrayList<Alumno> alumnos;
    private ArrayList<Asignatura> asignaturas;

    //constructor
    public GestorDatos() {
        this.alumnos = new ArrayList<Alumno>();
        this.asignaturas = new ArrayList<Asignatura>();
    }

    // getters and setter
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("boletin.bin"));) {

            oos.writeObject(alumnos);
            oos.writeObject(asignaturas);
            System.out.println("\nLibreta guardada correctamente.");

        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }

      
    }

    public void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("boletin.bin"));) {
            alumnos.clear();
            alumnos = (ArrayList<Alumno>) ois.readObject();

            asignaturas.clear();
            asignaturas = (ArrayList<Asignatura>) ois.readObject();
    

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }

    }
    
    /*--------------Asignatura--------------- */

    public void nuevaAsigntura(String nombre) {
        asignaturas.add(new Asignatura(nombre));
    }

    public void eliminarAsigntura(String nombre) {
        asignaturas.remove(new Asignatura(nombre));
    }

    public void listAsignatura() {
        System.out.println("Lista de asignaturas:\n");
        for (Asignatura a : asignaturas) {
            System.out.println(a);
        }
    }
    /*--------------Alumno--------------- */
    public void nuevoAlumno(String nombre, String curso) {
        alumnos.add(new Alumno(nombre, curso));
    }

    public void eliminarAlumno(String nombre) {
        Alumno alumEliminar = null;
        for (Alumno a : alumnos) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                alumEliminar = a;
            }
        }
        if (alumEliminar != null) {
            alumnos.remove(alumEliminar);
        }
    }

    /*--------------Notas--------------- */
    public void asignarNotaAAlumno() {

    }

    public void elimiNotaDeAlumno() {

    }

    public void listaAlumnoYNotas() {
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }

}