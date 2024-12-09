package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelos.Coordinador;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para validar las restricciones y el comportamiento de la clase Coordinador.
 * Esta clase utiliza un validador de Jakarta Validation para verificar que los valores asignados
 * a los atributos de la clase Coordinador cumplan con las restricciones definidas en sus
 * anotaciones.
 */
class CoordinadorTest {
    private Validator validator;

    /**
     * Configura el validador antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Prueba que el atributo "número de personal" no puede estar vacío.
     *
     * Valida que, si se asigna un valor vacío al número de personal, se genere una
     * violación de validación con el mensaje correspondiente.
     */
    @Test
    void testNoPersonalNoVacio() {
        Coordinador coordinador = new Coordinador();
        coordinador.setNoPersonal("");  // Número de personal vacío

        Set<ConstraintViolation<Coordinador>> violations = validator.validate(coordinador);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para el número de personal" +
                " vacío");
    }

    /**
     * Prueba que el atributo "número de personal" debe tener al menos 5 caracteres.
     *
     * Valida que, si el número de personal tiene menos de 5 caracteres, se genere una
     * violación de validación con el mensaje adecuado.
     */
    @Test
    void testNoPersonalTamanioMinimo() {
        Coordinador coordinador = new Coordinador();
        coordinador.setNoPersonal("123");  // Número de personal con menos de 5 caracteres

        Set<ConstraintViolation<Coordinador>> violations = validator.validate(coordinador);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para el número de personal" +
                " con tamaño mínimo incorrecto");
        assertEquals("El número de personal no debe ser menor a 5 caracteres",
                violations.iterator().next().getMessage());
    }

    /**
     * Prueba que el atributo "número de personal" no puede exceder los 15 caracteres.
     *
     * Valida que, si el número de personal tiene más de 15 caracteres, se genere una
     * violación de validación con el mensaje adecuado.
     */
    @Test
    void testNoPersonalTamanioMaximo() {
        Coordinador coordinador = new Coordinador();
        coordinador.setNoPersonal("1234567890123456");  // Número de personal con más de 15 caracteres

        Set<ConstraintViolation<Coordinador>> violations = validator.validate(coordinador);
        assertFalse(violations.isEmpty(), "Debe haber una violación de validación para el número de personal" +
                " con tamaño máximo incorrecto");
        assertEquals("El número de personal no debe exceder los 15 caracteres",
                violations.iterator().next().getMessage());
    }

    /**
     * Prueba que el atributo "número de personal" sea válido cuando se encuentra
     * dentro del rango permitido (entre 5 y 15 caracteres).
     *
     * Valida que no se generen violaciones de validación para un número de personal
     * válido.
     */
    @Test
    void testNoPersonalValido() {
        Coordinador coordinador = new Coordinador();
        coordinador.setNoPersonal("12345");  // Número de personal válido

        Set<ConstraintViolation<Coordinador>> violations = validator.validate(coordinador);
        assertTrue(violations.isEmpty(), "No debe haber violaciones de validación para el número de personal" +
                " válido");
    }

    /**
     * Prueba que la validación de la clase Coordinador lanza una excepción si el
     * número de personal no cumple con las restricciones.
     *
     * Valida que el método {@code validate()} de la clase Coordinador genere una
     * {@link ConstraintViolationException} cuando las restricciones no se cumplen.
     */
    @Test
    void testValidateConExcepcion() {
        Coordinador coordinador = new Coordinador();
        coordinador.setNoPersonal("123");  // Número de personal con menos de 5 caracteres

        // Validar que se lanza una excepción de violación de restricción
        assertThrows(ConstraintViolationException.class, coordinador::validate);
    }
}