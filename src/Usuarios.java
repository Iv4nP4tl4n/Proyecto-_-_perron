import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Usuarios extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;

    public Usuarios() {
        setTitle("Usuarios");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(turquesaClaro);

        // ===================== SIDEBAR =====================
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
            boolean esActivo = texto.equals("USUARIOS");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(esActivo ? turquesaOscuro : turquesa);

            JLabel icono = new JLabel();
            ImageIcon img = new ImageIcon(ruta);
            icono.setIcon(new ImageIcon(
                    img.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

            JLabel lbl = new JLabel(texto);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));

            fila.add(icono);
            fila.add(lbl);

            Color bgNormal = esActivo ? turquesaOscuro : turquesa;

            fila.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    fila.setBackground(turquesaOscuro);
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    fila.setBackground(bgNormal);
                }

                public void mouseClicked(java.awt.event.MouseEvent e) {
                    switch (texto) {
                        case "INICIO" -> { new Inicio(); dispose(); }
                        case "VENTAS" -> { new ventas(); dispose(); }
                        case "PRODUCTOS" -> { new Productos(); dispose(); }
                        case "PROVEEDORES" -> { new Proveedores(); dispose(); }
                        case "PEDIDOS" -> { new Pedidos(); dispose(); }
                        case "REPORTES" -> { new Reportes(); dispose(); }
                        case "USUARIOS" -> { new Usuarios(); dispose(); }
                        case "TICKETS" -> { new Tickets(); dispose(); }
                        case "REGRESAR" -> { new inicio_sesion(); dispose(); }
                    }
                }
            });

            sidebar.add(fila);
        }

        // ===================== PRINCIPAL =====================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("USUARIOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // ===================== TABLA =====================
        String[] columnas = {
            "ID", "NOMBRE", "CORREO", "USUARIO", "ROL", "Editar", "Eliminar"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.setShowGrid(true);

        tabla.getColumnModel().getColumn(5).setMaxWidth(40);
        tabla.getColumnModel().getColumn(6).setMaxWidth(40);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int col = tabla.columnAtPoint(e.getPoint());

                if (fila < 0) return;
                if (col == 5) editarUsuario(fila);
                if (col == 6) eliminarUsuario(fila);
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);

        // ===================== BOTÓN =====================
        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(turquesa);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.addActionListener(e -> abrirFormularioAgregar());

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(turquesaClaro);
        panelBoton.add(btnAgregar);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(turquesaClaro);
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(panelBoton, BorderLayout.SOUTH);

        principal.add(panelTitulo, BorderLayout.NORTH);
        principal.add(centro, BorderLayout.CENTER);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);
        setVisible(true);
    }

    // ===================== FUNCIONES BD =====================
    public void agregarUsuario(String id, String nombre, String correo,
                               String usuario, String rol) {
        modeloTabla.addRow(new Object[]{id, nombre, correo, usuario, rol, "", ""});
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    // ===================== ACCIONES =====================
    private void editarUsuario(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();
        JOptionPane.showMessageDialog(this, "Editar usuario: " + id);
        // AQUÍ CONECTAS BD
    }

    private void eliminarUsuario(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();

        int op = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar usuario " + id + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(fila);
            // AQUÍ CONECTAS BD
        }
    }

    private void abrirFormularioAgregar() {
        JOptionPane.showMessageDialog(this,
                "Aquí conectas formulario a BD");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Usuarios::new);
    }
}