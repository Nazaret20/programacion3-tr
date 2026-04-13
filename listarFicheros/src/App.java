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

        String rutaDada = ".";

        // System.out.print("Introduce un directorio: ");

        recorrerDirectorio(rutaDada);
        
    }

    public static void recorrerDirectorio(String ruta) {
        File fichero = new File(ruta);

        if (fichero.isDirectory() && fichero.exists()) {
            File[] archivos = fichero.listFiles();

            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("+ " + archivo.getName().toUpperCase());
                    recorrerDirectorio(archivo.getPath());
                } else {
                    System.out.println("\t - " + archivo.getName().toLowerCase());
                }
            }
            
        } else {
            System.out.println("Error, inválido");
        }

        
    }
}
