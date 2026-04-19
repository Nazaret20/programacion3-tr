import java.io.IOException;
import java.io.RandomAccessFile;

public class RegistroTemperatura {
    private RandomAccessFile accion;
    private int TAMANIO_REGISTRO = 28;

    public RegistroTemperatura() throws IOException {
        this.accion = new RandomAccessFile("temperaturas.dat", "rw");
    }

    public void anadir(Temperatura t) {
       
    }

    public void consultarT(int dia, int mes, int anio) {
        
    }
}
