import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GimnasioDAO {

    /**
     * 1. MÚSCULOS: Devuelve la lista de músculos básicos para los botones o JList
     */
    public List<Musculo> obtenerMusculos() {
        List<Musculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM musculos";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Musculo(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * 2. EJERCICIOS: Devuelve los ejercicios que corresponden a un músculo concreto
     */
    public List<Ejercicio> obtenerEjerciciosPorMusculo(int musculoId) {
        List<Ejercicio> lista = new ArrayList<>();
        String sql = "SELECT e.*, m.nombre AS nombre_musculo FROM ejercicios e " +
                "INNER JOIN musculos m ON e.musculo_id = m.id WHERE e.musculo_id = ?";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, musculoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Musculo m = new Musculo(rs.getInt("musculo_id"), rs.getString("nombre_musculo"));
                    Ejercicio e = new Ejercicio(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("dificultad"),
                            m
                    );
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}