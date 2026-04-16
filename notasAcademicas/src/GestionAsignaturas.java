import java.util.ArrayList;

public class GestionAsignaturas {
    private ArrayList<Asignatura> listaAsignturas;

    public GestionAsignaturas() {
        this.listaAsignturas = new ArrayList<>();
    }

    public void anadirAsig(String nomAsignatura, String curso) {
        for (Asignatura asignatura : listaAsignturas) {
            if (nomAsignatura.equals(asignatura.getAsignatura())) {
                System.out.println("Esta asignatura ya existe.");
            } else {
                Asignatura a = new Asignatura(nomAsignatura, curso);
                listaAsignturas.add(a);
                System.out.println("Asignatura añadida.");
            }
        }
    }

    public void elimiAsig(String nomAsignatura) {
        for (Asignatura asignatura : listaAsignturas) {
            if (nomAsignatura.equals(asignatura.getAsignatura())) {
                listaAsignturas.remove(asignatura);
                System.out.println("Asignatura eliminada.");
            } else {
                System.out.println("Error al eliminar asignatura.");
            }
        }
    }

    public void mostrarAsig() {
        for (Asignatura asignatura : listaAsignturas) {
            System.out.println(asignatura.toString());
        }
    }

    @Override
    public String toString() {
        return "Lista de asignaturas:\n" + listaAsignturas;
    }

    public ArrayList<Asignatura> getListaAsignturas() {
        return listaAsignturas;
    }

    public void setListaAsignturas(ArrayList<Asignatura> listaAsignturas) {
        this.listaAsignturas = listaAsignturas;
    }

    
}
