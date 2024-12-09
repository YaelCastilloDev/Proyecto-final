package DAO.Estudiante;
import Modelos.Estudiante;
import jakarta.validation.ConstraintViolationException;

/**
 * Clase de utilidad que proporciona métodos para asignar valores a las propiedades de un objeto {@link Estudiante}
 * y validar estos datos utilizando restricciones de validación.
 * 
 * Los métodos de esta clase permiten asignar valores a los campos de un objeto {@link Estudiante} y aseguran
 * que los datos cumplan con las reglas de validación definidas en el modelo. Si los datos no son válidos, se lanza
 * una {@link jakarta.validation.ConstraintViolationException}.
 */

public class Utiles {
    Estudiante estudiante = new Estudiante();
    /**
     * Asigna valores a las propiedades necesarias para registrar un estudiante.
     * Este método asigna el correo electrónico, la contraseña y la matrícula de un estudiante, 
     * valida que estos valores cumplan con las restricciones definidas y lanza excepciones 
     * si los valores son inválidos.
     *
     * @param email      El correo electrónico del estudiante. No debe estar vacío y debe cumplir con el formato de email.
     * @param contrasena La contraseña del estudiante. Debe cumplir con las restricciones definidas en el modelo.
     * @param matricula  El número de matrícula del estudiante. Debe cumplir con las restricciones definidas en el modelo.
     * @throws ConstraintViolationException Si alguna de las restricciones de validación en el objeto {@link Estudiante} no se cumple.
     */
    public void asignarRegistroEstudiante(String email, String contrasena,
                                          String matricula) throws ConstraintViolationException {
        estudiante.setContrasena(contrasena);
        estudiante.setEmail(email);
        estudiante.setMatricula(matricula);
        estudiante.validate(); // Puede lanzar ConstraintViolationException
    }

    /**
     * Asigna valores a las propiedades personales de un estudiante para su actualización.
     * Este método establece los valores del teléfono, nombre, dirección y género de un estudiante,
     * valida que estos datos cumplan con los requisitos definidos en el modelo.
     *
     * @param telefono   El número de teléfono actualizado del estudiante. No debe estar vacío.
     * @param nombre     El nombre actualizado del estudiante. No debe estar vacío.
     * @param direccion  La dirección actualizada del estudiante. No debe estar vacía.
     * @param genero     El género actualizado del estudiante. No debe estar vacío.
     * @throws ConstraintViolationException Si alguna de las restricciones de validación en el objeto {@link Estudiante} no se cumple.
     */
    public void asignarActualizarEstudiante(String telefono, String nombre,
                                            String direccion, String genero) throws ConstraintViolationException {
        estudiante.setTelefono(telefono);
        estudiante.setNombre(nombre);
        estudiante.setDireccion(direccion);
        estudiante.setGenero(genero);
        estudiante.validate(); // Puede lanzar ConstraintViolationException
    }
}