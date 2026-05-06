
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class AWTExample {
    public static void main(String[] args) {
        Frame frame = new Frame("Formulario de Registro");
        Label lblNombre = new Label("Nombre");
        Label lblContrasena = new Label("Contraseña");
        Label lblEmail = new Label("Email");
        TextField txtfNombre = new TextField();
        TextField txtfContrasena = new TextField();
        TextField txtfEmail = new TextField();
        Button button = new Button("Validar");

        frame.setLayout(new FlowLayout(FlowLayout.RIGHT));

        frame.add(lblNombre);
        frame.add(txtfNombre);
        txtfNombre.setPreferredSize(new Dimension(200, 20));
        boolean nombre = esNombre(txtfNombre.getText());


        frame.add(lblContrasena);
        frame.add(txtfContrasena);
        txtfContrasena.setEchoChar('*');
        txtfContrasena.setPreferredSize(new Dimension(200, 20));
        boolean contrasena = esContrasena(txtfContrasena.getText());
        

        frame.add(lblEmail);
        frame.add(txtfEmail);
        txtfEmail.setPreferredSize(new Dimension(200, 20));
        boolean email = esEmail(txtfEmail.getText());

        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos el texto actual de los cuadros
                String sNombre = txtfNombre.getText();
                String sPass = txtfContrasena.getText();
                String sEmail = txtfEmail.getText();

                // Validamos usando tus métodos estáticos
                boolean nombreValido = esNombre(sNombre);
                boolean passValida = esContrasena(sPass);
                boolean emailValido = esEmail(sEmail);

                System.out.println("--- Intento de Registro ---");
                
                if (nombreValido && passValida && emailValido) {
                    System.out.println("ESTADO: Todo correcto. Enviando datos...");
                } else {
                    System.out.println("ESTADO: Error en los siguientes campos:");
                    if (!nombreValido) System.out.println("- El nombre debe ser 'Nombre Apellido' (Mayúsculas iniciales).");
                    if (!passValida)   System.out.println("- Contraseña débil (mín. 8 caracteres, Mayús, Minús y Número).");
                    if (!emailValido)  System.out.println("- Formato de Email no válido.");
                }
                System.out.println("---------------------------");
            }
        });

        frame.setSize(320, 160);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }

    public static boolean esNombre(String nombre) {
        return nombre.matches("([A-Z][a-z]{2,} ){1,}[A-Z][a-z]{2,}");
    }
    
    public static boolean esEmail(String email) {
        return email.toLowerCase().matches("[a-z][a-z0-9._-]{5,32}@[a-z][a-z0-9._-]{2,32}\\.[a-z]{2,32}");
    }

    public static boolean esContrasena(String contrasena) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        
        return Pattern.compile(regex)
                      .matcher(contrasena)
                      .matches();
    }
}
