import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Desarrolla un programa que lea un archivo de texto largo y lo divida en
         * archivos con un máximo de 20 líneas cada uno.
         */

        
        try (FileReader fr = new FileReader("src/archivo.txt");
            BufferedReader br = new BufferedReader(fr)) {
            String linea = br.readLine();

            int contador = 0;
            int numFichero = 1;
            BufferedWriter bw = null;

            while (linea != null) {
                if (contador % 20 == 0) {
                    if (bw != null) {
                        bw.close();
                    }
                    String nombreFichero = "fichero_" + numFichero + ".txt";
                    File fichero = new File(nombreFichero);
                    FileWriter fw = new FileWriter(fichero);
                    bw = new BufferedWriter(fw);
                    numFichero++;
                }
                bw.write(linea);
                bw.newLine();

                contador++;
                linea = br.readLine();
            }

            if (bw != null) {
                bw.close();
            }
            System.out.println("Proceso acabado");
        } catch (Exception e) {
            System.out.println("No se pudo completar " + e.getMessage());
        }
    }
}
