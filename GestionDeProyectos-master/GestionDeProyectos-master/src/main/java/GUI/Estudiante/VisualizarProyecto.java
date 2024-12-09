package GUI.Estudiante;

import Servicio.ProyectoServicio;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana gráfica que permite visualizar los detalles de un proyecto asignado al estudiante.
 *
 * Muestra el nombre y la descripción del proyecto, o un mensaje si el estudiante no tiene un proyecto asignado.
 */
public class VisualizarProyecto extends JFrame {

    private ProyectoServicio proyectoServicio;

    /**
     * Constructor de la clase VisualizarProyecto.
     *
     * Configura la ventana con la información del proyecto asignado al estudiante, utilizando su correo electrónico para obtener los datos.
     *
     * @param email El correo electrónico del estudiante, utilizado para obtener el proyecto asignado.
     */
    public VisualizarProyecto(String email) {
        proyectoServicio = new ProyectoServicio();

        setTitle("Visualizar Proyecto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Etiquetas para el proyecto
        JLabel lblTitulo = new JLabel("Proyecto Asignado", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false); // Solo lectura
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));

        // Obtiene los datos del proyecto asignado
        List<String> proyectoData = obtenerProyecto(email);

        // Si el proyecto existe, mostrar su título y descripción
        if (proyectoData != null && !proyectoData.isEmpty()) {
            String titulo = proyectoData.get(0);
            String descripcion = proyectoData.get(1);
            txtDescripcion.setText("Nombre del Proyecto: " + titulo + "\n\nDescripción:\n" + descripcion);
        } else {
            // Si no hay proyecto asignado, mostrar mensaje
            txtDescripcion.setText("No tienes un proyecto asignado.");
        }

        JScrollPane scrollPane = new JScrollPane(txtDescripcion);

        // Agrega componentes al panel
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Agrega panel a la ventana
        add(panel);
    }

    /**
     * Obtiene la información del proyecto asignado al estudiante.
     *
     * Llama al servicio de proyecto para obtener el título y descripción del proyecto.
     *
     * @param email El correo electrónico del estudiante, utilizado para obtener el proyecto asignado.
     * @return Una lista con el título y la descripción del proyecto, o null si no se pudo obtener la información.
     */
    private List<String> obtenerProyecto(String email) {
        try {
            return proyectoServicio.obtenerProyectoEstudiante(email);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al recuperar el proyecto: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
