import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Crear un programa que escriba una línea de texto proporcionada por el usuario
         * en un archivo.
         */

        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe un texto: ");
        String frase = sc.nextLine();

        File archivo = new File("miarchivo.txt");
        FileWriter fw = new FileWriter(archivo, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(frase);

        pw.close();
        sc.close();
    }
}
