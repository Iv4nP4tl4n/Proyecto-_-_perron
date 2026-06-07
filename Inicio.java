import java.awt.*;
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

    public Inicio() {
        setTitle("Inicio");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(turquesaClaro);

        
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(turquesa);
        sidebar.setPreferredSize(new Dimension(200, 600));

        String[][] items = {
            {"Iconos/home.png",              "INICIO"},
            {"Iconos/shopping-cart.png",     "VENTAS"},
            {"Iconos/cube.png",              "PRODUCTOS"},
            {"Iconos/users-alt.png",         "PROVEEDORES"},
            {"Iconos/truck-side.png",        "PEDIDOS"},
            {"Iconos/book-alt.png",          "REPORTES"},
            {"Iconos/users.png",             "USUARIOS"},
            {"Iconos/search-alt.png",        "TICKETS"},
            {"Iconos/sign-out-alt (1).png",  "REGRESAR"}
        };

        for (String[] item : items) {
            String ruta      = item[0];
            String texto     = item[1];
            boolean esActivo = texto.equals("INICIO");

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
                    if (texto.equals("REGRESAR")) {
                        new inicio_sesion();  // Regresa al login
                        dispose();            // Cierra el dashboard
                    }
                }
            });

            sidebar.add(fila);
        }

        // ===================== PANEL PRINCIPAL =====================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título INVENTARIO
        JLabel titulo = new JLabel("INVENTARIO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // Tarjetas — guardamos referencia al JLabel del valor
        JPanel tarjetas = new JPanel(new GridLayout(1, 4, 10, 0));
        tarjetas.setBackground(turquesaClaro);
        tarjetas.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        ventasDia  = new JLabel("-");
        pedidos    = new JLabel("-");
        productos  = new JLabel("-");
        proveedores= new JLabel("-");

        tarjetas.add(crearTarjeta("Ventas del día",     ventasDia));
        tarjetas.add(crearTarjeta("Pedidos pendientes", pedidos));
        tarjetas.add(crearTarjeta("Total productos",    productos));
        tarjetas.add(crearTarjeta("Total proveedores",  proveedores));

        // Tabla vacía
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel tituloTabla = new JLabel("Ventas Recientes");
        tituloTabla.setFont(new Font("Arial", Font.BOLD, 15));
        tituloTabla.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        String[] columnas = {"Fecha", "Producto", "Cantidad", "Total"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setForeground(new Color(50, 50, 50));
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setBackground(Color.WHITE);
        tabla.setSelectionBackground(turquesaClaro);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        panelTabla.add(tituloTabla, BorderLayout.NORTH);
        panelTabla.add(scroll, BorderLayout.CENTER);

        // Ensamblar
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
    }

    // ===================== MÉTODOS PARA LA BD =====================

    public void setMetricas(String vDia, String ped, String prod, String prov) {
        ventasDia.setText(vDia);
        pedidos.setText(ped);
        productos.setText(prod);
        proveedores.setText(prov);
    }

    public void agregarVenta(String fecha, String producto,
                String cantidad, String total) {
        modeloTabla.addRow(new Object[]{fecha, producto, cantidad, total});
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    // ===================== TARJETA =====================
    private JPanel crearTarjeta(String etiqueta, JLabel valorLabel) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));

        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(new Color(100, 100, 100));

        valorLabel.setFont(new Font("Arial", Font.BOLD, 22));
        valorLabel.setForeground(new Color(30, 30, 30));

        tarjeta.add(lbl, BorderLayout.NORTH);
        tarjeta.add(valorLabel, BorderLayout.CENTER);
        return tarjeta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Inicio::new);
    }
}