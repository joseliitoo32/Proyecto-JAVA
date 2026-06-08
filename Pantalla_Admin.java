import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Pantalla_Admin extends JFrame {

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar, btnCerrarSesion;

    public Pantalla_Admin() {
        setTitle("FitTrack - Panel de Administración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizado completo

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        // Cabecera
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(45, 52, 54));
        panelTop.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblTitulo = new JLabel("👑 PANEL DE GESTIÓN DE USUARIOS (VISTA SELECT)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        panelTop.add(lblTitulo);
        add(panelTop, BorderLayout.NORTH);

        // Estructura de la Tabla de Datos
        String[] columnas = {"ID", "Nombre", "Apellidos", "Edad", "Estatura (m)", "Rol"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaUsuarios.setRowHeight(25);

        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(scrollTabla, BorderLayout.CENTER);

        // Panel de acciones inferior
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBottom.setBackground(Color.WHITE);

        btnActualizar = new JButton("Actualizar Lista");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        panelBottom.add(btnActualizar);
        panelBottom.add(btnCerrarSesion);
        add(panelBottom, BorderLayout.SOUTH);

        // Cargar usuarios al abrir
        cargarDatosUsuarios();

        // Lógica de actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosUsuarios();
                JOptionPane.showMessageDialog(Pantalla_Admin.this, "Lista de usuarios actualizada.");
            }
        });

        // Volver al Login
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Inicio login = new Pantalla_Inicio();
                login.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarDatosUsuarios() {
        modeloTabla.setRowCount(0);
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.obtenerTodosUsuarios();

        for (Usuario u : usuarios) {
            Object[] fila = {
                    u.getId(),
                    u.getNombre(),
                    u.getApellidos(),
                    u.getEdad(),
                    u.getAltura(),
                    u.getRol()
            };
            modeloTabla.addRow(fila);
        }
    }
}