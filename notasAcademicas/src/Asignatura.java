public class Asignatura {
    private String asignatura, curso;

    public Asignatura(String asignatura, String curso) {
        this.asignatura = asignatura;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return asignatura + " (" + curso + ")";
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    
}
