import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Pantalla_Ejercicios extends JFrame {

    private JTable tablaEjercicios;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public Pantalla_Ejercicios(List<String> musculosSeleccionados) {
        setTitle("FitTrack - Tu Rutina Generada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        // Panel Superior (Cabecera)
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(45, 52, 54));
        panelTop.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        String gruposTexto = String.join(", ", musculosSeleccionados);
        JLabel lblTitulo = new JLabel("🏋️ TU RUTINA DE HOY PARA: [ " + gruposTexto.toUpperCase() + " ]");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTop.add(lblTitulo);
        add(panelTop, BorderLayout.NORTH);

        // Tabla de datos central
        String[] columnas = {"ID", "Nombre del Ejercicio", "Descripción / Ejecución", "Dificultad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEjercicios = new JTable(modeloTabla);
        tablaEjercicios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEjercicios.setRowHeight(30);
        tablaEjercicios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollTabla = new JScrollPane(tablaEjercicios);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));
        add(scrollTabla, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBottom.setBackground(Color.WHITE);
        btnVolver = new JButton("↩️ Cambiar Músculos o Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        panelBottom.add(btnVolver);
        add(panelBottom, BorderLayout.SOUTH);

        // Cargar datos desde la Base de Datos MariaDB
        UsuarioDAO dao = new UsuarioDAO();
        List<Ejercicio> ejerciciosRutina = dao.obtenerEjerciciosPorMusculos(musculosSeleccionados);

        for (Ejercicio ej : ejerciciosRutina) {
            Object[] fila = {
                    ej.getId(),
                    ej.getNombre(),
                    ej.getDescripcion(),
                    ej.getDificultad()
            };
            modeloTabla.addRow(fila);
        }

        // Si la base de datos no arrojó nada, avisamos amigablemente
        if (ejerciciosRutina.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se han encontrado ejercicios en la base de datos para los grupos musculares seleccionados.\nVerifica los nombres en tu tabla de phpMyAdmin.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        // Volver a las casillas
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Musculos pMusculos = new Pantalla_Musculos();
                pMusculos.setVisible(true);
                dispose();
            }
        });
    }
}