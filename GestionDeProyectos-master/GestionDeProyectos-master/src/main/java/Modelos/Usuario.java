package Modelos;

import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

/**
 * Clase abstracta que representa un usuario en el sistema.
 * Contiene los atributos comunes para los diferentes tipos de usuarios como nombre, correo,
 * contraseña, teléfono, dirección y género.
 * Además, proporciona un método para validar que los atributos cumplen con las restricciones definidas.
 */
abstract public class Usuario {

    /**
     * Nombre del usuario.
     * No puede estar vacío y debe tener un máximo de 100 caracteres.
     */
    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String Nombre;

    /**
     * ID del usuario, utilizado para identificar de forma única al usuario.
     * Puede ser un número de matrícula o un identificador similar.
     */
    String id;

    /**
     * Correo electrónico del usuario.
     * No puede estar vacío y debe tener un formato válido.
     * También tiene un límite de 100 caracteres.
     */
    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String email;

    /**
     * Contraseña del usuario.
     * Debe tener entre 6 y 15 caracteres.
     */
    @NotBlank(message = "Contrasena no puede estar vacía")
    @Size(min = 6, max = 15, message = "La contrasena debe tener de 6 a 15 caracteres")
    private String contrasena;

    /**
     * Teléfono del usuario.
     * Debe tener exactamente 10 caracteres.
     */
    @NotBlank(message = "Teléfono no puede estar vacío")
    @Size(min = 10, max = 10, message = "Número de teléfono inválido")
    private String Telefono;

    /**
     * Dirección del usuario.
     * No puede estar vacía y debe tener un máximo de 255 caracteres.
     */
    @NotBlank(message = "Dirección no puede estar vacía")
    @Size(max = 255, message = "La dirección debe ser más corta")
    private String Direccion;

    /**
     * Género del usuario.
     * Debe ser 'Masculino' o 'Femenino'.
     */
    @NotBlank(message = "Género no puede estar vacío")
    @Pattern(regexp = "Masculino|Femenino", message = "El género debe ser 'Masculino' o 'Femenino'")
    private String Genero;

    // Getters and setters without validation annotations

    public String getDireccion() {
        return Direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return Genero;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public void setId(String id) {
        this.id = id;
    }



    public
    @NotBlank(message = "Contrasena no puede estar vacía")
    @Size(min = 6, max = 15, message = "La contrasena debe tener de 6 a 15 caracteres")
    String getContrasena() {
        return contrasena;
    }

    public void setContrasena(
            @NotBlank(message = "Contrasena no puede estar vacía")
            @Size(min = 6, max = 15, message = "La contrasena debe tener de 6 a 15 caracteres")
            String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    /**
     * Constructor predeterminado que inicializa los valores con datos predeterminados.
     */
    public Usuario() {
        this.Nombre = "Sin nombre";
        this.id = "";
        this.email = "email@ejemplo.com";
        this.contrasena = "1234567";
        this.Telefono = "0000000000";
        this.Direccion = "Sin dirección";
        this.Genero = "Masculino"; 
    }

    /**
     * Valida que todos los atributos del usuario cumplan con las restricciones de validación definidas.
     * Si alguna validación falla, lanza una excepción `ConstraintViolationException`.
     */
    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuario>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            ConstraintViolation<Usuario> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}
