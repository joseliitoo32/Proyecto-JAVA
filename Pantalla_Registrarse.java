import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Registrarse extends JFrame {

    private JTextField txtNombre, txtApellidos, txtEdad, txtAltura;
    private JPasswordField txtPassword;
    private JButton btnGuardar, btnVolver;

    public Pantalla_Registrarse() {
        setTitle("FitTrack - Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        JPanel panelRegistro = new JPanel(new BorderLayout());
        panelRegistro.setPreferredSize(new Dimension(500, 450));
        panelRegistro.setBorder(BorderFactory.createLineBorder(new Color(220, 221, 225), 1));
        panelRegistro.setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("📝 Registro FitTrack");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panelRegistro.add(panelTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Estatura (metros):"));
        txtAltura = new JTextField();
        panelFormulario.add(txtAltura);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panelFormulario.add(txtPassword);

        panelRegistro.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setBackground(Color.WHITE);
        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        panelRegistro.add(panelBotones, BorderLayout.SOUTH);

        add(panelRegistro, new GridBagConstraints());

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText().trim();
                    String apellidos = txtApellidos.getText().trim();
                    String password = new String(txtPassword.getPassword()).trim();

                    if(nombre.isEmpty() || password.isEmpty() || txtEdad.getText().isEmpty() || txtAltura.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "Por favor, rellene todos los campos.");
                        return;
                    }

                    int edad = Integer.parseInt(txtEdad.getText().trim());
                    double altura = Double.parseDouble(txtAltura.getText().trim());

                    Usuario nuevo = new Usuario(0, nombre, apellidos, edad, altura, password, "USER");
                    UsuarioDAO dao = new UsuarioDAO();

                    if (dao.registrarUsuario(nuevo)) {
                        JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "¡Usuario registrado con éxito!");
                        btnVolver.doClick();
                    } else {
                        JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "Error al guardar el usuario.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "Introduzca datos numéricos válidos en Edad y Altura.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Inicio pInicio = new Pantalla_Inicio();
                pInicio.setVisible(true);
                dispose();
            }
        });
    }
}