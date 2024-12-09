package DAO.Proyecto;

import DBConeccion.SQLConeccion;
import Modelos.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link DAO} para manejar operaciones relacionadas con proyectos
 * en la base de datos utilizando SQL.
 */
public class DAOimp implements DAO {

    /**
     * Registra un nuevo proyecto en la base de datos.
     * Este método aún no está implementado.
     *
     * @param proyecto El objeto {@link Proyecto} que contiene los datos del proyecto a registrar.
     * @return false ya que el método no está implementado.
     */
    @Override
    public boolean postRegistrar(Proyecto proyecto) {
        return false;
    }

    /**
     * Obtiene una lista de proyectos disponibles en la base de datos.
     * Este método devuelve una lista vacía porque aún no está implementado.
     *
     * @param proyecto Filtro o criterio opcional para buscar proyectos. Puede ser null.
     * @return Una lista vacía de objetos {@link Proyecto}.
     */
    @Override
    public List<Proyecto> getVisualizarProyectos(Proyecto proyecto) {
        return new ArrayList<>();
    }

    /**
     * Obtiene información sobre el proyecto asignado a un estudiante específico.
     *
     * @param email El correo electrónico del estudiante cuyo proyecto se desea visualizar.
     * @return Un objeto {@link Proyecto} con los datos del proyecto asignado,
     *         o null si no se encuentra ninguno.
     */
    @Override
    public Proyecto getVisualizarProyectoEstudiante(String email) {
        String selectProyecto = "SELECT p.nombre, p.descripcion " +
                                "FROM proyecto p " +
                                "JOIN estudiante e ON e.id_proyecto = p.id_proyecto " +
                                "JOIN usuario_base u ON u.id_usuario = e.id_usuario " +
                                "WHERE u.email = ?";
        
        Proyecto proyecto = null;
    
        try (Connection conn = SQLConeccion.obtenerConeccion();
             PreparedStatement stmt = conn.prepareStatement(selectProyecto)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                proyecto = new Proyecto();
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar el proyecto del estudiante: " + e.getMessage());
        }
        return proyecto;
    }

    /**
     * Registra un nuevo proyecto en la base de datos utilizando los datos proporcionados.
     * Este método lanza una excepción porque aún no está implementado.
     *
     * @param nombre      El nombre del proyecto a registrar.
     * @param descripcion La descripción del proyecto.
     * @return No devuelve un valor porque lanza una excepción.
     * @throws UnsupportedOperationException Siempre se lanza porque el método no está implementado.
     */
    @Override
    public boolean postRegistrar(String nombre, String descripcion) {
        throw new UnsupportedOperationException("Unimplemented method 'postRegistrar'");
    }
}
