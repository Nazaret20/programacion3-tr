import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre;
    private double telefono;
    
    public Contacto(String nombre, double telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Telefono: " + telefono;
    }    
}
