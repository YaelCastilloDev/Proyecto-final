package DAO.Estudiante;

import DBConeccion.SQLConeccion;
import Modelos.Estudiante;

import static Seguridad.PasswordHasher.encodePassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz {@link DAO}. Esta clase proporciona los métodos necesarios
 * para interactuar con la base de datos, permitiendo registrar, actualizar y obtener la información 
 * de los estudiantes, así como asignar proyectos.
 */
public class DAOimp implements DAO {

    /**
     * Registra un nuevo estudiante en el sistema.
     *
     * @param email      El correo electrónico del estudiante que se registrará.
     * @param contrasena La contraseña del estudiante para su cuenta, que será encriptada antes de guardarse.
     * @param matricula  El número de matrícula único del estudiante.
     * @return true si el estudiante se registró exitosamente, false si ocurrió algún error durante el proceso.
     */
    @Override
    public boolean postRegistrar(String email, String contrasena, String matricula) {

        String ContrasenaHasheada = encodePassword(contrasena);
        String insertUsuarioBase = "INSERT INTO usuario_base (email, contrasena) VALUES (?, ?)";
        String insertEstudiante = "INSERT INTO estudiante (id_usuario, matricula) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmtUsuarioBase = null;
        PreparedStatement stmtEstudiante = null;

        try {
            conn = SQLConeccion.obtenerConeccion();
            conn.setAutoCommit(false); 

            // Inserta datos en la tabla usuario_base.
            stmtUsuarioBase = conn.prepareStatement(insertUsuarioBase, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuarioBase.setString(1, email);
            stmtUsuarioBase.setString(2, ContrasenaHasheada);
            stmtUsuarioBase.executeUpdate();

            // Obtiene el ID generado.
            ResultSet rs = stmtUsuarioBase.getGeneratedKeys();
            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }

            // Inserta datos en la tabla estudiante.
            stmtEstudiante = conn.prepareStatement(insertEstudiante);
            stmtEstudiante.setInt(1, idUsuario);
            stmtEstudiante.setString(2, matricula);
            stmtEstudiante.executeUpdate();

            conn.commit();
            System.out.println("Estudiante registrado exitosamente.");
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Error al registrar el estudiante: " + e.getMessage());
            return false;

        } finally {
            try {
                if (stmtUsuarioBase != null) stmtUsuarioBase.close();
                if (stmtEstudiante != null) stmtEstudiante.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza los datos personales de un estudiante existente.
     *
     * @param email      El correo electrónico actualizado del estudiante.
     * @param telefono   El número de teléfono actualizado del estudiante.
     * @param nombre     El nombre completo actualizado del estudiante.
     * @param direccion  La dirección actualizada del estudiante.
     * @param genero     El género actualizado del estudiante.
     * @return true si los datos se actualizaron correctamente, false si hubo algún error.
     */
    @Override
    public boolean updateActualizarDatosPersonales(String email, String telefono, String nombre,
                                                   String direccion, String genero) {

        String updateUsuarioBase = "UPDATE usuario_base SET nombre = ?, telefono = ?, direccion = ? WHERE email = ?";
        String updateEstudiante = "UPDATE estudiante SET genero = ? WHERE id_usuario = (SELECT id_usuario FROM usuario_base WHERE email = ?)";

        Connection conn = null;
        PreparedStatement stmtUsuarioBase = null;
        PreparedStatement stmtEstudiante = null;

        try {
            conn = SQLConeccion.obtenerConeccion();
            conn.setAutoCommit(false); // Desactiva auto-commit para manejo seguro de transacciones.

            // Actualizar datos en usuario_base.
            stmtUsuarioBase = conn.prepareStatement(updateUsuarioBase);
            stmtUsuarioBase.setString(1, nombre);
            stmtUsuarioBase.setString(2, telefono);
            stmtUsuarioBase.setString(3, direccion);
            stmtUsuarioBase.setString(4, email);
            int usuarioBaseRows = stmtUsuarioBase.executeUpdate();

            if (usuarioBaseRows == 0) {
                throw new SQLException("No se encontró un usuario con el email proporcionado.");
            }

            // Actualizar datos en estudiante.
            stmtEstudiante = conn.prepareStatement(updateEstudiante);
            stmtEstudiante.setString(1, genero);
            stmtEstudiante.setString(2, email);
            int estudianteRows = stmtEstudiante.executeUpdate();

            if (estudianteRows == 0) {
                throw new SQLException("No se encontró un estudiante asociado al usuario con el email proporcionado.");
            }

            // Confirma la transacción.
            conn.commit();
            System.out.println("Estudiante actualizado exitosamente.");
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Reversión en caso de error.
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Error al actualizar el estudiante: " + e.getMessage());
            return false;

        } finally {
            try {
                if (stmtUsuarioBase != null) stmtUsuarioBase.close();
                if (stmtEstudiante != null) stmtEstudiante.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Obtiene la información de un estudiante a partir de su correo electrónico.
     *
     * @param email El correo electrónico del estudiante.
     * @return Un objeto {@link Estudiante} con los datos del estudiante, o null si no se encuentra.
     */
    @Override
    public Estudiante getEstudiante(String email) {
        return null; // Implementación pendiente.
    }

    /**
     * Asigna un proyecto a un estudiante en la base de datos.
     *
     * @param email      El correo electrónico del estudiante al que se asignará el proyecto.
     * @param idProyecto El identificador único del proyecto que será asignado.
     * @return true si la asignación fue exitosa; false si ocurrió algún error.
     */
    @Override
    public boolean asignarProyectoAEstudiante(String email, int idProyecto) {
        String updateProyectoEstudiante = "UPDATE estudiante SET id_proyecto = ? WHERE id_usuario = (SELECT id_usuario FROM usuario_base WHERE email = ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = SQLConeccion.obtenerConeccion();
            stmt = conn.prepareStatement(updateProyectoEstudiante);
            stmt.setInt(1, idProyecto);
            stmt.setString(2, email);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No se encontró un estudiante con el email proporcionado.");
            }

            System.out.println("Proyecto asignado exitosamente.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al asignar el proyecto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
