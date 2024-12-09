package DAO.Proyecto;

import Modelos.Proyecto;
import jakarta.validation.ConstraintViolationException;

/**
 * Clase de utilidad que proporciona métodos para asignar valores a las propiedades
 * de un objeto {@link Proyecto} y validar estos datos utilizando restricciones de validación.
 */
public class Utiles {
    Proyecto proyecto = new Proyecto();
    /**
     * Asigna valores al nombre y descripción de un proyecto y realiza la validación de las propiedades del objeto.
     *
     * Este método asigna los valores proporcionados al proyecto, los valida de acuerdo con las restricciones
     * definidas en el modelo, y lanza una excepción si alguna validación falla.
     *
     * @param nombre      El nombre del proyecto a asignar.
     * @param descripcion La descripción del proyecto a asignar.
     * @throws ConstraintViolationException Si alguna de las propiedades del proyecto no cumple con las
     *                                      restricciones de validación definidas en el modelo.
     */
    public void AsignarRegistro(String nombre, String descripcion) throws ConstraintViolationException {
        try {
            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.validate(); 
        } catch (ConstraintViolationException e) {
            System.err.println("Error de validación: " + e.getMessage());
            throw e;
        }
    }
}
