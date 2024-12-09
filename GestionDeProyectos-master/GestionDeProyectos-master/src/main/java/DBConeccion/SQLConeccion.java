package DBConeccion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar la conexión con la base de datos.
 *
 * Esta clase proporciona métodos para inicializar, obtener y cerrar la conexión a la base de datos,
 * así como un método para probar la conectividad.
 */
public class SQLConeccion {
    private static Connection connection;

    /**
     * Inicializa la conexión con la base de datos si aún no está establecida o si se encuentra cerrada.
     *
     * Este método utiliza las credenciales predeterminadas para conectarse a una base de datos MySQL.
     *
     * @throws SQLException Sí ocurre un error al establecer la conexión con la base de datos.
     */
    public static void inicializarConnecion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String user = "root";
            String password = "123456";
            String url = "jdbc:mysql://localhost:3306/GestionDeProyectos";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established.");
        }
    }

    /**
     * Obtiene la conexión actual a la base de datos.
     *
     * Este método verifica si la conexión está inicializada y activa antes de devolverla.
     *
     * @return La conexión activa a la base de datos.
     * @throws SQLException Si la conexión no está inicializada o si se encuentra cerrada.
     */
    public static Connection obtenerConeccion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not initialized. Call initializeConnection() first.");
        }
        return connection;
    }

    /**
     * Cierra la conexión actual con la base de datos, si está activa.
     *
     * Este método garantiza que los recursos asociados a la conexión sean liberados.
     */
    public static void CerrarConneciones() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }

    /**
     * Prueba la conexión con la base de datos utilizando las configuraciones predeterminadas.
     *
     * Este método intenta establecer una conexión con la base de datos y muestra un mensaje en caso de fallo.
     */
    public static void tryConneccion() {
        try {
            SQLConeccion.inicializarConnecion();
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Error connecting to the database. Please check your configuration.",
                    "Database Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
