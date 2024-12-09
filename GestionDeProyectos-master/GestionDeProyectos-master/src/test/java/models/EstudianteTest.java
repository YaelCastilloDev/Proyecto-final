package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelos.Estudiante;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para validar las restricciones y el comportamiento de la clase Estudiante.
 * Esta clase utiliza Jakarta Validation para verificar que los valores asignados
 * al atributo matrícula de la clase Estudiante cumplan con las restricciones definidas.
 */
class EstudianteTest {
    private Validator validator;

    /**
     * Configura el validador antes de ejecutar cada prueba.
     */
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Prueba que el atributo "matrícula" sea válido cuando sigue las reglas definidas
     * (debe comenzar con 'S' seguido de 8 dígitos).
     *
     * Valida que no se generen violaciones para una matrícula válida.
     */
    @Test
    void testMatriculaValida() {
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula("S12345678");  // Matricula válida

        Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
        assertTrue(violations.isEmpty(), "No debe haber violaciones de validación para matrícula válida");
    }

    /**
     * Prueba que el atributo "matrícula" no puede estar vacío.
     *
     * Valida que se genere una violación de validación si la matrícula se establece como
     * una cadena vacía.
     */
    @Test
    void testMatriculaNoVacia() {
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula("");  // Matrícula vacía

        Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para matrícula vacía");
    }

    /**
     * Prueba que el atributo "matrícula" tenga exactamente 9 caracteres.
     *
     * Valida que se genere una violación de validación si la matrícula tiene menos
     * o más de 9 caracteres.
     */
    @Test
    void testMatriculaTamanoIncorrecto() {
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula("S1234567");  // Matrícula con menos de 9 caracteres

        Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para matrícula con tamaño " +
                "incorrecto");
    }

    /**
     * Prueba que el atributo "matrícula" siga el formato correcto.
     *
     * Valida que se genere una violación de validación si la matrícula no comienza con
     * la letra "S" seguida de 8 dígitos.
     */
    @Test
    void testMatriculaFormatoIncorrecto() {
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula("A12345678");  // Matrícula con formato incorrecto (no comienza con "S")

        Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para matrícula con" +
                " formato incorrecto");
        assertEquals("El ID debe comenzar con 'S' seguido de 8 dígitos", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que la validación de la clase Estudiante lanza una excepción
     * {@link ConstraintViolationException} si la matrícula no cumple con las reglas.
     *
     * Valida que el método {@code validate()} de la clase Estudiante arroje una
     * excepción cuando se detecten violaciones de validación.
     */
    @Test
    void testValidateConExcepcion() {
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula("A12345678");  // Formato incorrecto

        // Validar que se lanza una excepción
        assertThrows(ConstraintViolationException.class, estudiante::validate);
    }
}