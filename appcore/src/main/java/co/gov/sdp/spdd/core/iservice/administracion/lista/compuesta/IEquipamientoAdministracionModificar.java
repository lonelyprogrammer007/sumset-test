package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEquipamientoAdministracionModificar {


    /**
     * Metodo para modificar Equipamiento
     *
     * @param peticion Objeto DTO con los datos para modificar el equipamiento
     * @return Objeto DTO informando la modificacion exitosa del equipamiento,
     * sino un objeto vacio con el mensaje de error correspondiente
     */
    public EquipamientoDTO modificarEquipamiento(EquipamientoDTO peticion) throws SpddExceptions;
}
