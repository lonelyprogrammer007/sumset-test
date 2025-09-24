package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEquipamientoAdministracionRegistrar {

    /**
     * Metodo para crear equipamiento
     *
     * @param peticion Objeto DTO con los datos para crear el equipamiento
     * @return Objeto DTO informando la creacion exitosa del equipamiento, sino
     * un objeto vacio con el mensaje de error correspondiente
     */
    public EquipamientoDTO crearEquipamiento(EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException;

}
