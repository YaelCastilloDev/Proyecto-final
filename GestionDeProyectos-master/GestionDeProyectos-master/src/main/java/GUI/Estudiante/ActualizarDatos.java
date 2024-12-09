package GUI.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Servicio.EstudianteServicio;
import jakarta.validation.ConstraintViolationException;

/**
 * Ventana gráfica para actualizar los datos personales del estudiante.
 *
 * Permite al estudiante modificar su nombre, teléfono, dirección y género, así como visualizar su información de proyectos.
 */
public class ActualizarDatos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JComboBox<String> cmbGenero;
    private JButton btnActualizar;
    private JTextArea txtProyectos;

    /**
     * Constructor de la clase ActualizarDatos.
     *
     * Configura la ventana y sus componentes para permitir la actualización de los datos personales del estudiante.
     *
     * @param email El correo electrónico del estudiante, utilizado para identificar los datos a actualizar.
     */
    public ActualizarDatos(String email) {
        setTitle("Actualizacion de datos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5)); // Ajusta el layout para añadir más componentes

        // Campos de texto para datos personales
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();
        JLabel lblGenero = new JLabel("Género:");
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});

        btnActualizar = new JButton("Actualizar");
        txtProyectos = new JTextArea();
        txtProyectos.setEditable(false);

        // Acción de actualizar perfil
        btnActualizar.addActionListener(new ActionListener() {
            /**
             * Método que se ejecuta cuando el estudiante presiona el botón de actualizar.
             *
             * Valida los datos ingresados y llama al servicio de actualización para guardar los cambios.
             * </p>
             *
             * @param e El evento de acción generado por el botón de actualización.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                EstudianteServicio estudianteServicio = new EstudianteServicio();
                String generoSeleccionado = (String) cmbGenero.getSelectedItem();

                try {
                    boolean actualizado = estudianteServicio.actualizarDatosPersonales(email,
                            txtTelefono.getText(),
                            txtNombre.getText(),
                            txtDireccion.getText(),
                            generoSeleccionado
                    );

                    if (actualizado) {
                        JOptionPane.showMessageDialog(ActualizarDatos.this, "Datos actualizados con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(ActualizarDatos.this, "Error al actualizar los datos.");
                    }
                } catch (ConstraintViolationException validationException) {
                    JOptionPane.showMessageDialog(ActualizarDatos.this,
                            "Error en los datos ingresados: " + validationException.getMessage(),
                            "Error de validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar los componentes al JFrame
        add(lblNombre);
        add(txtNombre);
        add(lblTelefono);
        add(txtTelefono);
        add(lblDireccion);
        add(txtDireccion);
        add(lblGenero);
        add(cmbGenero);
        add(btnActualizar);
        add(new JScrollPane(txtProyectos));
    }
}
