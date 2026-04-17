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
}


