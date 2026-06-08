import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Musculos extends JFrame {

    public Pantalla_Musculos() {
        setTitle("FitTrack - Selecciona Grupo Muscular");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // FORZAR PANTALLA COMPLETA
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(45, 52, 54));
        JLabel lblInfo = new JLabel("Elige el grupo muscular que quieres entrenar");
        lblInfo.setFont(new Font("Arial", Font.BOLD, 22));
        lblInfo.setForeground(Color.WHITE);
        panelTop.add(lblInfo);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(245, 246, 250));

        JPanel gridBotones = new JPanel(new GridLayout(2, 3, 20, 20));
        gridBotones.setBackground(new Color(245, 246, 250));

        String[] musculos = {"Pecho", "Espalda", "Piernas", "Brazos", "Abdomen"};
        for (String m : musculos) {
            JButton btn = new JButton(m);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setPreferredSize(new Dimension(150, 80));
            gridBotones.add(btn);
        }

        panelCentral.add(gridBotones, new GridBagConstraints());
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 15));
        panelBottom.setBackground(Color.WHITE);
        JButton btnLogout = new JButton("Cerrar Sesión");
        panelBottom.add(btnLogout);
        add(panelBottom, BorderLayout.SOUTH);

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