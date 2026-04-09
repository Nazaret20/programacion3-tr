import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Escribe un programa que liste todos los archivos y directorios de un
         * directorio dado por el usuario.
         * Por ejemplo, al darle la ruta de este proyecto mostrará:
         * 
         * Los archivos dentro de .
         * + .VSCODE
         * - settings.json
         * + BIN
         * - listadoficheros.class
         * + LIB
         * - readme.md
         * + SRC
         * - listadoficheros.java
         */

        String directorio = "./listarFicheros";

        // System.out.print("Introduce un directorio: ");

        File file = new File(directorio);

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("Existe");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("Error");
        }

        
    }

    public static void recorrerDirectorio(File file) {
        String[] archivos = file.listFiles();
    }
}
