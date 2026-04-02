import java.awt.*;
import javax.swing.*;

public class Inicio {

    JFrame ventana;
    JTextField usuario;
    JPasswordField contraseña;
    JButton boton;

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

        // Botón
        gbc.gridx = 1;
        gbc.gridy = 2;
        boton = new JButton("Entrar");
        boton.setPreferredSize(new Dimension(150, 45));
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        formulario.add(boton, gbc);

        panel.add(formulario);
        ventana.add(panel);

        // Evento
        boton.addActionListener(e -> validarLogin());

        ventana.setVisible(true);
    }

    // 🔥 AQUÍ ESTÁ LA MAGIA
    public void validarLogin() {

        String user = usuario.getText();
        String pass = new String(contraseña.getPassword());

        if (user.equals("ivan") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Bienvenido");

            ventana.dispose(); // 🔥 cierra esta ventana
            new Indice();      // 🔥 abre la otra (tu menú)

        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public static void main(String[] args) {
        new Inicio();
    }
}