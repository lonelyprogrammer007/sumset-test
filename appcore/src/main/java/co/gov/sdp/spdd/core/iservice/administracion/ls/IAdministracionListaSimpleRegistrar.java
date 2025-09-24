package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja todos los metodos asociados con el registro de listas
 * simples
 *
 * @author Bryan Munoz
 *
 */
public interface IAdministracionListaSimpleRegistrar {

    /**
     * Metodo que permite registrar una lista simple
     *
     * @param listaSimpleDTO campos con los cuales se crea la entidad lista
     * simple
     * @return el objeto dto con un codigo y mensaje de respuesta
     */
    public ListaSimpleDTO guardarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions;
}
