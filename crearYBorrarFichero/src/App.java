import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Desarrolla un programa que cree un archivo llamado "miArchivo.txt" y luego lo
         * elimine. El programa debe informar en cada paso si la operación fue exitosa o
         * no.
         */
        Scanner sc = new Scanner(System.in);
        File archivo = new File("miArchivo.txt");
        
        
            System.out.println("Pulsa intro para crear");
            sc.nextLine();

            if (archivo.createNewFile()) {
                System.out.println("El archivo se ha creado");
            } else {
                System.out.println("Error al crear archivo");
            }

            System.out.println("Pulsa intro para borrar");
            sc.nextLine();

            if (archivo.delete()) {
                System.out.println("El archivo se eliminó correctamente");
            } else {
                System.out.println("Error al eliminar el archivo");
            }
      
        
        sc.close();
    }
}
