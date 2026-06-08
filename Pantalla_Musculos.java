import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Pantalla_Musculos extends JFrame {

    // Lista de casillas para poder verificar cuáles ha marcado el usuario
    private List<JCheckBox> listaCheckBoxes;

    public Pantalla_Musculos() {
        setTitle("FitTrack - Selecciona Grupos Musculares");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        // Cabecera superior
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(45, 52, 54));
        panelTop.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblInfo = new JLabel("Selecciona uno o varios grupos musculares para tu rutina");
        lblInfo.setFont(new Font("Arial", Font.BOLD, 22));
        lblInfo.setForeground(Color.WHITE);
        panelTop.add(lblInfo);
        add(panelTop, BorderLayout.NORTH);

        // Panel Central para las casillas de selección
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(245, 246, 250));

        JPanel gridMusculos = new JPanel(new GridLayout(2, 3, 30, 30));
        gridMusculos.setBackground(new Color(245, 246, 250));

        // Listado de grupos musculares disponibles
        String[] musculos = {"Pecho", "Espalda", "Piernas", "Brazos", "Abdomen"};
        listaCheckBoxes = new ArrayList<>();

        for (String m : musculos) {
            JCheckBox check = new JCheckBox(m);
            check.setFont(new Font("Arial", Font.BOLD, 18));
            check.setBackground(Color.WHITE);
            // Añadimos un borde acolchado para que parezca una tarjeta independiente
            check.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 221, 225), 1),
                    BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));
            check.setBorderPainted(true);
            gridMusculos.add(check);
            listaCheckBoxes.add(check); // La guardamos en la lista global para leerla luego
        }

        panelCentral.add(gridMusculos, new GridBagConstraints());
        add(panelCentral, BorderLayout.CENTER);

        // Panel Inferior de Acciones con el botón de entrenar ahora
        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));

        JButton btnEntrenar = new JButton("💪 ¡ENTRENAR AHORA!");
        btnEntrenar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEntrenar.setBackground(new Color(46, 204, 113)); // Verde llamativo para la acción principal
        btnEntrenar.setForeground(Color.WHITE);
        btnEntrenar.setPreferredSize(new Dimension(250, 50));

        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 14));

        panelBottom.add(btnEntrenar, BorderLayout.CENTER);
        panelBottom.add(btnLogout, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        // LÓGICA DEL BOTÓN ENTRENAR AHORA
        btnEntrenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> musculosSeleccionados = new ArrayList<>();
                for (JCheckBox check : listaCheckBoxes) {
                    if (check.isSelected()) {
                        musculosSeleccionados.add(check.getText());
                    }
                }

                if (musculosSeleccionados.isEmpty()) {
                    JOptionPane.showMessageDialog(Pantalla_Musculos.this,
                            "Por favor, selecciona al menos un grupo muscular para entrenar.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Abrimos la pantalla de ejercicios pasándole la lista de lo que queremos entrenar
                Pantalla_Ejercicios pEjercicios = new Pantalla_Ejercicios(musculosSeleccionados);
                pEjercicios.setVisible(true);
                dispose();
            }
        });

        // LÓGICA DE CERRAR SESIÓN
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Inicio p = new Pantalla_Inicio();
                p.setVisible(true);
                dispose();
            }
        });
    }
}