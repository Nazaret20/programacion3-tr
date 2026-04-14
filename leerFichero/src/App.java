import java.io.*;


public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Desarrollar un programa que lea y muestre por pantalla todo el contenido de
         * un archivo de texto.
         */

        File archivo = new File("texto.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";

       

        while (linea != null) {
            linea = br.readLine();
            System.out.println(linea);
            if (linea != null) {
                System.out.println(linea);
            }
        }

        br.close();
    }
}
