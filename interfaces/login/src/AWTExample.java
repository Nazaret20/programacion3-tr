
import java.awt.*;
import java.awt.event.*;

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
        String placeholder = "Nazaret";
        txtfNombre.setText(placeholder);
        txtfNombre.setPreferredSize(new Dimension(200, 20));

        frame.add(lblContrasena);
        frame.add(txtfContrasena);
        txtfContrasena.setEchoChar('*');
        String placeholderC = "******";
        txtfContrasena.setText(placeholderC);
        txtfContrasena.setPreferredSize(new Dimension(200, 20));

        frame.add(lblEmail);
        frame.add(txtfEmail);
        String placeholderE = "naza@gmail.com";
        txtfEmail.setText(placeholderE);
        txtfEmail.setPreferredSize(new Dimension(200, 20));

        frame.add(button);

        frame.setSize(320, 160);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }
}
