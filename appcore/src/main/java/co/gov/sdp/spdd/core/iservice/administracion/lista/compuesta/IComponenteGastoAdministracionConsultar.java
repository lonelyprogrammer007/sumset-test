package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de admnistracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IComponenteGastoAdministracionConsultar {

    /**
     * Metodo para obtener la lista de todos los componentes de gasto
     *
     * @return Objeto Generico que contiene la lista de todos los componentes de
     * gasto
     * @throws SpddExceptions
     */
    public GenericoDTO obtenerComponenteGastoTodos(ComponenteGastoDTO peticion) throws SpddExceptions;

}
