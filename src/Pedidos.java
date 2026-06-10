import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Pedidos extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private TableRowSorter<DefaultTableModel> sorter;

    public Pedidos() {

        setTitle("Pedidos");
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
        // ================= PANEL PRINCIPAL =================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);

        JLabel titulo = new JLabel("PEDIDOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));

        principal.add(titulo, BorderLayout.NORTH);

        // ================= TABLA =================
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Proveedor", "Fecha", "Total", "Estado", "Entrega"}, 0
        );

        tabla = new JTable(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(tabla);
        principal.add(scroll, BorderLayout.CENTER);

        // ================= BOTÓN =================
        JButton btnAgregar = new JButton("AGREGAR PEDIDO");
        btnAgregar.setBackground(turquesaOscuro);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 13));
        btnAgregar.setFocusPainted(false);

        btnAgregar.addActionListener(e -> insertarPedido());

        JPanel bottom = new JPanel();
        bottom.setBackground(turquesaClaro);
        bottom.add(btnAgregar);

        principal.add(bottom, BorderLayout.SOUTH);

        // ================= ENSAMBLE =================
        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);

        setVisible(true);

        cargarPedidos();
    }

    // ================= BD =================

    private Connection getConexion() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proyecto_perron",
                "root",
                "Iv4nP4tl4n"
        );
    }

    public void cargarPedidos() {

        modeloTabla.setRowCount(0);

        String sql = "SELECT * FROM pedido";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getString("id_pedido"),
                        rs.getString("proveedor"),
                        rs.getString("fecha"),
                        rs.getString("total"),
                        rs.getString("estado"),
                        rs.getString("fecha_entrega")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error cargando pedidos:\n" + e.getMessage());
        }
    }

    public void insertarPedido() {

        String proveedor = JOptionPane.showInputDialog("Proveedor");
        String fecha = JOptionPane.showInputDialog("Fecha");
        String total = JOptionPane.showInputDialog("Total");
        String estado = JOptionPane.showInputDialog("Estado");
        String entrega = JOptionPane.showInputDialog("Entrega");

        String sql = "INSERT INTO pedido(proveedor, fecha, total, estado, fecha_entrega) VALUES (?,?,?,?,?)";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor);
            ps.setString(2, fecha);
            ps.setString(3, total);
            ps.setString(4, estado);
            ps.setString(5, entrega);

            ps.executeUpdate();

            cargarPedidos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error insertando:\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pedidos::new);
    }
}