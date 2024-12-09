package DAO.Estudiante;

import Modelos.Estudiante;

/**
 * Interfaz que define los métodos para interactuar con la base de datos de estudiantes.
 * Proporciona métodos para registrar, actualizar y obtener la información de los estudiantes,
 * así como para asignar proyectos a un estudiante específico.
 */
public interface DAO {

    /**
     * Registra un nuevo estudiante en el sistema.
     *
     * @param email      El correo electrónico del estudiante que se registrará.
     * @param contrasena La contraseña del estudiante para su cuenta.
     * @param matricula  El número de matrícula del estudiante.
     * @return true si el registro fue exitoso, false si hubo algún error durante el proceso.
     */
    boolean postRegistrar(String email, String contrasena, String matricula);

    /**
     * Actualiza los datos personales de un estudiante existente en la base de datos.
     *
     * @param email      El correo electrónico actualizado del estudiante.
     * @param telefono   El número de teléfono actualizado del estudiante.
     * @param nombre     El nombre completo actualizado del estudiante.
     * @param direccion  La dirección actualizada del estudiante.
     * @param genero     El género actualizado del estudiante.
     * @return true si la actualización fue exitosa, false si ocurrió algún error.
     */
    boolean updateActualizarDatosPersonales(String email, String telefono, String nombre,
                                            String direccion, String genero);

    /**
     * Obtiene la información de un estudiante en base a su correo electrónico.
     *
     * @param email El correo electrónico del estudiante a buscar.
     * @return Un objeto {@link Estudiante} con los datos del estudiante si existe, 
     *         o null si no se encuentra en la base de datos.
     */
    Estudiante getEstudiante(String email);

    /**
     * Asigna un proyecto específico a un estudiante en el sistema.
     *
     * @param email      El correo electrónico del estudiante al que se le asignará el proyecto.
     * @param idProyecto El identificador único del proyecto que será asignado.
     * @return true si la asignación fue exitosa, false si hubo algún error durante el proceso.
     */
    boolean asignarProyectoAEstudiante(String email, int idProyecto);
}
