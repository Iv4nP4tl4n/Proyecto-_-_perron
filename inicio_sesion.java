import java.awt.*;
import javax.swing.*;

public class inicio_sesion extends JFrame {

    public inicio_sesion() {

        setTitle("Inicio de sesión");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contenedor = new JPanel(new GridLayout(1, 2));

        // PANEL IZQUIERDO
        JPanel izquierda = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(188, 237, 234));
                g2.fillOval(-80, 180, 580, 480);
            }
        };

        izquierda.setLayout(null);
        izquierda.setBackground(Color.WHITE);

        JLabel logo = new JLabel();
        ImageIcon imgLogo = new ImageIcon("Iconos/Logo+ nombre.png");
        logo.setIcon(new ImageIcon(
                imgLogo.getImage().getScaledInstance(240, 190, Image.SCALE_SMOOTH)));
        logo.setBounds(50, 20, 240, 190);

        JLabel perro = new JLabel();
        ImageIcon imgPerro = new ImageIcon("Iconos/firulais.png");
        perro.setIcon(new ImageIcon(
                imgPerro.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH)));
        perro.setBounds(110, 270, 230, 230);

        izquierda.add(logo);
        izquierda.add(perro);

        // PANEL DERECHO
        JPanel derecha = new JPanel();
        derecha.setLayout(null);
        derecha.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(30, 30, 30));
        titulo.setBounds(60, 70, 300, 45);

        JLabel subtitulo = new JLabel("Inicia sesión para continuar");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(120, 120, 120));
        subtitulo.setBounds(60, 118, 260, 20);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
        lblUsuario.setBounds(60, 165, 100, 20);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(60, 188, 300, 38);
        txtUsuario.setBackground(new Color(220, 245, 250));
        txtUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setFont(new Font("Arial", Font.PLAIN, 13));
        lblContra.setBounds(60, 248, 120, 20);

        JPanel panelContra = new JPanel(null);
        panelContra.setBounds(60, 271, 300, 38);
        panelContra.setBackground(new Color(220, 245, 250));

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(0, 0, 262, 38);
        txtContra.setBackground(new Color(220, 245, 250));
        txtContra.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        txtContra.setFont(new Font("Arial", Font.PLAIN, 13));
        txtContra.setEchoChar('●');

        ImageIcon imgEye = new ImageIcon("Iconos/eye.png");
        JButton btnOjo = new JButton(
                new ImageIcon(imgEye.getImage()
                        .getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnOjo.setBounds(264, 7, 28, 24);
        btnOjo.setBackground(new Color(220, 245, 250));
        btnOjo.setBorderPainted(false);
        btnOjo.setFocusPainted(false);
        btnOjo.setContentAreaFilled(false);
        btnOjo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnOjo.addActionListener(e -> {
            if (txtContra.getEchoChar() == '●') {
                txtContra.setEchoChar((char) 0);
            } else {
                txtContra.setEchoChar('●');
            }
        });

        panelContra.add(txtContra);
        panelContra.add(btnOjo);

        // Botón Iniciar sesión — abre Inicio y cierra login
        JButton iniciar = new JButton("Iniciar sesión");
        iniciar.setBounds(60, 355, 300, 42);
        iniciar.setBackground(new Color(0, 190, 185));
        iniciar.setForeground(Color.WHITE);
        iniciar.setFont(new Font("Arial", Font.BOLD, 14));
        iniciar.setBorderPainted(false);
        iniciar.setFocusPainted(false);
        iniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iniciar.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtContra.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor completa todos los campos.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                new Inicio();   // Abre el dashboard
                dispose();      // Cierra el login
            }
        });

        ImageIcon imgSalir = new ImageIcon("Iconos/sign-out-alt.png");
        Image imgSalirEscalada = imgSalir.getImage()
                .getScaledInstance(18, 18, Image.SCALE_SMOOTH);

        JButton salir = new JButton("  Salir",
                new ImageIcon(imgSalirEscalada));
        salir.setBounds(60, 435, 120, 35);
        salir.setBackground(Color.WHITE);
        salir.setBorderPainted(false);
        salir.setFocusPainted(false);
        salir.setContentAreaFilled(false);
        salir.setForeground(new Color(50, 50, 50));
        salir.setFont(new Font("Arial", Font.PLAIN, 13));
        salir.setHorizontalAlignment(SwingConstants.LEFT);
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir.addActionListener(e -> System.exit(0));

        derecha.add(titulo);
        derecha.add(subtitulo);
        derecha.add(lblUsuario);
        derecha.add(txtUsuario);
        derecha.add(lblContra);
        derecha.add(panelContra);
        derecha.add(iniciar);
        derecha.add(salir);

        contenedor.add(izquierda);
        contenedor.add(derecha);
        add(contenedor);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(inicio_sesion::new);
    }
}