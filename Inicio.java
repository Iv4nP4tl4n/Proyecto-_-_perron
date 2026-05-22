import java.awt.*;
import javax.swing.*;

public class Inicio {

    JFrame ventana;
    JTextField usuario;
    JPasswordField contraseña;
    JButton confirmar;
    JButton regresar;

    public Inicio() {

        ventana = new JFrame("Inicio");
        ventana.setSize(1300, 900);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        // Panel principal centrado
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // FORMULARIO
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 18));
        formulario.add(lblUser, gbc);

        gbc.gridx = 1;
        usuario = new JTextField(15);
        usuario.setPreferredSize(new Dimension(300, 45));
        usuario.setFont(new Font("Arial", Font.PLAIN, 18));
        formulario.add(usuario, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        formulario.add(lblPass, gbc);

        gbc.gridx = 1;
        contraseña = new JPasswordField(15);
        contraseña.setPreferredSize(new Dimension(300, 45));
        contraseña.setFont(new Font("Arial", Font.PLAIN, 18));
        formulario.add(contraseña, gbc);

        // 🔥 Panel para botones (mejor diseño)
        JPanel panelBotones = new JPanel();

        confirmar = new JButton("Entrar");
        confirmar.setPreferredSize(new Dimension(150, 45));
        confirmar.setFont(new Font("Arial", Font.BOLD, 16));

        regresar = new JButton("Regresar");
        regresar.setPreferredSize(new Dimension(150,45));
        regresar.setFont(new Font("Arial", Font.BOLD, 16));

        panelBotones.add(regresar);
        panelBotones.add(confirmar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formulario.add(panelBotones, gbc);

        panel.add(formulario);
        ventana.add(panel);

        // Evento Entrar
        confirmar.addActionListener(e -> validarLogin());

        // Evento Regresar 
        regresar.addActionListener(e -> {
            ventana.dispose();
            new pantalla();
        });

        ventana.setVisible(true);
    }

    // Validación
    public void validarLogin() {

        String user = usuario.getText();
        String pass = new String(contraseña.getPassword());

        if (user.equals("ivan") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Bienvenido");

            ventana.dispose();
            new Indice_Adm();

        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public static void main(String[] args) {
        new Inicio();
    }
}