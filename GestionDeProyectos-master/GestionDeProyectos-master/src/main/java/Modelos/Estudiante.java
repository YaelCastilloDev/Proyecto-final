package Modelos;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * Clase que representa a un Estudiante, que extiende de la clase {@link Usuario}.
 * Un estudiante tiene una matrícula única que debe cumplir con restricciones específicas de longitud y formato.
 */
public class Estudiante extends Usuario {

    /**
     * Matrícula del estudiante. Debe tener 9 caracteres y seguir el formato:
     * "S" seguido de 8 dígitos numéricos.
     */
    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
    @Pattern(regexp = "S\\d{8}", message = "El ID debe comenzar con 'S' seguido de 8 dígitos")
    private String Matricula;

    /**
     * Constructor de la clase Estudiante. Inicializa la matrícula con un valor predeterminado.
     */
    public Estudiante() {
        super(); 
        this.Matricula = "S00000000"; 
    }

    public @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
    String getMatricula() {
        return Matricula;
    }

    public void setMatricula(@NotBlank(message = "Número de personal no puede estar vacío")
                             @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
                             String matricula) {
        this.Matricula = matricula;
    }

    /**
     * Valida los campos del estudiante, incluyendo las restricciones definidas
     * en la clase y las de la clase {@link Usuario} (padre).
     *
     * Si alguna restricción de validación no se cumple, se lanza una excepción
     * {@link ConstraintViolationException}.
     */
    public void validate() {
        super.validate(); // Validar campos de la clase padre
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Estudiante>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            ConstraintViolation<Estudiante> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}
