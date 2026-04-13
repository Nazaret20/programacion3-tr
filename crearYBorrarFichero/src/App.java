import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Desarrolla un programa que cree un archivo llamado "miArchivo.txt" y luego lo
         * elimine. El programa debe informar en cada paso si la operación fue exitosa o
         * no.
         */

        
        try {
            File archivo = new File("crearYBorrarFichero/miArchivo.txt");

            if (archivo.createNewFile()) {
                System.out.println("El archivo se ha creado");
            } else {
                System.out.println("Error al crear archivo");
            }

            System.out.println(archivo.getName());
            recorrerDirectorio(archivo.getPath());

            if (archivo.delete()) {
                System.out.println("El archivo se eliminó correctamente");
            } else {
                System.out.println("Error al eliminar el archivo");
            }
        } catch (Exception e) {
           System.out.println("Ha ocurrido un fallo");
        }

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
