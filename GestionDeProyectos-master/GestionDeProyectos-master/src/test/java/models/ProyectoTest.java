package models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import Modelos.Proyecto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

/**
 * Clase de pruebas para validar las restricciones y comportamientos de la clase Proyecto.
 * Esta clase utiliza Jakarta Validation para garantizar que los valores de los atributos
 * de un Proyecto cumplan con las restricciones definidas, como la longitud y contenido
 * de los campos Nombre y Descripción.
 */
class ProyectoTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Prueba que un proyecto con valores válidos en sus atributos no genera violaciones de validación.
     */
    @Test
    public void testValidProyecto() {
        // Crear un proyecto válido
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto de Prueba");
        proyecto.setDescripcion("Descripción válida del proyecto.");

        // Validar el proyecto
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(proyecto);

        // No deben existir violaciones
        assertTrue(violations.isEmpty(), "El proyecto debería ser válido");
    }

    /**
     * Prueba que el atributo Nombre del proyecto no puede estar vacío.
     *
     * Valida que se genere una violación de validación si el Nombre es una cadena vacía
     * y que el mensaje de error sea el esperado.
     */
    @Test
    public void testNombreVacio() {
        // Crear un proyecto con el nombre vacío
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("");
        proyecto.setDescripcion("Una descripción válida del proyecto.");

        // Validar el proyecto
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(proyecto);

        // Debe haber una violación por el campo Nombre
        assertFalse(violations.isEmpty(), "Debería haber una violación de validación por el nombre vacío");

        // Comprobar que la violación es sobre el campo 'Nombre'
        assertEquals("El nombre del proyecto no puede estar vacío",
                violations.iterator().next().getMessage());
    }

    /**
     * Prueba que el atributo Descripción del proyecto no puede estar vacío.
     *
     * Valida que se genere una violación de validación si la Descripción es una cadena vacía
     * y que el mensaje de error sea el esperado.
     */
    @Test
    public void testDescripcionVacia() {
        // Crear un proyecto con la descripción vacía
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto de Prueba");
        proyecto.setDescripcion("");

        // Validar el proyecto
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(proyecto);

        // Debe haber una violación por el campo Descripción
        assertFalse(violations.isEmpty(), "Debería haber una violación de " +
                "validación por la descripción vacía");

        // Comprobar que la violación es sobre el campo 'Descripcion'
        assertEquals("Se debe anadir una descripcion", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que el atributo Nombre del proyecto no exceda los 100 caracteres.
     *
     * Valida que se genere una violación de validación si el Nombre supera esta longitud
     * y que el mensaje de error sea el esperado.
     */
    @Test
    public void testNombreExcedeLongitudMaxima() {
        // Crear un proyecto con el nombre excediendo los 100 caracteres
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("P".repeat(101)); // 101 caracteres
        proyecto.setDescripcion("Descripción válida");

        // Validar el proyecto
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(proyecto);

        // Debe haber una violación por el campo 'Nombre' excediendo la longitud máxima
        assertFalse(violations.isEmpty(), "Debería haber una violación de " +
                "validación por el nombre demasiado largo");

        // Comprobar que la violación es sobre el campo 'Nombre'
        assertEquals("El nombre del proyecto no debe exceder los 100 caracteres",
                violations.iterator().next().getMessage());
    }

    /**
     * Prueba que el atributo Descripción del proyecto no exceda los 1000 caracteres.
     *
     * Valida que se genere una violación de validación si la Descripción supera esta longitud
     * y que el mensaje de error sea el esperado.
     */
    @Test
    public void testDescripcionExcedeLongitudMaxima() {
        // Crear un proyecto con la descripción excediendo los 1000 caracteres
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto de Prueba");
        proyecto.setDescripcion("D".repeat(1001)); // 1001 caracteres

        // Validar el proyecto
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(proyecto);

        // Debe haber una violación por el campo 'Descripcion' excediendo la longitud máxima
        assertFalse(violations.isEmpty(), "Debería haber una violación de validación " +
                "por la descripción demasiado larga");

        // Comprobar que la violación es sobre el campo 'Descripcion'
        assertEquals("El nombre del proyecto no debe exceder los 1000 caracteres",
                violations.iterator().next().getMessage());
    }
}