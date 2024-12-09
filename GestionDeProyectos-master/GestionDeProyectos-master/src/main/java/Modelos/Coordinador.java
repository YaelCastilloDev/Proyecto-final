package Modelos;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * Clase que representa a un Coordinador, que extiende de la clase {@link Usuario}.
 * Un coordinador tiene un número de personal único y es validado con restricciones específicas de tamaño.
 */
public class Coordinador extends Usuario {

    /**
     * Número de personal del coordinador. Este campo debe tener entre 5 y 15 caracteres.
     * No puede ser nulo ni estar vacío.
     */
    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 5, message = "El número de personal no debe ser menor a 5 caracteres")
    @Size(max = 15, message = "El número de personal no debe exceder los 15 caracteres")
    private String NoPersonal;

    public String getNoPersonal() {
        return NoPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.NoPersonal = noPersonal;
    }

    /**
     * Valida los campos del coordinador, incluyendo las restricciones definidas
     * en la clase y las de la clase {@link Usuario} (padre).
     *
     * Si alguna restricción de validación no se cumple, se lanza una excepción
     * {@link ConstraintViolationException}.
     */
    public void validate() {
        super.validate(); // Validate parent fields
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Coordinador>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            ConstraintViolation<Coordinador> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}