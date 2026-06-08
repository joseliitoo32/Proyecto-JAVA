import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Inicio extends JFrame {

    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JButton btnEntrar;
    private JButton btnRegistrar;

    public Pantalla_Inicio() {
        setTitle("FitTrack - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizado a pantalla completa

        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        JPanel panelLogin = new JPanel(new BorderLayout());
        panelLogin.setPreferredSize(new Dimension(450, 350));
        panelLogin.setBorder(BorderFactory.createLineBorder(new Color(220, 221, 225), 1));
        panelLogin.setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(45, 52, 54));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblTitulo = new JLabel("💪 FitTrack");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panelLogin.add(panelTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 25));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(40, 45, 20, 45));

        panelFormulario.add(new JLabel("Usuario:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panelFormulario.add(txtPassword);
        panelLogin.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20));
        panelBotones.setBackground(Color.WHITE);
        btnEntrar = new JButton("Entrar");
        btnRegistrar = new JButton("Registrarse");
        panelBotones.add(btnEntrar);
        panelBotones.add(btnRegistrar);
        panelLogin.add(panelBotones, BorderLayout.SOUTH);

        add(panelLogin, new GridBagConstraints());

        // EVENTO DEL BOTÓN ENTRAR
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();

                if (nombre.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Pantalla_Inicio.this, "Por favor, rellene todos los campos.");
                    return;
                }

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario user = usuarioDAO.login(nombre, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(Pantalla_Inicio.this, "¡Bienvenido, " + user.getNombre() + "!");

                    if ("ADMIN".equalsIgnoreCase(user.getRol())) {
                        Pantalla_Admin pAdmin = new Pantalla_Admin();
                        pAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        pAdmin.setVisible(true);
                    } else {
                        Pantalla_Musculos pMusculos = new Pantalla_Musculos();
                        pMusculos.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        pMusculos.setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Pantalla_Inicio.this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // EVENTO DEL BOTÓN REGISTRARSE
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Registrarse pReg = new Pantalla_Registrarse();
                pReg.setExtendedState(JFrame.MAXIMIZED_BOTH);
                pReg.setVisible(true);
                dispose();
            }
        });
    }
}