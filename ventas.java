import java.awt.*;
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
            boolean esActivo = texto.equals("VENTAS");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(esActivo ? turquesaObscuro : turquesa);

            JLabel icono = new JLabel();
            ImageIcon img = new ImageIcon(ruta);
            icono.setIcon(new ImageIcon(
                    img.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

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
                        case "INICIO"   -> { new Inicio();        dispose(); }
                        case "REGRESAR" -> { new inicio_sesion(); dispose(); }
                    }
                }
            });

            sidebar.add(fila);
        }

        // ===================== PANEL PRINCIPAL =====================
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(turquesaClaro);
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Nueva venta");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(turquesaClaro);
        panelTitulo.add(titulo);

        // ===== BUSCAR + MÉTODO DE PAGO =====
        JPanel panelSuperior = new JPanel(new BorderLayout(15, 0));
        panelSuperior.setBackground(turquesaClaro);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panelBuscar = new JPanel(new BorderLayout());
        panelBuscar.setBackground(Color.WHITE);
        panelBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        JLabel lblBuscar = new JLabel("Buscar producto");
        lblBuscar.setFont(new Font("Arial", Font.BOLD, 13));
        lblBuscar.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JPanel filaBuscar = new JPanel(new BorderLayout(6, 0));
        filaBuscar.setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        txtBuscar.setBackground(new Color(250, 250, 250));

        JButton btnBuscar = new JButton();
        ImageIcon imgLupa = new ImageIcon("Iconos/search-alt.png");
        btnBuscar.setIcon(new ImageIcon(
                imgLupa.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        btnBuscar.setBackground(turquesa);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setPreferredSize(new Dimension(36, 30));

        filaBuscar.add(txtBuscar, BorderLayout.CENTER);
        filaBuscar.add(btnBuscar, BorderLayout.EAST);

        panelBuscar.add(lblBuscar, BorderLayout.NORTH);
        panelBuscar.add(filaBuscar, BorderLayout.CENTER);

        // Método de pago — solo Efectivo y Transferencia
        JPanel panelPago = new JPanel(new BorderLayout());
        panelPago.setBackground(Color.WHITE);
        panelPago.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        panelPago.setPreferredSize(new Dimension(180, 80));

        JLabel lblPago = new JLabel("Método de pago");
        lblPago.setFont(new Font("Arial", Font.BOLD, 13));
        lblPago.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        comboPago = new JComboBox<>(new String[]{"Efectivo", "Transferencia"});
        comboPago.setFont(new Font("Arial", Font.PLAIN, 12));
        comboPago.setBackground(Color.WHITE);

        panelPago.add(lblPago, BorderLayout.NORTH);
        panelPago.add(comboPago, BorderLayout.CENTER);

        panelSuperior.add(panelBuscar, BorderLayout.CENTER);
        panelSuperior.add(panelPago,   BorderLayout.EAST);

        // ===== TABLA =====
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));

        String[] columnas = {"Producto", "Precio", "Cantidad", "Subtotal", "Eliminar"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(240, 240, 240));
        tabla.getTableHeader().setForeground(new Color(50, 50, 50));
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setBackground(Color.WHITE);
        tabla.setSelectionBackground(turquesaClaro);

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder());
        scrollTabla.setPreferredSize(new Dimension(0, 160));

        panelTabla.add(scrollTabla);

        // ===== OBSERVACIONES + TOTALES =====
        JPanel panelInferior = new JPanel(new BorderLayout(15, 0));
        panelInferior.setBackground(turquesaClaro);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panelObs = new JPanel(new BorderLayout());
        panelObs.setBackground(turquesaClaro);

        JLabel lblObs = new JLabel("Observaciones");
        lblObs.setFont(new Font("Arial", Font.BOLD, 13));
        lblObs.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));

        txtObservaciones = new JTextArea(4, 20);
        txtObservaciones.setFont(new Font("Arial", Font.PLAIN, 12));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        scrollObs.setBorder(BorderFactory.createEmptyBorder());

        panelObs.add(lblObs,    BorderLayout.NORTH);
        panelObs.add(scrollObs, BorderLayout.CENTER);

        // Totales
        JPanel panelTotales = new JPanel(new BorderLayout());
        panelTotales.setBackground(Color.WHITE);
        panelTotales.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        panelTotales.setPreferredSize(new Dimension(220, 0));

        lblSubtotal = new JLabel("-");
        lblIVA      = new JLabel("-");
        lblTotal    = new JLabel("-");

        JPanel filaTotales = new JPanel(new GridLayout(3, 2, 0, 10));
        filaTotales.setBackground(Color.WHITE);

        JLabel tSubtotal = new JLabel("Subtotal");
        tSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
        tSubtotal.setForeground(new Color(80, 80, 80));
        lblSubtotal.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel tIva = new JLabel("IVA (16%)");
        tIva.setFont(new Font("Arial", Font.PLAIN, 12));
        tIva.setForeground(new Color(80, 80, 80));
        lblIVA.setFont(new Font("Arial", Font.PLAIN, 12));
        lblIVA.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel tTotal = new JLabel("Total");
        tTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        filaTotales.add(tSubtotal); filaTotales.add(lblSubtotal);
        filaTotales.add(tIva);      filaTotales.add(lblIVA);
        filaTotales.add(tTotal);    filaTotales.add(lblTotal);

        panelTotales.add(filaTotales, BorderLayout.CENTER);

        panelInferior.add(panelObs,     BorderLayout.CENTER);
        panelInferior.add(panelTotales, BorderLayout.EAST);

        // ===== BOTONES INFERIORES =====
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(turquesaClaro);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        JButton btnCancelar = new JButton("CANCELAR VENTA");
        btnCancelar.setBackground(new Color(220, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> limpiarTodo());

        JPanel panelDerBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelDerBotones.setBackground(turquesaClaro);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(Color.WHITE);
        btnLimpiar.setForeground(new Color(50, 50, 50));
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLimpiar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(e -> limpiarTodo());

        JButton btnTicket = new JButton("Generar ticket");
        btnTicket.setBackground(turquesa);
        btnTicket.setForeground(Color.WHITE);
        btnTicket.setFont(new Font("Arial", Font.BOLD, 12));
        btnTicket.setBorderPainted(false);
        btnTicket.setFocusPainted(false);
        btnTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // btnTicket.addActionListener(e -> generarTicket()); // conectar con BD

        panelDerBotones.add(btnLimpiar);
        panelDerBotones.add(btnTicket);

        panelBotones.add(btnCancelar,     BorderLayout.WEST);
        panelBotones.add(panelDerBotones, BorderLayout.EAST);

        // ===== ENSAMBLAR TODO =====
        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(turquesaClaro);
        centro.add(panelSuperior,  BorderLayout.NORTH);
        centro.add(panelTabla,     BorderLayout.CENTER);
        centro.add(panelInferior,  BorderLayout.SOUTH);

        principal.add(panelTitulo,  BorderLayout.NORTH);
        principal.add(centro,       BorderLayout.CENTER);
        principal.add(panelBotones, BorderLayout.SOUTH);

        fondo.add(sidebar,   BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);
        setVisible(true);
    }

    // ===================== MÉTODOS PARA LA BD =====================

    public void agregarProducto(String nombre, String precio,
                                String cantidad, String subtotal) {
        modeloTabla.addRow(new Object[]{nombre, precio, cantidad, subtotal, "🗑"});
    }

    public void setTotales(String subtotal, String iva, String total) {
        lblSubtotal.setText(subtotal);
        lblIVA.setText(iva);
        lblTotal.setText(total);
    }

    public void limpiarTodo() {
        modeloTabla.setRowCount(0);
        txtBuscar.setText("");
        txtObservaciones.setText("");
        comboPago.setSelectedIndex(0);
        lblSubtotal.setText("-");
        lblIVA.setText("-");
        lblTotal.setText("-");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ventas::new);
    }
}
