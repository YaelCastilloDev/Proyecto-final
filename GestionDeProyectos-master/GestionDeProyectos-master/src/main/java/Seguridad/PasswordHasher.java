package Seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase utilitaria para manejar el hash de contraseñas utilizando BCrypt.
 * Proporciona métodos para encriptar contraseñas y verificar si una contraseña
 * coincide con su versión encriptada almacenada.
 */
public class PasswordHasher {

    /**
     * Instancia estática de {@link PasswordEncoder} que utiliza BCrypt
     * para realizar operaciones de encriptación y verificación.
     */
    private static final PasswordEncoder ENCRIPTADOR = new BCryptPasswordEncoder();

    /**
     * Encripta una contraseña utilizando BCrypt.
     *
     * @param contrasena La contraseña que se desea encriptar.
     * @return Una cadena de texto que representa la contraseña encriptada.
     */
    public static String encodePassword(String contrasena) {
        return ENCRIPTADOR.encode(contrasena);
    }

    /**
     * Verifica si una contraseña en texto plano coincide con una contraseña previamente encriptada.
     *
     * @param contrasena           La contraseña en texto plano introducida por el usuario.
     * @param contrasenaEncriptada La contraseña previamente encriptada almacenada en la base de datos.
     * @return true si la contraseña coincide con la versión encriptada; false en caso contrario
     *         o si la contraseña en texto plano es null.
     */
    // Verifica que la contrasena introducida coincida con la de la base de datos
    public static boolean matches(String contrasena, String contrasenaEncriptada) {
        if (contrasena == null){
            return false;
        }
        return ENCRIPTADOR.matches(contrasena, contrasenaEncriptada);
    }
}