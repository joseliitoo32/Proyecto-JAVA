import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // ==========================================
    // 1. COMPROBAR SI EL USUARIO YA EXISTE
    // ==========================================
    public boolean existeUsuario(String nombre) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE LOWER(nombre) = LOWER(?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si ya existe
                }
            }
        } catch (SQLException e) {
            System.err.println("🔴 Error al comprobar existencia del usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ==========================================
    // 2. REGISTRAR UN NUEVO USUARIO
    // ==========================================
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellidos, edad, altura, password, rol) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setInt(3, usuario.getEdad());
            ps.setDouble(4, usuario.getAltura());
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getRol() != null ? usuario.getRol() : "USER");

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("🔴 Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // 3. INICIO DE SESIÓN (LOGIN)
    // ==========================================
    public Usuario login(String nombre, String password) {
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
            System.err.println("🔴 Error en el login: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================
    // 4. OBTENER TODOS LOS USUARIOS (Admin)
    // ==========================================
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
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
            System.err.println("🔴 Error al obtener lista de usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================
    // 5. OBTENER EJERCICIOS FILTRADOS POR MÚSCULOS
    // ==========================================
    public List<Ejercicio> obtenerEjerciciosPorMusculos(List<String> musculos) {
        List<Ejercicio> lista = new ArrayList<>();
        if (musculos == null || musculos.isEmpty()) return lista;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < musculos.size(); i++) {
            sb.append("LOWER(?)");
            if (i < musculos.size() - 1) {
                sb.append(", ");
            }
        }

        // Buscamos ignorando mayúsculas/minúsculas
        String sql = "SELECT * FROM ejercicios WHERE LOWER(nombre_musculo) IN (" + sb.toString() + ")";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < musculos.size(); i++) {
                ps.setString(i + 1, musculos.get(i).toLowerCase().trim());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Ejercicio(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("dificultad")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("🔴 Error al obtener ejercicios: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}