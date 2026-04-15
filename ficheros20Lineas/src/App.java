import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Desarrolla un programa que lea un archivo de texto largo y lo divida en
         * archivos con un máximo de 20 líneas cada uno.
         */
        int contador = 1;
        int numFichero = 1;
        String linea;

        try (BufferedReader lector = new BufferedReader(new FileReader(new File("src/archivo.txt")))) {

            String nombreFichero = "fichero_" + numFichero + ".txt";

            PrintWriter escritor = new PrintWriter(nombreFichero);

            while ((linea = lector.readLine()) != null) {
                escritor.println(linea);
                contador++;

                if (contador > 20) {
                    escritor.close();
                    numFichero++;

                    nombreFichero = "fichero_" + numFichero + ".txt";
                    escritor = new PrintWriter(nombreFichero);
                    contador = 1;
                }
            }
            escritor.close();

            System.out.println("Proceso acabado");
        } catch (Exception e) {
            System.out.println("No se pudo completar " + e.getMessage());
        }
    }
}
