package GUI;

import DBConeccion.SQLConeccion;
import GUI.Coordinador.AsignarProyecto;
import GUI.Coordinador.RegistrarAlumno;
import GUI.Estudiante.ActualizarDatos;
import GUI.Estudiante.VisualizarProyecto;
import Seguridad.PasswordHasher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Clase que representa el formulario de inicio de sesión de la aplicación.
 *
 * Este formulario permite a los usuarios ingresar su correo electrónico y contraseña,
 * autenticar sus credenciales y acceder a la aplicación según su rol (estudiante o coordinador).
 */
public class Registro extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    /**
     * Constructor de la clase LoginForm.
     *
     * Configura la ventana con los componentes necesarios para el inicio de sesión (campos de correo y contraseña).
     */
    public Registro() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
    }

    /**
     * Inicializa los componentes del formulario de inicio de sesión, incluyendo los campos de correo,
     * contraseña y el botón de inicio de sesión.
     */
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new LoginAction());

        panel.add(new JLabel("Correo:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(passwordField);

        add(panel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    /**
     * Clase interna que maneja la acción de iniciar sesión.
     *
     * Al hacer clic en el botón de inicio de sesión, esta clase autentica las credenciales del usuario
     * y redirige a la ventana correspondiente según el rol del usuario.
     */
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            String role = authenticate(email, password);

            if (role != null) {
                messageLabel.setText("Inicio de sesión exitoso");
                JOptionPane.showMessageDialog(Registro.this, "Bienvenido " + email);

                // Abrir la ventana correspondiente según el rol
                switch (role) {
                    case "estudiante":
                        ActualizarDatos estudianteManager = new ActualizarDatos(email);
                        estudianteManager.setVisible(true);
                        VisualizarProyecto visualizarProyecto = new VisualizarProyecto(email);
                        visualizarProyecto.setVisible(true);
                        break;
                    case "coordinador":
                        new RegistrarAlumno();
                        RegistrarAlumno coordinadorManager = new RegistrarAlumno();
                        coordinadorManager.setVisible(true);
                        AsignarProyecto asignarProyecto = new AsignarProyecto();
                        asignarProyecto.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(Registro.this, "Rol desconocido");
                }

                dispose(); // Cierra el formulario de inicio de sesión
            } else {
                messageLabel.setText("Correo o contraseña incorrectos");
            }
        }

        /**
         * Autentica las credenciales del usuario, verificando su correo y contraseña.
         *
         * Si las credenciales son correctas, verifica el rol del usuario (estudiante o coordinador).
         *
         * @param email El correo electrónico del usuario.
         * @param password La contraseña ingresada por el usuario.
         * @return El rol del usuario ("estudiante", "coordinador") o null si las credenciales son incorrectas.
         */
        private String authenticate(String email, String password) {

            SQLConeccion.tryConneccion();

            String queryUsuario = "SELECT id_usuario, contrasena FROM usuario_base WHERE email = ?";
            String queryEstudiante = "SELECT id_usuario FROM estudiante WHERE id_usuario = ?";
            String queryCoordinador = "SELECT id_usuario FROM coordinador WHERE id_usuario = ?";

            try (Connection conn = SQLConeccion.obtenerConeccion();
                 PreparedStatement stmtUsuario = conn.prepareStatement(queryUsuario);
                 PreparedStatement stmtEstudiante = conn.prepareStatement(queryEstudiante);
                 PreparedStatement stmtCoordinador = conn.prepareStatement(queryCoordinador)) {

                // Verificar las credenciales en la tabla usuario_base
                stmtUsuario.setString(1, email);
                ResultSet rsUsuario = stmtUsuario.executeQuery();

                if (rsUsuario.next()) {
                    int idUsuario = rsUsuario.getInt("id_usuario");
                    String contrasenaBD = rsUsuario.getString("contrasena");

                    // Comparar contraseñas
                    if (!PasswordHasher.matches(password, contrasenaBD)) {
                        return null; // Contraseña incorrecta
                    }

                    // Verificar si es estudiante
                    stmtEstudiante.setInt(1, idUsuario);
                    ResultSet rsEstudiante = stmtEstudiante.executeQuery();

                    if (rsEstudiante.next()) {
                        return "estudiante";
                    }

                    // Verificar si es coordinador
                    stmtCoordinador.setInt(1, idUsuario);
                    ResultSet rsCoordinador = stmtCoordinador.executeQuery();

                    if (rsCoordinador.next()) {
                        return "coordinador";
                    }

                    return null; // Rol no encontrado
                } else {
                    return null; // Usuario no encontrado
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(Registro.this, "Error al verificar el usuario: " + ex.getMessage());
                return null;
            }
        }
    }
}
