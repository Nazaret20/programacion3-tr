
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

        frame.add(lblContrasena);
        frame.add(txtfContrasena);
        txtfContrasena.setEchoChar('*');
        txtfContrasena.setPreferredSize(new Dimension(200, 20));

        frame.add(lblEmail);
        frame.add(txtfEmail);
        txtfEmail.setPreferredSize(new Dimension(200, 20));

        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean esValido = true;

                if (txtfNombre.getText().isEmpty()) {
                    esValido = false;
                }

                if (txtfContrasena.getText().isEmpty()) {
                    esValido = false;
                }

                if (txtfEmail.getText().isEmpty()) {
                    esValido = false;
                }

                Dialog dialog = new Dialog(frame, "Resultado validación", true);
                Label msg;
                if (esValido) {
                    msg = new Label("Los datos para " + txtfNombre.getText() + " son válidos.", Label.CENTER);
                } else {
                    msg = new Label("Datos no válidos.", Label.CENTER);
                }

                dialog.add(msg);
                
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        dialog.dispose();
                    }
                });
                dialog.setSize(200, 200);
                dialog.setVisible(true);
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
