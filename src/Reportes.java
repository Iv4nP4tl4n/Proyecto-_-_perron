import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Reportes extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private JLabel lblVentas, lblTickets, lblProductos, lblPromedio;

    private DefaultTableModel modeloVentas;
    private DefaultTableModel modeloProductos;
    private DefaultTableModel modeloPago;

    public Reportes() {

        setTitle("Reportes");
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
            boolean activo = texto.equals("REPORTES");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setBackground(activo ? turquesaOscuro : turquesa);

            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // ICONO
            JLabel icono = new JLabel();
            ImageIcon img = new ImageIcon(ruta);
            icono.setIcon(new ImageIcon(
                    img.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)
            ));

            // TEXTO
            JLabel lbl = new JLabel(texto);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));

            fila.add(icono);
            fila.add(lbl);

            fila.addMouseListener(new java.awt.event.MouseAdapter() {
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

        // ================= PRINCIPAL =================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);

        JLabel titulo = new JLabel("REPORTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        principal.add(titulo, BorderLayout.NORTH);

        // ================= TARJETAS =================
        JPanel tarjetas = new JPanel(new GridLayout(1, 4, 10, 10));
        tarjetas.setBackground(turquesaClaro);

        lblVentas = new JLabel("0");
        lblTickets = new JLabel("0");
        lblProductos = new JLabel("0");
        lblPromedio = new JLabel("0");

        tarjetas.add(crearCard("Ventas", lblVentas));
        tarjetas.add(crearCard("Tickets", lblTickets));
        tarjetas.add(crearCard("Productos", lblProductos));
        tarjetas.add(crearCard("Promedio", lblPromedio));

        // ================= TABLAS =================
        JPanel panelTablas = new JPanel(new GridLayout(1, 3, 10, 10));
        panelTablas.setBackground(turquesaClaro);

        modeloVentas = new DefaultTableModel(new String[]{"Fecha", "Ventas", "Total"}, 0);
        JTable t1 = new JTable(modeloVentas);
        panelTablas.add(new JScrollPane(t1));

        modeloProductos = new DefaultTableModel(new String[]{"Producto", "Cantidad"}, 0);
        JTable t2 = new JTable(modeloProductos);
        panelTablas.add(new JScrollPane(t2));

        modeloPago = new DefaultTableModel(new String[]{"Método", "Total"}, 0);
        JTable t3 = new JTable(modeloPago);
        panelTablas.add(new JScrollPane(t3));

        // ================= ARMAR =================
        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setBackground(turquesaClaro);
        centro.add(tarjetas, BorderLayout.NORTH);
        centro.add(panelTablas, BorderLayout.CENTER);

        principal.add(centro, BorderLayout.CENTER);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);

   

        setVisible(true);
    }

    // ================= CARD =================
    private JPanel crearCard(String titulo, JLabel valor) {

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Arial", Font.BOLD, 12));

        valor.setFont(new Font("Arial", Font.BOLD, 18));

        p.add(t, BorderLayout.NORTH);
        p.add(valor, BorderLayout.CENTER);

        return p;
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Reportes::new);
    }
}