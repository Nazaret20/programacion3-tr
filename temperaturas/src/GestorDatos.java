import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorDatos {
    private final String ARCHIVO = "temp.bin";
    private final int REGISTRO_SIZE = Long.BYTES + Double.BYTES * 2;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void anadir(LocalDate fecha, double tMax, double tMin) {
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "rw")) {
            raf.seek(raf.length());
            raf.writeLong(fecha.toEpochDay());
            raf.writeDouble(tMax);
            raf.writeDouble(tMin);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public void modificarDatos(LocalDate fecha, double tMax, double tMin) {
        boolean encontrado = false;
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "rw")) {
            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (fecha.toEpochDay() == raf.readLong()) {
                    raf.writeDouble(tMax);
                    raf.writeDouble(tMin);
                    encontrado = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (encontrado) {
            System.out.println("Actualizado correctamente");
        } else {
            System.out.println("Fecha no encontrada");
        }
    }

    public void consultarDatos(LocalDate fecha) {
         boolean encontrado = false;
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "r")) {
            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (fecha.toEpochDay() == raf.readLong()) {
                    System.out.printf("%s [%.2f,%.2f]", fecha.format(formato), raf.readDouble(), raf.readDouble());
                    encontrado = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (!encontrado) {
            System.out.println("Registro no encontrado");
        } 
    }

    public double[] calcularMedia(int mes) {
        double sumaMax = 0;
        double sumaMin = 0;
        int numTemps = 0;

        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "r")) {
            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (mes == LocalDate.ofEpochDay(raf.readLong()).getMonthValue()) {
                    sumaMax += raf.readDouble();
                    sumaMax += raf.readDouble();
                    numTemps++;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (numTemps > 0) {
            double[] medias = new double[2];
            medias[0] = sumaMax / numTemps;
            medias[1] = sumaMin / numTemps;
            return medias;
        } else {
            return null;

        }

        
    }

}
