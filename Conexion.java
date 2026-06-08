
import java.sql.PreparedStatement;

public class EjemploFitTrack {

    public static void main(String[] args) {
        // Abrir conexión
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("objectdb:$objectdb/db/coches.odb");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Operaciones
            Usuario u1 = new Usuario();
            u1.setNombre("Juan");
            u1.setApellidos("Perez");
            u1.setEdad(20);
            u1.setAltura(175);
            u1.setPassword("1234");
            u1.setRol("USER");

            String sql = "INSERT INTO usuarios (nombre, apellidos, edad, altura, password, rol) VALUES ('Oscar', 'Amaya', '20', '180', 'oscar', 'usuario')";

            PreparedStatement ps = em.prepareStatement(sql);
            ps.setString(1, u1.getNombre());
            ps.setString(2, u1.getApellidos());
            ps.setInt(3, u1.getEdad());
            ps.setDouble(4, u1.getAltura());
            ps.setString(5, u1.getPassword());
            ps.setString(6, u1.getRol());

            ps.executeUpdate();

            // Finalizar transacción (guardar)
            em.getTransaction().commit();

        } catch (Exception e) {

            try {
                // Si algo falla, deshacer cambios
                em.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                // Cerrar conexión
                em.close();
                Conexion.cerrar(em);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}