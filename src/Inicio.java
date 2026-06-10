import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inicio extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private JLabel ventasDia;
    private JLabel pedidos;
    private JLabel productos;
    private JLabel proveedores;

    private DefaultTableModel modeloTabla;

    private String usuarioLogueado;
    private String rol;

    // ===================== CONSTRUCTOR PRINCIPAL =====================
    public Inicio(String usuario, String rol) {

        this.usuarioLogueado = usuario;
        this.rol = rol;

        setTitle("Inicio");
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

            String texto = item[1];
            boolean esActivo = texto.equals("INICIO");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(esActivo ? turquesaOscuro : turquesa);

            JLabel icono = new JLabel(new ImageIcon(
                    new ImageIcon(item[0]).getImage()
                            .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

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
                        case "INICIO" -> { new Inicio(usuarioLogueado, rol); dispose(); }
                        case "VENTAS" -> { new ventas(); dispose(); }
                        case "PRODUCTOS" -> { new Productos(); dispose(); }
                        case "PROVEEDORES" -> { new Proveedores(); dispose(); }
                        case "PEDIDOS" -> { new Pedidos(); dispose(); }
                        case "REPORTES" -> { new Reportes(); dispose(); }
                        case "USUARIOS" -> { new Usuarios(); dispose(); }
                        case "TICKETS" -> { new Tickets(); dispose(); }
                        case "REGRESAR" -> {
                            new inicio_sesion();
                            dispose();
                        }
                    }
                }
            });

            sidebar.add(fila);
        }

        // ===================== PRINCIPAL =====================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HEADER USUARIO + ROL
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.setBackground(turquesaClaro);

        JLabel infoUser = new JLabel("Usuario: " + usuarioLogueado + " | Rol: " + rol);
        infoUser.setFont(new Font("Arial", Font.BOLD, 12));

        JButton logout = new JButton("Cerrar sesión");
        logout.addActionListener(e -> {
            new inicio_sesion();
            dispose();
        });

        header.add(infoUser);
        header.add(logout);

        // TITULO
        JLabel titulo = new JLabel("INVENTARIO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(header, BorderLayout.NORTH);
        panelTitulo.add(titulo, BorderLayout.CENTER);

        // ===================== TARJETAS =====================
        JPanel tarjetas = new JPanel(new GridLayout(1, 4, 10, 0));
        tarjetas.setBackground(turquesaClaro);

        ventasDia = new JLabel("-");
        pedidos = new JLabel("-");
        productos = new JLabel("-");
        proveedores = new JLabel("-");

        tarjetas.add(crearTarjeta("Ventas del día", ventasDia));
        tarjetas.add(crearTarjeta("Pedidos pendientes", pedidos));
        tarjetas.add(crearTarjeta("Total productos", productos));
        tarjetas.add(crearTarjeta("Total proveedores", proveedores));

        // ===================== TABLA =====================
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);

        String[] columnas = {"Fecha", "Producto", "Cantidad", "Total"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        panelTabla.add(scroll, BorderLayout.CENTER);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(turquesaClaro);
        centro.add(tarjetas, BorderLayout.NORTH);
        centro.add(panelTabla, BorderLayout.CENTER);

        principal.add(panelTitulo, BorderLayout.NORTH);
        principal.add(centro, BorderLayout.CENTER);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);

        setVisible(true);

        cargarDatos();
    }

    // constructor vacío (evita errores del sidebar)
    public Inicio() {
        this("Invitado", "Sin rol");
    }

    // ===================== BD =====================
    public static Connection getConexion() throws Exception {
        String url = "jdbc:mysql://localhost:3306/proyecto_perron?useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(url, "root", "Iv4nP4tl4n");
    }

    private void cargarDatos() {
        try (Connection con = getConexion()) {

            ventasDia.setText(getCount(con, "SELECT COUNT(*) FROM venta WHERE fecha = CURDATE()"));
            pedidos.setText(getCount(con, "SELECT COUNT(*) FROM pedido WHERE estado='pendiente'"));
            productos.setText(getCount(con, "SELECT COUNT(*) FROM producto"));
            proveedores.setText(getCount(con, "SELECT COUNT(*) FROM proveedor"));

            modeloTabla.setRowCount(0);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT v.fecha, p.nombre, dv.cantidad, dv.importe " +
                    "FROM detalle_venta dv " +
                    "INNER JOIN venta v ON dv.id_venta = v.id_venta " +
                    "INNER JOIN producto p ON dv.id_producto = p.id_producto " +
                    "ORDER BY v.fecha DESC LIMIT 10");

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCount(Connection con, String sql) throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs.next() ? rs.getString(1) : "0";
    }

    private JPanel crearTarjeta(String t, JLabel v) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel l = new JLabel(t);
        v.setFont(new Font("Arial", Font.BOLD, 20));

        p.add(l, BorderLayout.NORTH);
        p.add(v, BorderLayout.CENTER);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inicio("Admin", "gerente"));
    }
}