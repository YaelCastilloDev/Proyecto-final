package GUI.Coordinador;

import Servicio.EstudianteServicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para la interfaz gráfica que permite registrar un nuevo estudiante en el sistema.
 *
 * Proporciona un formulario donde se ingresan el correo, la contraseña y la matrícula del estudiante.
 * Los datos son enviados al servicio correspondiente para realizar el registro.
 */
public class RegistrarAlumno extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField matriculaField;
    private JLabel messageLabel;
    private EstudianteServicio estudianteServicio;

    /**
     * Constructor de la clase RegistrarAlumno.
     *
     * Configura las propiedades principales de la ventana, inicializa los componentes de la interfaz gráfica
     * y establece el servicio para gestionar estudiantes.
     */
    public RegistrarAlumno() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
        estudianteServicio = new EstudianteServicio();
    }

    /**
     * Inicializa los componentes de la interfaz gráfica y organiza su disposición.
     *
     * Crea campos de texto para ingresar el correo, la contraseña y la matrícula, y un botón para registrar
     * al estudiante. También incluye un mensaje de retroalimentación para mostrar errores o resultados exitosos.
     */
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        matriculaField = new JTextField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton registrarButton = new JButton("Registrar Estudiante");
        registrarButton.addActionListener(new RegisterAction());

        panel.add(new JLabel("Correo:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(passwordField);
        panel.add(new JLabel("Matrícula:", SwingConstants.CENTER));
        panel.add(matriculaField);

        add(panel, BorderLayout.CENTER);
        add(registrarButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    /**
     * Clase interna para manejar el evento de registro de un nuevo estudiante.
     *
     * Esta clase maneja el evento cuando se presiona el botón "Registrar Estudiante",
     * validando los campos y llamando al servicio correspondiente para registrar al estudiante.
     * También muestra retroalimentación sobre el resultado de la operación.
     */
    private class RegisterAction implements ActionListener {
        /**
         * Método que se ejecuta cuando se presiona el botón "Registrar Estudiante".
         *
         * Valida los campos de entrada y llama al servicio para registrar al estudiante.
         * Muestra mensajes de retroalimentación dependiendo del resultado de la operación.
         *
         * @param e El evento de acción generado por el botón.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                estudianteServicio.registrarEstudiante(
                        emailField.getText(),
                        new String(passwordField.getPassword()),
                        matriculaField.getText()
                );
                messageLabel.setText("Estudiante registrado exitosamente");
                JOptionPane.showMessageDialog(RegistrarAlumno.this, "Estudiante " + emailField.getText() + " registrado.");
            } catch (Exception ex) {
                messageLabel.setText("Error al registrar el estudiante");
                JOptionPane.showMessageDialog(RegistrarAlumno.this, "Error: " + ex.getMessage());
            }
        }
    }
}
