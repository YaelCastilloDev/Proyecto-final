package GUI.Coordinador;

import Servicio.EstudianteServicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para la interfaz gráfica que permite a un coordinador asignar un proyecto a un estudiante.
 *
 * Proporciona un formulario donde se ingresa el correo del estudiante y el ID del proyecto,
 * y realiza la asignación utilizando el servicio correspondiente.
 */
public class AsignarProyecto extends JFrame {

    private JTextField emailField;
    private JTextField proyectoIdField;
    private JLabel messageLabel;

    /**
     * Constructor de la clase AsignarProyecto.
     *
     * Configura las propiedades principales de la ventana y inicializa los componentes de la interfaz gráfica.
     */
    public AsignarProyecto() {
        setTitle("Asignar Proyecto a Estudiante");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
    }

    /**
     * Inicializa los componentes de la interfaz gráfica y organiza su disposición.
     * Se crean los campos de texto, botones y etiquetas necesarios para la interacción con el usuario.
     */
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        emailField = new JTextField();
        proyectoIdField = new JTextField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton asignarButton = new JButton("Asignar Proyecto");
        asignarButton.addActionListener(new AsignarAction());

        panel.add(new JLabel("Correo del Estudiante:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("ID del Proyecto:", SwingConstants.CENTER));
        panel.add(proyectoIdField);

        add(panel, BorderLayout.CENTER);
        add(asignarButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    /**
     * Clase interna para manejar el evento de asignación del proyecto al estudiante.
     * Esta clase es responsable de realizar la validación de los datos ingresados por el usuario
     * y de llamar al servicio correspondiente para asignar el proyecto.
     */
    private class AsignarAction implements ActionListener {
        /**
         * Método que se ejecuta cuando se presiona el botón "Asignar Proyecto".
         *
         * Valida los campos de entrada, llama al servicio para realizar la asignación y muestra un mensaje
         * indicando el resultado de la operación.
         *
         * @param e El evento de acción generado por el botón.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            EstudianteServicio estudianteServicio = new EstudianteServicio();
            String email = emailField.getText();
            String proyectoIdStr = proyectoIdField.getText();

            try {
                // Validar que el proyectoId sea un número entero
                int proyectoId = Integer.parseInt(proyectoIdStr);
                // Llamar al servicio para asignar el proyecto al estudiante
                boolean result = estudianteServicio.asignarProyectoAEstudiante(email, proyectoId);

                if (result) {
                    messageLabel.setText("Proyecto asignado exitosamente.");
                    JOptionPane.showMessageDialog(AsignarProyecto.this, "Proyecto asignado a " + email);
                } else {
                    messageLabel.setText("Error al asignar el proyecto.");
                    JOptionPane.showMessageDialog(AsignarProyecto.this, "Error: No se pudo asignar el proyecto.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: El ID del proyecto debe ser un número.");
                JOptionPane.showMessageDialog(AsignarProyecto.this, "Error: El ID del proyecto debe ser un número.");
            }
        }
    }
}
