import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Proveedores extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;

    public Proveedores() {

        setTitle("Proveedores");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(turquesaClaro);

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(turquesa);
        sidebar.setPreferredSize(new Dimension(200, 600));

        String[][] items = {
            {"Iconos/home.png", "INICIO"},
            {"Iconos/shopping-cart.png", "VENTAS"},
            {"Iconos/cube.png", "PRODUCTOS"},
            {"Iconos/users-alt.png", "PROVEEDORES"},
            {"Iconos/truck-side.png", "PEDIDOS"},
            {"Iconos/book-alt.png", "REPORTES"},
            {"Iconos/users.png", "USUARIOS"},
            {"Iconos/search-alt.png", "TICKETS"},
            {"Iconos/sign-out-alt (1).png", "REGRESAR"}
        };

        for (String[] item : items) {

            String ruta = item[0];
            String texto = item[1];

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(texto.equals("PROVEEDORES") ? turquesaOscuro : turquesa);

            JLabel icono = new JLabel(new ImageIcon(
                new ImageIcon(ruta).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)
            ));

            JLabel lbl = new JLabel(texto);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));

            fila.add(icono);
            fila.add(lbl);

            Color normal = texto.equals("PROVEEDORES") ? turquesaOscuro : turquesa;

            fila.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    fila.setBackground(turquesaOscuro);
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    fila.setBackground(normal);
                }

                public void mouseClicked(java.awt.event.MouseEvent e) {
                    switch (texto) {
                        case "INICIO" -> { new Inicio(); dispose(); }
                        case "VENTAS" -> { new ventas(); dispose(); }
                        case "PRODUCTOS" -> { new Productos(); dispose(); }
                        case "PROVEEDORES" -> { /* ya estás aquí */ }
                        case "PEDIDOS" -> { new Pedidos(); dispose(); }
                        case "REPORTES" -> { new Reportes(); dispose(); }
                        case "USUARIOS" -> { new Usuarios(); dispose(); }
                        case "TICKETS" -> { new Tickets(); dispose(); }
                        case "REGRESAR" -> { System.exit(0); }
                    }
                }
            });

            sidebar.add(fila);
        }

        // ================= PRINCIPAL =================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("PROVEEDORES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // ================= TABLA =================
        String[] columnas = {
            "ID", "NOMBRE", "TELÉFONO",
            "DIRECCIÓN", "PRODUCTO", "EDITAR", "ELIMINAR"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabla);

        // ================= BOTÓN =================
        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(turquesa);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAgregar.addActionListener(e -> abrirFormulario());

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(turquesaClaro);
        panelBtn.add(btnAgregar);

        // ================= ENSAMBLE =================
        principal.add(panelTitulo, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);
        principal.add(panelBtn, BorderLayout.SOUTH);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);
        setVisible(true);
    }

    // ================= FORM =================
    private void abrirFormulario() {

        JDialog d = new JDialog(this, "Agregar", true);
        d.setSize(350, 300);
        d.setLocationRelativeTo(this);
        d.setLayout(new GridLayout(5, 2));

        JTextField n = new JTextField();
        JTextField t = new JTextField();
        JTextField di = new JTextField();
        JTextField p = new JTextField();

        d.add(new JLabel("Nombre"));
        d.add(n);
        d.add(new JLabel("Teléfono"));
        d.add(t);
        d.add(new JLabel("Dirección"));
        d.add(di);
        d.add(new JLabel("Producto"));
        d.add(p);

        JButton g = new JButton("Guardar");

        g.addActionListener(e -> {
            modeloTabla.addRow(new Object[]{
                "1", n.getText(), t.getText(), di.getText(), p.getText(), "✏", "🗑"
            });
            d.dispose();
        });

        d.add(g);
        d.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Proveedores::new);
    }
}