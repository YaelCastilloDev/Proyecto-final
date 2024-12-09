package Security;

import org.junit.jupiter.api.Test;

import Seguridad.PasswordHasher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para la funcionalidad de la clase PasswordHasher.
 *
 * Se realizan pruebas unitarias para validar la correcta codificación de contraseñas y la verificación de coincidencia
 * entre contraseñas codificadas y sus originales utilizando las funciones encodePassword y matches.
 */
class PasswordHasherTest {

    /**
     * Prueba que la contraseña se codifique correctamente.
     *
     * Valida que:
     * - La contraseña codificada no sea igual a la original.
     * - La contraseña codificada no sea nula.
     * - La contraseña codificada no esté vacía.
     */
    @Test
    void testEncodePassword() {
        String contrasena = "password123";

        // Codificar la contraseña
        String contrasenaEncriptada = PasswordHasher.encodePassword(contrasena);

        // Verificar que la contraseña codificada no es la misma que la original
        assertNotEquals(contrasena, contrasenaEncriptada, "La contraseña codificada no debe ser igual " +
                "a la contraseña original");

        // Verificar que la contraseña codificada no esté vacía
        assertNotNull(contrasenaEncriptada, "La contraseña codificada no debe ser nula");
        assertFalse(contrasenaEncriptada.isEmpty(), "La contraseña codificada no debe estar vacía");
    }

    /**
     * Prueba que la función matches verifique correctamente la coincidencia
     * entre una contraseña original y su versión codificada.
     */
    @Test
    void testMatchesWithCorrectPassword() {
        String contrasena = "password123";
        String contrasenaEncriptada = PasswordHasher.encodePassword(contrasena);

        // Verificar que la contraseña original coincide con la codificada
        assertTrue(PasswordHasher.matches(contrasena, contrasenaEncriptada), "La contraseña original debe " +
                "coincidir con la codificada");
    }

    /**
     * Prueba que la función matches falle cuando se compara una contraseña incorrecta
     * con una contraseña codificada.
     */
    @Test
    void testMatchesWithIncorrectPassword() {
        String contrasena = "password123";
        String contrasenaEncriptada = PasswordHasher.encodePassword(contrasena);

        // Verificar que una contraseña incorrecta no coincida con la codificada
        assertFalse(PasswordHasher.matches("wrongPassword", contrasenaEncriptada), "La " +
                "contraseña incorrecta no debe coincidir con la codificada");
    }

    /**
     * Prueba que la función encodePassword maneje correctamente contraseñas vacías.
     *
     * Valida que:
     * - La contraseña codificada no sea nula.
     * - La contraseña codificada no esté vacía.
     */
    @Test
    void testEncodeEmptyPassword() {
        String contrasena = "";

        // Codificar la contraseña vacía
        String contrasenaEncriptada = PasswordHasher.encodePassword(contrasena);

        // Verificar que la contraseña codificada no sea nula o vacía
        assertNotNull(contrasenaEncriptada, "La contraseña codificada no debe ser nula");
        assertFalse(contrasenaEncriptada.isEmpty(), "La contraseña codificada no debe estar vacía");
    }

    /**
     * Prueba que la función matches falle cuando se compara una contraseña vacía
     * con una contraseña codificada.
     */
    @Test
    void testMatchesWithEmptyPassword() {
        String contrasena = "";
        String contrasenaEncriptada = PasswordHasher.encodePassword("somePassword");

        // Verificar que una contraseña vacía no coincida
        assertFalse(PasswordHasher.matches(contrasena, contrasenaEncriptada), "Una contraseña vacía no" +
                " debe coincidir con la codificada");
    }

    /**
     * Prueba que la función matches falle cuando se intenta comparar una contraseña nula
     * con una contraseña codificada.
     */
    @Test
    void testMatchesWithNullPassword() {
        String contrasenaEncriptada = PasswordHasher.encodePassword("password123");

        // Verificar que la comparación con una contraseña nula falle
        assertFalse(PasswordHasher.matches(null, contrasenaEncriptada), "Una contraseña nula no" +
                " debe coincidir con la codificada");
    }
}