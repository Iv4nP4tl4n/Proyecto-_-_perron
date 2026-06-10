import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Tickets extends JFrame {

    Color turquesa       = new Color(0, 190, 185);
    Color turquesaClaro  = new Color(188, 237, 234);
    Color turquesaOscuro = new Color(0, 160, 155);

    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private TableRowSorter<DefaultTableModel> sorter;

    private JLabel lblMostrando;
    private JTextField txtBuscar;
    private JComboBox<String> cbCajero, cbEstado;

    public Tickets() {
        setTitle("Tickets");
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
            boolean esActivo = texto.equals("TICKETS");

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
            fila.setMaximumSize(new Dimension(200, 55));
            fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fila.setBackground(esActivo ? turquesaOscuro : turquesa);

            JLabel icono = new JLabel(new ImageIcon(
                    new ImageIcon(ruta).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

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

        JLabel titulo = new JLabel("TICKETS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 230, 230));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(turquesaClaro);
        top.add(titulo);

        // ===================== TABLA =====================
        String[] columnas = {
            "ID", "FECHA", "CLIENTE", "CAJERO",
            "TOTAL", "PAGO", "ESTADO", "ACCIONES"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        tabla.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabla);

        // ===================== FILTROS =====================
        JPanel filtros = new JPanel(new GridLayout(1, 3, 10, 10));
        filtros.setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        cbCajero = new JComboBox<>(new String[]{"Todos los cajeros"});
        cbEstado = new JComboBox<>(new String[]{"Todos", "Completado", "Pendiente", "Anulado"});

        filtros.add(txtBuscar);
        filtros.add(cbCajero);
        filtros.add(cbEstado);

        // ===================== MOSTRAR =====================
        lblMostrando = new JLabel("Mostrando 0 tickets");

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(lblMostrando);

        // ===================== ENSAMBLAR =====================
        principal.add(top, BorderLayout.NORTH);
        principal.add(filtros, BorderLayout.CENTER);
        principal.add(scroll, BorderLayout.SOUTH);
        principal.add(bottom, BorderLayout.PAGE_END);

        fondo.add(sidebar, BorderLayout.WEST);
        fondo.add(principal, BorderLayout.CENTER);

        add(fondo);
        setVisible(true);
    }

    // ===================== BD FUNCTIONS =====================

    public void agregarTicket(String id, String fecha, String cliente,
                              String cajero, String total,
                              String pago, String estado) {
        modeloTabla.addRow(new Object[]{
            id, fecha, cliente, cajero, total, pago, estado, ""
        });
        actualizar();
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
        actualizar();
    }

    // ===================== ACCIONES =====================

    private void actualizar() {
        lblMostrando.setText("Total: " + modeloTabla.getRowCount());
    }

    private void verTicket(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();
        JOptionPane.showMessageDialog(this, "Ver ticket: " + id);
    }

    private void eliminarTicket(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();

        int op = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar ticket " + id + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(fila);
            actualizar();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tickets::new);
    }
}