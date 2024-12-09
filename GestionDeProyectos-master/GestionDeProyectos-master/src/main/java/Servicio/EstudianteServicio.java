package Servicio;

import DAO.Estudiante.DAOimp;
import DAO.Estudiante.Utiles;
import DBConeccion.SQLConeccion;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con el estudiante,
 * como el registro, actualización de datos personales, obtención de estudiante por email,
 * y la asignación de proyectos a estudiantes.
 */
public class EstudianteServicio {

    // Instancia de la clase DAOimp para interactuar con la base de datos
    DAOimp estudianteDAO = new DAOimp();

    // Instancia de la clase Utils para manejar tareas auxiliares
    Utiles utils = new Utiles();

    // Instancia del modelo Estudiante
    /**
     * Registra un nuevo estudiante en el sistema.
     *
     * @param email El correo electrónico del estudiante.
     * @param contrasena La contraseña del estudiante.
     * @param matricula La matrícula del estudiante.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    public boolean registrarEstudiante(String email, String contrasena, String matricula) {
        // Asignar los valores al objeto Estudiante utilizando la clase Utils
        utils.asignarRegistroEstudiante( email, contrasena, matricula);

        // Intentar establecer la conexión con la base de datos
        SQLConeccion.tryConneccion();

        // Registrar el estudiante a través de la clase DAOimp
        return estudianteDAO.postRegistrar(email, contrasena, matricula);
    }

    /**
     * Actualiza los datos personales de un estudiante en el sistema.
     *
     * @param email El correo electrónico del estudiante a actualizar.
     * @param telefono El nuevo teléfono del estudiante.
     * @param nombre El nuevo nombre del estudiante.
     * @param direccion La nueva dirección del estudiante.
     * @param genero El nuevo género del estudiante.
     * @return true si los datos fueron actualizados correctamente, false en caso contrario.
     */
    public boolean actualizarDatosPersonales(String email, String telefono, String nombre, String direccion, String genero) {
        // Asignar los nuevos valores al objeto Estudiante
        utils.asignarActualizarEstudiante( telefono, nombre, direccion, genero);
        // Intentar establecer la conexión con la base de datos
        SQLConeccion.tryConneccion();
        // Actualizar los datos personales del estudiante a través de la clase DAOimp
        return estudianteDAO.updateActualizarDatosPersonales( email, telefono, nombre, direccion, genero);
    }


    /**
     * Asigna un proyecto a un estudiante.
     *
     * @param email El correo electrónico del estudiante.
     * @param idProyecto El ID del proyecto a asignar al estudiante.
     * @return true si la asignación fue exitosa, false en caso contrario.
     */
    public boolean asignarProyectoAEstudiante(String email, int idProyecto) {
        // Intentar establecer la conexión con la base de datos
        SQLConeccion.tryConneccion();

        // Asignar el proyecto al estudiante a través de la clase DAOimp
        return estudianteDAO.asignarProyectoAEstudiante(email, idProyecto);
    }
}
