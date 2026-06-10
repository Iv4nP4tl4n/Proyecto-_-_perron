import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/proyecto_perron";

    private static final String USER = "root";

    private static final String PASSWORD = "Iv4nP4tl4n";

    public static Connection getConexion() throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}