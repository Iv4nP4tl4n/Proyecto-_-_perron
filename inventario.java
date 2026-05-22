import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class inventario extends JFrame {

    JTable tabla;
    JButton regresar;
    DefaultTableModel modelo;

    public inventario() {

        setTitle("Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Columnas
        String[] columnas = {
            "ID",
            "Producto",
            "Precio",
            "Cantidad"
        };

        // Modelo
        modelo = new DefaultTableModel(columnas, 0);

        // Datos de prueba
        modelo.addRow(new Object[]{1, "Croquetas", 12000, 5});
        modelo.addRow(new Object[]{2, "Cepillos", 250, 20});
        modelo.addRow(new Object[]{3, "Juguetes", 500, 10});

        // Tabla
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        // Botón regresar
        regresar = new JButton("Regresar");

        // Panel para botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(regresar);

        // Agregar componentes
        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        regresar.addActionListener(e -> {
            dispose();
            new Indice_Adm();
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new inventario();

    }
}