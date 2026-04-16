import java.util.ArrayList;

public class GestionNotas {
    private GestionAlumnos gestionAlumno;
    private GestionAsignaturas gestionAsignatura;
    private ArrayList<Nota> listaNotas;

    public GestionNotas(GestionAlumnos gestionAlumno, GestionAsignaturas gestionAsignatura) {
        this.gestionAlumno = gestionAlumno;
        this.gestionAsignatura = gestionAsignatura;
        this.listaNotas = new ArrayList<>();
    }

    public void asignarNota(String nombreAlum, double notaAlum) {
        for (Nota nota : listaNotas) {
            
        }
    }

    public Alumno buscarAlum(String nombreAlum) {
        for (Nota nota : listaNotas) {
            
        }
    }

    public void alimiNotas() {

    }

    @Override
    public String toString() {
        return "GestionNotas [gestionAlumno=" + gestionAlumno + ", gestionAsignatura=" + gestionAsignatura + ", listaNotas=" + listaNotas + "]";
    }

    public GestionAlumnos getGestionAlumno() {
        return gestionAlumno;
    }

    public void setGestionAlumno(GestionAlumnos gestionAlumno) {
        this.gestionAlumno = gestionAlumno;
    }

    public GestionAsignaturas getGestionAsignatura() {
        return gestionAsignatura;
    }

    public void setGestionAsignatura(GestionAsignaturas gestionAsignatura) {
        this.gestionAsignatura = gestionAsignatura;
    }
}
