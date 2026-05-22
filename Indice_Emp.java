
import java.awt.*;
import javax.swing.*;

public class Indice_Emp {
    JFrame ventanaIn;
    JButton ventas;
    JButton inventario;
    JButton productos;
    JButton regresar;

    public Indice_Emp(){

        ventanaIn = new JFrame("Indice");
        ventanaIn.setSize(1300, 900);
        ventanaIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaIn.setLocationRelativeTo(null);

        // Layout centrado
        ventanaIn.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(15, 10, 15, 10);

        // BOTÓN VENTAS
        ventas = new JButton("Ventas");
        ventas.setPreferredSize(new Dimension(200, 50));
        ventas.setFont(new Font("Arial", Font.BOLD, 16));
        ventas.setFocusPainted(false);

        // BOTÓN INVENTARIO
        inventario = new JButton("Inventario");
        inventario.setPreferredSize(new Dimension(200, 50));
        inventario.setFont(new Font("Arial", Font.BOLD, 16));
        inventario.setFocusPainted(false);

        // BOTÓN PRODUCTOS
        productos = new JButton("Productos");
        productos.setPreferredSize(new Dimension(200, 50));
        productos.setFont(new Font("Arial", Font.BOLD, 16));
        productos.setFocusPainted(false);

        // BOTÓN REGRESAR
        regresar = new JButton("Regresar");
        regresar.setPreferredSize(new Dimension(200, 50));
        regresar.setFont(new Font("Arial", Font.BOLD, 16));
        regresar.setFocusPainted(false);

        // AGREGAR BOTONES CENTRADOS
        ventanaIn.add(ventas, gbc);
        ventanaIn.add(inventario, gbc);
        ventanaIn.add(productos, gbc);
        ventanaIn.add(regresar, gbc);
        

        // EVENTOS
        ventas.addActionListener(e -> {
            
        });

        inventario.addActionListener(e -> {
            ventanaIn.dispose();
            new inventario();
        });

        productos.addActionListener(e -> {

        });

        regresar.addActionListener(e -> {
            ventanaIn.dispose();
            new Inicio();
        });

        ventanaIn.setVisible(true); 
    }

    public static void main(String[] args) {
        new Indice_Adm();
    }
}






/* 
                                                                               
                                  -%%%%%%%%%%%##**++==---::....                                     
                                  *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%##*+=--::...              
                                  #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#*######******##*****+           
                                 .#@%**+++**##%%@@@@@@@@@@@@@@@@@@%#*#*******************:          
                                 .#@#*+.-*######%%%%#**+++++=+#@@%%#*#####***************+          
                                 .#@#*=:=***####%%%%%%%%%%%%%#%@@%##*####*****************:         
                                 .%@#*--+****######%%%%%%%%%%%@@@%##*#####***************+:         
                                 :%@##-=+****########%%%%%%%%%@@@%##*####****************+.         
                                 :%@#*-===+++**########%%%%%%@@@@%#**#####**************+*.         
                                 :@@#*-====++++**###########%%@@@%#*######**************+*          
                                 -@@#*--====+++++++*****#####%@@@%#*#######*************++          
                                 -@@#*--======++++++++++*****#@@%%#*#####***************++          
                                 -@%#*--========+++++++++++++#@@%%#*######**************++          
                                 =@%#*--=================+===#@@%%#*##*****************++=          
                                 =@%#*---====================#@@%#**#####*#************++=          
                                 =@%#*----===================%@@%#*########************++-          
                                 +@%%@#*+====================@@@%#*######*************+++-          
                                 +@@@@@@@@@%%*==============-@@@%#*###*#**************+++:          
                                 +@@@@@@@@@@@@@@@@@@@#+======@@@%#**##****************+++:          
                                 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%***#****************++++.          
                                 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%********************++++.          
                                 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%*******************+++++           
                                 *@@@@@@@@@@@@@##@@@@@@@@@@@@@@%#******************+++++=           
                                 #@%%@@@@@@@@@@@@@%#-:=*#%%@@@@%#*****************++++++=           
                                .#@@@@@@@@@@@@@@@@@@@@@%=::*@@@%#*****************++++++=           
                                 +##%%@@@@@@@@@@@@@@@@@@@@@@@@@%*****************++++++=-           
                    .*%#*#%##:     .:+####%%%@@@@@@@@@@@@@@@@@@%*****************+++=+==-           
                .:*@%##+**++%%#%%*-:.-++===**##%%%@@@@@@@@@@@@@%***************+++=+=+==:           
              -*%%%##%*#+*****+#**##%%%#*=======++*##%%%%@@@@@%#************++*++++++-.             
           -%@@#%#**+#+##+**=#*+#*+#+##+#%@@#+=========+####%%##*********+++++++++:.                
       .=%@@@@##*+*#+##*+#+##***+**+**+#*+#**#%%@#++======-==+*=--=****++*+**+=:                 .:.
     .*@@@@@@@%*##*###**+#***#+*#**#*+#***#=*+*#**%%%%#*+=====::++*****+***-:                    :. 
      +%@@@@@@@@@@########**#+#*+**+#*+*#*+#*+##+**+####%@@@#====++*****:                       ::  
       -*##%@@@@@@@@@%########*+#+*#*+*#+#*****=#*+*#****%@@@@@@#-=+=:                         :-   
         .:=*#%%@@@@@@@@@@%#########+*+##*+**+###******%@@@@@@%#*:......                      ::    
              .*##%%@@@@@@@@@@%###*+####+*%%####****#@@@@@@@##**=--=-==++++.               .-=.     
                 .-+#%%@@@@@@@@@@@%###*+##*=+##***%@@@@@@%#**+-.:..   ..:-==:         .:--:.        
                     .-*#%%@@@@@@@@@@@%##**%%%%%@@@@@@@%#**+: .:=#**==-+---:.:----::::.             
                         :+##%%@@@@@@@@@@@@@@@@@@@@@@##**+-. :#@#####***+--=.                       
                            .-+#%%%@@@@@@@@@@@@@@@%#**+-. .+@@@@@@@@%%%@@@%-                        
                                .:*##%%@@@@@@@@@%#**+: .=%@@@@@@@@@@@@@@##*=                        
                                    .-*##%%@@@#**+=. .*@@@@@@@@@@@@@@%#*****.                       
                                        :=*##**+-.   =@@@@@@@@@@@@@%#****+-.                        
                                           .::.      +@@@@@@@@@@@#****+-.                           
                                                      :+#%@@@@@#***+=.                              
                                                      */

