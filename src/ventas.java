import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ventas extends JFrame {

    Color turquesa        = new Color(0, 190, 185);
    Color turquesaClaro   = new Color(188, 237, 234);
    Color turquesaObscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;
    private JComboBox<String> comboPago;
    private JTextArea txtObservaciones;
    private JLabel lblSubtotal;
    private JLabel lblIVA;
    private JLabel lblTotal;

    public ventas() {

        setTitle("Ventas");
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
            boolean esActivo = texto.equals("VENTAS");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(esActivo ? turquesaObscuro : turquesa);

            JLabel icono = new JLabel(new ImageIcon(
                    new ImageIcon(ruta).getImage()
                            .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

            JLabel lbl = new JLabel(texto);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));

            fila.add(icono);
            fila.add(lbl);

            Color bgNormal = esActivo ? turquesaObscuro : turquesa;

            fila.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    fila.setBackground(turquesaObscuro);
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

        JLabel titulo = new JLabel("Nueva venta");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // ===================== BUSCAR =====================
        JPanel panelBuscar = new JPanel(new BorderLayout());
        panelBuscar.setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> buscarProducto());

        panelBuscar.add(txtBuscar, BorderLayout.CENTER);
        panelBuscar.add(btnBuscar, BorderLayout.EAST);

        // ===================== TABLA =====================
        String[] columnas = {"Producto", "Precio", "Cantidad", "Subtotal", "Eliminar"};

        modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);

        // ===================== TOTALES =====================
        lblSubtotal = new JLabel("-");
        lblIVA = new JLabel("-");
        lblTotal = new JLabel("-");

        JPanel totales = new JPanel(new GridLayout(3, 2));
        totales.add(new JLabel("Subtotal"));
        totales.add(lblSubtotal);
        totales.add(new JLabel("IVA"));
        totales.add(lblIVA);
        totales.add(new JLabel("Total"));
        totales.add(lblTotal);

        // ===================== ARMAR =====================
        principal.add(panelTitulo, BorderLayout.NORTH);
        principal.add(panelBuscar, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);
        principal.add(totales, BorderLayout.SOUTH);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);

        setVisible(true);
    }

    // ===================== CONEXIÓN =====================
    private Connection getConexion() throws Exception {
        return ConexionDB.getConexion();
    }

    // ===================== BUSCAR PRODUCTO =====================
    private void buscarProducto() {

        String nombre = txtBuscar.getText();

        try (Connection con = getConexion()) {

            String sql = "SELECT nombre, precio_venta FROM producto WHERE nombre LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String prod = rs.getString(1);
                double precio = rs.getDouble(2);

                agregarProducto(prod, precio, 1, precio);
                calcularTotales();

            } else {
                JOptionPane.showMessageDialog(this, "No encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===================== AGREGAR =====================
    public void agregarProducto(String nombre, double precio, int cantidad, double subtotal) {
        modeloTabla.addRow(new Object[]{
                nombre,
                precio,
                cantidad,
                subtotal,
                "X"
        });
    }

    // ===================== TOTALES =====================
    private void calcularTotales() {

        double subtotal = 0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += Double.parseDouble(modeloTabla.getValueAt(i, 3).toString());
        }

        double iva = subtotal * 0.16;
        double total = subtotal + iva;

        lblSubtotal.setText(String.valueOf(subtotal));
        lblIVA.setText(String.valueOf(iva));
        lblTotal.setText(String.valueOf(total));
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ventas::new);
    }
}