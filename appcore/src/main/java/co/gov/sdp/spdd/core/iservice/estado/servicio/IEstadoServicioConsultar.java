package co.gov.sdp.spdd.core.iservice.estado.servicio;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que obtiene los metodos de consultar asociados a un estado de
 * servicio
 *
 * @author Bryan Munoz
 *
 */
public interface IEstadoServicioConsultar {

    /**
     * Metodo que obtiene todos los estados de servicios
     *
     * @return una lista con un codigo y un mensaje de respuesta
     */
    public GenericoDTO obtenerTodos(EstadoServicioDTO peticion) throws SpddExceptions;
}
