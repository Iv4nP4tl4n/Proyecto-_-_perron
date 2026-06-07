import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Productos extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;

    public Productos() {
        setTitle("Productos");
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
            {"Iconos/home.png",             "INICIO"},
            {"Iconos/shopping-cart.png",    "VENTAS"},
            {"Iconos/cube.png",             "PRODUCTOS"},
            {"Iconos/users-alt.png",        "PROVEEDORES"},
            {"Iconos/truck-side.png",       "PEDIDOS"},
            {"Iconos/book-alt.png",         "REPORTES"},
            {"Iconos/users.png",            "USUARIOS"},
            {"Iconos/search-alt.png",       "TICKETS"},
            {"Iconos/sign-out-alt (1).png", "REGRESAR"}
        };

        for (String[] item : items) {
            String ruta      = item[0];
            String texto     = item[1];
            boolean esActivo = texto.equals("PRODUCTOS");

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
                        case "INICIO"    -> { new Inicio();        dispose(); }
                        case "VENTAS"    -> { new ventas();        dispose(); }
                        case "REGRESAR"  -> { new inicio_sesion(); dispose(); }
                    }
                }
            });

            sidebar.add(fila);
        }

        // ===================== PANEL PRINCIPAL =====================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("PRODUCTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // ===== TABLA =====
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));

        String[] columnas = {
            "ID_PRODUCTOS", "NOMBRE", "CATEGORÍA",
            "PRECIO", "STOCK", "STOCK MIN", "ESTADO", "Editar", "Eliminar"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tabla.getTableHeader().setBackground(new Color(240, 240, 240));
        tabla.getTableHeader().setForeground(new Color(50, 50, 50));
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setBackground(Color.WHITE);
        tabla.setSelectionBackground(turquesaClaro);

        // Columnas de íconos más angostas
        tabla.getColumnModel().getColumn(7).setMaxWidth(40);
        tabla.getColumnModel().getColumn(8).setMaxWidth(40);

        // Renderer para íconos de editar y eliminar
        tabla.getColumnModel().getColumn(7).setCellRenderer((t, val, sel, foc, row, col) -> {
            JLabel lbl = new JLabel();
            ImageIcon imgEdit = new ImageIcon("Iconos/edit.png");
            lbl.setIcon(new ImageIcon(
                    imgEdit.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(sel ? turquesaClaro : Color.WHITE);
            lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return lbl;
        });

        tabla.getColumnModel().getColumn(8).setCellRenderer((t, val, sel, foc, row, col) -> {
            JLabel lbl = new JLabel();
            ImageIcon imgDel = new ImageIcon("Iconos/trash.png");
            lbl.setIcon(new ImageIcon(
                    imgDel.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(sel ? turquesaClaro : Color.WHITE);
            lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return lbl;
        });

        // Clic en íconos editar / eliminar
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int col  = tabla.columnAtPoint(e.getPoint());
                if (fila < 0) return;
                if (col == 7) editarProducto(fila);
                if (col == 8) eliminarProducto(fila);
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        panelTabla.add(scroll, BorderLayout.CENTER);

        // ===== BOTÓN AGREGAR =====
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(turquesaClaro);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(turquesa);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 13));
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setPreferredSize(new Dimension(130, 36));
        // btnAgregar.addActionListener(e -> abrirFormularioAgregar()); // conectar con BD

        panelBoton.add(btnAgregar);

        // ===== ENSAMBLAR =====
        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(turquesaClaro);
        centro.add(panelTabla, BorderLayout.CENTER);
        centro.add(panelBoton, BorderLayout.SOUTH);

        principal.add(panelTitulo, BorderLayout.NORTH);
        principal.add(centro,      BorderLayout.CENTER);

        fondo.add(sidebar,   BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);
        setVisible(true);
    }

    // ===================== MÉTODOS PARA LA BD =====================

    // Agrega una fila a la tabla
    public void agregarProducto(String id, String nombre, String categoria,
                                String precio, String stock,
                                String stockMin, String estado) {
        modeloTabla.addRow(new Object[]{
            id, nombre, categoria, precio, stock, stockMin, estado, "", ""
        });
    }

    // Limpia la tabla (para recargar desde BD)
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    // Placeholder — conectar con BD
    private void editarProducto(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();
        JOptionPane.showMessageDialog(this,
                "Editar producto: " + id,
                "Editar", JOptionPane.INFORMATION_MESSAGE);
    }

    // Placeholder — conectar con BD
    private void eliminarProducto(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar producto " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(fila);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Productos::new);
    }
}
