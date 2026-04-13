import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        /*Desarrolla un programa que cree un archivo llamado "miArchivo.txt" y luego lo elimine. El programa debe informar en cada paso si la operación fue exitosa o no. */


        File archivo = new File("miArchivo.txt");
        File rutaArchivo = new File("C:\\Users\\Nazaret\\Documents\\programacion\\vscode\\3ºTr\\crearYBorrarFichero");

        if (archivo.exists()) {
            System.out.println("El archivo se ha creado");
        } else {
            System.out.println("Error al crear archivo");
        }

        System.out.println(archivo);

       if (archivo.exists()) {
            archivo.delete();
            System.out.println("El archivo se eliminó correctamente");
       } else {
            System.out.println("Error al eliminar el archivo");
       }
    }
}
