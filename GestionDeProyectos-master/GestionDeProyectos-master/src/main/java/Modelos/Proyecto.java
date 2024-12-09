package Modelos;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * Clase que representa un proyecto en el sistema. Un proyecto tiene un nombre,
 * una descripción y un identificador único.
 *
 * Las restricciones de validación aseguran que el nombre del proyecto no esté vacío
 * y no exceda los 100 caracteres, y que la descripción no exceda los 1000 caracteres.
 */
public class Proyecto {

    /**
     * Nombre del proyecto. No puede estar vacío y debe tener un máximo de 100 caracteres.
     */
    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(max = 100, message = "El nombre del proyecto no debe exceder los 100 caracteres")
    private String Nombre;

    /**
     * Descripción del proyecto. No puede estar vacía y no debe exceder los 1000 caracteres.
     */
    @NotBlank(message = "Se debe anadir una descripcion")
    @Size(max = 1000, message = "El nombre del proyecto no debe exceder los 1000 caracteres")
    private String Descripcion;

    /**
     * Identificador único del proyecto.
     */
    private String proyectoId;

    /**
     * Constructor por defecto de la clase Proyecto.
     */
    public Proyecto() {}

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {}

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    /**
     * Valida los atributos del proyecto. Si alguna restricción no se cumple,
     * lanza una excepción {@link ConstraintViolationException}.
     */
    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            ConstraintViolation<Proyecto> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}