import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // MÉTODO REGISTRAR: Blindado con trazas de depuración exactas
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellidos, edad, altura, password, rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Forzamos valores por defecto si los strings llegan vacíos para evitar fallos de restricción NOT NULL
            ps.setString(1, usuario.getNombre() != null && !usuario.getNombre().isEmpty() ? usuario.getNombre() : "SinNombre");
            ps.setString(2, usuario.getApellidos() != null && !usuario.getApellidos().isEmpty() ? usuario.getApellidos() : "SinApellidos");
            ps.setInt(3, usuario.getEdad());
            ps.setDouble(4, usuario.getAltura());
            ps.setString(5, usuario.getPassword() != null && !usuario.getPassword().isEmpty() ? usuario.getPassword() : "1234");
            ps.setString(6, usuario.getRol() != null ? usuario.getRol() : "USER");

            System.out.println("LOG: Intentando ejecutar INSERT en MySQL...");
            int filasAfectadas = ps.executeUpdate();
            System.out.println("LOG: ¡Registro completado con éxito! Filas añadidas: " + filasAfectadas);
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("\n🛑 --- ERROR CRÍTICO AL REGISTRAR EN MYSQL ---");
            System.err.println("Código de estado SQL: " + e.getSQLState());
            System.err.println("Código de error nativo: " + e.getErrorCode());
            System.err.println("Mensaje exacto del servidor: " + e.getMessage());
            System.err.println("-----------------------------------------------\n");
            e.printStackTrace();
            return false;
        }
    }

    // MÉTODO LOGIN: Credenciales globales de admin e inicio normal por Base de Datos
    public Usuario login(String nombre, String password) {
        // Validación prioritaria para el usuario administrador por defecto
        if ("admin".equalsIgnoreCase(nombre) && "admin".equals(password)) {
            Admin administradorDefecto = new Admin();
            administradorDefecto.setId(999);
            administradorDefecto.setNombre("Admin");
            administradorDefecto.setApellidos("Del Sistema");
            administradorDefecto.setEdad(30);
            administradorDefecto.setAltura(1.80);
            administradorDefecto.setPassword("admin");
            administradorDefecto.setRol("ADMIN");
            return administradorDefecto;
        }

        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = "ADMIN".equalsIgnoreCase(rs.getString("rol")) ? new Admin() : new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEdad(rs.getInt("edad"));
                    usuario.setAltura(rs.getDouble("altura"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("🔴 ERROR EN EL LOGIN: " + e.getMessage());
        }
        return null;
    }

    // MÉTODO SELECT ALL: Obtención de registros limpia para el JTable administrativo
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellidos, edad, altura, password, rol FROM usuarios";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getDouble("altura"),
                        rs.getString("password"),
                        rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            System.err.println("🔴 ERROR AL CONSULTAR LA LISTA DE USUARIOS: " + e.getMessage());
        }
        return lista;
    }
}