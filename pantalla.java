
import java.awt.*;
import javax.swing.*;

public class pantalla {

    JFrame ventana;

    public pantalla(){

        ventana = new JFrame("Pantalla");
        ventana.setSize(1400, 900);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        // 🔥 CARGAR Y ESCALAR IMAGEN (más chica)
        ImageIcon fondoOriginal = new ImageIcon(getClass().getResource("/Logo de proyecto + perron.png"));
        Image imagen = fondoOriginal.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        ImageIcon fondoImg = new ImageIcon(imagen);

        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(new GridBagLayout()); // para posicionar

        // CONFIGURACIÓN GRID
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // 🔘 BOTÓN ENTRAR
        JButton entrar = new JButton("Entrar");
        entrar.setPreferredSize(new Dimension(200, 50));
        entrar.setFont(new Font("Arial", Font.BOLD, 18));
        entrar.setFocusPainted(false);

        // POSICIÓN: ABAJO
        gbc.gridy = 1; // debajo de la imagen
        gbc.insets = new Insets(200, 0, 50, 0); // empuja hacia abajo

        fondo.add(entrar, gbc);

        // EVENTO
        entrar.addActionListener(e -> {
            ventana.dispose();
            new Inicio(); // o new Indice();
        });

        ventana.setContentPane(fondo);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new pantalla();
    } 
}