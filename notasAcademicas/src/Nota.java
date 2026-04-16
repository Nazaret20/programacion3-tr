public class Nota {
    private Alumno alumno;
    private Asignatura asignatura;
    private double nota;
    
    public Nota(Alumno alumno, Asignatura asignatura, double nota) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "El alumno " + alumno + " en " + asignatura + " tiene de nota " + nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10");
        }
        this.nota = nota;
    }

    
}
