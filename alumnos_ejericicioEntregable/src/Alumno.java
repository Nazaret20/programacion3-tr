import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Alumno implements Serializable{
    private String nombre, curso;
    private HashMap<Asignatura, Double> notas;

    public Alumno(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
        this.notas = new HashMap<Asignatura, Double>();
    }

    /*----------------------------- */
    public static void anadirNotas(double nota, Asignatura asignatura, HashMap<Asignatura, Double> notas) {

        if (!notas.containsKey(asignatura)) {
            notas.put(asignatura, nota);
        } else {
            System.out.println("La nota ya existe en esa asignatura.");
        }
    }

    public static void modificarNotas(double nota, Asignatura asignatura, HashMap<Asignatura, Double> notas) {

        if (notas.containsKey(asignatura)) {
            notas.put(asignatura, nota);
        } else {
            System.out.println("No se ha podido modificar la nota.");
        }
    }

    public static void eliminarNotas(Asignatura asignatura, HashMap<Asignatura, Double> notas) {

        if (notas.containsKey(asignatura)) {
            notas.remove(asignatura);
        } else {
            System.out.println("La asignatura no existe.");
        }
    }

    /*----------------------------- */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre);
        sb.append(" (" + curso + ")\n");
        for (Map.Entry<Asignatura, Double> nota : notas.entrySet()) {
            sb.append("\t" + nota.getKey().getNombre() + ": ");
            sb.append(nota.getValue() + "\n");
        }

        return sb.toString();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public HashMap<Asignatura, Double> getNotas() {
        return notas;
    }

    public void setNotas(HashMap<Asignatura, Double> notas) {
        this.notas = notas;
    }

}
