import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TxtToHtml {
    public static void main(String[] args) throws Exception {
        String linea;

       try (
         BufferedReader lector = new BufferedReader(new FileReader("input.txt"));
         BufferedWriter escritor = new BufferedWriter(new FileWriter("output.html") );
       ) {
            String cabeceraHtml = "<!DOCTYPE html>\r\n" + //
                                "<html>\r\n" + //
                                "<head>\r\n" + //
                                "    <title>Conversi&oacute;n de input.txt</title>\r\n" + //
                                "</head>\r\n" + //
                                "<body>\r\n";
            String pieHtml = "</body>\r\n" + //
                                "</html>";
            escritor.write(cabeceraHtml);

            while ((linea = lector.readLine()) != null) {
                escritor.write("<p>");                
                String[] palabras = linea.split(" ");
                for(String palabra : palabras) {
                    if(palabra.toLowerCase().startsWith("s"))
                        escritor.write("<b>" + palabra + "</b> ");
                    else
                        escritor.write(palabra + " ");
                }
                escritor.write("</p>\n");                
            }

            escritor.write(pieHtml);
   
        
       } catch (Exception e) {
        // TODO: handle exception
       }
        
    }
}