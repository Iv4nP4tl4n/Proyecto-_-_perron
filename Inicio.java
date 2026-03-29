import javax.swing.*;

public class Inicio {

    JFrame ventana;
    JTextField usuario;
    JPasswordField contraseña;
    JButton boton;

    public Inicio() {

        ventana = new JFrame("Login");
        ventana.setSize(400, 300);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usuario
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(50, 50, 100, 30);
        ventana.add(lblUser);

        usuario = new JTextField();
        usuario.setBounds(150, 50, 150, 30);
        ventana.add(usuario);

        // Contraseña
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(50, 100, 100, 30);
        ventana.add(lblPass);

        contraseña = new JPasswordField();
        contraseña.setBounds(150, 100, 150, 30);
        ventana.add(contraseña);

        // Botón
        boton = new JButton("Entrar");
        boton.setBounds(130, 160, 120, 40);
        ventana.add(boton);

        // Evento (AQUÍ ESTÁ LA LÓGICA)
        boton.addActionListener(e -> validarLogin());

        ventana.setVisible(true);
    }

    // MÉTODO PARA VALIDAR
    public void validarLogin() {

        String user = usuario.getText();
        String pass = new String(contraseña.getPassword());

        if (user.equals("admin") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public static void main(String[] args) {
        new Inicio();
    }
}