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
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 15, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField(15);
        panelFormulario.add(txtApellidos);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField(15);
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Altura (metros, ej: 1.75):"));
        txtAltura = new JTextField(15);
        panelFormulario.add(txtAltura);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField(15);
        panelFormulario.add(txtPassword);

        btnGuardar = new JButton("Guardar Registro");
        btnVolver = new JButton("Volver");
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnVolver);

        add(panelFormulario, new GridBagConstraints());

        // LÓGICA DEL BOTÓN GUARDAR
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();

                if (nombre.isEmpty() || apellidos.isEmpty() || password.isEmpty() || txtEdad.getText().isEmpty() || txtAltura.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "Por favor, rellene todos los campos.");
                    return;
                }

                UsuarioDAO usuarioDAO = new UsuarioDAO();

                // 🌟 VALIDACIÓN: COMPROBAR SI YA EXISTE EL NOMBRE
                if (usuarioDAO.existeUsuario(nombre)) {
                    JOptionPane.showMessageDialog(Pantalla_Registrarse.this,
                            "El nombre de usuario '" + nombre + "' ya está registrado. Por favor, elige otro.",
                            "Usuario Duplicado",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Detiene el registro por completo
                }

                try {
                    int edad = Integer.parseInt(txtEdad.getText().trim());
                    double altura = Double.parseDouble(txtAltura.getText().trim());

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombre(nombre);
                    nuevoUsuario.setApellidos(apellidos);
                    nuevoUsuario.setEdad(edad);
                    nuevoUsuario.setAltura(altura);
                    nuevoUsuario.setPassword(password);
                    nuevoUsuario.setRol("USER");

                    boolean completado = usuarioDAO.registrarUsuario(nuevoUsuario);

                    if (completado) {
                        JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "¡Usuario registrado correctamente!");
                        Pantalla_Inicio pInicio = new Pantalla_Inicio();
                        pInicio.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "Error crítico al guardar en la Base de Datos.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Pantalla_Registrarse.this, "La edad debe ser un número entero y la altura un decimal con punto (ej: 1.77).");
                }
            }
        });

        // LÓGICA DEL BOTÓN VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla_Inicio p = new Pantalla_Inicio();
                p.setVisible(true);
                dispose();
            }
        });
    }
}