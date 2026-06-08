import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Usamos el protocolo compatible de mariadb para tu librería .jar
    private static final String URL = "jdbc:mariadb://localhost:3306/fittrack";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Por defecto en XAMPP va vacío

    public static Connection obtenerConexion() throws SQLException {
        try {
            // Forzamos la carga del Driver de MariaDB que tienes instalado
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró la clase del Driver de MariaDB.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}