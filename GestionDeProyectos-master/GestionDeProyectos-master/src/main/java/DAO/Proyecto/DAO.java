package DAO.Proyecto;

import java.util.List;

import Modelos.Proyecto;

/**
 * Interfaz para manejar operaciones relacionadas con proyectos en la base de datos.
 * 
 * Esta interfaz define los métodos necesarios para registrar proyectos, visualizar
 * proyectos y obtener información específica sobre proyectos asignados a estudiantes.
 */
public interface DAO {

    /**
     * Registra un nuevo proyecto en la base de datos.
     *
     * @param proyecto El objeto {@link Proyecto} que contiene los datos del proyecto a registrar.
     * @return true si el proyecto se registró exitosamente, false en caso contrario.
     */
    boolean postRegistrar(Proyecto proyecto);

    /**
     * Obtiene una lista de proyectos disponibles en la base de datos.
     *
     * @param proyecto Filtro o criterio opcional para buscar proyectos. Puede ser null para listar todos los proyectos.
     * @return Una lista de objetos {@link Proyecto} que representan los proyectos disponibles.
     */
    List<Proyecto> getVisualizarProyectos(Proyecto proyecto);

    /**
     * Obtiene información sobre un proyecto asignado a un estudiante específico.
     *
     * @param email El correo electrónico del estudiante para buscar el proyecto asociado.
     * @return Un objeto {@link Proyecto} que contiene los datos del proyecto asignado, o null si no se encuentra ninguno.
     */
    Proyecto getVisualizarProyectoEstudiante(String email);

    /**
     * Registra un nuevo proyecto en la base de datos utilizando los datos proporcionados.
     *
     * @param nombre      El nombre del proyecto a registrar.
     * @param descripcion Una breve descripción del proyecto.
     * @return true si el proyecto se registró exitosamente, false en caso contrario.
     */
    boolean postRegistrar(String nombre, String descripcion);
}
