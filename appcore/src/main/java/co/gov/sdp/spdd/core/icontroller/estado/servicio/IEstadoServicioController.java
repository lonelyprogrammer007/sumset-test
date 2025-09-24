package co.gov.sdp.spdd.core.icontroller.estado.servicio;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;

/**
 * Intergace maneja los metodos del controlador de estado de servicio
 *
 * @author Bryan Munoz
 *
 */
public interface IEstadoServicioController {

    /**
     * Metodo que permite obtener todos los estados de servicio
     *
     * @return retorna una lista con un codigo y un objeto
     */
    public GenericoDTO obtenerTodos(EstadoServicioDTO peticion);
}
