import java.util.ArrayList;

public class GestionAlumnos {
    private ArrayList<Alumno> listaAlum;

    public GestionAlumnos() {
        this.listaAlum = new ArrayList<>();
    }

    public void anadirAlum(String nombre, String curso) {
        
        for (Alumno alumno : listaAlum) {
            if (nombre.equals(alumno.getNombre())) {
                System.out.println("Este alumno ya existe.");
            } else {
                Alumno a = new Alumno(nombre, curso);
                listaAlum.add(a);
                System.out.println("Alumno añadido.");
            }
        }
    }

    public void elimiAlum(String nombre) {
        
        for (Alumno alumno : listaAlum) {
            if (nombre.equals(alumno.getNombre())) {
                listaAlum.remove(alumno);
                System.out.println("Alumno eliminado.");
            } else {
                System.out.println("No se pudo eliminar.");
            }
        }
    }

    public void mostrarAlum() {
        
        for (Alumno alumno : listaAlum) {
            System.out.println(alumno.toString());
        }
    }
    
    @Override
    public String toString() {
        return "Lista de alumnos:\n" + listaAlum;
    }

    public ArrayList<Alumno> getListaAlum() {
        return listaAlum;
    }

    public void setListaAlum(ArrayList<Alumno> listaAlum) {
        this.listaAlum = listaAlum;
    }
}
